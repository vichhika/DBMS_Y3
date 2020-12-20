//r_Invoice_2
MATCH (i:Invoice),(c:Customer),(p_5:Product) WHERE i.InvoiceID = "2" AND c.CusID = "5" AND p_5.ProductID = "5" CREATE (i)-[:OWN_BY]->(c),(p_5)-[:ADDED_TO{Quantity:"1"}]->(i)