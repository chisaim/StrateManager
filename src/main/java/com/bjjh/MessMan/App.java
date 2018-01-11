package com.bjjh.MessMan;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        System.getProperty("user.dir");

        System.out.println(System.getProperty("user.dir"));

        final AtomicInteger seqNum = new AtomicInteger(0);

        System.out.println(String.format("PY%05d",seqNum.incrementAndGet()));
    }
}
