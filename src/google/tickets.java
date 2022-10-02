package google;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
There are n people standing in line to buy show tickets.Due to high demand, the venue sells tickets according to the following rules:

The person at the head of the line can buy exactly one ticket and must then exit the line.
if a person needs to purchase additional tickets,they must re-enter the end of the line and wait to be sold their next ticket(assume exit and re-entry takes zero seconds).
Each ticket sale takes exactly one second.
We express initial line of n people as an array, tickets = [tickets0, tickets1 ... ticketsN-1], where ticketsi denotes the number of tickets person i wishes to buy.
if Jesse is standing at a position p in this line, find out how much time it would take for him to buy all tickets. Complete the waiting time function in the editor below. It has two parameters:

An array, tickets, of n positive integers describing initial sequence of people standing in line. Each ticketsi describes number of tickets that a person waiting at initial place.
An integer p, denoting Alex's position in tickets.

Sample Input 5      2 6 3 4 5
2
Sample Output
12
Sample Input 4     5 5 2 3
3
Sample Output
11


 */
public class tickets {
    public int calc(List<Integer> line, int n){
        line = new LinkedList<>(line);
        int time = n+1;
        for(int i = 0; i<= n; i++){
            line.add(line.remove(0)-1);
        }
        int cycle = line.remove(line.size()-1);
        System.out.println(line);
        Collections.sort(line);
        System.out.println(line);
        System.out.println(cycle);
        for(int i =0, j = 0; i < cycle; i++){
            while(j < line.size() && i >= line.get(j)) j++;
            int wait = line.size() -j ;
            System.out.println(i + "  round  waits" + wait);
            time += ( 1+ wait);
        }
        return time;

    }

    public static void main(String[] args) {
        System.out.println(new tickets().calc(Arrays.asList(5 ,5 ,2 ,3),3));
    }
}
