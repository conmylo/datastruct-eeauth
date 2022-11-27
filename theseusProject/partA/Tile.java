package com.company;

public class Tile {
	//Variables
    int tileId;
    int x;
    int y;
    public boolean up;
    boolean down;
    boolean left;
    boolean right;

    //Constructors
    public Tile(){
        tileId=x=y=0;
        up=down=left=right=false;
    }

    public Tile(int tileId ,int  x , int y , boolean up , boolean down , boolean left , boolean right ){
        this.tileId=tileId;
        this.x=x;
        this.y=y;
        this.up=up;
        this.down=down;
        this.left=left;
        this.right=right;
    }

    public Tile(Tile tile2){
        tileId=tile2.tileId;
        x=tile2.x;
        y=tile2.y;
        up=tile2.up;
        down=tile2.down;
        left=tile2.left;
        right=tile2.right;
    }

    //Set&Get
    public void setTileId(int tileId){
        this.tileId=tileId;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setUp(boolean up){
        this.up=up;
    }

    public void setDown(boolean down){
        this.down=down;
    }

    public void setLeft(boolean left){
        this.left = left;
    }

    public void setRight(boolean right){
        this.right=right;
    }

    public  int getTileId(){
        return tileId;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getUp(){
        return up;
    }

    public boolean getDown(){
        return down;
    }

    public boolean getLeft(){
        return left;
    }

    public boolean getRight(){
        return right;
    }

    public int numberOfWalls(){            //This method returns how many walls are in the given tile.
        int counter=0;
        if(this.getUp()) counter++;
        if(this.getDown()) counter++;
        if(this.getLeft()) counter++;
        if(this.getRight()) counter++;
        return  counter;
    }
}
