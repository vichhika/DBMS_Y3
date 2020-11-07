DECLARE @g geometry;  
SET @g = 'COMPOUNDCURVE(CIRCULARSTRING(1.2 0.5, 0.6 1.6, 1.9 0.8),(1.9 0.8, 1.1 0.8), CIRCULARSTRING(1.1 0.8, 2.5 2, 2.8 0.4))';  
INSERT INTO GeometryObject("Type", GeomObject) VALUES (@g.STGeometryType(), @g)