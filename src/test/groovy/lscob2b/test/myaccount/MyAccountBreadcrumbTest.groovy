package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage
import spock.lang.Stepwise

import static lscob2b.TestConstants.getDefaultPassword
import static lscob2b.TestConstants.getLevisUser

@Stepwise
/**
 * Created by i303936 on 12/3/14 , added by I065970 on 12/5/14
 */
class MyAccountBreadcrumbTest extends GebReportingSpec {

    def setup() {
        to LoginPage
        doLogin(levisUser, defaultPassword)
        at HomePage
        to MyAccountPage
        at MyAccountPage
    }

    def "Check breadcrumbs on My account page"() { // tests the login itself without worrying about rights
        when: "At My Account page"
        then: "There should be 2 breadcrumbs"
        and: "1 should be home, the other should be 'my account'"
        and: "The text should be correct"

        masterTemplate.breadcrumbs.size() == 2

        def homeBC = masterTemplate.getBreadcrumbByUrl("/")

        homeBC
        homeBC.text().toUpperCase() == 'HOME'

        masterTemplate.isBreadcrumbActive("My Account")

    }

}
