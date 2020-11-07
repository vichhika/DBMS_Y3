DECLARE @p geometry;
SET @p = 'POINT(6.5 2)';
INSERT INTO GeometryObject("Type", GeomObject) VALUES (@p.STGeometryType(), @p)