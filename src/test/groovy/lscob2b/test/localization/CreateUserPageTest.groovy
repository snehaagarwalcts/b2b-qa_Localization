package lscob2b.test.localization

import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.ProfilePage
import lscob2b.pages.myaccount.admin.CreateUserPage

class CreateUserPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify Create User Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on Add New Users Link "
		addNewUsers.click()
		
		when: "at CreateUserPage"
		at CreateUserPage
		
		then: "Verify fields in CreateUserPage" 
		assert errorMessage.text()==expectedValue("loginPage.error.accountNotFound")				
	}
}