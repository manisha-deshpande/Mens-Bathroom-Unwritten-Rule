import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class urinals {
    static String invalidInput = "Invalid input";
    public static void main(String[] args){
        execute();
    }
    public static void execute() {
        try{
            List<String> allInputs = getAllInputsFromFile("src\\main\\resources\\urinal.dat");
            List<String> allOutputs = new ArrayList<>();

            for(String input: allInputs){
                if(invalidInput.equalsIgnoreCase(input))
                    allOutputs.add(input);
                else{
                    allOutputs.add(findVacantUrinals(input));
                }
            }
        }
        catch(Exception e){

        }
    }

    public static String findVacantUrinals(String input) {
        int count = 0;
        return String.valueOf(count);
    }

    public static List<String> getAllInputsFromFile(String filename) throws FileNotFoundException {
        List<String> response = new ArrayList<>();

        Scanner scan;
        File file = new File(filename);
        scan = new Scanner(file);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            response.add(checkInputValidity(line));
        }
        if (scan != null)
            scan.close();

        return response;
    }

    public static String checkInputValidity(String input) {
        Pattern p = Pattern.compile("[01]+");
        Matcher m = p.matcher(input);
        boolean b = m.matches();

        if(b==false){
            return invalidInput;
        }
        return input;
    }
}
