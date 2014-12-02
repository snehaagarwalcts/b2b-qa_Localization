package lscob2b.test.login

import lscob2b.modules.MasterTemplate
import lscob2b.pages.LoginPage;
import spock.lang.Stepwise;
import geb.spock.GebReportingSpec
import geb.spock.GebSpec

import org.openqa.selenium.support.ui.Select


class LanguageSelectorTest extends GebReportingSpec {

	def setup(){

		to LoginPage
	}

	def "Change web page language from English to Swedish"(){

		when: "Select Swedish as language"
		langSelector = 'sv'

		then: "Check that login page language is Swedish"
		pageheading == "Återkommande Kund"
	}
}
