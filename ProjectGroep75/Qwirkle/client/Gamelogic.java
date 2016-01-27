package client;

import java.util.ArrayList;
import java.util.List;

public class Gamelogic {
  // ------------------ Instance variables ----------------
  private Board board;
  private Servertile tile;
  private int current = 0;

  // @ private invariant players.length >= 0;
  private List<Player> players = new ArrayList<Player>();

  // ------------------ Constructor ------------------------
  /**
   * The constructor that concerns all the rules of the game.
   * 
   * @param board
   *          The board, which is used in the game
   * @param bag
   *          The bag, which is used in the game
   */
  public Gamelogic(Board board) {
    this.board = board;
  }

  /**
   * The method that checks whether your move is OK or not.
   * 
   * @param result
   *          checks whether your move is OK (MOVEOK_PUT) or not (ERROR
   *          INVALID_MOVE)
   * @param result2
   *          checks whether the tiles around x and y are the same color/shape.
   * @param result3
   *          checks whether the places around x and y are empty.
   */
  /*
   * requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
   * board.getdimXm(); requires y <= board.getdimYp() && y >= board.getdimYm();
   * 
   * ensures \result == (board.getTile(x,y) == null && (
   */
  public boolean moveOkPut(int tile, int x, int y) {
    assert tile <= 36 && tile >= 0;
    assert x <= board.getdimXp() && x >= board.getdimXm();
    assert y <= board.getdimYp() && y >= board.getdimYm();

    boolean result = false;
    int result2 = 0;
    int result3 = 0;

    this.tile = new Servertile(tile);

    if (board.getTile(x, y) == null) {
      if (board.getTile(x + 1, y) == null) {
        result3 = result3 + 1;
      } else if (!equal(board.getTile(x + 1, y), this.tile)
          && (board.getTile(x + 1, y).hasColor() == this.tile.hasColor()
              || board.getTile(x + 1, y).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
      if (board.getTile(x - 1, y) == null) {
        result3 = result3 + 1;
      } else if (!equal(board.getTile(x - 1, y), this.tile)
          && (board.getTile(x - 1, y).hasColor() == this.tile.hasColor()
              || board.getTile(x - 1, y).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
      if (board.getTile(x, y + 1) == null) {
        result3 = result3 + 1;
      } else if (board.getTile(x, y + 1) != this.tile && (board.getTile(x, y + 1).hasColor() == this.tile.hasColor()
          || board.getTile(x, y + 1).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
      if (board.getTile(x, y - 1) == null) {
        result3 = result3 + 1;
      } else if (board.getTile(x, y - 1) != this.tile && (board.getTile(x, y - 1).hasColor() == this.tile.hasColor()
          || board.getTile(x, y - 1).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
    }

    if (result2 > 0 && result3 > 0 || (result3 == 4 && x == 0 && y == 0)) {
      result = true;
    }

    return result;
  }

  /**
   * The method that checks whether tile and tile2 are equal.
   * 
   * @param result
   *          Says whether tile and tile2 are equal (true) or not (false)
   */
  // @ requires tile != null;
  // @ requires tile2 != null;
  // @ ensures \result == (this.hasColor() == tile2.hasColor() &&
  // tile.hasShape() == tile2.hasShape());
  public boolean equal(Servertile tile, Servertile tile2) {
    boolean result = false;

    if (tile.hasColor() == tile2.hasColor() && tile.hasShape() == tile2.hasShape()) {
      result = true;
    }

    return result;
  }

  /**
   * The method that makes the player pass.
   */
  // @ requires (\exists int i; i <= 0 & i < player.size();
  // this.hasPlayers().get(i) == player)
  public String movePass(Player player) {
    return "PASS";
  }

  /**
   * The method that puts the player to a list.
   * 
   * @param result
   *          checks whether the name of the new player is already used or not.
   */
  // ensures \old(this.hasPlayers().size()) == this.hasPlayers().size() - 1;
  public void putPlayer(Player player) {
    boolean result = true;

    if (players.size() != 0) {
      for (int i = 0; i < players.size(); i++) {
        if (players.get(i).hasName().equals(player.hasName())) {
          result = false;
          break;
        }
      }
      if (result && players.size() > 0) {
        players.add(player);
      }
    } else {
      players.add(player);
    }

  }

  /**
   * The method that returns a list of all players.
   */
  /* @ pure */
  public List<Player> hasPlayers() {
    return players;
  }

  /**
   * The method that calculates the score.
   * 
   * @param score
   *          checks the score of the current move.
   * @param qwirkle1
   *          checks whether there is qwirkle horizontale in color.
   * @param qwirkle2
   *          checks whether there is qwirkle horizontale in shape. ` * @param
   *          qwirkle3 checks whether there is qwirkle verticale in color.
   * @param qwirkle4
   *          checks whether there is qwirkle verticale in shape.
   */
  /*
   * @requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
   * board.getdimXm(); requires y <= board.getdimYp() && y >= board.getdimYm();
   * 
   * ensures \old(turn().hasScore()) != turn().hasScore();
   * 
   * @
   */
  public int score(int x, int y, int tile) {
    assert tile <= 36 && tile >= 0;
    assert x <= board.getdimXp() && x >= board.getdimXm();
    assert y <= board.getdimYp() && y >= board.getdimYm();
    this.tile = new Servertile(tile);
    int score = 1;
    int qwirkle1 = 1;
    int qwirkle2 = 1;
    int qwirkle3 = 1;
    int qwirkle4 = 1;

    if (board.getTile(x + 1, y) != null) {
      if (board.getTile(x + 1, y).hasColor().equals(this.tile.hasColor())) {
        score = score + 1;
        qwirkle1 = qwirkle1 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x + i + 1, y) != null
              && board.getTile(x + i + 1, y).hasColor() == board.getTile(x + i, y).hasColor()) {
            score = score + 1;
            qwirkle1 = qwirkle1 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(x + 1, y).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle2 = qwirkle2 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x + i + 1, y) != null
              && board.getTile(x + i + 1, y).hasShape() == board.getTile(x + i, y).hasShape()) {
            score = score + 1;
            qwirkle2 = qwirkle2 + 1;
          } else {
            break;
          }
        }
      }
      if (qwirkle1 == 6 || qwirkle2 == 6) {
        score = score + 6;
      }
    }

    if (board.getTile(x - 1, y) != null) {
      if (board.getTile(x - 1, y).hasColor() == this.tile.hasColor()) {
        score = score + 1;
        qwirkle1 = qwirkle1 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x - i - 1, y) != null
              && board.getTile(x - i - 1, y).hasColor() == board.getTile(x - i, y).hasColor()) {
            score = score + 1;
            qwirkle1 = qwirkle1 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(x - 1, y).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle2 = qwirkle2 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x - i - 1, y) != null
              && board.getTile(x - i - 1, y).hasShape() == board.getTile(x - i, y).hasShape()) {
            score = score + 1;
            qwirkle2 = qwirkle2 + 1;
          } else {
            break;
          }
        }
      }
      if (qwirkle1 == 6 || qwirkle2 == 6) {
        score = score + 6;
      }
    }

    if (board.getTile(x, y + 1) != null) {
      if (board.getTile(x, y + 1).hasColor() == this.tile.hasColor()) {
        score = score + 1;
        qwirkle3 = qwirkle3 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x, y + i + 1) != null
              && board.getTile(x, y + i + 1).hasColor() == board.getTile(x, y + i).hasColor()) {
            score = score + 1;
            qwirkle3 = qwirkle3 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(x, y + 1).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle4 = qwirkle4 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x, y + i + 1) != null
              && board.getTile(x, y + i + 1).hasShape() == board.getTile(x, y + i).hasShape()) {
            score = score + 1;
            qwirkle4 = qwirkle4 + 1;
          } else {
            break;
          }
        }
      }
      if (qwirkle3 == 6 || qwirkle4 == 6) {
        score = score + 6;
      }
    }

    if (board.getTile(x, y - 1) != null) {
      if (board.getTile(x, y - 1).hasColor() == this.tile.hasColor()) {
        score = score + 1;
        qwirkle3 = qwirkle3 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x, y - i - 1) != null
              && board.getTile(x, y - i - 1).hasColor() == board.getTile(x, y - i).hasColor()) {
            score = score + 1;
            qwirkle3 = qwirkle3 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(x, y - 1).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle4 = qwirkle4 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(x, y - i - 1) != null
              && board.getTile(x, y - i - 1).hasShape() == board.getTile(x, y - i).hasShape()) {
            score = score + 1;
            qwirkle4 = qwirkle4 + 1;
          } else {
            break;
          }
        }
      }

      if (qwirkle3 == 6 || qwirkle4 == 6) {
        score = score + 6;
      }
    }
    return score;
  }
}