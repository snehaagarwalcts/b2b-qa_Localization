package lscob2b.test.myaccount.admin;

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.admin.CreateUserPage;
import lscob2b.pages.MyAccount.admin.CustomerCreationConfirmationPage;
import lscob2b.pages.MyAccount.admin.ManageUsersPage;

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
		def email = UUID.randomUUID().toString() + "@test.tst"
		def title = selectFirstTitleOption()
		def defaultDeliveryAddr = selectDefaultDeliveryAddrOption()
		setFirstName(firstName)
		setLastName(lastName)
		setEmail(email)
		selectAllRoles()
		submit()

		then: "Should be at customer created page"

		at CustomerCreationConfirmationPage
		titleText == title
		firstNameText == firstName
		lastNameText == lastName
		emailText == email
		defaultDeliveryAddrText == defaultDeliveryAddr
		resetPasswordLinkUrl.endsWith("?user=${email}")
	}
}
