package com.RRS.Utilities;

import com.RRS.DAO.ReservationDAO;
import com.RRS.Exceptions.DatabaseException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

public class ExtraUtils {

    public static String getNearestValue(List<Integer> numberList, Integer number) {
        int[] intList = numberList.stream().mapToInt(Integer::intValue).toArray();
        List<Integer> list = Arrays.stream(intList).boxed().collect(Collectors.toList());
        int n = number;
        int c = list.stream().min(Comparator.comparingInt(i -> Math.abs(i - n))).orElseThrow(() -> new NoSuchElementException("No value present"));
        return String.valueOf(c);
    }

    public static int getRandomElement(List<Integer> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static List<Integer> findKClosestElements(List<Integer> input, int k, int x) {
        // find the insertion point using the binary search algorithm
        int i = Collections.binarySearch(input, x);

        // Collections.binarySearch() returns `-(insertion point) - 1`
        // if the key is not contained in the list
        if (i < 0) {
            i = -(i + 1);
        }

        int left = i - 1;
        int right = i;

        // run `k` times
        while (k-- > 0) {
            // compare the elements on both sides of the insertion point `i`
            // to get the first `k` closest elements

            if (left > 0 || (right < input.size()
                    && Math.abs(input.get(left) - x) > Math.abs(input.get(right) - x))) {
                right++;
            } else {
                left--;
            }
        }

        // return `k` closest elements
        return input.subList(left + 1, right);
    }

    public static void main(String[] args) throws DatabaseException, SQLException {
        List<Integer> gen = ReservationDAO.getSeatListForGeneral("5");
        List<Integer> quota = ReservationDAO.getSeatListForQuota("5");
        

        int num = getRandomElement(gen);
        System.out.println("Gen :" + num);
        
        List<Integer> list = findKClosestElements(gen, 6, num);
        for (Integer i : list) {
            System.out.println(i);
        }
        
//        List<Integer> list = ReservationDAO.getSeatListForGeneral("5");
//        for (Integer i : list) {
//            System.out.println(i);
//        }
    }
}
