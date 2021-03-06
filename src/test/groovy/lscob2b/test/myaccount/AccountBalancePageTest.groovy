package lscob2b.test.myaccount

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.AccountBalancePage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.test.data.PropertProvider

class AccountBalancePageTest extends PropertProvider {

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

	def "Verify AccountBalance Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()

		when: "at MyAccountPage"
		at MyAccountPage
		
		then: "click on AccountBalance Link "
		clickOnMyAccountBalanceLink()

		when: "at AccountBalancePage"
		at AccountBalancePage		

		then: "Verify translations at AccountBalancePage"
		verifyTrue(masterTemplate.breadCrumbActive.text(), expectedValue("text.account.accountBalance").toUpperCase())
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("text.account.accountBalance").toUpperCase())
		verifyTrue(masterTemplate.introContainerLabel.text(), expectedValue("text.account.accountBalanceIntro"))
		verifyTrue(totalBalance.text(), expectedValue("text.account.accountBalanceTotal").toUpperCase())
		verifyTrue(totalOverdue.text(), expectedValue("text.account.accountBalanceOverdue").toUpperCase())
		verifyTrue(creditLimit.text(), expectedValue("text.account.accountBalanceLimit").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
