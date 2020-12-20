//task_10
MATCH (p:Product)-[r:ADDED_TO]-(i:Invoice) WHERE i.InvoiceDate =~ '7/[0-9]*/2019' return p.ProductName AS Product, sum(toInteger(r.Quantity)) AS Qty