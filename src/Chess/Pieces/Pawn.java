package Chess.Pieces;

import Engine.Engine;
import Tile.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPieceBase{
    private int direction;

    public Pawn(Image chess_piece,int x,int y,int direction){
        super(chess_piece,x,y);
        set_Direction(direction);
    }



    public int get_Direction(){return this.direction;}
    public void set_Direction(int direction){this.direction = direction;}

    @Override
    public List<Tile> get_Available_Positions(Tile current_Tile) {
        List<Tile> available_Tiles = new ArrayList<Tile>();

        if(Engine.inBounds(current_Tile.get_X(),current_Tile.get_Y() + direction))
            available_Tiles.add(Engine.tiles[current_Tile.get_X()][current_Tile.get_Y() + direction]);

        if(Engine.occupied_Tile(current_Tile.get_X() + 1,current_Tile.get_Y() + direction))
            available_Tiles.add(Engine.tiles[current_Tile.get_X() + 1][current_Tile.get_Y() + direction]);

        if(Engine.occupied_Tile(current_Tile.get_X() - 1,current_Tile.get_Y() + direction))
            available_Tiles.add(Engine.tiles[current_Tile.get_X() + 1][current_Tile.get_Y() + direction]);

        return available_Tiles;
    }
}
