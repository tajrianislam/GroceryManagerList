package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuggyClassTestSC2 {

    @Test
    public void buggyMethod2() {
        BuggyClass buggyClass = new BuggyClass();
        assertEquals(4, buggyClass.buggyMethod2(4, 12));
        assertEquals(4, buggyClass.buggyMethod2(12, 4));
    }
}