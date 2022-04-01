import java.util.ArrayList;

public class Person {



    private String name;
    private int weight;
    private ArrayList<Person> possibleGuests;
    private ArrayList<Person> currentGuests = new ArrayList<Person>();
    private int numberOfGuests;

    /**
     * Constructor for creating a new Person object
     * @param name of Person
     * @param weight of the edge of this person
     * @param possibleGuests that this person could host
     */
    public Person (String name, int weight, ArrayList<Person> possibleGuests) {
        this.name = name;
        this.weight = weight;
        this.possibleGuests = possibleGuests;
    }
    public Person (String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * Get the name of the person
     * @return Person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the person
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the weight of the edge of the person
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of the edge of the person
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Returns an ArrayList<String> of possible guests this person could host
     * @return
     */
    public ArrayList<Person> getPossibleGuests() {
        return possibleGuests;
    }

    /**
     * Sets an ArrayList<String> of possible guests this person could host
     * @param possibleGuests ArrayString<String> to set as possibleGuests
     */
    public void setPossibleGuests(ArrayList<Person> possibleGuests) {
        this.possibleGuests = possibleGuests;
    }

    /**
     * Returns the current list of guests visiting this host
     * @return
     */
    public ArrayList<Person> getCurrentGuests() {
        return currentGuests;
    }

    /**
     * Sets the current guests visiting this host to @param
     * @param currentGuests
     */
    public void setCurrentGuests(ArrayList<Person> currentGuests) {
        this.currentGuests = currentGuests;
    }

    /**
     * Returns the number of guests visiting this host
     * @return
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Sets the number of guests to @param
     * @param numberOfGuests
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
