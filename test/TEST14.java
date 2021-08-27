public class TEST14 {

    public static void main(String[] args) {
        try {
            String var = "12312*45634";
            System.out.println(Integer.parseInt(var));
        } catch (Exception e) {
            System.out.println("Not an int value");
        }
    }

}
