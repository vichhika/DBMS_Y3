//r_Invoice_8
MATCH (i:Invoice),(c:Customer),(p_3:Product) WHERE i.InvoiceID = "8" AND c.CusID = "7" AND p_3.ProductID = "3" CREATE (i)-[:OWN_BY]->(c),(p_3)-[:ADDED_TO{Quantity:"1"}]->(i)