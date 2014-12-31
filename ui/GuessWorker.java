package ui;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import model.JottoModel;

/**
 * This class is used as a background thread to handle the server's 
 * feedback on the user's guess
 */
public class GuessWorker extends SwingWorker{

    private final JottoModel model;
    private final DefaultTableModel tableModel;
    private int row;
    private String guess;
    
    /**
     * This method creates a new instance of GuessWorker
     * @param guess - the user's guess
     * @param row - the row of the table the server's response will go in
     * @param model - the version of Jotto the user is playing
     * @param tableModel - the model for the table with the user's guessing history
     */
    public GuessWorker(String guess, int row, JottoModel model, DefaultTableModel tableModel){
        this.guess = guess;
        this.row = row;
        this.model = model;
        this.tableModel = tableModel;
    }
    
    /**
     * This method communicates with the server for feedback on the user's guess
     * @return the feedback on the user's guess from the server
     */
    @Override
    protected Object doInBackground() throws Exception {
        return model.makeGuess(guess);
    }
    
    /**
     * This method updates the table of the user's guessing history
     * After the server has provided a response to their guess
     */
    @Override
    protected void done(){
        try {
            String[] serverResponse = (String[]) get();
            
            if (serverResponse[1].equals("5") && serverResponse[1].equals("5"))
                tableModel.setValueAt("You Win!", row, 1);
            else if (serverResponse[1].contains("0:"))
                tableModel.setValueAt("Ill formatted request", row, 1);
            else if (serverResponse[1].contains("1:"))
                tableModel.setValueAt("Invalid puzzle number", row, 1);
            else if (serverResponse[1].contains("2:"))
                tableModel.setValueAt("Invalid guess", row, 1);
            else{
                tableModel.setValueAt(serverResponse[1], row, 1);
                tableModel.setValueAt(serverResponse[2], row, 2);
            }
            tableModel.fireTableRowsUpdated(row, row);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
