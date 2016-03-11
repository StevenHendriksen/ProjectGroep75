package Shared;

import server.Connection;

public class Player {
  String name;
  Bag bag;
  Connection connection;
  int score;

  /**
   * Constructor of Player, creating the player with a name and filling their hand.
   * @param name (name of player)
   */
  
  public Player(String name, Connection connection) {
    this.name = name;
    this.connection = connection;
    bag = new Bag(6);
  }
  
  public Player(String name) {
    this.name = name;
    bag = new Bag(6);
  }

  public String hasName() {
    return name;
  }

  public Bag getTiles() {
    return bag;
  }
  
  public void setTiles(Bag bag) {
    this.bag = bag;
  }

  public void addTile(Tile tile) {
    bag.putTile(tile.tileToInt());
  }
  
  public void removeTile(Tile tile) {
    bag.removeTile(tile);
  }

  /**
   * Removes a tile from the player's hand.
   * @param tile (tile to be removed)
   */

  public Connection getConnection() {
    return connection;
  }
  
  public int hasScore() {
    return score;
  }
  public void changeScore(int num) {
    score = score + num;
  }
}
