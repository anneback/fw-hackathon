package com.findwise.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The engine holding all configuration and helper methods for indexing
 * 
 * @author simon.stenstrom
 *
 */
public class Engine {
    
    private final Index index;
    private final DocumentParser documentParser;
    
    public Engine() {
        this.index = new MemoryIndex();
        this.documentParser = new PlainTextDocumentParser();
    }

    public Engine(Index index, DocumentParser parser) {
        this.index = index;
        this.documentParser = parser;
    }
    
    public void addDocument(Document doc) {
        index.addDocument(doc);
    }
    
    public List<Document> search(String query) {
        return index.search(query);
    }

    public List<Document> getDocuments(List<String> filePaths) {
        List<Document> documents = new ArrayList<>();
        
        for (String arg : filePaths) {
            Document doc;
            try {
                doc = documentParser.getDocument(arg);
            } catch (IOException e) {
                System.err.println("Failed to parse document: " + arg);
                System.err.println("Reason: " + e.getMessage());
                System.err.println("Skipping it.");
                continue;
            }
            documents.add(doc);
        }
        return documents;
    }
    
}
