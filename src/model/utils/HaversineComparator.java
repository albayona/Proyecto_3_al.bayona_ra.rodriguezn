package model.utils;



import model.vo.TripleCostEdge;

import java.util.Comparator;

public class HaversineComparator implements Comparator<TripleCostEdge> {

    @Override
    public int compare(TripleCostEdge o1, TripleCostEdge o2) {
        if (o1.weight(0) < o2.weight(0) ) return -1;
        if (o1.weight(0)  > o2.weight(0) ) return 1;
        else return 0;
    }
}
