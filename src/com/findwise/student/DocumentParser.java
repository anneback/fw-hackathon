package com.findwise.student;

import java.io.File;
import java.io.IOException;

/**
 * Used to create a Document from a File. Different parsers should be used for different file types.
 * 
 * @author simon.stenstrom
 *
 */
public interface DocumentParser {
    public Document getDocument(File file) throws IOException;
    public Document getDocument(String filePath) throws IOException;
}
