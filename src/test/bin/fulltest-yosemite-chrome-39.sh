#!/bin/sh

# Supported Browser Name {android|chrome|firefox|htmlunit|internet explorer|iPhone|iPad|opera|safari}.

export SELENIUM_PLATFORM="OS X 10.10"
export SELENIUM_VERSION="39"
export SELENIUM_BROWSER="chrome"

echo "Starting test on $SELENIUM_PLATFORM / $SELENIUM_BROWSER / $SELENIUM_VERSION"
mvn test-compile test -DargLine="-Dgeb.sauce.browser=1 -Dgeb.sauce.user=simone-romei -Dgeb.sauce.access.key=6654f839-e7e6-4263-b5e7-7980eefb4b42"
