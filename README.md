keep updated code on 
https://github.com/antoloyola90/WillYouWatchWhatYouRead
https://github.com/antoloyola90/WillYouWatchWhatYouReadMapperReducer

WillYouWatchWhatYouRead
=======================

MovieReviewScrapper.java - called from Merger.java
BookReviewScrapper.java - called from Merger.java

this is fully implemented - Parse through imdb.com/goodreads.com for movie/book reviews.
Appends to input file along with ratings, genre.
 Return to Merger.java.
*************************************************
FreeBaseExtractor.java - called from Merger.java

this is fully implemented - Extract from tables and return result to Merger.java
*************************************************
Merger.java

***Combiner.java the required columns and creates 1 text file

******Those Above Are for Creating the Input Data for the Analytic*******

***FindAverageMovieReview.java will make the Movie Review into a 10-point scale by finding the correct scale after running through the files.

***FindAverageBookReview.java will make the Book Review into a 10-point scale by finding the correct scale after running through the files.

***FindTop5.java will take top 5 output results and get some images to make the slideshows more visual. 


https://github.com/antoloyola90/WillYouWatchWhatYouReadMapperReducer

********
Mapper/Reducer 1 - Takes combinedFile and finds which are the top 5 genres to make movies from books
********
Mapper/Reducer 2 - Takes output from Mapper/Reduce 1 and finds which are the top 5 anamolies in each genre to make movies from books
********
Mapper/Reducer 3 - Takes output from Mapper/Reduce 1 and finds which does better in general(Movies or Books)
********
Reducer 4 - Takes output from Mapper/Reduce 1 and finds which does better in the top 5 anamolies(Movies or Books)
********

The PIG commands used have been added in the WillYouWatchWhatYouReadMapperReducer code under PIG folder. 
(This uses Reducer outputs to get the top 5 in each output) 