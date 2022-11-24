/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/29/22, 11:11 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.viyunsion.cefc.common.utils.CollectionUtils;
import com.viyunsion.cefc.modules.sys.model.SysMenu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SysMenuServiceImplTest {

    @Test
    void testListByParentId() {

        SysMenu $val1 = new SysMenu();
        SysMenu $val2 = new SysMenu();
        SysMenu $val3 = new SysMenu();

        List<Long> ids = new ArrayList<>() {{
            add(1L);
            add(3L);
            add(5L);
            add(6L);
        }};

        List<SysMenu> menuList = new ArrayList<>() {{
            $val1.setMenuId(1L);
            $val2.setMenuId(2L);
            $val3.setMenuId(3L);

            add($val1);
            add($val2);
            add($val3);
        }};


        List<SysMenu> menuList2 = new ArrayList<>() {{
            $val2.setMenuId(2L);
            $val3.setMenuId(3L);

            add($val2);
            add($val3);
        }};

        List<SysMenu> intersection = CollectionUtils.intersection(menuList, ids,
                (target, ref) -> ref.equals(target.getMenuId())
        );
        List<SysMenu> intersection2 = CollectionUtils.intersection(menuList, menuList2);

        // 1, 3
        intersection.forEach(System.out::println);
        System.out.println("-------------------------------");

        // 2, 3
        intersection2.forEach(System.out::println);
    }

    @Test
    void testListNotButton() {
    }

    @Test
    void listUserMenu() {
    }

    @Test
    void delete() {
    }
}