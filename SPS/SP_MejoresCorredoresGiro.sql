USE [BDProyectoFinal]
GO
/****** Object:  StoredProcedure [dbo].[SP_MejoresCorredoresGiro]    Script Date: 02/02/2021 18:45:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_MejoresCorredoresGiro] @inAnnoGiro int, @inNombreGiro varchar(50), @outResultCode int output
AS

BEGIN

SET NOCOUNT ON

	BEGIN TRY

		DECLARE 
		@giroId int,
		@instanciaGiroId int,
		@cantidadCarreras int,
		@corredorActual int = 1,
		@totalCorredores int,
		@totalCarrerasCorredorActual int, 
		@corredorId int,
		@pos int = 1

		SET @giroId = (SELECT ID FROM dbo.Giro WHERE Nombre = @inNombreGiro) 
		SET @instanciaGiroId = (SELECT ID FROM dbo.InstanciaGiro WHERE Anno = @inAnnoGiro AND GiroId = @giroId)
		SET @cantidadCarreras = (SELECT COUNT(*) FROM dbo.Carrera WHERE instanciaGiroId = @instanciaGiroId)
		
		DECLARE @mejoresGiro TABLE(
			Nombre varchar(50), 
			PosicionFinal int, 
			tiempoAcumulado int
		)

		DECLARE @ordenadosTiempo TABLE (
			ID int IDENTITY,
			IgxEqxCorredorId int
		)
		INSERT INTO @ordenadosTiempo(
			IgxEqxCorredorId
		)
		SELECT ID FROM dbo.IGxEQxCorredor WHERE InstanciaGiroId = @instanciaGiroId
		ORDER BY SumaTiempo ASC

		SET @totalCorredores = (SELECT COUNT(*) FROM @ordenadosTiempo)
		WHILE(@corredorActual <= @totalCorredores)
		BEGIN

			SET @totalCarrerasCorredorActual = (SELECT COUNT(*) FROM dbo.Llegada WHERE IGxEQxCorredorId = (SELECT IgxEqxCorredorId FROM @ordenadosTiempo WHERE ID = @corredorActual))
			SET @corredorId = (SELECT CorredorId FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosTiempo WHERE ID = @corredorActual))
			IF(@totalCarrerasCorredorActual = @cantidadCarreras)
			BEGIN
				INSERT INTO @mejoresGiro(
					Nombre,
					PosicionFinal, 
					tiempoAcumulado
				)
				VALUES(
					(SELECT Nombre FROM dbo.Corredor WHERE ID = @corredorId),
					@pos,
					(SELECT SumaTiempo FROM dbo.IGxEQxCorredor WHERE ID = (SELECT IgxEqxCorredorId FROM @ordenadosTiempo WHERE ID = @corredorActual))
				)
				SET @pos = @pos + 1

			END
			SET @corredorActual = @corredorActual + 1
		
		END
	SELECT TOP 10 Nombre, tiempoAcumulado, PosicionFinal FROM @mejoresGiro
		
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