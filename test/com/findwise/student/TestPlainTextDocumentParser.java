package com.findwise.student;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestPlainTextDocumentParser {

    @Rule
    public TemporaryFolder testFolder;

    private PlainTextDocumentParser parser = new PlainTextDocumentParser();
    
    @Before
    public void init() throws IOException {
        testFolder = new TemporaryFolder();
        testFolder.create();
    }

    @After
    public void tearDown() {
        testFolder.delete();
    }
    
    @Test
    public void testGetDocument_file() throws IOException {
        File file = createTestFile("my text");
        String absolutePath = file.getAbsolutePath();
        
        Document document = parser.getDocument(file);

        assertEquals("testFile.txt", document.getTitle());
        assertEquals("my text", document.getContent());
        assertEquals(absolutePath, document.getPath());
    }


    @Test
    public void testGetDocument_path() throws IOException {
        File file = createTestFile("my text");
        String absolutePath = file.getAbsolutePath();
        
        Document document = parser.getDocument(absolutePath);

        assertEquals("testFile.txt", document.getTitle());
        assertEquals("my text", document.getContent());
        assertEquals(absolutePath, document.getPath());
    }

    
    private File createTestFile(String content) throws IOException {
        File file = testFolder.newFile("testFile.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "utf-8"))) {
            writer.write(content);
        }
        
        return file;
    }
}
