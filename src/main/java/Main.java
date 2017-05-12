import core.Application;
import util.Helper;

import java.util.Scanner;

/**
 * Created by ASUS on 09-May-17.
 */


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Helper helper = new Helper();
        Application app = new Application();

            while(true) {

                try {
                    helper.getMessage("initialMsg");
                    Integer inputValue = scanner.nextInt();

                    switch (inputValue) {
                        case (1):
                            app.addContactToDB();
                            break;
                        case (2):
                            app.getContactListFromDB();
                            break;
                        case (3):
                            app.findContactFromDbById();
                            break;
                        case (4):
                            app.findContactFromDbByName();
                            break;
                        case (5):
                            app.deleteFromDbById();
                            break;
                        case (6):
                            app.updateContactInDb();
                            break;
                        case (7):
                            app.exit();
                            break;
                        default:
                            helper.getMessage("wrongCommand");
                            break;
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

    }
}
