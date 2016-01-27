package client;

import java.util.HashMap;
import java.util.Map;

public class Ai extends Player {

  int finfinx = 0;
  int finfiny = 0;
  int finfintile = 0;
  int finfinscore = 0;

  private Gamelogic gamelogic;
  private Board board;
  private Map<String, Servertile> validMoves = new HashMap<String, Servertile>();

  public Ai(Board board) {
    super("AI");
    this.board = board;
  }

  public String smartMove(Board localBoard) {
    Gamelogic localGamelogic = new Gamelogic(localBoard);
    int dimXm = localBoard.getdimXm();
    int dimXp = localBoard.getdimXp();
    int dimYm = localBoard.getdimYm();
    int dimYp = localBoard.getdimYp();
    Servertile[] tiles = hasTiles();

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
    System.out.println("6");
    System.out.println("dimXm " + dimXm);
    System.out.println("dimXp " + dimYp);
    System.out.println("dimYm " + dimYm);
    System.out.println("dimYp " + dimYp);
    for (int x = dimXm - 1; x < dimXp + 1; x++) {
      System.out.println("6");
      for (int y = dimYm - 1; y < dimYp + 1; y++) {
        System.out.println("gettile: " + localBoard.getTile(x, y) );
        if (localBoard.getTile(x, y) == null) {
          System.out.println("5");
          for (int h = 0; h < hasTiles().length; h++) {
            if (localGamelogic.moveOkPut(localBoard.tileToInt(hasTiles()[h]), x, y)) {
              validMoves.put(x + " " + y, hasTiles()[h]);
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
      System.out.println("K: " + k);
      int x = new Integer(k.split("\\s+")[0]);
      int y = new Integer((k.split("\\s+")[1]));
      if (localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k))) > finscore) {
        System.out.println("4");
        finx = x;
        finy = y;
        fintile = localBoard.tileToInt(validMoves.get(k));
        finscore = localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k)));
      }
    }
    System.out.println("best tile: " + finx + " " + finy + " " + fintile);
    localBoard.putTile(finx, finy, fintile);
    Gamelogic localGamelogic2 = new Gamelogic(localBoard);
    System.out.println("3");
    finfinx = 0;
    finfiny = 0;
    finfintile = 0;
    finfinscore = 0;
    for (int h = 0; h < tiles.length; h++) {
      for (int x = dimXm - 1; x < dimXp + 1; x++) {
        for (int y = dimYm - 1; y < dimYp + 1; y++) {
          if (localBoard.getTile(x, y) == null) {
            for (int j = 0; j < hasTiles().length; j++) {
              if (localGamelogic2.moveOkPut(localBoard.tileToInt(hasTiles()[j]), x, y)) {
                System.out.println("1");
                validMoves.put(x +  "" + y, hasTiles()[j]);
              }
            }
          }
        }
      }
      for (String k : validMoves.keySet()) {
        int x = new Integer(k.split("\\s+")[0]);
        int y = new Integer((k.split("\\s+")[1]));
        if (localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k))) > finscore) {
          System.out.println("2");
          finfinx = x;
          finfiny = y;
          finfintile = localBoard.tileToInt(validMoves.get(k));
          finfinscore = localGamelogic.score(x, y, localBoard.tileToInt(validMoves.get(k)) + finscore);
        }
      }
    }
    return finfinx + " " + finfiny + " " + finfintile;
  }

}
