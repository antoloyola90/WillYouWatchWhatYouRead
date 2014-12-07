import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Combiner {
	
	static String [] array1 = new String [10000];
	static String [] array2 = new String [10000];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file1 = Merger.root + "mediaAdaptationExtractwithMovieReviews.txt";
		String file2 = Merger.root + "mediaAdaptedWorksExtractwithMovieReviews.txt";
		int cnt1, cnt2;
		BufferedReader reader1 = Files.newBufferedReader(Paths.get(file1), StandardCharsets.UTF_8);
		
		int cnt = 0;
		while ((array1[cnt++] = reader1.readLine()) != null);
		cnt1 = cnt-1;
		
		reader1 = Files.newBufferedReader(Paths.get(file2), StandardCharsets.UTF_8);
		cnt = 0;
		while ((array2[cnt++] = reader1.readLine()) != null);
		cnt2 = cnt-1;
		
		System.out.println(cnt1 + cnt2);
		
		System.out.println(combine(cnt1, cnt2));
	}
	
	public static int combine(int cnt1, int cnt2) throws IOException{
		int cnt3 = 0;
		for(int i = 0;i<cnt1;i++){
			String val1 = array1[i].substring(array1[i].indexOf("\"name\""));
			val1 = val1.split(",")[0];
			val1 = val1.split(":")[1];
			for(int j = 0;j<cnt2;j++){
				try{
					String val2 = array2[j].substring(array2[j].indexOf("\"name\""));
					val2 = val2.split(",")[0];
					val2 = val2.split(":")[1];
					if(val1.trim().equalsIgnoreCase(val2.trim())){
						System.out.println(val1 + " , " + val2);
						array2[j] = null;
					}
				}catch(NullPointerException e){
					//System.out.println(array2[j]);
				}
					
			}
		}
		String outputFilename = Merger.root + "combined.txt";
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename), StandardCharsets.UTF_8);
		
		for(int i = 0;i<cnt1;i++){
			writer.write(array1[i]);
			writer.newLine();
			cnt3++;
		}
		for(int j = 0;j<cnt2;j++){
			if(array2[j] != null){
				writer.write(array2[j]);
				writer.newLine();
				cnt3++;
			}
		}
		writer.close();
		return cnt3;
				    			
	}

}
