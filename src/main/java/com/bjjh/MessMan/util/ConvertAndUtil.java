package com.bjjh.MessMan.util;

import java.util.concurrent.atomic.AtomicInteger;

public class ConvertAndUtil {

    public String getAutoGenerID() {

        final AtomicInteger seqNum = new AtomicInteger(0);

        return String.format("PY%05d", seqNum.incrementAndGet());

    }

}
