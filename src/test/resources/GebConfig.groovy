package test.resources

import geb.driver.SauceLabsDriverFactory
import org.openqa.selenium.firefox.FirefoxDriver

def sauceBrowser = System.getProperty("geb.sauce.browser")
if (sauceBrowser) {
    driver = {
        def username = System.getenv("GEB_SAUCE_LABS_USER")
        assert username
        def accessKey = System.getenv("GEB_SAUCE_LABS_ACCESS_PASSWORD")
        assert accessKey
        new SauceLabsDriverFactory().create(sauceBrowser, username, accessKey)
    }
} else {
    driver = { new FirefoxDriver() }
}

dev = true

devHost = "lscob2b.local:9001"
qaHost = "b2bqa-000-web-000.lsco-b2b.com"

baseUrl = "http://" + (dev ? devHost + "/lscob2bstorefront" : qaHost)


reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

autoClearCookies = true