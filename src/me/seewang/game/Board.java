package me.seewang.game;

import java.util.Scanner;

public class Board {
  int[][] board;
  Scanner s;

  public Board() {
    board = new int[4][4];
    s = new Scanner(System.in);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        board[i][j] = 0;
      }
    }
  }

  //very ugly
  public void print() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        System.out.print(board[i][j]);
        System.out.print(" ");
      }
      System.out.println();
      System.out.println();
    }
  }

  // low efficiency
  public void generate() {
    int rand = (int) (Math.random() * 15);
    int row = rand / 4;
    int col = rand % 4;
    if (board[row][col] == 0) {
      board[row][col] = 2;
      return;
    }
    generate();
  }

  public void move() {

    String in = s.next();
    Direction d = null;
    switch (in) {
    case "w":
      d = Direction.UP;
      break;
    case "s":
      d = Direction.DOWN;
      break;
    case "a":
      d = Direction.LEFT;
      break;
    case "d":
      d = Direction.RIGHT;
      break;
    }
    if (d == Direction.UP || d == Direction.LEFT) {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          join(i, j, d);
        }
      }
    }
    if (d == Direction.DOWN || d == Direction.RIGHT) {
      for (int i = 3; i >= 0; i--) {
        for (int j = 3; j >= 0; j--) {
          join(i, j, d);
        }
      }
    }
    // System.out.println(d);
  }

  private void join(int i, int j, Direction d) {
    if (board[i][j] == 0)
      return;
    while (haveNext(i, j, d)) {
      board[i + d.x][j + d.y] = board[i][j];
      board[i][j] = 0;
      i = i + d.x;
      j = j + d.y;
    }
    if (canJoin(i, j, d)) {
      board[i + d.x][j + d.y] = 2 * board[i][j];
      board[i][j] = 0;
    }
  }

  private boolean canJoin(int i, int j, Direction d) {
    i = i + d.x;
    j = j + d.y;
    if (i < 0 || i >= 4 || j < 0 || j >= 4)
      return false;
    if (board[i][j] != board[i - d.x][j - d.y])
      return false;
    return true;
  }

  private boolean haveNext(int i, int j, Direction d) {
    i = i + d.x;
    j = j + d.y;
    if (i < 0 || i >= 4 || j < 0 || j >= 4)
      return false;
    if (board[i][j] != 0)
      return false;
    return true;
  }

  private boolean haveMove() {
    for (int i = 3; i >= 0; i--) {
      for (int j = 3; j >= 0; j--) {
        for (Direction d : Direction.values()) {
          if (haveNext(i, j, d) || canJoin(i, j, d))
            return true;
        }
      }
    }
    return false;
  }

  public void close() {
    s.close();
  }

  public static void main(String args[]) {
    Board b = new Board();
    b.generate();
    b.generate();
    b.print();
    while (b.haveMove()) {
      b.move();
      System.out.println();
      b.generate();
      b.print();
    }
    
    System.out.println("game over");
    b.close();  

  }

}
