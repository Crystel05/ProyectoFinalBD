USE BDProyectoFinal;

GO

SET NOCOUNT ON

--INSERT XML en Pais-----------------------------------------------------------

INSERT INTO dbo.Pais (ID,
nombre)

SELECT 
A.Pais.value('@Id', 'int') AS ID,
A.Pais.value('@Nombre', 'varchar(50)') AS nombre


FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/Paises/Pais') AS A(Pais)

--SELECT * FROM dbo.Pais

--INSERT XML en Giro-----------------------------------------------------------

INSERT INTO dbo.Giro(ID,
nombre,
PaisId)

SELECT 
A.Giro.value('@Id', 'int') AS ID,
A.Giro.value('@Nombre', 'varchar(50)') AS nombre,
A.Giro.value('@IdPais', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/Giros/Giro') AS A(Giro)

--SELECT * FROM dbo.Giro 

--INSERT XML en Etapa-----------------------------------------------------------

INSERT INTO dbo.Etapa(ID,
nombre,
GiroId,
Puntos)

SELECT 
A.Etapa.value('@Id', 'int') AS ID,
A.Etapa.value('@Nombre', 'varchar(50)') AS nombre,
A.Etapa.value('@IdGiro', 'varchar(50)') AS Giro,
A.Etapa.value('@Puntos', 'varchar(50)') AS puntos

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/Etapas/Etapa') AS A(Etapa)

--SELECT * FROM dbo.Etapa

--INSERT XML en PremiosMontana-----------------------------------------------------------

INSERT INTO dbo.PremiosMontana(Nombre,
EtapaId,
Puntos)

SELECT 
A.Premio.value('@Nombre', 'varchar(50)') AS nombre,
A.Premio.value('@IdEtapa', 'varchar(50)') AS Giro,
A.Premio.value('@Puntos', 'varchar(50)') AS puntos

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/PremiosMontana/PremioMontana') AS A(Premio)

--SELECT * FROM dbo.PremiosMontana

--INSERT XML en Equipo-----------------------------------------------------------

INSERT INTO dbo.Equipo(ID,
Nombre)

SELECT 
A.Equipo.value('@Id', 'int') AS ID,
A.Equipo.value('@Nombre', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/Equipos/Equipo') AS A(Equipo)

--SELECT * FROM dbo.Equipo

--INSERT XML en Corredor-----------------------------------------------------------

INSERT INTO dbo.Corredor(ID,
Nombre)

SELECT 
A.Corredor.value('@Id', 'int') AS ID,
A.Corredor.value('@Nombre', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/Corredores/Corredor') AS A(Corredor)

--SELECT * FROM dbo.Equipo

--INSERT XML en Juez-----------------------------------------------------------

INSERT INTO dbo.Juez(ID,
Nombre)

SELECT 
A.Juez.value('@Id', 'int') AS ID,
A.Juez.value('@Nombre', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/Jueces/Juez') AS A(Juez)

--SELECT * FROM dbo.Juez

--INSERT XML en TiposMovimientoTiempo-----------------------------------------------------------

INSERT INTO dbo.TipoMovTiempo(ID,
Nombre)

SELECT 
A.MovsTiempo.value('@Id', 'int') AS ID,
A.MovsTiempo.value('@Nombre', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/TiposMovimientoTiempo/TipoMovimientoTiempo') AS A(MovsTiempo)

--SELECT * FROM dbo.TipoMovTiempo

--INSERT XML en TipoMovPtsReg-----------------------------------------------------------

INSERT INTO dbo.TipoMovPtsReg(ID,
Nombre)

SELECT 
A.MovsReg.value('@Id', 'int') AS ID,
A.MovsReg.value('@Nombre', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/TiposMovimientosPuntosRegularidad/TipoMovimientosPuntosRegularidad') AS A(MovsReg)

--SELECT * FROM dbo.TipoMovPtsReg

--INSERT XML en TiposMovimientoTiempo-----------------------------------------------------------

INSERT INTO dbo.TipoMovPtsMontana(ID,
Nombre)

SELECT 
A.MovsMont.value('@Id', 'int') AS ID,
A.MovsMont.value('@Nombre', 'varchar(50)') AS nombre

FROM(
SELECT CAST(c AS XML) FROM 
OPENROWSET(BULK 'E:\ArchivosTec\Cuartosemestre\Bases\ProyectoFinalBD\Catalogo-Tarea-Final.xml', SINGLE_BLOB) AS T(c)
) AS S(c)

cross apply c.nodes('Catalogos/TiposMovimientoPuntosMontana/TipoMovimientoPuntosMontana') AS A(MovsMont)

--SELECT * FROM dbo.TipoMovPtsMontana

--DELETE FROM dbo.PremiosMontana
--DELETE FROM dbo.Etapa
--DELETE FROM dbo.Giro
--DELETE FROM dbo.Pais
--DELETE FROM dbo.Equipo
--DELETE FROM dbo.Corredor
--DELETE FROM dbo.Juez
--DELETE FROM dbo.TipoMovPtsMontana
--DELETE FROM dbo.TipoMovPtsReg
--DELETE FROM dbo.TipoMovTiempo

