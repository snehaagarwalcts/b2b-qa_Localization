

# RUN SAUCELABS FOR IE8
mvn -Psaucelabs,ie8 clean test-compile test 
mv target /Users/i312480/Desktop/sc/ie8

# RUN SAUCELABS FOR FIREFOX
mvn -Psaucelabs,firefox clean test-compile test 
mv target /Users/i312480/Desktop/sc/firefox

# RUN SAUCELABS FOR SAFARI
mvn -Psaucelabs,safari clean test-compile test 
mv target /Users/i312480/Desktop/sc/safari

# RUN SAUCELABS FOR CHROME
mvn -Psaucelabs,chrome clean test-compile test 
mv target /Users/i312480/Desktop/sc/chrome

# RUN SAUCELABS FOR IE11
mvn -Psaucelabs,ie11 clean test-compile test 
mv target /Users/i312480/Desktop/sc/ie11
