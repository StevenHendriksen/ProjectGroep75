package Server;

import java.util.List;
import java.util.ArrayList;

public class Bag {
	private Servertile[] tiles;
	private Servertile tile;
	
	public Bag() {
		newBag();
	}

	public static void main(String[] args){
		Bag bag = new Bag();
		
		bag.takeTile();
	}
	
	//OK
	public void newBag() {
		tiles = new Servertile[108];
		for (int j = 0; j < 36; j++) {
			for (int i = 0; i < 3; i++) {
				tiles[i + j * 3] = new Servertile(j + 1);
			}
		}
	}

	//OK
	public Servertile takeTile() {
		Servertile result = null;
		
		List<Servertile> baglist = new ArrayList<Servertile>();
		
		for(int i = 0; i<108; i++){
			if (tiles[i] != null){
				baglist.add(tiles[i]);
			}
		}
			
		if(baglist.size() > 0){
			int random = (int) Math.floor(Math.random() * baglist.size());
			result = baglist.get(random);

			for(int j = 0; j<108; j++){
				if(baglist.get(random) == tiles[j]){
					tiles[random] = null;
				}
			}
		}
		
		return result;
	}

	//Ok
	public void putTile(int tile){
		for(int i = 0; i < 108; i++){
			if(tiles[i] == null){
				tiles[i] = this.tile;
			}
		}
	}

	//Ok
	public Bag deepCopy() {
		Bag bag = new Bag();
		for (int i = 0; i < 108; i++) {
			tiles[i] = this.tiles[i];
		}
		return bag;
	}

	//Ok
	public boolean emptyBag() {
		boolean empty = true;

		for (int i = 0; i < 108; i++) {
			if (tiles[i] != null) {
				empty = false;
			}
		}

		return empty;
	}

	//Ok
	public Servertile[] tilesInBag() {
		return tiles;
	}
}
