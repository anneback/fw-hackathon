package com.findwise.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An in memory implementation of the index. The index is dropped when the search engine is turned of
 *  
 * @author simon.stenstrom
 *
 */
public class MemoryIndex implements Index {

    private Map<String, List<Document>> index;
    private final Tokenizer tokenizer;

    public MemoryIndex() {
        this.tokenizer = new WhitespaceTokenizer();
        this.index = new HashMap<>();
    }

    public MemoryIndex(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.index = new HashMap<>();
    }
    
    public MemoryIndex(Tokenizer tokenizer, Map<String, List<Document>> index) {
        this.tokenizer = tokenizer;
        this.index = index;
    }

    @Override
    public void addDocument(Document doc) {
        String content = doc.getContent();
        List<String> tokens = tokenizer.tokenize(content);
        for (String token : tokens) {
            addDocumentForToken(token, doc);
        }
    }

    private void addDocumentForToken(String token, Document doc) {
        if (!index.containsKey(token)) {
            index.put(token, new ArrayList<Document>());
        }
        List<Document> matchingDocuments = index.get(token);
        matchingDocuments.add(doc);
    }

    @Override
    public List<Document> search(String query) {
        List<Document> result = index.get(query);
        
        if (result == null) {
            result = new ArrayList<>();
        }
        
        return result;
    }
    
    public Map<String, List<Document>> getIndexMap() {
        return index;
    }
}
