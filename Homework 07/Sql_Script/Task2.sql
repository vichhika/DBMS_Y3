SELECT Title, COUNT(Movie.MovieID) AS LikeAmount
FROM [User] INNER JOIN ([Like] INNER JOIN Movie ON [Like].MovieID = Movie.MovieID) ON [User].UserID = [Like].UserID
WHERE Country IN (
	SELECT Country 
	FROM [User]
	WHERE UserID = 1
) AND [Like].UserID != 1 AND LikeOrDislike = 'yes'
GROUP BY Movie.Title
ORDER BY LikeAmount DESC;