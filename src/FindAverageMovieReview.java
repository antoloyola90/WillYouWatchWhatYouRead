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
	
	static double Max = 0;
	static double Min = 100;
	static double Average = 0;
    
	public static void getTheNumbers(String filename) throws Exception{
		filename = Merger.root + filename;
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
			String line = null;
		      
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
	
	public static void makeMovieReviewUpdates(String filename) throws Exception{
		String outputFile = Merger.root + "combinedMovieUpdated.txt";
		filename = Merger.root + filename;
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile), StandardCharsets.UTF_8);
		
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
			String line = null;
		      
		      while ((line = reader.readLine()) != null) {
		    	String str = "";  
		        for(String s : line.split(",")){
		        	if((s.split(":")[0].contains("imdbRating"))){
		        		String temp = s.split(":")[1].substring(1, s.split(":")[1].length()-1);
		        		if(!temp.contains("N")){
			        		int cnt = 1;
			        		for(double i = Min; i < Max ; i+= (Max - Min)/10){
			        			cnt++;
			        			if(Double.parseDouble(temp) > i && Double.parseDouble(temp) < i + (Max-Min)/10){
			        				s = ", redoneMovieRating : " + cnt + "(" + Double.parseDouble(temp) +"), ";
			        			}
			        		}
		        		str += s;
		        		}
		        		//System.out.println(movieRating);
		        	}
		        	else {
		        		str += "," + s;
		        	}
			        	
			    }
		        writer.write(str);
		        writer.newLine();
		      }
		      writer.close();
		    
	}
	
	public static void main(String[] args) throws Exception {
		
		//getTheNumbers(Merger.adaptationFile);
		//System.out.println((Max - Min)/10);
		//Thread.sleep(50000);
		//makeMovieReviewUpdates(Merger.adaptationFile);
		getTheNumbers("combinedBothReviews.txt");
		System.out.println((Max - Min)/10);
		//Thread.sleep(50000);
		makeMovieReviewUpdates("combinedBothReviews.txt");
		
	}

}
