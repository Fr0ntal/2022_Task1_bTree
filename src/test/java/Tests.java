import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void add(){
        Tree tester = new Tree(7, 3, 9, 2, 5, 8, 10, 1, 6);
        tester.add(4);
        String expectation = "1 2 3 4 5 6 7 8 9 10";
        assertEquals(expectation, tester.toString());
        tester.add(-2);
        expectation = "-2 1 2 3 4 5 6 7 8 9 10";
        assertEquals(expectation, tester.toString());
        tester.add(-1);
        expectation = "-2 -1 1 2 3 4 5 6 7 8 9 10";
        assertEquals(expectation, tester.toString());
        tester.add(90);
        expectation = "-2 -1 1 2 3 4 5 6 7 8 9 10 90";
        assertEquals(expectation, tester.toString());

        tester = new Tree();
        tester.add(1);
        expectation = "1";
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
        String expectation = "2 3 4 5 6 7 8 9 10";
        assertEquals(expectation, tester.toString());
        tester.remove(5);
        expectation = "2 3 4 6 7 8 9 10";
        assertEquals(expectation, tester.toString());
    }

    @Test
    public void containsEmpty() {
        Tree testTree = new Tree();
        Node result = testTree.contains(0);
        assertNull(result);
    }

    @Test
    public void toStringTest() {
        Tree tester = new Tree(7, 3, 9, 2, 5, 8, 10, 1, 4, 6);
        String testString = tester.toString();
        assertEquals("1 2 3 4 5 6 7 8 9 10", testString);
    }
}