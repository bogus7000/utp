package com.brodskyi.assignment07.implementation;

import org.junit.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import static org.junit.Assert.*;

public class JarUtilityTest {
    private static Path testJar;
    private static Path testFile;

    @BeforeClass
    public static void setUp() throws IOException {
        testJar = Files.createTempFile("assignment-07-test", ".jar");
        testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "assignment 07 test file.".getBytes());
        try (JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(testJar.toFile()))) {
            JarEntry jarEntry = new JarEntry(testFile.getFileName().toString());
            jarOut.putNextEntry(jarEntry);
            Files.copy(testFile, jarOut);
            jarOut.closeEntry();
        }
        testFile.toFile().deleteOnExit();
        testJar.toFile().deleteOnExit();
    }

    @Test
    public void findFilesByName() throws IOException {
        List<Path> result = JarUtility.findFilesByName(testJar, testFile.getFileName().toString());
        assertEquals(1, result.size());
        assertEquals(testFile.getFileName().toString(), result.get(0).getFileName().toString());
    }

    @Test
    public void findFilesByContent() throws IOException {
        List<Path> result = JarUtility.findFilesByContent(testJar, "assignment 07");
        assertEquals(1, result.size());
        assertEquals(testFile.getFileName().toString(), result.get(0).getFileName().toString());
    }
}