package client;

/**
 *Ai
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

import java.util.HashMap;
import java.util.Map;

import Shared.*;

public class Ai {
  // ------------------ Instance variables ----------------
  int finfinx = 0;
  int finfiny = 0;
  int finfintile = 0;
  int finfinscore = 0;

  private Player player;

  public Ai(Player player) {
    this.player = player;
  }

  /**
   * smartMove takes the board and returns a move (in String format) that is
   * decent, implemented in the way of 1 tile per turn.
   * 
   * @param board
   *          (board that you want a smartMove of)
   * @return (String of the smartMove)
   */

  public String smartMove(Board board) {
    Map<String, Tile> validMoves = new HashMap<String, Tile>();
    Gamelogic localGamelogic = new Gamelogic(board);
    int dimXm = board.getdimXm();
    if (dimXm > -1) {
      dimXm = -2;
    }
    int dimXp = board.getdimXp();
    if (dimXp < 1) {
      dimXp = 2;
    }
    int dimYm = board.getdimYm();
    if (dimYm > -1) {
      dimYm = -2;
    }
    int dimYp = board.getdimYp();
    if (dimYp < 1) {
      dimYp = 2;
    }

    for (int x = dimXm - 1; x < dimXp + 1; x++) {
      for (int y = dimYm - 1; y < dimYp + 1; y++) {
        System.out.println("gettile: " + board.getTile(x, y));
        if (board.getTile(x, y) == null) {
          for (int h = 0; h < player.getTiles().getBag().length - 1; h++) {
            if (player.getTiles().getBag()[h] != null 
                && localGamelogic.moveOkPut(board.tileToInt(player.getTiles().getBag()[h]), x, y)) {
              validMoves.put(x + " " + y, player.getTiles().getBag()[h]);
            }
          }
        }
      }
    }
    int finx = 0;
    int finy = 0;
    int fintile = 0;
    int finscore = 0;
    System.out.println("validmoves size: " + validMoves.keySet().size());
    for (String k : validMoves.keySet()) {
      int xcoord = new Integer(k.split("\\s+")[0]);
      int ycoord = new Integer((k.split("\\s+")[1]));
      if (localGamelogic.score(xcoord, ycoord, board.tileToInt(validMoves.get(k))) > finscore) {
        finx = xcoord;
        finy = ycoord;
        fintile = board.tileToInt(validMoves.get(k));
        finscore = localGamelogic.score(xcoord, ycoord, board.tileToInt(validMoves.get(k)));
      }
    }
    System.out.println("MOVE_PUT " + fintile + "@" + finx + "," + finy);
    return "MOVE_PUT " + fintile + "@" + finx + "," + finy;
  }

}
