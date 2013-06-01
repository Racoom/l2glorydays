@echo off
title Glory Of Day's: Game Server Console
color E
:start
echo Starting GameServer.
echo.

java -version:1.7 -server -Dfile.encoding=UTF-8 -Xmx2G -Xnoclassgc -XX:+UseConcMarkSweepGC -XX:-UseGCOverheadLimit -cp config/xml;../libs/*; lineage2.gameserver.GameServer

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Server restarted ...
echo.
goto start
:error
echo.
echo Server terminated abnormaly ...
echo.
:end
echo.
echo Server terminated ...
echo.

pause