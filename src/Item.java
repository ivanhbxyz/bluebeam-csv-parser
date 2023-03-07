
public class Item {
	
	private String id;
	private String space;
	private String subject;
	private String manufacture;
	private String model;
	private String dimensions;
	private String layer;
	private String comments; // needs parsing. what's the pattern?
	
	/*
	 * 
	 * 
	 idNo = 0;
		spaceNo = 1;
		plansTag = 2;
		subNo = 3;
		manufacture = 4;
		model = 5;
		commentNo = 6;
		dimensions = 7;
		layerNo = 8;
		length = 9;
	 */
	
	public Item(String s) {
		subject= s;
	}
	
	public Item(String i, String sub) {
		id = i;
		subject = sub;
	}
	
	public Item(String i, String sub, String spac) {
		id = i;
		subject = sub;
		space =spac;
	}
	
	public Item(String i, String sub, String spac, String la) {
		id = i;
		subject = sub;
		space = spac;
		layer = la;
	}
	
	public Item(String i, String sub, String spac, String la, String cc) {
		id = i;
		subject = sub;
		space = spac;
		layer = la;
		comments = cc;
	}
	
	public Item(String i, String spac, String sub, String man, String mod, String dim,String la, String cc) {
		id = i;
		space = spac;
		subject = sub;
		manufacture = man;
		model = mod;
		dimensions = dim;
		layer = la;
		comments = cc;
	}
	
	
	public String getID() {
		return id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getType() {
		return subject;
	}
	
	public String getSpace() {
		return space;
	}

}
