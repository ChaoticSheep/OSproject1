import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class Driver {
    // Method to validate user input only contains letters and is insensitive to case
    
    public static void history(ArrayList<String> history) {

        if (history.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }

        System.out.println("History:");

        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }
    //reset classes: javac *.java
    //to run the program:java Driver log.txt
    public static void main(String[] args) throws Exception {

        String fileName = args[0];
        String javaExecutable = System.getProperty("java.home") + File.separator + "bin"
            + File.separator + (File.separatorChar == '\\' ? "java.exe" : "java");
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder encryptionBuilder = new ProcessBuilder(
            javaExecutable, "-cp", classpath, "Encryption", "process"
        );
        encryptionBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        Process encryptionProcess = encryptionBuilder.start();
        InputStream encryptionIn = encryptionProcess.getInputStream();
        OutputStream encryptionOut = encryptionProcess.getOutputStream();

        ProcessBuilder loggerBuilder = new ProcessBuilder(
            javaExecutable, "-cp", classpath, "Logger", fileName
        );
        loggerBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        Process logger = loggerBuilder.start();
        OutputStream loggerOutStream = logger.getOutputStream();
        PrintStream toLogger = new PrintStream(loggerOutStream);
        
        Scanner fromEncryption = new Scanner(encryptionIn);
        PrintStream toEncryption = new PrintStream(encryptionOut);

        ArrayList<String> history = new ArrayList<>();
        
        // Create a Scanner to read user input from the console
        Scanner userInputScanner = new Scanner(System.in);
        
        String userInput = "";

    

        while(!userInput.equals("QUIT")) {
            System.out.print("Enter command (PASSWORD / ENCRYPT <text> / DECRYPT <text> / HISTORY / QUIT): ");
            userInput = userInputScanner.nextLine();
            
            if(userInput.equals("PASSWORD")) {
                System.out.println("1. Use password from history");
                System.out.println("2. Enter new password");
                System.out.print("Choose an option (1 or 2): ");
                int option = Integer.parseInt(userInputScanner.nextLine());
                
                if(option == 2) {
                    System.out.print("Enter new password: ");
                    
                    String newPassword = userInputScanner.nextLine();

                    //history.add(newPassword); password is not stored in history 
                    
                    toEncryption.println("PASS " + newPassword);
                    toEncryption.flush();
                    
                    if(fromEncryption.hasNextLine()) {
                        String result = fromEncryption.nextLine();
                        System.out.println(result);
                    }
                    toLogger.println("PASS");
                    toLogger.flush();
                    
                } else if(option == 1) {
                    if(history.isEmpty()) {
                        System.out.println("History is empty.");
                    } else {
                        history(history);
                        //get history choice from user and send to encryption process
                        System.out.print("Choose a password by number: ");
                        int choice = userInputScanner.nextInt();
                        userInputScanner.nextLine(); 
                        // Validate the choice and send the selected password to the encryption process
                        if(choice < 1 || choice > history.size()) {
                            System.out.println("Invalid choice.");
                        } else {
                            String selectedPassword = history.get(choice - 1);
                            toEncryption.println("PASS " + selectedPassword);
                            toEncryption.flush();
                            
                            if(fromEncryption.hasNextLine()) {
                                String result = fromEncryption.nextLine();
                                System.out.println(result);
                            }
                            toLogger.println("PASS");
                            toLogger.flush();
                        }
                    }
                } else {
                    System.out.println("Invalid option.");
                }
            
            }
            else if (userInput.equals("ENCRYPT")) { // DECRYPT, ENCRYPT, HISTORY, QUIT
                System.out.println("1. Use text from history");
                System.out.println("2. Enter new text");
                System.out.print("Choose an option (1 or 2): ");
                int option = userInputScanner.nextInt();
                userInputScanner.nextLine(); // consume the newline

                if(option == 1) {
                    if(history.isEmpty()) {
                        System.out.println("History is empty.");
                    } else {
                        history(history);
                        //get history choice from user and send to encryption process
                        System.out.print("Choose a text by number: ");
                        int choice = Integer.parseInt(userInputScanner.nextLine());
                        // Validate the choice and send the selected text to the encryption process
                        if(choice < 1 || choice > history.size()) {
                            System.out.println("Invalid choice.");
                        } else {
                            String selectedText = history.get(choice - 1);
                            toEncryption.println("ENCRYPT " + selectedText);
                            toEncryption.flush();
                            toLogger.println("ENCRYPT " + selectedText);
                            toLogger.flush();
                            if(fromEncryption.hasNextLine()) {
                                String result = fromEncryption.nextLine();
                                System.out.println(result);
                                if (result.startsWith("RESULT ")) {
                                    history.add(result.substring(7));
                                }
                            }
                            
                        }
                    }
                } else if(option == 2) {
                    System.out.print("Enter text to encrypt: ");
                    userInput = userInputScanner.nextLine();
                    history.add(userInput);
                    toEncryption.println("ENCRYPT " + userInput);
                    toEncryption.flush();
                    if(fromEncryption.hasNextLine()) {
                        String result = fromEncryption.nextLine();
                        System.out.println(result);
                        if (result.startsWith("RESULT ")) {
                            history.add(result.substring(7)); // Add the encrypted result to history
                        }
                    }
                    toLogger.println("ENCRYPT " + userInput);
                    toLogger.flush();
                } else {
                    System.out.println("Invalid option.");
                }


            }//end of encryption
            else if (userInput.equals("DECRYPT")) {
                System.out.println("1. Use text from history");
                System.out.println("2. Enter new text");
                System.out.print("Choose an option (1 or 2): ");
                int option = userInputScanner.nextInt();
                userInputScanner.nextLine(); // consume the newline

                if(option == 1) {
                    if(history.isEmpty()) {
                        System.out.println("History is empty.");
                    } else {
                        history(history);
                        //get history choice from user and send to encryption process
                        System.out.print("Choose a text by number: ");
                        int choice = userInputScanner.nextInt();
                        userInputScanner.nextLine(); // consume the newline
                        // Validate the choice and send the selected text to the encryption process
                        if(choice < 1 || choice > history.size()) {
                            System.out.println("Invalid choice.");
                        } else {
                            String selectedText = history.get(choice - 1);
                            toEncryption.println("DECRYPT " + selectedText);
                            toEncryption.flush();
                            toLogger.println("DECRYPT " + selectedText);
                            toLogger.flush();
                            if(fromEncryption.hasNextLine()) {
                                String result = fromEncryption.nextLine();
                                System.out.println(result);
                                if (result.startsWith("RESULT ")) {
                                    history.add(result.substring(7));
                                }
                            }
                            
                        }
                    }
                } else if(option == 2) {
                    System.out.print("Enter text to decrypt: ");
                    userInput = userInputScanner.nextLine();
                    history.add(userInput);
                    toEncryption.println("DECRYPT " + userInput);
                    toEncryption.flush();
                    toLogger.println("DECRYPT " + userInput);
                    toLogger.flush();
                    if(fromEncryption.hasNextLine()) {
                        String result = fromEncryption.nextLine();
                        System.out.println(result);
                        if (result.startsWith("RESULT ")) {
                            history.add(result.substring(7));
                        }
                    }
                } else {
                    System.out.println("Invalid option.");
                }
            }
            else if(userInput.equals("HISTORY")) {
                history(history);
            }
            else if(!userInput.equals("QUIT")) {
                System.out.println("Invalid command.");
            }
            
        }
        //Quit and close program
        toEncryption.println("QUIT");
        toEncryption.flush();
        toLogger.println("QUIT");
        toLogger.flush();
        toEncryption.close();
        toLogger.close();
        encryptionProcess.waitFor();
        logger.waitFor();
        userInputScanner.close();
    }
}