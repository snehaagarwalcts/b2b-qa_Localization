package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage

/**
 * Created by i303936 on 12/3/14.
 */
class MyAccountBreadcrumbTest extends GebReportingSpec {

    def setupSpec() {
        to LoginPage
        doLogin("robert.moris@monsoon.com", "12341234")
    }

    def "Check breadcrumb trail"() {
        when: "At my account page"

        to MyAccountPage

        then: "Home and my account breadcrumbs are present"

        masterTemplate.breadcrumbExistsByUrl("/lscob2bstorefront/lscob2b/en/")
        masterTemplate.breadcrumbExistsByUrl("/lscob2bstorefront/lscob2b/en/my-account")
        masterTemplate.breadcrumbIsActiveByUrl("/lscob2bstorefront/lscob2b/en/my-account")
    }
}
