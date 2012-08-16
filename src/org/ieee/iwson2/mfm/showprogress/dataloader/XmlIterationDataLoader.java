/**
 * 
 */
package org.ieee.iwson2.mfm.showprogress.dataloader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ieee.iwson2.mfm.showprogress.model.Iteration;
import org.ieee.iwson2.mfm.showprogress.model.IterationItem;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author ppradhan
 * 
 */
public class XmlIterationDataLoader {
	private static final String ITERATION_TAG = "Iteration";
	private static final String ITERATION_ITEM_TAG = "Cell";
	private static final String NAME_ATTRIBUTE_TAG = "name";
	private static final String ITERATION_ITEM_REPLUSION_TAG = "cell-replusion";
	private static final String ITERATION_REPULSION_TAG = "total-replusion";

	private XmlIterationDataLoader() {

	}

	public static List<Iteration> loadIterationsFromLocation(final String xmlDir) {
		List<Iteration> iterations = new ArrayList<Iteration>();
		final File input = new File(xmlDir);
		if (input.isDirectory()) {
			final List<String> iterationFiles = findXmlFilesInDir(xmlDir);
			for (final String iterationFile : iterationFiles) {
				iterations.addAll(loadIterationFromFile(iterationFile));
			}

		} else {
			iterations.addAll(loadIterationFromFile(xmlDir));
		}
		return iterations;
	}

	private static List<String> findXmlFilesInDir(final String dir) {
		final String xmlFilter = "xml";
		File file = new File(dir);
		String[] xmlFiles = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File arg, String name) {
				String extn = name.substring(name.length() - 3);
				return extn.equalsIgnoreCase(xmlFilter);
			}
		});
		return Arrays.asList(xmlFiles);
	}

	private static List<Iteration> loadIterationFromFile(final String fileName) {
		System.out.println(fileName);
		try {
			final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder builder = builderFactory.newDocumentBuilder();
			final Document document = builder.parse(new File(fileName));
			document.getDocumentElement().normalize();
			return loadIterationFromDocument(document);
		} catch (Exception e) {

		}
		return null;
	}

	private static List<Iteration> loadIterationFromDocument(final Document doc) {

		List<Iteration> iterations = new ArrayList<Iteration>();
		final NodeList iterationNodes = doc.getElementsByTagName(ITERATION_TAG);
		for (int i = 0; i < iterationNodes.getLength(); i++) {
			final Node iterationXmlNode = iterationNodes.item(i);
			final NamedNodeMap namedMap = iterationXmlNode.getAttributes();
			final String iterationName = getAttributeValue(namedMap,
					NAME_ATTRIBUTE_TAG);
			final String iterationRepulsion = getAttributeValue(namedMap,
					ITERATION_REPULSION_TAG);
			final Iteration iteration = new Iteration(iterationName, Double
					.valueOf(iterationRepulsion).doubleValue());
			final NodeList childNodes = iterationXmlNode.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				final Node iterationItemXmlNode = childNodes.item(j);
				final NamedNodeMap childNamedMap = iterationItemXmlNode
						.getAttributes();
				if (null != childNamedMap) {
					final String itemName = getAttributeValue(childNamedMap,
							NAME_ATTRIBUTE_TAG);
					final String itemRepulsion = getAttributeValue(
							childNamedMap, ITERATION_ITEM_REPLUSION_TAG);
					final double cost = Double.valueOf(itemRepulsion)
							.doubleValue();
					final IterationItem item = new IterationItem(itemName, cost);
					iteration.addIterationItem(item);
				}
			}
			iterations.add(iteration);
		}
		return iterations;
	}

	private static String getAttributeValue(final NamedNodeMap namedMap,
			final String attributeName) {
		final Node node = namedMap.getNamedItem(attributeName);
		return (node == null) ? "" : node.getNodeValue();
	}
}
