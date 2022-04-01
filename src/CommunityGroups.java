import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.Timer;


public class CommunityGroups {

    private Graph smallGroup = new SingleGraph("Small Group");
    private ArrayList<Person> people;
    int numberOfPeople;
    File file;

    /**
     * Constructor for a CommunityGroups object
     * @param people ArrayList of people who need to be assigned to small groups
     * @param numberOfPeople The total number or weight of the people in the array list
     */
    public CommunityGroups(ArrayList<Person> people, int numberOfPeople, File file){

        this.people = people;
        this.numberOfPeople = numberOfPeople;
        this.file = file;
        populateGraphNodes();

    }

    /**
     * Generates the small groups based off the ArrayList passed in the constructor
     * @param groupSize Average size of the groups that should be at each house
     *                  NOTE: Must be more than 3 and less than numberOfPeople/2
     */
    public void generateSmallGroups(int groupSize, boolean animation){

        if(animation)
            displayGraph();

        //Total people/GROUP_SIZE = NUM_OF_HOST
        int numberOfHosts = numberOfPeople/groupSize;
        //All the hosts for a current week
        ArrayList<Person> hosts = new ArrayList<>();
        //Total possible hosts to be picked from
        //This is all the people that still have yet to host everyone else at some point
        ArrayList<Person> possibleHosts = new ArrayList<>(people);

        //Counting the number of weeks
        int schedule_count = 0;
        //While everyone didnt go to everybody else’s home
        while(!possibleHosts.isEmpty()){
            //Reset/Clear all edges and repopulate the graph and array list
            smallGroup.clear();
            populateGraphNodes();
            ArrayList<Person> eligiblePeople = new ArrayList<>(people);

            boolean choosingRandomHosts = false;
            while (hosts.size() < numberOfHosts && !eligiblePeople.isEmpty()) {
                //Get the intersection of possible hosts and those who are available to host (i.e not already hosting or visiting)
                List<Person> availableHosts = possibleHosts.stream().filter(eligiblePeople::contains).collect(Collectors.toList());

                Person host;
                if (availableHosts.isEmpty()) {
                    //If no available hosts are present, pick a random eligible person
                    host = eligiblePeople.get(0);
                    //If we need to fill spaces in the final week(s) choose random hosts
                    choosingRandomHosts = true;
                } else {
                    //Pick our host from available hosts
                    host = availableHosts.get(0);
                }

                //Determine if a host is one who needs to have guests assigned
                if (host.getPossibleGuests().isEmpty() & !choosingRandomHosts) {
                    possibleHosts.remove(host);
                } else if (availableHosts.size() <= numberOfHosts) {
                    hosts.add(host);
                    eligiblePeople.remove(host);
                    assignGuestsToHost(groupSize, eligiblePeople, host, hosts.size());
                } else {
                    hosts.add(host);
                    eligiblePeople.remove(host);
                    assignGuestsToHost(groupSize, eligiblePeople, host, hosts.size());
                }

                //If we have had everyone visit everyone else, break and edit the program
                if (possibleHosts.isEmpty() && hosts.isEmpty())
                    break;
            }

            //there is people left, put them from last to first
            for(int i = hosts.size() - 1; i >= 0; i--){
                if(!eligiblePeople.isEmpty()) {
                    Person host = hosts.get(i);
                    Person guest = eligiblePeople.get(0);

                    int newWeight = host.getNumberOfGuests()  + host.getWeight() + guest.getWeight();
                    if(newWeight > groupSize + 1){
                        i--;
                    }
                    host = hosts.get(i);

                    assignGuest(eligiblePeople, host, guest);
                } else {
                    break;
                }
            }

            //Printing Schedule
            if(!hosts.isEmpty()) {
                schedule_count++;

                printWeeklyScheduledGroups(hosts, schedule_count);
                hosts.clear();
            }

            //For delay graph display
            if(animation) {
                new Thread(() -> {
                }).start();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Assigns the available guests of eligiblePeople to host with an average group size of groupSize
     * @param groupSize Average size of small group
     * @param eligiblePeople List of eligible people for the host to choose from
     * @param host Host of the small group
     */
    private void assignGuestsToHost(int groupSize, ArrayList<Person> eligiblePeople, Person host, int hostNumber) {
        int overflowGuests = numberOfPeople%groupSize;
        boolean hasOverflowGuest = false;
        //Set availableGuests to the list intersection of a host's possible guests and eligible people
        List<Person> availableGuests = host.getPossibleGuests().stream().filter(eligiblePeople::contains).collect(Collectors.toList());
        int eligibleGuestIndex = 0;
        while (host.getNumberOfGuests() + host.getWeight() < groupSize && !eligiblePeople.isEmpty()
                || (hostNumber == overflowGuests && host.getNumberOfGuests() + host.getWeight() >= groupSize && groupSize != 3 && overflowGuests != 0)) {

            if(hostNumber == overflowGuests && host.getNumberOfGuests() + host.getWeight() >= groupSize && groupSize != 3 && overflowGuests != 0)
                hasOverflowGuest = true;

            //If there are no guests that the host needs to have over that are available to have over
            if (availableGuests.isEmpty() && eligibleGuestIndex < eligiblePeople.size()) {
                //Select the next guest
                Person guest = eligiblePeople.get(eligibleGuestIndex);
                int newWeight = host.getNumberOfGuests()  + host.getWeight() + guest.getWeight();
                //See if they will fit in the small group
                if(newWeight <= groupSize || groupSize == 3 && newWeight == groupSize + 1 && guest.getWeight() == 2 || hasOverflowGuest) {
                    //Add the guest to the hosted small group
                    assignGuest(eligiblePeople, host, guest);
                    if (hasOverflowGuest)
                        break;
                } else{
                    //Else move to the next guest
                    eligibleGuestIndex++;
                }
            //If there is a guest the host needs to have over and they are available
            } else if (!availableGuests.isEmpty()){
                //Select the next available guest
                Person guest = availableGuests.get(0);
                int newWeight = host.getNumberOfGuests() + host.getWeight() + guest.getWeight();
                //See if they will fit in the small group
                //If the group sizes are 3 married couples need to be considered
                if(newWeight <= groupSize || groupSize == 3 && newWeight == groupSize + 1 && guest.getWeight() == 2 || hasOverflowGuest) {
                    //Add the guest to the hosted small group
                    assignGuest(eligiblePeople, host, guest);
                    availableGuests.remove(guest);
                    host.getPossibleGuests().remove(guest);
                    if (hasOverflowGuest)
                        break;
                } else {
                    availableGuests.remove(guest);
                }
            } else{
                Person guest = eligiblePeople.get(0);
                assignGuest(eligiblePeople, host, guest);
                if (hasOverflowGuest)
                    break;
            }

        }
    }

    /**
     * Assigns a guest to a specific host and removes them from eligiblePeople
     * @param eligiblePeople ArrayList to remove guest from
     * @param host Host to assign the guest to
     * @param guest Guest to be assigned to the host
     */
    private void assignGuest(ArrayList<Person> eligiblePeople, Person host, Person guest) {
        smallGroup.addEdge(guest.getName(),
                guest.getName(),
                host.getName(), true);
        eligiblePeople.remove(guest);
        host.setNumberOfGuests(host.getNumberOfGuests() + guest.getWeight());
        host.getCurrentGuests().add(guest);
    }

    /**
     * Print the scheduled small groups for that week by scanning the nodes to edges relationship on the graph
     * @param hosts ArrayList of hosts to print from
     * @param schedule_count The week number that this printed schedule correlates to
     */
    private void printWeeklyScheduledGroups(ArrayList<Person> hosts, int schedule_count) {


        try (FileWriter fileWriter = new FileWriter(file, true)){

            fileWriter.write("Week " + schedule_count + "\n");
            for (int i = 0; i < hosts.size(); i++) {
                Person host = hosts.get(i);
                String name = host.getName();
                StringBuilder retVal = new StringBuilder();

                retVal.append("House ").append(i + 1).append(": ").append(name).append("(host) |  ");
                smallGroup.getNode(name).edges().forEach(node -> retVal.append(node.getId()).append(" | "));
                retVal.deleteCharAt(retVal.length() - 2); //delete last comma
                fileWriter.write(retVal.toString() + "\n");
                host.setCurrentGuests(new ArrayList<Person>());
                host.setNumberOfGuests(0);
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Populates all graph nodes with the people in the class ArrayList People
     */
    private void populateGraphNodes(){
        for (Person person : people) {
            Node node = smallGroup.addNode(person.getName());
            node.setAttribute("ui.label", person.getName());
            node.setAttribute("ui.style", "text-alignment: above; text-background-mode: plain;");
        }
    }

    /**
     * Displays a Java Swing window of the class graph
     * Display library courtesy of GraphStream
     */
    public void displayGraph(){
        System.setProperty("org.graphstream.ui", "swing");
        Viewer viewer = smallGroup.display();
        //redrew graph
        viewer.replayGraph(smallGroup);
        View view = viewer.getDefaultView();

        viewer.enableAutoLayout();
    }

}
