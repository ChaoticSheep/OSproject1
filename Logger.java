import java.io.PrintStream;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;
//put this command into the terminal to run the logger
//java -cp "C:\Users\amari\AppData\Roaming\Code\User\workspaceStorage\772fcb70233a787b4b5a6cedf810bd7a\redhat.java\jdt_ws\OSproject1_d786fa28\bin" Logger log.txt
public class Logger {
    public static void main(String[] args) throws Exception {
        
        Scanner s = new Scanner(System.in);
        String fileName = args[0];
        PrintStream log = new PrintStream(new FileOutputStream(fileName, true));
        
        
        
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String line = s.nextLine();

        while (!line.equals("QUIT")) {
            LocalDateTime date = LocalDateTime.now();
            String formattedDate = date.format(formatTime);

            Scanner lineScanner = new Scanner(line);

            String action = lineScanner.next();

            String message = "";
            if (lineScanner.hasNextLine()) {
                message = lineScanner.nextLine();
            }

            log.println(formattedDate + " [" + action + "]" + message);
            log.flush();

            lineScanner.close();

            line = s.nextLine();
        }
        
        s.close();
        log.close();
    }
}
