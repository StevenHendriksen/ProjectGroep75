package Server;

import java.io.PrintWriter;
import java.net.Socket;

public class Player {
  String name;
  Servertile[] tiles;
  Bag bag;
  int score;
  PrintWriter out;

  public Player(String name, Bag bag, PrintWriter out) {
    this.name = name;
    this.out = out;
    tiles = new Servertile[6];
    score = 0;
    this.bag = bag;
  }

  public String hasName() {
    return name;
  }

  public void getTiles() {
    for (int i = 0; i < 6; i++) {
      tiles[i] = bag.takeTile();
    }
  }

  public Servertile[] hasTiles() {
    return tiles;
  }

  public void changeTiles(Servertile tile, int num) {
    tiles[num] = tile;
  }

  public int hasScore() {
    return score;
  }

  public void changeScore(int num) {
    score = score + num;
  }
  
  public PrintWriter getOut(){
    return out;
  }
}