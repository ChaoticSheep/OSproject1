import java.util.Scanner;
import java.io.PrintStream;

public class Encryption {
    private static String passkey = ""; // Store the passkey
    public static boolean isValidInput(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        // Check if every character is a letter
        for (char c : text.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    public static String encrypt(String text) {
       text = text.toUpperCase(); // Convert text to uppercase for consistent encryption
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            // Shift each character forward by 3 positions in the ASCII table
            char encryptedChar = (char) ('A' + (c - 'A' + 3) % 26);
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }
    public static String decrypt(String encryptedText) {
        encryptedText = encryptedText.toUpperCase(); // Convert to uppercase for consistent decryptions
        StringBuilder decryptedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            // Shift each character back by 3 positions in the ASCII table
            char decryptedChar = (char) ('A' + (c - 'A' - 3 + 26) % 26);
            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }
    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("process")) {
            processMode();
            return;
        }

        // Otherwise, run as the main/parent process.
        mainMode();
    }

    private static void processMode() {
        Scanner s = new Scanner(System.in);
        
        while(s.hasNextLine()) {
            String line = s.nextLine();
            if(line.equals("QUIT")) {
                break;
            }
            int index = line.indexOf(" ");
    

            if (index == -1) {
                System.out.println("ERROR Invalid command.");
                continue;
            } 
            
            String action = line.substring(0, index);
            String message = line.substring(index + 1);
            if (!isValidInput(message)) {
                System.out.println("ERROR Input must contain letters only.");
                System.out.flush();
                continue;
            }
            if(action.equals("PASS") && !message.isEmpty()) {
                passkey = message.toUpperCase();
                System.out.println("RESULT "+message);
                System.out.flush();
            } 
            else if (action.equals("ENCRYPT") ) {
                System.out.println("RESULT "+ encrypt(message));
                
                System.out.flush();
            } 
            else if(action.equals("DECRYPT")) {
                System.out.println("RESULT "+ decrypt(message));
                System.out.flush();
            }
            else {
                System.out.println("ERROR Invalid command.");
            }
        }

        
    } //end of processMode

    private static void mainMode() throws Exception {
        String javaPath = System.getProperty("java.home") + java.io.File.separator + "bin" + java.io.File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        Scanner s = new Scanner(System.in);
        ProcessBuilder pb = new ProcessBuilder(javaPath, "-cp", classpath, "Encryption", "process");
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = pb.start();
        Scanner fromProcess = new Scanner(process.getInputStream());
        PrintStream toProcess = new PrintStream(process.getOutputStream());

        while(s.hasNextLine()) {
            String line = s.nextLine();
            if(line.equals("QUIT")){
                toProcess.println("QUIT");
                toProcess.flush();
                break;
            }
            
            int index = line.indexOf(" ");
            
            if(index == -1) {
                if(line.equals("PASS")) {
                    System.out.println("Error No password provided.");
                }
                else {
                    System.out.println("ERROR Invalid command.");
                }
                
            }
            else{
                
                String action = line.substring(0, index);
                String message = line.substring(index + 1);
            if (action.equals("PASS") && !message.isEmpty()) {
                System.out.println("RESULT "+message);
            } 
            else if ((action.equals("ENCRYPT") || action.equals("DECRYPT"))
            && !message.isEmpty()) {
                //send command to process
                toProcess.println(line);
                toProcess.flush();

                //receive result from process
                if(fromProcess.hasNextLine()) {
                    String result = fromProcess.nextLine();
                    System.out.println(result);
                }
                else{
                    System.out.println("ERROR No result received from process.");
                }
                
            } 
            else {
                System.out.println("ERROR Invalid command or missing message.");
            }
        }
        }
        
        toProcess.println("QUIT");
        toProcess.flush();
        fromProcess.close();
        toProcess.close();
        process.waitFor();
        s.close();
    }
}