package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import Chess.Pieces.BasePiece;
import Chess.Pieces.MoveableImage;
import Chess.Team.Team;
import Chess.Tile.Tile;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


/*Base Painter JPanel that will control underlying rendering*/
public class Painter extends JPanel implements MouseMotionListener, MouseListener {

    private BasePiece selected_piece;
    private List<Tile> selected_Tiles = new ArrayList<Tile>();
    private List<Tile> capture_Tiles = new ArrayList<Tile>();
    private int relative_x;
    private int relative_y;

    public Painter(){
        //Setting defaults for JPanel
        addMouseMotionListener(this);
        addMouseListener(this);
        this.setPreferredSize(new Dimension(GUI.SQUARE_SIZE * 8,GUI.SQUARE_SIZE * 8));
        this.setMaximumSize(new Dimension(GUI.SQUARE_SIZE * 8,GUI.SQUARE_SIZE * 8));
        this.setMinimumSize(new Dimension(GUI.SQUARE_SIZE * 8,GUI.SQUARE_SIZE * 8));
        this.setLayout(new GridLayout(8,8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

        for(Tile[] row : Engine.tiles){
            for(Tile tile : row){
                //REDO OR REWRITE. ADDS SPACING IN TILES WHICH MAY NOT BE PLEASING TO SOME
            	
                g.setColor(selected_Tiles.contains(tile) ? Color.BLUE : tile.getBackground());
                g.fillRect(tile.getAbsolute_x() + 1, tile.getAbsolute_y() + 1, tile.getSize() - 2, tile.getSize() - 2);
                
                
                if(capture_Tiles.contains(tile)) {
                	g.setColor(Color.RED);
                    g.fillRect(tile.getAbsolute_x() + 1, tile.getAbsolute_y() + 1, tile.getSize() -2, tile.getSize() - 2);
                }                
            }
        }

        //Drawing Chess Pieces
        for(Team team : Engine.teams){
            for(MoveableImage piece : team.get_Chess_Pieces())
                piece.draw_Image(g,this);
        }

        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       mousePressed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(selected_Tiles.contains(Engine.tiles[relative_y][relative_x])) {
            selected_piece.setCurrent_Tile(Engine.tiles[relative_y][relative_x]);
        	ActionLog.appendAction("Selected " + selected_piece);
        }
        setSelected_piece(Engine.tiles[relative_y][relative_x].getCurrent_piece());
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved(e);
        if(selected_piece == null) return;

        if(selected_Tiles.contains(Engine.tiles[relative_y][relative_x]))
           mouseClicked(e);
        else
            selected_piece.setCurrent_Tile(selected_piece.getCurrent_Tile());
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selected_piece != null) selected_piece.setMidpoint(e.getX(),e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        relative_x = e.getX() / GUI.SQUARE_SIZE;
        relative_y = e.getY() / GUI.SQUARE_SIZE;
    }

    public void setSelected_piece(BasePiece piece){
        this.selected_piece = piece;
        this.selected_Tiles = piece == null ? new ArrayList<Tile>() : selected_piece.getAvailable_Moves();
        this.capture_Tiles = piece == null ? new ArrayList<Tile>() : selected_piece.getAvailable_Captures();
    }
}
