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

localHost = "lscob2b.local:9001"
intHost = "b2bint-000-int-000.lsco-b2b.com:9001"
qaHost = "b2bqa-000-web-000.lsco-b2b.com"


baseUrl = "http://" + localHost + "/lscob2bstorefront"

environments {
    integration {
        baseUrl = "http://" + intHost + "/lscob2bstorefront"
    }

    qa {
        baseUrl = "http://" + qaHost
    }
}

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

autoClearCookies = true
