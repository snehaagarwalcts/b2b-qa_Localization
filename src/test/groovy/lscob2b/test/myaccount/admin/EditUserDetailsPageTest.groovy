package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.EditUserDetailsPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
import lscob2b.test.data.PropertProvider

class EditUserDetailsPageTest extends PropertProvider {
	
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
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.company.manageUsers.edituser.title").toUpperCase())
		//verifyTrue(masterTemplate.introContainerLabel.text(), expectedValue("text.mycompany.user.updateForm"))	
		verifyTrue(titleDropdownLabels(0).text(), expectedValue("form.select.empty"))
		verifyTrueContains(titleDropdownLabels(1).text(), expectedValue("text.company.user.mr.name"), "use contains()")
		verifyTrueContains(titleDropdownLabels(2).text(), expectedValue("text.company.user.ms.name"), "use contains()")
		verifyTrue(EditUserDetailsLabel(0).text(), expectedValue("user.title").toUpperCase())
		verifyTrue(EditUserDetailsLabel(1).text(), expectedValue("text.company.manageUser.user.firstName").toUpperCase())
		verifyTrue(EditUserDetailsLabel(2).text(), expectedValue("text.company.manageUser.user.lastName").toUpperCase())
		verifyTrue(EditUserDetailsLabel(3).text(), expectedValue("text.company.manage.units.user.email").toUpperCase()) //FAILED
		verifyTrue(EditUserDetailsLabel(4).text(), expectedValue("text.company.user.default.shipping.address").toUpperCase())
		verifyTrue(EditUserDetailsLabel(5).text(), expectedValue("text.company.manage.units.user.roles").toUpperCase())		
		verifyTrue(roleUserManagement.text(), expectedValue("b2busergroup.b2badmingroup.name"))
		verifyTrue(rolePurchasing.text(), expectedValue("b2busergroup.b2bcustomergroup.name"))
		verifyTrue(roleFinance.text(), expectedValue("b2busergroup.b2bfinancegroup.name"))			
		verifyTrue(cancelButton.text()- ~/&/, expectedValue("text.company.manage.unit.address.cancelButton").toUpperCase())
		verifyTrue(userDetails.saveButton.text(), expectedValue("text.account.user.saveUpdates").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}

}
