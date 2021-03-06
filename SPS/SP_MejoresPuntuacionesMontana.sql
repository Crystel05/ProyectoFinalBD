USE [BDProyectoFinal]
GO
/****** Object:  StoredProcedure [dbo].[SP_MejoresPuntuacionesMontana]    Script Date: 02/02/2021 19:31:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_MejoresPuntuacionesMontana] @inAnnoGiro int, @inNombreGiro varchar(50), @outResultCode int output
AS

BEGIN

SET NOCOUNT ON

	BEGIN TRY

		DECLARE 
		@giroId int,
		@instanciaGiroId int,
		@corredorActual int = 1,
		@totalCorredores int,
		@carrerasPerdidas int, 
		@corredorId int

		SET @giroId = (SELECT ID FROM dbo.Giro WHERE Nombre = @inNombreGiro) 
		SET @instanciaGiroId = (SELECT ID FROM dbo.InstanciaGiro WHERE Anno = @inAnnoGiro AND GiroId = @giroId)
		
		DECLARE @ganadoresMontana TABLE(
			Nombre varchar(50), 
			PosicionFinal int, 
			CantidadPuntos int
		)

		DECLARE @ordenadosPuntaje TABLE (
			ID int IDENTITY,
			IgxEqxCorredorId int
		)

		INSERT INTO @ordenadosPuntaje(
			IgxEqxCorredorId
		)

		SELECT TOP 10 ID FROM dbo.IGxEQxCorredor WHERE InstanciaGiroId = @instanciaGiroId
		ORDER BY SumaPuntosMontana DESC

		SET @totalCorredores = (SELECT COUNT(*) FROM @ordenadosPuntaje)
		WHILE(@corredorActual <= @totalCorredores)
		BEGIN

			SET @corredorId = (SELECT CorredorId FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual))
			INSERT INTO @ganadoresMontana
			VALUES(
				(SELECT Nombre FROM  dbo.Corredor WHERE ID = @corredorId),
				@corredorActual,
				(SELECT SumaPuntosMontana FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID  = @corredorActual))
				
			)
			
			SET @corredorActual = @corredorActual + 1
			PRINT @corredorActual
		
		END
	SELECT Nombre, CantidadPuntos, PosicionFinal FROM @ganadoresMontana
		
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