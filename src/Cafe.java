// the main class

public class Cafe {

    private static int[][] map = {
        {1,1,1,1,1,1,1,1,2,2},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1},
    };
    private static Wall[][] walls = new Wall[map.length][map[0].length];


    public static void main(String[] args) {
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                walls[i][j] = new Wall(map[i][j]);
            }
        }
        // pass each number of the map into an object that will create the walls???
        // make game extend thread -- so we can make it sleep etc and have Cafe run in the background at all times
    }
    
}