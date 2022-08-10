package arithmetic;

public class PlusOrMinusOne {
    public  int x;

    public PlusOrMinusOne(int a){
        this.x=a;
    }

    public  int getValue(){
        return this.x;
    }

    public static PlusOrMinusOne[] values(){
        return new PlusOrMinusOne[]{new PlusOrMinusOne(1),new PlusOrMinusOne(-1)};
    }

    public String toString(){
            return String.valueOf(this.x);
    }
}
