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
		MovieReviewScrapper.updateFileWithReviews(adaptationFile);
		MovieReviewScrapper.updateFileWithReviews(adaptedWorksFile);
		
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
