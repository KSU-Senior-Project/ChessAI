package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Knight extends BasePiece{
    public Knight(Image image, Tile current_Tile) {
        super(image, current_Tile);
    }

    @Override
    public List<Tile> getAvailable_Tiles() {
        return this.getAvailable_Tiles(5);
    }
}
