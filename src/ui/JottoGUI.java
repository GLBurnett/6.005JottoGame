/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package ui;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.JottoModel;

/**
 * This class provides the GUI for the Jotto Game
 */
public class JottoGUI extends JFrame {

    private static final long serialVersionUID = 1L; // required by Serializable
     
    // components to use in the GUI
    private final JButton newPuzzleButton;
    private final JTextField newPuzzleNumber;
    private final JLabel puzzleNumber;
    private final JTextField guess;
    private final JTable guessTable;
    private final JLabel guessLabel;
    private final JScrollPane guessScrollPane;
    
    // other components we need
    private final JottoModel modelThread = new JottoModel();

    /**
     * This method sets up the graphics window and initializes all the graphical objects
     */
    public JottoGUI() {
        
        this.setSize(700,860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel myPanel = new JPanel();
        GroupLayout layout = new GroupLayout(myPanel);     
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        newPuzzleButton = new JButton();
        newPuzzleButton.setName("newPuzzleButton");
        newPuzzleButton.setText("New Puzzle");
        
        newPuzzleNumber = new JTextField();
        newPuzzleNumber.setName("newPuzzleNumber");
        
        puzzleNumber = new JLabel();
        puzzleNumber.setName("puzzleNumber");
        puzzleNumber.setText("Puzzle #"+modelThread.getPuzzleNumber());
        
        guess = new JTextField();
        guess.setName("guess");
        
        guessLabel = new JLabel();
        guessLabel.setName("guess label");
        guessLabel.setText("Your guess here");
        
        guessTable = new JTable(new DefaultTableModel(new String[]{"guess", "in common", "correct position"}, 0));
        guessTable.setName("guessTable");
        guessTable.setFillsViewportHeight(true);
        
        guessScrollPane = new JScrollPane(guessTable);
        guessScrollPane.setName("guessScrollPane");
        
        newPuzzleButton.addActionListener(new NewPuzzleListener(newPuzzleNumber, guessTable, puzzleNumber, modelThread));
        newPuzzleNumber.addActionListener(new NewPuzzleListener(newPuzzleNumber, guessTable, puzzleNumber, modelThread));
        guess.addActionListener(new GuessListener(modelThread, guessTable, guess));
        
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(puzzleNumber)
                            .addComponent(newPuzzleButton)
                            .addComponent(newPuzzleNumber))
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(guessLabel)
                            .addComponent(guess))
                    .addComponent(guessScrollPane)
        );  
        
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(puzzleNumber)
                            .addComponent(newPuzzleButton)
                            .addComponent(newPuzzleNumber))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(guessLabel)
                            .addComponent(guess))
                    .addComponent(guessScrollPane)
        );
        
        myPanel.setLayout(layout);
        this.add(myPanel);
        setVisible(true);
    }

    /**
     * This method starts the GUI Jotto client.
     * @param args unused
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JottoGUI main = new JottoGUI();
                main.setVisible(true);
            }
        });
    }
}
