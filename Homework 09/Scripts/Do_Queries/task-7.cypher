//task_7
MATCH (c:Customer) WHERE "2000" in split(c.BirthDate,"/") AND "B" in split(toUpper(c.CusName),"") return c AS Customer