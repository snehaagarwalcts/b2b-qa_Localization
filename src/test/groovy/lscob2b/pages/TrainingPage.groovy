package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

class TrainingPage extends Page{

	static url = "/training"

	static at = { waitFor { title == "Training | LSCO B2B Site" || title == "DE_Training | LSCO B2B Site"} }

	static content = { 
		
		masterTemplate {module MasterTemplate} 
		
	}
}
