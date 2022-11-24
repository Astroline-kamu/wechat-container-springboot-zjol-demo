/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/18/22, 5:30 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.common.chunk.model;

import com.viyunsion.cefc.modules.app.sales_order.common.chunk.helper.PojoChunkHelper;
import com.viyunsion.cefc.modules.app.sales_order.common.chunk.worker.ChunkWorker;
import com.viyunsion.cefc.modules.app.sales_order.common.chunk.worker.Classifier;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 * @Params ChunkKey要求：
 * 是pojo属性名（无视首字母大小写）
 * 在pojo中拥有相对应的get方法
 * <p>
 * PojoChunk是一种Pojo to Array类型的数据格式
 * 分为 键对和值对（头标记） 以及一个数据源
 * chunk是一个二维数组，用来存放数据列表，用键值对标记数据的长度
 * 可以修改、补充、删除中间的内容
 * <p>
 * 想想看，这确实挺酷的；但是再想想看，数据是要静态化的，想要使用这种数据格式必须要经常读取重写，要二次加工，就显得得不偿失了（除非将keys和唯一键值单独存放）
 * <p>
 * 待完成特性：
 * 支持以Plugin的方式进行补充功能，如：
 * ClassifyPlugin
 * SortPlugin
 * ContainsPlugin
 * ... 等诸如此类
 */
public class PojoChunk<T> {

    // 为了保障可以双向存储，可能有必要保存类型 getGenericType （两种思路：第一种自己写一种模式进行转换 第二种用Gson进行反射转换）
    // 第二种思路 数据的修改直接反射pojo方法进行操作
    // 第三种思路 将所有数据处于一种半反射的状态，操作结束抽出代码（相对理想，但是其实和没说一样）
    // 目前写的 反射成一种数据结果进行操作，操作结束后反射回去（最好支持直接传入List<List<Object>>）

    // 唯一地址（序列）由出现角标的列表计算出
    // idCol是Pojo一个字段
    PojoChunk(List<T> pojoList, Class<T> clazz, Collection<String> slice, String idCol) {

        this.helper = new PojoChunkHelper<>(clazz);

        if (idCol != null && idCol.isEmpty()) idCol = null;
        this.idCol = idCol;

        if (slice == null) chunkKeys = Arrays.stream(clazz.getDeclaredMethods())
                // boolean类型的是isBoolean
                .filter(n -> n.getName().startsWith("get") || n.getName().startsWith("is"))
                .collect(Collectors.toList());
        else chunkKeys = helper.sliceCks(slice);


        // 内容键值
//        this.chunkKeys = new ArrayList<>(chunkKeys);

        // String表统一首字母大写 你说为什么大写？因为好看！
        for (Method method : chunkKeys) {
            String name = method.getName().startsWith("get")
                    ? method.getName().substring(3)
                    : method.getName().substring(2);
            // 注意这里的顺序，最后才能是取值的数组
            this.colName.put(name, colIndex.size());
            this.colIndex.add(colIndex.size());
        }

        // colIndex & chunk
        this.generalChunk(pojoList);
    }

    // todo slice作为静态数据抛出 可嵌入、替换 基于元数据的直接操作

    /**
     * 一般使用：创建一个Vo，通过单Class的方式读取
     * 非一般使用：用同一个Pojo读存，只有相关字段写入Pojo
     *
     * @param pojoList PojoList
     * @param clazz    PojoClass
     * @param slice    PojoField
     */
    PojoChunk(List<T> pojoList, Class<T> clazz, Collection<String> slice) {
        this(pojoList, clazz, slice, null);
    }

    public PojoChunk(List<T> pojoList, Class<T> clazz, String idCol) {
        this(pojoList,
                clazz,
                null,
                idCol
        );
    }

    public PojoChunk(List<T> pojoList, Class<T> clazz) {
        this(pojoList, clazz, "");
    }

    PojoChunk(Class<T> clazz) {
        this(new ArrayList<>(), clazz);
    }

    // 哨兵前缀 比如classify就要用到这个哨兵位
    private final String guardPrefix = "chunk:key:";

    PojoChunkHelper<T> helper;

