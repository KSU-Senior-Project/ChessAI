package Chess.Team;

import Chess.Pieces.BasePiece;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public enum Controller{
        player,bot
    }

    private Controller player;
    private List<BasePiece> chess_pieces;



    public Team(Controller player){
        setPlayer(player);
        setChess_Pieces(new ArrayList<BasePiece>());
    }


    public List<BasePiece> get_Chess_Pieces(){return this.chess_pieces;}
    public Controller get_Player(){return this.player;}

    public void setPlayer(Controller player){this.player = player;}
    public void setChess_Pieces(List<BasePiece> chess_pieces){this.chess_pieces = chess_pieces;}
    public void addChess_Piece(BasePiece piece){
        this.chess_pieces.add(piece);
        piece.setCurrent_Team(this);
    }
}
