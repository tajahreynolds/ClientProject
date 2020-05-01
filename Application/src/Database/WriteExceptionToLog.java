package Database;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteExceptionToLog { 
	
    public WriteExceptionToLog(String errorMessage) {   
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");  
    	LocalDateTime now = LocalDateTime.now();  
    	String time = dtf.format(now);
        PrintWriter pw;
		try {
			pw = new PrintWriter("logs/" + time);
	        pw.append(errorMessage + "\n\n");
	        pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
}