package com.findwise.student;

import java.util.Arrays;
import java.util.List;

/**
 * A tokenizer that splits text on any whitespace recognised by java regex. Multiple whitespace next to each other are ignored 
 * 
 * @author simon.stenstrom
 *
 */
public class WhitespaceTokenizer implements Tokenizer {

    @Override
    public List<String> tokenize(String text) {
        String[] tokens = text.split("\\s+");
        return Arrays.asList(tokens);
    }
    
}
