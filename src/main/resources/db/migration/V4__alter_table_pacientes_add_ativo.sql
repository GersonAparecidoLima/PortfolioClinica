ALTER TABLE pacientes ADD ativo TINYINT;
GO

UPDATE pacientes SET ativo = 1;
GO