/**
 * 
 */
package org.ieee.iwson2.mfm.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ieee.iwson2.mfm.model.networkelements.Cell;

/**
 * @author darr
 */
public class MfmCellCollection {

    private final Map<String, Cell> myCellNameCellMap;
    private final List<Cell> myCells;
    private final Map<Integer, List<Cell>> myParentCellMap = new HashMap<Integer, List<Cell>>();

    public MfmCellCollection(final List<Cell> cells) {
        this.myCells = cells;
        myCellNameCellMap = new HashMap<String, Cell>();
        for (final Cell cell : cells) {
            myCellNameCellMap.put(cell.getName(), cell);
            List<Cell> siblingCells = myParentCellMap.get(cell.getParentId());
            if (siblingCells == null) {
                siblingCells = new ArrayList<Cell>();
                myParentCellMap.put(cell.getParentId(), siblingCells);
            }
            siblingCells.add(cell);
        }
    }

    public List<Cell> getCells() {
        return myCells;
    }

    public Map<Integer, List<Cell>> getParentCellMap() {
        return myParentCellMap;
    }

}
