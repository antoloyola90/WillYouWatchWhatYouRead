import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.ParseException;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;


public class Merger {
	
	public static String root = "./src/freebaseExtracts/";
	public static String adaptationFile = "mediaAdaptationExtract.txt";
	public static String adaptedWorksFile = "mediaAdaptedWorksExtract.txt";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		//createInputFiles();
		//MovieReviewScrapper.updateFileWithReviews(adaptationFile, 0, 100000);
		//MovieReviewScrapper.updateFileWithReviews(adaptedWorksFile, 0, 100000);
		
		//BookReviewScrapper.updateFileWithReviews(adaptationFile, 0, 100000);
		//BookReviewScrapper.updateFileWithReviews(adaptedWorksFile, 0, 100000);
		
		FindAverageMovieReview.getTheNumbers(adaptationFile);
		FindAverageMovieReview.getTheNumbers(adaptedWorksFile);
		
		FindAverageMovieReview.makeMovieReviewUpdates(adaptationFile);
		FindAverageMovieReview.makeMovieReviewUpdates(adaptedWorksFile);
		
		FindAverageBookReview.getTheNumbers(adaptationFile);
		FindAverageBookReview.getTheNumbers(adaptedWorksFile);
		
		FindAverageBookReview.makeBookReviewUpdates(adaptationFile);
		FindAverageBookReview.makeBookReviewUpdates(adaptedWorksFile);
		
	}
	
	public static void createInputFiles() throws JsonSyntaxException, ParseException, IOException{
		(new File(root + adaptationFile)).delete();
		FreebaseExtractor.getMediaAdaptation("cursor");
		FileWriter fw = new FileWriter(root + adaptationFile,true);
		if(FreebaseExtractor.results != null){
           for (Object r : FreebaseExtractor.results){
        	  fw.write(((JsonObject)r).toString() + "\n");
           }
       }
		fw.close();
		
		(new File(root + adaptedWorksFile)).delete();
		FreebaseExtractor.getMediaAdaptedWork("cursor");
		fw = new FileWriter(root + adaptedWorksFile,true);
		if(FreebaseExtractor.results != null){
           for (Object r : FreebaseExtractor.results){
        	  fw.write(((JsonObject)r).toString() + "\n");
           }
       }
		fw.close();
	}
	
}
