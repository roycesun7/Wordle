package org.cis1200.wordle;

import org.cis1200.Wordle.WordList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class WordleTests {

    @Test
    public void testInWordList() {
        WordList w  = new WordList("files/wordleWordList.txt");
        assertFalse(w.inWordList("abcde"));
    }

    @Test
    public void testNotInWordList() {
        WordList w  = new WordList("files/wordleWordList.txt");
        assertTrue(w.inWordList("house"));
    }

    @Test
    public void testInvalidWordList() {
        WordList w  = new WordList("files/wordleWordList.txt");
        assertFalse(w.inWordList("juoim"));
    }

    @Test
    public void testInvalidCharacters() {
        WordList w  = new WordList("files/wordleWordList.txt");
        assertFalse(w.inWordList("ju##m"));
    }

    @Test
    public void testInvalidTooLong() {
        WordList w  = new WordList("files/wordleWordList.txt");
        assertFalse(w.inWordList("houses"));
    }

}
