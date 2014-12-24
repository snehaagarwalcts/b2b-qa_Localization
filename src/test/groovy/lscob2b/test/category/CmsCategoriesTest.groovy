package lscob2b.test.category;

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.login.LoginFailureTest;
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;
import static lscob2b.TestConstants.*

@Stepwise
public class CmsCategoriesTest extends GebReportingSpec {

	def setupSpec() {
		to LoginPage
		login (multibrandUser)
		at HomePage
	}

	def "All cms categories should be displayed in the navigation menu"(){
		
		when: "Switch correct brand"
		if (masterTemplate.brandSelectionInput.value() == brand){
			masterTemplate.switchBrand()
		}
		then: "we should be at correct brand" //could not find a good way to confirm this

		when: "Open category menu"
		masterTemplate.util.mouseOverHrefEndsWith(parentCategory)

		then: "Check category exists in menu"
		!$("a", href: endsWith(subCategory)).empty

		where:
		brand		|	parentCategory					|	subCategory
		"levis"		|	"Levis_151_Male"				|	"Levis_151_Male_Bottoms_Long%20Bottoms"
		"levis"		|	"Levis_151_Male"				|	"Levis_151_Male_Bottoms_Short%20Bottoms"
		"levis"		|	"Levis_151_Female"				|	"Levis_151_Female_Bottoms_Long%20Bottoms"
		"levis"		|	"Levis_151_Female"				|	"Levis_151_Female_Bottoms_Skirts"
		"dockers"	|	"Dockers_151_Male_Accessories"	|	"Dockers_151_Male_Accessories_Belts"
		"dockers"	|	"Dockers_151_Male_Accessories"	|	"Dockers_151_Male_Accessories_Muffler_Scarf"
	}
}
