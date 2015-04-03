package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.EditUserDetailsPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
import lscob2b.test.data.PropertProviderTest

class EditUserDetailsPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify EditUserDetails Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on ManageUsers Link "
		clickOnManageUsersLink()
		
		when: "at ManageUsersPage"
		at ManageUsersPage
		
		then:"click on any of the existing users"
		clickOnSelectFirstUserLink()
		
		when: "at ViewUserDetailsPage"
		at ViewUserDetailsPage
		
		then: "click on Edit User button"
		userDetails.clickEditUser()
		
		when: "at EditUserDetailsPage"
		at EditUserDetailsPage
		
		then: "Verify translations in EditUserDetailsPage"
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.company.manageUsers.edituser.title").toUpperCase()
		//assert masterTemplate.introContainerLabel.text() == expectedValue("text.mycompany.user.updateForm")	
		assert titleDropdownLabels(0).text() == expectedValue("form.select.empty")
		assert titleDropdownLabels(1).text() == expectedValue("text.company.user.mr.name")+ "."
		assert titleDropdownLabels(2).text() == expectedValue("text.company.user.ms.name")+ "."
		assert EditUserDetailsLabel(0).text() == expectedValue("user.title").toUpperCase()
		assert EditUserDetailsLabel(1).text() == expectedValue("text.company.manageUser.user.firstName").toUpperCase()
		assert EditUserDetailsLabel(2).text() == expectedValue("text.company.manageUser.user.lastName").toUpperCase()
		assert EditUserDetailsLabel(3).text() == expectedValue("text.company.manage.units.user.email").toUpperCase() //FAILED
		assert EditUserDetailsLabel(4).text() == expectedValue("text.company.user.default.shipping.address").toUpperCase()
		assert EditUserDetailsLabel(5).text() == expectedValue("text.company.manage.units.user.roles").toUpperCase()		
		assert roleUserManagement.text()== expectedValue("b2busergroup.b2badmingroup.name")
		assert rolePurchasing.text()== expectedValue("b2busergroup.b2bcustomergroup.name")
		assert roleFinance.text()== expectedValue("b2busergroup.b2bfinancegroup.name")				
		assert cancelButton.text()- ~/&/ == expectedValue("text.company.manage.unit.address.cancelButton").toUpperCase()
		assert userDetails.saveButton.text() == expectedValue("text.account.user.saveUpdates").toUpperCase()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}

}
