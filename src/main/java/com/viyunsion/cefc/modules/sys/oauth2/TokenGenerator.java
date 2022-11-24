/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 4:07 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.oauth2;

import com.viyunsion.cefc.common.exception.ArException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class TokenGenerator {
    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ArException("生产Token失败", e);
        }
    }

    public static String toHexString(byte[] bytes) {
        if (bytes == null) return null;
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b :
                bytes)
            sb.append(hexCode[(b >> 4) & 0xF])
                    .append(hexCode[(b & 0xF)]);
        return sb.toString();
    }


}
