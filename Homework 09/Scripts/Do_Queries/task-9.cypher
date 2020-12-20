//task_9
MATCH (c:Customer) return c.Province AS Province,c.Gender AS Gender,COUNT(c.Gender) AS `Number of Customers` ORDER BY Province