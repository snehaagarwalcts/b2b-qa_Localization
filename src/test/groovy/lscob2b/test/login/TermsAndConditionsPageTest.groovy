package lscob2b.test.login

import de.hybris.geb.page.hac.console.ImpexImportPage
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.pages.TermsAndConditionPage
import lscob2b.test.data.PropertProvider

class TermsAndConditionsPageTest extends PropertProvider{		def "load impex [/impex/UpdateUsers.impex]"() {		setup:			browser.go(browser.config.rawConfig.hacUrl)			at de.hybris.geb.page.hac.LoginPage					doLogin(browser.config.rawConfig.hacUsername, browser.config.rawConfig.hacPassword)			at de.hybris.geb.page.hac.HomePage					when: "at HAC home page"			at de.hybris.geb.page.hac.HomePage					and: "go to Console>ImpexImport page"			browser.go(browser.config.rawConfig.hacUrl + "console/impex/import")				and: "at ImpexImport page"			waitFor { ImpexImportPage}			at ImpexImportPage				and: "load impex in HAC"			setLegacyMode(true)			importTextScript(getClass().getResource('/impex/Users.impex').text)					then: "check import result"			checkNotification()					cleanup:			menu.logout()	}	def "verify translations of TemsAndConditions page - For 1st time user login"(){		setup:
		PageHelper.gotoPageLogout(browser, baseUrl)		to LoginPage		when: "at login page"		at LoginPage		and: "do login"		login(user)		and: "at terms and conditions page"		at TermsAndConditionPage
		
		then: "verify translations of TermsAndConditionPage"
		verifyTrueContains(masterTemplate.mainContainerLabel.text(), expectedValue("login.agreement.terms").toUpperCase(),"use contains()")
		verifyTrue(headerMessage.text(), expectedValue("login.agreement.infoMsg").toUpperCase())
		verifyTrue(disagree.text(), expectedValue("login.agreement.dontagree").toUpperCase())
		verifyTrue(agree.text(), expectedValue("login.agreement.agree").toUpperCase())
//		for(msg in message){
//			verifyTrueContains(msg.text().toLowerCase(), expectedValue("login.agreement.msg").toLowerCase(), "use contains()")
//		}		and: "Disgree to terms and conditions"		disagreeLinkClick()		then: "verify translations of disagree message"
		verifyTrue(headerMessage.text(), expectedValue("login.agreement.errorMsg").toUpperCase())		verifyTestFailedOrPassed()				where:		user = UserHelper.getTermsAndConditionUser()	}
	
	def "verify translations of TemsAndConditions page - from Footer Links"(){
		setup:
		PageHelper.gotoPageLogout(browser, baseUrl)
		to LoginPage
		at LoginPage
		login(user)
		
		when: "at Home Page"
		at HomePage
		
		then: "click on TermsAndConditions link from footer"
		masterTemplate.termsAndConditionsLink.click()
		
		when: "at terms and conditions page"
		at TermsAndConditionPage
		
		then: "verify translations of TermsAndConditionPage"
		verifyTrue(masterTemplate.mainContainerLabel.text(), expectedValue("login.agreement.terms").toUpperCase())
		//verifyTrue(title.text(), expectedValue("login.agreement.terms").toUpperCase())
//		for(msg in message){
//			verifyTrueContains(msg.text().toLowerCase(), expectedValue("login.agreement.msg").toLowerCase(), "use contains()")
//		}
		verifyTestFailedOrPassed()
		
		where:
		user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
}
