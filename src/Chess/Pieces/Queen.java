package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Queen extends BasePiece {
    public Queen(Image image, Tile current_Tile,int ID) {
        super(image, current_Tile,"Queen",ID);
        this.movement_distance = 3;
    }
}
