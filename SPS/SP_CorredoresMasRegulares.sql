USE [BDProyectoFinal]
GO
/****** Object:  StoredProcedure [dbo].[SP_MejoresCorredoresGiro]    Script Date: 31/01/2021 21:33:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_CorredoresMasRegulares] @inAnnoGiro int, @inNombreGiro varchar(50), @outResultCode int output
AS

BEGIN

SET NOCOUNT ON

	BEGIN TRY

		DECLARE 
		@corredorActual int = 1,
		@totalCorredores int,
		@carrerasPerdidas int, 
		@corredorId int,
		@pos int = 1
		
		
		DECLARE @corredoresMasRegulares TABLE(
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

		SELECT ID FROM dbo.IGxEQxCorredor
		ORDER BY SumaPuntosReg DESC

		SET @totalCorredores = (SELECT COUNT(*) FROM @ordenadosPuntaje)
		WHILE(@corredorActual <= @totalCorredores)
		BEGIN

			SET @carrerasPerdidas = (SELECT COUNT(*) FROM dbo.MovPtsReg WHERE IGxEQxCorredorId = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual) AND CantidadPuntos = 0)
			SET @corredorId = (SELECT CorredorId FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual))
			IF(@carrerasPerdidas = 0)
			BEGIN
				INSERT INTO @corredoresMasRegulares(
					Nombre,
					PosicionFinal, 
					CantidadPuntos
				)
				VALUES(
					(SELECT Nombre FROM dbo.Corredor WHERE ID = @corredorId),
					@pos,
					(SELECT SumaPuntosReg FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual))
				)
				SET @pos = @pos + 1

			END
			SET @corredorActual = @corredorActual + 1
		
		END
	SELECT TOP 10 Nombre, CantidadPuntos, PosicionFinal FROM @corredoresMasRegulares
		
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