DECLARE @mutiline geometry;
SET @mutiline = geometry::STGeomFromText('MULTILINESTRING((3.25 6.5, 3.25 5.5, 4.40 6.5, 5 6), (4 5, 4.5 6))',0);

INSERT INTO GeometryObject("Type", GeomObject) VALUES (@mutiline.STGeometryType(), @mutiline);