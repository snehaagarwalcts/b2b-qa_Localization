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
intHost000 = "b2bint-000-store-000.lsco-b2b.com:9001"
intHost001 = "b2bint-001-store-000.lsco-b2b.com:9001"
qaHost000 = "b2bqa-000-web-000.lsco-b2b.com"
qaHost001 = "b2bqa-001-web-000.lsco-b2b.com"

baseUrl = "http://" + localHost + "/lscob2bstorefront/"

environments {
	integration000 {
		baseUrl = "http://" + intHost000 + "/lscob2bstorefront/"
	}
	integration001 {
		baseUrl = "http://" + intHost001 + "/lscob2bstorefront/"
	}
	qa000 { 
		baseUrl = "http://" + qaHost000
	}
	qa001 {
		 baseUrl = "http://" + qaHost001 
	}
}

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

autoClearCookies = true
