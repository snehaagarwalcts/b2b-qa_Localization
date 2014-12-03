package lscob2b.test.login

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import spock.lang.Stepwise

@Stepwise
class LoginTest extends GebReportingSpec { 

	static String defaultPassword = "12341234"
	static String levisUser = "robert.moris@monsoon.com"
	static String dockersUser = "deno.rota@fashion-world.com"
	static String multibrandUser = "joseph.hall@city-apparel.com"

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}

	def "Login to home page"() { // tests the login itself without worrying about rights

		when: "Logging in"

		login (multibrandUser)

		then: "We should arrive at the homepage"

		at HomePage

	}

	def "Login as Levi's or Dockers customer"() {
		when: "Logging in as a single brand customer"

		login (user)

		then: "We should see correct logo logo"

		at HomePage

		!themeForm

		logoAltTag == themeSpecificAltTag

		where:

		user		| themeSpecificAltTag
		levisUser	| "Levis Strauss & Company"
		dockersUser	| "Levis Strauss & Company" // TODO this should be different for Dockers, not yet implemented

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
