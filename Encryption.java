import java.util.Scanner;

public class Encryption {
    public static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            // Shift each character by 3 positions in the ASCII table
            char encryptedChar = (char) (c + 3);
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }
    public static String decrypt(String encryptedText) {
        StringBuilder decryptedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            // Shift each character back by 3 positions in the ASCII table
            char decryptedChar = (char) (c - 3);
            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();

        while (!line.equals("QUIT")) {

            int index = line.indexOf(" ");

            if (index == -1) {
                if (line.equals("PASS")) {
                    System.out.println("ERROR No password provided.");
                } else {
                    System.out.println("ERROR Invalid command.");
                }
            } 
            else {
                String action = line.substring(0, index);
                String message = line.substring(index + 1);

                if (action.equals("PASS") && !message.isEmpty()) {
                    System.out.println("RESULT"+message);
                } 
                else if (action.equals("ENCRYPT") && !message.isEmpty()) {
                    String text = message;

                    String encryptedText = encrypt(text);
                    System.out.println("RESULT"+encryptedText);
                } 
                else if(action.equals("DECRYPT")  && !message.isEmpty()) {
                    String encryptedText = message;

                    String decryptedText = decrypt(encryptedText); // Decrypting by applying the same encryption method
                    System.out.println("RESULT"+decryptedText);
                }
                else {
                    System.out.println("ERROR Invalid command or missing message.");
                }
            }

            // get the next command
            line = s.nextLine();
        }

        s.close();
    }
}