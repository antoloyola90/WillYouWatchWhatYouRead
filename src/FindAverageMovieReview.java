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

import com.google.api.client.util.StringUtils;


public class FindAverageMovieReview {

	public static String getReview(String website, String term1, String term2) throws Exception{
		Document doc = Jsoup.connect(website+"?t="+term2+"&y=&plot=short&r=json").get();
		
		String s = null;
		try{
			s = (doc.toString().substring(doc.toString().indexOf("imdbRating"))).split(",")[0];
			//System.out.println(s + ", (1) " + term2);
		}catch(StringIndexOutOfBoundsException e){
			
		}
		if(s == null){
			doc = Jsoup.connect(website+"?t="+term1+"&y=&plot=short&r=json").get();
			try{
				s = (doc.toString().substring(doc.toString().indexOf("imdbRating"))).split(",")[0];
				//System.out.println(s + ", (2) " + term1);
			}catch(StringIndexOutOfBoundsException e){
				
			}
		}
		return s;
	}
	
	public static void updateFileWithReviews(String filename) throws Exception{
		filename = Merger.root + filename.split("[.]")[0]+"withMovieReviews.txt";;
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
			String line = null;
		      double Max = 0;
		      double Min = 100;
		      double Average = 0;
		      int cnt = 0;
		      while ((line = reader.readLine()) != null) {
		    	  
		        String movieRating = null;
		        for(String s : line.split(",")){
		        	if((s.split(":")[0].contains("imdbRating"))){
		        		movieRating = s.split(":")[1].substring(1, s.split(":")[1].length()-1);
		        		//System.out.println(movieRating);
		        		if(!movieRating.contains("N")){
			        		Average += Double.parseDouble(movieRating);
			        		Max = Math.max(Max, Double.parseDouble(movieRating));
			        		Min = Math.min(Min, Double.parseDouble(movieRating));
			        		cnt++;
			        	}
		        	}
			        	
			    }
		      }
		      Average = Average/cnt;
		      System.out.println(Min +", " + Max + ", " + Average);
		     for(double i = Min; i <= Max; i += (Max - Min)/10)
		    	 System.out.println(i);
		    
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String website = "http://www.omdbapi.com/";
		//String term = "Harry+Potter+And+the+Prisoner+of+Azkaban";
		String term = "La+fiesta+del+chivo+pelicula";
		//(new File(Merger.root + Merger.adaptedWorksFile)).delete();
		//(new File(Merger.root + Merger.adaptationFile)).delete();
		//getReview(website, term);
		updateFileWithReviews(Merger.adaptationFile);
		//updateFileWithReviews(Merger.adaptedWorksFile, 0, 100000);
		//Document doc = Jsoup.connect("http://www.city-data.com/zips/"+ String.format("%05d",i) +".html").get();
			
			
			//System.out.println(doc.title().split(" ")[0]);
			
			//Elements links = doc.select("div div div table tbody tr td div div table tbody tr td blockquote");
			
			//System.out.println("i = "+ i + ", " + links.first().text());
			
		
		
	}

}
