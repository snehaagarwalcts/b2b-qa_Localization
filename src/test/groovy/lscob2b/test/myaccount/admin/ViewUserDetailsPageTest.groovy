package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
import lscob2b.test.data.PropertProviderTest

class ViewUserDetailsPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify ViewUserDetails Page Fields"(){
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
		
		then: "click on any existing Users Link"
		clickOnSelectFirstUserLink()
		
		when: "at ViewUserDetailsPage"
		at ViewUserDetailsPage
		
		then: "Verify translations in ViewUserDetailsPage"
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.company.manageUser.user.viewDetails").toUpperCase()
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.company.manageusers.details.subtitle")	
		assert userDetails.editUserButton.text()- ~/&/ == expectedValue("text.company.manageUser.button.edit").toUpperCase()
		assert userDetails.disableUserButton.text()- ~/&/ == expectedValue("text.company.manageusers.button.disableuser").toUpperCase()
		assert ViewUserDetailsLabel(0).text()- ~/:/ == expectedValue("user.title").toUpperCase()
		assert ViewUserDetailsLabel(1).text()- ~/:/ == expectedValue("text.company.manageUser.user.firstName").toUpperCase()
		assert ViewUserDetailsLabel(2).text() == expectedValue("text.company.manageUser.user.lastName").toUpperCase()
		assert ViewUserDetailsLabel(3).text() == expectedValue("text.company.manage.units.user.email").toUpperCase() //FAILED
		assert ViewUserDetailsLabel(4).text() == expectedValue("text.company.user.default.shipping.address").toUpperCase()
		assert ViewUserDetailsLabel(5).text()- ~/:/ == expectedValue("text.company.manage.units.user.roles").toUpperCase()		

		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
