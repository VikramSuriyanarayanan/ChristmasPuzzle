package christmasPuzzleOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PilotFindingDay2 {
	
	
	public static final String HORIZONDAL_ADD = "forward";
	public static final String VERTICAL_ADD = "down";
	public static final String VERTICAL_SUB = "up";


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<String> inputArray = new ArrayList<String>();
	    Scanner inFile1 = new Scanner(new File("inputFile2"));
	    
		while (inFile1.hasNext()) {
		    inputArray.add(inFile1.nextLine());
		}
		
		PilotFindingDay2 c = new PilotFindingDay2();
		System.out.println(c.getDirection(inputArray));
		System.out.println("*******************");
		
		System.out.println(c.getAimedDirection(inputArray));
		System.out.println("*******************");
	}
	
	public int getDirection(List<String> input) {
		
		int horizondal = 0;
		int vertical = 0;
		
		for(String in:input) {
			String[] inSplit = in.split(" ");
			if(inSplit[0].equalsIgnoreCase(HORIZONDAL_ADD)) {
				horizondal+=Integer.parseInt(inSplit[1]);
			} else if (inSplit[0].equalsIgnoreCase(VERTICAL_ADD)) {
				vertical +=Integer.parseInt(inSplit[1]);
			} else {
				vertical -=Integer.parseInt(inSplit[1]);
			}
			
		}
		return horizondal*vertical;
	}
	
	public int getAimedDirection(List<String> input) {
		int horizondal = 0;
		int depth = 0;
		int aim = 0;
		
		for(String in:input) {
			String[] inSplit = in.split(" ");
			if(inSplit[0].equalsIgnoreCase(HORIZONDAL_ADD)) {
				depth += aim*Integer.parseInt(inSplit[1]);
				horizondal+=Integer.parseInt(inSplit[1]);
			} else if (inSplit[0].equalsIgnoreCase(VERTICAL_ADD)) {
				aim +=Integer.parseInt(inSplit[1]);
			} else {
				aim -=Integer.parseInt(inSplit[1]);
			}
			
		}
		return horizondal*depth;
	}

}
