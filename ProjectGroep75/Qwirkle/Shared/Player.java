package Shared;

import server.Connection;

public class Player {
  String name;
  Tile[] tiles;
  Bag bag;
  Connection connection;
  int score;

  /**
   * Constructor of Player, creating the player with a name and filling their hand.
   * @param name (name of player)
   */
  
  public Player(String name, Connection connection) {
    this.name = name;
    bag = new Bag();
    tiles = new Tile[6];
    for (int i = 0; i < 6; i++) {
      tiles[i] = bag.takeTile();
    }
  }
  
  public Player(String name) {
    this.name = name;
    bag = new Bag();
    tiles = new Tile[6];
    for (int i = 0; i < 6; i++) {
      tiles[i] = bag.takeTile();
    }
  }

  public String hasName() {
    return name;
  }

  public Tile[] hasTiles() {
    return tiles;
  }
  
  public void setTiles(Tile[] tiles) {
    this.tiles = tiles;
  }

  public void addTile(Tile tile) {
    tiles[tiles.length - 1] = tile;
  }
  
  public void changeTiles(Tile tile, int num) {
    tiles[num] = tile;
  }

  /**
   * Removes a tile from the player's hand.
   * @param tile (tile to be removed)
   */
  
  public void getTiles() {
    for (int i = 0; i < 6; i++) {
      tiles[i] = bag.takeTile();
    }
  }
  
  public void removeTile(Tile tile) {
    for (int j = 0; j < tiles.length; j++) {
      if (bag.tilesInBag()[j] == tile) {
        bag.tilesInBag()[j] = null;
      }
    }

  }
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
