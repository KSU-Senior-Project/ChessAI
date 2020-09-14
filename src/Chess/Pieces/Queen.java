package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Queen extends BasePiece {
    public Queen(Image image, Tile current_Tile) {
        super(image, current_Tile,"Queen");
    }

    @Override
    public List<Tile> getAvailable_Tiles() {
        return this.getAvailable_Tiles(3);
    }

	@Override
	public List<Tile> getAvailable_Moves() {
		return this.getAvailable_Moves(3);
	}

	@Override
	public List<Tile> getAvailable_Captures() {
		return super.getAvailable_Captures(2);
	}
}
