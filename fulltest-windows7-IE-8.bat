SELENIUM_PLATFORM="Windows 7"
SELENIUM_VERSION="8"
SELENIUM_BROWSER="internet explorer"

echo "Starting test on %SELENIUM_PLATFORM% / %SELENIUM_BROWSER% / %SELENIUM_VERSION%"
mvn test-compile test -DargLine="-Dgeb.sauce.browser=1 -Dgeb.sauce.user=USERNAME -Dgeb.sauce.access.key=ACCESS_KEY"
