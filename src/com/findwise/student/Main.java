package com.findwise.student;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Handles the interaction with the end user
 * 
 * @author simon.stenstrom
 *
 */
public class Main {
    
    /**
     * Starts the search engine and indexes the files specified as arguments.
     * 
     * @param filePaths Full path to the files that should be indexed, ex. c:\test\file1.txt "c:\test files\file2.txt"  
     */
    public static void main(String[] filePaths) {
        Engine engine = new Engine();
        
        long startTime = System.currentTimeMillis();
        List<Document> documents = engine.getDocuments(Arrays.asList(filePaths));
        
        if (documents.size() == 0) {
            System.err.println("No valid documents added. Add them with full path using the arguments. Exiting.");
            System.exit(0);
        }
        
        for (Document doc : documents) {
            engine.addDocument(doc);
        }
        
        long duration = System.currentTimeMillis() - startTime;
        
        System.out.println(String.format("Indexing done in %s ms", duration));
        
        run(engine);
    }
    
    public static void run(Engine engine) {
        System.out.println("Quit by entering \"!quit\" as query text");
        
        Scanner in = new Scanner(System.in);
        String query = "";
        while (true) {
            System.out.println("Enter your query text: ");
            query = in.nextLine();
            if (query.equals("!quit")) {
                break;
            }
            List<Document> results = engine.search(query);
            show(query, results);
        }
        
        in.close();
        System.out.println("Bye!");
    }

    private static void show(String query, List<Document> documents) {
        System.out.println(String.format("Your query for \"%s\" returned %s results", query, documents.size()));
        for (Document document : documents) {
            System.out.println(String.format("--- %s ---", document.getTitle()));
            System.out.println(String.format("Path: %s", document.getPath()));
            System.out.println(String.format("Content: %s", document.getContent()));
        }
    }
}
