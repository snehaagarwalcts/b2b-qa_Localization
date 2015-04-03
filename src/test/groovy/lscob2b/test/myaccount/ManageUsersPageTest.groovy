package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.modules.MasterTemplate

class ManageUsersPageTest extends PropertProviderTest {
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify ManageUser Page Fields"(){
	setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on ManageUsers Link"
		clickOnManageUsersLink()
		
		when: "at ManageUsersPage"
		at ManageUsersPage
	
		then: "Verify translations in ManageUsersPage"
		assert breadcrumbLink.text() == expectedValue("text.company.manageUser").toUpperCase()	
		assert masterTemplate.mainContainerLabel.text() == expectedValue("text.company.manageUser").toUpperCase()
		assert masterTemplate.introContainerLabel.text() == expectedValue("text.company.manageusers.roles.subtitle") //FAILED
		assert usersFoundLabel.text()- ~/\d+\s+/ == expectedValue("text.company.manageUser.pageAll.totalResults").toUpperCase()
		assert buttonCreateNewUser.text()- ~/ &/ == expectedValue("text.company.manageUser.button.create").toUpperCase()
		assert pageLabel.text().replaceAll("\\s+\\d+","") == expectedValue("text.company.manageUser.pageAll.currentPage").toUpperCase()
		assert nameLabel.text() == expectedValue("text.company.manage.units.user.name").toUpperCase()
		assert rolesLabel.text() == expectedValue("text.company.manageUser.roles").toUpperCase()
		assert statusLabel.text() == expectedValue("text.status").toUpperCase()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
