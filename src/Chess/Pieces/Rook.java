package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Rook extends BasePiece{
    public Rook(Image image, Tile current_Tile,int ID) {
        super(image, current_Tile,"Rook",ID);
        this.movement_distance = 1;
        this.attack_distance = 2;
    }
}
