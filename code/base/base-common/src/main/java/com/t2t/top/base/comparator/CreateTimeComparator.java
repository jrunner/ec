package com.t2t.top.base.comparator;

import java.util.Comparator;

public class CreateTimeComparator implements Comparator<CreateTimeIntf> {

    @Override
    public int compare(CreateTimeIntf o1, CreateTimeIntf o2) {
        long sr = o1.getCreateTime().getTime() - o2.getCreateTime().getTime();
        if (sr > 0) {
            return 1;
        }
        if (sr == 0) {
            return 0;
        }
        return -1;
    }

}
