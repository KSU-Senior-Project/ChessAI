package Engine_v2;

import Chess.Pieces.BasePiece;
import Chess.Pieces.MoveableImage;
import Chess.Team.Team;
import Chess.Tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


/*Base Painter JPanel that will control underlying rendering*/
public class GameBoard extends JPanel implements MouseMotionListener, MouseListener {

    private BasePiece selectedPiece;
    private List<Tile> availableMovements = new ArrayList<>();
    private List<Tile> availableCaptures = new ArrayList<>();
    private int relative_x;
    private int relative_y;

    public GameBoard(int SQUARE_SIZE){
        addMouseMotionListener(this);
        addMouseListener(this);
        this.setPreferredSize(new Dimension(SQUARE_SIZE * 8, SQUARE_SIZE * 8));
        this.setLayout(new GridLayout(8,8));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,this.getWidth(),this.getHeight());

        for(Tile[] row : Engine.tiles){
            for(Tile tile : row){
                g.setColor(availableMovements.contains(tile) ? Color.BLUE : tile.getBackground());
                g.fillRect(tile.getAbsolute_x() + 1, tile.getAbsolute_y() + 1, tile.getSize() - 2, tile.getSize() - 2);
                if(availableCaptures.contains(tile)) {
                    g.setColor(Color.RED);
                    g.fillRect(tile.getAbsolute_x() + 1, tile.getAbsolute_y() + 1, tile.getSize() -2, tile.getSize() - 2);
                }
            }
        }
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
        if(getClickedPiece() != null && getClickedPiece().getCurrent_Team() == Engine.current_Turn()){
            if(selectedPiece != null)
                selectedPiece.setCurrent_Tile(selectedPiece.getCurrent_Tile());
            setSelected_piece(getClickedPiece());
        }
        else if(selectedPiece == null){
            if(getClickedPiece() != null && getClickedPiece().getCurrent_Team() == Engine.current_Turn())
                setSelected_piece(getClickedPiece());
        }else{
            if(availableMovements.contains(getRelativeTile()) || availableCaptures.contains(getRelativeTile())) {
                Engine.make_move(selectedPiece, Engine.tiles[relative_y][relative_x]);
                setSelected_piece(getClickedPiece());
            }else{
                selectedPiece.setCurrent_Tile(selectedPiece.getCurrent_Tile());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved(e);
        mouseClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectedPiece != null) selectedPiece.setMidpoint(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        relative_x = e.getX() / Engine.SQUARE_SIZE;
        relative_y = e.getY() / Engine.SQUARE_SIZE;
    }
    public Tile getRelativeTile(){
        return Engine.tiles[relative_y][relative_x];
    }
    public BasePiece getClickedPiece(){
        return Engine.tiles[relative_y][relative_x].getCurrent_piece();
    }
    public BasePiece getSelectedPiece(){
        return this.selectedPiece;
    }
    public void setSelected_piece(BasePiece piece){
        this.selectedPiece = piece;
        if(selectedPiece != null)
            selectedPiece.update_movement();
        setAvailableCaptures(selectedPiece == null || Engine.move_count >= 3 ? new ArrayList<>() : selectedPiece.getAvailable_captures());
        setAvailableMovements(selectedPiece == null || Engine.move_count >= 3 ? new ArrayList<>() : selectedPiece.getAvailable_movements());
    }
    public void setAvailableMovements(List<Tile> list){
        availableMovements = list;
    }
    public void setAvailableCaptures(List<Tile> list){
        availableCaptures = list;
    }
}
