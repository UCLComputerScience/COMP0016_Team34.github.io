package GUI.Widgets;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * a roundbutton
 */
public class RoundButton extends JButton {
    /**
     * create a round button
     * @param label the text on the button
     */
    public RoundButton(String label) {
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    /**
     * paint the button with graohics
     * @param g graphics to be used on the button
     */
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width-1, getSize().height-1);
        super.paintComponent(g);
    }

    /**
     * paint the border with graphics
     * @param g graphics to be used as border
     */
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
    }

    Shape shape;

    /**
     * judge if this coordinate is inside the button
     * @param x the x value of the coordinate
     * @param y the y value of the coordinate
     * @return whether this coordinate is inside the button
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}