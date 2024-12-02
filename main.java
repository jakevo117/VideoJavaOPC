import Model.Controller;
import java.time.LocalDate;

public class main {
    public static void main(String[] args)
    {
        Controller user = new Controller();
        user.addUserInfo();
        user.listUserInfo();

        Controller user1 = new Controller();
        user.addUserInfo();
        user.listUserInfo();
    }
}
