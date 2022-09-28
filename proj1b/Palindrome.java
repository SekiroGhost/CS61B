public class Palindrome {
    public Deque<Character> wordToDeque (String word){
        Deque<Character> characterDeque = new ArrayDeque<Character>();
        for (Character i : word.toCharArray()){
            characterDeque.addLast(i);
        }
        return characterDeque;
    }

    public boolean isPalindrome (String word){
        Deque<Character> characterDeque = wordToDeque(word);
        boolean res = true;
        int size = characterDeque.size() ;
        for (int i = 0; i < size / 2; i++){
            if (characterDeque.removeFirst() == characterDeque.removeLast()){
                res = res && true;
            }
            else{
                res = res && false;
            }
        }

        return res;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> characterDeque = wordToDeque(word);
        boolean res = true;
        int size = characterDeque.size() ;

        for (int i = 0; i < size / 2; i++){
            if (cc.equalChars(characterDeque.removeFirst(), characterDeque.removeLast())){
                res = res && true;
            }
            else{
                res = res && false;
            }
        }
        return res;
    }
}