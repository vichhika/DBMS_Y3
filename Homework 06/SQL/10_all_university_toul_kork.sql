DECLARE @g geography = (SELECT Geo FROM District WHERE DistricID = 1);
DECLARE @i geometry = geometry::STGeomFromText(@g.ToString(),4326);
SELECT UniName AS "University at TOUK KORK" FROM University WHERE @i.STIntersects(geometry::STGeomFromText(Geo.ToString(),4326)) = 1;
