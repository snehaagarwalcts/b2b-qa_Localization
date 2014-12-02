package lscob2b.test.login

import lscob2b.modules.MasterTemplate
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage;
import spock.lang.Stepwise;
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import org.openqa.selenium.support.ui.Select


class LanguageSelectorTest extends GebReportingSpec {

	String defaultPassword = "12341234"
	String levisUser = "robert.moris@monsoon.com"
	String dockersUser = "deno.rota@fashion-world.com"
	String multibrandUser = "joseph.hall@city-apparel.com"

	def setup(){

		to LoginPage
	}

	def "Log in with Swedish as your default language"(){

		when: "Select Swedish as language"
		langSelector = 'sv'

		then: "Check that login page language is Swedish"
		pageheading == "Återkommande kund"

	}
	
}
