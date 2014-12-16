package lscob2b.test.QuickOrder

import geb.spock.GebReportingSpec
import lscob2b.TestConstants
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.QuickOrder.QuickOrderPage
import spock.lang.Stepwise
import static lscob2b.TestConstants.*

@Stepwise
class QuickOrderTest extends GebReportingSpec {

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def login(String username) {
		doLogin(username, defaultPassword)
	}

	def "Quick Order link is accessible"() {

		when: "logged in as any user"

		login(user)

		then: "Quick Order link is available"
		at HomePage

		masterTemplate.clickQuickOrder()

		at QuickOrderPage

		where:

		user << [levisUser, dockersUser]    // TODO change for user roles
	}
	
}
