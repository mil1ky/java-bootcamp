public class ControlFlow
{
    public static void main(String[] args)
    {
        int number = 4;

        // TODO: if / else — print "even" when number is even, "odd" otherwise
        if (number % 2 == 0)
        {
            System.out.println("even");
        }
        else
        {
            System.out.println("odd");
        }

                // TODO: for loop — print 1 through 5 (one number per line)
                for (int num = 1; num <= 5; num++)
                {
                    System.out.println(num);
                }

        // TODO: while loop — countdown from 3 to 1 ("countdown " + count)
        int count = 3;
                while (count > 0)
                {
                    System.out.println("countdown " + count);
                    count --;
                }

        // TODO: switch on day (value 2) — 1 Monday, 2 Tuesday, default Other day
        int day = 2;
                switch (day)
                {
                    case 1:
                        System.out.println("Monday");
                        break;
                    case 2:
                        System.out.println("Tuesday");
                        break;

                    case 3:
                        System.out.println("Wednesday");
                        break;
                }
    }
}
