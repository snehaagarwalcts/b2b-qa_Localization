package lscob2b.test.login

import lscob2b.modules.MasterTemplate
import lscob2b.pages.LoginPage;
import spock.lang.Stepwise;
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

import org.openqa.selenium.support.ui.Select

@Stepwise
class LanguageSelectorTest extends GebReportingSpec {

	def setupSpec() {
		to LoginPage

		// force redirect to ensure URL is correct
		langSelector = 'sv'
		langSelector = 'en'
	}

	def "Check languages are present" () {
		setup: "Define languages"

		def languages = ["en", "sv"]

		when: "At login page"

		at LoginPage

		then: "All available languages should be in language select box"

		langSelector

		langSelectorValues.size() == languages.size()

		languages.each {
			langSelectorValues.filter(value: it)
		}
	}

	def "Change language on login page"() {
		when: "Selecting a language"

		langSelector = lang

		then: "Page content changes language"
		and: "URL changes"

		pageheading == greetingValue
		browser.currentUrl.contains(urlPart)

		where:

		lang 	| greetingValue			|	urlPart
		"sv"	| "Returning Customer"	|	"/sv/"  //TODO change sv greetingValue once the language is implemented 
		"en"	| "Returning Customer"	|	"/en/"
	}

}
