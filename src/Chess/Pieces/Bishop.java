package Chess.Pieces;

import Chess.Tile.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends BasePiece{
	private int direction;
	
    public Bishop(Image image, Tile current_Tile, int direction,int ID) {
        super(image, current_Tile,"Bishop",ID);
        this.direction = direction;
    }
    
    @Override
    public List<Tile> getAvailable_Tiles() {
    	int spaces = 4;
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
        return movement_tiles;
    }

	@Override
	public List<Tile> getAvailable_Captures() {
		return super.getAvailable_Captures(2);
	}

	@Override
	public List<Tile> getAvailable_Moves() {
		int spaces = 4;
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
        return movement_tiles;
	}
    
}
