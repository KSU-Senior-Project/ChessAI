package Engine;

import javax.swing.*;
import java.awt.*;


/*Base GUI class*/
public class GUI extends JFrame{

    public static Painter painter;
    public static ActionLog log;
    public static final int square_size = 60;
    public final int BOARD_HEIGHT = 480;
    public final int BOARD_WIDTH = 480;
    public final int SPACER_WIDTH = 100;
    public final int LOG_HEIGHT = 100;

    public GUI(){
        super("Chess AI");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.getContentPane().setPreferredSize(new Dimension(BOARD_WIDTH+200, BOARD_HEIGHT + LOG_HEIGHT));
        
        new Engine();

        //spacers for visual effect - could replace one with a button panel or something?
        JPanel top_spacer = new JPanel();
        top_spacer.setBackground(Color.BLACK);
        top_spacer.setPreferredSize(new Dimension(BOARD_WIDTH + (SPACER_WIDTH*2), 10));
        this.getContentPane().add(top_spacer, BorderLayout.NORTH);
        
        JPanel left_spacer = new JPanel();
        left_spacer.setBackground(Color.BLACK);
        left_spacer.setPreferredSize(new Dimension(SPACER_WIDTH, BOARD_HEIGHT));
        this.getContentPane().add(left_spacer, BorderLayout.WEST);
        
        JPanel right_spacer = new JPanel();
        right_spacer.setBackground(Color.BLACK);
        right_spacer.setPreferredSize(new Dimension(SPACER_WIDTH, BOARD_HEIGHT));
        this.getContentPane().add(right_spacer, BorderLayout.EAST);
        
        //painter for the board
        painter = new Painter();
        this.getContentPane().add(painter, BorderLayout.CENTER);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.requestFocus();
        painter.repaint();
        
        //a scrollable text area to log the actions of the game
        log = new ActionLog(BOARD_WIDTH+ (SPACER_WIDTH * 2), LOG_HEIGHT);
        this.getContentPane().add(log, BorderLayout.SOUTH);
        
        this.pack();
        
        
        
    }

}
