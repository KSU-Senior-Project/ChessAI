package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Pawn extends BasePiece {

    private int direction;

    public Pawn(Image image, Tile current_Tile,int direction) {
        super(image, current_Tile);
        setDirection(direction);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public List<Tile> getAvailable_Tiles() {
        List<Tile> allDirection_Tiles = this.getAvailable_Tiles(1);
        for(int i =0; i < allDirection_Tiles.size();i++){
            if(allDirection_Tiles.get(i).getRelative_y() != getCurrent_Tile().getRelative_y() + direction){
                allDirection_Tiles.remove(i);
                i--;
            }
        }
        return allDirection_Tiles;
    }
}
