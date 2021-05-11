package gym;

import java.util.Scanner;

public class ConsoleUI {
	private static Scanner scanner = new Scanner(System.in);	
	public static String[] DAYS = {"Lun","Mar","Mer","Jeu","Ven","Sam","Dim"};
	
	private static final char BORDER_CHAR = '#';
	private static final char SEPARATOR_CHAR = '-';
	private static final int CONSOLE_WIDTH = 60;
	
	private static final String HEADER_STRING = "[ GYM ]";
	
	// Display : App Header
	public static void printAppHeader() {
		String header = '|'+ center(HEADER_STRING,CONSOLE_WIDTH -2, SEPARATOR_CHAR);
		System.out.println( header );
	}
	
	// Display : Menu
	public static void printMenu(String title, String[] items) {
		// Header
		printMenuHeader(title);
		
		// Items
		for( int i = 0; i < items.length; i++) {
			printMenuItem("["+(i+1)+"] " + items[i]);
		}
		
		// Border
		System.out.println(hFiller());	
	}
	
	// Display : Menu Header
	private static void printMenuHeader( String title ) {		
		String middle = BORDER_CHAR + center(title, CONSOLE_WIDTH - 2, ' ') + BORDER_CHAR + "\n";
		System.out.println( "\n\n" + hFiller() + "\n" + middle + hFiller() );
	}
	
	// Display : Menu Item
	private static void printMenuItem( String item ) {
		String line = BORDER_CHAR + "  " + allignLeft(item, CONSOLE_WIDTH - 4, ' ') + BORDER_CHAR;
		System.out.println( line );
	}
	
	// Display : User Prompt
	public static void printUserPrompt( String prompt ) {
		System.out.println("\n[ "+prompt+" ] :");
	}
	
	// Display : Print feedback message to gym clerk
	public static void printFeedback(String feedback) {
		System.out.println("\n[Message] "+feedback);
	}
		
	// Display : Error message
	public static void printError(String error) {
		System.out.println("\n[Erreur] "+error);
	}
	
	// Display : Horizontal border
	private static String hFiller() {
		return fill(BORDER_CHAR, CONSOLE_WIDTH);
	}
	// String format : Center
    private static String center(String s, int width,char padChar) {       	
    	int room = width - s.length(), leftPad, rightPad; 
    	if(room <= 0) return s;
    	
    	if(room%2 == 0) 
    		leftPad =(room/2); 
    	else 
    		leftPad =(room/2)+1;
    	
    	rightPad = room-leftPad;  
    	
    	return allignRight(s,width-rightPad,padChar) + fill(padChar,rightPad);
    }
    
    // String format : Right Allign
    private static String allignRight(String s, int width,char padChar) {
    	String format = "%1$"+width+"s";
    	return String.format(format, s).replace(' ',padChar);
    }
    
    // String format : Left Allign
    private static String allignLeft(String s, int width,char padChar) {
    	String format = "%1$-"+width+"s";    	
    	return String.format(format, s).replace(' ',padChar);
    }
    
    // String format : Fill
    private static String fill(char c,int width) {
    	String s = "";
    	for(int i = 0; i < width; i++) 
    		s+=c;    	
    	return s;
    }  
    
    // Read
    public static String getInput() {
    	return scanner.nextLine();
    }
    
    // Close
    public static void close() {
    	System.out.println("\n[ Au Revoir ! ]");
    	scanner.close();
    }
    
    public static int dayToIndex(String day) {
    	for(int i = 0; i < 7; i++) {
    		if(DAYS[i].equals(day))
    			return i;
    	}
    	
    	//Default
    	 return -1;
    }   
}
