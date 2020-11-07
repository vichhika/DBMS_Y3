DECLARE @line geometry;
SET @line = 'LINESTRING(0.5 3.5, 0.5 4.25, 2.25 4.25, 2.25 3.5, 1.45 3.30, 0.5 3.5)';
INSERT INTO GeometryObject("Type", GeomObject) VALUES(@line.STGeometryType(), @line)