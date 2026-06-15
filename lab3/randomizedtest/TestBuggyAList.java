package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        a.addLast(4);
        b.addLast(4);
        a.addLast(5);
        b.addLast(5);
        a.addLast(6);
        b.addLast(6);
        assertEquals(a.size(), b.size());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber) {
                case 0:
                    int randVal = StdRandom.uniform(0, 100);
                    L.addLast(randVal);
                    M.addLast(randVal);
                    System.out.println("addLast(" + randVal + ")");
                    break;
                case 1:
                    if (L.size() <= 0 || M.size() <= 0) {
                        break;
                    }
                    int L_get_num = L.getLast();
                    int M_get_num = M.getLast();
                    System.out.println("L_getLast(" + L_get_num + ")");
                    System.out.println("M_getLast(" + M_get_num + ")");
                    assertEquals(L_get_num, M_get_num);
                    break;
                case 2:
                    if (L.size() <= 0 || M.size() <= 0) {
                        break;
                    }
                    int L_remove_num = L.removeLast();
                    int M_remove_num = M.removeLast();
                    System.out.println("L_removeLast(" + L_remove_num + ")");
                    System.out.println("M_removeLast(" + M_remove_num + ")");
                    assertEquals(L_remove_num, M_remove_num);
                    break;
                case 3:
                    int L_size = L.size();
                    int M_size = M.size();
                    System.out.println("L_size: " + L_size);
                    System.out.println("M_size: " + M_size);
                    assertEquals(L_size, M_size);
            }
        }
    }
}
