package com.company;

//Konstantinos Mylonas 10027 6980369600     kmylonas@ece.auth.gr
//Konstantinos Margaritis 10061 6989850683  knmargar@ece.auth.gr
//Filippos Xaralampidis 9959 6945147108     fcharala@ece.auth.gr

public class Game {
	int round;

    public Game() {
        round = 0;
    }

    public Game(int r) {
        round = r;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int r) {
        this.round = r;
    }
    
	public static void main(String[] args) {
		final int N = 15;
        final int S = 4;
        final int W = (N * N * 3 + 1) / 2;

        boolean thWon = false;
        boolean minWon = false;

        int[] moveTheseus ;
        int[] moveMinotaur;

        int temp = 0;

        int random;
        int dice;

        final int n =100;

        Game game = new Game();                                                              //Instantiates the game 
        Board board = new Board( N , S , W );											//Instantiates the board
        board.createBoard(); //Creates the board

        HeuristicPlayer Theseus = new HeuristicPlayer( 1 , "Theseus" , board , 0 , 0 , 0  );
        Player Minotaur = new Player( 2, "Minotaur" , board , 0 , N / 2 , N / 2);                  

        System.out.println("The game has started!!");
        board.printTable(board.getStringRepresentation(Theseus.getPlayerTileId() , Minotaur.getPlayerTileId())); 

        for(game.round = 1 ; game.round<= 2 * n ;game.round++) {
        	
        	System.out.println();                             //Prints the round,table,coordinates of  Theseus,the statistics of Theseus,coordinates of Minotaur and the current Board
            System.out.println("Round:" + game.round);
            System.out.println();

            random=(int)(4*Math.random()); 
            dice = 2 * random + 1;         //Dice for Minotaur


            moveTheseus = Theseus.smartMove(Theseus.getPlayerTileId(),Minotaur.getPlayerTileId()); //Makes the  move of Theseus
            if(moveTheseus[1] > -1){      //Checks if Theseus picked up a SUpply
                board.getSupplies()[moveTheseus[1]].setSupplyTileId(-1);         
                System.out.println("Theseus has picked up a supply !");
            }

            if(Theseus.getPlayerTileId() == Minotaur.getPlayerTileId()){  //Checks if Minotaur Won
                minWon = true;
                break;
            }
            else if(Theseus.score >= 4){         //Checks if Theseus Won
                thWon = true;
                break;
            }


            moveMinotaur=Minotaur.move(Minotaur.getPlayerTileId(), Minotaur.playerId, dice); //Makes the move of Minotaur

            if(Theseus.getPlayerTileId() == Minotaur.getPlayerTileId()){       //Checks if Minotaur Won
                minWon = true;
                break;
            }
            else if(Theseus.score >= 4){  //Checks if Theseus Won
                thWon = true;
                break;
            }
            
            System.out.println("The new coordinates of Theseus is (" +Theseus.getY() + "," + Theseus.getX() +") and his new score is " + Theseus.getScore() + " !");
            System.out.println("The new coordinates of Minotaur is (" +Minotaur.getY() + "," + Minotaur.getX() +")." );
            Theseus.statistics(Minotaur.getPlayerTileId(),false);
            board.printTable(board.getStringRepresentation(Theseus.getPlayerTileId() , Minotaur.getPlayerTileId()));
        }
        if(minWon){                                        //Checks who won and prints the correct message
            System.out.println("Minotaur won the game!");
        }
        else if(thWon){
            System.out.println("Theseus got all the supplies and won the game!");
        }
        else{
            System.out.println("The game is a tie!");
        }
        Theseus.statistics(0, true);
	}
}
