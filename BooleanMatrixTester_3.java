
import java.util.ArrayList;
import java.util.Arrays;

public class BooleanMatrixTester_3 {

    private enum Implementation {
        LIST_MATRIX, ARRAY_MATRIX, BIT_MATRIX, SPARSE_MATRIX;
    }

    private ArrayList<TestResult> tests;
    private String expected, result;

    public static void main(String[] args) {
        BooleanMatrixTester_3 tester = new BooleanMatrixTester_3();

        /* Uncomment the below lines to test different implementations. */

        // tester.runTests(Implementation.LIST_MATRIX);
        // tester.runTests(Implementation.ARRAY_MATRIX);
        tester.runTests(Implementation.BIT_MATRIX);
        // tester.runTests(Implementation.SPARSE_MATRIX);
    }

    public void runTests(Implementation implementation) {
        System.out.println("=================================================");
        tests = new ArrayList<>();

        switch (implementation) {
            case LIST_MATRIX:
                // System.out.println("Testing List Matrix Implementation");
                // testConstructor(new ListMatrix());
                // testInterfaceMethods(new ListMatrix());
                break;
            case ARRAY_MATRIX:
                // System.out.println("Testing Array Matrix Implementation");
                // testConstructor(new ArrayMatrix());
                // testInterfaceMethods(new ArrayMatrix());
                break;
            case SPARSE_MATRIX:
                // System.out.println("Testing Sparse Matrix Implementation");
                // testConstructor(new SparseMatrix());
                // testInterfaceMethods(new SparseMatrix());
                break;
            case BIT_MATRIX:
                System.out.println("Testing Bit Matrix Implementation");
                testConstructor(new BitMatrix());
                testInterfaceMethods(new BitMatrix());
                break;
            default:
                System.out.println("Invalid implementation specified. No tests executed.");
                System.exit(-1);
        }

        TestResult.reportTestResults(tests);
    }

