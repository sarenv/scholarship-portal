package ui;

import database.DatabaseConnectionHandler;

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
        dbHandler = new DatabaseConnectionHandler();
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

    //////////// ALL BUTTONS + ACTION LISTENER METHODS //////////////////////

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
            getContentPane().add(applicantPanel);
            repaint();
            revalidate();
        }
    }

//    public class DisplayPanel extends JPanel {
//        private JTable table;
//
//        public DisplayPanel(ArrayList<String[]> data) {
//            setLayout(new BorderLayout());
//
//            DefaultTableModel model = new DefaultTableModel();
//            table = new JTable(model);
//
//            for (int i = 0; i < data.get(0).length; i++) {
//                model.addColumn("Column " + (i + 1));
//            }
//
//            for (String[] row : data) {
//                model.addRow(row);
//            }
//
//            JScrollPane scrollPane = new JScrollPane(table);
//            add(scrollPane, BorderLayout.CENTER);
//        }
//    }
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

        ArrayList<String[]> data = dbHandler.applicantTable();

//        DisplayPanel displayPanel = new DisplayPanel(data);
//
//        JFrame frame = new JFrame("Table Display Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//        frame.getContentPane().add(displayPanel);
//        frame.setVisible(true);

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

    //////////// ALL PANEL METHODS ////////////////
    private void showTable(ArrayList<String[]> table, JScrollPane scrollPane) {
        String[] columnNames = new String[table.get(0).length];
        String[][] data = new String[table.size() - 1][table.get(0).length];
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(0).length; j++) {
                if (i == 0) {
                    columnNames[j] = table.get(0)[j];
                } else {
                    data[i - 1][j] = table.get(i)[j];
                }
            }
        }
        // Initializing the JTable
        JTable jTable;
        jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 200, 300);
        scrollPane.setViewportView(jTable);
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
