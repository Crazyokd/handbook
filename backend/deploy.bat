dir /s /B *.java > .\sources.txt

dir /s /B *.jar > .\classpath.txt

@REM 设置servlet-api.jar包路径  |  请将下面的目录修改为自己电脑上的相应目录
set CLSPT="D:\ProgramFiles2\Tomcat\apache-tomcat-8.0.50\lib\servlet-api.jar"

for /f "tokens=*" %%a in (classpath.txt) do call :processline %%a

set TARGET_DIR=".\WEB-INF\classes"

javac -encoding UTF-8 -d %TARGET_DIR% -classpath %CLSPT% -nowarn -g:none @sources.txt

del .\sources.txt
del .\classpath.txt

@REM echo %CLSPT% > out.txt

goto :eof

:processline
set CLSPT=%CLSPT%;%*

goto :eof

:eof