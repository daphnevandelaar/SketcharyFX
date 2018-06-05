SELECT * FROM funcCustomerById(38)
SELECT * FROM Invoice
SELECT * FROM funcInvoiceById(175)

go
CREATE VIEW vwBestTasks 
AS
SELECT COUNT(customerID) as AmountCustomers, Task.description 
FROM Invoice
INNER JOIN Customer ON Customer.id = invoice.customerID
INNER JOIN TasksOnInvoice ON Invoice.Id = TasksOnInvoice.invoiceID
INNER JOIN Task ON TasksOnInvoice.taskID = Task.id
GROUP BY Task.description;


SELECT * FROM vwBestTasks

