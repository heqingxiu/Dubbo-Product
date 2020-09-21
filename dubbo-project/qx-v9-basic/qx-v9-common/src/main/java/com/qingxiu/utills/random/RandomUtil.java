package com.qingxiu.utills.random;

import java.util.Random;

public class RandomUtil {
    public static String getRandomString() {
        int length = 15;
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomStringWithLength(int size) {
        int length = size;
        if (length < 4) {
            length = 4;
        }
        if (length > 15) {
            length = 15;
        }
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String randomString = RandomUtil.getRandomString();
        String randomString2 = RandomUtil.getRandomStringWithLength(100);
        System.out.println(randomString);
        System.out.println(randomString2);
    }
}
