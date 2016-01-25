package Server;

public class Player {
  String name;
  Servertile[] tiles;
  Bag bag;
  int score;

  public Player(String name, Bag bag) {
    this.name = name;
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
}
