package TargetDocumentSearch;

import java.io.*;
import java.util.Scanner;

public class DocumentSearchMain {
	protected int choice;
	protected String term;
	protected String[] fileName = { "french_armed_forces.txt",
			"hitchhikers.txt", "warp_drive.txt" };
	protected File[] filePath = {
			new File("sample_text/french_armed_forces.txt"),
			new File("sample_text/hitchhikers.txt"),
			new File("sample_text/warp_drive.txt") };
	protected String[] fileString = new String[3];

	public void readFiles() throws IOException {
		// Read search files into strings
		BufferedReader[] f = new BufferedReader[3];
		String str, temp;
		for (int i = 0; i < 3; i++) {
			str = "";
			f[0] = new BufferedReader(new FileReader(filePath[i]));
			while ((temp = f[0].readLine()) != null) {
				str += temp;
			}
			fileString[i] = str;
		}
	}

	public void display(int[] count) {
		// sort and print the results based on relevance using Bubble sort
		int n = count.length;
		int temp = 0;
		String tempStr;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (count[j - 1] < count[j]) {
					// swap elements
					temp = count[j - 1];
					count[j - 1] = count[j];
					count[j] = temp;
					// swap file names
					tempStr = fileName[j - 1];
					fileName[j - 1] = fileName[j];
					fileName[j] = tempStr;
				}
			}
		}
		for (int i = 0; i < 3; i++)
			System.out.println(fileName[i] + " has " + count[i] + " matches");
	}

	public void searcher(StringMethods obj) throws IOException {
		int[] count = new int[3];
		switch (choice) {
		// check the method selected by user and run the appropriate search
		case 1:
			for (int i = 0; i < 3; i++)
				count[i] = obj.stringMatch(term, fileString[i]);
			display(count);
			break;
		case 2:
			for (int i = 0; i < 3; i++)
				count[i] = obj.regExpSearch(term, fileString[i]);
			display(count);
			break;
		case 3:
			for (int i = 0; i < 3; i++)
				count[i] = obj.indexedSearch(term, fileName[i]);
			display(count);
			break;
		default:
			System.out.println("Invalid Selection");
		}

	}

	public static void main(String args[]) {

		try {
			DocumentSearchMain search = new DocumentSearchMain();
			// read all the search files
			search.readFiles();
			// get the input from the user
			System.out.print("Enter the Search Term:");
			Scanner scan = new Scanner(System.in);
			search.term = scan.nextLine();
			System.out
					.print("Select Search Method:1) String Match 2) Regular Expression 3) Indexed:");
			search.choice = scan.nextInt();
			StringMethods obj = new StringMethods();
			// Index the file if the option selected is for Indexed search
			if (search.choice == 3) {
				for (int i = 0; i < 3; i++)
					obj.indexFile(search.fileString[i], search.fileName[i]);
			}
			// start search based on the input
			search.searcher(obj);
			scan.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
