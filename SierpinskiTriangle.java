import java.util.Arrays;

/**
 * Draws the Sierpinski Triangle recursively when ran.
 */
public class SierpinskiTriangle {
    // Window width and height
    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;

    // Changes how close the triangle's vertices are to the edge of the screen
    private static final int EDGE_FACTOR = 40;

    // Positive integer for number of levels
    private static final int ORDER = 6;

    /** Empty constructor */
    public SierpinskiTriangle() {}

    /** Set up the window to draw. */
    public static void setUpWindow() {
        StdDraw.setTitle("Recursive Fractal");
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);

        // Initial coordinates of the triangle
        double[] x = {WIDTH/EDGE_FACTOR, WIDTH/2, WIDTH - WIDTH/EDGE_FACTOR};
        double[] y = {HEIGHT/EDGE_FACTOR, HEIGHT - HEIGHT/EDGE_FACTOR, HEIGHT/EDGE_FACTOR};

        StdDraw.polygon(x, y);
        drawTop(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), ORDER);

        // System.out.println("\nMain top done");
        drawLeft(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), ORDER);

        // System.out.println("\nMain left done");
        drawRight(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), ORDER);

        // System.out.println("\nMain right done");
    }

    /**
     * Draws the middle top triangle.
     * @param x         X-coordinates of the triangle to draw
     * @param y         Y-coordinates of the triangle to draw
     * @param order     The triangle's level (depth)
     */
    public static void drawTop(double[] x, double[] y, int order) {
        // System.out.println("Top " + order);
        if (order == 0)
            return;
        else {
            x[0] = (x[0] + x[1])/2;
            x[2] = (x[1] + x[2])/2;
            y[0] = (y[0] + y[1])/2;
            y[2] = y[0];
            // System.out.println(order + " Drew top");
            StdDraw.polygon(x, y);

            drawTop(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
            drawLeft(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
            drawRight(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
        }
    }

    /**
     * Draws the bottom left triangle.
     * @param x         X-coordinates of the triangle to draw
     * @param y         Y-coordinates of the triangle to draw
     * @param order     The triangle's level (depth)
     */
    public static void drawLeft(double[] x, double[] y, int order) {
        // System.out.println("Left " + order);
        if (order == 0)
            return;
        else {
            x[2] = x[1];
            x[1] = (x[0] + x[1])/2;
            y[1] = (y[0] + y[1])/2;
            // System.out.println(order + " Drew left");
            StdDraw.polygon(x, y);

            drawTop(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
            drawLeft(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
            drawRight(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
        }
    }

    /**
     * Draws the bottom right triangle.
     * @param x         X-coordinates of the triangle to draw
     * @param y         Y-coordinates of the triangle to draw
     * @param order     The triangle's level (depth)
     */
    public static void drawRight(double[] x, double[] y, int order) {
        // System.out.println("Right " + order);
        if (order == 0)
            return;
        else {
            x[0] = x[1];
            x[1] = (x[1] + x[2])/2;
            y[1] = (y[0] + y[1])/2;
            // System.out.println(order + " Drew right");
            StdDraw.polygon(x, y);

            drawTop(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
            drawLeft(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
            drawRight(Arrays.copyOf(x, 3), Arrays.copyOf(y, 3), order - 1);
        }
    }

    /**
     * Run the Sierpinski Triangle program.
     * @param args  Arguments
     */
    public static void main(String[] args) {
        setUpWindow();
    }
}
