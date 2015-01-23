package com.findwise.student;

/**
 * A document that can be indexed to the search engine
 * 
 * @author simon.stenstrom
 *
 */
public class Document {

    private final String title;
    private final String path;
    private final String content;

    public Document(String title, String path, String content) {
        this.title = title;
        this.path = path;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }
}
