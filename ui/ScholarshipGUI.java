package ui;

import database.DatabaseConnectionHandler;
import ui.panel.ProjectionPanel;
import ui.panel.SelectionPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScholarshipGUI extends JFrame {
    // Put buttons and shit and instantiate other shit here

    private DatabaseConnectionHandler dbHandler = null;
    private JPanel mainPanel = new JPanel();;
    private JPanel applicantPanel = new JPanel();

    private ProjectionPanel projectionPanel = new ProjectionPanel(this);
    private SelectionPanel selectionPanel = new SelectionPanel(this);

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
    private JButton selectionButton;

    public ScholarshipGUI() {
        dbHandler = new DatabaseConnectionHandler();
        // Initializers and this.adds go in here
        initializeButtons();
        initializer();
        mainPanel();
        //applicantPanel();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        selectionButton = new JButton();
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
        getSelectionButton();
        this.getContentPane().add(mainPanel);
        return mainPanel;
    }

    //////////// ALL BUTTONS + ACTION LISTENER METHODS //////////////////////


    public class DisplayPanel extends JPanel {
        private JTable table;

        public DisplayPanel(ArrayList<String[]> data) {
            setLayout(new BorderLayout());

            // Create table model
            DefaultTableModel model = new DefaultTableModel();
            table = new JTable(model);

            // Add table columns
            for (int i = 0; i < data.get(0).length; i++) {
                model.addColumn("Column " + (i + 1));
            }

            // Add table rows
            for (String[] row : data) {
                model.addRow(row);
            }

            // Add table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        }
    }
    /* APPLICANT */
    // lead to applicantPanel
    public void getApplicantButton() {
        applicantButton.setPreferredSize(new Dimension(50,20));
        applicantButton.setText("Applicant table");
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

            ArrayList<String[]> res = dbHandler.applicantTable();
            DisplayPanel displayPanel = new DisplayPanel(res);

            getContentPane().add(displayPanel);
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
        JLabel title = new JLabel("Applicant table: ", JLabel.CENTER);
        title.setFont(new Font("Proxima Nova", Font.ITALIC, 20));
        applicantPanel.add(title, BorderLayout.CENTER);
        this.getContentPane().add(applicantPanel);
    }




    /* APPLICATION */
    // lead to applicationPanel
    public void getApplicationButton() {
        applicationButton.setPreferredSize(new Dimension(40,20));
        applicationButton.setText("Application table");
        applicationButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        applicationButton.setFocusable(false);
        mainPanel.add(applicationButton, BorderLayout.CENTER);
    }


    /* SCHOLARSHIP */
    // lead to scholarshipPanel
    public void getScholarshipButton() {
        scholarshipButton.setPreferredSize(new Dimension(40,20));
        scholarshipButton.setText("Scholarship table");
        scholarshipButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        scholarshipButton.setFocusable(false);
        mainPanel.add(scholarshipButton, BorderLayout.CENTER);
    }



    /* SELECTION CRITERIA */
    public void getSelectioncriteriaButton() {
        selectioncriteriaButton.setPreferredSize(new Dimension(40,20));
        selectioncriteriaButton.setText("Selection Criteria table");
        selectioncriteriaButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        selectioncriteriaButton.setFocusable(false);
        mainPanel.add(selectioncriteriaButton, BorderLayout.CENTER);
    }


    /* COMMITTEE */
    public void getCommitteeButton() {
        committeeButton.setPreferredSize(new Dimension(40,20));
        committeeButton.setText("Committee table");
        committeeButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        committeeButton.setFocusable(false);
        mainPanel.add(committeeButton, BorderLayout.CENTER);
    }


    /* REFERENCE LETTER */
    public void getReferenceletterButton() {
        referenceletterButton.setPreferredSize(new Dimension(40,20));
        referenceletterButton.setText("Reference Letter table");
        referenceletterButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        referenceletterButton.setFocusable(false);
        mainPanel.add(referenceletterButton, BorderLayout.CENTER);
    }


    /* SUPERINTENDENT */
    public void getSuperintendentButton() {
        superintendentButton.setPreferredSize(new Dimension(40,20));
        superintendentButton.setText("Superintendent table");
        superintendentButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        superintendentButton.setFocusable(false);
        mainPanel.add(superintendentButton, BorderLayout.CENTER);
    }


    /* DONOR */
    public void getDonorButton() {
        donorButton.setPreferredSize(new Dimension(40,20));
        donorButton.setText("Donor table");
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

    // ALL SELECTION STUFF IS HERE
    public void getSelectionButton() {
        selectionButton.setPreferredSize(new Dimension(50,20));
        selectionButton.setText("Selection");
        selectionButton.setFont(new Font("Proxima Nova",Font.PLAIN, 15));
        selectionButton.addActionListener( new gotoSelectionListener(selectionButton));
        selectionButton.setFocusable(false);
        mainPanel.add(selectionButton, BorderLayout.CENTER);
    }

    class gotoSelectionListener implements ActionListener {
        private JButton jbutton;
        public gotoSelectionListener(JButton button) {
            this.jbutton = button;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().remove(mainPanel);
            getContentPane().add(selectionPanel);
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
