package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuggyClassTestSC1a {
    // 100% coverage but doesn't catch divide by 0
    @Test
    public void buggyMethod1() {
        BuggyClass buggyClass = new BuggyClass();
        assertEquals(10, buggyClass.buggyMethod1(50, 5));
        assertEquals(5, buggyClass.buggyMethod1(5, 25));
        assertEquals(1, buggyClass.buggyMethod1(10, 10));
    }
}