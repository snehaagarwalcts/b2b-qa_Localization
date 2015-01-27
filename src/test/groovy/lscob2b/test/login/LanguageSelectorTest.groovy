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
//		langSelector = 'sv'
		langSelector = 'en'
	}
	
	def "Check languages are present" () {
		setup: "Define languages"

		def languages = ["en", "sv", "ru", "cs", "hu", "pt", "de", "fr", "fi", "no", "da", "pl", "el", "tr", "nl", "it", "es" ]

		when: "At login page"

		at LoginPage

		then: "All available languages should be in language select box"

		langSelector
		langSelectorValueCount == languages.size()

		languages.each {
			assert langSelectorFor(it)
		}
	}

	def "Change language on login page"() {
		when: "Selecting a language"

		langSelector = lang

		then: "Page content changes language"
		waitFor {
			pageheading == greetingValue
		}
		
		and: "Check browser url"
		waitFor {
			browser.currentUrl.contains(urlPart)
		}
		
		where:
		//TODO change sv greetingValue once the language is implemented
		//FIXME Put in JSON
		lang 	| greetingValue			|	urlPart
		"sv"	| "WELCOME"	|	"/sv/"   
		"en"	| "WELCOME"	|	"/en/"
		"ru"	| "WELCOME"	|	"/ru/"
		"cs"	| "WELCOME"	|	"/cs/"
		"hu"	| "WELCOME"	|	"/hu/"
		"pt"	| "WELCOME"	|	"/pt/"
		"de"	| "WELCOME"	|	"/de/"
		"es"	| "WELCOME"	|	"/es/"
		"it"	| "WELCOME"	|	"/it/"
		"nl"	| "WELCOME"	|	"/nl/"
		"tr"	| "WELCOME"	|	"/tr/"
		"el"	| "WELCOME"	|	"/el/"
		"pl"	| "WELCOME"	|	"/pl/"
		"da"	| "WELCOME"	|	"/da/"
		"no"	| "WELCOME"	|	"/no/"
		"fi"	| "WELCOME"	|	"/fi/"
		"fr"	| "WELCOME"	|	"/fr/"
	}

}
