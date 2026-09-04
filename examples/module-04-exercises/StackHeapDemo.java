public class StackHeapDemo {
    static class Person {
        String name;

        Person(String name) {
            // TODO: assign the name parameter to this.name
            this.name = name;
        }
    }

    static void printPerson(Person person) {
        // Local primitive in the printPerson stack frame
        // TODO: int nameLength = person.name.length();
        int nameLength = person.name.length();
        // TODO: printf "%s has %d letters.%n" with person.name and nameLength
        System.out.printf("%s has %d letters.%n", person.name, nameLength);
    }

    public static void main(String[] args) {
        // Primitive value belongs to main's frame
        // TODO: int count = 1;
            int count = 1;
        // Reference is local; new Person object is on the heap
        // TODO: Person person = new Person("Aman");
            Person person = new Person("Aman");
        // TODO: call printPerson(person);
        printPerson(person);
        // TODO: println "Count: " + count
        System.out.println("Count: " + count);
    }
}