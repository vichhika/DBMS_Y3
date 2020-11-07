DECLARE @circular geometry;
SET @circular = 'CIRCULARSTRING(7.95 6.40, 9 6, 8.60 6.90, 8.73 5.65, 9.5 5.5)';
INSERT INTO GeometryObject("Type", GeomObject) VALUES (@circular.STGeometryType(), @circular)