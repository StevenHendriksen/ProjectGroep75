package ss.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort {

    public static <Elem extends Comparable<Elem>>

    void mergesort(List<Elem> list) {
        List<Elem> sequence = new ArrayList<>();
        sequence.addAll(sort(list));
        list.clear();
        list.addAll(sequence);
    }

    public static <Elem extends Comparable<Elem>>

    List<Elem> sort(List<Elem> list) {
        if(list.size() <= 1) return list;

        List<Elem> fst = sort(list.subList(0, list.size() / 2));
        List<Elem> snd = sort(list.subList(list.size() / 2, list.size()));

        List<Elem> finalList = new ArrayList<>();

        int fi = 0;
        int si = 0;

        while(fi < fst.size() && si < snd.size()) {
            if(fst.get(fi).compareTo(snd.get(si)) < 0){
                finalList.add(finalList.size(), fst.get(fi));
                fi++;
            } else {
                finalList.add(finalList.size(), snd.get(si));
                si++;
            }
        }

        if(fi < fst.size()) {
            finalList.addAll(finalList.size(), fst.subList(fi, fst.size()));
        }
        if(si < snd.size()) {
            finalList.addAll(finalList.size(), snd.subList(si, snd.size()));
        }

        return finalList;
    }

}