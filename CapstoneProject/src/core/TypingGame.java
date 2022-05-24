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
	/**
	 * constructor 
	 * @param inputFile random game text 
	 */
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
	/**
	 * 
	 * @param inputFile input file 
	 * @return contents of input file 
	 * @throws IOException
	 */
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
	/**
	 * starts the timer 
	 */
	public void play() {
		startTime = System.currentTimeMillis();
		play = true;
	}
	/**
	 * 
	 * @param a adds char to user text 
	 * @return user text 
	 */
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
			if(a == ' ') {
				words ++; 
			}
		}
		
		return user;
	}
	/**
	 * deleting text 
	 */
	public void delete() {
		scored.remove(scored.size()-1);
		user = user.substring(0, user.length()-1);
	}
	/**
	 * 
	 * @return calculated score for user performance 
	 */
	public double getScore() {
		double count = 0.0; 
		for(boolean check: scored) {
			 if(check) {
				 count++; 
			 }
			 //System.out.println(words); 
		}
		double percent = count/scored.size(); 
		return percent* words; 
	 
		
	}
	/**
	 * 
	 * @return scored chars 
	 */
	public ArrayList<Boolean> getScored() {
		return scored; 
	}
	/**
	 * 
	 * @return how long its been 
	 */
	public long getTime() {
		return endTime-startTime;
	}
	/**
	 * 
	 * @return if the game is still on
	 */
	public boolean gameOn() {
		return play;
	}
	/**
	 * 
	 * @return user text
	 */
	public String getUser() {
		return user;
	}
	/**
	 * 
	 * @return og text 
	 */
	public String getFile() {
		return text; 
	}
	/**
	 * change user text completely 
	 * @param s  new string 
	 */
	public void setUser(String s) {
		user = s;
	}
	/**
	 * 
	 * @return it ends the game if the timer's up and returns the score 
	 */
	public double end() {
		if (System.currentTimeMillis() - startTime >= 60000) { //ends after a minute 
			endTime = System.currentTimeMillis();
			play = false;
			return getScore();
			
		}
		return 0;
	}
	
	
}
