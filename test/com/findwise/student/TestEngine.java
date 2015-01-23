package com.findwise.student;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class TestEngine {
    private Engine engine;
    private Index index;
    private DocumentParser parser;
    
    @Before
    public void init() {
        index = mock(MemoryIndex.class);
        parser = mock(PlainTextDocumentParser.class);
        engine = new Engine(index, parser);
    }
    
    @Test
    public void testAddDocument() {
        Document doc = new Document("test", "test", "test");
        engine.addDocument(doc);
        verify(index).addDocument(doc);
    }

    @Test
    public void testSearch() {
        engine.search("test");
        verify(index).search("test");
    }
}
