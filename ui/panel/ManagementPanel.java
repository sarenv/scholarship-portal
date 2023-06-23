package ui.panel;

import database.DatabaseConnectionHandler;
import ui.ScholarshipGUI;
import model.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;

public class ManagementPanel extends BasePanel {
    private JPanel resultsPanel;
    private JScrollPane scrollPane;

    private String qryString;
    public ManagementPanel(ScholarshipGUI scholarshipGUI)  {
        super(scholarshipGUI);
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
        results = DatabaseConnectionHandler.selectApplicant(input);

        if (results.size() > 0) {
            resultsPanel.removeAll();               // to clear results
            resultsPanel.add(getColumnNamePanel());
            for (Applicant a: results) {
                resultsPanel.add(getResultsPanel(a));
            }
            resultsPanel.setVisible(true);
            scrollPane.setVisible(true);
        } else {
            scrollPane.setVisible(false);
        }
        revalidate();
        repaint();
    }

    private JPanel getColumnNamePanel() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        JLabel applicantIDLabel = new JLabel(String.valueOf("ApplicationID"));
        applicantIDLabel.setPreferredSize(new Dimension(200,50));
        result.add(applicantIDLabel);

        JLabel FNLabel = new JLabel("First Name");
        FNLabel.setPreferredSize(new Dimension(200,50));
        result.add(FNLabel);

        JLabel LNLabel = new JLabel("Last Name");
        LNLabel.setPreferredSize(new Dimension(200,50));
        result.add(LNLabel);

        JLabel emailLabel = new JLabel(String.valueOf("Email Address"));
        emailLabel.setPreferredSize(new Dimension(200,50));
        result.add(emailLabel);

        JLabel schoolLabel = new JLabel("School");
        schoolLabel.setPreferredSize(new Dimension(200,50));
        result.add(schoolLabel);

        JLabel gpaLabel = new JLabel(String.valueOf("Current GPA"));
        gpaLabel.setPreferredSize(new Dimension(200,50));
        result.add(gpaLabel);

        return result;
    }

    private JPanel getResultsPanel (Applicant applicant) {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));

        // FOR ALL ATTRIBUTES
        JLabel applicantIDLabel = new JLabel(String.valueOf(applicant.getApplicantID()));
        applicantIDLabel.setPreferredSize(new Dimension(200,50));
        result.add(applicantIDLabel);

        JLabel FNLabel = new JLabel(applicant.getFirstName());
        FNLabel.setPreferredSize(new Dimension(200,50));
        result.add(FNLabel);

        JLabel LNLabel = new JLabel(applicant.getLastName());
        LNLabel.setPreferredSize(new Dimension(200,50));
        result.add(LNLabel);

        JLabel emailLabel = new JLabel(String.valueOf(applicant.getEmail()));
        emailLabel.setPreferredSize(new Dimension(200,50));
        result.add(emailLabel);

        JLabel schoolLabel = new JLabel(applicant.getSchool());
        schoolLabel.setPreferredSize(new Dimension(200,50));
        result.add(schoolLabel);

        JLabel gpaLabel = new JLabel(String.valueOf(applicant.getGPA()));
        gpaLabel.setPreferredSize(new Dimension(200,50));
        result.add(gpaLabel);

        // BUTTONS FOR THE OTHER QUERIES
        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(100,15));
        updateButton.addActionListener(e -> infoPrompt(applicant));
        result.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100,15));
        deleteButton.addActionListener(e -> {
            DatabaseConnectionHandler.deleteApplicant(applicant.getApplicantID());
            JOptionPane.showMessageDialog(this, "Applicant " + applicant.getApplicantID() + "'s data has been deleted!");
                performSelect(qryString);
                });
        result.add(deleteButton);

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
            DatabaseConnectionHandler.updateApplicant(applicant);
        JOptionPane.showMessageDialog(this, "Update Successful!");
            performSelect(qryString);

    }

    private  void infoPrompt(Applicant applicant) {
        JFrame infoFrame = new JFrame("Applicant Info");
        infoFrame.setSize(new Dimension(1000,500));
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
            schoolTextField.setText(String.valueOf(applicant.getSchool()));
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
                Float gpa = Float.valueOf(gpaTextField.getText());

                update(new Applicant(id, fn,ln,email,school,gpa));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Update Failed!");
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
