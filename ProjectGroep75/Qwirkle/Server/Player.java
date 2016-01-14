package Server;

public class Player {
	String name;
	Tile[] tiles;
	Bag bag;
	
	public Player(String name){
		this.name = name;
		tiles = new Tile[6];
		for(int i = 0; i < 6; i++){
			tiles[i] = bag.takeTile();
		}
	}
	
	public String hasName(){
		return name;
	}
	
	public Tile[] hasTiles(){
		return tiles;
	}
}
