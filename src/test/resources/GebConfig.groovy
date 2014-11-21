package test.resources

import org.openqa.selenium.firefox.FirefoxDriver


driver = { new FirefoxDriver() }

baseUrl = "http://lscob2b.local:9001/lscob2bstorefront"

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

autoClearCookies = true