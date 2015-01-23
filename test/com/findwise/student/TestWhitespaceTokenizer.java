package com.findwise.student;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestWhitespaceTokenizer {
    
    private Tokenizer tokenizer;
    
    @Before
    public void init() {
        tokenizer = new WhitespaceTokenizer();
    }
    
    @Test
    public void testTokenize() {
        List<String> tokens = tokenizer.tokenize("test1 test2");

        assertEquals("test1", tokens.get(0));
        assertEquals("test2", tokens.get(1));
    }

    @Test
    public void testTokenize_tabs() {
        List<String> tokens = tokenizer.tokenize("test1\ttest2");

        assertEquals("test1", tokens.get(0));
        assertEquals("test2", tokens.get(1));
    }

    @Test
    public void testTokenize_multipleWhitespace() {
        List<String> tokens = tokenizer.tokenize("test1  test2 \ttest3\t\ttest4");

        assertEquals("test1", tokens.get(0));
        assertEquals("test2", tokens.get(1));
        assertEquals("test3", tokens.get(2));
        assertEquals("test4", tokens.get(3));
    }
    
    @Test
    public void testTokenize_lineBreaks() {
        List<String> tokens = tokenizer.tokenize("test1\n\rtest2");

        assertEquals("test1", tokens.get(0));
        assertEquals("test2", tokens.get(1));
    }
}
