package com.brodskyi.assignment07.implementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtility {
    public static List<Path> findFilesByName(Path directory, String name) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream.filter(path -> path.getFileName().toString().equals(name))
                    .collect(Collectors.toList());
        }
    }

    public static List<Path> parallelFindFilesByName(Path directory, String name) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream.parallel().filter(path -> path.getFileName().toString().equals(name))
                    .collect(Collectors.toList());
        }
    }

    public static List<Path> findFilesByContent(Path directory, String content) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream.filter(path -> {
                        try {
                            if (Files.isRegularFile(path))
                                return Files.lines(path).anyMatch(line -> line.contains(content));
                            return false;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    public static List<Path> parallelFindFilesByContent(Path directory, String content) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream.parallel().filter(path -> {
                        try {
                            if (Files.isRegularFile(path))
                                return Files.lines(path).anyMatch(line -> line.contains(content));
                            return false;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

}
