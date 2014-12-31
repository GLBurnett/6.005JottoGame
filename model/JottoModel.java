/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class communicates with a server to analyze the user's guess for correctness
 * Rep Invariant:
 *      puzzleNumber > 0;
 */
public class JottoModel{
    
    private int puzzleNumber;
    
    /**
     * This method checks to make sure the rep invariant is preserved
     */
    public void checkRep(){
        assert puzzleNumber>0;
    }
    
    /**
     * This method creates a new instance of JottoModel
     * It also sets the puzzleNumber to be 1 as a default
     */
    public JottoModel(){
        this.puzzleNumber = 1;
        checkRep();
    }
    
    /**
     * This method sends the user's guess to a server, so it can be analyzed for correctness
     * @param guess - the user's guess
     * @return - a String[] representing the server's response to the user's guess
     * @throws MalformedURLException - if the URL was not able to be created
     */
    public String[] makeGuess(String guess) throws MalformedURLException {
        try {
            URL guessURL = new URL("http://courses.csail.mit.edu/6.005/jotto.py?puzzle="+puzzleNumber+"&guess="+guess);
            URLConnection guessURLConnection = guessURL.openConnection();           
            
            String nextLine;         
            String serverResponse = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(guessURLConnection.getInputStream()));            
            while ((nextLine = in.readLine()) != null)
                serverResponse+=nextLine;
            in.close();            
            checkRep();
            return serverResponse.split(" ");
        } catch (MalformedURLException e){
            System.err.println("URL not created");
        } catch (IOException e) {
            System.err.println("Connection failed to open");
        }
        checkRep();
        return "guess 0 0".split(" ");
    }
    
    /**
     * This class sets the puzzleNumber
     * @param puzzleNumber
     */
    public void setPuzzleNumber(int puzzleNumber){
        this.puzzleNumber = puzzleNumber;
        checkRep();
    }
    
    /**
     * This class returns the current puzzleNumber
     * @return - an integer representing the current puzzleNumber
     */
    public int getPuzzleNumber(){
        checkRep();
        return this.puzzleNumber;
    }
}
