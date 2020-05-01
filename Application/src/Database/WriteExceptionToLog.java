package Database;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteExceptionToLog { 
	
    public WriteExceptionToLog(String errorMessage) {   
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");  
    	DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	String time = dtf.format(now);
        PrintWriter pw;
		try {
			FileWriter fileWriter = new FileWriter("logs/" + time, true);
			pw = new PrintWriter(fileWriter);
	        pw.println(dtf2.format(now) + "\t" + errorMessage + "\n");
	        pw.close();
		} catch (FileNotFoundException e) {
			new WriteExceptionToLog(e.getMessage());
		}catch (IOException e) {
			new WriteExceptionToLog(e.getMessage());
		}
    }
    
}