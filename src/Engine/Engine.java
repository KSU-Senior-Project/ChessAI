package Engine;


/*This is the base class where most of the game logic will be handled */

import Chess.Pieces.*;
import Chess.Team.Team;
import Chess.Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Engine {
    public static Tile[][] tiles;
    public static Team[] teams;
    public static int turn = -1;


    public Engine(){
        //Creating teams
        teams = new Team[2];
        teams[0] = new Team(Team.Controller.player);
        teams[1] = new Team(Team.Controller.bot);

        //Converting Sprite Sheet into Images
        Image[][] chess_pieces = null;
        try {
            chess_pieces = new Image[2][6];
            BufferedImage chess_sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("Chess.png")));
            for(int y = 0; y < 2;y++){
                for(int x = 0; x < 6;x++)
                    chess_pieces[y][x] = chess_sprite.getSubimage(x * 45,y * 45,45,45).getScaledInstance(GUI.square_size,GUI.square_size,Image.SCALE_SMOOTH);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }

        //Creating Tile, Assigning correct chess piece to tile, assigning that chess piece to the correct team
        tiles = new Tile[8][8];
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
            for(int x = 0; x < 8; x++) {
                tiles[y][x] = new Tile(x, y, GUI.square_size, (x + y) % 2 == 1 ? Color.BLACK : Color.WHITE);

                if(board[y][x] == -1)
                    continue;

                int current_team = y < tiles.length / 2 ? 0 : 1;

                switch (board[y][x]){
                    case 5:
                        teams[current_team].addChess_Piece(new Pawn(chess_pieces[current_team][board[y][x]],tiles[y][x],current_team == 0 ? 1 : -1));
                        break;
                    case 4:
                        teams[current_team].addChess_Piece(new Rook(chess_pieces[current_team][board[y][x]],tiles[y][x]));
                        break;
                    case 3:
                        teams[current_team].addChess_Piece(new Knight(chess_pieces[current_team][board[y][x]],tiles[y][x]));
                        break;
                    case 2:
                        teams[current_team].addChess_Piece(new Bishop(chess_pieces[current_team][board[y][x]],tiles[y][x],current_team == 0 ? 1 : -1));
                        break;
                    case 1:
                        teams[current_team].addChess_Piece(new King(chess_pieces[current_team][board[y][x]],tiles[y][x]));
                        break;
                    case 0:
                        teams[current_team].addChess_Piece(new Queen(chess_pieces[current_team][board[y][x]],tiles[y][x]));
                        break;
                }
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


    public static boolean isEnemy_Tile(int x,int y,BasePiece piece){
        return isOccupied_Tile(x,y) && tiles[y][x].getCurrent_piece().getCurrent_Team() != piece.getCurrent_Team();
    }
    public static boolean isOccupied_Tile(int x,int y){
        return inBounds(x,y) && tiles[y][x].getCurrent_piece() != null;
    }
    public static boolean inBounds(int x,int y){
        return x >=0 && x < 8 && y >= 0 && y < 8;
    }
    public static Team current_Turn(){return teams[turn % 2];}
    public static Team next_Turn(){return teams[++turn % 2];}
}
