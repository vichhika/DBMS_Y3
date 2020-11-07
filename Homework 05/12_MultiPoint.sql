DECLARE @p geometry;
SET @p = 'MULTIPOINT((8 1.5), (8.5 1), (9 2))';
INSERT INTO GeometryObject("Type", GeomObject) VALUES (@p.STGeometryType(), @p)