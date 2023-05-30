package net.trapdoll;

import java.util.Random;

public class TrapDollAPI {

    private static final String random_chars = "qwertyuiopasdfghjklzxcvbnm";
    private final static short chars_lght = (short) random_chars.length();

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    public static String getRandomText(int count) {
        String builder_randomtext = "";
        for(int c = 0; c < count + 1; c++) {
            builder_randomtext = builder_randomtext.concat(String.valueOf(random_chars.charAt(getRandomNumber(0, chars_lght - 1))));
        }
        return builder_randomtext;
    }
}
