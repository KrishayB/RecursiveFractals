import java.util.Arrays;

public class FractalTree {
    // Window width and height
    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;

    // Positive integer for number of levels
    private static final int ORDER = 10;

    // Initial right and left angles
    private static final double INIT_ANGLE_R = Math.PI/4;
    private static final double INIT_ANGLE_L = 3*Math.PI/4;

    // How much the angle should change by (should be between 0 and PI)
    private static final double CHANGE_ANGLE = Math.PI/4;

    // How much the length should change by for each level
    private static final double CHANGE_LENGTH = 0.7;

    // How much the pen radius should change by and initial pen radius
    private static final double CHANGE_PENR = 0.9;
    private static final double INIT_RAD = 0.008;

    /** Empty constructor */
    public FractalTree() {}

    /** Set up the window to draw. */
    public static void setUpWindow() {
        StdDraw.setTitle("Recursive Fractal");
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenRadius(INIT_RAD);
        StdDraw.setPenColor(StdDraw.BLACK);

        double[] x = {WIDTH/2, WIDTH/2};
        double[] y = {HEIGHT/5, 2*HEIGHT/5};

        drawRightBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), INIT_ANGLE_R, 1, WIDTH/2, 2*HEIGHT/5, HEIGHT/5, INIT_RAD);
        drawLeftBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), INIT_ANGLE_L, 1, WIDTH/2, 2*HEIGHT/5, HEIGHT/5, INIT_RAD);
    }

    // n - how many times to call method
    // x, y, angle - tells properties of lines
    public static void drawRightBranch(double[] x, double[] y, double angle, int n, double horz_shift, double vert_shift, double rad, double penRad) {
        // Manipulate the points, angle, etc
        // Call the method again in a loop (n = 0, n < 2^(order - 1))
        // n starts from 1, goes to order
        // Base case, n == order

        // System.out.println("running" + n);

        if (n == ORDER)
            return;
        else {
            StdDraw.setPenRadius(penRad);
            penRad *= CHANGE_PENR;
            StdDraw.line(x[0], y[0], x[1], y[1]);

            x[0] = x[1];
            y[0] = y[1];
            x[1] = horz_shift + rad*Math.cos(angle);
            y[1] = vert_shift + rad*Math.sin(angle);

            drawRightBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), angle - CHANGE_ANGLE, n + 1, x[1], y[1], rad*CHANGE_LENGTH, penRad);
            drawLeftBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), angle + CHANGE_ANGLE, n + 1, x[1], y[1], rad*CHANGE_LENGTH, penRad);
        }

    }

    // n - how many times to call method
    // x, y, angle - tells properties of lines
    public static void drawLeftBranch(double[] x, double[] y, double angle, int n, double horz_shift, double vert_shift, double rad, double penRad) {
        // Manipulate the points, angle, etc
        // Call the method again in a loop (n = 0, n < 2^(order - 1))
        // n starts from 1, goes to order
        // Base case, n == order

        // System.out.println("running" + n);

        if (n == ORDER)
            return;
        else {
            StdDraw.setPenRadius(penRad);
            penRad *= CHANGE_PENR;
            StdDraw.line(x[0], y[0], x[1], y[1]);

            x[0] = x[1];
            y[0] = y[1];
            x[1] = horz_shift + rad*Math.cos(angle);
            y[1] = vert_shift + rad*Math.sin(angle);

            drawRightBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), angle - CHANGE_ANGLE, n + 1, x[1], y[1], rad*CHANGE_LENGTH, penRad);
            drawLeftBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), angle + CHANGE_ANGLE, n + 1, x[1], y[1], rad*CHANGE_LENGTH, penRad);
        }

    }


    public static void main(String[] args) {
        setUpWindow();
    }
}
