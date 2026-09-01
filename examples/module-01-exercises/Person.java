public class Person
{
    // Provided — do not rewrite boilerplate fields
    String name;
    int age;

    // TODO: assign parameters to fields using this.name / this.age
    public Person(String name, int age)
    {
        this.age = age;
        this.name = name;

    }

    // TODO: print "<name> is <age> years old"
    public void display()
    {
        System.out.printf(this.name + " is " + this.age + " years old");
    }

    public static void main(String[] args)
    {
        // TODO: create Person("Aman", 21) and call display()

       Person newUser = new Person("Aman", 21);
       newUser.display();

    }
}
