package edu.qc.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("a bee");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private File createEmptyFile() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("");

        fileWriter.close();
        return file1;
    }

    // Actual test cases

    // Implementation of test frame #1:
    // empty file
    @Test
    public void myMainTest1() throws Exception {

        File inputFile = createEmptyFile();

        String args[] = {"Howdy", "Hello", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "";
        String actual = getFileContent(inputFile.getPath());

        assertEquals(expected, actual);
    }

    // Implementation of test frame #2:
    // empty StringFrom
    @Test
    public void myMainTest2() throws Exception {
        File inputFile = createInputFile3();
        String args[] = {"Howdy", "--", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Implementation of test frame #3:
    // length of one StringFrom
    @Test
    public void myMainTest3j() throws Exception {

        File inputFile = createInputFile1();

        String args[] = {"!", "Ho", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" againHo";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Implementation of test frame #4:
    // length of longer than file StringFrom
    @Test
    public void myMainTest4() throws Exception {

        File inputFile = createInputFile1();

        String args[] = {"How are you William Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again! more" +
                " more and more and more more..................", "Hello", "--", inputFile.getPath()};

        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ", expected, actual);
    }

    // Implementation of test frame #5:
    // no occurrence of StringFrom in the file
    @Test
    public void myMainTest5()throws Exception{

        File inputFile = createInputFile1();

        String args[] = {"cool", "fine", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ", expected, actual);
    }

    // Implementation of test frame #6:
    // empty StringTo
    @Test
    public void myMainTest6() throws Exception {

        File inputFile = createInputFile3();

        String args[] = {"Hello", "--", inputFile.getPath()};
        Main.main(args);

        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Implementation of test frame #7:
    // length of one StringTo
    @Test
    public void myMainTest7() throws Exception {

        File inputFile = createInputFile1();

        String args[] = {"How", "a", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "ady Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals(expected, actual);
    }

    // Implementation of test frame #8:
    // length of longer than file StringTo
    @Test
    public void myMainTest8() throws Exception {

        File inputFile = createInputFile4();

        String args[] = {"bee","horse run through the plainfield", "--", inputFile.getPath()};

        Main.main(args);

        String expected = "a horse run through the plainfield";

        String actual = getFileContent(inputFile.getPath());

        assertEquals(expected, actual);
    }

    // Implementation of test frame #9:
    // no occurrence of StringTo in the file
    @Test
    public void myMainTest9()throws Exception{

        File inputFile = createInputFile1();

        String args[] = {"Hi", "fine", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ", expected, actual);
    }

    // Implementation of test frame #10:
    // no filename provided
    @Test
    public void myMainTest10() throws Exception {

        String args[] = {"Howdy", "Hello", "--"};
        Main.main(args);

        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }


    // test frame case #11:
    // filename does not exist
    @Test
    public void myMaintest11() throws Exception {

        String args[] = {"Howdy", "Hello", "--", "no.txt"};
        Main.main(args);

        assertEquals("File no.txt not found", errStream.toString().trim());
    }

    // Newly Created Test Cases

    // Purpose: test for valid filename
    @Test
    public void myMainTest12() throws Exception{

        File inputFile = createInputFile1();

        String args[] = {"Howdy", "bye", "--", inputFile.getPath()};
        Main.main(args);

        String expected1 = "bye Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual1 = getFileContent(inputFile.getPath());

        assertEquals("The files differ", expected1, actual1);
        assertTrue(Files.exists(Paths.get(inputFile.getPath())));
    }

    // Purpose: test for 1 file without OPTs
    @Test
    public void myMainTest13()throws Exception{

        File inputFile = createInputFile1();

        String args[] = {"Howdy", "goodbye", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "goodbye Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ", expected, actual);
    }

    // Purpose: test for 1 file with -b OPT
    @Test
    public void myMainTest14() throws Exception {

        File inputFile = createInputFile1();

        String args[] = {"-b", "Bill", "William", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    // Purpose: test for 1 file with -f OPT
    @Test
    public void myMainTest15() throws Exception {

        File inputFile = createInputFile3();

        String args[] = {"-f", "123", "456", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 456?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for 1 file with -l OPT
    @Test
    public void myMainTest16() throws Exception {

        File inputFile = createInputFile3();

        String args[] = {"-l", "123", "456", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 456";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for 1 file with -i OPT
    @Test
    public void myMainTest17() throws Exception {

        File inputFile = createInputFile1();

        String args[] = {"-i", "Bill", "Jack", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Jack,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy Jack\" again!";
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for single file with -l and -f OPTs
    @Test
    public void myMainTest18() throws Exception {

        File inputFile = createInputFile3();
        String args[] = {"-f", "-l", "123", "456", "--", inputFile.getPath()};

        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 456?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 456";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for 1 file with -f, and -i OPTs
    @Test
    public void myMainTest19() throws Exception {

        File inputFile = createInputFile1();
        String args[] = {"-f", "-i", "Bill", "Jack", "--", inputFile.getPath()};

        Main.main(args);
        String expected = "Howdy Jack,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for 1 file with -l and -i OPTs
    @Test
    public void myMainTest20() throws Exception {

        File inputFile = createInputFile1();
        String args[] = {"-l", "-i", "Bill", "Jack", "--", inputFile.getPath()};

        Main.main(args);
        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy Jack\" again!";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for 1 file with -l, -f and -i OPTs
    @Test
    public void myMainTest21() throws Exception {

        File inputFile = createInputFile1();

        String args[] = {"-l", "-f", "-i", "Bill", "Jack", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Jack,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy Jack\" again!";

        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: test for 2 empty files with -b OPT
    @Test
    public void myMainTest22() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-b", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    // Purpose: test for 2 empty files with -f OPT
    @Test
    public void myMainTest23() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-f", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 empty files with -l OPT
    @Test
    public void myMainTest24() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-l", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 empty files with -i OPT
    @Test
    public void myMainTest25() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 empty files with -f and -l OPTs
    @Test
    public void myMainTest26() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-f", "-l", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    /// Purpose: test for 2 empty files with -f, -l  and -i OPTs
    @Test
    public void myMainTest27() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-f", "-l", "-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 empty files with all OPTs
    @Test
    public void myMainTest28() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"-b", "-f", "-l", "-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    // Purpose: test for 2 files  with -f OPT
    @Test
    public void myMainTest29() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files with -l OPT
    @Test
    public void myMainTest30() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files with -f and -l OPTs
    @Test
    public void myMainTest31() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "-l", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files with -i OPT
    @Test
    public void myMainTest32() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files with -f, and -i OPTs
    @Test
    public void myMainTest33() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-i", "-f", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files with -l and -i OPTs
    @Test
    public void myMainTest34() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-l", "-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files with -b and -i OPTs
    @Test
    public void myMainTest35() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-b", "-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    // Purpose: test for 2 files with -f, -l and -i OPTs
    @Test
    public void myMainTest36() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"-f", "-l", "-i", "Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy William\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 1 file without stringFrom and stringTo
    @Test
    public void myMainTest37() throws Exception {

        File inputFile = createEmptyFile();

        String args[] = {"--", inputFile.getPath()};
        Main.main(args);

        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }

    // Purpose: test for empty file with -b OPT
    @Test
    public void myMainTest38() throws Exception {

        File inputFile = createEmptyFile();

        String args[] = {"-b", "Bill", "William", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    // Purpose: test for empty file with -b and -i OPTs
    @Test
    public void myMainTest39() throws Exception {

        File inputFile = createEmptyFile();

        String args[] = {"-b", "-i", "-f", "-l", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "";
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }


    // Purpose: test for 2 empty files
    @Test
    public void myMainTest40() throws Exception {

        File inputFile1 = createEmptyFile();
        File inputFile2 = createEmptyFile();

        String args[] = {"Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "";
        String expected2 = "";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 files without any OPTs
    @Test
    public void myMainTest41() throws Exception {

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile2();

        String args[] = {"Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy William,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        String expected2 = "Howdy William,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy William\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: test for 2 invalid filenames
    @Test
    public void myMainTest42() throws Exception {

        String args[] = {"Bill", "William", "--", "no1.txt", "no2.txt"};
        Main.main(args);

        assertEquals("File no1.txt not found" + System.lineSeparator() +
                "File no2.txt not found", errStream.toString().trim());
    }
}
//