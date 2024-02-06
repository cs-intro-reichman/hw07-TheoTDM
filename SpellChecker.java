
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		//System.out.println(levenshtein("word", "world"));
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static String Head(String str) {
		return str.substring(0,1);
	}
	public static int levenshtein(String word1, String word2) {
		if (word2.length() == 0){
			return word1.length();
		} 
		else if (word1.length() == 0){
			return word2.length();
		}
		else if (Head(word1).equals(Head(word2))){
			return levenshtein(tail(word1), tail(word2));
		}
		else {
			return 1 + Math.min(levenshtein(tail(word1), word2), 
				Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1),tail(word2))));
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i < dictionary.length; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		int min = levenshtein(word, dictionary[0]);
		String spelled_word = dictionary[0];
		for (int i=1; i < dictionary.length; i++){
			int distance = levenshtein(word, dictionary[i]);
			if (distance < min){
				min = distance;
				spelled_word = dictionary[i];
			}
		}
		if (min > threshold){
			return word;
		}
		return spelled_word;
	}

}
