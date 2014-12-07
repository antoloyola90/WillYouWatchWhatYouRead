import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Reducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void reducer() throws IOException{
		String file1 = Merger.root + "combined.txt";
		BufferedReader reader1 = Files.newBufferedReader(Paths.get(file1), StandardCharsets.UTF_8);
		
		String line = null;
		while ((line = reader1.readLine()) != null){
			
		}
		
		String s = "{"id":"/en/kathleen_mavourneen_1919","/common/topic/description":[{"lang":"/lang/en\",\"value\":\"Kathleen Mavourneen is a 1919 film directed by Charles Brabin. Fox Studio pulled the movie from theaters due to protests by religious groups.\"}],\"name\":\"Kathleen Mavourneen\",\"type\":\"/media_common/adaptation\"}, \"imdbRating\":\"4.4\", Genre:Short###Drama###Romance";

	}

}
