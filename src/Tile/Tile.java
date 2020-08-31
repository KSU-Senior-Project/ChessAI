package Tile;

import Chess.Pieces.ChessPieceBase;
import Engine.GUI;

import java.awt.*;

public class Tile{
    private Color Background;
    private int x;
    private int y;
    private ChessPieceBase chess_piece;
    private Rectangle bounds;

    public Tile(int x,int y,Color Background){
        super();
        set_X(x);
        set_Y(y);
        set_Background(Background);
        bounds = new Rectangle(x * GUI.square_size,y * GUI.square_size, GUI.square_size,GUI.square_size);
    }
    public Tile(int x,int y,Color Background,ChessPieceBase chess_piece){
        this(x,y,Background);
    }


    public int get_X(){
        return this.x;
    }
    public int get_Y(){
        return this.y;
    }
    public Color get_Background(){return this.Background;}
    public ChessPieceBase get_Chess_Piece(){return this.chess_piece;}
    public Rectangle get_Bounds(){return this.bounds;}

    public void set_X(int x){
        this.x = x;
    }
    public void set_Y(int y){
        this.y = y;
    }
    public void set_Background(Color Background){this.Background = Background;}
    public void set_Chess_Piece(ChessPieceBase chess_piece){this.chess_piece = chess_piece;}
}
