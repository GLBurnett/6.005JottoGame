package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import model.JottoModel;

/**
 * This class is used as a listener for when a guess is submitted
 */
public class GuessListener implements ActionListener{

    private final JottoModel model;
    private final DefaultTableModel tableModel;
    private final JTextField textField;
    
    /**
     * This method creates a new instance of GuessListener
     * @param model - the version of jotto the user is playing
     * @param table - used to display their guessing history
     * @param textField - where the user's guess is entered
     */
    public GuessListener(JottoModel model, JTable table, JTextField textField){
        this.model = model;
        this.tableModel = (DefaultTableModel) table.getModel();
        this.textField = textField;
    }
    
    /**
     * This method is called when a guess is submitted
     * It adds the user's guess to the guess table
     * It also adds how many letters their guess has in common with the answer
     * And how many of those letters were in the correct place
     */
    @Override
    public void actionPerformed(ActionEvent e) {    
        
        // get row number
        int row = tableModel.getRowCount();
                
        // get user's guess
        String guess = textField.getText();
                
        // get server response
        new GuessWorker(guess, row, model, tableModel).execute();
        
        // reset text field
        textField.setText("");
        
        // add guess to table    
        tableModel.addRow(new String[]{guess, "", ""});
                
        // update table
        tableModel.fireTableRowsInserted(-1, -1);
    }       
}