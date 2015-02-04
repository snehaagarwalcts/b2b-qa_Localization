package lscob2b.test.myaccount.admin;

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.admin.CreateUserConfirmationPage
import lscob2b.pages.myaccount.admin.CreateUserPage
import lscob2b.pages.myaccount.admin.EditUserDetailsPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.UpdateUserConfirmationPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
public class CrudUserAccountTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
		to LoginPage
		login (TestDataCatalog.getAnAdminUser())
		at HomePage
	}

	def cleanupSpec() {
		masterTemplate.doLogout()
	}
	
	@Shared
	String email

	def "Create a new user"(){
		setup: 
			masterTemplate.selectManageUsers()

		when: "At ManageUsers"
			at ManageUsersPage

		then: "Open create new user page"
			clickCreateNewUsersLink()

		when: "At CreateUserPage"
			at CreateUserPage
			email = UUID.randomUUID().toString() + "@test.tst"
			
		then: "Fill the form"
			def firstName = "firstName"
			def lastName = "lastName"
			userDetails.selectTitleOption(1)
			def defaultDeliveryAddr = userDetails.selectDefaultDeliveryAddrOption(1)
			userDetails.setFirstNameField(firstName)
			userDetails.setLastNameField(lastName)
			userDetails.setEmailField(email)
			userDetails.selectAllRoles()
			
		and: "Submit the form"	
			userDetails.submit()

		when: "At UserConfirmation page"
			at CreateUserConfirmationPage
		
		then: "Check user details"
			waitFor { 
			userDetails.firstNameText == firstName
			userDetails.lastNameText == lastName
			userDetails.emailText == email
			userDetails.defaultDeliveryAddrText == defaultDeliveryAddr
			}
	}

	def "Update the created user"(){
		setup:
			userDetails.clickEditUser()

		when: "At EditUserDetail page"
			at EditUserDetailsPage

		then: "Fill the form"
			def firstName = "firstName_updt"
			def lastName = "lastName_updt"
//TODO to fix in Safari			def defaultDeliveryAddr = userDetails.selectDefaultDeliveryAddrOption(2)
			waitFor { !userDetails.empty }
			userDetails.setFirstNameField(firstName)
			userDetails.setLastNameField(lastName)
			userDetails.unSelectFirstRole()

		and: "Submit Form"
			userDetails.submit()
		
		when: "At UpdateUserConfirmation page"		
			at UpdateUserConfirmationPage
		
		then: "Check user details"	
			waitFor {
				userDetails.firstNameText == firstName
				userDetails.lastNameText == lastName
				userDetails.emailText == email
//				userDetails.defaultDeliveryAddrText == defaultDeliveryAddr
			}
	}
}
