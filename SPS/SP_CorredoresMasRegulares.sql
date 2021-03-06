USE [BDProyectoFinal]
GO
/****** Object:  StoredProcedure [dbo].[SP_CorredoresMasRegulares]    Script Date: 02/02/2021 18:18:32 ******/
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
		@giroId int, 
		@instanciaGiroId int,
		@corredorActual int = 1,
		@totalCorredores int,
		@carrerasPerdidas int, 
		@corredorId int,
		@pos int = 1, 
		@instanciCorredor int, 
		@cantidadCorredoresGanadores int
		
		SET @giroId = (SELECT ID FROM dbo.Giro WHERE Nombre = @inNombreGiro) 
		SET @instanciaGiroId = (SELECT ID FROM dbo.InstanciaGiro WHERE Anno = @inAnnoGiro AND GiroId = @giroId)
		
		DECLARE @corredoresMasRegulares TABLE(
			Nombre varchar(50), 
			PosicionFinal int, 
			CantidadPuntos int
		)

		DECLARE @ordenadosPuntaje TABLE (
			ID int IDENTITY,
			IgxEqxCorredorId int, 
			ingresado bit DEFAULT 0
		)

		INSERT INTO @ordenadosPuntaje(
			IgxEqxCorredorId
		)

		SELECT ID FROM dbo.IGxEQxCorredor WHERE InstanciaGiroId = @instanciaGiroId
		ORDER BY SumaPuntosReg DESC

		SET @totalCorredores = (SELECT COUNT(*) FROM @ordenadosPuntaje)
		WHILE(@corredorActual <= @totalCorredores)
		BEGIN
			SET @instanciCorredor = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual)
			SET @carrerasPerdidas = (SELECT COUNT(*) FROM dbo.MovPtsReg WHERE IGxEQxCorredorId = @instanciCorredor  AND CantidadPuntos = 0)
			SET @corredorId = (SELECT CorredorId FROM dbo.IGxEQxCorredor WHERE ID = @instanciCorredor)
			IF(@carrerasPerdidas = 0)
			BEGIN
				INSERT INTO @corredoresMasRegulares
				VALUES(
					(SELECT Nombre FROM dbo.Corredor WHERE ID = @corredorId),
					@pos,
					(SELECT SumaPuntosReg FROM dbo.IGxEQxCorredor WHERE ID = @instanciCorredor)
				)

				UPDATE @ordenadosPuntaje
				SET ingresado = 1
				WHERE ID = @corredorActual

				SET @pos = @pos + 1

			END
			SET @corredorActual = @corredorActual + 1
		
		END

		SET @cantidadCorredoresGanadores = (SELECT COUNT(*) FROM @corredoresMasRegulares)
		SET @pos = @cantidadCorredoresGanadores + 1
		IF ( @cantidadCorredoresGanadores < 10)
		BEGIN
			
			SET @corredorActual = 1
			WHILE(@corredorActual <= (SELECT COUNT(*) FROM @ordenadosPuntaje))
			BEGIN
				SET @instanciCorredor = (SELECT IgxEqxCorredorId FROM @ordenadosPuntaje WHERE ID = @corredorActual AND ingresado = 0)
				IF((SELECT ingresado FROM @ordenadosPuntaje WHERE ID = @corredorActual) = 0)
				BEGIN
					
					SET @corredorId = (SELECT CorredorId FROM dbo.IGxEQxCorredor WHERE ID = @instanciCorredor)
					INSERT INTO @corredoresMasRegulares VALUES(
						(SELECT Nombre FROM dbo.Corredor WHERE ID = @corredorId),
						@pos, 
						(SELECT SumaPuntosReg FROM dbo.IGxEQxCorredor WHERE ID = @instanciCorredor)
					)
					SET @pos = @pos + 1
					
				END
				
				SET @corredorActual = @corredorActual + 1
			
			END

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