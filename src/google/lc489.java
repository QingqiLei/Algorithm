package google;

import java.util.HashSet;
import java.util.Set;

public class lc489 {
    int[][] dires= new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    Robot robot;
    Set<String> visited = new HashSet<>();
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        dfs(0,0,0);
    }
    void back(){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    void dfs(int x, int y, int dire){
        visited.add(x+":"+y);
        robot.clean();
        for(int i = 0; i < 4; i++){
            int ndire = (dire + i)%4, direArr[] = dires[ndire];
            int nx = x + direArr[0], ny = y + direArr[1];
            if(!visited.contains(nx+":"+ny) && robot.move()){
                dfs(nx, ny, ndire);
                back();
            }
            robot.turnLeft();
        }
    }
    class Robot{
        void turnRight(){

        }
        boolean move(){
            return false;
        }
        void turnLeft(){

        }
        void clean(){

        }
    }
}
