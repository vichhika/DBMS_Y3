/*In case want to get userid and MovieID we can select it but need to remove group by*/
/*The code below only out put the movies that need to recommend to current user only*/
SELECT [Movie].Title
FROM [Like] INNER JOIN [Movie] ON [Like].MovieID = [Movie].MovieID
WHERE [Like].UserID IN (
                        SELECT [Like].UserID
                        FROM [Like] INNER JOIN [User] ON [Like].[UserID] = [User].[UserID]
                        WHERE [Like].MovieID = 1 AND LikeOrDislike = 'yes'
                    ) 
                    AND [Like].MovieID != 1 
                    AND LikeOrDislike = 'yes'
                    AND [Like].MovieID NOT IN (
                        SELECT [Like].[MovieID]
                        FROM [Like]
                        WHERE [Like].[UserID] = 1
                    ) 
GROUP BY Movie.Title