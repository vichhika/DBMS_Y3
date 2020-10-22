CREATE TABLE StudentCard(
	StudentID INT FOREIGN KEY REFERENCES Student ON DELETE CASCADE,
	IsuedDate DATE NOT NULL,
	ExpiredDate DATE NOT NULL,
	UniversityName CHAR(50) NOT NULL
) 
