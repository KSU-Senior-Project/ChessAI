package Chess.Pieces;

import Chess.Team.Team;
import Chess.Tile.Tile;
import Engine.Engine;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class BasePiece extends MoveableImage{
    private Team current_Team;
    private Tile current_Tile;
    public static int DIRECTION_UP = -1;
    public static int DIRECTION_DOWN = 0;

    public BasePiece(Image image, Tile current_Tile) {
        super(image, current_Tile.getAbsolute_x(), current_Tile.getRelative_y());
        setCurrent_Tile(current_Tile);
        getCurrent_Tile().setCurrent_piece(this);
    }

    public Tile getCurrent_Tile() {
        return current_Tile;
    }

    public void setCurrent_Tile(Tile current_Tile) {
        //REDO OR REWRITE CALLS
        if(this.current_Tile != null)
            this.current_Tile.setCurrent_piece(null);
        this.current_Tile = current_Tile;
            current_Tile.setCurrent_piece(this);
        this.setX(current_Tile.getAbsolute_x());
        this.setY(current_Tile.getAbsolute_y());
    }

    public Team getCurrent_Team() {
        return current_Team;
    }

    public void setCurrent_Team(Team current_Team) {
        this.current_Team = current_Team;
    }


    private class TileDistance{
        public int distance;
        public Point direction;
        public Tile tile;

        public TileDistance(Tile tile,Point direction,int distance) {
            this.distance = distance;
            this.direction = direction;
            this.tile = tile;
        }
    }
    
    protected List<Tile> getAvailable_Captures(int distance){
    	List<Tile> explored = new ArrayList<Tile>();
        List<TileDistance> open = new ArrayList<TileDistance>();
        for(int y = -1;y < 2;y++){
            for(int x = -1;x < 2;x++)
                open.add(new TileDistance(getCurrent_Tile(),new Point(x,y),0));
        }

        while(open.size() > 0) {
            TileDistance current = open.remove(0);
            int x = current.direction.x + current.tile.getRelative_x();
            int y = current.direction.y + current.tile.getRelative_y();
            if(current.distance + 1 > distance)
                continue;

            if(!Engine.inBounds(x,y) || explored.contains(Engine.tiles[y][x]))
                continue;
            if(Engine.isEnemy_Tile(x,y,this)) {
                explored.add(Engine.tiles[y][x]);
                open.add(new TileDistance(Engine.tiles[y][x],current.direction,current.distance + 1));
            }
            
        }
        return explored;
    }
    
    protected List<Tile> getAvailable_Moves(int distance){
    	//REDO OR REWRITE
        List<Tile> explored = new ArrayList<Tile>();
        List<TileDistance> open = new ArrayList<TileDistance>();
        for(int y = -1;y < 2;y++){
            for(int x = -1;x < 2;x++)
                open.add(new TileDistance(getCurrent_Tile(),new Point(x,y),0));
        }

        while(open.size() > 0) {
            TileDistance current = open.remove(0);
            int x = current.direction.x + current.tile.getRelative_x();
            int y = current.direction.y + current.tile.getRelative_y();
            if(current.distance + 1 > distance)
                continue;

            if(!Engine.inBounds(x,y) || explored.contains(Engine.tiles[y][x]))
                continue;
            if (!Engine.isOccupied_Tile(x, y)) {
                explored.add(Engine.tiles[y][x]);
                open.add(new TileDistance(Engine.tiles[y][x],current.direction,current.distance + 1));
            }
            //ignore enemy tiles for movement
            else if(Engine.isEnemy_Tile(x,y,this)) {}
                //explored.add(Engine.tiles[y][x]);
        }
        return explored;
    }
    
    
    protected List<Tile> getAvailable_Tiles(int distance){
        //REDO OR REWRITE
        List<Tile> explored = new ArrayList<Tile>();
        List<TileDistance> open = new ArrayList<TileDistance>();
        for(int y = -1;y < 2;y++){
            for(int x = -1;x < 2;x++)
                open.add(new TileDistance(getCurrent_Tile(),new Point(x,y),0));
        }

        while(open.size() > 0) {
            TileDistance current = open.remove(0);
            int x = current.direction.x + current.tile.getRelative_x();
            int y = current.direction.y + current.tile.getRelative_y();
            if(current.distance + 1 > distance)
                continue;

            if(!Engine.inBounds(x,y) || explored.contains(Engine.tiles[y][x]))
                continue;
            if (!Engine.isOccupied_Tile(x, y)) {
                explored.add(Engine.tiles[y][x]);
                open.add(new TileDistance(Engine.tiles[y][x],current.direction,current.distance + 1));
            }else if(Engine.isEnemy_Tile(x,y,this))
                explored.add(Engine.tiles[y][x]);
        }
        return explored;
    }
    
    public abstract List<Tile> getAvailable_Captures();
    
    public abstract List<Tile> getAvailable_Moves();

    public abstract List<Tile> getAvailable_Tiles();
}
