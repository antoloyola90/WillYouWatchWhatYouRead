import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.ws.http.HTTPException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookReviewScrapper {
	
	public static String getReview(String website, String term1, String term2) throws Exception{
		
		Document doc = null;
		String s = null;
		String s1 = null;
		try {
			doc = Jsoup.connect(website+term2).get();
			s = doc.getElementsByTag("average_rating").first().html();
		}
		catch(HttpStatusException e){
			
		}
		catch(StringIndexOutOfBoundsException e){
			
		}
		
		if(s == null || s == "0.00"){
			try {
				doc = Jsoup.connect(website+term1).get();
				s1 = doc.getElementsByTag("average_rating").first().html();
				
			}
			catch(HttpStatusException e){
				
			}
			catch(StringIndexOutOfBoundsException e){
				
			}
		}
		if(s==null && s1==null)
			return s;
		else if(s!=null && s1!=null)
			return s1;
		else if(s==null)
			return s1;
		return s;
	}
	public static void updateFileWithReviews(String filename, int startLine, int endLine) throws Exception{
		String website = "https://www.goodreads.com/book/title.xml?key=zE7cvGiOo6mgMMwf7ReyA&title=";
		String outputFilename = Merger.root + filename.split("[.]")[0]+"withMovieReviewsWithBookReviews.txt";
		filename = Merger.root + filename.split("[.]")[0] +"withMovieReviews.txt";
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename), StandardCharsets.UTF_8);
		
		      String line = null;
		      int cnt=0;
		      //while ((line = reader.readLine()) != null && cnt<startLine && cnt!=startLine) {
		    	//  cnt++;
		      //}
		      while ((line = reader.readLine()) != null /*&& startLine<endLine*/ ) {
		    	  
		        //process each line in some way
		    	String review = null;
		    	String movieName = null;
	        	String name = null;
		        for(String s : line.split(",")){
		        	
		        	if((s.split(":")[0].equalsIgnoreCase("\"id\"")) || (s.split(":")[0].equalsIgnoreCase("\"name\""))){
			        	if((s.split(":")[0].equalsIgnoreCase("\"id\""))){
			        		movieName = s.split(":")[1];
			        		movieName = movieName.split("/")[movieName.split("/").length-1];
			        		movieName = movieName.substring(0, movieName.length()-2);
			        		movieName = movieName.toLowerCase().replaceAll("_", "+");
			        		//movieReview = getReview(website, movieName);
			        		//System.out.println(movieName + ", " + movieReview);
			        	}
			        	else if((s.split(":")[0].equalsIgnoreCase("\"name\""))){
			        		name = s.split(":")[1];
			        		name = name.substring(1, name.length()-1);
			        		name = name.toLowerCase().replaceAll(" ", "+");
			        		//nameReview = getReview(website, name);
			        		//System.out.println(name+ ", " + nameReview);
			        		//writer.write(line);
			        	}
			        	//System.out.println(movieName +"  ,   " + name);
			        	
		        	}	
		        }
		        
		        review = getReview(website, movieName, name);
	        	
		        if(review != null){
		        	review = "bookRating : " + review;
		        	if (startLine%200 == 0)
		        		System.out.println(startLine + " = " + line + ", " + review);
		        	writer.write(line + ", " + review);
		        	writer.newLine();
		        }
		        startLine++;
		      }
		      writer.close();
		    
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String website = "https://www.goodreads.com/book/title.xml?key=zE7cvGiOo6mgMMwf7ReyA&title=";
		String term = "Harry+Potter+and+the+Prisoner+of+Azkaban";
		
		//getReview(website, term);
		updateFileWithReviews(Merger.adaptedWorksFile, 0, 100000);
		//updateFileWithReviews(Merger.adaptationFile, 0, 100000);
		//Document doc = Jsoup.connect("http://www.city-data.com/zips/"+ String.format("%05d",i) +".html").get();
			
			
			//System.out.println(doc.title().split(" ")[0]);
			
			//Elements links = doc.select("div div div table tbody tr td div div table tbody tr td blockquote");
			
			//System.out.println("i = "+ i + ", " + links.first().text());
			
		
		
	}

}
