package test.resources

import geb.driver.SauceLabsDriverFactory
import lscob2b.util.geb.DummyReporter

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.firefox.FirefoxDriver


def sysProps = System.getProperties()

/**
 * Env Configuration
 */
def platform = env.get "SELENIUM_PLATFORM"
def version = env.get "SELENIUM_VERSION"
def browserName = env.get "SELENIUM_BROWSER"

//Extra check for profile management (see pom.xml)
if(platform == null || platform == "") {
	platform = sysProps.get "geb.platform"
}
if(version == null || version == "") {
	version = sysProps.get "geb.version"
}
if(browserName == null || browserName == "") {
	browserName = sysProps.get "geb.browser"
}


/**
 * Target Configuration
 */

/* Default URL */
baseUrl = "http://lscob2b.local:9001/lscob2bstorefront/"

// when system property 'geb.env' is set to 'integration000'
environments {
	local {
		baseUrl = "http://lscob2b.local:9001/lscob2bstorefront/"
	}
	integration000 {
		baseUrl = "http://b2bint-000-store-000.lsco-b2b.com:9001/lscob2bstorefront/"
	}
	integration001 {
		baseUrl = "http://b2bint-001-store-000.lsco-b2b.com:9001/lscob2bstorefront/"
	}
	qa000 {
		baseUrl = "http://b2bqa-000-web-000.lsco-b2b.com/"
	}
	qa001 {
		 baseUrl = "http://b2bqa-001-web-000.lsco-b2b.com/"
	}
}

/**
 * Browser Configuration
 */
def sauceBrowser = sysProps.get "geb.sauce.browser" 
if (sauceBrowser && sauceBrowser != "false") {
	driver = {
		def env = System.getenv()
		def username = sysProps.get "geb.sauce.user"
		def accessKey = sysProps.get  "geb.sauce.access.key"
		def capabilities = [
			platform: platform, 
			version: version, 
			browserName: browserName, 
			'max-duration': 7200, //2h
			name: "" + platform + "-" + browserName + "-" + version, 
			"record-video": true, 
			"video-upload-on-pass": false,
			"record-screenshots": false
		] 
		new SauceLabsDriverFactory().create("", username, accessKey, capabilities)
	}
} else {

	//Create Driver
	if(browserName == "safari") {
		
		driver = { new SafariDriver() }
		
	} else if(browserName == "chrome") {
		
		driver = { new ChromeDriver() }
		
	} else {
		
		driver = { new FirefoxDriver() }	//Default Driver!
		
	}	
	
} 

/**
 * CONFIGURATION
 */
autoClearCookies = true


/**
 * REPORT
 */
reporter = new DummyReporter() // Fastest Reporter :)
reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

