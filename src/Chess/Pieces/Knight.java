package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Knight extends BasePiece{
    public Knight(Image image, Tile current_Tile,int ID) {
        super(image, current_Tile,"Knight",ID);
        this.movement_distance = 5;
        this.attack_distance = 5;
    }
}
