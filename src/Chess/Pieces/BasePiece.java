package Chess.Pieces;

import Chess.Team.Team;
import Chess.Tile.Tile;
import Engine.Engine;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class BasePiece extends MoveableImage{
    private Team current_Team;
    private Tile current_Tile;

    public BasePiece(Image image, Tile current_Tile) {
        super(image, current_Tile.getAbsolute_x(), current_Tile.getRelative_y());
        setCurrent_Tile(current_Tile);
        getCurrent_Tile().setCurrent_piece(this);
    }

    public Tile getCurrent_Tile() {
        return current_Tile;
    }

    public void setCurrent_Tile(Tile current_Tile) {
        if(this.current_Tile != null)
            this.current_Tile.setCurrent_piece(null);
        this.current_Tile = current_Tile;
            current_Tile.setCurrent_piece(this);
        this.setX(current_Tile.getAbsolute_x());
        this.setY(current_Tile.getAbsolute_y());
    }

    public Team getCurrent_Team() {
        return current_Team;
    }

    public void setCurrent_Team(Team current_Team) {
        this.current_Team = current_Team;
    }

    public List<Tile> getAvailable_Tiles(){
        List<Tile> available_Tiles = new ArrayList<Tile>();
        for(int y = current_Tile.getRelative_y() - 1; y <= current_Tile.getRelative_y() + 1;y++){
            for(int x = current_Tile.getRelative_x() - 1 ;x <= current_Tile.getRelative_x() + 1; x++){
                if(Engine.inBounds(x,y) && (!Engine.isOccupied_Tile(x,y) || Engine.isEnemy_Tile(x,y,this)))
                    available_Tiles.add(Engine.tiles[y][x]);
            }
        }
        return available_Tiles;
    }
}
