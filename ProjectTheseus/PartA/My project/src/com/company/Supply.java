package com.company;

public class Supply {
	//Variables
    int supplyId;
    int x;
    int y;
    int supplyTileId;

    //Empty Constructor
    public Supply(){

        supplyId=x=y=supplyTileId=0;
    }

    //Constructor
    public Supply(int supplyId2 , int x2 , int y2 , int supplyTileId2){
        supplyId=supplyId2;
        x=x2;
        y=y2;
        supplyTileId=supplyTileId2;
    }
    //Constructor με ορισμα Supply
    public Supply(Supply newSupply){
        supplyId = newSupply.supplyId;
        x=newSupply.x;
        y=newSupply.y;
        supplyTileId=newSupply.supplyTileId;
    }

    //Set&Get

    public void setSupplyId(int newSupplyId){
        supplyId=newSupplyId;
    }

    public void setX(int newx){
        x=newx;
    }

    public void setY(int newy){
        y=newy;
    }

    public void setSupplyTileId(int newSupplyTileId){
        supplyTileId=newSupplyTileId;
    }

    public int getSupplyId(){
        return supplyId;
    }

    public int getX(){
        return  x;
    }

    public int getY(){
        return y;
    }

    public int getSupplyTileId(){
        return supplyTileId;
    }
}
