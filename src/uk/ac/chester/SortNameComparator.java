//this comparator sorts disks by name (A to Z)
package uk.ac.chester;

import java.util.Comparator;

public class SortNameComparator implements Comparator<DiskProperty> {
    @Override
    public int compare(DiskProperty o1, DiskProperty o2) {
        return o1.getDiskTitle().compareTo(o2.getDiskTitle());
    }
}
