/**
 * 
 */
package org.ieee.iwson2.mfm.algorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.ieee.iwson2.mfm.model.networkelements.Cell;

/**
 * @author Darshan
 */
public class AlgorithmLogContainer {

    private final StringBuilder myStringBuilder = new StringBuilder();

    private final StringBuilder myCellLogTemp = new StringBuilder();

    public void logIterationRepulsion(final int interationId, final int rSystem) {
        myStringBuilder.append("<Iteration name= \"Iteration" + interationId + "\" total-replusion=\"" + rSystem
                + "\">");
        myStringBuilder.append("\n");
        myStringBuilder.append(myCellLogTemp.toString());
        myStringBuilder.append("</Iteration>");
        myStringBuilder.append("\n");
        clearCellLogging();
    }

    public void logCellReplusion(final Cell cell, final int cellRepulsion) {
        myCellLogTemp.append("<Cell name= \"" + cell.toString() + "\" cell-replusion=\"" + cellRepulsion + "\"/>");
        myCellLogTemp.append("\n");
    }

    public void createXmlFile() {
        final File file = new File("conf/iterationLog.xml");
        try {
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("<Iterations>");
            fileWriter.write("\n");
            fileWriter.write(myStringBuilder.toString());
            fileWriter.write("</Iterations>");
            fileWriter.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    public void clearCellLogging() {
        myCellLogTemp.delete(0, myCellLogTemp.length());
    }

}
