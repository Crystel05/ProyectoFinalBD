USE [BDProyectoFinal]
GO
/****** Object:  StoredProcedure [dbo].[SP_MejoresCorredoresGiro]    Script Date: 31/01/2021 21:33:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_MejoresPuntuacionesMontana] @inAnnoGiro int, @inNombreGiro varchar(50), @outResultCode int output
AS

BEGIN

SET NOCOUNT ON

	BEGIN TRY

		DECLARE 
		@corredorActual int = 1,
		@totalCorredores int,
		@carrerasPerdidas int, 
		@corredorId int
		
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

		SELECT TOP 10 ID FROM dbo.IGxEQxCorredor
		ORDER BY SumaPuntosMontana DESC

		SET @totalCorredores = (SELECT COUNT(*) FROM @ordenadosPuntaje)
		WHILE(@corredorActual <= @totalCorredores)
		BEGIN

			SET @corredorId = (SELECT CorredorId FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual))
			INSERT INTO @ganadoresMontana(
				Nombre,
				CantidadPuntos, 
				PosicionFinal
			)
			VALUES(
				(SELECT Nombre FROM  dbo.Corredor WHERE ID = @corredorId),
				(SELECT SumaPuntosMontana FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID  = @corredorActual)),
				@corredorActual
			)
			
		
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