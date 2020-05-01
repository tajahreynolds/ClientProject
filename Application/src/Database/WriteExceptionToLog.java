package Database;

import java.io.FileNotFoundException;
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
			pw = new PrintWriter("logs/" + time);
	        pw.append(dtf2.format(now) + errorMessage + "\n\n");
	        pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
}