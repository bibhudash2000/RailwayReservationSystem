package Test;

import com.RRS.DAO.ReservationDAO;
import com.RRS.Exceptions.DatabaseException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {

    public static String getNearestValue(List<Integer> numberList, Integer number) {
        int[] intList = numberList.stream().mapToInt(Integer::intValue).toArray();
        List<Integer> list = Arrays.stream(intList).boxed().collect(Collectors.toList());
        int n = number;
        int c = list.stream().min(Comparator.comparingInt(i -> Math.abs(i - n))).orElseThrow(() -> new NoSuchElementException("No value present"));
        return String.valueOf(c);
    }

    public static void main(String[] args) throws DatabaseException, SQLException {

        List<Integer> generalSeats = ReservationDAO.getSeatListForGeneral("5");
        List<Integer> quotaSeats = ReservationDAO.getSeatListForQuota("5");
        
        System.out.println(getNearestValue(quotaSeats, 11));
    }

}
