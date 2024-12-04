package Util;

import Service.UserService;

import java.time.LocalDate;
import java.util.Scanner;

public class Reader {
    private Scanner sc = new Scanner(System.in);

    public static Reader getReader() {
        return new Reader();
    }

    public int getNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                return choice;
            } catch (Exception e) {
                System.out.println("Input must be a number.");
            }
        }
    }

    public String getString(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();

                return input;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getNonEmptyString(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("The input must not be empty.");
                }
                return input;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getNonEmptyString(String message, String regex) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("The input must not be empty.");
                }
                if (!input.matches(regex)) {
                    throw new Exception("The input is invalid");
                }
                return input;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public LocalDate getValidDate(String message) {
        while (true) {
            try {
                System.out.print(message + "(yyyy-mm-dd): ");
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("The input must not be empty.");
                }
                if (!input.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
                    throw new Exception("The input format or date was invalid");
                }
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
