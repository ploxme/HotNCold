package hot_n_cold;

import java.util.Scanner;

public class Console
{
    private static Scanner scanner = new Scanner(System.in);

    public static int readInt(String prompt)
    {
        System.out.print(prompt);

        int result = 0;
        while (!scanner.hasNextInt())
        {
            System.out.print("Not a valid number! ");
            scanner.nextLine(); //clear the scanner
        }

        result = scanner.nextInt();
        scanner.nextLine(); //clear the scanner

        return result;
    }

    public static boolean readBoolean(String prompt)
    {
        System.out.print(prompt);

        boolean result = false;
        while (!scanner.hasNextBoolean())
        {
            System.out.print("Please enter a valid boolean: ");
            scanner.nextLine(); //clear the scanner
        }

        result = scanner.nextBoolean();
        scanner.nextLine(); //clear the scanner

        return result;
    }

    public static double readDouble(String prompt)
    {

        System.out.print(prompt);

        double result = 0.0;
        while (!scanner.hasNextDouble())
        {
            System.out.print("Please enter a valid decimal: ");
            scanner.nextLine(); //clear the scanner
        }

        result = scanner.nextDouble();
        scanner.nextLine(); //clear the scanner

        return result;
    }

    public static String readString(String prompt)
    {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}