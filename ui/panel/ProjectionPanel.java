
package ui.panel;

import ui.ScholarshipGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProjectionPanel extends BasePanel {
    private JComboBox<String> dropDownTable;
    private JPanel checkboxesPanel;
    private HashMap<String, List<String>> tableCol;

    public ProjectionPanel(ScholarshipGUI scholarshipGUI) {
        super(scholarshipGUI);
    }

    @Override
    protected void generate() {
        initialiseDropDownCheckBoxes();
        // first thing they will see
        tableCol.put("Applicant", new ArrayList<>(Arrays.asList("applicantID", "firstName", "lastName", "applicantEmail", "applicantSchool", "applicantGPA")));


        dropDownTable = new JComboBox<>(tableCol.keySet().toArray(new String[0]));
        dropDownTable.addActionListener(e -> {updateCheckBoxes();});
        checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new BoxLayout(checkboxesPanel, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        add(dropDownTable, BorderLayout.NORTH);
        add(checkboxesPanel, BorderLayout.CENTER);
    }


    private void initialiseDropDownCheckBoxes() {
        tableCol = new HashMap<>();
        tableCol.put("Applicant", new ArrayList<>(Arrays.asList("applicantID", "firstName", "lastName", "applicantEmail", "applicantSchool", "applicantGPA")));
        tableCol.put("Application",new ArrayList<>(Arrays.asList("applicantID","deadline")));
        tableCol.put("ReferenceLetter",new ArrayList<>(Arrays.asList("referenceID","applicantID","referenceName","referenceEmail","referenceSchool","referencePosition")));
        tableCol.put("AppliesTo",new ArrayList<>(Arrays.asList("applicantID","applicationID","scholarshipID","donorID")));
        tableCol.put("ScholarshipCommittee",new ArrayList<>(Arrays.asList("committeeID")));
        tableCol.put("Donor",new ArrayList<>(Arrays.asList("donorID")));
        tableCol.put("Superintendent",new ArrayList<>(Arrays.asList("superintendentID","firstName","lastName")));
        tableCol.put("SelectionCriteria",new ArrayList<>(Arrays.asList("criteriaID","major","minimumGPA","familyIncome")));
        tableCol.put("Evaluates",new ArrayList<>(Arrays.asList("applicationID","committeeID","status")));
        tableCol.put("Scholarship",new ArrayList<>(Arrays.asList("scholarshipID","amount","donorID","")));
        tableCol.put("Renewable",new ArrayList<>(Arrays.asList("applicantID","amount","dateOfRenewal","donorID")));
        tableCol.put("OneTime",new ArrayList<>(Arrays.asList("applicantID","amount","donorID")));
    }

    private void updateCheckBoxes() {
        checkboxesPanel.removeAll();
        String selectionTable = (String) dropDownTable.getSelectedItem();
        for (String col : tableCol.get(selectionTable)) {
            JCheckBox checkbox = new JCheckBox(col);
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
            // CHANGE HERE NEEDS TO RETURN SMTH
             ArrayList<String[]> result = null;
            generateProjection(selectionTable,result,columns);
        });
        checkboxesPanel.add(projectionButton);
        checkboxesPanel.repaint();
        checkboxesPanel.revalidate();
    }

    private void generateProjection(String tableHeader, ArrayList<String[]> result, ArrayList<String> colHeader) {
        JFrame projectionFrame = new JFrame();
        JTable table = new JTable();
        JLabel title = createTitle(tableHeader);
        title.setFont(new Font("Proxima Nova",Font.PLAIN, 30));
        JPanel panel = new JPanel(new BorderLayout());
        Object[] columns = colHeader.toArray();
        DefaultTableModel model = new DefaultTableModel();


        model.setColumnIdentifiers(columns);
        // making the rows
        if(result != null){
            for(String[] row : result){
                model.addRow(row);
            }
        }
        table.setModel(model);
        JScrollPane pane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panel.add(title,BorderLayout.NORTH);
        panel.add(pane,BorderLayout.WEST);
        projectionFrame.add(panel);
        projectionFrame.setSize(500,500);
        projectionFrame.setVisible(true);
        projectionFrame.setResizable(false);
    }

}
