package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuggyClassTestSC3 {

    @Test
    public void buggyMethod3() {
        BuggyClass buggyClass = new BuggyClass();
        assertEquals(10, buggyClass.buggyMethod3(100));
        assertEquals(0, buggyClass.buggyMethod3(4));
    }
}