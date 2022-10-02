package google;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
a list of coordinates，check if there is a square
 */
public class square {
    public boolean square(List<int[]> points){
        Set<String> set = new HashSet<>();
        int n = points.size();
        for(int[] point: points) set.add(point[0]+":"+point[1]);
        for(int i = 0; i < n; i++){
            for(int j =i+1; j < n;j ++){
                int dx = points.get(j)[0] - points.get(i)[0];
                int dy = points.get(j)[1] - points.get(i)[1];
                for(int[] delta: new int[][]{{dy, -dx},{-dy, dx}}){
                    int x3 = points.get(j)[0] + delta[0], y3 = points.get(j)[1] + delta[1];
                    if(!set.contains(x3+":"+y3)) continue;
                    int x4 = x3 - dx, y4 = y3 - dy;
                    if(set.contains(x4+":"+y4)) return true;

                }
            }
        }
        return false;
    }
}
/*
points = [ (0, 0), (0, 2), (0, 4), (2, 0), (2, 2), (2, 6), (4, 4) ]

grid = {}
for point in points:
    grid[point] = 1

squares = set()     # set of frozenset(p1, p2, p3, p4), each defining a square
while len(points) >= 4:
    p1 = points.pop()
    for p2 in points:
        dx = p2[0] - p1[0]
        dy = p2[1] - p1[1]
        for delta in [(dy, -dx), (-dy, dx)]:
            p3 = (p2[0] + delta[0], p2[1] + delta[1])
            if grid.get(p3, False):
                p4 = (p3[0] - dx, p3[1] - dy)
                if grid.get(p4, False):
                    square = frozenset((p1, p2, p3, p4))    # frozen so it can be a set element
                    squares.add(square) # might be duplicate but that's OK
                break

for square in squares:
    print(list(square))
 */