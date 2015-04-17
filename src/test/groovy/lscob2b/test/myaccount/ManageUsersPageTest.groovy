package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.test.data.PropertProvider
import lscob2b.modules.MasterTemplate

class ManageUsersPageTest extends PropertProvider {
	
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
		verifyTrue(breadcrumbLink.text(), expectedValue("text.company.manageUser").toUpperCase())	
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.company.manageUser").toUpperCase())
		verifyTrue(masterTemplate.introContainerLabel.text(), expectedValue("text.company.manageusers.roles.subtitle")) //FAILED
		verifyTrue(usersFoundLabel.text()- ~/(\d+,)?\d+\s+/, expectedValue("text.company.manageUser.pageAll.totalResults").toUpperCase())
		verifyTrue(buttonCreateNewUser.text()- ~/ &/, expectedValue("text.company.manageUser.button.create").toUpperCase())
		verifyTrue(pageLabel.text().replaceAll("\\s+\\d+",""), expectedValue("text.company.manageUser.pageAll.currentPage").toUpperCase())  
		verifyTrue(nameLabel.text(), expectedValue("text.company.manage.units.user.name").toUpperCase())
		verifyTrue(rolesLabel.text(), expectedValue("text.company.manageUser.roles").toUpperCase())
		verifyTrue(statusLabel.text(), expectedValue("text.status").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
