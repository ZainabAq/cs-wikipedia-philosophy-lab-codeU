package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {

	final static WikiFetcher wf = new WikiFetcher();
	static Boolean counter = false;
	static List<String> urlList = new ArrayList<String>();


public static Boolean isValid(String tag, String url) {
	if (tag.equals(url) || urlList.contains(tag)) {
		return false;
	}
	else {
	return true;
}
}

public static void printList() {
	for (String link : urlList) {
		System.out.println(link);
	}
}
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 *
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 *
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 *
	 * @param args
	 * @throws IOException
	 */

	 public static void recursiveLinkChecker(String url) throws IOException{
		 Connection conn = Jsoup.connect(url);
		 Document doc = conn.get();
		 Element content = doc.getElementById("mw-content-text");
		 Elements paras = content.select("p");
		 //exclude italics here
		 for (Element item : paras.children()) {
			 //how do I exclude items?
		 }
		 Elements links = paras.select("a[href]");
			 for (Element link : links){
				 String urlLink = link.attr("abs:href").toString();
				 if (isValid(urlLink, url)) {
					 counter = true;
					 if (urlLink.equals("https://en.wikipedia.org/wiki/Philosophy")) {
						 System.out.println("Success!");
						 printList();
						 return;
					 }
					 else {
						 urlList.add(urlLink);
					 recursiveLinkChecker(urlLink);
				 }
				 }
			 }
			 if (counter == false) {
		 	 		System.out.println("The program has failed.");
					printList();
		 			return;
	 		}

 }


	public static void main(String[] args) throws IOException {

        // some example code to get you started

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		recursiveLinkChecker(url);


	}
}
