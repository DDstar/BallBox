package com.mdxx.qqbh.Utils;

import java.util.Random;

/**
 * Created by DDstar on 2016/9/18.
 */
public class RandomUtil {
    private static String[] randomStr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "i", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",};

    public static String getTailsStr() {
        StringBuilder tail = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(randomStr.length);
            tail.append(randomStr[index]);
        }
        return tail.toString();
    }
}
