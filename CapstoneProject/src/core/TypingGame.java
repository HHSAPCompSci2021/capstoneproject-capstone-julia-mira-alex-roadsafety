package core;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

public class TypingGame {
	public final static String lineSeparator = System.getProperty("line.separator");
	private String text; 
	private String file; 
	private String checked; 
	private double score; 
	private Duration d;
	public TypingGame (String inputFile) {
		
		file = inputFile;
		try {
			text = readFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Please select a valid file");
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
		
//		d = new Duration(0, 0); //error with this statement
		
	}
	public double getScore() {
		return score;
	}
}
