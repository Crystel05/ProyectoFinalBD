USE [BDProyectoFinal]
GO
/****** Object:  StoredProcedure [dbo].[SP_ObtenerAnnos]    Script Date: 31/01/2021 13:10:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_ObtenerAnnos] @outResultCode int output
AS

BEGIN

SET NOCOUNT ON

	BEGIN TRY
		
		SELECT DISTINCT Anno FROM dbo.InstanciaGiro
		
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