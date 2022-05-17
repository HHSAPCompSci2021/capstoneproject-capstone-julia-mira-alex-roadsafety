package core;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TypingGame {
	public final static String lineSeparator = System.getProperty("line.separator");
	private String text; 
	private String user;  
	private double score; 
	private boolean play = false;
	private long startTime;
	private long endTime;
	public TypingGame (String inputFile) {
		startTime = 0;
		endTime = 0;
		try {
			text = readFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//tbh i think we should just throw a random file at them that's what feels right to me
			//System.out.println("Please select a valid file");
		}
	}
	
	public String readFile(String inputFile) throws IOException {
		StringBuffer fileData = new StringBuffer();
		Scanner scan = null;
		try {
			FileReader fr = new FileReader(inputFile);
			scan = new Scanner(fr);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				
				fileData.append(line);
				fileData.append(lineSeparator);
			}
			
		}  finally {
			if(scan!=null)
				scan.close();
		}
		
		
		return fileData.toString();
	
	}
	
	public void play() {
		startTime = System.currentTimeMillis();
		play = true;
	}
	
	public String type(char a) {
		user+=a;
		return user;
	}
	public double getScore() {
		return score;
	}
	public long getTime() {
		return endTime-startTime;
	}
	public boolean gameOn() {
		return play;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String s) {
		user = s;
	}

	public long end() {
		if (user == text) {
			endTime = System.currentTimeMillis();
			play = false;
			return text.length()*100000/(startTime-endTime);
		}
		return -1;
	}
	
	
}
