USE [BDProyectoFinal]
GO
ALTER PROCEDURE [dbo].[SP_IGxEQ_EquiposGanadores] @inAno int, @inNombreGiro varchar(50), @outResultCode int output
AS
BEGIN
SET NOCOUNT ON
	BEGIN TRY
		
		DECLARE 
		@instanciaGiroId int,
		@GiroId int,
		@EquiId int,
		@EquiNomb varchar(50),
		@lo int = 1,
		@hi int = 3

		DECLARE @equiposGanadores TABLE(
		ID int IDENTITY,
		EquipoId int,
		NombreEquipo varchar(50),
		Tiempo int)
		

		SET @outResultCode = 0
		IF NOT EXISTS(SELECT ID FROM dbo.Giro WHERE Nombre = @inNombreGiro) OR NOT EXISTS(SELECT ID FROM dbo.InstanciaGiro WHERE Anno = @inAno)
		BEGIN
			SET @outResultCode = 50002
		END

		SET @GiroId = (SELECT ID FROM dbo.Giro WHERE Nombre = @inNombreGiro)
		SET @instanciaGiroId = (SELECT ID FROM dbo.InstanciaGiro WHERE GiroId = @GiroId AND Anno = @inAno)

		INSERT INTO @equiposGanadores(
		EquipoId,
		Tiempo)
		SELECT TOP 3 
		EquipoId, 
		totalTiempo 
		FROM dbo.IGxEquipo
		WHERE InstanciagiroId = @instanciaGiroId
		ORDER BY totalTiempo ASC
		

		WHILE @lo <= @hi 
		BEGIN

			SET @EquiId = (SELECT EquipoId FROM @equiposGanadores WHERE ID = @lo)
			SET @EquiNomb = (SELECT Nombre FROM dbo.Equipo WHERE ID = @EquiId)

			UPDATE @equiposGanadores
			SET NombreEquipo = @EquiNomb
			WHERE ID = @lo

			SET @lo = @lo+1
		END

		SELECT ID, NombreEquipo, Tiempo FROM @equiposGanadores

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

SET NOCOUNT OFF
END
