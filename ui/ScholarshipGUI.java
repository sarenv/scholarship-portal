package ui;

import database.DatabaseConnectionHandler;
import ui.panel.ProjectionPanel;
import ui.panel.ManagementPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ScholarshipGUI extends JFrame {
    // Put buttons and shit and instantiate other shit here

    private DatabaseConnectionHandler dbHandler = null;
    private JPanel mainPanel = new JPanel();;
    private JPanel applicantPanel = new JPanel();

    private JPanel applicationPanel = new JPanel();

    private JPanel scholarshipPanel = new JPanel();
    private JPanel SelectioncriteriaPanel = new JPanel();

    private ProjectionPanel projectionPanel = new ProjectionPanel(this);
    private ManagementPanel managementPanel = new ManagementPanel(this);

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

    private JButton projectionButton;
    private JButton managementButton;

    public ScholarshipGUI() {
        dbHandler = new DatabaseConnectionHandler();
        // Initializers and this.adds go in here
        initializeButtons();
        initializer();
        mainPanel();
        //applicantPanel();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (dbHandler != null) {
                    dbHandler.close();
                    System.out.println("Database connection closed.");
                }
                dispose(); // Close the frame
                System.exit(0); // Terminate the application
            }
        });
        this.setTitle("Scholarship Search");
        this.setFont(new Font("Proxima Nova", Font.BOLD, 40));
        this.setBounds(375, 10, 300, 50);
        this.setSize(900, 500);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
//        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setVisible(true);
        this.add(title);
        this.add(mainPanel);
        //this.add(applicantPanel);

        //this.add(layoutPanel);
        //initializer();


    }

    public void initializer() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scholarship Search");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
