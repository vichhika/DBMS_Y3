// task_16
MATCH (c:Customer)<-[:OWN_BY]-(i:Invoice) WHERE toInteger(replace(i.TotalAmount,"$","")) > 50 SET c.MemberShip = "Premium" return c