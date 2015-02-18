package de.hybris.geb.module.hac.console

import geb.Module
import groovy.json.StringEscapeUtils

class ImportContentModule extends Module {

	static content = {
		
		form { $("form#contentForm") }
		
		textarea { form.find("textarea#script") }
		
		clearButton { form.find("button#clearScriptContent") }
		
		validateButton { form.find("input#validate") }
			
		importButton { form.find("input", value: "Import content") }
		
	}
	
	def void setText(txt) {
		//HACK for CodeMirror JS
		txt = StringEscapeUtils.escapeJavaScript(txt)
		js.exec("CodeMirror.fromTextArea(document.getElementById('script'), {mode: 'text/x-impex', lineNumbers: true, autofocus: true, extraKeys: {'F11': function(cm) {setFullScreen(cm, !isFullScreen(cm)); }, 'Esc': function(cm) {if (isFullScreen(cm)) setFullScreen(cm, false); }, 'Ctrl-Space': 'autocomplete'} }).setValue('" + txt + "');")
	}
	
	/**
	 * This is an hack for IE&Chrome (the js from setText cover the import button)
	 */
	def void hideTextArea() {
		js.exec("document.getElementById('textarea-container').style.display='none'")
	}
	
		
}