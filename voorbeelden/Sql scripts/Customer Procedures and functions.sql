
DROP FUNCTION CustomerExists
CREATE FUNCTION funcCustomerExists(@id INT)
returns INT
AS
begin
    DECLARE @Count int;
		SELECT @Count = COUNT(Id)
		FROM Customer
		WHERE Id = @Id;
    return @Count;
end

go
DROP PROCEDURE spManageCustomers
CREATE PROCEDURE spManageCustomer
@id INT,
@FirstName VARCHAR(15),
@LastName VARCHAR(30),
@Prefix VARCHAR(10),
@Street VARCHAR(50),
@Place VARCHAR(20),
@ZipCode VARCHAR(10),
@Email VARCHAR(50),
@Phonenumber VARCHAR(15)

AS
begin
    IF (SELECT dbo.funcCustomerExists(@Id)) > 0
		BEGIN
				UPDATE Customer SET
				firstName = @FirstName, 
				prefix = @Prefix, 
				lastName = @LastName, 
				street = @Street, 
				place = @Place, 
				zipCode = @ZipCode, 
				email = @Email, 
				phoneNumber = @Phonenumber
			WHERE id = @id;
		END
    ELSE
		BEGIN
			INSERT INTO Customer (firstName, prefix, lastName, street, place, zipCode, email, phoneNumber)
			VALUES(@FirstName, @Prefix, @LastName, @Street, @Place, @ZipCode, @Email, @Phonenumber)
			SELECT SCOPE_IDENTITY();
		END
end

EXEC spManageCustomer 
	@id = null, 
	@FirstName = 'Daphne', 
	@Prefix = null, 
	@LastName = 'Verhees',
	@Street = 'Vaartdijk 17',
	@Place = 'Someren',
	@Phonenumber = '0612345678',
	@ZipCode = '5712ER',
	@Email = 'daphlicious@live.nl'

EXEC spManageCustomer
	@id = 41,
	@FirstName = 'Elke', 
	@Prefix = 'van der', 
	@LastName = 'Schouw',
	@Street = 'Vaartdijk 17',
	@Place = 'Someren-Eind',
	@Phonenumber = '+31678964222',
	@ZipCode = '5712ER',
	@Email = 'elkevanderschouw@live.nl'

SELECT * FROM Customer
SELECT dbo.funcCustomerExists(1)