//        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
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
        projectionButton = new JButton();
        managementButton = new JButton();
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
        getProjectionButton();
        getManagementButton();
        this.getContentPane().add(mainPanel);
        return mainPanel;
    }

    //////////// ALL BUTTONS + ACTION LISTENER METHODS //////////////////////

    /* APPLICANT */
    // lead to applicantPanel
    public void getApplicantButton() {
        applicantButton.setPreferredSize(new Dimension(50,20));
        applicantButton.setText("Applicants who submitted an application (Divide)");
        applicantButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        applicantButton.addActionListener(new goToApplicantListener(applicantButton));
        applicantButton.setFocusable(false);
        mainPanel.add(applicantButton, BorderLayout.CENTER);
    }

    // applicant button's ActionListener
    class goToApplicantListener implements ActionListener {
        private JButton jbutton;
        public goToApplicantListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            applicantPanel();

            repaint();
            revalidate();
        }
    }
    // applicant's panel


    public void applicantPanel() {
        applicantPanel.setLayout(new BoxLayout(applicantPanel, BoxLayout.PAGE_AXIS));
        applicantPanel.setBackground(Color.getHSBColor(66,66,66));
        applicantPanel.setPreferredSize(new Dimension(800,350));
        applicantPanel.setMaximumSize(new Dimension(800, 350));
        JLabel title = new JLabel("Applicants who submitted an application: ", JLabel.CENTER);
        title.setFont(new Font("Proxima Nova", Font.ITALIC, 20));
        applicantPanel.add(title, BorderLayout.CENTER);
        this.getContentPane().add(applicantPanel);

        ArrayList<String[]> res = dbHandler.findAllApplied();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ApplicantID"});
        for (String[] r : res) {
            tableModel.addRow(r);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        applicantPanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(applicantPanel);

    }




    /* APPLICATION */
    // lead to applicationPanel
    public void getApplicationButton() {
        applicationButton.setPreferredSize(new Dimension(40,20));
        applicationButton.setText("View minimum GPA required for each major (Having)");
        applicationButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        applicationButton.addActionListener(new goToApplicationListener(applicationButton));
        applicationButton.setFocusable(false);
        mainPanel.add(applicationButton, BorderLayout.CENTER);
    }

    class goToApplicationListener implements ActionListener {
        private JButton jbutton;
        public goToApplicationListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            applicationPanel();

            repaint();
            revalidate();
        }
    }

    public void applicationPanel() {
        applicationPanel.setLayout(new BoxLayout(applicationPanel, BoxLayout.PAGE_AXIS));
        applicationPanel.setBackground(Color.getHSBColor(66,66,66));
        applicationPanel.setPreferredSize(new Dimension(800,350));
        applicationPanel.setMaximumSize(new Dimension(800, 350));
        JLabel title = new JLabel("Minimum GPA required for each major: ", JLabel.CENTER);
        title.setFont(new Font("Proxima Nova", Font.ITALIC, 20));
        applicationPanel.add(title, BorderLayout.CENTER);
        this.getContentPane().add(applicationPanel);

        ArrayList<String[]> res = dbHandler.findMinGPAForEachMajor();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"major", "minimumGPA"});
        for (String[] r : res) {
            tableModel.addRow(r);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        applicationPanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(applicationPanel);

    }


    /* SCHOLARSHIP */
    // lead to scholarshipPanel
    public void getScholarshipButton() {
        scholarshipButton.setPreferredSize(new Dimension(40,20));
        scholarshipButton.setText("GPA comparison between schools (Nested)");
        scholarshipButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        scholarshipButton.addActionListener(new goToScholarshipListener(scholarshipButton));
        scholarshipButton.setFocusable(false);
        mainPanel.add(scholarshipButton, BorderLayout.CENTER);
    }

    class goToScholarshipListener implements ActionListener {
        private JButton jbutton;
        public goToScholarshipListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            scholarshipPanel();

            repaint();
            revalidate();
        }
    }

    public void scholarshipPanel() {
        scholarshipPanel.setLayout(new BoxLayout(scholarshipPanel, BoxLayout.PAGE_AXIS));
        scholarshipPanel.setBackground(Color.getHSBColor(66,66,66));
        scholarshipPanel.setPreferredSize(new Dimension(800,350));
        scholarshipPanel.setMaximumSize(new Dimension(800, 350));
        JLabel title = new JLabel("Schools where average GPA is higher than overall average:  ", JLabel.CENTER);
        title.setFont(new Font("Proxima Nova", Font.ITALIC, 20));
        scholarshipPanel.add(title, BorderLayout.CENTER);
        this.getContentPane().add(scholarshipPanel);

        ArrayList<String[]> res = dbHandler.findAvgGPAWhereHigher();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"applicantSchool", "applicantGPA"});
        for (String[] r : res) {
            tableModel.addRow(r);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scholarshipPanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(scholarshipPanel);

    }



    /* SELECTION CRITERIA */
    public void getSelectioncriteriaButton() {
        selectioncriteriaButton.setPreferredSize(new Dimension(40,20));
        selectioncriteriaButton.setText("View GPA requirements for each major (Group By)");
        selectioncriteriaButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        selectioncriteriaButton.addActionListener(new goToSelectioncriteriaListener(selectioncriteriaButton));
        selectioncriteriaButton.setFocusable(false);
        mainPanel.add(selectioncriteriaButton, BorderLayout.CENTER);
    }

    class goToSelectioncriteriaListener implements ActionListener {
        private JButton jbutton;
        public goToSelectioncriteriaListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            SelectioncriteriaPanel();

            repaint();
            revalidate();
        }
    }

    public void SelectioncriteriaPanel() {
        SelectioncriteriaPanel.setLayout(new BoxLayout(SelectioncriteriaPanel, BoxLayout.PAGE_AXIS));
        SelectioncriteriaPanel.setBackground(Color.getHSBColor(66,66,66));
        SelectioncriteriaPanel.setPreferredSize(new Dimension(800,350));
        SelectioncriteriaPanel.setMaximumSize(new Dimension(800, 350));
        JLabel title = new JLabel("Minimum GPA required for each Major:  ", JLabel.CENTER);
        title.setFont(new Font("Proxima Nova", Font.ITALIC, 20));
        SelectioncriteriaPanel.add(title, BorderLayout.CENTER);
        this.getContentPane().add(SelectioncriteriaPanel);

        ArrayList<String[]> res = dbHandler.findminGPAforMajor();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"major", "minimumGPA"});
        for (String[] r : res) {
            tableModel.addRow(r);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        SelectioncriteriaPanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(SelectioncriteriaPanel);

    }


    /* COMMITTEE */
    public void getCommitteeButton() {
        committeeButton.setPreferredSize(new Dimension(40,20));
        committeeButton.setText("Unused");
        committeeButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        committeeButton.setFocusable(false);
        mainPanel.add(committeeButton, BorderLayout.CENTER);
    }


    /* REFERENCE LETTER */
    public void getReferenceletterButton() {
        referenceletterButton.setPreferredSize(new Dimension(40,20));
        referenceletterButton.setText("Unused");
        referenceletterButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        referenceletterButton.setFocusable(false);
        mainPanel.add(referenceletterButton, BorderLayout.CENTER);
    }


    /* SUPERINTENDENT */
    public void getSuperintendentButton() {
        superintendentButton.setPreferredSize(new Dimension(40,20));
        superintendentButton.setText("Unused");
        superintendentButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        superintendentButton.setFocusable(false);
        mainPanel.add(superintendentButton, BorderLayout.CENTER);
    }


    /* DONOR */
    public void getDonorButton() {
        donorButton.setPreferredSize(new Dimension(40,20));
        donorButton.setText("Unused");
        donorButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        donorButton.setFocusable(false);
        mainPanel.add(donorButton, BorderLayout.CENTER);;
    }


    // PROJECTION
    public void getProjectionButton() {
        projectionButton.setPreferredSize(new Dimension(50,20));
        projectionButton.setText("Projection");
        projectionButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        projectionButton.addActionListener( new gotoProjectionListener(projectionButton));
        projectionButton.setFocusable(false);
        mainPanel.add(projectionButton, BorderLayout.CENTER);
    }

    // applicant button's ActionListener
    class gotoProjectionListener implements ActionListener {
        private JButton jbutton;
        public gotoProjectionListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            getContentPane().add(projectionPanel);
            repaint();
            revalidate();
        }
    }



    //////////// ALL PANEL METHODS ////////////////

    // More methods

    // ALL Management STUFF IS HERE
    public void getManagementButton() {
        managementButton.setPreferredSize(new Dimension(50,20));
        managementButton.setText("Manage Applicants (Insert,Delete,Update)");
        managementButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        managementButton.addActionListener( new gotoManagementListener(managementButton));
        managementButton.setFocusable(false);
        mainPanel.add(managementButton, BorderLayout.CENTER);
    }

    class gotoManagementListener implements ActionListener {
        private JButton jbutton;
        public gotoManagementListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            getContentPane().add(managementPanel);
            repaint();
            revalidate();
        }
    }
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
