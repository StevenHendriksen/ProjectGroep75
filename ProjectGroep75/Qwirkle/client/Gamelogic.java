package client;

/**
 * client Gamelogic
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.ArrayList;
import java.util.List;

public class Gamelogic {
  // ------------------ Instance variables ----------------
  private Board board;
  private Servertile tile;

  // @ private invariant players.length >= 0;
  private List<Player> players = new ArrayList<Player>();

  // ------------------ Constructor ------------------------
  /**
   * The constructor that concerns all the rules of the game.
   * 
   * @param board
   *          The board, which is used in the game
   */
  public Gamelogic(Board board) {
    this.board = board;
  }

  /**
   * This method checks if your move is correct or not.
   * 
   * @param tile
   *          (the tile to put)
   * @param xcoord
   *          (the x coordinate to put it)
   * @param ycoord
   *          (the y coordinate to put it)
   * @return (boolean of whether it's allowed)
   */
  /*
   * requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
   * board.getdimXm(); requires y <= board.getdimYp() && y >= board.getdimYm();
   * 
   * ensures \result == (board.getTile(x,y) == null && (
   */
  public boolean moveOkPut(int tile, int xcoord, int ycoord) {
    assert tile <= 36 && tile >= 0;
    assert xcoord <= board.getdimXp() && xcoord >= board.getdimXm();
    assert ycoord <= board.getdimYp() && ycoord >= board.getdimYm();

    boolean result = false;
    int result2 = 0;
    int result3 = 0;

    this.tile = new Servertile(tile);

    if (board.getTile(xcoord, ycoord) == null) {
      if (board.getTile(xcoord + 1, ycoord) == null) {
        result3 = result3 + 1;
      } else if (!equal(board.getTile(xcoord + 1, ycoord), this.tile)
          && (board.getTile(xcoord + 1, ycoord).hasColor() == this.tile.hasColor()
              || board.getTile(xcoord + 1, ycoord).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
      if (board.getTile(xcoord - 1, ycoord) == null) {
        result3 = result3 + 1;
      } else if (!equal(board.getTile(xcoord - 1, ycoord), this.tile)
          && (board.getTile(xcoord - 1, ycoord).hasColor() == this.tile.hasColor()
              || board.getTile(xcoord - 1, ycoord).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
      if (board.getTile(xcoord, ycoord + 1) == null) {
        result3 = result3 + 1;
      } else if (board.getTile(xcoord, ycoord + 1) != this.tile
          && (board.getTile(xcoord, ycoord + 1).hasColor() == this.tile.hasColor()
              || board.getTile(xcoord, ycoord + 1).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
      if (board.getTile(xcoord, ycoord - 1) == null) {
        result3 = result3 + 1;
      } else if (board.getTile(xcoord, ycoord - 1) != this.tile
          && (board.getTile(xcoord, ycoord - 1).hasColor() == this.tile.hasColor()
              || board.getTile(xcoord, ycoord - 1).hasShape() == this.tile.hasShape())) {
        result2 = result2 + 1;
      }
    }

    if (result2 > 0 && result3 > 0 || (result3 == 4 && xcoord == 0 && ycoord == 0)) {
      result = true;
    }

    return result;
  }

  /**
   * The method that checks whether tile and tile2 are equal.
   * 
   * @return (boolean of whether they are equal)
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
   * The method that adds the player to a list.
   * 
   * @param player
   *          (the player that you want to add)
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
   * The method that calculates the score of a move.
   * 
   * @param xcoord
   *          (the x coordinate of the move)
   * @param ycoord
   *          (the y coordinate of the move)
   * @param tile
   *          (the tile to be placed)
   * @return (the score the move gives)
   */
  /*
   * @requires tile >= 0 && tile <= 36; requires x <= board.getdimXp() && x >=
   * board.getdimXm(); requires y <= board.getdimYp() && y >= board.getdimYm();
   * 
   * ensures \old(turn().hasScore()) != turn().hasScore();
   * 
   * @
   */
  public int score(int xcoord, int ycoord, int tile) {
    assert tile <= 36 && tile >= 0;
    assert xcoord <= board.getdimXp() && xcoord >= board.getdimXm();
    assert ycoord <= board.getdimYp() && ycoord >= board.getdimYm();
    this.tile = new Servertile(tile);
    int score = 1;
    int qwirkle1 = 1;
    int qwirkle2 = 1;
    int qwirkle3 = 1;
    int qwirkle4 = 1;

    if (board.getTile(xcoord + 1, ycoord) != null) {
      if (board.getTile(xcoord + 1, ycoord).hasColor().equals(this.tile.hasColor())) {
        score = score + 1;
        qwirkle1 = qwirkle1 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord + i + 1, ycoord) != null
              && board.getTile(xcoord + i + 1, ycoord).hasColor() 
              == board.getTile(xcoord + i, ycoord).hasColor()) {
            score = score + 1;
            qwirkle1 = qwirkle1 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(xcoord + 1, ycoord).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle2 = qwirkle2 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord + i + 1, ycoord) != null
              && board.getTile(xcoord + i + 1, ycoord).hasShape() 
              == board.getTile(xcoord + i, ycoord).hasShape()) {
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

    if (board.getTile(xcoord - 1, ycoord) != null) {
      if (board.getTile(xcoord - 1, ycoord).hasColor() == this.tile.hasColor()) {
        score = score + 1;
        qwirkle1 = qwirkle1 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord - i - 1, ycoord) != null
              && board.getTile(xcoord - i - 1, ycoord).hasColor() 
              == board.getTile(xcoord - i, ycoord).hasColor()) {
            score = score + 1;
            qwirkle1 = qwirkle1 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(xcoord - 1, ycoord).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle2 = qwirkle2 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord - i - 1, ycoord) != null
              && board.getTile(xcoord - i - 1, ycoord).hasShape() 
              == board.getTile(xcoord - i, ycoord).hasShape()) {
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

    if (board.getTile(xcoord, ycoord + 1) != null) {
      if (board.getTile(xcoord, ycoord + 1).hasColor() == this.tile.hasColor()) {
        score = score + 1;
        qwirkle3 = qwirkle3 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord, ycoord + i + 1) != null
              && board.getTile(xcoord, ycoord + i + 1).hasColor() 
              == board.getTile(xcoord, ycoord + i).hasColor()) {
            score = score + 1;
            qwirkle3 = qwirkle3 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(xcoord, ycoord + 1).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle4 = qwirkle4 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord, ycoord + i + 1) != null
              && board.getTile(xcoord, ycoord + i + 1).hasShape() 
              == board.getTile(xcoord, ycoord + i).hasShape()) {
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

    if (board.getTile(xcoord, ycoord - 1) != null) {
      if (board.getTile(xcoord, ycoord - 1).hasColor() == this.tile.hasColor()) {
        score = score + 1;
        qwirkle3 = qwirkle3 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord, ycoord - i - 1) != null
              && board.getTile(xcoord, ycoord - i - 1).hasColor() 
              == board.getTile(xcoord, ycoord - i).hasColor()) {
            score = score + 1;
            qwirkle3 = qwirkle3 + 1;
          } else {
            break;
          }
        }
      } else if (board.getTile(xcoord, ycoord - 1).hasShape() == this.tile.hasShape()) {
        score = score + 1;
        qwirkle4 = qwirkle4 + 1;
        for (int i = 1; i < 5; i++) {
          if (board.getTile(xcoord, ycoord - i - 1) != null
              && board.getTile(xcoord, ycoord - i - 1).hasShape() 
              == board.getTile(xcoord, ycoord - i).hasShape()) {
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
