import java.util.ArrayList;
import java.util.List;

public class MaterialComplex {
	private String subject; // the name given by Bluebeam
	private String manufacture;
	private String type;
	private String dimension;
	private List<Item> myList;
	
	public MaterialComplex(String s) {
		subject = s;
		myList = new ArrayList<>();
	}
	
	public MaterialComplex(String s, List<String> r,int idCol, int subjectCol, int spaceCol, int layerCol, int commentsCol) {
		List fileRow = r; // the row read from the file. needs to be PARSED
		subject = s;
	}
	
	public MaterialComplex(String t, String m) {
		type = t;
		manufacture = m;
		myList = new ArrayList<>();
	}
	
	
	public boolean addItem(Item sup) {
		return myList.add(sup);
	}
	
	public String getTag() {
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
	
	
	private boolean parseComments(String comments) {
		
		
		return false;
	}
	
	
	
	
	
	

}
