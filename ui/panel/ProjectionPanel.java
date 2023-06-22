
package ui.panel;

import ui.ScholarshipGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProjectionPanel extends  ContentPanel {
    private JComboBox<String> tableDropDown;
    private JPanel checkboxesPanel;
    private HashMap<String, List<String>> tableColumns;

    public ProjectionPanel(ScholarshipGUI scholarshipGUI) {
        super(scholarshipGUI);
    }

    @Override
    protected void generate() {
        initaliseDropDownCheckBoxes();
        tableColumns.put("Applicant", new ArrayList<>(Arrays.asList("applicantID", "firstName", "lastName", "applicantEmail", "applicantSchool", "applicantGPA")));
        tableDropDown = new JComboBox<>(tableColumns.keySet().toArray(new String[0]));
        tableDropDown.addActionListener(e -> {
            updateCheckBoxes();
        });
        checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new BoxLayout(checkboxesPanel, BoxLayout.PAGE_AXIS));
        setLayout(new BorderLayout());
        add(tableDropDown, BorderLayout.NORTH);
        add(checkboxesPanel, BorderLayout.CENTER);
    }


    private void initaliseDropDownCheckBoxes() {
        tableColumns = new HashMap<>();
        tableColumns.put("Applicant", new ArrayList<>(Arrays.asList("applicantID", "firstName", "lastName", "applicantEmail", "applicantSchool", "applicantGPA")));
        tableColumns.put("Application",new ArrayList<>(Arrays.asList("applicantID","deadline")));
        tableColumns.put("ReferenceLetter",new ArrayList<>(Arrays.asList("referenceID","applicantID","referenceName","referenceEmail","referenceSchool","referencePosition")));
        tableColumns.put("AppliesTo",new ArrayList<>(Arrays.asList("applicantID","applicationID","scholarshipID","donorID")));
        tableColumns.put("ScholarshipCommittee",new ArrayList<>(Arrays.asList("committeeID")));
        tableColumns.put("Donor",new ArrayList<>(Arrays.asList("donorID")));
        tableColumns.put("Superintendent",new ArrayList<>(Arrays.asList("superintendentID","firstName","lastName")));
        tableColumns.put("SelectionCriteria",new ArrayList<>(Arrays.asList("criteriaID","major","minimumGPA","familyIncome")));
        tableColumns.put("Evaluates",new ArrayList<>(Arrays.asList("applicationID","committeeID","status")));
        tableColumns.put("Scholarship",new ArrayList<>(Arrays.asList("scholarshipID","amount","donorID","")));
        tableColumns.put("Renewable",new ArrayList<>(Arrays.asList("applicantID","amount","dateOfRenewal","donorID")));
        tableColumns.put("OneTime",new ArrayList<>(Arrays.asList("applicantID","amount","donorID")));
    }

    private void updateCheckBoxes() {
        checkboxesPanel.removeAll();
        String selectionTable = (String) tableDropDown.getSelectedItem();
        for (String column : tableColumns.get(selectionTable)) {
            JCheckBox checkbox = new JCheckBox(column);
            checkboxesPanel.add(checkbox);
        }
        JButton projectionButton = new JButton("Projection");
        projectionButton.addActionListener(e-> {
            ArrayList<String> columns = new ArrayList<>();
            for (Component c: checkboxesPanel.getComponents()) {
                if (c instanceof  JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) c;
                    if (checkBox.isSelected()) {
                        columns.add(checkBox.getText());
                    }
                }
            }
             ArrayList<String[]> result = null;
            generateView(selectionTable,result,columns);
        });
        checkboxesPanel.add(projectionButton);
        checkboxesPanel.repaint();
        checkboxesPanel.revalidate();
    }

    private void generateView(String tableHeader, ArrayList<String[]> result, ArrayList<String> colHeader) {
        JFrame viewFrame = new JFrame();
        JTable table = new JTable();
        JTableHeader header = table.getTableHeader();
        JPanel panel = new JPanel(new BorderLayout());
        Object[] columns = colHeader.toArray();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        if(result != null){
            for(String[] row : result){
                model.addRow(row);
            }
        }
        table.setModel(model);
        JScrollPane pane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        panel.add(pane,BorderLayout.WEST);
        viewFrame.add(panel);
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setSize(1000,1000);
        viewFrame.setResizable(false);
        viewFrame.setVisible(true);
    }

}
