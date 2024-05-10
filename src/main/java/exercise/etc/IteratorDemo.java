package exercise.etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorDemo {

    public static void main(String[] args) {
        ArrayList<String> sportsStarts = new ArrayList<>();
        sportsStarts.add("김연아");
        sportsStarts.add("박지성");
        sportsStarts.add("박태환");
        sportsStarts.add("손흥민");
        sportsStarts.add("이강인");

        for(int i = 0; i<sportsStarts.size(); i++){
            System.out.println(sportsStarts.get(i));
        }

        Iterator<String> sportsStarIterator = sportsStarts.iterator();

        while(sportsStarIterator.hasNext()){
            System.out.println(sportsStarIterator.next());
        }

        for(String sportsStar: sportsStarts){
            System.out.println(sportsStar);

            if(sportsStar == "김연아"){
                sportsStarts.remove(sportsStar);
            }
        }

        Map<Integer, String> fruits = new HashMap<>();
        fruits.put(1, "apple");
        fruits.put(2, "banana");
        fruits.put(3, "pineapple");
        fruits.put(4, "blueberry");
        fruits.put(5, "melon");

        Iterator<String> fruitsIterator = fruits.values().iterator();

        while(fruitsIterator.hasNext()){
            System.out.println(fruitsIterator.next());
        }
    }
}
