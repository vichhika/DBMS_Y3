DECLARE @g geography;
SELECT @g = Geo FROM University WHERE UniID = 9;
SELECT UniName FROM University WHERE Geo.STDistance(@g) <= 3 AND UniID != 9 ORDER BY Geo.STDistance(@g) 