//task_14
MATCH (p:Product)-[q:ADDED_TO]-(i:Invoice) WHERE "2018" IN split(i.InvoiceDate,"/") return p.ProductName AS Prodcut ORDER BY q.Quantity DESC LIMIT 1