CREATE TABLE University(
    UniID INT IDENTITY(1,1) PRIMARY KEY,
    UniName CHAR(50) NOT NULL,
    Geo GEOGRAPHY NOT NULL
)