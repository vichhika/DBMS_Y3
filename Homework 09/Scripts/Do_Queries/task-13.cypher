//task_13
MATCH(i:Invoice)-[:OWN_BY]-(c:Customer) RETURN c.CusName AS Name,c.Gender AS Gender,"$"+SUM(toInteger(replace(i.TotalAmount,"$",""))) AS `Purchased Amount`