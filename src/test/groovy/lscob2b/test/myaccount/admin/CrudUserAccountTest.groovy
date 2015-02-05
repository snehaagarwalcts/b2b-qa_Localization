package lscob2b.test.myaccount.admin;

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.admin.CreateUserPage
import lscob2b.pages.MyAccount.admin.EditUserDetailsPage
import lscob2b.pages.MyAccount.admin.ManageUsersPage
import lscob2b.pages.MyAccount.admin.UpdateUserConfirmationPage
import lscob2b.pages.MyAccount.admin.ViewUserDetailsPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import lscob2b.test.data.User
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise


@Stepwise
class CrudUserAccountTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
		
		to LoginPage
		login (TestDataCatalog.getAnAdminUser())
		at HomePage
	}

	@Shared
	User createdUser
	
	def "[C] Go to create new user page"(){
		setup: 
			masterTemplate.selectManageUsers()

		when: "At ManageUsers page"
			at ManageUsersPage
			
		and: "Click create a new user"
			buttonCreateNewUser.click()

		then: "At CreateUser page"
			at CreateUserPage
			
	}

	def "[C] Create new user "() {
		setup:
			createdUser = createTemporaryUser()
		
		when: "At CreateUser page"
			at CreateUserPage
		
		and: "Prepare form"
			userDetails.setUser(createdUser)
			
		and: "Submit form"
			userDetails.saveButton.click()
			
		then: "At ViewUserDetail page" 	
			at ViewUserDetailsPage
		
		and: "Check message"
			waitFor { noteMessage.displayed }				
	}
	
	def "[R] Read created user"() {
		when: "At ViewUserDetail page" 	
			at ViewUserDetailsPage
		
		then: "Compare user data"
			createdUser.title == userDetails.getUser().title
			createdUser.name == userDetails.getUser().name
			createdUser.surname == userDetails.getUser().surname
			createdUser.email == userDetails.getUser().email
	}
	
	def "[U] Update created user"() {
		when: "At ViewUserDetail page"
			at ViewUserDetailsPage
			
		and: "click on edit"
			userDetails.editUserButton.click()
			
		then: "At EditUserDetails page"
			at EditUserDetailsPage
			
		when: "At EditUserDetails page"
			at EditUserDetailsPage
			
		and: "Update user details"
			createdUser.name = "updateUsername"
			createdUser.surname = "updateSurname"
			waitFor { userDetails.customerForm.displayed }
			userDetails.setUser(createdUser)
		
		and: "Submit form"
			userDetails.saveButton.click()
			
		then: "At ViewUserDetail page"
			at ViewUserDetailsPage
		
		and: "Check message"
			waitFor { noteMessage.displayed }
			
	}
		
	def "[R] Read updated user"() {
		when: "At ViewUserDetail page"
			at ViewUserDetailsPage
		
		then: "Compare user data"
			createdUser.title == userDetails.getUser().title
			createdUser.name == userDetails.getUser().name
			createdUser.surname == userDetails.getUser().surname
			createdUser.email == userDetails.getUser().email
	}
	
	def "[D] Disable updated user"() {
		when: "At ViewUserDetail page"
			at ViewUserDetailsPage
		
		and: "Disable user"	
			userDetails.disableUserButton.click()
		
		then: "At ViewUserDetails page"
			at ViewUserDetailsPage

		and: "Check Message"	
			waitFor { noteMessage.displayed }
	}
	
	def User createTemporaryUser() {
		User user = new User();
		user.email = UUID.randomUUID().toString() + "@test.tst"
		user.title = "Mr."
		user.name = "userName"
		user.surname = "userSurname"
		user.groups = []
		user.address = ""
		user
	}
	
}
