DECLARE @g geography;
SELECT @g = Geo FROM University WHERE UniID = 9;
SELECT TOP(3) UniName FROM University WHERE Geo.STDistance(@g) IS NOT NULL AND UniID != 9 ORDER BY Geo.STDistance(@g);