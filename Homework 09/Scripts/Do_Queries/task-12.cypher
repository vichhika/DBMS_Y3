//task_12
MATCH g=(p:Product)-[q:ADDED_TO]-(:Invoice)-[:OWN_BY]-(:Customer{CusID:"1"}) return p.ProductName AS Product, toInteger(q.Quantity) AS Qty