package com.findwise.student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A document parser the handles plain text files. File name is used as title
 * 
 * @author simon.stenstrom
 *
 */
public class PlainTextDocumentParser implements DocumentParser {

    @Override
    public Document getDocument(File file) throws IOException {
        
        String title = file.getName();
        String path = file.getAbsolutePath();
        String content = getContent(file);

        Document document = new Document(title, path, content);
        
        return document;
    }

    @Override
    public Document getDocument(String filePath) throws IOException {
        File file = new File(filePath);

        return getDocument(file);
    }

    private String getContent(File file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF8"));

        StringBuffer content = new StringBuffer();
        String line;

        while ((line = in.readLine()) != null) {
            content.append(line);
        }

        in.close();
        return content.toString();
    }
}
