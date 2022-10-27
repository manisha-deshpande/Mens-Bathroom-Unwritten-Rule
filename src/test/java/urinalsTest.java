import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class urinalsTest {
        @Test
        public void testGetAllInputsFromFile() throws FileNotFoundException {
            List<String> inputs = Arrays.asList("1001","000000","0001");
            assertEquals(inputs,urinals.getAllInputsFromFile("src\\main\\resources\\urinal.dat"));
        }

        @Test(expected = FileNotFoundException.class)
        public void testGetAllInputsFromFileNotFound() throws FileNotFoundException {
            assertEquals(new ArrayList<>(),urinals.getAllInputsFromFile("src\\main\\resources\\testing.dat"));
        }

        @Test
        public void testCheckInputValidity(){
            assertEquals(urinals.invalidInput,urinals.checkInputValidity("AB1010"));
            assertEquals("1001",urinals.checkInputValidity("1001"));
        }

        @Test
        public void testGetIntegersFromString(){
            assertEquals(Arrays.asList(0,1,0),urinals.getIntegersFromString("010"));
            assertEquals(Arrays.asList(0,0,0,0,0),urinals.getIntegersFromString("00000"));
        }
}

