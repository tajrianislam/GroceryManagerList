package edu.qc.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuggyClassTestBC3 {

    @Test
    public void buggyMethod3() {
        BuggyClass buggyClass = new BuggyClass();
        assertEquals(10, buggyClass.buggyMethod3(100));
        assertEquals(47, buggyClass.buggyMethod3(45));
        assertEquals(1, buggyClass.buggyMethod3(22));
    }
}