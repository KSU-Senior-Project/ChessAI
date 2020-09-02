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
import Chess.Tile.Tile;


/*Base Painter JPanel that will control underlying rendering*/
public class Painter extends JPanel implements MouseMotionListener, MouseListener {

    private BasePiece selected_piece;
    private int relative_x;
    private int relative_y;

    public Painter(){
        //Setting defaults for JPanel
        addMouseMotionListener(this);
        addMouseListener(this);
        this.setLayout(new GridLayout(8,8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,this.getWidth(),this.getHeight());
        List<Tile> selected_Tiles = new ArrayList<Tile>();
        if(selected_piece != null)
            selected_Tiles = selected_piece.getAvailable_Tiles();

        //Drawing Tiles
        for(int y = 0; y < Engine.tiles.length;y++){
            for(int x = 0; x < Engine.tiles.length;x++){

                //REDO OR REWRITE CODE TO CLEANUP
                g.setColor(Engine.tiles[y][x].getBackground());
                if(selected_Tiles.contains(Engine.tiles[y][x]))
                    g.setColor(Color.BLUE);
                g.fillRect(Engine.tiles[y][x].getAbsolute_x(),Engine.tiles[y][x].getAbsolute_y(),GUI.square_size,GUI.square_size);
                g.setColor(Color.BLACK);
                g.drawRect(Engine.tiles[y][x].getAbsolute_x(),Engine.tiles[y][x].getAbsolute_y(),GUI.square_size,GUI.square_size);
            }
        }

        //Drawing Chess Pieces
        for(int i = 0; i < Engine.teams.length;i++){
            for(MoveableImage piece : Engine.teams[i].get_Chess_Pieces())
                piece.draw_Image(g,this);
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(selected_piece == null){
            selected_piece = Engine.tiles[relative_y][relative_x].getCurrent_piece();
        }else if(selected_piece.getAvailable_Tiles().contains(Engine.tiles[relative_y][relative_x])){
            selected_piece.setCurrent_Tile(Engine.tiles[relative_y][relative_x]);
        }else{
            selected_piece = Engine.tiles[relative_y][relative_x].getCurrent_piece();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved(e);
        if(selected_piece == null)
            return;

        if(selected_piece.getAvailable_Tiles().contains(Engine.tiles[relative_y][relative_x]))
            selected_piece.setCurrent_Tile(Engine.tiles[relative_y][relative_x]);
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
        mouseMoved(e);
        if(selected_piece != null)
           selected_piece.setMidpoint(e.getX(),e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        relative_x = e.getX() / GUI.square_size;
        relative_y = e.getY() / GUI.square_size;
    }
}
