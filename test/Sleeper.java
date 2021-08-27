
public class Sleeper {

    int[] SEAT = {};
    int[] LOWER_BERTH = {1, 4};
    int[] MIDDLE_BERTH = {2, 5};
    int[] UPPER_BERTH = {3, 6};
    int[] SIDE_LOWER_BERTH = {7};
    int[] SIDE_UPPER_BERTH = {8};

    static void berth_type(int s) {

        if (s > 0 && s < 73) {
            if (s % 8 == 1 || s % 8 == 4) {
                System.out.println(s + " is lower berth");
                
            } else if (s % 8 == 2 || s % 8 == 5) {
                System.out.println(s + " is middle berth");
                
            } else if (s % 8 == 3 || s % 8 == 6) {
                System.out.println(s + " is upper berth");
                
            } else if (s % 8 == 7) {
                System.out.println(s + " is side lower berth");
                
            } else {
                System.out.println(s + " is side upper berth");
                
            }
        } else {
            System.out.println(s + " invalid seat number");
        }
    }

    public static void main(String[] args) {
        berth_type(12);
    }
}
