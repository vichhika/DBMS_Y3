//r_Invoice_7
MATCH (i:Invoice),(c:Customer),(p_6:Product) WHERE i.InvoiceID = "7" AND c.CusID = "9" AND p_6.ProductID = "6" CREATE (i)-[:OWN_BY]->(c),(p_6)-[:ADDED_TO{Quantity:"1"}]->(i)