SELECT "Type" ,GeomObject.STArea() AS "Area",GeomObject.STLength() AS "Length",GeomObject.STAsText() AS "GeoText"
FROM GeometryObject ORDER BY ID;