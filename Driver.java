import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

public class Driver {
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
    public static void main(String[] args) throws Exception {
        // set up encryption process
        Process encryptionProcess = Runtime.getRuntime().exec(
            "java -cp \"C:\\Users\\amari\\AppData\\Roaming\\Code\\User\\workspaceStorage\\772fcb70233a787b4b5a6cedf810bd7a\\redhat.java\\jdt_ws\\OSproject1_d786fa28\\bin\" Encryption process"
        );
        InputStream encryptionIn = encryptionProcess.getInputStream();
        OutputStream encryptionOut = encryptionProcess.getOutputStream();
        //set up logger process
        Process logger = Runtime.getRuntime().exec(
            "java -cp \"C:\\Users\\amari\\AppData\\Roaming\\Code\\User\\workspaceStorage\\772fcb70233a787b4b5a6cedf810bd7a\\redhat.java\\jdt_ws\\OSproject1_d786fa28\\bin\" Logger log.txt"
        );  
        OutputStream loggerOutStream = logger.getOutputStream();
        PrintStream toLogger = new PrintStream(loggerOutStream);
        
        Scanner fromEncryption = new Scanner(encryptionIn);
        PrintStream toEncryption = new PrintStream(encryptionOut);

        ArrayList<String> history = new ArrayList<>();
        
        // Create a Scanner to read user input from the console
        Scanner userInputScanner = new Scanner(System.in);
        
        String userInput = "";

    

        while(!userInput.equals("QUIT")) {
            System.out.print("Enter command (PASS / ENCRYPT <text> / DECRYPT <text> / HISTORY / QUIT): ");
            userInput = userInputScanner.nextLine();
            
            if(userInput.equals("PASS")) {
                System.out.println("1. Use password from history");
                System.out.println("2. Enter new password");
                System.out.print("Choose an option (1 or 2): ");
                String option = userInputScanner.nextLine();
                if(option.equals("2")) {
                    System.out.print("Enter new password: ");
                    String newPassword = userInputScanner.nextLine();
                    history.add(newPassword);
                    toEncryption.println("PASS " + newPassword);
                    toEncryption.flush();
                    
                    if(fromEncryption.hasNextLine()) {
                        String result = fromEncryption.nextLine();
                        System.out.println(result);
                    }
                    toLogger.println("PASS");
                    toLogger.flush();
                    
                } else if(option.equals("1")) {
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
                        int choice = userInputScanner.nextInt();
                        userInputScanner.nextLine(); // consume the newline
                        // Validate the choice and send the selected text to the encryption process
                        if(choice < 1 || choice > history.size()) {
                            System.out.println("Invalid choice.");
                        } else {
                            String selectedText = history.get(choice - 1);
                            toEncryption.println("ENCRYPT " + selectedText);
                            toEncryption.flush();
                            
                            if(fromEncryption.hasNextLine()) {
                                String result = fromEncryption.nextLine();
                                System.out.println(result);
                                if (result.startsWith("RESULT ")) {
                                    history.add(result.substring(7));
                                }
                            }
                            toLogger.println("ENCRYPT " + selectedText);
                            toLogger.flush();
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
                            
                            if(fromEncryption.hasNextLine()) {
                                String result = fromEncryption.nextLine();
                                System.out.println(result);
                                if (result.startsWith("RESULT ")) {
                                    history.add(result.substring(7));
                                }
                            }
                            toLogger.println("DECRYPT " + selectedText);
                            toLogger.flush();
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