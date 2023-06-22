package ui.panel;

import ui.ScholarshipGUI;

import javax.swing.*;
import java.awt.*;

public class AggregationPanel  extends BasePanel {
    public AggregationPanel(ScholarshipGUI scholarshipGUI) {
        super(scholarshipGUI);

    }

    @Override
    protected void generate() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        add(createTitle("Aggregation"), BorderLayout.NORTH);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.X_AXIS));
        add(subPanel,BorderLayout.WEST);

//        subPanel.add(generateMinGPA);
//        subPanel.add(generate)
    }
}
