import java.util.Arrays;

/**
 * The Cantor Set involves drawing line segments where the middle third is
 * removed as more levels are drawn.
 */
public class CantorSet {
    /** Window width */
    private static final int WIDTH = 960;

    /** Window height */
    private static final int HEIGHT = 540;

    /** Positive integer for number of levels */
    private static final int ORDER = 10;

    /** The pen radius */
    private static final double PEN_RADIUS = 0.01;

    /** Sets up the window to draw. */
    public static void setUpWindow() {
        StdDraw.setTitle("Recursive Fractal");
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenRadius(PEN_RADIUS);
        StdDraw.setPenColor(StdDraw.BLACK);

        double[] x = {WIDTH/5, 4*WIDTH/5};
        double[] y = {4*HEIGHT/5, 4*HEIGHT/5};

        drawLine(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), 1);
    }

    /**
     * Draws each line for the fractal.
     * 
     * @param x The set of x coordinates
     * @param y The set of y coordinates
     * @param n The current level being iterated through
     */
    public static void drawLine(double[] x, double[] y, int n) {
        if (ORDER == n)
            return;
        else {
            StdDraw.line(x[0], y[0], x[1], y[1]);

            final double LINE_WIDTH = x[1] - x[0];

            x[1] -= 2*LINE_WIDTH/3;
            y[0] -= 10;
            y[1] -= 10;
            drawLine(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), n + 1);

            x[0] += 2*LINE_WIDTH/3;
            x[1] += 2*LINE_WIDTH/3;
            drawLine(Arrays.copyOf(x, 2), Arrays.copyOf(y, 2), n + 1);
        }
    }

    public static void main(String[] args) {
        setUpWindow();
    }
}
