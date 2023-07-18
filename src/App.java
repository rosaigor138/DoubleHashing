import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Welcome to this little project
                Created by Igor Rosa F. Pinto
                \n
                """);
        System.out.println("""
                We will start creating the hash table,
                the table needs to be prime number, that way we minimize the number of collisions.
                If you do not enter one prime number, the system will use the next prime number
                \n
                \n
                Enter the number of the Hash Table:""");
        HashTable hashTable = new HashTable(scanner.nextInt());
        System.out.println("""
                Now you will enter values to be insert on the Hash Table
                1º key
                2º Element
                """);
        int scanInt;
        String scanStr;
        do {
            System.out.println("Enter the key: ");
            scanInt = scanner.nextInt();
            System.out.println("Enter the element");
            scanStr = scanner.next();
            hashTable.insert(scanInt, scanStr);
            System.out.println("Entered, you wanna enter another ?\ntrue or false");
        } while (scanner.nextBoolean());

        while (true){
            System.out.println("""
                    Options:
                    1- Print Hash table
                    2- Search for one key
                    3- Delete by one specific key
                    4- Delete all available elements
                    5- Set one element available by key
                    9- End program
                    """);
            switch (scanner.nextInt()) {
                case 1 -> hashTable.printTable();
                case 2 -> {
                    System.out.println("Enter the key");
                    System.out.println(hashTable.search(scanner.nextInt()));
                }
                case 9 -> System.exit(0);
            }
        }
    }
}