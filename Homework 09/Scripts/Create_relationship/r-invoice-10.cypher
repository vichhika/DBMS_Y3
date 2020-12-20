//r_Invoice_10
MATCH (i:Invoice),(c:Customer),(p_3:Product) WHERE i.InvoiceID = "10" AND c.CusID = "3" AND p_3.ProductID = "3" CREATE (i)-[:OWN_BY]->(c),(p_3)-[:ADDED_TO{Quantity:"10"}]->(i)