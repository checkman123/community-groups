# Creating Community Small Groups
A work by: Levi Dahlberg and Sanyapoom Sirijirakarn (Safe)

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

## Reflection
This was a very challenging problem to work with. Initially looking at it, Safe and I were overwhelmed by the problem as it has many parts to it. However, in breaking those parts down, we came to realize that a
greedy algorithm would work very well for this problem. We started out by working out a possible algorithm by hand. Drawing pictures and bouncing ideas off of one another was extremely helpful. However, what we
didn't account for was how pseudo code would only get us so far.


When it came to actually writing the code we ran into a few roadblocks. Our algorithm worked in theory, but in practice had some short comings. Our first algorithm had multiple bugs that had to be squashed and
once that happened, we were able to get a group of 16 names to be properly assigned in a whole 113 weeks. This was not ideal and as such, needed to be refactored.


When working on this problem, it came to our attention that our first algorithm wasn't really greedy. Rather it was a pale immitation of a greedy algorithm. Our new algorithm is far more optimized and allows for
us to complete a group of 16 names in about 35 weeks, or 35 iterations.


When working on this problem, it came to our attention that our first algorithm wasn't really greedy. Rather it was a pale immitation of a greedy algorithm. Our new algorithm is far more optimized and allows for
us to complete a group of 16 names in about 25 weeks, or 25 iterations.


However, I do not believe this implementation to be the best implementation possible. Some bottlenecks of our solution is the intersection of our available guests. It requires the comparison of two lists which gives a poor time complexity. Concurrently, it may be quicker for us to have added all hosts and their guests to one large array and done a dynamic algorithm with the greedy implementation. The ideal would be equal or sub 22 iterations for all 16 names with 4 groups (As an example). I also believe that the solution for this problem could be performed in O(n) time complexity, althought the algorithm may not be as easy/neat to write as one may hope.


Unfortunately our algorithm is O(n^3). It is not an ideal result but one that came with many lessons learned during the project.


First, Safe is located in Thailand where as Levi is in Washington State. We have about a 9 hr time difference which makes collaboration difficult. Scheduling became key, to know when our schedules lined up
and how we could most effectively complete our respective tasks and still contribute equally.


Secondly, when our algorithms were failing one after another and were riddled with bugs, it took both heads to collaboratively work towards solving them. Learning how to work and collaborate together helped us
prevail. Listening to the opinions, feedback and contributions of one another made our project stronger.


Third, the art of refactoring and knowing when to start over. When our algorithms were failing, we needed to figure out ways to refactor them to get the intended effect we wanted. However, there came a point
where our refactored code was beyond understanding easily and as such, we determined it was time to start over. We took the things we found effective from our previous algorithm and wondered if they could be
applied in the new one or if we should consider scrapping them. We had lines of code that were kept, and lines that were not. Ultimately, our idea of building up to a clique was one we stuck with and
reimplemented in a new algorithm. Through careful logic control and documentation, we were able to reduce our number of iterations.


Finally, burn out and time management were heavy burdens during this project. Each of us are hard working students and as such our classes outside of Algorithm design, (While not our favorite or as fun) do
require our time and attention. Because of this, we had to learn to respect the time of the other. Perserverance was the biggest key in this project. Not giving up and working to prevail was necessary to
complete this task.


Working with graphs was fun and challenging but the most valuable thing from this project, were the lessons learned through doing it.

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
