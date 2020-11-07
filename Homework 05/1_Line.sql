DECLARE @line geometry = 'LINESTRING(0.75 6.5, 0.75 5.5, 1.75 5.5, 1.75 6.5, 2.5 6.5)';
INSERT INTO GeometryObject("Type",GeomObject) VALUES (@line.STGeometryType(),@line.STAsText())
