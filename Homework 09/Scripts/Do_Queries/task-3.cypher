// task_3
//Do_Queries
MATCH (p:Product) WHERE toInteger(p.StockQty) < 10 return p.ProductName AS Product