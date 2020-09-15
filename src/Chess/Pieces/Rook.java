package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.List;

public class Rook extends BasePiece{
    public Rook(Image image, Tile current_Tile,int ID) {
        super(image, current_Tile,"Rook",ID);
    }

    @Override
    public List<Tile> getAvailable_Tiles() {
        return this.getAvailable_Tiles(1);
    }

	@Override
	public List<Tile> getAvailable_Captures() {
		return super.getAvailable_Captures(2);
	}

	@Override
	public List<Tile> getAvailable_Moves() {
		 return this.getAvailable_Moves(1);
	}
}
