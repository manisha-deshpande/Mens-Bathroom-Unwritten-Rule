import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                    List<Integer> integerList = getIntegersFromString(input);
                    allOutputs.add(findVacantUrinals(integerList));
                }
            }
        }
        catch(Exception e){

        }
    }

    public static List<Integer> getIntegersFromString(String input) {
        String[] split = input.split("");
        List<Integer> integerList = new ArrayList<>();
        for(int i = 0; i < split.length; i++){
            integerList.add(Integer.valueOf(split[i]));
        }
        return integerList;
    }

    public static String findVacantUrinals(List<Integer> integerList) {
        int count = 0;
        Integer previous = null;
        Integer current = null;
        Integer next = null;
        for(int i = 0; i < integerList.size()-1; i++){
            next = integerList.get(i+1);
            current = integerList.get(i);
            if(current == 0){
                if(next==0){
                    if(Objects.isNull(previous)){
                        count++;
                        current =1;
                    }
                    else if(previous==0){
                        count++;
                        current=1;
                    }
                }
            }
            previous = current;
        }
        if(previous==0 && next==0)
            count++;
        System.out.println(count);
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
