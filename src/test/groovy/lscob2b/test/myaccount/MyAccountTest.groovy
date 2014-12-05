package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.LoginPage
import lscob2b.pages.HomePage
import lscob2b.pages.MyAccountPage
import spock.lang.Stepwise

@Stepwise

//Created by I065970 on 12/2/14 and modified by I065970 on 12/5/14

class MyAccountTest extends GebReportingSpec {
	
	static String defaultPassword = "12341234"

	static String levisUser = "robert.moris@monsoon.com"
	static String dockersUser = "deno.rota@fashion-world.com"
	static String multibrandUser = "joseph.hall@city-apparel.com"
		
	def setup() {
		to LoginPage
	}
	
	   def login(String username) {
		doLogin(username, defaultPassword)
	}
	
	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def "Login and click on My account Page"() { // tests the login itself without worrying about rights
		when: "Login as selected user"
		
				login (user)
		
				then: "We should see correct logo "
		
				at HomePage
				
				when: "at my homepage"
				
				to MyAccountPage
				
				then:"at my accounts page"
			//	!themeForm
		
			//	logoAltTag == themeSpecificAltTag
		
				where:
		
				user		| themeSpecificAltTag
				levisUser	| "Levis Strauss & Company"
				dockersUser	| "Levis Strauss & Company" // TODO this should be different for Dockers, not yet implemented
				multibrandUser | "Levis Strauss & Company"
				}
	
}
