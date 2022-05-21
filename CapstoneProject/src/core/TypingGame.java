package core;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList; 

public class TypingGame {
	public final static String lineSeparator = System.getProperty("line.separator");
	private String text; 
	private String user; 
	private int words; 
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
		String t = fileData.toString();
		
//		String[] util = t.split(" "); 
//		words = util.length; 
		return t;
	
	}
	
	public void play() {
		startTime = System.currentTimeMillis();
		play = true;
	}
	
	public String type(char a) {
		int x = (int) a;
		if(x>=32 && x<=127) {
			user+=a;
			
			if(a == text.charAt(user.length()-1)) {
				scored.add(true); 
				
			}
			else { 
				scored.add(false); 
			}
			if(a == text.charAt(user.length()-1)) {
				words ++; 
			}
		}
		
		return user;
	}
	public void delete() {
		scored.remove(scored.size()-1);
		user = user.substring(0, user.length()-1);
	}
	public double getScore() {
		double count = 0.0; 
		for(boolean check: scored) {
			 if(check) {
				 count++; 
			 }
			 System.out.println(words); 
		}
		//double ratio = count*1.0/scored.size(); 
		return count/words; 
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

	public double end() {
		if (System.currentTimeMillis() - startTime >= 30000) { //ends after a minute 
			endTime = System.currentTimeMillis();
			play = false;
			return getScore();
			
		}
		return 0;
	}
	
	
}
