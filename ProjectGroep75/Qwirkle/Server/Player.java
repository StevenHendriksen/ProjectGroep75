package Server;

public class Player {
	String name;
	Servertile[] tiles;
	Bag bag;
	int score;
	
	public Player(String name){
		this.name = name;
		tiles = new Servertile[6];
		score = 0;
	}
	
	public String hasName(){
		return name;
	}
	
	public void getTiles(){
		for(int i = 0; i < 6; i++){
			tiles[i] = bag.takeTile();
		}
	}
	
	public Servertile[] hasTiles(){
		return tiles;
	}
	
	public int hasScore(){
		return score;
	}
	
	public void changeScore(int i){
		score = score + i;
	}
}
