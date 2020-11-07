DECLARE @polygon geometry;
SET @polygon = 'MULTIPOLYGON(
    ((8.25 3.25, 8.25 3.5, 8 3.5, 8 4, 8.25 4, 8.25 4.25, 9.25 4.25, 9.25 4, 9.5 4, 9.5 3.5, 9.25 3.5, 9.25 3.25, 8.25 3.25),(8.5 3.5, 8.5 4, 9 4, 9 3.5, 8.5 3.5))
)';
INSERT INTO GeometryObject("Type", GeomObject) VALUES(@polygon.STGeometryType(),@polygon)