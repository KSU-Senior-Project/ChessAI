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
    public final int STATUS_HEIGHT = 40;

    public GUI(){
        super("Chess AI");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.getContentPane().setPreferredSize(new Dimension(BOARD_WIDTH+200, BOARD_HEIGHT + LOG_HEIGHT + STATUS_HEIGHT));
        
        new Engine();

        //status panel to show whos turn - moves made - and a button to end turn
        StatusPanel status = new StatusPanel(BOARD_WIDTH + (SPACER_WIDTH*2), STATUS_HEIGHT);        
        this.getContentPane().add(status, BorderLayout.NORTH);
        
        
        //capture panels to show total captures each player has made
        CapturePanel player1caps = new CapturePanel(SPACER_WIDTH, BOARD_HEIGHT,1);
        this.getContentPane().add(player1caps, BorderLayout.WEST);
        
        CapturePanel player2caps = new CapturePanel(SPACER_WIDTH, BOARD_HEIGHT,2);
        this.getContentPane().add(player2caps, BorderLayout.EAST);
        
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
