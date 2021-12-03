package christmasPuzzleOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BitCountsDay3 {

	public static final char ONE = '1';

	public static void main(String[] args) throws FileNotFoundException {
		List<String> inputArray = new ArrayList<String>();
		Scanner inFile1 = new Scanner(new File("inputFile3"));

		while (inFile1.hasNext()) {
			inputArray.add(inFile1.nextLine());
		}

		BitCountsDay3 c = new BitCountsDay3();
		System.out.println(c.getBitCount(inputArray));
		System.out.println("*******************");

		List<String> newList = new ArrayList<>(inputArray);

		int a = c.getGammaBitCount_WithExclusion(newList);
		int b = c.getEpsilonBitCount_WithExclusion(inputArray);
		System.out.println("*******************");

		
		System.out.println(a*b);

	}

	public int getBitCount(List<String> input) {
		StringBuffer gammaRate = new StringBuffer();
		StringBuffer epsilonRate = new StringBuffer();

		Collections.sort(input);
		System.out.println(input.toString());

		int[] oneCounter = new int[input.get(0).length()];
		for(String in:input) {
			for(int i=0;i<in.length();i++) {
				if(in.charAt(i) == ONE)
					oneCounter[i]++;
			}
		}

		for(int j:oneCounter) {
			if(j >= input.size()/2) {
				gammaRate.append("1");
				epsilonRate.append("0");
			} else {
				gammaRate.append("0");
				epsilonRate.append("1");
			}
		}

		System.out.println(gammaRate);
		System.out.println(epsilonRate);
		return Integer.parseInt(gammaRate.toString(),2)*Integer.parseInt(epsilonRate.toString(),2);
	}

	public int getGammaBitCount_WithExclusion(List<String> input) {
		StringBuffer gammaRate = new StringBuffer();

		int[] oneCounter = new int[input.get(0).length()];

		for(int i=0;i<oneCounter.length;i++) {
			
			if(input.size() == 1)
				return Integer.parseInt(input.get(0),2);
			
			for(int j=0;j<input.size();j++) {
				if(input.get(j).charAt(i) == ONE)
					oneCounter[i]++;
			}

			int sizeToConsider = (input.size()%2 != 0)? (input.size()/2)+1:input.size()/2;

			if( oneCounter[i] >= sizeToConsider) {
				gammaRate.append("1");
				//epsilonRate.append("0");
			} else {
				gammaRate.append("0");
				//epsilonRate.append("1");
			}

			int localVal = i;

			input.removeIf(in1 -> {
				int compVal = Integer.parseInt(String.valueOf(in1.charAt(localVal)), 2);	
				int gammaVal = Integer.parseInt(String.valueOf(gammaRate.charAt(localVal)), 2);
				boolean res = compVal != gammaVal;
				return res;
			});
		}

		System.out.println(gammaRate);
		//System.out.println(epsilonRate);
		return Integer.parseInt(gammaRate.toString(),2);
	}

	public int getEpsilonBitCount_WithExclusion(List<String> input) {
		StringBuffer epsilonRate = new StringBuffer();

		int[] oneCounter = new int[input.get(0).length()];

		for(int i=0;i<oneCounter.length;i++) {
			

			if(input.size() == 1)
				return Integer.parseInt(input.get(0),2);
			
			for(int j=0;j<input.size();j++) {
				if(input.get(j).charAt(i) == ONE)
					oneCounter[i]++;
			}

			int sizeToConsider = (input.size()%2 != 0)? (input.size()/2)+1:input.size()/2;

			if( oneCounter[i] >= sizeToConsider) {
				epsilonRate.append("0");
			} else {
				epsilonRate.append("1");
			}

			int localVal = i;

			input.removeIf(in1 -> {
				int compVal = Integer.parseInt(String.valueOf(in1.charAt(localVal)), 2);	
				int epsilonVal = Integer.parseInt(String.valueOf(epsilonRate.charAt(localVal)), 2);
				boolean res = compVal != epsilonVal;
				return res;
			});
		}

		System.out.println(epsilonRate);
		//System.out.println(epsilonRate);
		return Integer.parseInt(epsilonRate.toString(),2);
	}

}
