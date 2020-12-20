//task_6
MATCH (i:Invoice)-[r:ADDED_TO]-(p:Product) WHERE i.InvoiceID = "1" return p.ProductID AS ProductID,p.ProductName AS ProductName,toInteger(r.Quantity) AS Quantities