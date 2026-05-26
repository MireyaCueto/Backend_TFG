@echo off

echo Preparando el prototipo de Spring Boot con Maven...
echo Compilando...
call mvnw.cmd clean package -DskipTests
if errorlevel 1 (
    echo Error durante la compilacion con Maven.
    goto :end
)

set "JAR_PATH="
for %%f in (target\*.jar) do set "JAR_PATH=%%f"

if "%JAR_PATH%"=="" (
    echo Error: No se encontro el .jar compilado en la carpeta target.
    goto :end
)

java -jar "%JAR_PATH%"

:end
pause