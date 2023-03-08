

public class MaterialSimple {
	private String subject; // the name given by Bluebeam
	private String plansTag;
	private String manufacture;
	private String type;
	private String dimension;
	private String model;
	private String space;
	private String comments;
	private String layer;
	private int count;

	
	public MaterialSimple(String tg) {
		plansTag = tg;
		count = 0;
	}

	
	public MaterialSimple(String s, String sp, String mod) {
		subject = s;
		space = sp;
		manufacture = mod;
		count = 0;
	}
	
	public MaterialSimple(String tag, String sub, String man, String mod, String spac) {
		subject = sub;
		space = spac;
		plansTag = tag;
		manufacture = man;
		model = mod;
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

	public String getTag() {
		return plansTag;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean addItem() {
		count +=1;
		return true;
	}
	
	
	public String getModel() {
		return model;
	}
	
	public boolean addItem(int i) {
		count += i;
		return true;
	}
	
	
	
	private boolean parseComments(String comments) {
		
		
		return false;
	}
	
	
	
	
	
	

}
