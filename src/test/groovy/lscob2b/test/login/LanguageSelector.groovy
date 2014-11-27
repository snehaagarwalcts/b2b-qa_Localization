package lscob2b.test.login

import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage;
import spock.lang.Stepwise;
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import org.openqa.selenium.support.ui.Select


class LanguageSelectorTest extends GebReportingSpec
{
	
	String defaultPassword = "12341234"
	String levisUser = "robert.moris@monsoon.com"
	String dockersUser = "deno.rota@fashion-world.com"
	String multibrandUser = "joseph.hall@city-apparel.com"
	
	def setup(){
		
		to LoginPage
		
		}
	
	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def "Log in with Swedish as your default language"(){
		
		when: "Selecting a different language dropdown selection list should appear"
		langSelector.click()
		
		then: "Select Swedish as language"
		langSelector.value('sv')//.click()
		
		//Error Element not found in the cache - perhaps the page has changed since it was looked up(..)..reason is that once SV is selected as a value the page refreshed
		//and the 'sv' element is not loaded back.
		
		//This makes the browser wait for 10 seconds for value 'sv' to load before preceding with rest of the test.
		waitFor(10){
			langSelector.value('sv')
		}
		
		and: "Log in"
		login (multibrandUser)
		
		then: "We should arrive at the homepage"
				at HomePage
	}

	def login(String username) {
		doLogin(username, defaultPassword)
	}
}
