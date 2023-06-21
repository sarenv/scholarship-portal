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
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(900, 500);
        title.setText("Scholarship Search!");
        title.setFont(new Font("Proxima Nova", Font.BOLD, 22));
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.getHSBColor(66,66,66));
        mainPanel.setPreferredSize(new Dimension(700,300));
        mainPanel.setMaximumSize(new Dimension(700, 300));
        JLabel question = new JLabel("Choose one table: ", JLabel.CENTER);
//        getApplicantButton();
//        getApplicationButton();
//        getScholarshipButton();
//        getSelectioncriteriaButton();
//        getCommitteeButton();
//        getReferenceletterButton();
//        getSuperintendentButton();
//        getDonorButton();
        return mainPanel;
    }

    //////////// ALL BUTTONS METHODS //////////////////////
    // lead to applicantPanel
    public JButton getApplicantButton() {
        return applicantButton;
    }

    // lead to applicationPanel
    public JButton getApplicationButton() {
        return applicationButton;
    }

    // lead to scholarshipPanel
    public JButton getScholarshipButton() {
        return scholarshipButton;
    }

    public JButton getSelectioncriteriaButton() {
        return selectioncriteriaButton;
    }

    public JButton getCommitteeButton() {
        return committeeButton;
    }

    public JButton getReferenceletterButton() {
        return referenceletterButton;
    }

    public JButton getSuperintendentButton() {
        return superintendentButton;
    }

    public JButton getDonorButton() {
        return donorButton;
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
