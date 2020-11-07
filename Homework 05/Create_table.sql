CREATE TABLE GeometryObject(
    ID INT IDENTITY(1,1) NOT NULL,
    "Type" CHAR(25) NOT NULL,
    GeomObject geometry
)