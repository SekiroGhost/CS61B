public class OffByN implements CharacterComparator{
    
    private int offSet;

    public OffByN (int n){
        offSet = n;
    }

    @Override
    public boolean equalChars(char x, char y){
        int res = x - y;

        if (res <= 0) { res += offSet;}
        else if (res > 0) { res -= offSet;}

        if (res == 0){ return true;}
        else {return false;}
    }
    
}
