//this comparator sorts disks by year of release (oldest first)
package uk.ac.chester;

import java.util.Comparator;

public class SortYearComparator implements Comparator<DiskProperty> {
    @Override
    public int compare(DiskProperty o1, DiskProperty o2) {
        return o1.getDiskYear() - o2.getDiskYear();
    }
}
