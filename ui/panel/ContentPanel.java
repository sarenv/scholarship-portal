package ui.panel;

import ui.ScholarshipGUI;

import javax.swing.*;
import java.awt.*;

public abstract class ContentPanel  extends JPanel {
    protected ScholarshipGUI scholarshipGUI;

    public ContentPanel(ScholarshipGUI scholarshipGUI) {
        this.scholarshipGUI = scholarshipGUI;
        generate();
    }

    protected abstract void generate();

    protected JLabel createTitle(String title) {
        JLabel label = new JLabel(title);
        Font font = label.getFont();

        label.setFont(new Font("Proxima Nova",Font.PLAIN, 35));
        label.setPreferredSize(new Dimension(5,40));

        return label;


    }

    protected JLabel createSubtitle(String subtitle) {
        JLabel label = new JLabel(subtitle);
        Font font = label.getFont();

        label.setFont(new Font("Proxima Nova",Font.PLAIN, 40));
        label.setPreferredSize(new Dimension(5,25));

        return label;
    }

}
