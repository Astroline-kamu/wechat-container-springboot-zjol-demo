/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/29/22, 11:34 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ? extends T 存上界 只能父类向上
 * ? super T 存下界 只能父类向下
 * <p>
 *
 * 这个工具类参考了Collection的sort的实现 这个实现太巧妙了，无法抗拒的想要实现
 * 这里还有一个思想：如果操作的是List，需要让它继承List类，这样才能直接从this调用到原始数据——而这正是sort的实现。
 * 但我设计里这是个多个集合的工具，所以我暂时不考虑给List单独开一个增强类。
 *
 * @author Niyredra Astroline_kamu@outlook.com
 * @see List
 */
public class CollectionUtils {

    /**
     * 两个相同类型的List中取交集
     * 结果返回交集的内容（直接修改传入的target或许会比较优雅，但是怕使用者不懂指针，然后看到自己的数据莫名其妙便少了qwq）
     *
     * @param target    主体List 选取的交集内容（包括返回类）以它为准
     * @param ref       参照List
     * @param <T>       target.class
     * @return List T   交集内容
     */
    public static <T> List<T> intersection(List<T> target, List<T> ref) {
        List<T> res = new ArrayList<>();
        for (T s : ref)
            if (target.contains(s))
                res.add(s);
        return res;
    }

    /**
     * 两个不同类型的List中取交集
     *
     * @param target    主体List 选取的交集内容（包括返回类）以它为准（如果该工具继承了List类，那么应当是target.intersection这种形式）
     * @param reference 参照List 用于判断项
     * @param callback  判断条件
     * @param <T>       target.class
     * @param <E>       reference.class
     * @return List T   IntersectionList T instanceof target:class
     */
    public static <T, E> List<T> intersection(List<T> target, List<E> reference,
                                              InterSectionCallback<? super T, E> callback) {
        List<T> res = new ArrayList<>();
        // 讨厌的两次循环...这到底能怎么优化啊QAQ
        for (T t : target)
            for (E r : reference)
                if (callback.contains(t, r)) {
                    res.add(t);
                    break;
                }
        return res;
    }

    public interface InterSectionCallback<T, E> {
        boolean contains(T target, E ref);
    }
}
