package com.bjjh.MessMan.util;

import com.bjjh.MwssMan.config.GetConfigMess;
import org.apache.spark.sql.DataFrameReader;

import java.util.concurrent.atomic.AtomicInteger;

public class ConvertAndUtil {

    public String getAutoGenerID() {

        final AtomicInteger seqNum = new AtomicInteger(0);

        return String.format("PY%05d", seqNum.incrementAndGet());

    }

    public void compare(DataFrameReader reader){

        GetConfigMess getConfigMess = new GetConfigMess();


    }

}
