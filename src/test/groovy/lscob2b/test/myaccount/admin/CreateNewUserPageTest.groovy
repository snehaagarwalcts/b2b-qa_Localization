package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.CreateUserPage
import lscob2b.test.data.PropertProvider

class CreateNewUserPageTest extends PropertProvider {
	
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
		verifyTrue(masterTemplate.mainContainerLabel.text(),expectedValue("text.company.manageUsers.adduser.title").toUpperCase())
		verifyTrue(masterTemplate.introContainerLabel.text(), expectedValue("text.mycompany.user.createForm"))
		verifyTrue(masterTemplate.requiredMessageText.text(),expectedValue("address.required"))
		verifyTrue(AddUserLabels(0).text(),expectedValue("user.title").toUpperCase())		
		verifyTrue(AddUserLabels(1).text(),expectedValue("text.company.manageUser.user.firstName").toUpperCase())
		verifyTrue(AddUserLabels(2).text(),expectedValue("text.company.manageUser.user.lastName").toUpperCase())
		verifyTrue(AddUserLabels(3).text(),expectedValue("text.company.manage.units.user.email").toUpperCase())
		verifyTrue(AddUserLabels(4).text(),expectedValue("text.company.user.default.shipping.address").toUpperCase())
		verifyTrue(AddUserLabels(5).text(),expectedValue("text.company.manageUser.user.roles").toUpperCase())		
		verifyTrue(roleUserManagement.text(), expectedValue("b2busergroup.b2badmingroup.name"))
		verifyTrue(rolePurchasing.text(), expectedValue("b2busergroup.b2bcustomergroup.name"))
		verifyTrue(roleFinance.text(), expectedValue("b2busergroup.b2bfinancegroup.name"))
		verifyTrue(cancelButton.text()- ~/&/, expectedValue("text.company.manage.unit.address.cancelButton").toUpperCase())
		verifyTrue(saveButton.text(), expectedValue("text.account.user.saveUpdates").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
