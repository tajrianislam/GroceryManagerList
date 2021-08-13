package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuggyClassTestSC1b {

    @Test
    public void buggyMethod1() {
        BuggyClass buggyClass = new BuggyClass();
        assertEquals(0, buggyClass.buggyMethod1(10, 0));
    }
}