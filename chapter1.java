import java.util.*;
public class chapter1 {
	
	public static class Tuple{
		public int x;
		public int y;
		public Tuple(int x, int y){
			this.x=x;
			this.y=y;
		}
		
	}
	
	public static class CompareTuple implements Comparator<Tuple>{
		public int compare(Tuple t1, Tuple t2){
			return t1.x-t2.x;
		}
	}
	
	//Calculates the number of paths from the top left corner to bottom right corner of a nxm matrix, 
	//Paths can only cross entries that are true in isValid
	public int numPaths(boolean[][] isValid, int n, int m){
		int[][] count = new int[n][m];
		for (int i=0;i<n;i++){
			if(isValid[i][0]){
				count[i][0]=1;
			}
		}
		for (int i=0;i<m;i++){
			if(isValid[0][i]){
				count[0][i]=1;
			}
		}
		for (int i=1;i<n;i++){
			for (int j=1;j<m;j++){
				if(isValid[i][j]){
					count[i][j] = count[i-1][j]+count[j-1][i];
				}
			}
		}
		return count[n-1][m-1];
	}
	
	public static boolean uniqueChars(String s){
		for (int i=0; i<s.length(); i++){
			char currentChar = s.charAt(i);
			if (i == s.length()-1) return true;
			for (int j=i+1; j<s.length();j++){
				if (currentChar == s.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}
	
	//Reverses a C-style string (last character is null)
	public static String stringReverse(String s){
		char[] data = s.toCharArray();
		String result = "";
		for (int i=1; i<data.length; i++){
			result = result + data[data.length-i-1];
		}
		result = result+null;
		return result;
	}
	
	public static String removeDuplicates(String input){
		String result = "";
		for (int i=0; i<input.length(); i++){
			char currentChar = input.charAt(i);
			boolean seen = false;
			for (int j=0; j<i; j++){
				seen = seen || (currentChar == input.charAt(j));
			}
			if (!seen){
				result = result + currentChar;
			}
		}
		return result;
	}
	
	public static HashMap<Character,Integer> countChars(char[] charList){
		HashMap<Character, Integer> h1 = new HashMap<Character, Integer>();
		for (int i=0; i<charList.length; i++){
			if (!h1.containsKey(charList[i])){
				h1.put(charList[i], 1);
			}
			else {
				h1.put(charList[i],h1.get(charList[i]) + 1);
			}
		}
		return h1;
	}
	public static boolean anagrams(String s1, String s2){
		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();
		HashMap<Character, Integer> h1 = countChars(chars1);
		HashMap<Character, Integer> h2 = countChars(chars2);
		return h1.equals(h2);
		
	}
	
	public static int intersectsMost(int[][] intervals){
		List<Tuple> endpoints = new ArrayList<Tuple>();
		for (int i=0; i<intervals.length; i++){
			Tuple beginPoint = new Tuple(intervals[i][0],1);
			Tuple endPoint = new Tuple(intervals[i][1],-1);
			endpoints.add(beginPoint);
			endpoints.add(endPoint);
		}
		Collections.sort(endpoints, new CompareTuple());
		int intersects = 0;
		int maxIntersects = 0;
		for (int i=0; i<endpoints.size(); i++){
			intersects = intersects + endpoints.get(i).y;
			if (intersects>maxIntersects){
				maxIntersects = intersects;
			}
		}
		return maxIntersects;
	}
	
	public static String stringReplace(String input){
		String result = "";
		for (int i=0; i<input.length(); i++){
			if (input.charAt(i) == ' '){
				result += "%20";
			}
			else result += input.charAt(i);
		}
		return result;
	}
	
	//Performs a 90 degree clockwise rotation on a nxn image matrix
	public static int[][] rotate90(int[][] image){
		int[][] result = new int[image.length][image.length];
		for (int i=0; i<image.length; i++){
			for (int j=0; j<image.length; j++){
				result[i][j] = image[j][image.length-i];
			}
		}
		return result;
	}
	
	//Replace rows/columns with a 0 with rows/columns of all 0s
	public static int[][] makeZeros(int[][] input){
		if (input==null) return null;
		int[] rowZeros = new int[input[0].length];
		int[] columnZeros = new int[input.length];
		
		for (int i=0; i<input[0].length; i++){
			for (int j=0; j<input.length; j++){
				if (input[i][j] == 0){
					rowZeros[i] = 1;
					columnZeros[j] = 1;
				}
			}
		}
		
		for (int i=0; i<input[0].length; i++){
			for (int j=0; j<input.length; j++){
				if (rowZeros[i] == 1 || columnZeros[j] == 1) {
					input[i][j] = 0;
				}
			}
		}
		return input;
	}
	
	//Returns true if s2 is a rotation of s1
	public static boolean isRotation(String s1, String s2){
		if (s1.length() != s2.length()) return false;
		String concatenated = s1 + s1;
		if (concatenated.contains(s2)) return true;
		return false;
	}
	public static void main(String[] args){
		String test1 = "abcdea";
		String test2 = "abcdef";
		String test3 = "";
		String test4 = "alopo";
		String test5 = "waterbottle";
		String test6 = "adebca";
		String test7 = "hello world I'm here";
		System.out.println(removeDuplicates(test1));
		System.out.println(removeDuplicates(test2));
		System.out.println(removeDuplicates(test3));
		System.out.println(removeDuplicates(test4));
		System.out.println(uniqueChars(test5));
		System.out.println(anagrams(test1,test6));
		System.out.println(isRotation(test5,"rbottlewate"));
	}
}
