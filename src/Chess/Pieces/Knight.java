package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Knight extends BasePiece{
    public Knight(Image image, Tile current_Tile,int ID) {
        super(image, current_Tile,"Knight",ID);
    }

    @Override
    public List<Tile> getAvailable_Tiles() {
        return this.getAvailable_Tiles(5);
    }

	@Override
	public List<Tile> getAvailable_Captures() {
		return super.getAvailable_Captures(2);
	}

	@Override
	public List<Tile> getAvailable_Moves() {
		return this.getAvailable_Moves(5);
	}
}
