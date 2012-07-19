package org.ieee.iwson2.mfm;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ieee.iwson2.mfm.Application.MFMSimulatorApplication;
import org.ieee.iwson2.mfm.Application.MFMSimulatorApplicationImpl;

/**
 * 
 * Main class for Magnetic Field Model
 * @author prem
 *
 */
public class myMFM {

	static Logger logger = Logger.getLogger(myMFM.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize logging, in future use Aspectj
		init_myMFM(args);
		// Create the application
		logger.debug("Start MFM Simulator...");
		MFMSimulatorApplication myMFM = MFMSimulatorApplicationImpl.getMFMApplication();
		myMFM.start();
		// Do the exit formalities
		exit_myMFM();
	}

	private static void exit_myMFM() {
		logger.debug("All is well... :)...");
	}

	private static void init_myMFM(String[] args) {
		// BasicConfigurator replaced with PropertyConfigurator.
		logger.debug("Initializing...");
		String[] log_property = new String[1];
		if (null == args || (args.length <= 0)) {
			log_property[0] = "./conf/MFMLogsettings.props";
		} else {
			log_property = args;
		}
		PropertyConfigurator.configure(log_property[0]);
	}
}
