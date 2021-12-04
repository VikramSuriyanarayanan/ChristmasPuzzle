package christmasPuzzleOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.IntPredicate;

public class BingoGameDay4 {

	List<Integer> smallestCompletionIndex = new ArrayList<Integer>();
	Map<Integer, Integer> smallestRowCount = new HashMap<Integer, Integer>();
	Map<Integer, Integer> smallestColumnCount = new HashMap<Integer, Integer>();


	public static void main(String[] args) throws FileNotFoundException {

		Scanner inFile1 = new Scanner(new File("inputFile4"));
		int[] choices = {57,9,8,30,40,62,24,70,54,73,12,3,71,95,58,88,23,81,53,80,22,45,98,37,18,72,14,20,66,0,19,31,82,34,55,29,27,96,48,28,87,83,36,26,63,21,5,46,33,86,32,56,6,38,52,16,41,74,99,77,13,35,65,4,78,91,90,43,1,2,64,60,94,85,61,84,42,76,68,10,49,89,11,17,79,69,39,50,25,51,47,93,44,92,59,75,7,97,67,15};
		//int[] choices = {7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1};
		List<Integer> choice = Arrays.stream(choices).boxed().toList();

		List<int[][]> array2List = new ArrayList<int[][]>();
		int[][] val1 = new int[5][5];
		int j = 0;
		while (inFile1.hasNext()) {
			String sb = inFile1.nextLine();
			if (!sb.trim().equals("")) {
				String[] splitter = sb.split(" ");
				for(int i=0,k=0;i<splitter.length;i++) {
					if(!splitter[i].trim().isEmpty()) {
						val1[j][k] = Integer.parseInt(splitter[i]);
						k++;
					}
				}
				j++;
			} else {
				array2List.add(val1);
				val1 = new int[5][5];
				j=0;
			}
		}

		array2List.add(val1);

		BingoGameDay4 c = new BingoGameDay4();

		System.out.println(c.playGame(array2List, choice));
		System.out.println("*******************");

		//System.out.println(c.getAimedDirection(inputArray));
		System.out.println("*******************");
	}

	public int playGame(List<int[][]> inputArray, List<Integer> choices) {
		//iterate
		//eval
		// iterate smallestCompletionIndex & get corresponding index of it. 

		List<int[][]> clonedList = new ArrayList<int[][]>(inputArray.size());

		Iterator<int[][]> iterator = inputArray.iterator();
		while(iterator.hasNext()){
			int[][] original = iterator.next();
			int[][] result = new int[original.length][];
			for (int i = 0; i < original.length; i++) {
				result[i] = Arrays.copyOf(original[i], original[i].length);
			}
			clonedList.add(result);
		}

		for (int i=0;i<clonedList.size();i++) {
			for(int j=0;j<choices.size();j++) {
				if(isAllMarked(clonedList.get(i), i)) {
					smallestCompletionIndex.add(j);
					break;
				}
				markItem(clonedList.get(i), choices.get(j));
			}
		}

		int smallestArray = Integer.MAX_VALUE;
		int largestArray = Integer.MIN_VALUE;
		
		for(int k=0;k<smallestCompletionIndex.size();k++) {
			smallestArray = Math.min(smallestArray, smallestCompletionIndex.get(k));
			largestArray = Math.max(largestArray, smallestCompletionIndex.get(k));
		}

		int smallestIndex = smallestCompletionIndex.indexOf(smallestArray);
		int largestIndex = smallestCompletionIndex.indexOf(largestArray);

		int[][] targetArray = clonedList.get(largestIndex);
		int totalSum = getTotalSum(targetArray);
		System.out.println(choices.get(smallestArray-1));
		System.out.println(totalSum);

		//return (totalSum) * (choices.get(smallestArray-1));
		return (totalSum) * (choices.get(largestArray-1));

	}

	public boolean isAllMarked(int[][] input, int index) {

		IntPredicate intPredicate = (x) -> {
			if (x == -1)
				return true;
			return false;
		};

		for (int i=0;i<input[0].length;i++) {
			if(Arrays.stream(input[i]).distinct().allMatch(intPredicate)) {
				smallestRowCount.put(index, i);
				smallestColumnCount.put(index, -1);		
				return true;
			}
		}

		for(int i=0;i<input.length;i++) {
			Set<Integer> uniqueElements = new HashSet<Integer>();
			for(int j=0;j<input[0].length;j++) {
				uniqueElements.add(input[j][i]);
			}
			if(uniqueElements.size() == 1 && uniqueElements.contains(-1)) {
				smallestColumnCount.put(index, i);
				smallestRowCount.put(index, -1);	
				return true;
			}
		}

		return false;		
	}

	public void markItem(int[][] input, int value) {
		for(int i=0;i<input.length;i++) {
			for(int j=0;j<input.length;j++) {
				if (input[i][j] == value)
					input[i][j] = -1;
			}
		}
	}

	public int getTotalSum(int[][] input) {
		int sum = 0;
		for(int i=0;i<input.length;i++) {
			for(int j=0;j<input.length;j++) {
				if(input[i][j] != -1)
					sum+=input[i][j];
			}
		}
		return sum;
	}
}
