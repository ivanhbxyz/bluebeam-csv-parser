

public class MaterialSimple {
	private String subject; // the name given by Bluebeam
	private String manufacture;
	private String type;
	private String dimension;
	private String model;
	private String space;
	private String comments;
	private int count;

	
	public MaterialSimple(String s) {
		subject = s;
		count = 0;
	}
	
	public MaterialSimple(String s, String man, String dim) {
		subject = s;
		manufacture = man;
		dimension = dim;
		count = 0;
	}
	
	public MaterialSimple(String s, String sp, String mod, String com) {
		subject = s;
		space = sp;
		manufacture = mod;
		comments = com;
		count = 0;
		
	}
	
	
	public String getSubject() {
		return subject;
	}
	
	public String getManufacture() {
		return manufacture;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDimension() {
		return dimension;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean addItem() {
		count +=1;
		return true;
	}
	
	public boolean addItem(int i) {
		count += i;
		return true;
	}
	
	
	
	private boolean parseComments(String comments) {
		
		
		return false;
	}
	
	
	
	
	
	

}
