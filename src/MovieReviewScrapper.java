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


public class MovieReviewScrapper {

	public static String getReview(String website, String term1, String term2) throws Exception{
		Document doc = Jsoup.connect(website+"?t="+term1+"&y=&plot=short&r=json").get();
		
		String s = null;
		try{
			s = (doc.toString().substring(doc.toString().indexOf("imdbRating"))).split(",")[0];
			//System.out.println(s);
		}catch(StringIndexOutOfBoundsException e){
			
		}
		if(s == null){
			doc = Jsoup.connect(website+"?t="+term2+"&y=&plot=short&r=json").get();
			try{
				s = (doc.toString().substring(doc.toString().indexOf("imdbRating"))).split(",")[0];
				//System.out.println(s);
			}catch(StringIndexOutOfBoundsException e){
				
			}
		}
		return s;
	}
	
	public static void updateFileWithReviews(String filename) throws Exception{
		String website = "http://www.omdbapi.com/";
		String outputFilename = Merger.root + filename.split("[.]")[0]+"withMovieReviews.txt";
		filename = Merger.root + filename;
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename), StandardCharsets.UTF_8);
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8)){
		      String line = null;
		      while ((line = reader.readLine()) != null) {
		        //process each line in some way
		    	String review = "";
		        for(String s : line.split(",")){
		        	String movieName = "";
		        	String name = "";
		        	if((s.split(":")[0].equalsIgnoreCase("\"id\""))){
		        		movieName = s.split(":")[1];
		        		movieName = movieName.split("/")[movieName.split("/").length-1];
		        		movieName = movieName.substring(0, movieName.length()-2);
		        		movieName = movieName.toLowerCase().replaceAll("_", "+");
		        		//movieReview = getReview(website, movieName);
		        		//System.out.println(movieName + ", " + movieReview);
		        	}
		        	if((s.split(":")[0].equalsIgnoreCase("\"name\""))){
		        		name = s.split(":")[1];
		        		name = name.substring(1, name.length()-1);
		        		name = name.toLowerCase().replaceAll(" ", "+");
		        		//nameReview = getReview(website, name);
		        		//System.out.println(name+ ", " + nameReview);
		        		//writer.write(line);
		        	}
		        	//System.out.println(movieName +"  ,   " + name);
		        	review = getReview(website, movieName, name);
		        }
		        if(review != null){
		        	System.out.println(line + ", " + review);
		        	writer.write(line + ", " + review);
		        	writer.newLine();
		        }
		      }
		      writer.close();
		    }
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String website = "http://www.omdbapi.com/";
		//String term = "Harry+Potter+And+the+Prisoner+of+Azkaban";
		String term = "La+fiesta+del+chivo+pelicula";
		
		//getReview(website, term);
		//updateFileWithReviews(Merger.adaptationFile);
		updateFileWithReviews(Merger.adaptedWorksFile);
		//Document doc = Jsoup.connect("http://www.city-data.com/zips/"+ String.format("%05d",i) +".html").get();
			
			
			//System.out.println(doc.title().split(" ")[0]);
			
			//Elements links = doc.select("div div div table tbody tr td div div table tbody tr td blockquote");
			
			//System.out.println("i = "+ i + ", " + links.first().text());
			
		
		
	}

}
