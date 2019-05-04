public class ArrayDequeTest {
    public static void main(String args[]) {
        ArrayDeque<String> test = new ArrayDeque<>();
        System.out.println("Should be true for empty list: " + test.isEmpty());
        System.out.println(test.getSize());

//        test.addLast("hello");
//        test.addFirst("world");

        // addLast Test
        test.addLast("Greyworm");
        test.addLast("Missandei");
        test.addLast("Daenerys");
        test.addLast("Jon/Aegon");
        test.addLast("Arya");
        test.addLast("Tyrion");
        test.addLast("Sansa");
        test.addLast("Sam");
        test.addLast("Jorah");
//        test.printDeque();

        // addFront Test
        test.addFirst("Ghost");
        test.addFirst("Ned");
        test.addFirst("Bran");
        test.addFirst("Cersei");
        test.addFirst("Robert");
        test.addFirst("Catlyn");
        test.addFirst("Jaime");
        test.addFirst("Bron");
        test.addFirst("Varys");
        test.printDeque();

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeLast();
        test.removeFirst();

        test.printDeque();

        // testing copy constructor
        ArrayDeque<String> testCopy = new ArrayDeque<>(test);
        testCopy.printDeque();

        System.out.println("Testing get: " + test.get(0));
        System.out.println("Testing get: " + test.get(3));

        System.out.println(test.getSize());
    }
}
