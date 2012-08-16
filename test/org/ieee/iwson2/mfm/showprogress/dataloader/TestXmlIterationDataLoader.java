package org.ieee.iwson2.mfm.showprogress.dataloader;

import java.util.List;

import org.ieee.iwson2.mfm.showprogress.model.Iteration;
import org.junit.Before;
import org.junit.Test;

public class TestXmlIterationDataLoader {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		final String testDir = "D:/userdata/mfm-simulation/conf/iterationLog.xml";
		final List<Iteration> iterations = XmlIterationDataLoader
				.loadIterationsFromLocation(testDir);

		for (final Iteration iteration : iterations) {
			System.out.println(iteration);
		}
	}
}
