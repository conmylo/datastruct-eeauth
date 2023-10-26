package com.company;

//Μυλωνάς Κωνσταντίνος 10027 kmylonas@ece.auth.gr    6980369600
//Μαργαρίτης Κωνσταντίνος 10061 knmargar@ece.auth.gr 6989850683
//Χαραλαμπίδης Φίλιππος   9959   fcharala@ece.auth.gr  6945147108

public class Game {
	int round;

    public Game(){
    }

    public Game(int round){
        this.round=round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

	public static void main(String[] args) {
		Game game=new Game(0); //Initializes game
		int N=15;
		final int n=100;    
        int W=(N*N*3+1)/2;
        int S=4;
		
		int[] theseusMove= {N*N-N,N-1,0,0};          //Initializes Theseus
		Player Theseus =new Player();
        Theseus.setName("Theseus");
        Theseus.setPlayerTileId(N*N-N);
        Theseus.setPlayerId(1);
        
		int[] minotaurMove= {(N*N)/2,N/2,N/2,0};        //Initializes Minautar
		Player Minotaur =new Player(); 
        Minotaur.setName("Minotaur");
        Minotaur.setPlayerTileId((N*N) / 2);

        Board board = new Board(N,S,W);
        board.createBoard();               //Creates board
        
        for(game.round=0;game.round<2*n;game.round++){
            System.out.println("Round: "+ Integer.toString(1+game.round));
            board.printTable(board.getStringRepresentation(Theseus.getPlayerTileId(),Minotaur.getPlayerTileId()));
            
            theseusMove=Theseus.move(Theseus.getPlayerTileId());
           
            System.out.print(Theseus.getPlayerTileId());
            System.out.println(" is the move of Theseus.");
            System.out.print(Theseus.getScore());
            System.out.println(" is the score of Theseus.");
            if(theseusMove[3]!=0) {                                    //Deletes the supply from the board
            	board.supplies[theseusMove[3]-1].setSupplyTileId(-1);
                board.supplies[theseusMove[3]-1].setX(-1);
                board.supplies[theseusMove[3]-1].setY(-1);
            }
            
            minotaurMove=Minotaur.move(Minotaur.getPlayerTileId());
            
            System.out.print(Minotaur.getPlayerTileId());
            System.out.println(" is the move of Minotaur.");
            
            if(Theseus.getScore()==4) {
                System.out.println("Theseus is the winner!");
                break;
            }
            else if(Theseus.getPlayerTileId()==Minotaur.getPlayerTileId()){
                System.out.println("Minotaur is the winner!");
                break;
            }
            
        }
        if(Theseus.getPlayerTileId() != Minotaur.getPlayerTileId() && Theseus.getScore()<4)
        	System.out.println("No one is the winner!");
        	
	}

}
