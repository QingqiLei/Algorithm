package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 给有限个的files（file里面是未知数量的数字）和无限个machines，以及一个返回指定机器计算指定文件数字总和的API：
 int total (int fileId, int MachineID)
设计一种方案高效计算所有文件所有数字的总和
 */
public class calcMultiThread {



    long calcFile(List<Integer> files) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        List<Thread> jobs = new ArrayList<>();
        for(int i = 0; i < files.size(); i++){
            Thread t = new Thread(new calcJob(files.get(i), i,list));
            jobs.add(t);
            t.start();
        }
        for(Thread t: jobs) t.join();
        long res = 0;
        for(int i: list) res+=i;
        return res;

    }
}

class calcJob implements  Runnable{
    int file;
    int machine;
    List<Integer> list;

    calcJob(int file, int machine, List<Integer> list){
        this.file = file;
        this.machine = machine;
        this.list = list;
    }
    @Override
    public void run() {
        list.add(total(file, machine));
    }
    int total (int fileId, int MachineID){
        return 1;

    }
}


