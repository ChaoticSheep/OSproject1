import java.lang.*;
import java.io.*;
import java.util.Scanner;

public class CPU
{

	public static void main(String[] args)
	{
		try
		{
		Process mem = Runtime.getRuntime().exec(
    		"java -cp \"C:\\Users\\amari\\AppData\\Roaming\\Code\\User\\workspaceStorage\\772fcb70233a787b4b5a6cedf810bd7a\\redhat.java\\jdt_ws\\OSproject1_d786fa28\\bin\" MainMemory"
);		InputStream inStream = mem.getInputStream();
		OutputStream outStream = mem.getOutputStream();
		Scanner fromMem = new Scanner(inStream);
		PrintStream toMem = new PrintStream(outStream);


		for(int i=10; i>=0; i--)
		{
			toMem.println("write");
			toMem.println(i);

			System.out.print("Set to ");
			toMem.println("read");
			toMem.flush();
			System.out.println(fromMem.nextInt());
		}

		toMem.println("halt");
		toMem.flush();

		mem.waitFor();
		}
		catch(IOException ex)
		{
			System.out.println("Unable to run MainMemory");
		}
		catch(InterruptedException ex)
		{
			System.out.println("Unexpected Termination");
		}
	
	}

}
