
import java.io.*;
import java.util.*;

public class ReadSerFile implements Serializable
{
	public static void main(String args[])
	{
		readFile();
	}

	public static void readFile()
	{
		Object obj;
		ObjectInputStream input;
		
		try
		{
			input = new ObjectInputStream(new FileInputStream("owners.ser"));
		
			while(true)
			{
				obj = (Object)input.readObject();

				System.out.println(obj);


				
				/*
				if(obj instanceof ResidentialProperty)
				
					System.out.println(((ResidentialProperty)obj).toString());
				

				else
					System.out.println(((CommercialProperty)obj).toString());	*/
				
			}
		}
	
		catch(EOFException eofe)
		{
			return;
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}

	}

}





