public class StringBuilderComparison
{
    private static final int ITERATIONS = 50_000;

    static String withString() {
        String result = "";
        for (int i = 0; i < ITERATIONS; i++)
        {
            // TODO: result += "x";  (each update creates another String)
            result += "x";
        }
        return result;
    }

    static String withBuilder() {
        // Initial capacity avoids repeated buffer growth.
        // TODO: StringBuilder result = new StringBuilder(ITERATIONS);
        StringBuilder result = new StringBuilder(ITERATIONS);
        for (int i = 0; i < ITERATIONS; i++) {
            // TODO: result.append('x');
            result.append('x');
        }
        // TODO: return result.toString();
        return result.toString();
    }

    public static void main(String[] args)
    {
        // TODO: time withString() with System.nanoTime()
        // TODO: time withBuilder() with System.nanoTime()
        // TODO: printf both lengths and ms (stringNanos / 1_000_000.0)

        long startString = System.nanoTime();
        String stringResult = withString();
        long endString = System.nanoTime();

        long stringNanos = endString - startString;


        long startBuilder = System.nanoTime();
        String builderResult = withBuilder();
        long endBuilder = System.nanoTime();

        long builderNanos = endBuilder - startBuilder;


        System.out.printf(
                "withString: length=%d, time=%.3f ms%n",
                stringResult.length(),
                stringNanos / 1_000_000.0
        );

        System.out.printf(
                "withBuilder: length=%d, time=%.3f ms%n",
                builderResult.length(),
                builderNanos / 1_000_000.0
        );

    }
}