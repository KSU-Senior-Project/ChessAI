package Chess.Pieces;

import Chess.Tile.Tile;
import Engine.Engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends BasePiece{
	private int direction;
	
    public Bishop(Image image, Tile current_Tile, int direction,int ID) {
        super(image, current_Tile,"Bishop",ID);
        this.direction = direction;
        this.attack_distance = 2;
    }

    @Override
    public boolean can_move_to_tile(int x,int y,int distance){
        return distance <= movement_distance && !Engine.isOccupied_Tile(x,y) && (direction == BasePiece.DIRECTION_UP ? y > getCurrent_Tile().getRelative_y() : y < getCurrent_Tile().getRelative_y());
    }
}
