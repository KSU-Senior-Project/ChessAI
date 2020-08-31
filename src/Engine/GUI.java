package Engine;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;


/*Base GUI class*/
public class GUI extends JFrame{

    public static Painter painter;
    public static final int square_size = 40;

    public GUI(){
        super("Chess AI");
        this.getContentPane().setPreferredSize(new Dimension(8 * square_size,8 * square_size));
        this.pack();
        new Engine();

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        painter = new Painter();
        this.getContentPane().add(painter);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.requestFocus();
        painter.repaint();
    }

}
