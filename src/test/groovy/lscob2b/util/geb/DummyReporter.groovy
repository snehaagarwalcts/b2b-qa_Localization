package lscob2b.util.geb

import geb.report.ReportState
import geb.report.Reporter;
import geb.report.ReportingListener;

/**
 * Fastest Reporter implementation ever! Geb is by default using a CompositeReporter unless configured otherwise.
 * That composite reporter will be created from a ScreenshotReporter (takes a PNG screenshot) and PageSourceReporter (dumps the current DOM state as HTML).
 * These operations are very slow and geb is taking a report after every test (and every iteration for data driven tests) regardless of test being successful or not.
 * fyi: "reportOnTestFailureOnly" setting is currently not working for Spock (working only for TestNG according to the documentation: http://www.gebish.org/manual/current/configuration.html#reporter) 
 * 
 * @author i310850
 */
class DummyReporter implements Reporter {

	@Override
	public void writeReport(ReportState reportState) {
		// We are doing nothing
	}

	@Override
	public void addListener(ReportingListener listener) {
		// we do not care about listeners
	}

}
