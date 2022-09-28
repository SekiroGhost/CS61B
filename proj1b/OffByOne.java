public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        int res = Character.toLowerCase(x) - Character.toLowerCase(y);

        if (res == 0){ return true;}
        else {return false;}
    }
    
}
