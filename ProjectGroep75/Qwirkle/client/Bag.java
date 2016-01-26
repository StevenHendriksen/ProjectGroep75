package client;

public class Bag {
  private Servertile[] tiles = {};

  public Bag() {
    newBag();
  }

  public static void main(String[] args) {
    Bag bag = new Bag();
  }

  public void newBag() {
    tiles = new Servertile[108];
    for (int j = 0; j < 36; j++) {
      for (int i = 0; i < 3; i++) {
        tiles[i + j * 3] = new Servertile(j + 1);
      }
    }
  }

  public Servertile takeTile() {
    int random = (int) Math.floor(Math.random() * 108);
    Servertile randomtile = tiles[random];

    while (randomtile == null) {
      random = (int) Math.floor(Math.random() * 108);
    }

    tiles[random] = null;

    return randomtile;
  }

  public void getTile(Servertile tile) {
    boolean inBag = false;
    for (int i = 0; i < 108; i++) {
      if (tiles[i] == tile) {
        if (i % 3 == 0 && tiles[i + 1] == null) {
          tiles[i + 1] = tile;
        } else if (i % 3 == 0 && tiles[i + 2] == null) {
          tiles[i + 2] = tile;
        } else if (i % 3 == 1 && tiles[i - 1] == null) {
          tiles[i - 1] = tile;
        } else if (i % 3 == 1 && tiles[i + 1] == null) {
          tiles[i + 1] = tile;
        } else if (i % 3 == 2 && tiles[i - 2] == null) {
          tiles[i - 2] = tile;
        } else if (i % 3 == 2 && tiles[i - 1] == null) {
          tiles[i - 1] = tile;
        }
        inBag = true;
      }
    }
    if (!inBag) {
      for (int i = 0; i < 108; i++) {
        if (tiles[i] == null) {
          tiles[i] = tile;
        }
      }
    }
  }


  public boolean emptyBag() {
    boolean empty = true;

    for (int i = 0; i < 108; i++) {
      if (tiles[i] != null) {
        empty = false;
      }
    }

    return empty;
  }

  public Servertile[] tilesInBag() {
    return tiles;
  }
}
