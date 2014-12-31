package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.JottoModel;

public class NewPuzzleListener implements ActionListener{

    private final int maxPuzzleNumber = 10000;
    private final JTextField textField;
    private final DefaultTableModel tableModel;
    private final JLabel label;
    private final JottoModel model;
    
    public NewPuzzleListener(JTextField textField, JTable table, JLabel label, JottoModel model){
        this.textField = textField;
        this.tableModel = (DefaultTableModel) table.getModel();
        this.label = label;
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // get new puzzle number
        String newPuzzleNumber = textField.getText();
        // set new puzzle number
        if (newPuzzleNumber.isEmpty()){
            Random randomPuzzleNumberGenerator = new Random();
            model.setPuzzleNumber(randomPuzzleNumberGenerator.nextInt(maxPuzzleNumber)+1);
        }
        else
            model.setPuzzleNumber(Integer.parseInt(newPuzzleNumber));
        // update label
        label.setText("Puzzle #"+model.getPuzzleNumber());
        // clear old guesses
        tableModel.setNumRows(0);
        // update table
        tableModel.fireTableDataChanged();
        // reset text field
        textField.setText("");
    }
}