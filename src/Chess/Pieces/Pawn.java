package Chess.Pieces;

import Chess.Tile.Tile;
import Engine_v2.Engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends BasePiece {

    private int direction;

    public Pawn(Image image, Tile current_Tile,int direction,int ID) {
        super(image, current_Tile,"Pawn",ID);
        setDirection(direction);
        setCurrent_Tile(current_Tile);
        this.movement_distance = 1;
    }
    @Override
    public boolean can_move_to_tile(int x,int y,int distance){
        return distance <= movement_distance &&
                !Engine.isOccupied_Tile(x,y) &&
                (direction == BasePiece.DIRECTION_UP ? y > getCurrent_Tile().getRelative_y() : y < getCurrent_Tile().getRelative_y());
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
