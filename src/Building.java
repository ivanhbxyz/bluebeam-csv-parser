import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



public class Building {
	private String name;
	private HashMap<String, Integer> BldgDict;
	private List<Space> rooms;
	
	public Building() {
		initBuilding();
	}
	
	public Building(String s) {
		name = s;
		initBuilding();
	}
	
	private void initBuilding() {
		BldgDict = new HashMap<>();
		rooms = new ArrayList<>();
	}
	
	public boolean addRoom() {
		return false;
	}
	
	
	
	
}
