package com.brodskyi.assignment08.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputParser {
    private static final String ERR_PREFIX = "[InputParser] ";
    private static final String ALPHABET_ONLY_PATTERN = "[a-zA-Z]*";
    private static final String DATE_PATTERN = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public static List<Person> parse(String path) throws FileNotFoundException, IllegalArgumentException, ParseException {
        List<Person> result = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        Scanner scan = new Scanner(new File(path));

        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        if (lines.size() < 1) throw new IllegalArgumentException(ERR_PREFIX + "File must not be empty");
        scan.close();

        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            validateLine(line, i);
            result.add(createPerson(line));
        }

        return result;
    }

    private static void validateLine(String personLine, int index) {
        String prefix = ERR_PREFIX + "Failed to parse Person at line " + (index + 1) + ".\n";

        String[] split = personLine.split(" ");

        if (split.length != 3) {
            throw new IllegalArgumentException(prefix + "Expected format is <FirstName[a-z]> <Surname[a-z]> <Birthdate[YYYY-MM-DD]>.");
        }

        if (testAlphabetOnly(split[0])) {
            throw new IllegalArgumentException(prefix + "Name can only contain alphabet characters.");
        }

        if (testAlphabetOnly(split[1])) {
            throw new IllegalArgumentException(prefix + "Surname can only contain alphabet characters.");
        }

        if (!testDate(split[2])) {
            throw new IllegalArgumentException(prefix + "Expected birthdate format is YYYY-MM-DD.");
        }
    }

    private static Person createPerson(String personLine) throws ParseException {
        String[] split = personLine.split(" ");
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date birthDate = simpleDateFormat.parse(split[2]);
        return new Person(split[0], split[1], birthDate);
    }

    private static boolean testAlphabetOnly(String text) {
        return !runTest(ALPHABET_ONLY_PATTERN, text);
    }

    private static boolean testDate(String text) {
        return runTest(DATE_PATTERN, text);
    }

    private static boolean runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text).matches();
    }

}
