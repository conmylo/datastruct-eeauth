package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class HeuristicPlayer extends Player{    
    ArrayList<Integer> path;
    int[] numberOfTimesPassedATile;
    int[] timesPlayedMove;

    public HeuristicPlayer(){               //Empty constructor
        path =new ArrayList();
        this.board = new Board();
        numberOfTimesPassedATile = new int[board.getN()*board.getN()];
        numberOfTimesPassedATile[0] = 1;
        timesPlayedMove = new int[4];
    }

    public HeuristicPlayer(int newPlayerId, String newName, Board newBoard,int newScore,int newX,int newY){   //Constructor with parameters
        path = new ArrayList();
        this.playerId=newPlayerId;
        this.name=newName;
        this.board=newBoard;
        this.score=newScore;
        this.x=newX;
        this.y=newY;
        numberOfTimesPassedATile = new int[board.getN()*board.getN()];
        numberOfTimesPassedATile[0] = 1;
        timesPlayedMove = new int[4];

    }

    public HeuristicPlayer(HeuristicPlayer player){         //Constructor with HeuristicPlayer as parameter
        path = new ArrayList();
        this.playerId=player.playerId;
        this.name=player.name;
        this.board=player.board;
        this.score=player.score;
        this.x=player.x;
        this.y=player.y;
        numberOfTimesPassedATile = player.numberOfTimesPassedATile;
        timesPlayedMove=player.timesPlayedMove;
    }

    void fillArrayList(int currentPos,int dice,int minotaurPos) { //Fills the ArrayList with four values
        path.add(dice);										       //The first value is the direction of the move of Theseus								

        int counter = 0;
        for (int i = 0; i < board.getS(); i++) {
            if (currentPos == board.getSupplies()[i].getSupplyTileId()) {
                path.add(1);                                         //The second value checks if Theseus will pick up a Supply ( 1 == yes , 0 == no )
                counter = 1;
                break;
            }
        }
        if (counter == 0) {
            path.add(0);
        }

        counter = 0;                                                  //The third value checks if there is a supply near Theseus 
        if (dice == 1) {
            if (!board.getTiles()[currentPos].getUp()) {             //Checks in the first upper tile (if there is no wall)
                for (int i = 0; i < board.getS(); i++) {
                    if (currentPos + board.getN() == board.getSupplies()[i].getSupplyTileId()) {
                        path.add(1);
                        counter = 1;
                        break;
                    }
                }
                if (!board.getTiles()[currentPos + board.getN()].getUp() && counter == 0) {    //Checks in the second upper tile (if there is no wall)
                    for (int i = 0; i < board.getS(); i++) {
                        if (currentPos + 2 * board.getN() == board.getSupplies()[i].getSupplyTileId()) {
                            path.add(2);
                            counter = 1;
                            break;
                        }
                    }
                    if (!board.getTiles()[currentPos + 2 * board.getN()].getUp() && counter == 0) {           //Checks in the third upper tile (if there is no wall)
                        for (int i = 0; i < board.getS(); i++) {
                            if (currentPos + 3 * board.getN() == board.getSupplies()[i].getSupplyTileId()) {
                                path.add(3);
                                counter = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (dice == 3) {                                             //It works the same way as above to check for supplies in right tiles etc
            if (!board.getTiles()[currentPos].getRight()) {
                for (int i = 0; i < board.getS(); i++) {
                    if (currentPos + 1 == board.getSupplies()[i].getSupplyTileId()) {
                        path.add(1);
                        counter = 1;
                        break;
                    }
                }
                if (!board.getTiles()[currentPos + 1].getRight() && counter == 0) {
                    for (int i = 0; i < board.getS(); i++) {
                        if (currentPos + 2 == board.getSupplies()[i].getSupplyTileId()) {
                            path.add(2);
                            counter = 1;
                            break;
                        }
                    }
                    if (!board.getTiles()[currentPos + 2].getRight() && counter == 0) {
                        for (int i = 0; i < board.getS(); i++) {
                            if (currentPos + 3 == board.getSupplies()[i].getSupplyTileId()) {
                                path.add(3);
                                counter = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (dice == 5) {
            if (!board.getTiles()[currentPos].getDown()) {
                for (int i = 0; i < board.getS(); i++) {
                    if (currentPos - board.getN() == board.getSupplies()[i].getSupplyTileId()) {
                        path.add(1);
                        counter = 1;
                        break;
                    }
                }
                if (!board.getTiles()[currentPos - board.getN()].getDown() && counter == 0) {
                    for (int i = 0; i < board.getS(); i++) {
                        if (currentPos - 2 * board.getN() == board.getSupplies()[i].getSupplyTileId()) {
                            path.add(2);
                            counter = 1;
                            break;
                        }
                    }
                    if (!board.getTiles()[currentPos - 2 * board.getN()].getDown() && counter == 0) {
                        for (int i = 0; i < board.getS(); i++) {
                            if (currentPos - 3 * board.getN() == board.getSupplies()[i].getSupplyTileId()) {
                                path.add(3);
                                counter = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (dice == 7) {
            if (!board.getTiles()[currentPos].getLeft()) {
                for (int i = 0; i < board.getS(); i++) {
                    if (currentPos - 1 == board.getSupplies()[i].getSupplyTileId()) {
                        path.add(1);
                        counter = 1;
                        break;
                    }
                }
                if (!board.getTiles()[currentPos - 1 ].getLeft() && counter == 0) {
                    for (int i = 0; i < board.getS(); i++) {
                        if (currentPos - 2  == board.getSupplies()[i].getSupplyTileId()) {
                            path.add(2);
                            counter = 1;
                            break;
                        }
                    }
                    if (!board.getTiles()[currentPos - 2 ].getLeft() && counter == 0) {
                        for (int i = 0; i < board.getS(); i++) {
                            if (currentPos -3 == board.getSupplies()[i].getSupplyTileId()) {
                                path.add(3);
                                counter = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if( counter == 0 ){
            path.add(4); //Sets the value to 4 which means that the supply is out of Theseus field of view
        }
        counter = 0 ;
        if(dice == 1){             //We work the same way to check for Minotaur's position according to Theseus
            if( !board.getTiles()[currentPos].getUp()){
                if( minotaurPos == currentPos + board.getN() ){
                    path.add(1);
                    counter = 1;
                }
                else if( !board.getTiles()[currentPos + board. getN()].getUp() ){
                    if(minotaurPos == currentPos + 2* board.getN()){
                        path.add(2);
                        counter = 1;
                    }
                    else if( !board.getTiles()[currentPos + 2 * board.getN()].getUp() ){
                        if( minotaurPos == currentPos + 3 * board.getN() ) {
                            path.add(3);
                            counter = 1;
                        }
                    }
                }
            }
        }
        if(dice == 3){
            if( !board.getTiles()[currentPos].getRight()){
                if( minotaurPos == currentPos + 1 ){
                    path.add(1);
                    counter = 1;
                }
                else if( !board.getTiles()[currentPos + 1].getRight() ){
                    if(minotaurPos == currentPos + 2){
                        path.add(2);
                        counter = 1;
                    }
                    else if( !board.getTiles()[currentPos + 2].getRight() ){
                        if( minotaurPos == currentPos + 3 ) {
                            path.add(3);
                            counter = 1;
                        }
                    }
                }
            }
        }
        if(dice == 5){
            if( !board.getTiles()[currentPos].getDown()){
                if( minotaurPos == currentPos - board.getN() ){
                    path.add(1);
                    counter = 1;
                }
                else if( !board.getTiles()[currentPos - board. getN()].getDown() ){
                    if(minotaurPos == currentPos - 2* board.getN()){
                        path.add(2);
                        counter = 1;
                    }
                    else if( !board.getTiles()[currentPos - 2 * board.getN()].getDown() ){
                        if( minotaurPos == currentPos - 3 * board.getN() ) {
                            path.add(3);
                            counter = 1;
                        }
                    }
                }
            }
        }
        if(dice == 7){
            if( !board.getTiles()[currentPos].getLeft()){
                if( minotaurPos == currentPos - 1 ){
                    path.add(1);
                    counter = 1;
                }
                else if( !board.getTiles()[currentPos - 1].getLeft() ){
                    if(minotaurPos == currentPos - 2){
                        path.add(2);
                        counter = 1;
                    }
                    else if( !board.getTiles()[currentPos - 2].getLeft() ){
                        if( minotaurPos == currentPos - 3 ) {
                            path.add(3);
                            counter = 1;
                        }
                    }
                }
            }
        }
        if(counter == 0){
            path.add(4); //path(3)
        }
    }


    double evaluate(int currentPos , int dice , int minotaurPos){        //Evaluates the score of a given move
        double score;

        fillArrayList(currentPos,dice,minotaurPos);

        if(dice == 1){
            if(!board.getTiles()[currentPos].getUp()){
                score = + 3 *(4- path.get(2) ) - 6 *(4-path.get(3)) - 0.01 * numberOfTimesPassedATile[currentPos + board.getN()] ;
            }
            else{
                score= -20;
            }
        }
        else if(dice == 3){
            if(!board.getTiles()[currentPos].getRight()){
                score = + 3 *(4- path.get(2) ) - 6 *(4-path.get(3)) - 0.01 * numberOfTimesPassedATile[currentPos + 1] ;
            }
            else{
                score= -20;
            }
        }
        else if(dice == 5){
            if(!board.getTiles()[currentPos].getDown()){
                score = + 3 *(4- path.get(2) ) - 6 *(4-path.get(3)) - 0.01 * numberOfTimesPassedATile[currentPos - board.getN()] ;
            }
            else{
                score= -20;
            }
        }
        else{
            if(!board.getTiles()[currentPos].getLeft()){
                score = + 3 *(4- path.get(2) ) - 6 *(4-path.get(3)) - 0.01 * numberOfTimesPassedATile[currentPos - 1] ;
            }
            else{
                score= -20;
            }
        }
        path.clear(); //Clears the path
        return  score;
    }

    int getNextMove(int currentPos ,int minotaurPos){ //Checks for the best move in each directions
        double[] scoreOfMove = new double[4];
        double max = - 19 ;
        int[] temp = {-1,-1,-1,-1};
        int index;
        int move = 0;
        for(int i=0;i<4;i++){
            scoreOfMove[i] = evaluate(currentPos,2 * i +1,minotaurPos);  //Stores the score of each move
        }
        while(true){
            for(int i=0;i<4;i++){
                if(scoreOfMove[i] > max){             //We get the move with the highest score
                    max = scoreOfMove[i];
                    temp[i] = i;
                }
                else if(scoreOfMove[i] == max){       //Or if we have two or more moves with the max score
                    temp[i] = i;
                }
            }
            while (true) {
                int random=0;
                random = (int)(4*Math.random());   //Chooses randomly between the moves with the highest score
                if(temp[random] > -1){
                    index = random;
                    break;
                }
            }
            if( index == 0 && board.getTiles()[currentPos].getUp()){     //Checks for walls
                scoreOfMove[index] = - 2;
                max = -2;
            }
            else if(index == 1 && board.getTiles()[currentPos].getRight() ){
                scoreOfMove[index] = - 2;
                max = - 2;
            }
            else if( index == 2 && board.getTiles()[currentPos].getDown() ){
                scoreOfMove[index] = - 2;
                max = - 2;
            }
            else if( index == 3 && board.getTiles()[currentPos].getLeft() ){
                scoreOfMove[index] = - 2;
                max = - 2;
            }
            else{
                break;
            }
        }
        move = 2 * index + 1;
        return move;            //Returns the move
    }

    public int[] smartMove(int currentPos,int minotaurPos){ //Makes the move
        int[] move =new int[2];
        move[0] = getNextMove(currentPos,minotaurPos);
        move[1] = -1;
        if(move[0] == 1){       //If Theseus chooses to play up 
            numberOfTimesPassedATile[currentPos + board.getN()]++;   //Add 1 to the number of times Theseus has passed from his upper tile
            timesPlayedMove[0]++; //Add 1 to timesPlayedUp
            x++;  //Move him up
            for(int i=0 ; i<board.getS() ; i++){ //Checks for supplies in his new position 
                if( currentPos == board.supplies[i].getSupplyTileId() && playerId == 1){     
                     score++;
                     move[1] = i;

                     board.supplies[i].setSupplyTileId(-1);
                     break;
                }
            }
        }
        else if(move[0] == 3){  // Like above 
            numberOfTimesPassedATile[currentPos + 1]++;
            timesPlayedMove[1]++;
            y++;
            for(int i=0 ; i<board.getS() ; i++){
                if( currentPos == board.supplies[i].getSupplyTileId() && playerId == 1){
                    score++;
                    move[1] = i;
                    board.supplies[i].setSupplyTileId(-1);
                    break;
                }
            }
        }
        else if(move[0] == 5){ // Like above 
            numberOfTimesPassedATile[currentPos - board.getN()]++;
            timesPlayedMove[2]++;
            x--;
            for(int i=0 ; i<board.getS() ; i++){
                if( currentPos == board.supplies[i].getSupplyTileId() && playerId == 1){
                    score++;
                    move[1] = i;
                    board.supplies[i].setSupplyTileId(-1);
                    break;
                }
            }
        }
        else if(move[0] == 7){ // Like above 
            numberOfTimesPassedATile[currentPos - 1]++;
            timesPlayedMove[3]++;
            y--;
            for(int i=0 ; i<board.getS() ; i++){
                if( currentPos == board.supplies[i].getSupplyTileId() && playerId == 1){
                    score++;
                    move[1] = i;
                    board.supplies[i].setSupplyTileId(-1);
                    break;
                }
            }
        }
        return move;
    }

    public void statistics(int minotaurPos,boolean gameHasFinished){            //Prints the statistics
        if(!gameHasFinished) {
        	if(score == 1 ){
                System.out.println("The player has picked up " + score + " supply!" );        
            }
            else if(score != 1){
                System.out.println("The player has picked up " + score + " supplies!" );
            }

            int min=4;
            for(int i=0;i<4;i++){
                fillArrayList(getPlayerTileId(),2*i+1,minotaurPos);
                if(min > path.get(2)){
                    min=path.get(2);
                }
                path.clear();
            }
            if(min < 4 ) {
                System.out.println("The minimum distance from a supply is " + min + " tiles!");
            }
            else if (min == 4){
                System.out.println("There is no supply near Theseus!");
            }

            min=4;
            for(int i=0;i<4;i++){
                fillArrayList(getPlayerTileId(),2*i+1,minotaurPos);
                if(min > path.get(3)){
                    min=path.get(3);
                }
                path.clear();
            }
            if(min < 4 ) {
                System.out.println("The minimum distance from Minotaur is " + min + " tiles!");
            }
            else if (min == 4){    
                System.out.println("Theseus can't see the Minotaur!");
            }
        }
        else if(gameHasFinished){  //If the game has finished
            System.out.println("Theseus has played " + timesPlayedMove[0] +" times up!");
            System.out.println("Theseus has played " + timesPlayedMove[1] +" times right!");
            System.out.println("Theseus has played " + timesPlayedMove[2] +" times down!");
            System.out.println("Theseus has played " + timesPlayedMove[3] +" times left!");
        }
    }
}
