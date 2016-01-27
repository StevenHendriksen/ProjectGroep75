package client;

import java.util.HashMap;
import java.util.Map;

public class Ai {

  int finfinx = 0;
  int finfiny = 0;
  int finfintile = 0;
  int finfinscore = 0;

  private Gamelogic gamelogic;
  private Board board;
  private Player player;
  private Map<String, Servertile> validMoves = new HashMap<String, Servertile>();

  public Ai(Board board, Player player) {
    this.board = board;
    this.player = player;
  }

  public String smartMove(Board localBoard) {
    String result = "MOVE_PUT";
    Gamelogic localGamelogic = new Gamelogic(localBoard);
    int dimXm = localBoard.getdimXm();
    int dimXp = localBoard.getdimXp();
    int dimYm = localBoard.getdimYm();
    int dimYp = localBoard.getdimYp();
    Servertile[] tiles = player.hasTiles();

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
        System.out.println("gettile: " + localBoard.getTile(x, y));
        if (localBoard.getTile(x, y) == null) {
          for (int h = 0; h < player.hasTiles().length - 1; h++) {
            if (localGamelogic.moveOkPut(localBoard.tileToInt(player.hasTiles()[h]), x, y)) {
              validMoves.put(x + " " + y, player.hasTiles()[h]);
            }
          }
        }
      }
    }
    int finx = 0;
    int finy = 0;
    int fintile = board.tileToInt(player.hasTiles()[0]);
    int finscore = 0;
    System.out.println("validmoves size: " + validMoves.keySet().size());
    for (String k : validMoves.keySet()) {
      int x = new Integer(k.split("\\s+")[0]);
      int y = new Integer((k.split("\\s+")[1]));
      if (localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k))) > finscore) {
        finx = x;
        finy = y;
        fintile = localBoard.tileToInt(validMoves.get(k));
        finscore = localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k)));
      }
    }
    System.out.println("best tile: " + finx + " " + finy + " " + fintile);
    localBoard.putTile(finx, finy, fintile);
    Gamelogic localGamelogic2 = new Gamelogic(localBoard);
    for (String k : validMoves.keySet()) {
      int x = new Integer(k.split("\\s+")[0]);
      int y = new Integer((k.split("\\s+")[1]));
      if (localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k))) > finscore && x != 0 && y != 0) {
        finfinx = x;
        finfiny = y;
        finfintile = localBoard.tileToInt(validMoves.get(k));
        finfinscore = localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k)) + finscore);
      }
    }
    result = result + " " + finfintile + "@" + finfiny + "," + finfinx;
    localBoard.putTile(finfinx, finfiny, finfintile);
    validMoves = new HashMap<String, Servertile>();
    for (int x = localBoard.getdimXm() - 1; x < localBoard.getdimXp() + 1; x++) {
      for (int y = localBoard.getdimYm() - 1; y < localBoard.getdimYp() + 1; y++) {
        if (localBoard.getTile(x, y) == null) {
          for (int h = 0; h < player.hasTiles().length; h++) {
            if (localGamelogic.moveOkPut(localBoard.tileToInt(player.hasTiles()[h]), x, y)) {
              validMoves.put(x + " " + y, player.hasTiles()[h]);
            }
          }
        }
      }
    }
    for (String k : validMoves.keySet()) {
      int x = new Integer(k.split("\\s+")[0]);
      int y = new Integer((k.split("\\s+")[1]));
      if (localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k))) > finscore && x != 0 && y != 0) {
        finfinx = x;
        finfiny = y;
        finfintile = localBoard.tileToInt(validMoves.get(k));
        finfinscore = localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k)) + finscore);
      }
    }
    result = result + " " + finfintile + "@" + finfiny + "," + finfinx;
    return result;
  }

}
