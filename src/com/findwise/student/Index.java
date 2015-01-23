package com.findwise.student;

import java.util.List;

/**
 * Used to hold the index. At least needs to handle adding documents and searching the added documents
 * 
 * @author simon.stenstrom
 *
 */
public interface Index {

    public void addDocument(Document doc);

    public List<Document> search(String query);

}
