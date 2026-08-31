public class WoraProbe
{
    public static void main(String[] args) {
        // TODO: store System.getProperty("os.name") in a String variable
        String osName = System.getProperty("os.name");

        // TODO: print the OS name
        System.out.println(osName);
        // TODO: print "Bytecode runs on: " + that OS name
        System.out.println(("Bytecode runs on: "+ osName));
    }
}
