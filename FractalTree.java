import java.util.Arrays;

/**
 * Draws a fractal tree recursively when ran.
 */
public class FractalTree {
    // Window width and height
    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;

    // Positive integer for number of levels
    private static final int ORDER = 10;

    // Initial right and left angles
    private static final double INIT_ANGLE_R = Math.PI/4;
    private static final double INIT_ANGLE_L = 3*Math.PI/4;

    // How much the angle should change by (should be between 0 and π)
    private static final double CHANGE_ANGLE = Math.PI/4;

    // How much the length should change by for each level
    private static final double CHANGE_LENGTH = 0.7;

    // How much the pen radius should change by and initial pen radius
    private static final double CHANGE_PENR = 0.9;
    private static final double INIT_RAD = 0.008;

    /** Empty constructor */
    public FractalTree() {}

    /** Sets up the window to draw. */
    public static void setUpWindow() {
        StdDraw.setTitle("Recursive Fractal");
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenRadius(INIT_RAD);
        StdDraw.setPenColor(StdDraw.BLACK);

        double[] x = {WIDTH/2, WIDTH/2};
        double[] y = {HEIGHT/5, 2*HEIGHT/5};

        // Draw right branch (π/4 is in first quadrant, move clockwise)
        drawBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), INIT_ANGLE_R, 1, WIDTH/2, 2*HEIGHT/5, HEIGHT/5, INIT_RAD);

        // Draw left branch (3π/4 is in third quadrant, move counterclockwise)
        drawBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), INIT_ANGLE_L, 1, WIDTH/2, 2*HEIGHT/5, HEIGHT/5, INIT_RAD);
    }

    /**
     * Draws branches of the tree until ORDER levels have been drawn.
     * @param x             X-coordinates of the line to draw
     * @param y             Y-coordinates of the line to draw
     * @param angle         Angle to "rotate" the line
     * @param n             Number of levels that have been drawn
     * @param horz_shift    Horizontal shift (used to draw the X-coordinates)
     * @param vert_shift    Vertical shift (used to draw the Y-coordinates)
     * @param rad           A "radius", or the length of the line
     * @param penRad        The pen thickness
     */
    public static void drawBranch(double[] x, double[] y, double angle, int n, double horz_shift, double vert_shift, double rad, double penRad) {
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

            // Draw right branch (subtract CHANGE_ANGLE to move clockwise on the unit circle)
            drawBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), angle - CHANGE_ANGLE, n + 1, x[1], y[1], rad*CHANGE_LENGTH, penRad);

            // Draw left branch (add CHANGE_ANGLE to move counterclockwise on the unit circle)
            drawBranch(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), angle + CHANGE_ANGLE, n + 1, x[1], y[1], rad*CHANGE_LENGTH, penRad);
        }

    }

    /**
     * Run the Fractal Tree program.
     * @param args
     */
    public static void main(String[] args) {
        setUpWindow();
    }
}
