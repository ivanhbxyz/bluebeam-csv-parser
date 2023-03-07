import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Space {
	
	String name;
	public HashMap<String, Integer> materialDict;
	//private List<List<Material>> materialsLog;
	//private List<MaterialComplex> materialsLog;
	public List<MaterialSimple> materialsLog;
	
	public List<Space> subspaces;
	
	public Space(String n) {
		name = n;
		materialDict = new HashMap<>();
		materialsLog = new ArrayList<>();
		subspaces = new ArrayList<>();
	}
	

	
	public void addItem(Item i) {
		int nextLoc = materialsLog.size();
		
		if(!materialDict.containsKey(i.getSubject()) ) {
			materialDict.put(i.getSubject(), nextLoc);
			//MaterialComplex newMaterial = new MaterialComplex(i.getSubject());
			MaterialSimple newMaterial = new MaterialSimple(i.getSubject());
			
			materialsLog.add(newMaterial);
			//materialsLog.get(materialDict.get(i.getSubject())).addItem(i);
			materialsLog.get(materialDict.get(i.getSubject())).addItem();
			
		} else {
			//materialsLog.get(materialDict.get(i.getSubject())).addItem(i);
			materialsLog.get(materialDict.get(i.getSubject())).addItem();
			
		}
		
	}
	
	
	public void addItem(Item i, int c) {
		int nextLoc = materialsLog.size();
		
		if(!materialDict.containsKey(i.getSubject()) ) {
			materialDict.put(i.getSubject(), nextLoc);
			//MaterialComplex newMaterial = new MaterialComplex(i.getSubject());
			MaterialSimple newMaterial = new MaterialSimple(i.getSubject());
			
			materialsLog.add(newMaterial);
			//materialsLog.get(materialDict.get(i.getSubject())).addItem(i);
			materialsLog.get(materialDict.get(i.getSubject())).addItem(c);
			
		} else {
			//materialsLog.get(materialDict.get(i.getSubject())).addItem(i);
			materialsLog.get(materialDict.get(i.getSubject())).addItem(c);
			
		}
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public void addSubspace(Space s) {
		subspaces.add(s);
	}
	
	
}
