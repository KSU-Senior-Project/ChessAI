package Engine_v2;

import Chess.Pieces.*;
import Chess.Team.Team;
import Chess.Tile.Tile;
import Combat.CombatGUI;
import Dice.Dice;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Engine extends Thread{
    final int FPS = 60;
    public static GUI mainFrame;
    public static GameBoard gameBoard;
    public static StatusPanel statusPanel;
    public static ActionLog log;
    public static CapturePanel[] capturePanels;

    public static final int SQUARE_SIZE = 60;
    public static final int SPACER_WIDTH = 300;
    public static final int LOG_HEIGHT = 100;
    public static final int STATUS_HEIGHT = 40;

    public static int[][][] combat_stats;
    public static Team[] teams;
    public static Dice[] dice;
    public static Tile[][] tiles;

    public static int move_count = 0;
    public static int turn = 0;
    public static CombatGUI combatGUI;
    public static BasePiece leftBishopCommander;
    public static BasePiece rightBishopCommander;
    public static BasePiece kingCommander;
    private static ArrayList<BasePiece> leftBishopCorp = new ArrayList<>();
    private static ArrayList<BasePiece> rightBishopCorp = new ArrayList<>();
    private static ArrayList<BasePiece> kingCorp = new ArrayList<>();

    //each corp gets one move per turn.
    private static int bishop1MoveCount = 0;
    private static int bishop2MoveCount = 0;
    private static int king2MoveCount = 0;
    private static int treeDepth = 5; //Number of turns to look ahead


    public enum GameState {
        paused,
        combat,
        running,
        stop,
        end
    }

    public static GameState state;

    public Engine(){
        combat_stats = new int[][][]{
                /*Queen*/ {{6,5,4},{6,5,4},{6,5,4},{6,5,4},{6,5},{6,5,4,3,2}},
                /*King*/ {{6,5,4},{6,5,4},{6,5,4},{6,5,4},{6,5},{}},
                /*Bishop*/{{6,5},{6,5},{6,5,4},{6,5},{6,5},{6,5,4,3}},
                /*Knight*/{{6},{6},{6,5,4},{6,5,4},{6,5},{6,5,4,3,2}},
                /*Rook*/{{6,5,4},{6,5,4},{6,5},{6,5},{6},{6,5}},
                /*Pawn*/{{6},{6},{6,5},{6},{6},{6,5,4}},
        };

        //Creating teams
        System.out.println("creating teams");
        teams = new Team[2];
        teams[0] = new Team(Team.Controller.player);
        teams[1] = new Team(Team.Controller.bot);

        //Converting Sprite Sheet into Images
        Image[][] chess_pieces = null;
        try {
            chess_pieces = new Image[2][6];
            BufferedImage chess_sprite = ImageIO.read(new File("resources/Chess.png"));
            System.out.println("loaded resources");
            for(int y = 0; y < 2;y++){
                for(int x = 0; x < 6;x++)
                    chess_pieces[y][x] = chess_sprite.getSubimage(x * 45,y * 45,45,45).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,Image.SCALE_SMOOTH);
            }
            dice = new Dice[6];
            for(int x = 0; x < 6; x++){
                dice[x] = new Dice(x + 1,chess_sprite.getSubimage(x * 45,90,45,45).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE,Image.SCALE_SMOOTH));
            }
        }catch(Exception e){
            System.out.println("error loading images");
            System.out.println(e.toString());
        }

        //Creating Tile, Assigning correct chess piece to tile, assigning that chess piece to the correct team
        tiles = new Tile[8][8];


        //possible create different numbers for black/white same peice
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
                tiles[y][x] = new Tile(x, y, SQUARE_SIZE, (x + y) % 2 == 1 ? Color.BLACK : Color.WHITE);

                if(board[y][x] == -1)
                    continue;

                int current_team = y < tiles.length / 2 ? 0 : 1;

                switch (board[y][x]){
                    case 5:
                        teams[current_team].addChess_Piece(new Pawn(chess_pieces[current_team][board[y][x]],tiles[y][x],
                                current_team == 0 ? BasePiece.DIRECTION_UP : BasePiece.DIRECTION_DOWN,board[y][x]));
                        break;
                    case 4:
                        teams[current_team].addChess_Piece(new Rook(chess_pieces[current_team][board[y][x]],tiles[y][x],board[y][x]));
                        break;
                    case 3:
                        teams[current_team].addChess_Piece(new Knight(chess_pieces[current_team][board[y][x]],tiles[y][x],board[y][x]));
                        break;
                    case 2:
                        teams[current_team].addChess_Piece(new Bishop(chess_pieces[current_team][board[y][x]],tiles[y][x],
                                current_team == 0 ? BasePiece.DIRECTION_UP : BasePiece.DIRECTION_DOWN,board[y][x]));
                        break;
                    case 1:
                        teams[current_team].addChess_Piece(new King(chess_pieces[current_team][board[y][x]],tiles[y][x],board[y][x]));
                        break;
                    case 0:
                        teams[current_team].addChess_Piece(new Queen(chess_pieces[current_team][board[y][x]],tiles[y][x],board[y][x]));
                        break;
                }
            }
        }
        //Create the three AI groups
        leftBishopCommander = teams[1].get_Chess_Pieces().get(10);
        rightBishopCommander = teams[1].get_Chess_Pieces().get(13);
        kingCommander = teams[1].get_Chess_Pieces().get(11);

        leftBishopCorp.add(teams[1].get_Chess_Pieces().get(0));
        leftBishopCorp.add(teams[1].get_Chess_Pieces().get(1));
        leftBishopCorp.add(teams[1].get_Chess_Pieces().get(2));
        leftBishopCorp.add(teams[1].get_Chess_Pieces().get(9));

        rightBishopCorp.add(teams[1].get_Chess_Pieces().get(5));
        rightBishopCorp.add(teams[1].get_Chess_Pieces().get(6));
        rightBishopCorp.add(teams[1].get_Chess_Pieces().get(7));
        rightBishopCorp.add(teams[1].get_Chess_Pieces().get(14));

        kingCorp.add((teams[1].get_Chess_Pieces().get(3)));
        kingCorp.add((teams[1].get_Chess_Pieces().get(4)));
        kingCorp.add((teams[1].get_Chess_Pieces().get(8)));
        kingCorp.add((teams[1].get_Chess_Pieces().get(12)));
        kingCorp.add((teams[1].get_Chess_Pieces().get(15)));

        state = GameState.running;

        mainFrame = new GUI(SQUARE_SIZE,SPACER_WIDTH,STATUS_HEIGHT,LOG_HEIGHT);
        gameBoard = mainFrame.getGameBoard();
        capturePanels = mainFrame.getCapturePanels();
        log = mainFrame.getLog();
        statusPanel = mainFrame.getStatusPanel();

        start();
    }

    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / FPS;
        double delta = 0;
        while (state != GameState.stop) {
            long now = System.nanoTime();
            delta = delta + ((now - lastTime) / ns);
            lastTime = now;
            while (delta >= 1) {
                delta--;
            }
            update();
        }
    }


    public void update(){
        switch (state){
            case running:
                gameBoard.repaint();
                break;
            case paused:
                break;
            case combat:
                if(!combatGUI.isVisible()){
                    switch (combatGUI.getCombatState()){
                        case leave:
                            combatGUI.getPiece_one().setCurrent_Tile(combatGUI.getPiece_one().getCurrent_Tile());
                            gameBoard.setSelected_piece(combatGUI.getPiece_one());
                            combatGUI.getPiece_one().setPreviousMove("move");
                        break;
                        case won:
                            if(combatGUI.getDiceModifier() == 0) {
                                statusPanel.updateMove_Count(++move_count);
                            }
                            combatGUI.getPiece_one().setPreviousMove("capture");
                            combatGUI.getPiece_one().move(combatGUI.getPiece_two().getCurrent_Tile());
                            gameBoard.setSelected_piece(combatGUI.getPiece_one());
                            ActionLog.appendAction(String.format("%s won combat, moving %s to %s", combatGUI.getPiece_one().getName(),  combatGUI.getPiece_one().getName(), combatGUI.getPiece_two().getCurrent_Tile().getName()));
                            combatGUI.getPiece_two().getCurrent_Team().get_Chess_Pieces().remove(combatGUI.getPiece_two());
                            capturePanels[combatGUI.getPiece_two().getCurrent_Team() == teams[0] ? 0 : 1].addCapture(combatGUI.getPiece_two().getImage());


                        break;
                        case lost:
                            if(combatGUI.getDiceModifier() == 0) {
                                statusPanel.updateMove_Count(++move_count);
                            }
                            ActionLog.appendAction(String.format("%s lost combat, %s stays at %s", combatGUI.getPiece_one().getName(),  combatGUI.getPiece_one().getName(), combatGUI.getPiece_one().getCurrent_Tile().getName()));
                            combatGUI.getPiece_one().setCurrent_Tile(combatGUI.getPiece_one().getCurrent_Tile());
                            gameBoard.setSelected_piece(combatGUI.getPiece_one());
                            combatGUI.getPiece_one().setPreviousMove("capture");
                            break;
                    }
                    state = GameState.running;
                }
                break;
            case end:
                this.stop();

            default:
                break;
        }
    }


    public static boolean make_move(BasePiece selected_piece,Tile move_to_tile){
        if(move_to_tile.getCurrent_piece() == null){
            if(selected_piece.getName().equals("Pawn") && selected_piece.movement_distance == 1){
                selected_piece.setMovement_Distance(2);
                selected_piece.setAttack_Distance(2);
            }
            statusPanel.updateMove_Count(++move_count);
            selected_piece.setPreviousMove("move");
            selected_piece.move(move_to_tile);
            ActionLog.appendAction(String.format("Moving %s to %s",selected_piece.getName(),move_to_tile.getName()));

        }else if(move_to_tile.getCurrent_piece().getCurrent_Team() != current_Turn()){

            ActionLog.appendAction(String.format("Starting Combat Between %s and %s",selected_piece.getName(),move_to_tile.getCurrent_piece().getName()));
            Thread thread = new Thread(){
                public void run(){
                    BasePiece piece = move_to_tile.getCurrent_piece();
                    int distance = Math.abs(selected_piece.getY() - piece.getY()) + Math.abs(selected_piece.getX() - piece.getMiddle_x());
                    combatGUI = new CombatGUI(selected_piece,move_to_tile.getCurrent_piece(),selected_piece.getName().equals("Knight") && distance > 1 && selected_piece.previousMove.equals("move") && !isDiagonal(selected_piece.getCurrent_Tile(), move_to_tile) ? -1 : 0);
                    state = GameState.combat;

                }
            };
            thread.start();
        }

        return true;
    }
    //AI find best move here, currently just ends turn
    public static void find_Move(){
        next_Turn();
    }


    public static int[] get_Rolls(int y,int x){
        return combat_stats[y][x];
    }

    public static boolean isDiagonal(Tile a, Tile b){
        return Math.abs(a.getRelative_x() - b.getRelative_x()) == Math.abs(a.getRelative_y() - b.getRelative_y());
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
    public static void next_Turn(){

        move_count = 0;
        teams[0].resetPreviousMoveAll();
        teams[1].resetPreviousMoveAll();
        statusPanel.updateMove_Count(move_count);
        statusPanel.updatePlayer_Turn(((turn + 1) % 2) + 1);
        ActionLog.appendAction(String.format("Player %s ended turn. ", ((turn) % 2) + 1));
        gameBoard.setSelected_piece(null);
        ++turn;
        if (current_Turn() == teams[1]){
            find_Move();
        }

    }
}
