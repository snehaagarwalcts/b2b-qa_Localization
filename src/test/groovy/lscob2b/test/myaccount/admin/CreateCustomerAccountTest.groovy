package lscob2b.test.myaccount.admin;

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage

public class CreateCustomerAccountTest extends GebReportingSpec {

	def setupSpec() {
		to LoginPage
		login (administrator)
		at HomePage
	}

	def "Create a user"(){

		when: "Open manage users page"

		masterTemplate.selectManageUsers()

		then: "We should be at manage users page"

		at ManageUsersPage

		when: "Open create new user page"

		clickCreateNewUsersLink()

		then: "We should be at create user page"

		at CreateUserPage

		when: "Fill in the form and submit it"
		def firstName = "serkan"
		def lastName = "erdur"
		def email = "serkan@test.tst"
		def firstTitle = selectFirstTitleOption()
		def defaultDeliveryAddr = selectDefaultDeliveryAddrOption()
		setFirstName(firstName)
		setLastName(lastName)
		setEmail(email)
		selectAllRoles()
		submit()

		then: "Should be at customer created page"
		at CustomerCreatedPage
		//TODO check if everything created as entered

	}
}
