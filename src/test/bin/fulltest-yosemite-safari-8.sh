#!/bin/sh

# Supported Browser Name {android|chrome|firefox|htmlunit|internet explorer|iPhone|iPad|opera|safari}.

export SELENIUM_PLATFORM="OS X 10.10"
export SELENIUM_VERSION="8"
export SELENIUM_BROWSER="safari"

echo "Starting test on $SELENIUM_PLATFORM / $SELENIUM_BROWSER / $SELENIUM_VERSION"
mvn test-compile test -DargLine="-Dgeb.sauce.browser=1 -Dgeb.sauce.user= -Dgeb.sauce.access.key="
