package lscob2b.test.myaccount.admin

import spock.lang.Stepwise
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.ViewUserDetailsPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.test.data.User

@Stepwise
class ViewUserDetailsPageTest extends PropertProviderTest {
	
	def static User user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	
	def setupSpec() {
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
		assert ViewUserDetailsLabel(0).text()- ~/:/ == expectedValue("text.company.user.title").toUpperCase()
		assert ViewUserDetailsLabel(1).text()- ~/:/ == expectedValue("text.company.manageUser.user.firstName").toUpperCase()
		assert ViewUserDetailsLabel(2).text() == expectedValue("text.company.manageUser.user.lastName").toUpperCase()
		assert ViewUserDetailsLabel(3).text() == expectedValue("text.company.manage.units.user.email").toUpperCase() //FAILED
		assert ViewUserDetailsLabel(4).text() == expectedValue("text.company.user.default.shipping.address").toUpperCase()
		assert ViewUserDetailsLabel(5).text()- ~/:/ == expectedValue("text.company.manage.units.user.roles").toUpperCase()		
	}
	
	def "Verify ViewUserDetails Page messages"(){

		when: "at ViewUserDetailsPage"
		at ViewUserDetailsPage
		
		then: "click on Disable User button"
		userDetails.clickDisableUser()
		
		and: "Verify translation of User Disabled message"
		assert masterTemplate.noteMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","") == expectedValue("text.confirmation.user.disable")
		
		and: "Verify translations of Enable User button"
		assert userDetails.enableUserButton.text()- ~/&/== expectedValue("text.company.manageusers.button.enableuser").toUpperCase()
		
		when: "click on Enable User Button"
		userDetails.clickEnableUser()
		
		then: "Verify translation of User Enabled message"
		assert masterTemplate.noteMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
		assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","") == expectedValue("text.confirmation.user.enable")
	}
	
}
