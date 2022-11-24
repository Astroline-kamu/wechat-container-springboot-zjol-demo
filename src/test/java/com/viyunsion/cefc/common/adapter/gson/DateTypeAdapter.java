/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/18/22, 12:27 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.adapter.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.viyunsion.cefc.common.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class DateTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {
        try {
            System.out.println(o.toString());
            if (o.equals("")) o = null;
            jsonWriter.value((String) o);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object read(JsonReader jsonReader) {
        try {
            Date value;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            if (jsonReader.peek() == JsonToken.BOOLEAN) {
                boolean b = jsonReader.nextBoolean();
                return null;
            }
            if (jsonReader.peek() == JsonToken.STRING) {
                String str = jsonReader.nextString();
                if (StringUtils.isEmpty(str)) {
                    return null;
                } else {
                    return DateTimeUtils.format(str);
                }
            } else {
                String str = jsonReader.nextString();
                value = DateTimeUtils.format(str);
            }
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
