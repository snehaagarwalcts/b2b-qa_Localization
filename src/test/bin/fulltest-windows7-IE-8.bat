set SELENIUM_PLATFORM=Windows 7
set SELENIUM_VERSION=8
set SELENIUM_BROWSER=Internet explorer

echo "Starting test on %SELENIUM_PLATFORM% / %SELENIUM_BROWSER% / %SELENIUM_VERSION%"
mvn test-compile test -DargLine="-Dgeb.sauce.browser=1 -Dgeb.sauce.user= -Dgeb.sauce.access.key="
