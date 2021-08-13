package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }
    //empty string
    @Test
    public void testCountNumbers2() {
        mycustomstring.setString("");
        assertEquals(0,mycustomstring.countNumbers());
    }
    //contigious string of digits
    @Test
    public void testCountNumbers3() {
        mycustomstring.setString("102349281");
        assertEquals(1,mycustomstring.countNumbers());
    }
    //string with no numbers
    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("String has no numbers");
        assertEquals(0,mycustomstring.countNumbers());
    }
    //specials chars with numbers
    @Test
    public void testCountNumbers5() {
        mycustomstring.setString("5##$$$8$#9");
        assertEquals(3,mycustomstring.countNumbers());
    }
    //tests for missing string
    @Test (expected = NullPointerException.class)
    public void testCountNumbers6() {
        mycustomstring.countNumbers();
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }
    //empty string
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
        mycustomstring.setString("");
        assertEquals("",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3,true));
    }
    //missing string
    @Test (expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1,true);
    }
    // negative n
    @Test (expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
        mycustomstring.setString("Th1s w1ll hav3 a n3gativ n");
        assertEquals("1wla nav",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-3,false));
    }
    //same as previous but start from end true
    @Test (expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
        mycustomstring.setString("This has Double n");
        assertEquals("ssu ",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-4,true));
    }
    // n>string.length
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        mycustomstring.setString("a");
        assertEquals("",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2,true));
    }
    //same as above but startFromEnd: false
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        mycustomstring.setString("zzz");
        assertEquals("",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4,false));
    }
    //n = string.length, startFromEnd= true
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        mycustomstring.setString("Sunny Day");
        assertEquals("S",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(9,true));
    }
    // n= string.length, startFromEnd= false
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        mycustomstring.setString("Rainy Day");
        assertEquals("y", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(9,false));
    }
    //special characters
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        mycustomstring.setString("$H#%H#$#$");
        assertEquals("$",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(9,true));
    }
    //numbers and special characters
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        mycustomstring.setString("5$3#2#6$");
        assertEquals("3#",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3,false));
    }
    //n=1
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        mycustomstring.setString("COVID 19");
        assertEquals("COVID 19",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1,false));
    }
    //n=1 startFromEnd is true
    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        mycustomstring.setString("COVID19");
        assertEquals("COVID19",mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1,true));
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }
    //Index out of bounds
    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("Hi");
        mycustomstring.convertDigitsToNamesInSubstring(10,11);
        mycustomstring.getString();
    }
    //Illegal argument
    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring3() {
        mycustomstring.setString("The World NEeds to be restored");
        mycustomstring.convertDigitsToNamesInSubstring(3,2);
        mycustomstring.getString();
    }
    //missing string
    @Test (expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring4() {
        mycustomstring.convertDigitsToNamesInSubstring(1,2);
        mycustomstring.getString();
    }
    // start and end pos 1
    @Test
    public void testConvertDigitsToNamesInSubstring5() {
        mycustomstring.setString("Return same string");
        mycustomstring.convertDigitsToNamesInSubstring(1,1);
        assertEquals("Return same string",mycustomstring.getString());
    }
    //1,1 first character is digit_string
    @Test
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString("1istheway");
        mycustomstring.convertDigitsToNamesInSubstring(1,1);
        assertEquals("Oneistheway",mycustomstring.getString());
    }
    // last character is digit_string
    @Test
    public void testConvertDigitsToNamesInSubstring7() {
        mycustomstring.setString("AB3");
        mycustomstring.convertDigitsToNamesInSubstring(3,3);
        assertEquals("ABThree", mycustomstring.getString());
    }
    //basic example
    @Test
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.setString("123");
        mycustomstring.convertDigitsToNamesInSubstring(1,3);
        assertEquals("OneTwoThree", mycustomstring.getString());
    }

}
