package test.resources

import geb.driver.SauceLabsDriverFactory
import org.openqa.selenium.firefox.FirefoxDriver

def sauceBrowser = System.getProperty("geb.sauce.browser")

if (sauceBrowser) {
    driver = {
        def username = System.getProperty("geb.sauce.user")
        def accessKey = System.getProperty("geb.sauce.access.key")
        assert username
        assert accessKey
        def platform = System.getProperty("geb.sauce.platform")
        def version = System.getProperty("geb.sauce.version")
        def browserName = System.getProperty("geb.sauce.browserName")
        def capabilities = [platform: platform, version: version, browserName: browserName]
        new SauceLabsDriverFactory().create(sauceBrowser, username, accessKey, capabilities)
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
