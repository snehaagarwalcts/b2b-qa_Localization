package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.CreateUserPage
import lscob2b.test.data.PropertProviderTest

class CreateNewUserPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify CreateNewUser Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on AddNewUsers Link "
		clickOnAddNewUsers()
		
		when: "at AddUserDetails Page"
		at CreateUserPage
		
		then: "Verify translations in AddUserDetails Page"
		//assert masterTemplate.breadCrumbActive.text() == expectedValue("text.account.profile")
		assert masterTemplate.mainContainerLabel.text()==expectedValue("text.company.manageUsers.adduser.title")
		assert userDetails.firstNameLabel.text()==expectedValue("text.company.manageUser.user.firstName")
		assert masterTemplate.requiredMessageText.text()==expectedValue("")
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
