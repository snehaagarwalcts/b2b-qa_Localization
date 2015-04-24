package lscob2b.pages

import lscob2b.modules.MasterTemplate
import geb.Page

class UploadOrderPage extends Page{

	static url = "/order/upload"

	static at = { waitFor { title == "Upload Order | LSCO B2B Site" | title == "DE_Upload Order | LSCO B2B Site"} }

	static content = { 
		
		masterTemplate {module MasterTemplate} 
		
		downloadFileTitle { $('.optionsDownload>h2') }
		
		downloadFileLink { $('a.button.btn-txt-red') }
		
		uploadFileTitle { $('.optionsUpload>h2') }
		
		uploadFileButton { $('div.upload input.button') }
		
	}
}
