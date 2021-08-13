package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuggyClassTestBC2 {

    @Test
    public void buggyMethod2() {
        BuggyClass buggyClass = new BuggyClass();
        assertEquals(2, buggyClass.buggyMethod2(6, 2));
        assertEquals(0, buggyClass.buggyMethod2(0, 4));
    }
}