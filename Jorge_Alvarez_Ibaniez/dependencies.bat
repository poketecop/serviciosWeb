@echo off
echo Ejecutando el primer comando...
call mvn install:install-file -Dfile=lib/SignalPlot-1.0.jar -DgroupId=es.uned.scc.grados.appdist.trabajos.ws -DartifactId=SignalPlot -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar

echo.

echo Ejecutando el segundo comando...
call mvn install:install-file -Dfile=lib/SignalGenerator-1.0.jar -DgroupId=es.uned.scc.grados.appdist.trabajos.ws -DartifactId=SignalGenerator -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar

echo.

echo Ejecutando el tercer comando...

call mvn dependency:copy-dependencies

echo.
echo Proceso completado.