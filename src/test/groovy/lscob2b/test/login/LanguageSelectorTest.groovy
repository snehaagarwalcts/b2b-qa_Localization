package lscob2b.test.login

import geb.spock.GebReportingSpec
import lscob2b.data.LanguageHelper
import lscob2b.data.PageHelper
import lscob2b.pages.LoginPage

class LanguageSelectorTest extends GebReportingSpec {

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
		to LoginPage
	}
	
	/**
	 * TC BB-339 Automated Test Case: When selecting a different language from the Login Page -> Language field,\
	 * the system language changes to the selected value.
	 */
	def "Check languages are present" () {
		when: "At login page"
			at LoginPage

		then: "Check Language Selector"
			langSelector.displayed
			
		and: "Check available languages"	
			langSelectorValueCount == languages.size()

		and: "Check each language"
			languages.each {
				assert langSelectorFor(it)
			}
			
		where:
			languages = LanguageHelper.getLanguages()
	}

	/**
	 * TC BB-339 Automated Test Case: When selecting a different language from the Login Page -> Language field,\
	 * the system language changes to the selected value.
	 */
	def "Change language on login page"() {
		when: "At login page"
			at LoginPage
			
		and: "Select language"
			langSelector = lang

		and: "Reload login page"
			at LoginPage
		
		then: "Check browser url"
			if(LanguageHelper.getDefaultLang() != lang) {
				waitFor {
					browser.currentUrl.contains("/" + lang + "/")
				}
			}
		
		where:
			lang << LanguageHelper.getLanguages() 
	}

}
