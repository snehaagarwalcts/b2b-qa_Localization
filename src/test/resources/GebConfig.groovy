package test.resources

import geb.driver.SauceLabsDriverFactory
import org.openqa.selenium.firefox.FirefoxDriver

def sysProps = System.getProperties()

def sauceBrowser = sysProps.get "geb.sauce.browser"

if (sauceBrowser) {
    driver = {
        def env = System.getenv()
        def username = sysProps.get "geb.sauce.user"
        def accessKey = sysProps.get  "geb.sauce.access.key"
        def platform = env.get "SELENIUM_PLATFORM"
        def version = env.get "SELENIUM_VERSION"
        def browserName = env.get "SELENIUM_BROWSER"
        def capabilities = [platform: platform, version: version, browserName: browserName]
        new SauceLabsDriverFactory().create("", username, accessKey, capabilities)
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
