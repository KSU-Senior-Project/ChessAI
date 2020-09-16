package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class King extends BasePiece {

    public King(Image image, Tile current_Tile,int ID) {
        super(image, current_Tile,"King",ID);
        this.movement_distance = 3;
    }
}
