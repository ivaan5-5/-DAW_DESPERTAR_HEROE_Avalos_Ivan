<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ranking Jedi - El Despertar del Héroe</title>
                <link rel="stylesheet" type="text/css" href="estilos.css"/>
            </head>
            <body>
                <div class="contenedor">
                    <h1>STAR WARS: EL DESPERTAR DEL HÉROE</h1>
                    <h2>Clasificación JEDI (Top 10)</h2>
                    <p class="fecha">Generado desde ranking.xml</p>

                    <table>
                        <thead>
                            <tr>
                                <th>Posición</th>
                                <th>Jugador</th>
                                <th>Puntuación</th>
                            </tr>
                        </thead>
                        <tbody>
                            <xsl:for-each select="ranking/jugador">
                                <xsl:sort select="puntuacion" data-type="number" order="descending"/>
                                <tr>
                                    <td>#<xsl:value-of select="position()"/></td>
                                    <td><xsl:value-of select="nombre"/></td>
                                    <td><xsl:value-of select="puntuacion"/> pts</td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>