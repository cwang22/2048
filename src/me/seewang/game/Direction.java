package me.seewang.game;

public enum Direction {
  UP(-1,0),DOWN(1,0),LEFT(0,-1),RIGHT(0,1);
  
  int x;
  int y;
  
  Direction(int x, int y){
    this.x = x;
    this.y = y;
  }
}
