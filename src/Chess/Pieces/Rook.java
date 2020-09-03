package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Rook extends BasePiece{
    public Rook(Image image, Tile current_Tile) {
        super(image, current_Tile);
    }

    @Override
    public List<Tile> getAvailable_Tiles() {
        return this.getAvailable_Tiles(1);
    }
}
