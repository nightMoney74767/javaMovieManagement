//this comparator sorts disks by price (least expensive first)
package uk.ac.chester;

import java.util.Comparator;

public class SortPriceComparator implements Comparator<DiskProperty>{
    @Override
    public int compare(DiskProperty o1, DiskProperty o2) {
        //adapted from Stack Overflow (arshajii, 2012)
        return Double.compare(o1.getDiskPrice(), o2.getDiskPrice());
        //end of adapted code
    }
}
