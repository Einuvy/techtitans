package com.techtitans.ecommerce.utils;

public final class RandomNumberUtils {

    public static int getRandomNumber5() {
        return (int) (Math.random() * (99999 - 10000) + 10000);
    }

}
