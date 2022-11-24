/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/18/22, 6:03 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.common.chunk.helper;

import com.viyunsion.cefc.modules.app.sales_order.common.chunk.model.PojoChunk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 用来处理转换规则，此处会有一套默认转换规则，类似Gson的TypeAdapter，可以注册不同的Helper
 * 1. 这个类需要是抽象的 可以被实现的
 * 2. 目前看来好像没有拆出来的必要
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class PojoChunkHelper<T> {

    private HashMap<String, Method> methodMap = new HashMap<>();

    public PojoChunkHelper(Class<T> clazz){
        getColNameMap(clazz);
    }

    public HashMap<String, Method> getColNameMap(Class<T> clazz){
        for (Method method :
                clazz.getDeclaredMethods()) {
            String name = method.getName();

            // 这种情况下还要考虑用正则判断么？而且为什么要判断啊？可能put方法应该比startsWith更占性能的吧？啊？？？
            if (!(name.startsWith("get")
                    || name.startsWith("is"))) continue;

            if (name.startsWith("get")) name = name.substring(3);
            else if (name.startsWith("is")) name = name.substring(2);
            this.methodMap.put(name, method);
        }
        return this.methodMap;
    }

    /**
     * sliceCke -> slice chunk keys 针对chunk的key进行拆分
     * 根据Class的所有字段进行拆分
     *
     * @param slice 分片的部分
     * @return 返回分片后的Method列表
     */
    public List<Method> sliceCks(Collection<String> slice) {

        if (slice.isEmpty()) return new ArrayList<>();
        // 啊？为了防止有些好奇的猫给我弄一堆重复的数据，我只好用Set去重咯？反正HashSet也是extend了Collection的对吧～
        LinkedHashSet<String> set = slice.stream()
                .map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return set.stream()
                .map(this.methodMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * Pojo对象变成Array的方法
     *
     * @param pojo pojo对象本象
     * @param cks 需要变换的对应字段
     * @return Array
     */
    public List<Object> pojo2array(T pojo, List<Method> cks) {
        Method[] source = cks.toArray(Method[]::new);
        return Arrays.stream(source).sequential()
                .map(method -> {
                    Object res;
                    try {
                        res = method.invoke(pojo);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    return res;
                })
                .collect(Collectors.toList());
    }


    public T arrayToPojo(Object[] arr, List<Method> cks){
        return null;
    }


}
