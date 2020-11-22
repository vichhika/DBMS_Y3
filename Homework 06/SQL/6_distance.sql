DECLARE @g geography;  
DECLARE @h geography;  
SELECT @g = Geo FROM University WHERE UniID = 9;  
SELECT @h = Geo FROM University WHERE UniID = 11;    
SELECT @g.STDistance(@h)/1000 AS km;