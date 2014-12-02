//Created by I065970 on 12/2/14
package lscob2b.test.login

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage
import spock.lang.Stepwise

@Stepwise

class MyAccountTest extends GebReportingSpec {
	String defaultPassword = "12341234"
	String dockersUser = "deno.rota@fashion-world.com"
	String levisUser = "robert.moris@monsoon.com"	
	String multibrandUser = "joseph.hall@city-apparel.com"
	
	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}


def login(String username) {
	doLogin(username, defaultPassword)
}

def "Login as Dockers and go to My Account"() {
	when: "Logging in as a Dockers only customer"

	login(dockersUser)

	then: "We should see Dockers logo"

	at HomePage
	
	when: "at home page"
	
	masterTemplate.doMyaccount()
				
	then: "we should see my account"

	at MyAccountPage
	
	!themeForm

	logoAltTag == "Levis Strauss & Company" // TODO This text should change depending on Levi's or Dockers.
	// As of now it's always Levi's even for Dockers customer

  }

def "Login as levis user and go to My Account"() {
	when: "Logging in as a Levis only customer"

	login(levisUser)

	then: "We should see Levis logo"

	at HomePage
	
	when: "at home page"
	
	masterTemplate.doMyaccount()
				
	then: "we should see my account"

	at MyAccountPage
	

	!themeForm

	logoAltTag == "Levis Strauss & Company" // TODO This text should change depending on Levi's or Dockers.
	// As of now it's always Levi's even for Dockers customer

  }

def "Login as mutilbrand and go to My Account"() {
	when: "Logging in as a Multibrand only customer"

	login(multibrandUser)

	then: "We should see Levi's logo and theme selector"

	at HomePage
	
	when: "at home page"
	
	masterTemplate.doMyaccount()
				
	then: "we should see my account"

	at MyAccountPage
	
  }

}
