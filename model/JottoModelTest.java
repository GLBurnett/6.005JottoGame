/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package model;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.Test;

public class JottoModelTest {
    
    //  makeGuess
    //      puzzleNumber: 0, greater than 0
    //      guess: valid, not 5 chars long, not in dictionary
    //      letters in common: 0, greater than 0, all
    //      letters in correct place: 0, greater than 0, all
    
    private final JottoModel myJottoModel = new JottoModel();
    
    @Test
    public void puzzleNumberZeroTest() throws MalformedURLException{
        int puzzleNumber = 0;
        String guess = "vapid";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("error 1: Non-number puzzle ID.".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test 
    public void puzzleNumberGreaterThanZero() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "vapid";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("guess 5 5".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test
    public void guessValid() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "vapid";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("guess 5 5".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test
    public void guessNotFiveCharsLong() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "hi";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word.".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test
    public void guessNotInDictionary() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "hellx";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word.".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test 
    public void guessZeroLettersInCommon() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "hello";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("guess 0 0".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test 
    public void guessMoreThanZeroLettersInCommonZeroLettersInCorrectPlace() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "penny";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("guess 1 0".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test 
    public void guessAllLettersInCommonAllLettersInCorrectPlace() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "vapid";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("guess 5 5".split(" "), myJottoModel.makeGuess(guess));
    }
    
    @Test 
    public void guessMoreThanZeroLettersInCorrectPlace() throws MalformedURLException{
        int puzzleNumber = 5555;
        String guess = "apple";
        myJottoModel.setPuzzleNumber(puzzleNumber);
        assertEquals("guess 2 1".split(" "), myJottoModel.makeGuess(guess));
    }
}
