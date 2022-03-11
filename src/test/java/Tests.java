import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void add(){
        Tree tester = new Tree(7, 3, 9, 2, 5, 8, 10, 1, 6);
        tester.add(4);
        String expectation = "7 3 2 1 5 4 6 9 8 10";
        assertEquals(expectation, tester.toString());
    }

    @Test
    public void search(){
        Tree tester = new Tree(7, 3, 9, 2, 5, 8, 10, 1, 4, 6);
        assertEquals(4, tester.contains(4).getValue());
        assertNull(tester.contains(11));

    }
    @Test
    public void showNeighbours(){
        Tree tester = new Tree(7, 3, 9, 2, 5, 8, 10, 1, 4, 6);
        assertEquals(3, tester.contains(7).getLeftChild().getValue());
        assertEquals(5,tester.contains(3).getRightChild().getValue());
    }
    @Test
    public void remove() {
        Tree tester = new Tree(7, 3, 9, 2, 5, 8, 10, 1, 4, 6);
        tester.remove(1);
        String expectation = "7 3 2 5 4 6 9 8 10";
        assertEquals(expectation, tester.toString());
        tester.remove(5);
        expectation = "7 3 2 6 9 8 10";
        assertEquals(expectation, tester.toString());
    }

}