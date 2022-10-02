package google;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
https://leetcode.com/discuss/interview-question/1221350/google-l3-april-2021-reject
Question 1 : Given a CPU schedule with starting & ending time of processes. This schedule is used by the server to run the processes.
When a new process comes into the scheduler, it is first checked with this scheduler if it can be accommodated or not.

You have to return a boolean value if the incoming process can be accommodated into the process schedule or not.
 If it can be accommodated, then the scheduler has to be updated with the new process being a part of the
 schedule and if not then return false.

Note : The time is given as a string input and is military time. (00:00-23:59)
Input :
schedule : [[“10:00”,”11:00”],[“14:00”,”16:00”],[“23:00”,”23:30”]]
incomingProcesses : [[“11:00”,”11:30”],[“12:00”:”15:00”],[“11:15”,”13:43”],[“17:00”,”18:40”]]
Output : [true, false, false, true]
Explanation :
11:00 - 11:30 can be accommodated by the processor hence it is added as a part of the scheduler.
12:00 - 15:00 cannot be accommodated as a process is already starting at 12:00 therefore a clash will happen.
Since 11:00-11:30 is a part of the scheduler, 11:15-13:43 will clash.
17:00 - 18:40 can be accommodated as a part of the scheduler.

Follow up question : How will you handle the case if multiple cores of CPU are in present?
 */
public class errorRate {
    TreeMap<Integer, Integer> tasks = new TreeMap<>();
    List<int[]> tasks1 = new ArrayList<>();
    void schedule(List<String[]> processes){
        for(String[] process: processes){
            tasks.put(getIntFromTime(process[0]), getIntFromTime(process[1]));
            tasks1.add(new int[]{getIntFromTime(process[0]),getIntFromTime(process[1]) });
        }
    }

    int getIntFromTime(String time){
        return Integer.parseInt(time.substring(0,2) + time.substring(3));
    }

    boolean add(String[] process){
        Integer start = getIntFromTime(process[0]), end = getIntFromTime(process[1]);
        Integer preStart = tasks.lowerKey(end);
        if(preStart != null && tasks.get(preStart) > start) return false;
        tasks.put(start, end);
        return true;
    }

    boolean add1(String[] process, int core){
        int overlap = 1;
        Integer start = getIntFromTime(process[0]), end = getIntFromTime(process[1]);
        for(int[] task : tasks1){
           if(start >= task[1] || task[0] >= end) continue;
           overlap++;
        }

        if(overlap <= core) {
            tasks1.add(new int[]{start, end});
            return true;
        }
        else return false;
    }


}
