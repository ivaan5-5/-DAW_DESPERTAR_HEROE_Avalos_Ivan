# Star Wars: El Despertar del Héroe

Juego de aventura por turnos en consola hecho en Java. Permite crear un personaje, elegir arma, combatir contra tres enemigos y guardar las mejores puntuaciones en un archivo XML para luego mostrarlas en una página web con HTML y CSS.

## Requisitos
* Java (JDK 17 o superior).
* Saxon-HE 13.1 (o la extensión *XSLT/XPath for Visual Studio Code*) para generar el HTML.
* Un navegador web cualquiera para abrir la tabla.

## Archivos del proyecto
* `ElDespertarDelHeroe.java`: Código del juego con los menús, los combates, el inventario de Bacta y la lectura/escritura del ranking en XML.
* `ranking.xml`: Archivo donde se guardan las 10 mejores puntuaciones.
* `ranking.dtd`: Archivo DTD que comprueba que la estructura del XML sea correcta.
* `ranking.xsl`: Plantilla XSLT para convertir el XML en una tabla HTML ordenada de mayor a menor.
* `estilo.css`: Estilos de la tabla web.
* `ranking.html`: Página web generada con el ranking.
* `xpath.txt`: Las 5 consultas XPath de prueba con sus resultados.
* `Pruebas/`: Dos archivos XML con fallos hechos a propósito para probar la validación con el DTD.

## Cómo compilar y jugar
Abre una terminal en la carpeta del proyecto y ejecuta:

```bash
javac -encoding UTF-8 ElDespertarDelHeroe.java
java ElDespertarDelHeroe

Empiezas con 100 de vida y 3 viales de Bacta (cada uno cura 25 de vida, sin pasar de 100).

Tienes que ganar 3 combates seguidos: Cazarrecompensas de Greedo (40 HP), Soldado de Asalto Imperial (60 HP) e Inquisidor Sith (80 HP).

Mirar el inventario o intentar curarte cuando tienes la vida al máximo o no te quedan viales no gasta turno.

Ganas 100 puntos por cada enemigo derrotado y, si ganas los tres combates, se te suma la vida que te haya sobrado.

Guardado en XML
Al abrir el juego se leen los datos de ranking.xml. Si el archivo no existe todavía, el ranking empieza vacío. Si el archivo está roto o tiene datos mal puestos, el programa avisa por pantalla y no lo borra ni lo sobrescribe. Al terminar cada partida, se actualiza el Top 10 (manteniendo delante el más antiguo si hay empate) y se guarda en ranking.xml

ranking.xml está enlazado con ranking.dtd. En la carpeta Pruebas/ hay dos XML erróneos (uno sin puntuación y otro con una etiqueta inventada) para comprobar que el validador detecta los fallos
En xpath.txt están apuntadas las 5 consultas XPath probadas sobre ranking.xml

Para volver a crear ranking.html después de cambiar datos en ranking.xml, se ejecuta Saxon-HE desde la carpeta del proyecto (asegurándose de que la ruta no tenga tildes)


### Restablecer los datos de prueba y herramientas usadas
* **Restaurar el ranking inicial:** Si se modifica `ranking.xml` jugando partidas y se quiere volver a los 5 jugadores de prueba originales, se puede restaurar desde Git con `git checkout -- ranking.xml` (o volviendo a copiar los 5 registros base).
* **Herramientas externas:** Para Java solo se han utilizado las librerías estándar del JDK. Para la transformación XSLT a HTML se ha utilizado el procesador **Saxon-HE 13.1** (de Saxonica) y la extensión **XSLT/XPath for Visual Studio Code** para probar las consultas XPath y validar con el DTD.
