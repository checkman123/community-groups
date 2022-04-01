import org.graphstream.graph.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {


        Path fileName;
        int groupSize = 0;
        String actual = "";

        String animateChoice = "";
        boolean shouldAnimate = false;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello and welcome to the Small Group Generator-inator 9000\n");
        System.out.println("To begin, please input your plaintext file location with the names of your small group participants:");
        try {
            fileName = Paths.get((bufferedReader.readLine().toString()));
        } catch (IOException e) {
            System.out.println("Uh Oh! That file cannot be found.\nPlease ensure it is where you say it is, " +
                    "and re run the Small Group Generator-inator 9000");
            return;
        }


        try {
            Scanner sc = new Scanner(fileName);
            while (sc.hasNextLine())
                actual += sc.nextLine() + "\n";
        }catch (NoSuchFileException e) {
            //e.printStackTrace();
            System.out.println("Uh Oh! That file cannot be found.\nPlease ensure it is where you say it is, and " +
                    "rerun the Small Group Generator-inator 9000");
            return;
        } catch (IOException e) {
            //e.printStackTrace();
        }

        System.out.println("Great! We are reading those wonderful names now. Quite the group you have! Oh this is so much fun!");
        int numberOfPeople = 0;
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(actual.split("\n")));
        ArrayList<Person> eligiblePeople = new ArrayList<Person>();
        for (String s : list) {
            //Create new person with name s and if it is a couple, set weight to 2, 1 otherwise
            eligiblePeople.add(new Person(s.replace("\r", ""), (s.contains(",") ? 2 : 1)));
            numberOfPeople += (s.contains(",") ? 2 : 1);
        }

        System.out.println("\nNow, what is the group size? It shouldn't be greater than half of the number of people " +
                "but should be more than 3.\nHow many people will be in each group?");


        try {
            groupSize = Integer.parseInt(bufferedReader.readLine());
            if((groupSize < 3) && (groupSize <= numberOfPeople/2)){
                System.out.println("Group Size must be greater than or equal to 3 and less than half the number of " +
                        "total people! \nPlease reconsider your group sizes and rerun the program!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Uh Oh! That input was not recognized. Are you sure it is an integer? " +
                    "\nPlease ensure your input is correct, and re run the Small Group Generator-inator 9000");
            return;
        }

        System.out.println("\nWould you like to have an animated view of the graphs being made each week? (y/n)");


        animateChoice = bufferedReader.readLine();

        if(animateChoice.equalsIgnoreCase("y")) {
            shouldAnimate = true;
        } else if (animateChoice.equalsIgnoreCase("n")){
            shouldAnimate = false;
        } else {
            System.out.println("Uh Oh! That input was not recognized. Are you sure it matches the accepted " +
                    "inputs? (y/n) \nPlease ensure your input is correct, and re run the Small Group Generator-inator 9000");
            return;
        }

        System.out.println("Oh, splendid! This is just what I needed! I will begin generating the schedule now! " +
                "Please see smallgroups.txt for your Church Small group assignments!\n\nGod Bless!");

        File file = new File(OutPutSmallGroups());
        if (!file.createNewFile()){
            file.delete();
        }
        CommunityGroups groups = new CommunityGroups(eligiblePeople, numberOfPeople, file);

        //Add the eligible people to the possible Guest array for each person
        //Except for that person
        for (Person p : eligiblePeople) {
            ArrayList<Person> tempCopy = new ArrayList<Person>(eligiblePeople);
            tempCopy.remove(p);
            p.setPossibleGuests(tempCopy);
        }


        groups.generateSmallGroups(groupSize, shouldAnimate);

    }

    /**
     * Generates output text file path to the current directory
     */
    private static String OutPutSmallGroups(){
        //Get the whole path
        String dirPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        dirPath = dirPath.substring(0, dirPath.lastIndexOf("/"));
        //Replace all %20 with a single space
        dirPath = dirPath.replaceAll("%20"," ");
        dirPath += "/smallgroups.txt";
        return dirPath;
    }
}
