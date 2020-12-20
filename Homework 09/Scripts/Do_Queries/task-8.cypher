//task_8
MATCH (c:Customer) return c.Province,count(c.Province) AS `Number of Customers` ORDER BY `Number of Customers` DESC