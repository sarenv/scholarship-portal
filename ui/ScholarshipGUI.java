package ui;

import javafx.scene.shape.Box;

import javax.swing.*;
import java.awt.*;

public class ScholarshipGUI extends JFrame {
    // Put buttons and shit and instantiate other shit here

    private JPanel mainPanel = new JPanel();;
    private JPanel applicationPanel = new JPanel();
    private JLabel title = new JLabel();
//    private JLabel question = new JLabel();
    private JButton applicantButton;
    private JButton applicationButton;
    private JButton scholarshipButton;
    private JButton committeeButton;
    private JButton superintendentButton;
    private JButton selectioncriteriaButton;
    private JButton referenceletterButton;
    private JButton donorButton;
    public ScholarshipGUI() {
        // Initializers and this.adds go in here
        initializeButtons();
        mainPanel();
        applicationPanel();

        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(new BoxLayout(layoutPanel,BoxLayout.Y_AXIS));
        layoutPanel.add(title);
        layoutPanel.add(mainPanel);
        layoutPanel.add(applicationPanel);

        this.add(layoutPanel);
        initializer();

    }

    public void initializer() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scholarship Search");
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(900, 500);
        title.setText("Scholarship Search!");
        title.setFont(new Font("Proxima Nova", Font.BOLD, 40));
        title.setBounds(375, 10, 300, 50);

    }

    public void initializeButtons() {
        applicationButton = new JButton();
        applicantButton = new JButton();
        referenceletterButton = new JButton();
        scholarshipButton = new JButton();
        donorButton = new JButton();
        selectioncriteriaButton = new JButton();
        superintendentButton = new JButton();
        committeeButton = new JButton();
    }


    // main menu panel to switch to all panels
    public JPanel mainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.getHSBColor(66,66,66));
        mainPanel.setPreferredSize(new Dimension(800,350));
        mainPanel.setMaximumSize(new Dimension(800, 350));
        JLabel question = new JLabel("Choose one table: ", JLabel.CENTER);
        question.setFont(new Font("Proxima Nova", Font.PLAIN, 25));
        mainPanel.add(question, BorderLayout.CENTER);
        getApplicantButton();
        getApplicationButton();
        getScholarshipButton();
        getSelectioncriteriaButton();
        getCommitteeButton();
        getReferenceletterButton();
        getSuperintendentButton();
        getDonorButton();
        this.getContentPane().add(mainPanel);
        return mainPanel;
    }

    //////////// ALL BUTTONS METHODS //////////////////////
    // lead to applicantPanel
    public void getApplicantButton() {
        applicantButton.setPreferredSize(new Dimension(50,20));
        applicantButton.setText("Applicant table");
        applicantButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        applicantButton.setFocusable(false);
        mainPanel.add(applicantButton, BorderLayout.CENTER);
    }

    // lead to applicationPanel
    public void getApplicationButton() {
        applicationButton.setPreferredSize(new Dimension(40,20));
        applicationButton.setText("Application table");
        applicationButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        applicationButton.setFocusable(false);
        mainPanel.add(applicationButton, BorderLayout.CENTER);
    }

    // lead to scholarshipPanel
    public void getScholarshipButton() {
        scholarshipButton.setPreferredSize(new Dimension(40,20));
        scholarshipButton.setText("Scholarship table");
        scholarshipButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        scholarshipButton.setFocusable(false);
        mainPanel.add(scholarshipButton, BorderLayout.CENTER);
    }

    public void getSelectioncriteriaButton() {
        selectioncriteriaButton.setPreferredSize(new Dimension(40,20));
        selectioncriteriaButton.setText("Selection Criteria table");
        selectioncriteriaButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        selectioncriteriaButton.setFocusable(false);
        mainPanel.add(selectioncriteriaButton, BorderLayout.CENTER);
    }

    public void getCommitteeButton() {
        committeeButton.setPreferredSize(new Dimension(40,20));
        committeeButton.setText("Committee table");
        committeeButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        committeeButton.setFocusable(false);
        mainPanel.add(committeeButton, BorderLayout.CENTER);
    }

    public void getReferenceletterButton() {
        referenceletterButton.setPreferredSize(new Dimension(40,20));
        referenceletterButton.setText("Reference Letter table");
        referenceletterButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        referenceletterButton.setFocusable(false);
        mainPanel.add(referenceletterButton, BorderLayout.CENTER);
    }

    public void getSuperintendentButton() {
        superintendentButton.setPreferredSize(new Dimension(40,20));
        superintendentButton.setText("Superintendent table");
        superintendentButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        superintendentButton.setFocusable(false);
        mainPanel.add(superintendentButton, BorderLayout.CENTER);
    }

    public void getDonorButton() {
        donorButton.setPreferredSize(new Dimension(40,20));
        donorButton.setText("Donor table");
        donorButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        donorButton.setFocusable(false);
        mainPanel.add(donorButton, BorderLayout.CENTER);;
    }

    //////////// ALL PANEL METHODS ////////////////
    // applicant's application panel
    public JPanel applicationPanel() {

        return applicationPanel;
    }

    // More methods
}





//    public void setTitle() {
//        headerPanel = new JPanel();
//        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
//        headerPanel.setLayout(new FlowLayout());
//        headerPanel.setBackground(Color.lightGray);
//
//        JLabel text = new JLabel("Hello!");
//        headerPanel.setFont(new Font("Proxima Nova", Font.BOLD,40));
//        headerPanel.add(text, BorderLayout.CENTER);
//        headerPanel.setPreferredSize(new Dimension(480,100));
//        headerPanel.setMaximumSize(new Dimension(480,100));
//
//    }
