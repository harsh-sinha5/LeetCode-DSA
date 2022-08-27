import java.util.Arrays;

public class CustomArrayList <H>{
    private  int size;
    private Object data[];

    public  CustomArrayList(int capacity) {
        if (capacity > 0){
            this.data = new Object[capacity];
        }
        else if (capacity == 0) {
            this.data = new Object[10];
        }
        else{
            throw new IllegalArgumentException("Illegal Capacity" +capacity);
        }
    }

    public CustomArrayList(){
        this(10);
    }

    public void add(H h) {
        resizeArray();
        data[size++] = h;
    }

    private void resizeArray() {
        if (size >= data.length){
            int newSize = (data.length * 2)/2 +1;
            data = Arrays.copyOf(data, newSize);
        }
    }

    public H remove(int index){

        return  null;
    }

}
