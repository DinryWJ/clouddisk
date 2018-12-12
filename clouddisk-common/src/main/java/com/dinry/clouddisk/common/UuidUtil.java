package com.dinry.clouddisk.common;

import java.util.UUID;

/**
 * @Author: 吴佳杰
 * @Date: 2018/12/12 16:04
 * @Description:
 */
public class UuidUtil {
    /**
     * 功能：去掉UUID中的“-”
     *     
     */
    public static String replaceUUID(String uuid) {
        uuid = uuid.replaceAll("\\-", "");
        return uuid;
    }

    /**
     * 功能：获取UUID并去除“-”
     *     
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("\\-", "");
    }
}
