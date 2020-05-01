call runcrud.bat
if "%ERRORLEVEL%" == "0" goto start
echo.
echo An error occurred, probably dev has no idea what he's doing
goto error

:start
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:end
echo.
echo Sukces!

:error
echo.
echo There were errors2