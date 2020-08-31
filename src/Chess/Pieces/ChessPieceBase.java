package Chess.Pieces;

import Chess.Team.Team;
import Tile.Tile;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public class ChessPieceBase {
    private Image chess_piece;
    private int x;
    private int y;
    private int size;
    private Team team;

    public ChessPieceBase(Image chess_piece,int x,int y){
        this.chess_piece = chess_piece;
        set_X(x);
        set_Y(y);
    }


    public void set_X(int x){this.x = x;}
    public void set_Y(int y){this.y = y;}
    public void set_Team(Team team){this.team = team;}

    public int get_Y(){return this.y;}
    public int get_X(){return this.x;}
    public Team get_Team(){return this.team;}


    public void draw_Piece(Graphics g, ImageObserver observer){
        g.drawImage(chess_piece,x,y,observer);
    }

    public List<Tile> get_Available_Positions(Tile current_Tile){return null;}
}
