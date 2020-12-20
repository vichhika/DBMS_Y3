//task_5
MATCH (i:Invoice)-[r:OWN_BY]-(c:Customer) WHERE i.InvoiceID = "1" return c.CusID AS CusID,c.CusName AS Name,c.PhoneNo AS `Phone Number`