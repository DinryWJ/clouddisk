package com.dinry.clouddisk.common;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author: 吴佳杰
 * @Date: 2018/11/19 16:40
 * @Description:
 */
public class RandomString {
    public static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static void main(String[] args) {

        System.out.println(RandomString.generateString(new Random(), 4));

    }

    /**
     * Generate a random string.
     *
     * @param random     the random number generator.
     * @param length     the length of the generated string.
     * @return
     */
    public static String generateString(Random random, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }
}
