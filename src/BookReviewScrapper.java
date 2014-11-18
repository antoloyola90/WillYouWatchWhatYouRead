import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BookReviewScrapper {

	public static String getReview(String website, String term) throws IOException{
		Document doc = Jsoup.connect(website+"/"+term+"?from_search=true").get();
		
		//String s = (doc.toString().substring(doc.toString().indexOf("imdbRating"))).split(",")[0];
		System.out.println(doc.toString());
		return doc.toString();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String website = "http://www.goodreads.com/book/show";
		String term = "5.Harry_Potter_and_the_Prisoner_of_Azkaban";
		
		getReview(website, term);
		
		//Document doc = Jsoup.connect("http://www.city-data.com/zips/"+ String.format("%05d",i) +".html").get();
			
			
			//System.out.println(doc.title().split(" ")[0]);
			
			//Elements links = doc.select("div div div table tbody tr td div div table tbody tr td blockquote");
			
			//System.out.println("i = "+ i + ", " + links.first().text());
			
		
		
	}

}
