@REM 设置Tomcat根目录  |  请将下面的目录修改为自己电脑上的Tomcat根目录
set TOMCAT_HOME="D:\ProgramFiles2\Tomcat\apache-tomcat-8.0.50"
@REM 保存当前目录
set CURRENT_DIR=%cd%

call .\deploy.bat
copy config\*.xml "%TOMCAT_HOME%\conf\Catalina\localhost\"

set TOMCAT_HOME_BIN="%TOMCAT_HOME%\bin"

@REM Open a new tab with the default profile in the current window
@REM wt -w 0 nt --title console -d %TOMCAT_HOME_BIN% cmd /k catalina run

cd %TOMCAT_HOME_BIN%
.\startup.bat
cd %CURRENT_DIR%