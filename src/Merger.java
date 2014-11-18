import java.io.IOException;


public class Merger {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FreebaseExtractor f = new FreebaseExtractor();
		BookReviewScrapper b = new BookReviewScrapper();
		MovieReviewScrapper m = new MovieReviewScrapper();
		
		String website = "http://www.goodreads.com/book/show";
		String term = "5.Harry_Potter_and_the_Prisoner_of_Azkaban";
		
		String bookReview = b.getReview(website, term);
		
		website = "http://www.omdbapi.com/";
		term = "Harry+Potter+And+the+Prisoner+of+Azkaban";
		
		String movieReview = m.getReview(website, term);
		
		//make f combine its data and these and write to file. 
	}

}
