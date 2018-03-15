package field;

import java.awt.*;

import javax.swing.*;


public class View extends JPanel {
    private static final int GRID_SIZE = 16;
    private Field field;

    public View(Field field) {
        this.field = field;
    }

    public void paint(Graphics g) {
        super.paint(g);
        
    }
}
