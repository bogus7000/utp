package com.brodskyi.assignment06.model;

import java.util.Locale;
import java.util.Random;

public enum Nationality {
    POLISH, UKRAINIAN, BELARUSIAN, SLOVAK, LITHUANIAN, LATVIAN, BRITISH, INDIAN, CHINESE, VIETNAMESE;

    private static final Random random = new Random();

    public static Nationality randomNationality() {
        Nationality[] nationalities = values();
        return nationalities[random.nextInt(nationalities.length)];
    }

    public static Locale getLocale(Nationality nationality) {
        return switch (nationality) {
            case UKRAINIAN -> new Locale("uk");
            case BELARUSIAN -> new Locale("be");
            case SLOVAK -> new Locale("sk");
            case LITHUANIAN -> new Locale("lt");
            case LATVIAN -> new Locale("lv");
            case BRITISH -> new Locale("en");
            case INDIAN -> new Locale("hi");
            case CHINESE -> new Locale("zh");
            case VIETNAMESE -> new Locale("vi");
            default -> new Locale("pl");
        };
    }
}
