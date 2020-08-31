package Engine;


/*This is the base class where most of the game logic will be handled */

import Chess.Pieces.ChessPieceBase;
import Chess.Team.Team;
import Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Engine {
    public static Tile[][] tiles;
    public static Image[][] chess_pieces;

    public static Team[] teams;
    public static int turn = -1;


    public Engine(){
        //Creating teams
        teams = new Team[2];
        teams[0] = new Team(Team.Controller.player);
        teams[1] = new Team(Team.Controller.bot);

        //Rendering in Chess Pieces
        try {
            chess_pieces = new Image[2][6];
            BufferedImage chess_sprite = ImageIO.read(getClass().getClassLoader().getResource("Chess.png"));
            for(int y = 0; y < 2;y++){
                for(int x = 0; x < 6;x++){
                    //Creating Image
                    chess_pieces[y][x] = chess_sprite.getSubimage(x * 45,y * 45,45,45).getScaledInstance(GUI.square_size,GUI.square_size,Image.SCALE_SMOOTH);
                }
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }

        /*Adding in Chess Board Tiles (REDO or REWRITE when needed)
        This is the base initialization that assigns an image to a piece type as well as a team type.
        */
        tiles = new Tile[8][8];
        Color current_Color;

        int[][] board = new int[][]{
                {4,3,2,0,1,2,3,4},
                {5,5,5,5,5,5,5,5},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {5,5,5,5,5,5,5,5},
                {4,3,2,1,0,2,3,4},
        };

        for(int y = 0; y < 8;y++){
            for(int x = 0; x < 8;x++){
                if(y % 2 == 0)
                    current_Color = x % 2 == 1 ? Color.BLACK : Color.WHITE;
                else
                    current_Color = x % 2 == 0 ? Color.BLACK : Color.WHITE;
                tiles[y][x] = new Tile(x,y,current_Color);

                System.out.println(board[1][1]);
                if(board[y][x] != -1)
                    teams[y < 3 ? 0 : 1].get_Chess_Pieces().add(new ChessPieceBase(chess_pieces[y < 3 ? 0 : 1][board[y][x]],tiles[y][x].get_Bounds().x,tiles[y][x].get_Bounds().y));
            }
        }
    }







    public static void player_move(){
        if(current_Turn().get_Player() != Team.Controller.player)
            return;
    }
    public static void ai_move(){
        if(current_Turn().get_Player() != Team.Controller.bot)
            return;
    }




    public static Team current_Turn(){return teams[turn % 2];}
    public static Team next_Turn(){return teams[++turn % 2];}
    public static boolean occupied_Tile(int x,int y){
        return inBounds(x,y) && tiles[y][x].get_Chess_Piece() != null;
    }
    public static boolean inBounds(int x,int y){
        return x >=0 && x < 8 && y >= 0 && y < 8;
    }
}
