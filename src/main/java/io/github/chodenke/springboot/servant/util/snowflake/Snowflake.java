/*
 * Copyright @ 2024 甘肃诚诚网络技术有限公司 All rights reserved.
 * 甘肃诚诚网络技术有限公司 专有/保密源代码，未经许可禁止任何人通过任何渠道使用、修改源代码。
 */
package io.github.chodenke.springboot.servant.util.snowflake;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>雪花id生成器</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>该类原代码来自xyz.downgoon:snowflake库，原作者downgoon。(<a href="https://github.com/downgoon/snowflake">链接</a>)</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author codeke
 * @since JDK 17
 */
class Snowflake {

    /*
     * bits allocations for timeStamp, datacenterId, workerId and sequence
     */
    private final long unusedBits = 1L;

    /**
     * 'time stamp' here is defined as the number of millisecond that have
     * elapsed since the {@link #epoch} given by users on Snowflake
     * instance initialization
     */
    private final long timestampBits = 41L;
    private final long datacenterIdBits = 5L;
    private final long workerIdBits = 5L;
    private final long sequenceBits = 12L;

    /*
     * max values of timeStamp, workerId, datacenterId and sequence
     */
    private final long maxDatacenterId = ~(-1L << datacenterIdBits); // 2^5-1
    private final long maxWorkerId = ~(-1L << workerIdBits); // 2^5-1
    private final long maxSequence = ~(-1L << sequenceBits); // 2^12-1

    /**
     * left shift bits of timeStamp, workerId and datacenterId
     */
    private final long timestampShift = sequenceBits + datacenterIdBits + workerIdBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long workerIdShift = sequenceBits;

    /*
     * object status variables
     */

    /**
     * reference material of 'time stamp' is '2024-04-01'. its value can't be
     * modified after initialization.
     */
    private final long epoch = 1680278400000L;

    /**
     * data center number the process running on, its value can't be modified
     * after initialization.
     * <p>
     * max: 2^5-1 range: [0,31]
     */
    private final long datacenterId;

    /**
     * machine or process number, its value can't be modified after
     * initialization.
     * <p>
     * max: 2^5-1 range: [0,31]
     */
    private final long workerId;
    /**
     * track the amount of calling {@link #waitNextMillis(long)} method
     */
    private final AtomicLong waitCount = new AtomicLong(0);
    /**
     * the unique and incrementing sequence number scoped in only one
     * period/unit (here is ONE millisecond). its value will be increased by 1
     * in the same specified period and then reset to 0 for next period.
     * <p>
     * max: 2^12-1 range: [0,4095]
     */
    private long sequence = 0L;
    /** the time stamp last snowflake ID generated */
    private long lastTimestamp = -1L;

    /**
     * @param datacenterId data center number the process running on, value range: [0,31]
     * @param workerId     machine or process number, value range: [0,31]
     */
    Snowflake(long datacenterId, long workerId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    /**
     * generate an unique and incrementing id
     *
     * @return id
     */
    synchronized long nextId() {
        long currTimestamp = timestampGen();

        if (currTimestamp < lastTimestamp) {
            throw new IllegalStateException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - currTimestamp));
        }

        if (currTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) { // overflow: greater than max sequence
                currTimestamp = waitNextMillis(currTimestamp);
            }

        } else { // reset to 0 for next period/millisecond
            sequence = 0L;
        }

        // track and memo the time stamp last snowflake ID generated
        lastTimestamp = currTimestamp;

        return ((currTimestamp - epoch) << timestampShift) | //
                (datacenterId << datacenterIdShift) | //
                (workerId << workerIdShift) | // new line for nice looking
                sequence;
    }

    /**
     * @return the amount of calling {@link #waitNextMillis(long)} method
     */
    long getWaitCount() {
        return waitCount.get();
    }

    /**
     * running loop blocking until next millisecond
     *
     * @param currTimestamp current time stamp
     * @return current time stamp in millisecond
     */
    long waitNextMillis(long currTimestamp) {
        waitCount.incrementAndGet();
        while (currTimestamp <= lastTimestamp) {
            currTimestamp = timestampGen();
        }
        return currTimestamp;
    }

    /**
     * get current time stamp
     *
     * @return current time stamp in millisecond
     */
    long timestampGen() {
        return System.currentTimeMillis();
    }

    long getEpoch() {
        return this.epoch;
    }

    /**
     * extract time stamp, datacenterId, workerId and sequence number
     * information from the given id
     *
     * @param id a snowflake id generated by this object
     * @return an array containing time stamp, datacenterId, workerId and
     * sequence number
     */
    long[] parseId(long id) {
        long[] arr = new long[5];
        arr[4] = ((id & diode(unusedBits, timestampBits)) >> timestampShift);
        arr[0] = arr[4] + epoch;
        arr[1] = (id & diode(unusedBits + timestampBits, datacenterIdBits)) >> datacenterIdShift;
        arr[2] = (id & diode(unusedBits + timestampBits + datacenterIdBits, workerIdBits)) >> workerIdShift;
        arr[3] = (id & diode(unusedBits + timestampBits + datacenterIdBits + workerIdBits, sequenceBits));
        return arr;
    }

    /**
     * extract and display time stamp, datacenterId, workerId and sequence
     * number information from the given id in humanization format
     *
     * @param id snowflake id in Long format
     * @return snowflake id in String format
     */
    String formatId(long id) {
        long[] arr = parseId(id);
        String tmf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(arr[0]));
        return String.format("%s, #%d, @(%d,%d)", tmf, arr[3], arr[1], arr[2]);
    }

    /**
     * a diode is a long value whose left and right margin are ZERO, while
     * middle bits are ONE in binary string layout. it looks like a diode in
     * shape.
     *
     * @param offset left margin position
     * @param length offset+length is right margin position
     * @return a long value
     */
    private long diode(long offset, long length) {
        int lb = (int) (64 - offset);
        int rb = (int) (64 - (offset + length));
        return (-1L << lb) ^ (-1L << rb);
    }

}
