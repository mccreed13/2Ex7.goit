import java.util.Scanner;

public class HttpImageStatusCli {
    void askStatus() {
        System.out.println("Enter HTTP status code");
        Scanner scanner = new Scanner(System.in);
        boolean isMessage = true;
        int code = 0;
        while (isMessage){
            try{
                code = Integer.parseInt(scanner.nextLine());
                isMessage = false;
            } catch (NumberFormatException e){
                System.out.println("Please enter valid number");
            }
        }
        try{
            new HttpStatusImageDownloader().downloadStatusImage(code);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
