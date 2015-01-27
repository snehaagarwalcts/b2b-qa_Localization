b2b-qa
======

Automatic functional/acceptance testing for B2B project 

###############################################
# RUN ALL LOCAL TEST
###############################################

# 1 - Download & Install Maven tool version 3.X

http://maven.apache.org/download.cgi

# 2 - Move to root folder of project "lscob2b-quality" (the folder must contain file "pom.xml")
cd ${PROJECT_FOLDER}

# 3 - Clean & Compile & Run Test
${MAVEN_FOLDER}/bin/mvn clean test-compile test

###############################################
# RUN SINGLE LOCAL TEST
###############################################

# 3 - To run single test (or group)
${MAVEN_FOLDER}/bin/mvn clean test-compile -Dtest=WaitListTest test

