DECLARE @polygon geometry;
SET @polygon = 'POLYGON((3 3.75, 4 4.25, 5 3.75, 4 3.25, 3 3.75))'
INSERT INTO GeometryObject("Type", GeomObject) VALUES(@polygon.STGeometryType(), @polygon)