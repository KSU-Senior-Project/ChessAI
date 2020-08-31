package Chess.Team;

import Chess.Pieces.ChessPieceBase;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public enum Controller{
        player,bot
    }

    private Controller player;
    private List<ChessPieceBase> chess_pieces;



    public Team(Controller player){
        set_Player(player);
        set_Chess_Pieces(new ArrayList<ChessPieceBase>());
    }


    public List<ChessPieceBase> get_Chess_Pieces(){return this.chess_pieces;}
    public Controller get_Player(){return this.player;}

    public void set_Player(Controller player){this.player = player;}
    public void set_Chess_Pieces(List<ChessPieceBase> chess_pieces){this.chess_pieces = chess_pieces;}

}
