package org.ieee.iwson2.mfm;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ieee.iwson2.mfm.Application.MFMSimulatorApplication;
import org.ieee.iwson2.mfm.Application.MFMSimulatorApplicationImpl;

/*
 Copyright 2012 mfm-simulation, contributors acknowledged
 by mfm-simulation project owner.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 contact : premnathkn@gmail.com
*/
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
