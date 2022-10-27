import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class urinalsTest {
        @Test
        public void testGetAllInputsFromFile() throws FileNotFoundException {
            assertEquals(new ArrayList<>(),urinals.getAllInputsFromFile("src\\main\\resources\\urinal.dat"));
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
}

