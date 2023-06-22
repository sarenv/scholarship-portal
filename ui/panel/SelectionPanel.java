package ui.panel;

import ui.ScholarshipGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SelectionPanel extends  ContentPanel {
    private JPanel resultsPanel;
    private JScrollPane ScrollingPane;
    private SelectionPanel(ScholarshipGUI scholarshipGUI)  {
        super(scholarshipGUI);
    }

    @Override
    protected void generate() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(createTitle("Selecting"), BorderLayout.NORTH);
//        headerPanel.add(getSearchP)
    }

}
