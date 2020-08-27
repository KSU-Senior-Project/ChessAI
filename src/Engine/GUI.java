package Engine;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;


/*Base GUI class*/
public class GUI extends JFrame{

    public static Painter painter;
    public GUI(){
        super("Chess AI");
        this.getContentPane().setPreferredSize(new Dimension(640,640));
        this.pack();
        painter = new Painter();
        this.getContentPane().add(painter);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.requestFocus();
        painter.repaint();
    }

}
