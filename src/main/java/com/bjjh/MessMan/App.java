package com.bjjh.MessMan;

import com.bjjh.MwssMan.config.GetConfigMess;
import com.bjjh.MwssMan.conn.GetContext;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.util.Iterator;

import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        System.out.println(System.getProperty("user.dir"));

        final AtomicInteger seqNum = new AtomicInteger(0);

        System.out.println(String.format("PY%05d",seqNum.incrementAndGet()));

        GetConfigMess configMess = new GetConfigMess();
        GetContext context = new GetContext();
        SparkSession session = context.getContext().getOrCreate();

        Dataset<Row> strategies = session.read().jdbc(configMess.getDBUrl(), configMess.getTabName1(),configMess.getProp()).select(configMess.getTab1col());
        Dataset<Row> messages = session.read().jdbc(configMess.getDBUrl(), configMess.getTabName1(),configMess.getProp()).select(configMess.getTab2col());

        Iterator<Row> d1 = strategies.collectAsList().iterator();

        while (d1.hasNext()){
            String streategy = d1.next().getString(0);


        }
    }
}
