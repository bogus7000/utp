package com.brodskyi.assignment07.implementation;

import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileUtilityTest {
    private static Path testDir;
    private static Path testFile;

    @BeforeClass
    public static void setUp() throws IOException {
        testDir = Files.createTempDirectory("assignment-07-test");
        testFile = Files.createFile(testDir.resolve("test.txt"));
        Files.write(testFile, "assignment 07 test file.".getBytes());
        testDir.toFile().deleteOnExit();
        testFile.toFile().deleteOnExit();
    }

    @Test
    public void findFilesByName() throws IOException {
        List<Path> result = FileUtility.findFilesByName(testDir, testFile.getFileName().toString());
        assertEquals(1, result.size());
        assertEquals(testFile, result.get(0));
    }

    @Test
    public void findFilesByContent() throws IOException {
        List<Path> result = FileUtility.findFilesByContent(testDir, "assignment 07");
        assertEquals(1, result.size());
        assertEquals(testFile, result.get(0));
    }

}