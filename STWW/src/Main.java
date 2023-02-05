import java.awt.Color;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class Main {
	//Here we created some of our variables
	
	static int x_coordinate=0;
	static int y_coordinate=0;
	static int[][] GameArea = new int[23][55];
	private static Clip clip = null;
	static enigma.console.Console cn = Enigma.getConsole("Star Trek Warp Wars", 80, 24, 20, 10);
	public static int cursorQueuex=57;
	public static int cursorQueuey=2;
	public static int movingNumCounter = 0;
	public static int computerCounter = 0;
	public static int deviceCounter = 0;
	static int cursorBackpx=67;
	static int cursorBackpy=12;
	public static Random rnd = new Random();
	public static int thread_counter = 0;
	public static long startTime = 0;
	public static long elapsedTime = 0;
	public static MovingNumbers[] four_five = new MovingNumbers[1000];
	public static Player[] computers = new Player[1000];
	public static Devices[] devices = new Devices[1000];
	public static String[] queueArr = new String[1000];
	public static Player player = new Player();
	//This method is for reading Game area from text and assigning the values to Game Array
	public static void readingGameArea() throws IOException {
		Path mapPath = Paths.get("map.txt");
		Scanner mapScan = new Scanner(mapPath);
		String temp = " ";
		int lineCount = 0;
		while (mapScan.hasNextLine() && lineCount < 23) {
			temp = " ";
			char ch;
			temp = mapScan.nextLine();
			for (int i = 0; i < 55; i++) {
				ch = temp.charAt(i);
				if (ch == '#') {
					GameArea[lineCount][i] = -10;
				} else {
					GameArea[lineCount][i] = 0;
				}
			}
			lineCount++;
		}
		mapScan.close();
	}

	public static void main(String[] args) throws Exception {
		
		readingGameArea();
		TextAttributes attrs = new TextAttributes(Color.LIGHT_GRAY, Color.BLACK);//Here we added some additional features
        cn.setTextAttributes(attrs);	
        cn.getTextWindow().setCursorPosition(0, 3);
        System.out.println("\r\n"
				+ "\t ███████╗████████╗ █████╗ ██████╗     ████████╗██████╗ ███████╗██╗  ██╗    \r\n"
				+ "\t ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗    ╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    \r\n"
				+ "\t ███████╗   ██║   ███████║██████╔╝       ██║   ██████╔╝█████╗  █████╔╝     \r\n"
				+ "\t ╚════██║   ██║   ██╔══██║██╔══██╗       ██║   ██╔══██╗██╔══╝  ██╔═██╗     \r\n"
				+ "\t ███████║   ██║   ██║  ██║██║  ██║       ██║   ██║  ██║███████╗██║  ██╗    \r\n"
				+ "\t ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝    \r\n"
				+ "\t                                                                           \r\n"
				+ "\t ██╗    ██╗ █████╗ ██████╗ ██████╗     ██╗    ██╗ █████╗ ██████╗ ███████╗  \r\n"
				+ "\t ██║    ██║██╔══██╗██╔══██╗██╔══██╗    ██║    ██║██╔══██╗██╔══██╗██╔════╝  \r\n"
				+ "\t ██║ █╗ ██║███████║██████╔╝██████╔╝    ██║ █╗ ██║███████║██████╔╝███████╗  \r\n"
				+ "\t ██║███╗██║██╔══██║██╔══██╗██╔═══╝     ██║███╗██║██╔══██║██╔══██╗╚════██║  \r\n"
				+ "\t ╚███╔███╔╝██║  ██║██║  ██║██║         ╚███╔███╔╝██║  ██║██║  ██║███████║  \r\n"
				+ "\t ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝          ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝  \r\n"
				+ "                                                                          \r\n"
				+ "");
		
		
		TextAttributes attrsss = new TextAttributes(Color.ORANGE, Color.BLACK);
		cn.setTextAttributes(attrsss);
		cn.getTextWindow().setCursorPosition(29, 20);
		playSound("/ST.wav");
		System.out.println("Press enter to continue..");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		stopSound(clip);
		TextAttributes attrss = new TextAttributes(Color.WHITE, Color.BLACK);
		cn.setTextAttributes(attrss);
		//Here we are starting the game
		Game myGame = new Game();
		gameScreen();
		//This is our random method
		Random rnd = new Random();
		
		
		cursorQueuex=57;
		boolean gameOver = false;//This is a boolean for controlling the while loop
		int count=0;
		CircularQueue inputQ=new CircularQueue(15); //This is our input queue
		CircularQueue tempQ=new CircularQueue(15);
		
		//System.out.println(Arrays.toString(queueArr));
		for(int i=0;i<20&&placedControl();i++) {	
			queueAdder(inputQ);	
			cn.getTextWindow().setCursorPosition(cursorQueuex, cursorQueuey);
			queueArr[count]=(String)inputQ.peek();
			if((boolean)inputQ.peek().toString().equalsIgnoreCase("4") || (boolean)inputQ.peek().toString().equalsIgnoreCase("5")) {
				four_five[movingNumCounter] = new MovingNumbers();
				four_five[movingNumCounter].mov_x_coordinate = Main.x_coordinate;
				four_five[movingNumCounter].mov_y_coordinate = Main.y_coordinate;
				if((boolean)inputQ.peek().toString().equalsIgnoreCase("4")) {
					four_five[movingNumCounter].mov_num_point = 4;
				}
				else if((boolean)inputQ.peek().toString().equalsIgnoreCase("5")) {
					four_five[movingNumCounter].mov_num_point = 5;
				}
				movingNumCounter++;
			}
			else if((boolean)inputQ.peek().toString().equalsIgnoreCase("C")){
				computers[computerCounter] = new Player();
				computers[computerCounter].x_coordinate = Main.x_coordinate;
				computers[computerCounter].y_coordinate = Main.y_coordinate;
				computerCounter ++;
			}
			else if((boolean)inputQ.peek().toString().equalsIgnoreCase("=") || (boolean)inputQ.peek().toString().equalsIgnoreCase("*")) {
				devices[deviceCounter] = new Devices();
				devices[deviceCounter].x_coordinate = Main.x_coordinate;
				devices[deviceCounter].y_coordinate = Main.y_coordinate;
				if((boolean)inputQ.peek().toString().equalsIgnoreCase("=")) {
					devices[deviceCounter].name = "=";
				}
				else if((boolean)inputQ.peek().toString().equalsIgnoreCase("*")){
					devices[deviceCounter].name = "*";
				}
				deviceCounter++;
			}
			queueToGame(inputQ);
			cn.getTextWindow().setCursorPosition(Main.x_coordinate, Main.y_coordinate);
			System.out.print(inputQ.peek());			
			inputQ.dequeue();
			count++;
			
		}
		
		
		boolean playerPlaced = false;//This boolean is for placing the player some where empty
		while(!playerPlaced) {
			player.x_coordinate = rnd.nextInt(54) + 1;
			player.y_coordinate = rnd.nextInt(22) + 1;
			if(GameArea[player.y_coordinate][player.x_coordinate] == 0) {
				GameArea[player.y_coordinate][player.x_coordinate] = 100;
				cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
				System.out.print("P");
				playerPlaced = true;
			}
		}
		myGame.keypr = 0;
		startTime = System.currentTimeMillis();
		while(!gameOver) {
			while(!inputQ.isFull()) {	
				queueAdder(inputQ);					
			}
			
			cursorQueuex=57;
			while(!inputQ.isEmpty()&&!tempQ.isFull()) {
				tempQ.enqueue(inputQ.peek());
				inputQ.dequeue();
			}
			while(!tempQ.isEmpty()&&!inputQ.isFull()) {
				inputQ.enqueue(tempQ.peek());
				tempQ.dequeue();								
			}
			if(myGame.keypr == 1 && player.MoveTime()) {//In this if statement we are comparing the pressed key to possibility of move
				Player.LeftP(player, myGame);
				Player.RightP(player, myGame);
				Player.DownP(player, myGame);
				Player.UpP(player, myGame);
				
				if((!Player.backpack.isFull())&&(Player.LeftBackpack(player, myGame)||Player.RightBackpack(player, myGame)||Player.DownBackpack(player, myGame)||Player.UpBackpack(player, myGame))) {			
					Player.LeftBackpack(player, myGame);
					Player.RightBackpack(player, myGame);
					Player.DownBackpack(player, myGame);
					Player.UpBackpack(player, myGame);
					printBackpack();
					
					
				}
			
				Player.LeftRemoveElement(player, myGame);
				myGame.keypr = 0;
			}
		
			
			if(thread_counter % 4 == 0 && player.energy > 0) {
				player.energy = player.energy - 1;
				
			}
			if(thread_counter % 12 == 0 &&placedControl()) {// computer pushlancak 
				cn.getTextWindow().setCursorPosition(cursorQueuex, cursorQueuey);
				queueArr[count]=(String)inputQ.peek();
				if((boolean)inputQ.peek().toString().equalsIgnoreCase("4") || (boolean)inputQ.peek().toString().equalsIgnoreCase("5")) {
					four_five[movingNumCounter] = new MovingNumbers();
					four_five[movingNumCounter].mov_x_coordinate = Main.x_coordinate;
					four_five[movingNumCounter].mov_y_coordinate = Main.y_coordinate;
					if((boolean)inputQ.peek().toString().equalsIgnoreCase("4")) {
						four_five[movingNumCounter].mov_num_point = 4;
					}
					else if((boolean)inputQ.peek().toString().equalsIgnoreCase("5")) {
						four_five[movingNumCounter].mov_num_point = 5;
					}
					movingNumCounter++;
				}
				else if((boolean)inputQ.peek().toString().equalsIgnoreCase("C")){
					computers[computerCounter] = new Player();
					computers[computerCounter].x_coordinate = Main.x_coordinate;
					computers[computerCounter].y_coordinate = Main.y_coordinate;
					computerCounter ++;
				}
				else if((boolean)inputQ.peek().toString().equalsIgnoreCase("=") || (boolean)inputQ.peek().toString().equalsIgnoreCase("*")) {
					devices[deviceCounter] = new Devices();
					devices[deviceCounter].x_coordinate = Main.x_coordinate;
					devices[deviceCounter].y_coordinate = Main.y_coordinate;
					if((boolean)inputQ.peek().toString().equalsIgnoreCase("=")) {
						devices[deviceCounter].name = "=";
					}
					else if((boolean)inputQ.peek().toString().equalsIgnoreCase("*")){
						devices[deviceCounter].name = "*";
					}
					deviceCounter++;
				}
				queueToGame(inputQ);
				cn.getTextWindow().setCursorPosition(Main.x_coordinate, Main.y_coordinate);
				System.out.print(inputQ.peek());
				MovingNumbersArea();
				inputQ.dequeue();
				count++;
				
			}
			if(thread_counter % 4 == 0) {
				
				computerMove();
				
			}
			if(thread_counter % 4 == 0) {
				for (int i = 0; i < deviceCounter; i++) {
					if(devices[i] != null) {
						if((boolean)devices[i].name.toString().equalsIgnoreCase("=")) {
							if(devices[i].time > 0) {
								devices[i].time --;
							}
							else if (devices[i].time == 0){
								cn.getTextWindow().setCursorPosition(devices[i].x_coordinate, devices[i].y_coordinate);
								System.out.print(" ");
								GameArea[devices[i].y_coordinate][devices[i].x_coordinate] = 0;
								devices[i] = null;
								
							}
						}
						else if((boolean)devices[i].name.toString().equalsIgnoreCase("*")) {
							boolean flag = false;
							int[] tempArr = new int[9];
							int arrCounter = 0;
							for (int j = devices[i].y_coordinate - 1; j <= devices[i].y_coordinate + 1; j++) {
								for (int k = devices[i].x_coordinate - 1; k < devices[i].x_coordinate + 1; k++) {
									if(GameArea[j][k] != 0) {
										flag = true;
									}
									tempArr[arrCounter] = GameArea[j][k];
									arrCounter++;
								}
							}
							if(flag) {
								for (int j = 0; j < 9; j++) {
									if(tempArr[j] == 1) {
										player.score += 1;
									}
									else if(tempArr[j] == 2) {
										player.score += 5;
									}
									else if(tempArr[j] == 3) {
										player.score += 15;
									}
									else if(tempArr[j] == 4) {
										player.score += 50;
									}
									else if(tempArr[j] == 5) {
										player.score += 150;
									}
									else if(tempArr[j] == 8) {
										player.score += 300;
									}
								}
								cn.getTextWindow().setCursorPosition(devices[i].x_coordinate, devices[i].y_coordinate);
								System.out.print(" ");
								GameArea[devices[i].y_coordinate][devices[i].x_coordinate] = 0;
								devices[i] = null;
								
							}
							else if(!flag && devices[i].time > 0) {
								devices[i].time --;
							}
							else if(!flag && devices[i].time == 0) {
								cn.getTextWindow().setCursorPosition(devices[i].x_coordinate, devices[i].y_coordinate);
								System.out.print(" ");
								GameArea[devices[i].y_coordinate][devices[i].x_coordinate] = 0;
								devices[i] = null;
								
							}
						}
					}
				}
			}
			cursorQueuex=57;
			while(!inputQ.isEmpty()&&!tempQ.isFull()) {
				cn.getTextWindow().setCursorPosition(cursorQueuex, cursorQueuey);
				System.out.print(inputQ.peek());
				tempQ.enqueue(inputQ.peek());
				inputQ.dequeue();
				cursorQueuex++;
				
			}
			while(!tempQ.isEmpty()&&!inputQ.isFull()) {
				inputQ.enqueue(tempQ.dequeue());
				//cursorQueuex++;
				
			}
			
			cn.getTextWindow().setCursorPosition(67 , 17);
			System.out.println(player.score);
			cn.getTextWindow().setCursorPosition(67 , 18);
			System.out.println(player.life);
			cn.getTextWindow().setCursorPosition(67 , 16);
			System.out.println(player.energy);
			cn.getTextWindow().setCursorPosition(67 , 22);
			System.out.print(elapsedTime / 1000);
			myGame.rkey= 0;
			Thread.sleep(250);
			thread_counter ++;
			elapsedTime = System.currentTimeMillis() - startTime;
			
		   
			
			
		}
		
		
		}
	static void printBackpack() {	
		
		cn.getTextWindow().setCursorPosition(cursorBackpx, cursorBackpy);
		System.out.println(Player.backpack.peek());
		cursorBackpy--;
		
	}
	
	static void queueAdder(CircularQueue inputQ) {		
			int queueRnd=rnd.nextInt(40)+1;
			if(queueRnd>=1&&queueRnd<=12) {
				inputQ.enqueue("1");
			}
			else if(queueRnd>12&&queueRnd<=20) {
				inputQ.enqueue("2");
			}
			else if(queueRnd>20&&queueRnd<=26) {
				inputQ.enqueue("3");
			}
			else if(queueRnd>26&&queueRnd<=31) {
				inputQ.enqueue("4");
			}
			else if(queueRnd>31&&queueRnd<=35) {
				inputQ.enqueue("5");
			}
			else if(queueRnd>35&&queueRnd<=37) {
				inputQ.enqueue("=");
			}
			else if(queueRnd>37&&queueRnd<=38) {
				inputQ.enqueue("*");
			}
			else if(queueRnd>38&queueRnd<=40) {
				inputQ.enqueue("C");
			}
										
		
	}
	static void queueToGame(CircularQueue inputQ) {
		if((boolean)inputQ.peek().equals("1"))GameArea[Main.y_coordinate][Main.x_coordinate] = 1;
		if((boolean)inputQ.peek().equals("2"))GameArea[Main.y_coordinate][Main.x_coordinate] = 2;
		if((boolean)inputQ.peek().equals("3"))GameArea[Main.y_coordinate][Main.x_coordinate] = 3;
		if((boolean)inputQ.peek().equals("4"))GameArea[Main.y_coordinate][Main.x_coordinate] = 4;
		if((boolean)inputQ.peek().equals("5"))GameArea[Main.y_coordinate][Main.x_coordinate] = 5;
		if((boolean)inputQ.peek().equals("="))GameArea[Main.y_coordinate][Main.x_coordinate] = 6;
		if((boolean)inputQ.peek().equals("*"))GameArea[Main.y_coordinate][Main.x_coordinate] = 7;
		if((boolean)inputQ.peek().equals("C"))GameArea[Main.y_coordinate][Main.x_coordinate] = 8;
	}
	static boolean trapControl(int x_coodinate, int y_coordinate) {
		boolean rt = false;
		for(int i = y_coordinate - 1; i <= y_coordinate + 1; i++) {
			for(int j = x_coordinate - 1; j <= x_coordinate + 1; j++) {
				if(GameArea[i][j] == 6) {
					rt = true;
				}
			}
		}
		return rt;
	}
	static void MovingNumbersArea() {		
		for (int i = 0; i < movingNumCounter; i++) {
			if(four_five[i] != null && !trapControl(four_five[i].mov_x_coordinate, four_five[i].mov_y_coordinate)) {
				boolean placed=false;
				while(!placed) {
				cn.getTextWindow().setCursorPosition(four_five[i].mov_x_coordinate, four_five[i].mov_y_coordinate);
				System.out.print(" ");
				GameArea[four_five[i].mov_y_coordinate][four_five[i].mov_x_coordinate] = 0;
				
					
					Random rnd = new Random();
					int tempint = rnd.nextInt(4);
							
					if(tempint==0&&upControl(i)) {
						four_five[i].mov_y_coordinate--;
						placed=true;
					}
					else if(tempint== 1&&rightControl(i)) {
						four_five[i].mov_x_coordinate++;
						placed=true;
					}
					else if(tempint== 2&&downControl(i)) {
						four_five[i].mov_y_coordinate++;
						placed=true;
					}
					else if(tempint== 3&&leftControl(i)) {
						four_five[i].mov_x_coordinate--;
						placed=true;
					}
					
				
				cn.getTextWindow().setCursorPosition(four_five[i].mov_x_coordinate, four_five[i].mov_y_coordinate);
				System.out.print(four_five[i].mov_num_point);
				GameArea[four_five[i].mov_y_coordinate][four_five[i].mov_x_coordinate] = four_five[i].mov_num_point;
				}
			}
				
		}
	}
	
	static void computerMove() {
		for (int i = 0; i < computerCounter; i++) {
			if(computers[i] != null && !trapControl(computers[i].x_coordinate,computers[i].y_coordinate)) {
				int x_distance = Math.abs(player.x_coordinate - computers[i].x_coordinate);
				int y_distance = Math.abs(player.y_coordinate - computers[i].y_coordinate);
				
				if(y_distance >= x_distance) {
					
					if(player.y_coordinate - computers[i].y_coordinate < 0 && GameArea[computers[i].y_coordinate - 1][computers[i].x_coordinate] == 0){
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print(" ");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 0;
						computers[i].y_coordinate --;
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print("C");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 8;
					}
					
					else if(player.y_coordinate - computers[i].y_coordinate > 0 && GameArea[computers[i].y_coordinate + 1][computers[i].x_coordinate] == 0) {
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print(" ");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 0;
						computers[i].y_coordinate ++;
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print("C");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 8;
					}
					
					
				}
				else{
					if(player.x_coordinate - computers[i].x_coordinate < 0 && GameArea[computers[i].y_coordinate][computers[i].x_coordinate - 1] == 0){
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print(" ");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 0;
						computers[i].x_coordinate --;
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print("C");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 8;
					}
					
					else if(player.x_coordinate - computers[i].x_coordinate > 0 && GameArea[computers[i].y_coordinate][computers[i].x_coordinate + 1] == 0) {
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print(" ");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 0;
						computers[i].x_coordinate ++;
						cn.getTextWindow().setCursorPosition(computers[i].x_coordinate,computers[i].y_coordinate);
						System.out.print("C");
						GameArea[computers[i].y_coordinate][computers[i].x_coordinate] = 8;
					}
				}
			}
		}
	}
	static boolean rightControl(int i) {
		boolean placed = false;	
		if(GameArea[four_five[i].mov_y_coordinate][four_five[i].mov_x_coordinate+1] == 0) {											
			placed = true;
		}
		return placed;
	}
	static boolean downControl(int i) {
		boolean placed = false;
		if(GameArea[four_five[i].mov_y_coordinate+1][four_five[i].mov_x_coordinate] == 0) {									
			placed = true;
		}
		
		return placed;
	}
	static boolean leftControl(int i) {
		boolean placed = false;				
		if(GameArea[four_five[i].mov_y_coordinate][four_five[i].mov_x_coordinate+1] == 0) {										
			placed = true;
		}
		
		return placed;
	}
	static boolean upControl(int i) {
		boolean placed = false;	
		if(GameArea[four_five[i].mov_y_coordinate-1][four_five[i].mov_x_coordinate] == 0) {							
			placed = true;
		}
		
		return placed;
	}
	static boolean placedControl() {
		boolean placed = false;
		while(!placed) {
			Main.x_coordinate = rnd.nextInt(54) + 1;
			Main.y_coordinate = rnd.nextInt(22) + 1;
			if(GameArea[Main.y_coordinate][Main.x_coordinate] == 0) {				
							
				placed = true;
			}
		}
		return placed;
	}
	
		
	static void playSound(String soundFile)//This is a method for playing sounds
			throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		File f = new File("./" + soundFile);
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();

	}

	public static void stopSound(Clip clip) {
		clip.stop();
	}
	public static void consoleClear() {//This is a console clear method
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int k = 0; k < 100; k++) {
			for (int k2 = 0; k2 < 100; k2++) {
				System.out.print(" ");
			}
			System.out.println(" ");
		}
		cn.getTextWindow().setCursorPosition(0, 0);		
	}
	public static void gameScreen() throws IOException {//This is a method for printing game screen
		consoleClear();
	  
	  //cursor queue numbers for printing on the screen.
	  int cursorQueuex=57;
	  int cursorQueuey=2;
	  
	  System.out.println(new String(Files.readAllBytes(Paths.get("map.txt"))));
	  cn.getTextWindow().setCursorPosition(cursorQueuex, 0);
	  System.out.println("Input");
	  cn.getTextWindow().setCursorPosition(cursorQueuex, 1);
	  System.out.println("<<<<<<<<<<<<<<<");
	  cn.getTextWindow().setCursorPosition(cursorQueuex, 3);
	  System.out.println("<<<<<<<<<<<<<<<");
	  
	  int cursorBackpx=65;
	  int cursorBackpy=4;
	  for(int i=0;i<8;i++) {
		  cursorBackpy++;
		  cn.getTextWindow().setCursorPosition(cursorBackpx, cursorBackpy);
		  System.out.println("|   |");
		  
		  
	  }
	  cn.getTextWindow().setCursorPosition(cursorBackpx, cursorBackpy+1);
	  System.out.println("+---+");
	  cn.getTextWindow().setCursorPosition(cursorBackpx-2, cursorBackpy+2);
	  System.out.println("P.Backback");
	  
	  cn.getTextWindow().setCursorPosition(cursorQueuex, cursorBackpy+4);
	  System.out.println("P.Energy: ");
	  cn.getTextWindow().setCursorPosition(cursorQueuex, cursorBackpy+5);
	  System.out.println("P.Score : ");
	  cn.getTextWindow().setCursorPosition(cursorQueuex, cursorBackpy+6);
	  System.out.println("P.Life  : ");
	  cn.getTextWindow().setCursorPosition(cursorQueuex, cursorBackpy+8);
	  System.out.println("C.Score : ");
	  cn.getTextWindow().setCursorPosition(cursorQueuex, cursorBackpy+10);
	  System.out.println("Time    : ");
	
}
	
}
