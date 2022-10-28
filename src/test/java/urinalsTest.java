import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class urinalsTest {
        @Test
        public void testGetAllInputsFromFile() throws IOException {
            String fileName = urinals.filePath+urinals.inputFileName;
            List<String> inputs = Files.readAllLines(Path.of(fileName));
            assertEquals(inputs,urinals.getAllInputsFromFile(fileName));
        }

        @Test(expected = FileNotFoundException.class)
        public void testGetAllInputsFromFileNotFound() throws FileNotFoundException {
            assertEquals(new ArrayList<>(),urinals.getAllInputsFromFile(urinals.filePath+"testing.dat"));
        }

        @Test
        public void testCheckInputValidity(){
            assertEquals(urinals.invalidInput,urinals.checkInputValidity("AB1010"));
            assertEquals(urinals.invalidInput,urinals.checkInputValidity("001100"));
            assertEquals("1001",urinals.checkInputValidity("1001"));
        }

        @Test
        public void testGetIntegersFromString(){
            assertEquals(Arrays.asList(0,1,0),urinals.getIntegersFromString("010"));
            assertEquals(Arrays.asList(0,0,0,0,0),urinals.getIntegersFromString("00000"));
        }

        @Test
        public void testFindVacantUrinals(){
            assertEquals("0",urinals.findVacantUrinals(Arrays.asList(0,1,0)));
            assertEquals("3",urinals.findVacantUrinals(Arrays.asList(0,0,0,0,0)));
        }
}

