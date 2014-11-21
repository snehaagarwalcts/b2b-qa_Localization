package lscob2b.test

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import spock.lang.Stepwise

@Stepwise
class LoginTest extends GebReportingSpec { 

	String defaultPassword = "12341234"
	String levisUser = "robert.moris@monsoon.com"
	String dockersUser = "deno.rota@fashion-world.com"
	String multibrandUser = "joseph.hall@city-apparel.com"

	def setup() {
		to LoginPage
	}

	def cleanup() {
		logout.doLogout()
	}

	def "Login to home page"() { // tests the login itself without worrying about rights

		when: "Logging in"

		login (multibrandUser)

		then: "We should arrive at the homepage"

		at HomePage

	}

	def "Login as Levis"() {
		when: "Logging in as a Levi's only customer"

		login (levisUser)

		then: "We should see Levi's logo"

		at HomePage

		!themeForm

		logoAltTag == "Levis Strauss & Company"

	}

	def "Login as Dockers"() {
		when: "Logging in as a Dockers only customer"

		login(dockersUser)

		then: "We should see Dockers logo"

		at HomePage

		!themeForm

		logoAltTag == "Levis Strauss & Company" // TODO This text should change depending on Levi's or Dockers.
		// As of now it's always Levi's even for Dockers customer

	}


	def "Login as multi brand"() {
		when: "Logging in as a multibrand customer"

		login(multibrandUser)

		then: "We should see Levi's logo and theme selector"

		at HomePage

		// TODO currently logo is not there but when added, should check for it
		themeForm

	}

	def login(String username) {
		doLogin(username, defaultPassword)
	}
}
