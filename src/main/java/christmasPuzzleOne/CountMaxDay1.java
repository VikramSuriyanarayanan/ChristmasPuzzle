package christmasPuzzleOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountMaxDay1 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Integer> inputArray = new ArrayList<Integer>();
	    Scanner inFile1 = new Scanner(new File("inputFile"));
	    
		while (inFile1.hasNext()) {
		    inputArray.add(Integer.valueOf(inFile1.nextLine()));
		}
		
		CountMaxDay1 c = new CountMaxDay1();
		System.out.println(c.getMax(inputArray));
		System.out.println("*******************");
		
		int[] arr = inputArray.stream().mapToInt(i -> i).toArray();
		
		System.out.println(c.getSlidingWindow(arr));

	}
	
	public int getMax(List<Integer> input) {
		int output = 0 ;
		
		for(int i=1;i<input.size();i++) {
			if (input.get(i) > input.get(i-1))
				output++;
		}
		
		return output;
	}
	
	public int getSlidingWindow(int[] input) {
		int output=0;
		int initialSum = input[0]+input[1]+input[2];
		
		for(int i=3;i<input.length;i++) {
			int currentSum = initialSum - input[i-3] + input[i];
			
			if(currentSum > initialSum) 
				output++;
			
			initialSum = currentSum;
		}
		return output;
	}

}
