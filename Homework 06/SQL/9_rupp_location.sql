DECLARE @g geography = (SELECT Geo FROM District WHERE DistricID = 2);
DECLARE @h geography = (SELECT Geo FROM University WHERE UniID = 9);
DECLARE @i geometry = geometry::STGeomFromText(@g.ToString(),4326);
DECLARE @i1 geometry = geometry::STGeomFromText(@h.ToString(),4326);
SELECT @i.STOverlaps(@i1) AS LocatedIn;