    // 确保唯一id的列 内容不得重复 用于数据删改
    String idCol;

    // 单行内容
    List<Object> row = new ArrayList<>();

    // 数据主体
    List<List<Object>> chunk = new ArrayList<>();

    // ----------------------------------------------------------------------------------------------------------------
    // 数列的键值对，用来转换到Index，暂不支持行聚合操作。
    // 可追加哨兵位
    Map<String, Integer> colName = new HashMap<>();

    // 具体键值对 不输入则默认排列 - 永远固定的 后会追加哨兵字段
    List<Method> chunkKeys;

    // 数列的角标 对应转换后的结果 根据返回内容进行调整 程序初始化后就不会变更了
    List<Integer> colIndex = new ArrayList<>();
    // ----------------------------------------------------------------------------------------------------------------

    // 数行的角标 对应转换后的结果 根据返回内容进行调整 - 排序依据
    List<Integer> rowIndex = new ArrayList<>();

    @SuppressWarnings("未完成代码")
    void add(T pojo) {
        chunk.add(helper.pojo2array(pojo, this.chunkKeys));
        // 如果有Classify的话需要添加Classify
    }

    @SuppressWarnings("未完成代码")
    void remove(String idCol) {
//        remove(chunkKeys.get(idCol));
    }

    @SuppressWarnings("未完成代码")
    void remove(Integer col) {
        if (this.idCol == null) return;
        this.chunk = chunk.stream().filter(c -> c.get(col) == this.idCol).collect(Collectors.toList());
    }

    private void addCol(String name, Classifier classifier) {
        this.colName.put(name, this.colIndex.size());
        this.colIndex.add(this.colIndex.size());
        for (List<Object> list :
                this.chunk) {
            classifier.action(list);
        }
    }

    private void setCol(String name, Classifier classifier) {
        for (List<Object> list :
                this.chunk) {
            classifier.action(list);
        }
    }

    public PojoChunk<T> classifyBy(List<String> cols) {
        return classifyBy(
                cols.stream().map(col -> colName.get(col
                        .substring(0, 1).toUpperCase(Locale.ROOT) +
                        col.substring(1)
                )).collect(Collectors.toList()), true);
    }

    /**
     * 以后考虑加一个Pattern字段 单行的判断规则（Comparator）
     *
     * @param cols IndexList的角标
     * @param isIndex 单纯区分接口用的，貌似因为都是List类型，所以他们被识别成了同一个，不触发重载
     */
    public PojoChunk<T> classifyBy(List<Integer> cols, boolean isIndex) {

        String classifyName = this.guardPrefix + "classify:" + cols;


//        Collections.reverse(cols);
        HashMap<Integer, Set<Object>> dic = new HashMap<>() {{
            for (Integer i :
                    cols) {
                put(i, new HashSet<>());
            }
            ;
        }};

        int[] count = new int[this.colIndex.size()];

        addCol(classifyName, sourceList -> {
            int i = 0;
            int res = 0;
            for (Integer ci :
                    cols) {
                if (!dic.get(ci).contains(sourceList.get(ci))) {
                    dic.get(ci).add(sourceList.get(ci));
                    count[ci] += 1;
                }
                i = i + count[ci];
                res += i * i;
                System.out.println(Arrays.toString(count));

            }
//            sourceList.add(Math.sqrt(res));
            sourceList.add(res);
            System.out.println(res);
        });

        this.chunk.forEach(val -> {
            System.out.println(val.get(this.colName.get(classifyName)));
        });
        return this;
    }

    // todo 外部接口 - 针对这种数据格式进行的操作的API 可以做两个方法，一种返回原Pojo列表 一种就是现在实现的二维数组
    List<List<Object>> pivotByClassify() {
        return this.chunk;
    }


    public List<List<Object>> toArray() {
        return chunk;
    }


    private void generalChunk(List<T> pojoList) {
        // 键值分组 生成一个
        for (T pojo :
                pojoList) {
            this.chunk.add(helper.pojo2array(pojo, this.chunkKeys));
            this.rowIndex.add(this.rowIndex.size());
        }
    }


}
