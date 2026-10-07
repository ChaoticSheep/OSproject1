import java.io.PrintStream;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;
//put this command into the terminal to run the logger
//java -cp "C:\Users\amari\AppData\Roaming\Code\User\workspaceStorage\772fcb70233a787b4b5a6cedf810bd7a\redhat.java\jdt_ws\OSproject1_d786fa28\bin" Logger log.txt
public class Logger {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        Scanner s = new Scanner(System.in);
        System.out.println("Logging to " + fileName);
        PrintStream log = new PrintStream(new FileOutputStream(fileName, true));
        
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String line = s.nextLine();

        while (!line.equals("QUIT")) {
            // finds the first whitespace to seperate command
            int index = line.indexOf(" ");
            String action = line.substring(0,index);
            String formattedDate = date.format(formatTime);
            log.println(formattedDate + " [" + action + "]" + line.substring(index));
            line = s.nextLine();
        } 
        s.close();
        log.close();
    }
}
