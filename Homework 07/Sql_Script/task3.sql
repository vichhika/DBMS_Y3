
SELECT Movie.Title
FROM ​Movie INNER JOIN (
    Author INNER JOIN ​MovieDetail ON Author.AuthorID = MovieDetail.AuthorID
    ) ON Movie.MovieID = ​MovieDetail.MovieID

WHERE Author.AuthorID IN (
SELECT Author.AuthorID
FROM ​ [User] INNER JOIN([Like] INNER JOIN (Movie INNER JOIN (Author INNER JOIN ​MovieDetail ON Author.AuthorID = MovieDetail.AuthorID) 
ON Movie.MovieID = ​MovieDetail.MovieID) ON [Like].MovieID = Movie.MovieID) ON [User].UserID = [Like].UserID
WHERE [User].UserID = 5 AND LikeOrDislike = 'yes' 
GROUP BY Author.AuthorID
) AND Movie.MovieID NOT IN ( 
SELECT [Like].MovieID
FROM [User] INNER JOIN [Like] ON [User].UserID = [Like].UserID
WHERE [User].UserID = 5 AND LikeOrDislike = 'yes'
)







