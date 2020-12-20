// task_4
MATCH (c:Invoice) WITH split(c.InvoiceDate, "/") AS a, toInteger(replace(c.TotalAmount,"$","")) AS b ORDER BY a[2] return a[2] AS Years ,"$"+sum(b) AS Total