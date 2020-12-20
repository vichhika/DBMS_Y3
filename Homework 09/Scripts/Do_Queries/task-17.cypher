// task_17
//task_18
MATCH (c:Customer) WHERE NOT EXISTS {(c)-[:OWN_BY]-()} DETACH DELETE c