package TargetDocumentSearch;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMethods {

	HashMap<String, HashMap<String, Integer>> hmaplist = new HashMap<String, HashMap<String, Integer>>();

	public int stringMatch(String matchTerm, String fileString)
			throws IOException {
		// find the number of exact matches using string functions
		int count = 0;
		String[] currentLine;
		currentLine = fileString.split("[\\s]+");
		for (String word : currentLine) {
			if ((word.toLowerCase()).contains(matchTerm.toLowerCase())) {
				count++;
			}
		}
		return count;
	}

	public boolean specialCharacter(String term) {
		// Define special characters for RegEx search
		switch (term) {
		case ".":
			return true;
		case "\\":
			return true;
		case "[":
			return true;
		case "]":
			return true;
		case "(":
			return true;
		case ")":
			return true;
		case "\"":
			return true;
		case "?":
			return true;
		}
		return false;
	}

	public int regExpSearch(String matchTerm, String fileString)
			throws IOException {
		// find the number of exact matches using RegEx search
		int count = 0;
		if (specialCharacter(matchTerm)) {
			matchTerm = "\\" + matchTerm;
		}
		;
		Pattern p = Pattern.compile(matchTerm, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(fileString);
		while (m.find()) {
			count++;
		}
		return count;

	}

	public void indexFile(String fileString, String fileName) throws IOException {
		// convert input files into HashMap for indexed search
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		String[] currentLine;
		currentLine = fileString.split("[\\s]");
		for (String word : currentLine) {

			if (hmap.containsKey(word)) {
				hmap.put(word.toLowerCase(), hmap.get(word.toLowerCase()) + 1);
			} else {
				hmap.put(word.toLowerCase(), 1);
			}
		}
		hmaplist.put(fileName, hmap);
	}

	public int indexedSearch(String matchTerm, String fileName) {
		// find the number of exact matches for indexed search using HashMap
		int count = 0;
		HashMap<String, Integer> searchhmap = hmaplist.get(fileName);
		for (String key : searchhmap.keySet()) {
			if ((key.toLowerCase()).contains(matchTerm.toLowerCase()))
				count = count + (int) (searchhmap.get(key));
		}
		return count;
	}
	

}
