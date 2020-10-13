public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque d = wordToDeque(word);
        String half = "";
        for (int i = 0; i < word.length() / 2; i++) {
            half += d.removeLast();
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != half.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque d = wordToDeque(word);
        String half = "";
        for (int i = 0; i < word.length() / 2; i++) {
            half += d.removeLast();
        }
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), half.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
