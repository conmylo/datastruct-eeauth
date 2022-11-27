package com.company;

public class Board {
	//Variables
    int N;
    int S;
    int W;
    Tile[] tiles = new Tile[N*N];
    Supply[] supplies = new Supply[S];

    public Board(){
        N=S=W=0;
        tiles =new Tile[N*N];               
        for(int i=0;i<N*N;i++){            //Initialize the board with empty tiles
            tiles[i]=new Tile();
        }
        supplies=new Supply[S];
        for(int i=0;i<S;i++){             //Initialize the board with empty supplies
            supplies[i]=new Supply();  
        }
    }

    public Board(int N,int S,int W){
        Tile temp=new Tile();
        this.N=N;
        this.S=S;
        this.W=W;
        tiles=new Tile[N*N];                 
        for(int i=0;i<N*N;i++){                 //Initialize the board with empty tiles
            tiles[i]=new Tile();
        }
        supplies=new Supply[S];
        for(int i=0;i<S;i++){                  //Initialize the board with empty supplies
            supplies[i]=new Supply();
        }
    }

    public Board(Board board){
        this.N=board.N;
        this.S=board.S;
        this.W=board.W;
    }

    public void setN(int n) {
        N = n;
    }

    public void setS(int s) {
        S = s;
    }

    public void setW(int w) {
        W = w;
    }

    public void setSupplies(Supply[] supplies) {
        this.supplies = supplies;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int getN() {
        return N;
    }

    public int getS() {
        return S;
    }

    public int getW() {
        return W;
    }

    public Supply[] getSupplies() {
        return supplies;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    void createTile() {               //This method initializes the tiles of the board.       
        int wallsToBeAdded;           //This variable holds how many walls are going to be added in the tile
        int directionOfWall;	      //This variable holds the direction of the wall that is going to be added
        							  // 1 == up , 3 == right , 5 == down , 7 == left                     

        for (int i = 0; i < N; i++) {      // This loop initializes the outer walls of the board.
            for (int j = 0; j < N; j++) {
                tiles[N * i + j].setTileId(N*i+j);
                tiles[N * i + j].setY(j);
                tiles[N * i + j].setX(N - i - 1);
                if (tiles[N * i + j].getX() == 0) tiles[N * i + j].setDown(true);
                if (tiles[N * i + j].getX() == N - 1) tiles[N * i + j].setUp(true);
                if (tiles[N * i + j].getY() == 0) tiles[N * i + j].setLeft(true);
                if (tiles[N * i + j].getY() == N - 1) tiles[N * i + j].setRight(true);
            }
        }
        int wallsLeft = W - 4 * N; //A variable to count how many walls are left for the program to place
        for(int i=1;i<N-1;i++){
            for(int j=1;j<N-1;j++){
                wallsToBeAdded = (int) (10 * Math.random()) % 3 - tiles[i*N+j].numberOfWalls() ;//Randomly calculates how many walls are going to be added in this tile
                while(0<wallsToBeAdded){
                    directionOfWall = 2 * ((int) (10 * Math.random()) % 4) + 1; //Randomly calculates the direction of the wall
                    if(directionOfWall==1 && !tiles[i*N+j].getUp() && tiles[i*N+j -N].numberOfWalls()<2 ){  //Checks for the restrictions
                        tiles[i*N+j].setUp(true);         //Sets a wall on the north side of this tile
                        tiles[i*N+j-N].setDown(true);     //The north neighboring tile sets a wall tangent to both tiles
                        wallsToBeAdded--;                 
                        wallsLeft-=2;
                    }
                    if(directionOfWall==3 && !tiles[i*N+j].getRight() && tiles[i*N+j +1].numberOfWalls()<2){
                        tiles[i*N+j].setRight(true);
                        tiles[i*N+j+1].setLeft(true);
                        wallsToBeAdded--;
                        wallsLeft-=2;
                    }
                    if(directionOfWall==5 && !tiles[i*N+j].getDown() && tiles[i*N+j +N].numberOfWalls()<2){
                        tiles[i*N+j].setDown(true);
                        tiles[i*N+j+N].setUp(true);
                        wallsToBeAdded--;
                        wallsLeft-=2;
                    }
                    if(directionOfWall==7 && !tiles[i*N+j].getLeft() && tiles[i*N+j -1].numberOfWalls()<2){
                        tiles[i*N+j].setLeft(true);
                        tiles[i*N+j-1].setRight(true);
                        wallsToBeAdded--;
                        wallsLeft-=2;
                    }
                }
                if(wallsLeft==0) break;       //Breaks when there are no walls left
            }
        }
    }

    void createSupply(){  //Initializes the supplies array
        int i=0;   //Counter
        int j; //This variable holds the id of the supply
        int ctr; //Counter
        while(i<S){
            j=(int)((N*N-1)*Math.random());  //Calculate j randomly
            ctr=0;
            for(int k=0;k<i;k++){        //Checks if there is another supply in the given id
                if(j == supplies[k].getSupplyTileId()) ctr++;        
            }
            if(j == N*N-N) ctr++;  //Checks if Theseus is in the same tile
            if(j== N*N / 2 ) ctr++; // Checks if Minotaur is in the same tile
            if(ctr==0){
                supplies[i].setSupplyTileId(j);            //Sets supplyTileId,x,y,SupplyId
                supplies[i].setSupplyId(i+1);
                supplies[i].setX(supplies[i].getSupplyTileId() / N);       // x==supplyTileId div N
                supplies[i].setY(supplies[i].getSupplyTileId() % N);	   // y==supplyTileId mod N
                i++;
            }
        }
    }

    void createBoard(){ //Creates the board
        createTile();
        createSupply();
    }

    String[][] getStringRepresentation(int theseusTile, int minotaurTile){
        String[][] table=new String[2*N+1][N];  
        for(int i=0;i<2*N+1;i++){              //Iniatializes the table array to empty strings
            for(int j=0;j<N;j++){
                table[i][j]=new String();
            }
        }
        String temp="";                   //temporary string
        int ctr=0;
        for(int i=0;i<2*getN();i+=2){    //Initializes the up and down walls of the table
            for(int j=0;j<getN();j++){
                if(tiles[getN()*i/2+j].getUp()) table[i][j]="+---+";
                else table[i][j]="+   +";
            }
        }
        for(int i=1;i<2*N+1;i+=2){           //Initializes the right, left walls also the position of supplies ,the Minotaur and Theseus in the table
            for(int j=0;j<N;j++){
                ctr=0;
                if(tiles[N* (i / 2)  +j].getLeft()) temp="|";
                else if(!tiles[N*(i / 2) +j].getLeft()) temp=" ";
                for(int k=0;k<getS();k++){
                    if(tiles[N*(i / 2) +j].getTileId()==supplies[k].getSupplyTileId()) ctr=k+1;
                }
                if(ctr != 0) temp+=" S"+Integer.toString(ctr);
                else if(tiles[N*( i / 2 ) +j].getTileId()==minotaurTile) temp+=" M ";
                else if(tiles[N*(i/2)+j].getTileId()==theseusTile) temp+=" T ";
                else  temp+="   ";                                                             
                if(tiles[N*(i/2)+j].getRight()) temp +="|";
                else  temp += " ";

                table[i][j]=temp;
            }
        }
        for(int i=0;i<N;i++){    //Initialiazes the last row of the table 
            table[2*N][i]="+---+";
        }
        return table;
    }

    void printTable(String table[][]){   //This method prints the given table
        for(int i=0;i<2*N+1;i++){
            for(int j=0;j<N;j++){
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }
}
