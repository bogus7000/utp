package com.brodskyi.assignment07.implementation;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JarUtility {
    public static List<Path> findFilesByName(Path jarFile, String name) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(jarFile)) {
            Path root = fs.getPath("/");
            try (Stream<Path> stream = Files.walk(root)) {
                return stream.filter(path -> path.getFileName() != null && path.getFileName().toString().equals(name))
                        .collect(Collectors.toList());
            }
        }
    }

    public static List<Path> parallelFindFilesByName(Path jarFile, String name) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(jarFile)) {
            Path root = fs.getPath("/");
            try (Stream<Path> stream = Files.walk(root)) {
                return stream.parallel().filter(path -> path.getFileName() != null && path.getFileName().toString().equals(name))
                        .collect(Collectors.toList());
            }
        }
    }

    public static List<Path> findFilesByContent(Path jarFile, String content) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(jarFile)) {
            Path root = fs.getPath("/");
            try (Stream<Path> stream = Files.walk(root)) {
                return stream.filter(path -> {
                            try {
                                return Files.lines(path).anyMatch(line -> line.contains(content));
                            } catch (IOException e) {
                                return false;
                            }
                        })
                        .collect(Collectors.toList());
            }
        }
    }

    public static List<Path> parallelFindFilesByContent(Path jarFile, String content) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(jarFile)) {
            Path root = fs.getPath("/");
            try (Stream<Path> stream = Files.walk(root)) {
                return stream.parallel().filter(path -> {
                            try {
                                return Files.lines(path).anyMatch(line -> line.contains(content));
                            } catch (IOException e) {
                                return false;
                            }
                        })
                        .collect(Collectors.toList());
            }
        }
    }
}
