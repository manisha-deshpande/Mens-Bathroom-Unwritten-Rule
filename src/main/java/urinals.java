import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* AUTHOR:
* Manisha Malhar Rao Deshpande
* ASU ID: 1225578055
* ASURITE ID: mdeshp10
* */

public class urinals {
    static String filePath = "src\\main\\resources\\";

    static String inputFileName = "urinal.dat";

    static String outputFilePrefix = "rule";

    static Pattern zeroesOnesPattern = Pattern.compile("[01]+");

    static Pattern continuousOnesPattern = Pattern.compile("11");

    static String invalidInput = "Invalid input";

    public static void main(String[] args){
        execute();
    }
    public static void execute() {
        try{
            List<String> allInputs = getAllInputsFromFile(filePath+inputFileName);
            List<String> allOutputs = new ArrayList<>();

            for(String input: allInputs){
                input = checkInputValidity(input);
                if(invalidInput.equalsIgnoreCase(input))
                    allOutputs.add(input);
                else{
                    List<Integer> integerList = getIntegersFromString(input);
                    allOutputs.add(findVacantUrinals(integerList));
                }
            }

            writeToOutputFile(allOutputs);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void writeToOutputFile(List<String> allOutputs) {
        int length = new File(filePath).list().length;
        length = length-1;
        String fileNumber = length==0?"":String.valueOf(length);
        try {
            String output = String.join("\n", allOutputs);
            Files.write(Paths.get(filePath+outputFilePrefix+fileNumber+".txt"), output.getBytes(), StandardOpenOption.CREATE_NEW);
        }catch (IOException e) {
            e.printStackTrace();
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

        /* base case of only one digit(urinal) */
        if(integerList.size()==1){
            if(integerList.get(0)==0)
                return "1";
            else
                return "0";
        }

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
        if(!Objects.isNull(previous) && previous==0 && next==0)
            count++;
        return String.valueOf(count);
    }

    public static List<String> getAllInputsFromFile(String filename) throws Exception {
        List<String> response = new ArrayList<>();

        Scanner scan;
        File file = new File(filename);
        scan = new Scanner(file);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            response.add(line);
        }
        scan.close();

        if(response.size()==0)
            throw new IOException("File is empty");
        return response;
    }

    public static String checkInputValidity(String input) {
        Matcher basicPatternMatch = zeroesOnesPattern.matcher(input);

        /* Input should only have zeroes and ones and should not exceed 20 digits */
        if(basicPatternMatch.matches() && input.length()<=20){
            Matcher continuousOnesMatch = continuousOnesPattern.matcher(input);

            /* Input should not have continuous ones */
            if(!continuousOnesMatch.find())
                return input;
        }
        return invalidInput;
    }
}
