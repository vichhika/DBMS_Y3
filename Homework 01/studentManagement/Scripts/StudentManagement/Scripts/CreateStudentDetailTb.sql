CREATE TABLE StudentDetail(
	StudentID INT FOREIGN KEY REFERENCES Student ON DELETE CASCADE,
	CourseID INT FOREIGN KEY REFERENCES Course ON DELETE CASCADE,
	FinalScore TINYINT
)