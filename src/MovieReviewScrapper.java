import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import javax.xml.ws.http.HTTPException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MovieReviewScrapper {

	public static String getReview(String website, String term1, String term2) throws Exception{
		Document doc = Jsoup.connect(website+"?t="+term2+"&y=&plot=short&r=json").get();
		
		String s = null;
		String genre = null;
		try{
			s = (doc.toString().substring(doc.toString().indexOf("\"imdbRating\""))).split(",")[0];
			genre = ((doc.toString().substring(doc.toString().indexOf("\"Genre\""))));
			genre = genre.split("\"")[0] + genre.split("\"")[1] + genre.split("\"")[2] + genre.split("\"")[3].replaceAll(", ", "###");
			//System.out.println(s + ", (1) " + term2);
		}catch(StringIndexOutOfBoundsException e){
			
		}
		if(s == null){
			doc = Jsoup.connect(website+"?t="+term1+"&y=&plot=short&r=json").get();
			try{
				s = (doc.toString().substring(doc.toString().indexOf("\"imdbRating\""))).split(",")[0];
				genre = ((doc.toString().substring(doc.toString().indexOf("\"Genre\""))));
				genre = genre.split("\"")[0] + genre.split("\"")[1] + genre.split("\"")[2] + genre.split("\"")[3].replaceAll(", ", "###");
				//System.out.println(s + ", (2) " + term1);
			}catch(StringIndexOutOfBoundsException e){
				
			}
		}
		if(genre!=null)
			return s + ", " + genre;
		return s;
	}
	
	public static void updateFileWithReviews(String filename, int startLine, int endLine) throws Exception{
		String website = "http://www.omdbapi.com/";
		String outputFilename = Merger.root + filename.split("[.]")[0]+"withMovieReviews.txt";
		filename = Merger.root + filename;
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename), StandardCharsets.UTF_8);
		
		      String line = null;
		      int cnt=0;
		      while ((line = reader.readLine()) != null && cnt<startLine && cnt!=startLine) {
		    	  cnt++;
		      }
		      while ((line = reader.readLine()) != null && startLine<endLine) {
		    	  
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
		        	//if (startLine%200 == 0)
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
		
		String website = "http://www.omdbapi.com/";
		//String term = "Harry+Potter+And+the+Prisoner+of+Azkaban";
		String term = "La+fiesta+del+chivo+pelicula";
		//(new File(Merger.root + Merger.adaptedWorksFile)).delete();
		//(new File(Merger.root + Merger.adaptationFile)).delete();
		//getReview(website, term);
		//updateFileWithReviews(Merger.adaptationFile, 3490, 1000000);
		updateFileWithReviews(Merger.adaptedWorksFile, 5740, 100000);
		//Document doc = Jsoup.connect("http://www.city-data.com/zips/"+ String.format("%05d",i) +".html").get();
			
			
			//System.out.println(doc.title().split(" ")[0]);
			
			//Elements links = doc.select("div div div table tbody tr td div div table tbody tr td blockquote");
			
			//System.out.println("i = "+ i + ", " + links.first().text());
			
		
		
	}

}
