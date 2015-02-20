package lscob2b.test.a

import geb.spock.GebReportingSpec
import spock.lang.IgnoreIf
import spock.lang.Stepwise
import de.hybris.geb.page.hac.HomePage
import de.hybris.geb.page.hac.LoginPage
import de.hybris.geb.page.hac.console.ImpexImportPage


@Stepwise
class ImpexLoadingTest extends GebReportingSpec {

	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "login at HAC"() {
		setup:
			browser.go(browser.config.rawConfig.hacUrl)
			
		when: "at HAC login page"
			at LoginPage
			
		and: "do login"	
			doLogin(browser.config.rawConfig.hacUsername, browser.config.rawConfig.hacPassword)
			
		then: "at HAC home page"
			at HomePage	
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "load impex [/impex/Users.impex]"() {
		setup:
			browser.go(browser.config.rawConfig.hacUrl)
			
		when: "at HAC home page"
			at HomePage
			
		and: "go to Console>ImpexImport page"
			browser.go(browser.config.rawConfig.hacUrl + "console/impex/import")
		
		and: "at ImpexImport page"
			at ImpexImportPage
		
		and: "load impex in HAC"
			importTextScript(getClass().getResource('/impex/Users.impex').text)
			
		then: "check import result"
			checkNotification()
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "logout from HAC"() {
		setup:
			browser.go(browser.config.rawConfig.hacUrl)
		
		when: "at HAC home page"
			at HomePage
	
		and: "logout from HAC"	
			menu.logout.click()
			
		and: "go to HAC home page"	
			browser.go(browser.config.rawConfig.hacUrl)
			
		then: "at login page"
			at LoginPage	
			
	}
}
