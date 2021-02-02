USE [BDProyectoFinal]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_ObtenerNombresGiros] @inAnno int, @outResultCode int output
AS

BEGIN

SET NOCOUNT ON

	BEGIN TRY
		
		DECLARE 
		@actual int = 1,
		@totalTabla int, 
		@giroActual int

		DECLARE @giros TABLE(
			ID int IDENTITY, 
			GiroId int,
			nombreGiro varchar(50)
		)
		INSERT INTO @giros(
			GiroId
		)
		SELECT GiroId FROM dbo.InstanciaGiro WHERE Anno = @inAnno

		SET @totalTabla = (SELECT COUNT(*) FROM @giros)
		
		WHILE(@actual <= @totalTabla)
		BEGIN
			
			SET @giroActual = (SELECT GiroId FROM @giros WHERE ID = @actual)
			UPDATE @giros
			SET nombreGiro = (SELECT Nombre FROM dbo.Giro WHERE ID = @giroActual)
			WHERE ID = @actual

			SET @actual = @actual + 1
		
		END
		
	SET @outResultCode = 1
	SELECT nombreGiro FROM @giros

	END TRY

	BEGIN CATCH

		INSERT INTO dbo.errores VALUES(
			SUSER_NAME(),
			ERROR_NUMBER(),
			ERROR_STATE(),
			ERROR_SEVERITY(),
			ERROR_LINE(),
			ERROR_PROCEDURE(),
			ERROR_MESSAGE(),
			GETDATE()
		);

		SET @outResultCode = 50001

	END CATCH

	SELECT @outResultCode AS N

SET NOCOUNT OFF

END