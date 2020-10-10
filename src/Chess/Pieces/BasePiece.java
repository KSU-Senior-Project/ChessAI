package Chess.Pieces;

import Chess.Team.Team;
import Chess.Tile.Tile;
import Engine_v2.Engine;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public abstract class BasePiece extends MoveableImage{
    private Team current_Team;
    private Tile current_Tile;
    public static int DIRECTION_UP = -1;
    public static int DIRECTION_DOWN = 0;
    public String Name;
    public int ID;
    public List<Tile> available_movements = new ArrayList<Tile>();
    public List<Tile> available_captures = new ArrayList<Tile>();
    public int movement_distance = 1;
    public int attack_distance = 1;


    public BasePiece(Image image, Tile current_Tile,String Name,int ID) {
        super(image, current_Tile.getAbsolute_x(), current_Tile.getRelative_y());
        setName(Name);
        setID(ID);
        setCurrent_Tile(current_Tile);
    }

    public Tile getCurrent_Tile() {
        return current_Tile;
    }

    public void setCurrent_Tile(Tile current_Tile) {
        if(this.current_Tile != null)
            this.current_Tile.setCurrent_piece(null);
        this.current_Tile = current_Tile;
            current_Tile.setCurrent_piece(this);
        this.setX(current_Tile.getAbsolute_x());
        this.setY(current_Tile.getAbsolute_y());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Team getCurrent_Team() {
        return current_Team;
    }

    public void setCurrent_Team(Team current_Team) {
        this.current_Team = current_Team;
    }

    public void setMovement_Distance(int movement_distance){ this.movement_distance = movement_distance; }

    public void setAttack_distance(int movement_distance){ this.attack_distance = movement_distance; }

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

    public void move(Tile tile){
        setCurrent_Tile(tile);
        update_movement();
    }
    public void update_movement(){
        available_captures = new ArrayList<>();
        available_movements = new ArrayList<>();
        List<TileDistance> open = new ArrayList<>();

        for(int y = -1;y < 2;y++){
            for(int x = -1;x < 2;x++)
                open.add(new TileDistance(getCurrent_Tile(),new Point(x,y),0));
        }

        while(open.size() > 0){
            TileDistance current = open.remove(0);
            int x = current.direction.x + current.tile.getRelative_x();
            int y = current.direction.y + current.tile.getRelative_y();

            if(!Engine.inBounds(x,y) || available_captures.contains(Engine.tiles[y][x]) || available_movements.contains(Engine.tiles[y][x]))
                continue;

            if(current.distance + 1 < Math.max(movement_distance,attack_distance) && !Engine.isOccupied_Tile(x,y))
                open.add(new TileDistance(Engine.tiles[y][x],current.direction,current.distance + 1));
            if(can_move_to_tile(x,y,current.distance + 1))
                available_movements.add(Engine.tiles[y][x]);
            if(can_capture_tile(x,y,current.distance + 1))
                available_captures.add(Engine.tiles[y][x]);
        }
    }
    public boolean can_move_to_tile(int x,int y,int distance){
        return distance <= movement_distance && !Engine.isOccupied_Tile(x,y);
    }
    public boolean can_capture_tile(int x,int y,int distance){
        if(Engine.move_count >= 2){
            return false;
        }
        else return distance <= attack_distance && Engine.isEnemy_Tile(x,y,this);
    }

    public List<Tile> getAvailable_movements(){
        return this.available_movements;
    }
    public List<Tile> getAvailable_captures(){
        return this.available_captures;
    }
}
