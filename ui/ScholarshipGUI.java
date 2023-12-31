package ui;

import database.DatabaseConnectionHandler;
import model.Application;
import ui.panel.JoiningPanel;
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
import java.util.concurrent.ThreadLocalRandom;

public class ScholarshipGUI extends JFrame {
    // Put buttons and shit and instantiate other shit here

    private DatabaseConnectionHandler dbHandler = null;
    private JPanel mainPanel = new JPanel();;
    private JPanel applicantPanel = new JPanel();

    private JPanel ApplicantTablePanel = new JPanel();


    private JPanel applicationPanel = new JPanel();

    private JPanel scholarshipPanel = new JPanel();
    private JPanel SelectioncriteriaPanel = new JPanel();

    private ProjectionPanel projectionPanel = new ProjectionPanel(this);
    private ManagementPanel managementPanel = new ManagementPanel(this);

    private JoiningPanel joiningPanel = new JoiningPanel(this);

    private JLabel title = new JLabel();
    //    private JLabel question = new JLabel();
    private JButton applicantButton;
    private JButton applicationButton;
    private JButton scholarshipButton;

    private JButton insertButton;

    private JButton applicantTableButton;
    private JButton selectioncriteriaButton;
    private JButton donorButton;

    private JButton projectionButton;
    private JButton managementButton;
    private JButton joiningButton;

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
        scholarshipButton = new JButton();
        donorButton = new JButton();
        selectioncriteriaButton = new JButton();
        projectionButton = new JButton();
        managementButton = new JButton();
        applicantTableButton = new JButton();
        insertButton = new JButton();
        joiningButton = new JButton();


    }


    // main menu panel to switch to all panels
    public JPanel mainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.getHSBColor(66,66,66));
        mainPanel.setPreferredSize(new Dimension(800,350));
        mainPanel.setMaximumSize(new Dimension(800, 350));
        JLabel question = new JLabel("Choose: ", JLabel.CENTER);
        question.setFont(new Font("Proxima Nova", Font.PLAIN, 25));
        mainPanel.add(question, BorderLayout.CENTER);
        getApplicantButton();
        getApplicationButton();
        getScholarshipButton();
        getSelectioncriteriaButton();
        getApplicantTableButton();
        getJoiningButton();
        getProjectionButton();
        getManagementButton();
        getInsertButton();
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

    public void getApplicantTableButton() {
        applicantTableButton.setPreferredSize(new Dimension(40,20));
        applicantTableButton.setText("Applicant Table");
        applicantTableButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        applicantTableButton.addActionListener(new goToApplicantTableListener(applicantTableButton));
        applicantTableButton.setFocusable(false);
        mainPanel.add(applicantTableButton, BorderLayout.CENTER);
    }

    class goToApplicantTableListener implements ActionListener {
        private JButton jbutton;
        public goToApplicantTableListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            ApplicantTablePanel();
            repaint();
            revalidate();
        }
    }

    public void ApplicantTablePanel() {
        ApplicantTablePanel.setLayout(new BoxLayout(ApplicantTablePanel, BoxLayout.PAGE_AXIS));
        ApplicantTablePanel.setBackground(Color.getHSBColor(66,66,66));
        ApplicantTablePanel.setPreferredSize(new Dimension(800,350));
        ApplicantTablePanel.setMaximumSize(new Dimension(800, 350));
        JLabel title = new JLabel("Applicant Table:  ", JLabel.CENTER);
        title.setFont(new Font("Proxima Nova", Font.ITALIC, 20));
        ApplicantTablePanel.add(title, BorderLayout.CENTER);
        this.getContentPane().add(ApplicantTablePanel);

        ArrayList<String[]> res = dbHandler.applicantTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ApplicantID", "firstName", "lastName", "applicantEmail", "applicantSchool", "applicantGPA"});
        for (String[] r : res) {
            tableModel.addRow(r);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        ApplicantTablePanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(ApplicantTablePanel);

    }









    /* SUPERINTENDENT */


    /* DONOR */


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
        managementButton.setText("Manage Applicants (Select,Delete,Update)");
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

    public void getJoiningButton() {
        joiningButton.setPreferredSize(new Dimension(50,20));
        joiningButton.setText("Check Applicant Status (Join)");
        joiningButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        joiningButton.addActionListener( new gotoJoiningListener(joiningButton));
        joiningButton.setFocusable(false);
        mainPanel.add(joiningButton, BorderLayout.CENTER);
    }

    class gotoJoiningListener implements ActionListener {
        private JButton jbutton;
        public gotoJoiningListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            getContentPane().add(joiningPanel);
            repaint();
            revalidate();
        }
    }

    // INSERTION
    // INSERTIONN
    public void getInsertButton() {
        insertButton.setPreferredSize(new Dimension(50,20));
        insertButton.setText("Insert");
        insertButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        insertButton.addActionListener(e->infoPrompt()) ;
        insertButton.setFocusable(false);
        mainPanel.add(insertButton, BorderLayout.CENTER);
    }
    private  void infoPrompt() {
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

        JLabel deadlineLabel = new JLabel("Deadline (dd/mm/yyyy FORMAT PLEASE):");
        JTextField deadlineTextField = new JTextField(70);

        int eyeDee = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
        applicationIDTextField.setText(String.valueOf(eyeDee));
        applicationIDTextField.setEnabled(false);


        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            try {
                Integer tionID = Integer.valueOf(applicationIDTextField.getText());
                Integer antID = Integer.valueOf(applicantIDTextField.getText());
                String deadline = String.valueOf(deadlineTextField.getText());

                DatabaseConnectionHandler.insertApplication(new Application(tionID,antID,deadline));
                JOptionPane.showMessageDialog(this, "Insert Passed!");
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
