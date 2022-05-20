package core;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList; 

public class TypingGame {
	public final static String lineSeparator = System.getProperty("line.separator");
	private String text; 
	private String user; 
	private ArrayList<Boolean> scored; 
	//private double score; 
	private boolean play = false;
	private long startTime;
	private long endTime;
	public TypingGame (String inputFile) {
		startTime = 0;
		endTime = 0;
		scored = new ArrayList<Boolean> (); 
		user = ""; 
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
		if(a == text.charAt(user.length()-1)) {
			scored.add(true); 
		}
		else { 
			scored.add(false); 
		}
		return user;
	}
	public int getScore() {
		int count = 0; 
		for(boolean check: scored) {
			 if(check) {
				 count++; 
			 }
		}
		//double ratio = count*1.0/scored.size(); 
		return count; 
		//calc accuracy by counting how many true in boolean array and dividing by length of array it doesn't have to be hard 
		
	}
	public ArrayList<Boolean> getScored() {
		return scored; 
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
	public String getFile() {
		return text; 
	}
	public void setUser(String s) {
		user = s;
	}

	public long end() {
		if (System.currentTimeMillis() - startTime >= 60000) { //ends after a minute 
			endTime = System.currentTimeMillis();
			play = false;
			return text.length()*100000/(endTime-startTime);
		}
		return -1;
	}
	
	
}
