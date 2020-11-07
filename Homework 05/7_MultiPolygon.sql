DECLARE @polygon geometry;
SET @polygon = 'MULTIPOLYGON(
    ((5.70 3.30, 5.70 4.30, 7.30 4.30, 7.30 3.30, 5.70 3.30),(5.90 3.5, 6.5 4, 7.10 3.5, 5.90 3.5))
    )';
INSERT INTO GeometryObject("Type",GeomObject) VALUES(@polygon.STGeometryType(), @polygon)