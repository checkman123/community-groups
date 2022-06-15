# Creating Community Small Groups

Being in community with others is a fundamental necessity to building faith and sharing in the good news together. For we know from Matthew 18:20 "For where two or three gather in my name, there am I with them." To help create these communities and in turn a larger connected community at churches, the Community Groups program was developed. This program will be able to take in a list of names (inlcuding married couples) and then create a schedule that will allow for each person to visit every other person's house. In doing so, each person will then know every other person who is part of the Small Group Community at the church.

## Description
This program was made in Java and is used to take some number of people and then divide them up as evenly as possible to some x number of groups. Each group will have a host or hosts in the case of a married couple. On the initial pass through, no house will exceed the group size limit until it is necessary to do so to ensure that every person is assigned to a small group each week.

This program utilizes a graph and considers each person a node. Each person or couple then has a weight assigned to them to determine how many people are going to each host's house. Each guest creates a directed edge to their assigned host. In the end, a final graph of all nodes and edges should create a clique - a graph where every node is connected to every other node.

## Requirements
- Java Runtime Environment 1.8 (u281)
  - Download can be found here: https://www.java.com/en/download/windows_manual.jsp?locale=en

- Libraries (Automatically packaged with smallGroup.jar):
 
  - GraphStream Libraries
  
    - gs-algo-2.0
    
    - gs-core-2.0
    
    - gs-ui-swing-2.0
   
  - Native Libraries:
    
    - Java Util
    
    - Java io
    
    - Java nio
 
## User Manual
1. Clone or download a zip of the project and open smallGroup folder
2. Open command line (cmd)
3. run it on the cmd with the jar path ```java -jar <Jar file path>```
   ex:```java -jar "C:\Users\Nez\Desktop\smallGroup\smallGroup.jar"```
4. input the text file path ex:
```C:\Users\Nez\Desktop\smallGroup\group3.txt```
5. Specified how many people should have per group. Group size needs to have at least 3 people and less than half of the number of people.
6. input ```y``` or ```n``` to show graph
   6a. Y: Enjoy the graph! Output file smallgroups.txt will be in the current directory for group schedules each week
   6b. N: Output file smallgroups.txt will be in the current directory for group schedules each week
   
#### Note: Two important parameters:
1. File location
2. Number of people per group (More than 3 but less than the total number of people)


#### Example files:
- `group1.txt` a file with 16 names
- `group2.txt` a file with 29 names
- `group3.txt` a file with 34 names


## Results

- `group1.txt` group size 4
  https://pastebin.com/bUwB7D0d
- `group2.txt` group size 3
  https://pastebin.com/ciLYzUrc
- `group3.txt` group size 5
  https://pastebin.com/jaefEkfP

#### Checkout pictures of our output!
Screenshot: https://imgur.com/a/tLW0VQB
#### Checkout the video if you couldn't run it!
Video Link: https://www.youtube.com/watch?v=tz_C383_8fY
