package TargetDocumentSearch;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PerfTestWordList {

	public String[] searchlist;

	public void readWords(File tempfile) throws IOException {
		// generate the random words and save them in an string array
		BufferedReader f = new BufferedReader(new FileReader(tempfile));
		String temp, str;
		str = "";
		while ((temp = f.readLine()) != null) {
			str = str + temp;
		}
		searchlist = str.split("[\\s]*|[(]*|[)]*|[\\n]*|[\"]*|[\\[]*|[\\]]*");
		f.close();

	}

	public String getWord()
	// return an random word
	{
		return searchlist[(int) (Math.random() * searchlist.length)];
	}

}
