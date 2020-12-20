//task_10
MATCH (p:Product)-[r:ADDED_TO]-(i:Invoice) WHERE "2019" in split(i.InvoiceDate,"/") return p.ProductName AS Product