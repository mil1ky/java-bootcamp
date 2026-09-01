public class Methods
{
    public static void main(String[] args) {
        // TODO: call add(10, 20); print the result (expect 30)
        int sum = add(10,20);
        System.out.println(sum);

        // TODO: call greet("Aman"); print the result (expect Hello, Aman!)
        String message = greet("Aman");
        System.out.println(message);
    }

    // Scaffolded signature — implement the body only
    public static int add(int a, int b)
    {
        int sum  = a + b;
        return sum;
    }

    public static String greet(String name)
    {
        return "Hello, " + name + "!";

    }
}
