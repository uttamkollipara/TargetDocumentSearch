package TargetDocumentSearch;

import java.io.File;
import java.io.IOException;

public class PerformanceTestMain extends DocumentSearchMain {
	public static void main(String args[]) {
		File searchwords = new File("sample_text/searchWords.txt");

		try {
			PerformanceTestMain perf = new PerformanceTestMain();
			perf.readFiles();
			PerfTestWordList obj1 = new PerfTestWordList();
			obj1.readWords(searchwords);
			StringMethods obj = new StringMethods();
			long[] starttime = new long[3];
			long[] endtime = new long[3];
			// Performance test on 2M search starts
			for (int j = 0; j < 3; j++) {
				starttime[j] = System.currentTimeMillis();
				perf.choice = j + 1;
				// Index the file for Indexed search
				if (j == 2) {
					for (int i = 0; i < 3; i++)
						obj.indexFile(perf.fileString[i], perf.fileName[i]);
				}
				for (int i = 0; i < 2000000; i++) {
					perf.term = obj1.getWord();
					perf.searcher(obj);

				}
				endtime[j] = System.currentTimeMillis();
				// Performance test ends
			}
			// Display the results
			for (int i = 0; i < 3; i++)
				System.out.println("Time taken to run Search Type{ " + (i + 1)
						+ " }: " + (endtime[i] - starttime[i]) + " ms");

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
