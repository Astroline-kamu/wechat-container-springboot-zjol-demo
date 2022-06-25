/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/23/22, 11:02 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void format() throws ParseException {
        System.out.println(
                DateUtils.format("2022-02-01")
        );

        System.out.println(
                DateUtils.format("2022-02-01 23:20:09")
        );

        System.out.println(
                DateUtils.format(new Date())
        );

        System.out.println(
                DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")
        );
    }

    @Test
    void getRange() {
        System.out.println(
                Arrays.toString(
                        Arrays.stream(DateUtils.getWeekRange(1))
                                .map(DateUtils::format)
                                .toArray()
                )
        );
        System.out.println(
                Arrays.toString(
                        Arrays.stream(DateUtils.getMonthRange(0))
                                .map(DateUtils::format)
                                .toArray()
                )
        );
        System.out.println(
                Arrays.toString(
                        Arrays.stream(DateUtils.getYearRange(1))
                                .map(DateUtils::format)
                                .toArray()
                )
        );
    }
}