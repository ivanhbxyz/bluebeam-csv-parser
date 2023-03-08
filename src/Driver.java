
/*
 * 
 * 
 * REFERENCES:
 * 				https://mkyong.com/java/java-how-to-add-and-remove-bom-from-utf-8-file/
 * 				https://www.geeksforgeeks.org/split-string-java-examples/
 * 
 * 
 * 
 */

import org.apache.commons.codec.binary.Hex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	
	  private static boolean isContainBOM(Path path) throws IOException {

	      if (Files.notExists(path)) {
	          throw new IllegalArgumentException("Path: " + path + " does not exists!");
	      }

	      boolean result = false;

	      byte[] bom = new byte[3];
	      try (InputStream is = new FileInputStream(path.toFile())) {

	          // read 3 bytes of a file.
	          is.read(bom);

	          // BOM encoded as ef bb bf
	          String content = new String(Hex.encodeHex(bom));
	          if ("efbbbf".equalsIgnoreCase(content)) {
	              result = true;
	          }
	      }
	      return result;
	  }

	  private static void removeBom(Path path) throws IOException {

	      if (isContainBOM(path)) {

	          byte[] bytes = Files.readAllBytes(path);

	          ByteBuffer bb = ByteBuffer.wrap(bytes);

	          System.out.println("Found BOM!");

	          byte[] bom = new byte[3];
	          // get the first 3 bytes
	          bb.get(bom, 0, bom.length);

	          // remaining
	          byte[] contentAfterFirst3Bytes = new byte[bytes.length - 3];
	          bb.get(contentAfterFirst3Bytes, 0, contentAfterFirst3Bytes.length);

	          System.out.println("Remove the first 3 bytes, and overwrite the file!");

	          // override the same path
	          Files.write(path, contentAfterFirst3Bytes);

	      } else {
	          System.out.println("This file doesn't contains UTF-8 BOM!");
	      }

	  }
	
	
	public static void main(String[] args) throws Exception{
		System.out.println("Launching Bluebeam Parsing App\n");
		String csvPath = "./input/visa-csv.csv";
		
		Path path = Paths.get(csvPath);
		
		if(isContainBOM(path)){
	          System.out.println("Found BOM!");
	          
	          System.out.println("Deleting BOM");
	          removeBom(path);
	      }else{
	          System.out.println("No BOM.");
	      }
		
		List <String> csvArrayList = new ArrayList<>();
		
		try (Scanner sc = new Scanner(new File(csvPath))) {
			while(sc.hasNextLine()) {
				csvArrayList.add(sc.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			  e.printStackTrace();
		}
		// csv file has been injested into ArrayList
		
		/*
		 * What columns are we interested in?
		 * 
		 * ID
		 * SUBJECT
		 * LABEL
		 * COMMENT
		 * SPACE
		 * LAYER
		 */
		
		List <String> currLine = new ArrayList<>();
		
		try (Scanner rowScanner = new Scanner(csvArrayList.get(0))) {
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            currLine.add(rowScanner.next());
	        }
	    }

		int idNo = currLine.indexOf("ID");
		int spaceNo = currLine.indexOf("Space");
		int plansTag = currLine.indexOf("Plans Tag");
		int subNo = currLine.indexOf("Subject");
		int manufacture = currLine.indexOf("Manufacture");
		int model = currLine.indexOf("Model");
		int dimensions = currLine.indexOf("Dimensions");
		int layerNo = currLine.indexOf("Layer");
		int commentNo = currLine.indexOf("Comments");
		int length = currLine.indexOf("Length");
		
		List<List<String>> file = new ArrayList<>();
		
		for(int i = 1; i < csvArrayList.size(); i++) {
			String currentLine = csvArrayList.get(i);
			
			try (Scanner rowScanner = new Scanner(currentLine)) {
				List <String> curcur = new ArrayList<>();
		        rowScanner.useDelimiter(",");
		        
		        while (rowScanner.hasNext()) {
		        	String word = rowScanner.next();
		            curcur.add(word);
		        }
		        file.add(curcur);   
		    }
		}
		
		List<List<String>> filteredFile = new ArrayList<>();
		
		for(int i = 0; i < file.size(); i++) {
			List<String> newLine = new ArrayList<>();
			newLine.add(file.get(i).get(idNo)); // 0
			newLine.add(file.get(i).get(spaceNo)); // 1
			newLine.add(file.get(i).get(plansTag)); // 2
			newLine.add(file.get(i).get(subNo)); // 3
			newLine.add(file.get(i).get(manufacture)); // 4
			newLine.add(file.get(i).get(model)); // 5
			newLine.add(file.get(i).get(dimensions)); // 6
			newLine.add(file.get(i).get(commentNo)); // 7
			newLine.add(file.get(i).get(layerNo)); // 8
			newLine.add(file.get(i).get(length)); // 9
			
			filteredFile.add(newLine);
		}
		// We finally have a simplified file we can work with with ease
		// Re-assign places due to filtering
		idNo = 0;
		spaceNo = 1;
		plansTag = 2;
		subNo = 3;
		manufacture = 4;
		model = 5;
		dimensions = 6;
		commentNo = 7;
		layerNo = 8;
		length = 9;
		
		// Place holder arrays to hold the constructed custom data structures
		List <Space> foundSpaces = new ArrayList<>();
		//List <MaterialComplex> foundMaterial = new ArrayList<>(); // Note that for the time being we are forced to use a different array list for tracking
		//List <MaterialSimple> foundMaterial = new ArrayList<>();
		List <String> foundMaterialTags = new ArrayList<>();
		List <String> foundMaterialSubjects = new ArrayList<>();
		List <String> foundMaterialManufacs = new ArrayList<>();
		List <String> foundMaterialModels = new ArrayList<>();
		// Need to implement a comparator or something that works for the the .equals method
		//List<String> foundMat = new ArrayList<>();
		List<String> foundSpa = new ArrayList<>();
		List<Item> foundItems = new ArrayList<>();
		
		for(int i = 0; i < filteredFile.size(); i++) {
			/*
			if(!filteredFile.get(i).get(subNo).equals("")) { // If Subject is NOT empty then it must be a Material
				// Create the Item object
				Item newItem = new Item(filteredFile.get(i).get(idNo),filteredFile.get(i).get(subNo), filteredFile.get(i).get(spaceNo),"",filteredFile.get(i).get(spaceNo));
				
				// Check if the material already exists in the foundMaterial list
				if(foundMat.indexOf(filteredFile.get(i).get(subNo)) == -1) {
					
					
					// The tracking mechanism in this trial case is index based. based the the parallel location
					foundMat.add(filteredFile.get(i).get(subNo)); // add to tracking list
					foundMaterial.add(new Material(filteredFile.get(i).get(subNo)));
					
					foundMaterial.get(foundMat.indexOf(filteredFile.get(i).get(subNo))).addItem(newItem);
					
				} else if(foundMat.indexOf(filteredFile.get(i).get(subNo)) != -1) {
					foundMaterial.get(foundMat.indexOf(filteredFile.get(i).get(subNo))).addItem(newItem);
				}
			}
			*/
			if(!filteredFile.get(i).get(plansTag).equals("") ) { // If Subject is NOT empty then it must be a Material
				Item newItem = new Item (
						filteredFile.get(i).get(idNo), filteredFile.get(i).get(spaceNo), filteredFile.get(i).get(plansTag),
						filteredFile.get(i).get(subNo), filteredFile.get(i).get(manufacture),filteredFile.get(i).get(model),
						filteredFile.get(i).get(dimensions),
						filteredFile.get(i).get(layerNo),filteredFile.get(i).get(commentNo)
						);
				foundItems.add(newItem);
				
				if(foundMaterialTags.indexOf(newItem.getTag()) == -1) {
					
					MaterialSimple newMat = new MaterialSimple(newItem.getTag(), newItem.getSubject(),
							newItem.getManufacture(), newItem.getModel(), newItem.getSpace());
					
					foundMaterialTags.add(newMat.getTag());
					foundMaterialSubjects.add(newMat.getSubject());
					foundMaterialManufacs.add(newMat.getManufacture());
					foundMaterialModels.add(newMat.getModel());
				}
			}
			
			if(!filteredFile.get(i).get(spaceNo).equals("") ) { // is a Space
				
				if(foundSpa.indexOf(filteredFile.get(i).get(spaceNo)) ==-1) {
					Space newSpace = new Space(filteredFile.get(i).get(spaceNo));
					
					// The tracking mechanism in this trial case is index based. based the the parallel location
					foundSpa.add(filteredFile.get(i).get(spaceNo)); // add to tracking list
					foundSpaces.add(newSpace);
					
				}
			}
		}
		// Materials and Spaces have been detected and are ready for sorting
		
		Space s;
		for(int i = 0; i < foundItems.size(); i++) {
			// Will now go through each Item 1-by-1 and add a copy of it into each space as required			
			
			if( foundSpa.indexOf(foundItems.get(i).getSpace() ) != -1) {
				
				s = foundSpaces.get( foundSpa.indexOf( foundItems.get(i).getSpace() ) );
				s.addItem(foundItems.get(i));
				
			}
		} // Each space should have have its material tally
		
		
		for(int i =0; i < foundSpaces.size(); i++) {
			s = foundSpaces.get(i);
			
			for(int j = 0; j < s.materialsLog.size(); j++) {
				
				if( foundSpa.indexOf(s.materialsLog.get(j).getTag()) != -1) { // Then the Material is actually a Space
					Space t;
					t = foundSpaces.get(foundSpa.indexOf(s.materialsLog.get(j).getTag()));	
					for(int k = 0; k < t.materialsLog.size(); k++) {
						s.addItem(new Item(t.materialsLog.get(k).getTag()), t.materialsLog.get(k).getCount() );
					}
					//t.addItem(new Item(s.materialsLog.get(j).getSubject()));
					//s.addItem(new Item(s.materialsLog.get(j).getSubject()));		
				}
			}
		}
		
		//https://www.baeldung.com/java-multi-dimensional-arraylist
		System.out.println("\nPrinting RDA Takeoff Matrix\n");
		ArrayList<ArrayList<Integer> > mat = new ArrayList<>();
		int [][] matmat = new int [foundSpaces.size()][foundMaterialTags.size()];
		
		for(int i = 0; i < foundSpaces.size(); i++) {
			mat.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < foundSpaces.size(); i++) {
			for(int j = 0; j < foundMaterialTags.size(); j++) {
				mat.get(i).add(0);
			}
		}
		
		System.out.println(foundMaterialTags.toString());
		System.out.println(foundMaterialSubjects.toString());
		System.out.println(foundMaterialManufacs.toString());
		System.out.println(foundMaterialModels.toString());
		
		//for(int i = 0; i < foundMaterial.size();i++) {
		//	System.out.print(i + ", ");
		//}
		
		/*
		for(int i = 0; i < foundSpaces.size(); i++) {
			System.out.println(foundSpaces.get(i).getName() + " " + i +" ");
		}
		*/
		int row;
		int col;
		for(int i = 0; i < foundSpaces.size(); i++) {
			for (int j = 0; j < foundSpaces.get(i).materialsLog.size(); j++) {
				
				s = foundSpaces.get(i);
				row = foundSpa.indexOf(s.getName());
				//if(!s.materialsLog.get(j).getSubject().equals("")) {
				if( s.materialsLog.get(j).getTag() != null) {
					col = foundMaterialTags.indexOf(s.materialsLog.get(j).getTag());
					matmat[row][col] = s.materialsLog.get(j).getCount();
				}


				//matmat[row][col] = s.materialsLog.get(j).getCount();
			}
		}
		
		
		for(int i = 0; i < foundSpaces.size(); i++ ) {
			s = foundSpaces.get(i);
			System.out.print(s.name + ",");
			
			for(int j = 0; j< matmat[i].length-1; j++) {
				
				System.out.print(matmat[i][j] + ",");
			}
			
			System.out.println(matmat[i][matmat[i].length-1]);		
		}
		
	
		System.out.println("\nDone");
		
	} // END OF main()

} // END OF Driver class




