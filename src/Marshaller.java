import java.util.*;

class TrainLine {
    public ArrayList<Integer> dest = new ArrayList<>();
    public int id = -1;
    public int freespace = -1;

    @Override
    public String toString() {
        return "trains: " + dest + " freespace: " + freespace + " id: " + id;
    }
}

class MyCompare implements Comparator<TrainLine> {

    @Override
    public int compare(TrainLine o1, TrainLine o2) {
        if (o1.dest.get(0) > o2.dest.get(0))
            return 1;
        else
            return -1;
    }
}

class FreespaceCompare implements Comparator<TrainLine> {

    @Override
    public int compare(TrainLine o1, TrainLine o2) {
        if ( o1.freespace > o2.freespace )
            return -1;
        else
            return 1;
    }
}

public class Marshaller {

    HashMap<Integer, Line> lines;
    Character destination;

    public Marshaller(HashMap<Integer, Line> lines, Character destination) {
        this.lines = lines;
        this.destination = destination;
    }

    void updateLine(int id, int freespace){
        Line t = this.lines.get(id);
        t.freespace = freespace;

    }

    void marshallTrains (){

        PriorityQueue<TrainLine> garbageLines = new PriorityQueue<>(new FreespaceCompare());

        PriorityQueue<TrainLine> destInfo = new PriorityQueue<>(new MyCompare());

        ArrayList<TrainLine> freespaces = new ArrayList<>();


        for (int i = 0; i < this.lines.size(); i++){
            TrainLine tmp = new TrainLine();
            tmp.id = i;
            for (int j=0; j<this.lines.get(i).trains.size();j++){
                if (this.lines.get(i).trains.get(j) == this.destination){
                    tmp.dest.add(j);
                }
            }
            tmp.freespace = this.lines.get(i).freespace;
            if (tmp.dest.isEmpty())
                garbageLines.add(tmp);
            else {
                destInfo.add(tmp);
                freespaces.add(tmp);
            }

        }




        while (!destInfo.isEmpty()) {

            Collections.sort(freespaces, new FreespaceCompare());

            int locomotive = 0;

            TrainLine topLine = destInfo.poll();
            int toBeRemoved = topLine.dest.get(0);

            if (!garbageLines.isEmpty()) {

                while (toBeRemoved != 0) {

                    TrainLine topGarbageFree = garbageLines.poll();
                    if (topGarbageFree.freespace > toBeRemoved) {
                        System.out.println("Moved " + toBeRemoved + " cars " + "from line " + topLine.id +  " to" + " line " + topGarbageFree.id);
                        topGarbageFree.freespace -= toBeRemoved;
                        topLine.freespace += toBeRemoved;
                        toBeRemoved = 0;
                        garbageLines.add(topGarbageFree);

                    } else {
                        toBeRemoved -= topGarbageFree.freespace;
                        System.out.println("Moved " + topGarbageFree.freespace + " cars " + "from line " + topLine.id +  " to" + " line " + topGarbageFree.id);
                    }

                }

            }

            while (toBeRemoved != 0) {

                for (TrainLine freespaceCandidate : freespaces) {
                    if (freespaceCandidate.id == topLine.id) {
                        continue;
                    } else {
                        if (freespaceCandidate.freespace > toBeRemoved) {
                            freespaceCandidate.freespace -= toBeRemoved;
                            topLine.freespace += toBeRemoved;
                            toBeRemoved = 0;
                            destInfo.remove(freespaceCandidate);
                            ArrayList<Integer> rep = new ArrayList<>();
                            for(Integer j : freespaceCandidate.dest) {
                                j += toBeRemoved;
                                rep.add(j);
                            }
                            freespaceCandidate.dest = rep;
                            destInfo.add(freespaceCandidate);
                            System.out.println("Moved " + toBeRemoved + " cars " + "from line " + topLine.id +  " to" + " line " + freespaceCandidate.id);

                        } else {
                            toBeRemoved -= freespaceCandidate.freespace;
                            destInfo.remove(freespaceCandidate);
                            ArrayList<Integer> rep = new ArrayList<>();
                            for(Integer j : freespaceCandidate.dest) {
                                j += freespaceCandidate.freespace;
                                rep.add(j);
                            }
                            freespaceCandidate.dest = rep;
                            freespaceCandidate.freespace = 0;
                            destInfo.add(freespaceCandidate);
                            System.out.println("Moved " + freespaceCandidate.freespace + " cars " + "from line " + topLine.id +  " to" + " line " + freespaceCandidate.id);

                        }
                    }
                }
            }

            int fidx = topLine.dest.get(0);
            locomotive++;
            ArrayList<Integer> toBe = new ArrayList<>();
            toBe.add(fidx);
            for (int i = 1; i < topLine.dest.size(); i++) {
                if (topLine.dest.get(i) == fidx + 1) {
                    fidx++;
                    toBe.add(fidx);
                    locomotive++;
                }
                else break;
                if (locomotive >= 3)
                    break;
            }
            System.out.println("Moved " + locomotive + " cars " + "from line " + topLine.id +  " to" + " train line ");
            topLine.dest.removeAll(toBe);
            if (topLine.dest.isEmpty()) {
                garbageLines.add(topLine);
            }
            else {
                ArrayList<Integer> rep = new ArrayList<>();
                for(int j : topLine.dest) {
                    int tmp = j - locomotive;
                    rep.add(tmp);
                }
                topLine.dest = rep;
                destInfo.add(topLine);
            }

        }


    }

}
