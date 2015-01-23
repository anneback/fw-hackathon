package com.findwise.student;

import java.util.List;

/**
 * Used to split text into tokens that can be searched.
 * 
 * @author simon.stenstrom
 *
 */
public interface Tokenizer {
    public List<String> tokenize(String text);
}
