package com.company;

public class Player {
	int playerId;
    String name;
    Board board;
    int score;
    int x;
    int y;
    int playerTileId;

    public Player() {
        playerId = score = x = y = 0;
        board=new Board(15,4,600); //We create enough space in memory for boards with N>15,S>4
        board.createBoard();
    }

    public Player(int playerId, String name, Board board, int score, int x, int y) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
        this.x = x;
        this.y = y;
        this.playerTileId= board.getN()*x+y;
        board =new Board(15,4,600); //We create enough space in memory for boards with N>15,S>4
        board.createBoard();
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlayerTileId() {
        return playerTileId;
    }

    public void setPlayerTileId(int playerTileId) {
        this.playerTileId = playerTileId;
    }

    int[] move(int id){
        int xx,yy;

        playerTileId=id;

        xx=id / board.getN();
        yy=id % board.getN();
        int ctr=-1;
        int[] move=new int[4];
        for(int i=0;i<4;i++){
            move[i]=0;
        }
        move[3]=0;  //0 means Theseus hasn't found a supply
        int j=2*((int)(10*Math.random())%4)+1;             //Decides the next move of the Player 1==up etc
        if (j == 1) {
            if (!board.tiles[playerTileId].getUp())          //Check for wall in the given direction
                playerTileId = playerTileId - board.getN();   //Moves the player to given tile
            else
                System.out.println("The player can't go up!"); 
        }
        if (j == 3) {
            if (!board.tiles[playerTileId].getRight())
                playerTileId = playerTileId + 1;
            else
                System.out.println("The player can't go right!");
        }
        if (j == 5) {
            if (!board.tiles[playerTileId].getDown())
                playerTileId = playerTileId + board.getN();
            else
                System.out.println("The player can't go down!");
        }
        if (j == 7) {
            if (!board.tiles[playerTileId].getLeft())
                playerTileId = playerTileId - 1;
            else
                System.out.println("The player can't go left!");
        }
        for(int i=0;i<board.getS();i++){               //Checks for a supply in the id of the player
            if(board.supplies[i].getSupplyTileId()==getPlayerTileId()) {
            	ctr=i;
            	break;
            }
        }
        if(ctr >= 0 && getPlayerId()==1) {
            System.out.println("The player has picked up a supply!");   //Deletes the supply from the board.player array
            board.supplies[ctr].setSupplyTileId(-1);
            board.supplies[ctr].setX(-1);
            board.supplies[ctr].setY(-1);
            move[3] = board.supplies[ctr].getSupplyId();      //move[3] is the supplyId
            score++;
        }
        move[0] = playerTileId;
        move[1] = playerTileId / board.getN(); //gives x
        move[2] = playerTileId % board.getN(); //gives y
        return move;
    }
}
