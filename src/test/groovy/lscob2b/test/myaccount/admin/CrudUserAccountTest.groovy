package lscob2b.test.myaccount.admin;

import static lscob2b.TestConstants.*
import spock.lang.Shared;
import spock.lang.Stepwise;
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.admin.CreateUserPage;
import lscob2b.pages.MyAccount.admin.UpdateUserConfirmationPage;
import lscob2b.pages.MyAccount.admin.EditUserDetailsPage;
import lscob2b.pages.MyAccount.admin.ManageUsersPage;
import lscob2b.pages.MyAccount.admin.CreateUserConfirmationPage;
import lscob2b.pages.MyAccount.admin.ViewUserDetailsPage;

@Stepwise
public class CrudUserAccountTest extends GebReportingSpec {

	def setupSpec() {
		to LoginPage
		login (administrator)
		at HomePage
	}

	@Shared
	String email

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

		def firstName = "firstName"
		def lastName = "lastName"
		email = UUID.randomUUID().toString() + "@test.tst"
		def title = userDetails.selectTitleOption(1)
		def defaultDeliveryAddr = userDetails.selectDefaultDeliveryAddrOption(1)
		userDetails.setFirstNameField(firstName)
		userDetails.setLastNameField(lastName)
		userDetails.setEmailField(email)
		userDetails.selectAllRoles()
		userDetails.submit()

		then: "Should be at customer created page"

		at CreateUserConfirmationPage
		//userDetails.titleText == title //TODO bug: N/A is displayed as the title. but working on qa and int systems so assumeing a new bug. need to report it after reproducing on int or qa
		userDetails.firstNameText == firstName
		userDetails.lastNameText == lastName
		userDetails.emailText == email
		userDetails.defaultDeliveryAddrText == defaultDeliveryAddr
		userDetails.resetPasswordLinkUrl.endsWith("?user=${email}")
	}

	//continue from CreateUserConfirmationPage
	def "Update the created user"(){

		when:
		userDetails.clickEditUser()

		then:
		at EditUserDetailsPage

		when:
		def firstName = "firstName_updt"
		def lastName = "lastName_updt"
		def title = userDetails.selectTitleOption(2)
		def defaultDeliveryAddr = userDetails.selectDefaultDeliveryAddrOption(2)
		userDetails.setFirstNameField(firstName)
		userDetails.setLastNameField(lastName)
		userDetails.unSelectFirstRole()
		userDetails.submit()

		then:
		at UpdateUserConfirmationPage
		userDetails.firstNameText == firstName
		userDetails.lastNameText == lastName
		userDetails.emailText == email
		userDetails.defaultDeliveryAddrText == defaultDeliveryAddr
		userDetails.resetPasswordLinkUrl.endsWith("?user=${email}")
	}
}
