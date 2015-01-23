package com.findwise.student;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestMemoryIndex {
    
    private Tokenizer tokenizer;
    private MemoryIndex index;
    
    @Before
    public void setup() {
        tokenizer = mock(Tokenizer.class);
        when(tokenizer.tokenize("test1 test2")).thenReturn(Arrays.asList("test1", "test2"));
        when(tokenizer.tokenize("test2 test3")).thenReturn(Arrays.asList("test2", "test3"));
        
        index = new MemoryIndex(tokenizer);
    }
    
    @Test
    public void testAddDocument_oneDocument() {
        Map<String, List<Document>> map = index.getIndexMap();
        
        Document doc = new Document("test", "c:\\test\\file.txt", "test1 test2");
        index.addDocument(doc);
        
        Collection<List<Document>> docs = map.values();
        Set<Document> uniqueDocs = getUniqueDocuments(docs);
        
        assertEquals(1, uniqueDocs.size());
        
        List<Document> matchingDocsTest1 = map.get("test1");
        assertEquals(1, matchingDocsTest1.size());
        assertEquals(doc, matchingDocsTest1.get(0));

        List<Document> matchingDocsTest2 = map.get("test2");
        assertEquals(1, matchingDocsTest2.size());
        assertEquals(doc, matchingDocsTest2.get(0));
    }

    @Test
    public void testAddDocument_twoDocuments() {
        Map<String, List<Document>> map = index.getIndexMap();
        
        Document doc = new Document("test", "c:\\test\\file.txt", "test1 test2");
        index.addDocument(doc);
        
        Document doc2 = new Document("test2", "c:\\test\\file2.txt", "test2 test3");
        index.addDocument(doc2);

        Collection<List<Document>> docs = map.values();
        Set<Document> uniqueDocs = getUniqueDocuments(docs);
        
        assertEquals(2, uniqueDocs.size());
        
        List<Document> matchingDocsTest1 = map.get("test1");
        assertEquals(1, matchingDocsTest1.size());
        assertEquals(doc, matchingDocsTest1.get(0));

        List<Document> matchingDocsTest2 = map.get("test2");
        assertEquals(2, matchingDocsTest2.size());
        assertEquals(doc, matchingDocsTest2.get(0));
        assertEquals(doc2, matchingDocsTest2.get(1));
        
        List<Document> matchingDocsTest3 = map.get("test3");
        assertEquals(1, matchingDocsTest3.size());
        assertEquals(doc, matchingDocsTest3.get(0));
    }

    private Set<Document> getUniqueDocuments(Collection<List<Document>> docs) {
        Set<Document> uniqueDocs = new HashSet<>();
        
        for (List<Document> documents : docs) {
            for (Document document : documents) {
                uniqueDocs.add(document);
            }
        }
        return uniqueDocs;
    }
    
}
