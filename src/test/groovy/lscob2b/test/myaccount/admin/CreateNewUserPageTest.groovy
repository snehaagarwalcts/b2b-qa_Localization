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
		
		assert masterTemplate.mainContainerLabel.text()==expectedValue("text.company.manageUsers.adduser.title").toUpperCase()
		assert masterTemplate.introContainerLabel.text()== expectedValue("text.mycompany.user.createForm")
		//assert masterTemplate.requiredMessageText.text()==expectedValue("")
		assert AddUserLabels(0).text()==expectedValue("text.company.user.title").toUpperCase()		
		assert AddUserLabels(1).text()==expectedValue("text.company.manageUser.user.firstName").toUpperCase()
		assert AddUserLabels(2).text()==expectedValue("text.company.manageUser.user.lastName").toUpperCase()
		//assert AddUserLabels(3).text()==expectedValue("")
		assert AddUserLabels(4).text()==expectedValue("text.company.user.default.shipping.address").toUpperCase()
		assert AddUserLabels(5).text()==expectedValue("text.company.manageUser.user.roles").toUpperCase()
		
		assert roleUserManagement.text()== expectedValue("b2busergroup.b2badmingroup.name")
		assert rolePurchasing.text()== expectedValue("b2busergroup.b2bcustomergroup.name")
		assert roleFinance.text()== expectedValue("b2busergroup.b2bfinancegroup.name")
		assert cancelButton.text() == expectedValue("")
		 
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