    private void testConstructor(BooleanMatrix m) {
        expected = "" + false;
        result = "" + m.get(0, 0);
        tests.add(new TestResult("Constructor 1", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Constructor 2", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Constructor 3", expected, result));

        expected = "[[0, 0]]";
        result = "" + arr2DToString(m.getFalsePositions());
        tests.add(new TestResult("Constructor 4", expected, result));

        expected = "[[0, 0]]";
        result = "" + arr2DToString(m.getPositions(false));
        tests.add(new TestResult("Constructor 5", expected, result));

        expected = "[]";
        result = "" + arr2DToString(m.getTruePositions());
        tests.add(new TestResult("Constructor 6", expected, result));

        expected = "[]";
        result = "" + arr2DToString(m.getPositions(true));
        tests.add(new TestResult("Constructor 7", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberFalseValues();
        tests.add(new TestResult("Constructor 8", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Constructor 9", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberTrueValues();
        tests.add(new TestResult("Constructor 10", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Constructor 11", expected, result));

    }

    private void testInterfaceMethods(BooleanMatrix m) {
        // Test reset
        m.set(12, 15);
        m.reset();

        expected = "" + false;
        result = "" + m.get(0, 0);
        tests.add(new TestResult("Reset 1", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Reset 2", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Reset 3", expected, result));

        expected = "[[0, 0]]";
        result = "" + arr2DToString(m.getFalsePositions());
        tests.add(new TestResult("Reset 4", expected, result));

        expected = "[[0, 0]]";
        result = "" + arr2DToString(m.getPositions(false));
        tests.add(new TestResult("Reset 5", expected, result));

        expected = "[]";
        result = "" + arr2DToString(m.getTruePositions());
        tests.add(new TestResult("Reset 6", expected, result));

        expected = "[]";
        result = "" + arr2DToString(m.getPositions(true));
        tests.add(new TestResult("Reset 7", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberFalseValues();
        tests.add(new TestResult("Reset 8", expected, result));

        expected = "" + 1;
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Reset 9", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberTrueValues();
        tests.add(new TestResult("Reset 10", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Reset 11", expected, result));

        // Number of Rows/Cols
        m.reset();
        m.set(3, 5); // (row, col)
        expected = "" + 4;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 1", expected, result));

        expected = "" + 6;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 2", expected, result));

        m.set(2, 3);
        expected = "" + 4;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 3", expected, result));

        expected = "" + 6;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 4", expected, result));

        m.clear(4, 6);
        expected = "" + 5;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 5", expected, result));

        expected = "" + 7;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 6", expected, result));

        m.set(2, 3);
        expected = "" + 5;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 7", expected, result));

        expected = "" + 7;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 8", expected, result));

        m.put(5, 7, true);
        expected = "" + 6;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 9", expected, result));

        expected = "" + 8;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 10", expected, result));

        m.set(2, 3);
        expected = "" + 6;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 11", expected, result));

        expected = "" + 8;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 12", expected, result));

        m.put(6, 8, false);
        expected = "" + 7;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 13", expected, result));

        expected = "" + 9;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 14", expected, result));

        m.set(2, 3);
        expected = "" + 7;
        result = "" + m.getNumberRows();
        tests.add(new TestResult("Number of rows/cols 15", expected, result));

        expected = "" + 9;
        result = "" + m.getNumberCols();
        tests.add(new TestResult("Number of rows/cols 16", expected, result));

        // Number of true/false values
        expected = "" + 3;
        result = "" + m.getNumberTrueValues();
        tests.add(new TestResult("Number of True/False Values 1", expected, result));

        expected = "" + 60;
        result = "" + m.getNumberFalseValues();
        tests.add(new TestResult("Number of True/False Values 2", expected, result));

        expected = "" + 3;
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Number of True/False Values 3", expected, result));

        expected = "" + 60;
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Number of True/False Values 4", expected, result));

        // Get individual values
        expected = "" + true;
        result = "" + m.get(3, 5);
        tests.add(new TestResult("Get Value 1", expected, result));

        expected = "" + false;
        result = "" + m.get(0, 0);
        tests.add(new TestResult("Get Value 2", expected, result));

        expected = "" + false;
        result = "" + m.get(m.getNumberRows() - 1, m.getNumberCols() - 1);
        tests.add(new TestResult("Get Value 3", expected, result));

        // Toggle
        m.toggle(3, 5);
        expected = "" + false;
        result = "" + m.get(3, 5);
        tests.add(new TestResult("Toggle Value 1", expected, result));

        m.toggle(0, 0);
        expected = "" + true;
        result = "" + m.get(0, 0);
        tests.add(new TestResult("Toggle Value 2", expected, result));

        m.toggle(m.getNumberRows() - 1, m.getNumberCols() - 1);
        expected = "" + true;
        result = "" + m.get(m.getNumberRows() - 1, m.getNumberCols() - 1);
        tests.add(new TestResult("Toggle Value 3", expected, result));

        // Set/Clear all
        m.setAll();
        expected = "" + (m.getNumberRows() * m.getNumberCols());
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Set/Clear all 1", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Set/Clear all 2", expected, result));

        m.clearAll();
        expected = "" + (m.getNumberRows() * m.getNumberCols());
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Set/Clear all 3", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Set/Clear all 4", expected, result));

        m.putAll(true);
        expected = "" + (m.getNumberRows() * m.getNumberCols());
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Set/Clear all 5", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Set/Clear all 6", expected, result));

        m.putAll(false);
        expected = "" + (m.getNumberRows() * m.getNumberCols());
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Set/Clear all 7", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Set/Clear all 8", expected, result));

        m.putAll(true);
        expected = "" + (m.getNumberRows() * m.getNumberCols());
        result = "" + m.getNumberValues(true);
        tests.add(new TestResult("Set/Clear all 9", expected, result));

        expected = "" + 0;
        result = "" + m.getNumberValues(false);
        tests.add(new TestResult("Set/Clear all 10", expected, result));

        // Get Positions
        m.reset();
        expected = "[]";
        result = arr2DToString(m.getTruePositions());
        tests.add(new TestResult("getPositions 1", expected, result));

        expected = "[]";
        result = arr2DToString(m.getPositions(true));
        tests.add(new TestResult("getPositions 2", expected, result));

        expected = "[[0, 0]]";
        result = arr2DToString(m.getFalsePositions());
        tests.add(new TestResult("getPositions 3", expected, result));

        expected = "[[0, 0]]";
        result = arr2DToString(m.getPositions(false));
        tests.add(new TestResult("getPositions 4", expected, result));

        m.set(1, 1);
        expected = "[[1, 1]]";
        result = arr2DToString(m.getTruePositions());
        tests.add(new TestResult("getPositions 1", expected, result));

        expected = "[[1, 1]]";
        result = arr2DToString(m.getPositions(true));
        tests.add(new TestResult("getPositions 2", expected, result));

        expected = "[[0, 0], [0, 1], [1, 0]]";
        result = arr2DToString(m.getFalsePositions());
        tests.add(new TestResult("getPositions 3", expected, result));

        expected = "[[0, 0], [0, 1], [1, 0]]";
        result = arr2DToString(m.getPositions(false));
        tests.add(new TestResult("getPositions 4", expected, result));

    }

    private String arr2DToString(int[][] arr2D) {
        String s = "[";

        boolean first = true;

        for (int[] row : arr2D) {
            if (first) {
                s += Arrays.toString(row);
                first = false;
            } else {
                s += ", ";
                s += Arrays.toString(row);
            }
        }

        s += "]";
        return s;
    }

}

/* Holds results of a single result */
class TestResult {

    private final String message;
    private final String expected;
    private final String received;

    public TestResult(String message, String expected, String received) {
        this.message = message;
        this.expected = expected;
        this.received = received;
    }

    private boolean passed() {
        return expected.equals(received);
    }

    public static void reportTestResults(ArrayList<TestResult> testResults) {
        System.out.println("Results of " + testResults.size() + " tests:");
        boolean errorsFound = false;
        for (TestResult result : testResults) {
            if (!result.passed()) {
                System.out.println("Error: " + result.message);
                System.out.println("\tExpected: \"" + result.expected + "\"");
                System.out.println("\tReceived: \"" + result.received + "\"");
                errorsFound = true;
            }
        }

        if (!errorsFound) {
            System.out.println("No Errors Found.");
        }
    }
} // End of class TestResult
