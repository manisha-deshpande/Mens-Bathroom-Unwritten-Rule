import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* AUTHOR:
 * Manisha Malhar Rao Deshpande
 * ASU ID: 1225578055
 * ASURITE ID: mdeshp10
 * */

public class urinalsTest {
        static String filePath = "src\\test\\resources\\";

        static String inputFileName = "urinal.dat";

        static String outputFilePrefix = "rule";

        @Test
        public void testGetAllInputsFromFile() throws Exception {
            String fileName = filePath+inputFileName;
            List<String> inputs = Files.readAllLines(Path.of(fileName));
            assertEquals(inputs,urinals.getAllInputsFromFile(fileName));
        }

        @Test(expected = FileNotFoundException.class)
        public void testGetAllInputsFromFileNotFound() throws Exception {
            assertEquals(new ArrayList<>(),urinals.getAllInputsFromFile(filePath+"testing.dat"));
        }

        @Test(expected = IOException.class)
        public void testGetAllInputsFromEmptyFile() throws Exception {
            String fileName = filePath+"urinalEmpty.dat";
            assertEquals(new ArrayList<>(),urinals.getAllInputsFromFile(fileName));
        }
        @Test
        public void testCheckInputValidity(){
            assertEquals("00010100",urinals.checkInputValidity("00010100"));
            assertEquals("1",urinals.checkInputValidity("1"));
            assertEquals("1001",urinals.checkInputValidity("1001"));
        }
        @Test
        public void testCheckInputValidityForInvalidInputs(){
            assertEquals(urinals.invalidInput,urinals.checkInputValidity("AB1010"));
            assertEquals(urinals.invalidInput,urinals.checkInputValidity("001100"));
            assertEquals(urinals.invalidInput,urinals.checkInputValidity(""));
            assertEquals(urinals.invalidInput,urinals.checkInputValidity("001100001100001100001100001100001100"));
        }

        @Test
        public void testGetIntegersFromString(){
            assertEquals(Arrays.asList(1),urinals.getIntegersFromString("1"));
            assertEquals(Arrays.asList(0,1,0),urinals.getIntegersFromString("010"));
            assertEquals(Arrays.asList(0,1,0,1,0),urinals.getIntegersFromString("01010"));
            assertEquals(Arrays.asList(0,0,0,0,0),urinals.getIntegersFromString("00000"));
        }

        @Test
        public void testFindVacantUrinals(){
            assertEquals("0",urinals.findVacantUrinals(Arrays.asList(0,1,0)));
            assertEquals("0",urinals.findVacantUrinals(Arrays.asList(0,1,0,1,0)));
            assertEquals("1",urinals.findVacantUrinals(Arrays.asList(0)));
            assertEquals("3",urinals.findVacantUrinals(Arrays.asList(0,0,0,0,0)));
            assertEquals("3",urinals.findVacantUrinals(Arrays.asList(0,0,0,0,0,0)));
        }

        @Test(expected = FileAlreadyExistsException.class)
        public void testWriteToDuplicateOutputFile() throws Exception {
            assertEquals("rule.txt",urinals.writeToOutputFile(Arrays.asList("2","0","-1"),filePath));
        }
}

