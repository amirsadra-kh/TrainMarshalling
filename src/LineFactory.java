import java.util.ArrayList;

class Line {
    public ArrayList<Character> trains = new ArrayList<>();
    public int freespace = -1;
    public int capacity = 10;

    @Override
    public String toString() {
        return "trains: " + trains + " freespace: " + freespace + " capacity: " + capacity;
    }
}

public class LineFactory {

    public Line makeLine(String lineOrder, int maximumCapacity){

        Line tmp = new Line();
        for (int i=0; i<lineOrder.length();i++) {
            tmp.trains.add(lineOrder.charAt(i));
            tmp.capacity = maximumCapacity;
        }
        tmp.freespace = maximumCapacity - tmp.trains.size();

        return tmp;
    }
}
