package Server;

public class Player {
	String name;
	Servertile[] tiles;
	Bag bag;
	
	public Player(String name){
		this.name = name;
		tiles = new Servertile[6];
		for(int i = 0; i < 6; i++){
			tiles[i] = bag.takeTile();
		}
	}
	
	public String hasName(){
		return name;
	}
	
	public Servertile[] hasTiles(){
		return tiles;
	}
}
