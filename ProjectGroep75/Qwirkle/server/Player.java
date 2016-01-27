package server;

public class Player {
  String name;
  Servertile[] tiles;
  Bag bag;
  int score;
  Connection connection;
  
  /**
   * Constructor to make sure all the objects are there.
   * @param name (name of the Player)
   * @param bag (bag that the player will take out of)
   * @param connection (the connection the player has)
   */

  public Player(String name, Bag bag, Connection connection) {
    this.name = name;
    this.connection = connection;
    tiles = new Servertile[6];
    score = 0;
    this.bag = bag;
  }

  public String hasName() {
    return name;
  }
  
  /**
   * gets 6 new tiles.
   */

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
  
  public Connection getConnection() {
    return connection;
  }
}