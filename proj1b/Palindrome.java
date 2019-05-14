public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if(word.length() == 0) {
            return null;
        }
        LinkedListDeque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length() ; i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

   // Non-deque solution:
    /*public static boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.charAt(0) == word.charAt(word.length() - 1)) {
           return isPalindrome(word.substring(1, word.length() - 1));
        }
        return false;
    }*/

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wordDequed = wordToDeque(word);
        Character front = wordDequed.removeFirst();
        Character back = wordDequed.removeLast();
        if (front == back) {
            return isPalindrome(DequeToWord(wordDequed));
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> wordDequed = wordToDeque(word);
        Character front = wordDequed.removeFirst();
        Character back = wordDequed.removeLast();
        if (cc.equalChars(front, back)) {
            return isPalindrome(DequeToWord(wordDequed), cc);
        }
        return false;
    }
//    public boolean isPalindrome(Deque<Character> word) {
//        if (word.size() == 1 || word.size() == 0) {
//            return true;
//        }
//        Character first = word.removeFirst();
//        Character last = word.removeLast();
//        if (first == last) {
//            return isPalindrome(word);
//        }
//        return false;
//    }

    public String DequeToWord(Deque<Character> word) {
        StringBuilder res = new StringBuilder();
        while (word.size() != 0) {
            res.append(word.removeFirst());
        }
        return res.toString();
    }
}
