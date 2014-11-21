import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.ParseException;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;


public class Merger {
	
	public static String adaptationFile = "./src/freebaseExtracts/mediaAdaptationExtract.txt";
	public static String adaptedWorksFile = "./src/freebaseExtracts/mediaAdaptedWorksExtract.txt";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		createInputFiles();
		
		
		BookReviewScrapper b = new BookReviewScrapper();
		MovieReviewScrapper m = new MovieReviewScrapper();
		
		String website = "http://www.goodreads.com/book/show";
		String term = "5.Harry_Potter_and_the_Prisoner_of_Azkaban";
		
		//String bookReview = b.getReview(website, term);
		
		website = "http://www.omdbapi.com/";
		term = "Harry+Potter+And+the+Prisoner+of+Azkaban";
		
		//String movieReview = m.getReview(website, term);
		
	}
	
	public static void createInputFiles() throws JsonSyntaxException, ParseException, IOException{
		(new File(adaptationFile)).delete();
		FreebaseExtractor.getMediaAdaptation("cursor");
		FileWriter fw = new FileWriter(adaptationFile,true);
		if(FreebaseExtractor.results != null){
           for (Object r : FreebaseExtractor.results){
        	  fw.write(((JsonObject)r).toString() + "\n");
           }
       }
		fw.close();
		
		(new File(adaptedWorksFile)).delete();
		FreebaseExtractor.getMediaAdaptedWork("cursor");
		fw = new FileWriter(adaptedWorksFile,true);
		if(FreebaseExtractor.results != null){
           for (Object r : FreebaseExtractor.results){
        	  fw.write(((JsonObject)r).toString() + "\n");
           }
       }
		fw.close();
	}
	
	public static void matchMovie(){
		
	}
}
