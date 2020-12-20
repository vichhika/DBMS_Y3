//r_Invoice_6
MATCH (i:Invoice),(c:Customer),(p_2:Product) WHERE i.InvoiceID = "6" AND c.CusID = "2" AND p_2.ProductID = "2" CREATE (i)-[:OWN_BY]->(c),(p_2)-[:ADDED_TO{Quantity:"1"}]->(i)