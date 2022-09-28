/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffByN(5);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word,cc)) {
                System.out.println(word);
            }
        }
        int max = 0;
        int n = 0;
        for (int i = 0; i < 26; i ++){
            CharacterComparator c = new OffByN(i);
            int count = 0;
            In in2 = new In("../../library-sp18/data/words.txt");
            while (!in2.isEmpty()){
                String word = in2.readString();
                if (palindrome.isPalindrome(word,c)){
                    count += 1;
                }
            }

            if (count > max){
                max = count;
                n = i;
            }

        }

        System.out.println("The " + n + " most palindromes in English.");
    } //Uncomment this class once you've written isPalindrome. */
}
