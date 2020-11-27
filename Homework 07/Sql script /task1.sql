

SELECT TOP 3 Movie.Title,COUNT(Movie.Title) AS 'Like count'
FROM [User] INNER JOIN (Movie INNER JOIN [Like] ON Movie.MovieID = [Like].MovieID) ON [User].UserID = [Like].UserID
WHERE [Like].LikeOrDislike = 'yes' and [User].UserID != 5 --UserID is changeable 
GROUP BY Movie.Title
ORDER BY COUNT(Movie.Title) DESC;