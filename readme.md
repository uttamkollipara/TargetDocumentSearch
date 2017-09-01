Problem- Document Search

The goal of this exercise is to create a working program to search a set of documents for the given search term or phrase (single token), and return results in order of relevance. 
Relevancy is defined as number of times the exact term or phrase appears in the document. 
Create three methods for searching the documents: 
-Simple string matching
-Text search using regular expressions
-Pre process the content and then search the index
Prompt the user to enter a search term and search method, execute the search, and return results


Usage

>Clone the project and upload it into an Eclipse IDE
>For Document Search - Right click on DocumentSearchMain.java -> RunAs - > Java Application.
Inputs- enter the Search word and when asked select the type of search 

>For 2Million performance test - Right click on PerformanceTestMain.java -> RunAs - > Java Application.
runs 2M queries using terms from the text file-searchWords.txt

Performance Test Results

1.Which approach is fastest?
Indexed Search

2.Why?
For indexed search we preprocess the data into hash map and reuse it for giving search results. But for search using string methods or RegEx, we need to perform the entire search process for every term

Improvements

In order to handle the high loads we can consider placing the files on high speed NAS and divide the search process across multiple nodes with a load balancing middleware
