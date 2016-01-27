package client;

import java.util.HashMap;
import java.util.Map;

public class Ai {

  int finfinx = 0;
  int finfiny = 0;
  int finfintile = 0;
  int finfinscore = 0;

  private Player player;

  public Ai(Player player) {
    this.player = player;
  }

  public String smartMove(Board board) {
    Map<String, Servertile> validMoves = new HashMap<String, Servertile>();
    Gamelogic localGamelogic = new Gamelogic(board);
    int dimXm = board.getdimXm();
    int dimXp = board.getdimXp();
    int dimYm = board.getdimYm();
    int dimYp = board.getdimYp();

    if (dimXm > -1) {
      dimXm = -2;
    }
    if (dimXp < 1) {
      dimXp = 2;
    }
    if (dimYm > -1) {
      dimYm = -2;
    }
    if (dimYp < 1) {
      dimYp = 2;
    }
    for (int x = dimXm - 1; x < dimXp + 1; x++) {
      for (int y = dimYm - 1; y < dimYp + 1; y++) {
        System.out.println("gettile: " + board.getTile(x, y));
        if (board.getTile(x, y) == null) {
          for (int h = 0; h < player.hasTiles().length - 1; h++) {
            if (player.hasTiles()[h] != null && localGamelogic.moveOkPut(board.tileToInt(player.hasTiles()[h]), x, y)) {
              validMoves.put(x + " " + y, player.hasTiles()[h]);
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
      int x = new Integer(k.split("\\s+")[0]);
      int y = new Integer((k.split("\\s+")[1]));
      if (localGamelogic.score(x, y, board.tileToInt(validMoves.get(k))) > finscore) {
        finx = x;
        finy = y;
        fintile = board.tileToInt(validMoves.get(k));
        finscore = localGamelogic.score(x, y, board.tileToInt(validMoves.get(k)));
      }
    }
    System.out.println("MOVE_PUT " + fintile + "@" + finx + "," + finy);
    return "MOVE_PUT " + fintile + "@" + finx + "," + finy;
  }

}
