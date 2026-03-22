ALTER TABLE medicos ADD ativo TINYINT;
GO

UPDATE medicos SET ativo = 1;
GO

ALTER TABLE medicos ADD telefone VARCHAR(20);
GO

UPDATE medicos SET telefone = '000000000' WHERE telefone IS NULL;
GO