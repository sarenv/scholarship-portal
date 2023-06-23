package ui.panel;

import database.DatabaseConnectionHandler;
import ui.ScholarshipGUI;
import model.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InsertPanel extends BasePanel {
    private JPanel insertPanel;
    private JScrollPane scrollPane;
    private String query;

    public InsertPanel(ScholarshipGUI scholarshipGUI) {
        super(scholarshipGUI);
    }


    @Override
    protected void generate() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        insertPanel = new JPanel(new BorderLayout());
        insertPanel.add(createTitle("Insert Applications"), BorderLayout.NORTH);
        add(insertPanel, BorderLayout.NORTH);
    }



    private  void infoPrompt(Application application) {
        JFrame infoFrame = new JFrame("Application Info");
        infoFrame.setSize(new Dimension(1000,500));
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setVisible(true);

        JPanel infoPanel = new JPanel(new GridLayout(4,2,10,10));

        // Attribute Labels + Fields
        JLabel applicationIDLabel = new JLabel("Application ID:");
        JTextField applicationIDTextField = new JTextField(20);

        JLabel applicantIDLabel = new JLabel("Applicant ID:");
        JTextField applicantIDTextField = new JTextField(20);

        JLabel deadlineLabel = new JLabel("Deadline (dd/mm/yyyy format PLEASE):");
        JTextField deadlineTextField = new JTextField(70);


        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            try {
                Integer tionID = Integer.valueOf(applicationIDTextField.getText());
                Integer antID = Integer.valueOf(applicantIDTextField.getText());
                String deadline = String.valueOf(deadlineTextField.getText());

                DatabaseConnectionHandler.insertApplication(new Application(tionID,antID,deadline));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Insert Failed!");
            }
            infoFrame.dispose();
        });

        infoPanel.add(applicationIDLabel);
        infoPanel.add(applicationIDTextField);
        infoPanel.add(applicantIDLabel);
        infoPanel.add(applicantIDTextField);
        infoPanel.add(deadlineLabel);
        infoPanel.add(deadlineTextField);

        infoPanel.add(insertButton);
        infoPanel.add(new JLabel());

        infoPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        infoFrame.add(infoPanel);
    }


}