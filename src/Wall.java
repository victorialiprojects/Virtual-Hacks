// a number gets passed in which decides the texture. This class will store that info. 
// then, it creates a layer of a number of blocks high so we can put things at different levels. 
//<<< potential problem: placed objects might not turn out to be 3d rather just blend into the wall. 
//will have to do calculations to also see the top and bottom of the object

public class Wall {

    private final int HEIGHT = 5;
    private int[] wall = {1, 2, 3, 4} // bottom to top
    
    public Wall(int texture) {
        switch(texture) {
            case 1:
            case 2: 
            case 3: 
            case 4:
            break;
        }
    }


}