DROP FUNCTION funcTaskExists
go

CREATE FUNCTION funcTaskExists(@id INTEGER)
returns INT
AS
begin
    DECLARE @Count int;
    SELECT @Count = COUNT(Id)
    FROM Task
    WHERE Id = @Id;
    return @Count;
end

go
DROP PROCEDURE spManageTask

go

CREATE PROCEDURE spManageTask
@id INT,
@description VARCHAR(100),
@statementtype VARCHAR(10)

AS 
begin
	IF (SELECT dbo.funcTaskExists(@Id)) > 0
	BEGIN
		UPDATE Task SET
		[description] = @description
		WHERE id = @id;
	END

	
	BEGIN
		INSERT INTO Task([description])
		VALUES(@description)
	END
end

go

