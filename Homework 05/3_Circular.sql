DECLARE @circular geometry;
SET @circular = 'CIRCULARSTRING(6 5.40, 5.60 6.10, 6.45 5.90, 7.20 5.75, 7 6.60)';
INSERT INTO GeometryObject("Type", GeomObject) VALUES (@circular.STGeometryType(), @circular)