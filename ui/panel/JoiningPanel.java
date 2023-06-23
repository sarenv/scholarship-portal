package ui.panel;

import database.DatabaseConnectionHandler;
import ui.ScholarshipGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JoiningPanel extends  BasePanel {

    private JPanel resultsPanel;
    private JScrollPane scrollPane;


    public JoiningPanel(ScholarshipGUI scholarshipGUI) {
        super(scholarshipGUI);
    }



    protected void generate() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.add(createTitle("Check Application Status"), BorderLayout.NORTH);
        headingPanel.add(getInputPanel(), BorderLayout.WEST);
        add(headingPanel, BorderLayout.NORTH);

        makeScrollPane();
        add(scrollPane,BorderLayout.WEST);
    }

    private JPanel getInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(1,2,10,10));

        JLabel applicantIDLabel = new JLabel("Enter your Application ID:");
        JTextField applicantTextField = new JTextField(20);

        JButton checkButton = new JButton("Check Status");
        checkButton.addActionListener(e-> {
            try {
                Integer id = Integer.valueOf(applicantTextField.getText());
                checkStatus(id);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Checking Failed!");
            }
        });

        inputPanel.add(applicantIDLabel);
        inputPanel.add(applicantTextField);
        inputPanel.add(checkButton);
        inputPanel.add(new JLabel());

        inputPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        return inputPanel;
    }

    private void checkStatus(Integer id) {
        ArrayList<String[]> result = DatabaseConnectionHandler.findApplicationStatus(id);

        if (result.size() > 0) {
            resultsPanel.removeAll();               // to clear results
            resultsPanel.add(getColumnNamePanel());
            for (String[] s: result) {
                resultsPanel.add(getResultsPanel(s));
            }
            resultsPanel.setVisible(true);
            scrollPane.setVisible(true);
        } else {
            scrollPane.setVisible(false);
        }
        revalidate();
        repaint();

    }

    private JPanel getResultsPanel(String[] sArray) {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));

        // FOR ALL ATTRIBUTES
        JLabel applicantIDLabel = new JLabel(String.valueOf(sArray[0]));
        applicantIDLabel.setPreferredSize(new Dimension(200,50));
        result.add(applicantIDLabel);

        JLabel statusLabel = new JLabel(String.valueOf(sArray[1]));
        statusLabel.setPreferredSize(new Dimension(200,50));
        result.add(statusLabel);

        return  result;
    }

    private JPanel getColumnNamePanel() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));

        JLabel applicantIDLabel = new JLabel(String.valueOf("Application ID"));
        applicantIDLabel.setPreferredSize(new Dimension(200,50));
        result.add(applicantIDLabel);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setPreferredSize(new Dimension(200,50));
        result.add(statusLabel);
        return result;
    }

    private void makeScrollPane() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel,BoxLayout.PAGE_AXIS));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setVisible(false);
    }

}
