//r_Invoice_3
MATCH (i:Invoice),(c:Customer),(p_3:Product) WHERE i.InvoiceID = "3" AND c.CusID = "1" AND p_3.ProductID = "3" CREATE (i)-[:OWN_BY]->(c),(p_3)-[:ADDED_TO{Quantity:"3"}]->(i)