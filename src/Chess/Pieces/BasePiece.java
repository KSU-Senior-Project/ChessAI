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
        public Tile tile;

        public TileDistance(Tile tile,int distance) {
            this.distance = distance;
            this.tile = tile;
        }
    }
    protected List<Tile> getAvailable_Tiles(int distance){
        List<Tile> explored = new ArrayList<Tile>();
        List<TileDistance> open = new ArrayList<TileDistance>();
        open.add(new TileDistance(getCurrent_Tile(),0));

        while(open.size() > 0) {
            TileDistance current = open.remove(0);
            if(current.distance + 1 > distance)
                continue;

            for (int y = current.tile.getRelative_y() - 1; y <= current.tile.getRelative_y() + 1; y++) {
                for (int x = current.tile.getRelative_x() - 1; x <= current.tile.getRelative_x() + 1; x++) {
                    if(!Engine.inBounds(x,y) || explored.contains(Engine.tiles[y][x]))
                        continue;
                    if (!Engine.isOccupied_Tile(x, y)) {
                        explored.add(Engine.tiles[y][x]);
                        open.add(new TileDistance(Engine.tiles[y][x],current.distance + 1));
                    }else if(Engine.isEnemy_Tile(x,y,this))
                        explored.add(Engine.tiles[y][x]);
                }
            }
        }
        return explored;
    }

    public abstract List<Tile> getAvailable_Tiles();
}
