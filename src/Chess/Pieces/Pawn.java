package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends BasePiece {

    private int direction;
    private boolean hasMoved;

    public Pawn(Image image, Tile current_Tile,int direction) {
        super(image, current_Tile,"Pawn");
        setDirection(direction);
        hasMoved = false;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    
    //rewrite to getAvailable_Moves()
    public List<Tile> getAvailable_Moves(){
    	int spaces = hasMoved ? 2 : 1;
    	
        List<Tile> allDirection_Tiles = super.getAvailable_Moves(spaces);
        List<Tile> movement_tiles = new ArrayList<Tile>();
        
        for(int i =0; i < allDirection_Tiles.size();i++){
        	Tile directionTile = allDirection_Tiles.get(i);
            if(direction == BasePiece.DIRECTION_UP) {	            	
            	if(directionTile.getRelative_y() > this.getCurrent_Tile().getRelative_y()) {
            		movement_tiles.add(directionTile);
            	}
            }
            else if(direction == BasePiece.DIRECTION_DOWN) {
            	if(directionTile.getRelative_y() < this.getCurrent_Tile().getRelative_y()) {
            		movement_tiles.add(directionTile);
            	}
            }
        }
        
        hasMoved = true;
        return movement_tiles;
    }
    
    //create new method named getAvailable_Captures()
    //todo - need to be notified if move was successful to update hasMoved
    @Override
    public List<Tile> getAvailable_Tiles() {
    	int spaces = hasMoved ? 2 : 1;
	
        List<Tile> allDirection_Tiles = super.getAvailable_Tiles(spaces);
        List<Tile> movement_tiles = new ArrayList<Tile>();
        
        for(int i =0; i < allDirection_Tiles.size();i++){
        	Tile directionTile = allDirection_Tiles.get(i);
            if(direction == BasePiece.DIRECTION_UP) {	            	
            	if(directionTile.getRelative_y() > this.getCurrent_Tile().getRelative_y()) {
            		movement_tiles.add(directionTile);
            	}
            }
            else if(direction == BasePiece.DIRECTION_DOWN) {
            	if(directionTile.getRelative_y() < this.getCurrent_Tile().getRelative_y()) {
            		movement_tiles.add(directionTile);
            	}
            }
        }
        
        hasMoved = true;
        return movement_tiles;
    }

	@Override
	public List<Tile> getAvailable_Captures() {
		return super.getAvailable_Captures(2);
	}
}
