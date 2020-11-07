DECLARE @g geometry;  
SET @g = 'COMPOUNDCURVE(
    (4.3 0.5, 3.6 1.6, 4.3 1.9, 5 1.5), CIRCULARSTRING(5 1.5, 4.8 1.2, 5.4 1.1), (5.4 1.1, 5.4 0.5, 4.3 0.5)
    )';  
INSERT INTO GeometryObject("Type", GeomObject) VALUES (@g.STGeometryType(), @g)