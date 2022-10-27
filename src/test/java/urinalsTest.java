import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class urinalsTest {
        @Test
        public void testGetAllInputsFromFile(){
            assertEquals(new ArrayList<>(),urinals.getAllInputsFromFile("src\\main\\resources\\urinal.dat"));
        }
}

