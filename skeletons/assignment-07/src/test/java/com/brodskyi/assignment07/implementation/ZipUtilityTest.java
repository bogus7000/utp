package com.brodskyi.assignment07.implementation;

import org.junit.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

public class ZipUtilityTest {
    private static Path testZip;
    private static Path testFile;

    @BeforeClass
    public static void setUp() throws IOException {
        testZip = Files.createTempFile("assignment-07-test", ".zip");
        testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "assignment 07 test file.".getBytes());
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(testZip.toFile()))) {
            ZipEntry zipEntry = new ZipEntry(testFile.getFileName().toString());
            zipOut.putNextEntry(zipEntry);
            Files.copy(testFile, zipOut);
            zipOut.closeEntry();
        }
        testFile.toFile().deleteOnExit();
        testZip.toFile().deleteOnExit();
    }


    @Test
    public void findFilesByName() throws IOException {
        List<Path> result = ZipUtility.findFilesByName(testZip, testFile.getFileName().toString());
        assertEquals(1, result.size());
        assertEquals(testFile.getFileName().toString(), result.get(0).getFileName().toString());
    }

    @Test
    public void findFilesByContent() throws IOException {
        List<Path> result = ZipUtility.findFilesByContent(testZip, "assignment 07");
        assertEquals(1, result.size());
        assertEquals(testFile.getFileName().toString(), result.get(0).getFileName().toString());
    }
}