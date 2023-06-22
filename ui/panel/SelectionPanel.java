package ui.panel;

import database.DatabaseConnectionHandler;
import ui.ScholarshipGUI;
import model.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SelectionPanel extends BasePanel {
    private JPanel resultsPanel;
    private JScrollPane scrollPane;

    private String qryString;
    public SelectionPanel(ScholarshipGUI scholarshipGUI)  {
        super(scholarshipGUI);
    }

    private interface FunctionCallback {
        void apply(Applicant applicant);
    }

    @Override
    protected void generate() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.add(createTitle("Manage Applicants"), BorderLayout.NORTH);
        headingPanel.add(getSearchBar(),BorderLayout.WEST);
        add(headingPanel,BorderLayout.NORTH);

        makeScrollPane();
        add(scrollPane,BorderLayout.WEST);
    }

    private void performSelect(String input) {
        qryString = input;
        ArrayList<Applicant> results = new ArrayList<>();
//         results = DatabaseConnectionHandler.selection(input);

        if (results.size() > 0) {
            resultsPanel.removeAll();               // to clear results
            for (Applicant a: results) {
                resultsPanel.add(getResultsPanel(a));
            }
            scrollPane.setVisible(true);
        } else {
            scrollPane.setVisible(false);
        }
        revalidate();
        repaint();
    }

    private JPanel getResultsPanel (Applicant applicant) {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));

        // FOR ALL ATTRIBUTES
        JLabel applicantIDLabel = new JLabel(String.valueOf(applicant.getApplicantID()));
        applicantIDLabel.setPreferredSize(new Dimension(300,10));
        result.add(applicantIDLabel);

        JLabel FNLabel = new JLabel(applicant.getFirstName());
        FNLabel.setPreferredSize(new Dimension(300,10));
        result.add(FNLabel);

        JLabel LNLabel = new JLabel(applicant.getLastName());
        LNLabel.setPreferredSize(new Dimension(300,10));
        result.add(LNLabel);

        JLabel emailLabel = new JLabel(String.valueOf(applicant.getApplicantID()));
        emailLabel.setPreferredSize(new Dimension(300,10));
        result.add(emailLabel);

        JLabel schoolLabel = new JLabel(applicant.getSchool());
        schoolLabel.setPreferredSize(new Dimension(300,10));
        result.add(schoolLabel);

        JLabel gpaLabel = new JLabel(String.valueOf(applicant.getGPA()));
        gpaLabel.setPreferredSize(new Dimension(300,10));
        result.add(gpaLabel);

        // BUTTONS FOR THE OTHER QUERIES
        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(30,15));
        updateButton.addActionListener(e -> infoPrompt(applicant,this::update));
        result.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        updateButton.setPreferredSize(new Dimension(30,15));
        updateButton.addActionListener(e -> {
            DatabaseConnectionHandler.deleteApplicant(applicant.getApplicantID());
                performSelect(qryString);
                });
        result.add(updateButton);

        return  result;
    }

    private JPanel getSearchBar() {
        JPanel searchBarPanel = new JPanel(new FlowLayout());

        JTextField searchBox = new JTextField();
        searchBox.setPreferredSize(new Dimension(500,20));
        searchBarPanel.add(searchBox);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> performSelect(searchBox.getText()));
        searchBarPanel.add(selectButton);

        return searchBarPanel;
    }

    private void makeScrollPane() {
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel,BoxLayout.PAGE_AXIS));

        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setVisible(false);
    }

    private void update(Applicant applicant) {
//        try {
// //            DatabaseConnectionHandler.update(applicant);
//
//            Applicant check = DatabaseConnectionHandler.getApplicantByID(applicant.getApplicantID());
//            if (check != null && check.getApplicantID() == applicant.getApplicantID()) {
//                performSelect(qryString);
//            } else {
//                JOptionPane.showMessageDialog(this, "Update failed!");
//            }
//        }
//        catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Update failed!");
//        }
    }

    private  void infoPrompt(Applicant applicant, FunctionCallback callback) {
        JFrame infoFrame = new JFrame("Applicant Info");
        infoFrame.setSize(new Dimension(500,200));
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setVisible(true);

        JPanel infoPanel = new JPanel(new GridLayout(8,2,10,10));

        // Attribute Labels + Fields
        JLabel idLabel = new JLabel("Application ID:");
        JTextField idTextField = new JTextField(20);

        JLabel fnLabel = new JLabel("First Name:");
        JTextField fnTextField = new JTextField(70);

        JLabel lnLabel = new JLabel("Last Name:");
        JTextField lnTextField = new JTextField(70);

        JLabel emailLabel = new JLabel("Application Email:");
        JTextField emailTextField = new JTextField(70);

        JLabel schoolLabel = new JLabel("Application School:");
        JTextField schoolTextField = new JTextField(70);

        JLabel gpaLabel = new JLabel("Application GPA:");
        JTextField gpaTextField = new JTextField(70);

        if (applicant != null) {
            idTextField.setText(String.valueOf(applicant.getApplicantID()));
            idTextField.setEnabled(false);
            fnTextField.setText(String.valueOf(applicant.getFirstName()));
            lnTextField.setText(String.valueOf(applicant.getLastName()));
            emailTextField.setText(String.valueOf(applicant.getEmail()));
            schoolTextField.setText(String.valueOf(applicant.getGPA()));
            gpaTextField.setText(String.valueOf(applicant.getGPA()));
            idTextField.setEnabled(false);
        }

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            try {
                Integer id = Integer.valueOf(idTextField.getText());
                String fn = String.valueOf(fnTextField.getText());
                String ln = String.valueOf(lnTextField.getText());
                String email = String.valueOf(emailTextField.getText());
                String school = String.valueOf(schoolTextField.getText());
                Integer gpa = Integer.valueOf(gpaTextField.getText());

                callback.apply(new Applicant(id,fn,ln,email,school,gpa));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }
            infoFrame.dispose();
        });

        infoPanel.add(idLabel);
        infoPanel.add(idTextField);
        infoPanel.add(fnLabel);
        infoPanel.add(fnTextField);
        infoPanel.add(lnLabel);
        infoPanel.add(lnTextField);
        infoPanel.add(emailLabel);
        infoPanel.add(emailTextField);
        infoPanel.add(schoolLabel);
        infoPanel.add(schoolTextField);
        infoPanel.add(gpaLabel);
        infoPanel.add(gpaTextField);

        infoPanel.add(updateButton);
        infoPanel.add(new JLabel());

        infoPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        infoFrame.add(infoPanel);
    }
}
