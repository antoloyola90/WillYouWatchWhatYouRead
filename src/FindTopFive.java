import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FindTopFive {
	static String [] strArr = new String[5000];
	static int cnt =0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String [] topfive = {"film-noir", "adult", "short", "horror", "documentary"};
		for(int i =0;i<topfive.length;i++){
			getTopFiveMoviesInGenre(topfive[i]);
			print("----" +topfive[i] +"---Top in Each Genre");
			System.out.println();
		}
		
		System.out.println();
		String [] topfive1 = {"drama", "horror", "short", "comedy", "romance"};
		for(int i =0;i<topfive1.length;i++){
			getTopFiveMoviesInGenreAnamoly(topfive1[i]);
			print("----" +topfive1[i] +"---Anamoly");
			System.out.println();
		}
			
	}
	
	public static void print(String s){
		System.out.println(s);
		for(int i=0;i<(cnt>5?5:cnt);i++){
			if(!strArr[i].equalsIgnoreCase("junk"))
				System.out.println(strArr[i]);
		}
	}
	
	public static int getTopFiveMoviesInGenre(String genre) throws IOException{
		strArr = new String[5000];
		cnt=0;
		
		String filename = Merger.root + "combinedNormalized.txt";
	
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
		
		String line = null, genres;
	      
	      while ((line = reader.readLine()) != null) {
	    	  for(String s : line.split(",")){
		        	if((s.split(":")[0].contains("Genre")) && (s.split(":")[1]).toLowerCase().contains(genre.toLowerCase())){
		        		strArr[cnt++] = line;
		        	}
			        	
			    }
	      }
	      String temp;
	      int movieRating[] = new int[cnt];
	      int bookRating[] = new int[cnt];
	      String doubRating[] = new String[cnt];
	      
	      for(int i =0;i<cnt;i++){
	    	  movieRating[i] = -1;
	    	  bookRating[i] = -1;
	    	  for(String s : strArr[i].split(",")){
		        	if((s.split(":")[0].contains("redoneMovieRating"))){
		        		temp = s.split(":")[1].trim();
		        		String temp1 = temp;
		        		temp = temp.split("[(]")[0].trim();
		        		movieRating[i] = Integer.parseInt(temp);
		        		doubRating[i] = temp1.split("[(]")[1].trim();
		        	}
		        	else if((s.split(":")[0].contains("redoneBookRating"))){
		        		temp = s.split(":")[1].trim();
		        		temp = temp.split("[(]")[0].trim();
		        		bookRating[i] = Integer.parseInt(temp);
		        	}	
			    }
	    	  	
	    	  
	      }
	      for(int i=0;i<cnt;i++){
	    	  //System.out.println(movieRating[i]);
	      }
	      for(int i=0;i<cnt;i++){
	    	 // System.out.println(movieRating[i] + "-" + strArr[i]);
	      }
	      
	      for(int i=0;i<cnt;i++){
	    	  for(int j=0;j<cnt;j++){
	    		  if(movieRating[i] > movieRating[j] && movieRating[i] != -1){
	    			  String t1 = strArr[i];
	    			  strArr[i] = strArr[j];
	    			  strArr[j] = t1;
	    			  
	    			  int t = movieRating[i];
	    			  movieRating[i] = movieRating[j];
	    			  movieRating[j] = t;
	    			  
	    			  t1 = doubRating[i];
	    			  doubRating[i] = doubRating[j];
	    			  doubRating[j] = t1;
	    			}
	    	  }
	    	  if(movieRating[i] == -1)
	    		  strArr[i] = "junk";
	      }
	     
	      
	     // System.out.println();
	      for(int i=0;i<cnt;i++){
	    	  //System.out.println(movieRating[i] + "-" + strArr[i]);
	      }
	      
	      for(int i=0;i<cnt;i++){
				for(String s : strArr[i].split(",")){
					
		        	if((s.split(":")[0].trim().equalsIgnoreCase("\"name\""))){
		        		strArr[i] = (i+1) + ") " + s;
		        	}
				}
				strArr[i] += "/" + doubRating[i];
			}
	      
		return cnt;
		
	}
	
	public static int getTopFiveMoviesInGenreAnamoly(String genre) throws IOException{
		strArr = new String[5000];
		cnt = 0;
		
		String filename = Merger.root + "combinedNormalized.txt";
	
		BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
		
		String line = null, genres;
	      
	      while ((line = reader.readLine()) != null) {
	    	  for(String s : line.split(",")){
		        	if((s.split(":")[0].contains("Genre")) && (s.split(":")[1]).toLowerCase().contains(genre.toLowerCase())){
		        		strArr[cnt++] = line;
		        	}
			        	
			    }
	      }
	      String temp;
	      int movieRating[] = new int[cnt];
	      int bookRating[] = new int[cnt];
	      int ratingDiff[] = new int[cnt];
	      String doubRating[] = new String[cnt];
	      
	      for(int i =0;i<cnt;i++){
	    	  movieRating[i] = -1;
	    	  bookRating[i] = -1;
	    	  for(String s : strArr[i].split(",")){
		        	if((s.split(":")[0].contains("redoneMovieRating"))){
		        		temp = s.split(":")[1].trim();
		        		String temp1 = temp;
		        		temp = temp.split("[(]")[0].trim();
		        		movieRating[i] = Integer.parseInt(temp);
		        		doubRating[i] = temp1.split("[(]")[1].trim();
		        	}
		        	else if((s.split(":")[0].contains("redoneBookRating"))){
		        		temp = s.split(":")[1].trim();
		        		temp = temp.split("[(]")[0].trim();
		        		bookRating[i] = Integer.parseInt(temp);
		        	}	
			    }
	    	  	ratingDiff[i] = Math.abs(movieRating[i] - bookRating[i]);
	      }
	      for(int i=0;i<cnt;i++){
	    	  //System.out.println(movieRating[i]);
	      }
	      for(int i=0;i<cnt;i++){
	    	  //System.out.println(ratingDiff[i] + "-" + strArr[i]);
	      }
	      
	      for(int i=0;i<cnt;i++){
	    	  for(int j=0;j<cnt;j++){
	    		  if(ratingDiff[i] > ratingDiff[j] && movieRating[j] != -1){
	    			  
	    			  String t1 = strArr[i];
	    			  strArr[i] = strArr[j];
	    			  strArr[j] = t1;
	    			  
	    			  int t = ratingDiff[i];
	    			  ratingDiff[i] = ratingDiff[j];
	    			  ratingDiff[j] = t;
	    			  
	    			  t = movieRating[i];
	    			  movieRating[i] = movieRating[j];
	    			  movieRating[j] = t;
	    			  
	    			  t = bookRating[i];
	    			  bookRating[i] = bookRating[j];
	    			  bookRating[j] = t;
	    			  
	    			  t1 = doubRating[i];
	    			  doubRating[i] = doubRating[j];
	    			  doubRating[j] = t1;
	    			}
	    	  }
	    	  if(movieRating[i] == -1)
	    		  strArr[i] = "junk";
	      }
	     
	      
	      //System.out.println();
	      
	      for(int i=0;i<cnt;i++){
				for(String s : strArr[i].split(",")){
					
		        	if((s.split(":")[0].trim().equalsIgnoreCase("\"name\""))){
		        		strArr[i] = (i+1) + ") " + s;
		        	}
				}	
			}
	      for(int i=0;i<cnt;i++){
		    	  if(movieRating[i] > bookRating[i])
		    		  strArr[i] = strArr[i] + "/" + "Movie" + "/" +doubRating[i];
		    	  else if(movieRating[i] < bookRating[i])
		    		  strArr[i] = strArr[i] + "/" + "Book" + "/" +doubRating[i];
		    	  else 
		    		  strArr[i] = strArr[i] + "/" + "Equal" + "/" +doubRating[i];
		    	//  System.out.println(ratingDiff[i] + "-" + strArr[i]);
		      }
		return cnt;
		
	}
}
