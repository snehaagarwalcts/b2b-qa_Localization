set SELENIUM_PLATFORM=Windows 7
set SELENIUM_VERSION=39
set SELENIUM_BROWSER=Chrome

echo "Starting test on %SELENIUM_PLATFORM% / %SELENIUM_BROWSER% / %SELENIUM_VERSION%"
mvn test-compile test -DargLine="-Dgeb.sauce.browser=1 -Dgeb.sauce.user=dshah1992 -Dgeb.sauce.access.key=2620798f-5186-4a1c-b1ea-3b5f5f2ae882"
