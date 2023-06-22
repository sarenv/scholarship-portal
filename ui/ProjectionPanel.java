package ui;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProjectionPanel {
    private JComboBox<String> tableDropDown;

    private JPanel checkboxesPanel;

    private HashMap<String, List<String>> tableColumns;

    void generate() {
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
            ArrayList<String[]> result = DatabaseConnectionHandler.projectionTable();
            generateViewFrame(selectedTable,result,columns);
        });
        checkboxesPanel.add(projectionButton);
        checkboxesPanel.repaint();
        checkboxesPanel.revalidate();
    }

    private void generateViewFrame(String tableHeader, ArrayList<String[]> columnsResult, ArrayList<String> columnHeader)


}