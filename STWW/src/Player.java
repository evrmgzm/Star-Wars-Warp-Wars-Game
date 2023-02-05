import java.awt.event.KeyEvent;
import java.time.Instant;

public class Player {
	//Here we created some object elements this area will be filled with timer
	public int x_coordinate = 0;
	public int y_coordinate = 0;
	public int energy = 50;
	public int score = 0;
	public int life = 5;
	static Stack backpack=new Stack(8);
	public boolean MoveTime() {
		boolean rt = false;
		if(Main.thread_counter % 2 >= 0 && Main.thread_counter % 2 <= 1 && energy > 0 ) {
			rt = true;
		}
		else if(Main.thread_counter % 4 >= 0 && Main.thread_counter % 4 <= 1)
		{
			rt = true;
		}
		return rt;
	}
	public static boolean Backpack(String input) {		
		
		Player.backpack.push(input);
		
		return true;
	}
	public static void LeftRemoveElement(Player player, Game myGame) {
		if((!Player.backpack.isEmpty())&&myGame.rkey == KeyEvent.VK_A && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 0) {			
			int tempx = player.x_coordinate;
			tempx --;
			Main.cn.getTextWindow().setCursorPosition(tempx, player.y_coordinate);
			
			if(((String)Player.backpack.peek()).equals("=")){
				Main.GameArea[player.y_coordinate][tempx] = 6;
				System.out.print("=");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempx;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "=";
				Main.deviceCounter ++;
				
				}
			if(((String)Player.backpack.peek()).equals("*")){
				Main.GameArea[player.y_coordinate][tempx] = 7;
				System.out.print("*");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempx;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "*";
				Main.deviceCounter ++;
				}
		}	
	}
	public static void RightRemoveElement(Player player, Game myGame) {
		if((!Player.backpack.isEmpty())&&myGame.rkey == KeyEvent.VK_D && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 0&&(!Player.backpack.isEmpty())) {
			int tempx = player.x_coordinate;
			tempx --;
			Main.cn.getTextWindow().setCursorPosition(tempx, player.y_coordinate);
			
			if(((String)Player.backpack.peek()).equals("=")){
				Main.GameArea[player.y_coordinate][tempx] = 6;
				System.out.print("=");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempx;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "=";
				Main.deviceCounter ++;
				
				}
			if(((String)Player.backpack.peek()).equals("*")){
				Main.GameArea[player.y_coordinate][tempx] = 7;
				System.out.print("*");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempx;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "*";
				Main.deviceCounter ++;
				}
		}
	}
	public static void DownRemoveElement(Player player, Game myGame) {
		if((!Player.backpack.isEmpty())&&myGame.rkey == KeyEvent.VK_S && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 0&&(!Player.backpack.isEmpty())) {
			int tempy = player.x_coordinate;
			tempy ++;
			Main.cn.getTextWindow().setCursorPosition(tempy, player.y_coordinate);
			
			if(((String)Player.backpack.peek()).equals("=")){
				Main.GameArea[player.y_coordinate][tempy] = 6;
				System.out.print("=");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempy;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "=";
				Main.deviceCounter ++;
				
				}
			if(((String)Player.backpack.peek()).equals("*")){
				Main.GameArea[player.y_coordinate][tempy] = 7;
				System.out.print("*");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempy;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "*";
				Main.deviceCounter ++;
				}
		}
	}
	public static void UpRemoveElement(Player player, Game myGame) {
		if((!Player.backpack.isEmpty())&&myGame.rkey == KeyEvent.VK_W && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 0&&(!Player.backpack.isEmpty())) {
			int tempy = player.x_coordinate;
			tempy --;
			Main.cn.getTextWindow().setCursorPosition(tempy, player.y_coordinate);
			
			if(((String)Player.backpack.peek()).equals("=")){
				Main.GameArea[player.y_coordinate][tempy] = 6;
				System.out.print("=");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempy;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "=";
				Main.deviceCounter ++;
				
				}
			if(((String)Player.backpack.peek()).equals("*")){
				Main.GameArea[player.y_coordinate][tempy] = 7;
				System.out.print("*");
				Player.backpack.pop();
				Main.devices[Main.deviceCounter] = new Devices();
				Main.devices[Main.deviceCounter].x_coordinate = tempy;
				Main.devices[Main.deviceCounter].y_coordinate = player.y_coordinate;
				Main.devices[Main.deviceCounter].name = "*";
				Main.deviceCounter ++;
				}
		}
	}
	public static boolean LeftBackpack(Player player, Game myGame) {
		boolean rt = false;
		if(myGame.rkey == KeyEvent.VK_LEFT && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 1) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("1");
			player.score += 1;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_LEFT && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 2) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("2");
			player.score += 5;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_LEFT && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 3) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("3");
			player.score += 15;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_LEFT && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 4) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("4");
			player.score += 50;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_LEFT && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 5) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("5");
			player.score += 150;
			rt = true;
			}
		return rt;
		
	}
	public static boolean RightBackpack(Player player, Game myGame) {
		boolean rt = false;
		if(myGame.rkey == KeyEvent.VK_RIGHT && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 1) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("1");
			player.score += 1;
			rt=true;
			}
		if(myGame.rkey == KeyEvent.VK_RIGHT && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 2) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("2");
			player.score += 5;
			
			rt=true;
			}
		if(myGame.rkey == KeyEvent.VK_RIGHT && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 3) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("3");
			player.score += 15;
			rt=true;
			}
		if(myGame.rkey == KeyEvent.VK_RIGHT && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 4) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("4");
			player.score += 50;
			rt=true;
			}
		if(myGame.rkey == KeyEvent.VK_RIGHT && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 5) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("5");
			player.score += 150;
			rt=true;
			}
		return rt;
	}
	public static boolean DownBackpack(Player player, Game myGame) {
		boolean rt = false;
		if(myGame.rkey == KeyEvent.VK_DOWN && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 1) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("1");
			player.score += 1;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_DOWN && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 2) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("2");
			player.score += 5;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_DOWN && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 3) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("3");
			player.score += 15;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_DOWN && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 4) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("4");
			player.score += 50;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_DOWN && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 5) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("5");
			rt = true;
			player.score += 150;
			}
		return rt;
	}
	public static boolean UpBackpack(Player player, Game myGame) {
		boolean rt = false;
		if(myGame.rkey == KeyEvent.VK_UP && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 1) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("1");
			player.score += 1;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_UP && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 2) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("2");
			player.score += 5;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_UP && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 3) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("3");
			player.score += 15;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_UP && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 4) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("4");
			player.score += 50;
			rt = true;
			}
		if(myGame.rkey == KeyEvent.VK_UP && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 5) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			Player.Backpack("5");
			player.score += 150;
			rt = true;
			}
		return rt;
	}
	public static void LeftP(Player player, Game myGame) {
		if(myGame.rkey == KeyEvent.VK_LEFT && Main.GameArea[player.y_coordinate][player.x_coordinate - 1] == 0) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			}
	}
	public static void RightP(Player player, Game myGame) {
		if(myGame.rkey == KeyEvent.VK_RIGHT && Main.GameArea[player.y_coordinate][player.x_coordinate + 1] == 0) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.x_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			}
	}
	public static void DownP(Player player, Game myGame) {
		if(myGame.rkey == KeyEvent.VK_DOWN && Main.GameArea[player.y_coordinate + 1][player.x_coordinate] == 0) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate ++;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P"); 
			}
	}
	public static void UpP(Player player, Game myGame) {
		if(myGame.rkey == KeyEvent.VK_UP && Main.GameArea[player.y_coordinate - 1][player.x_coordinate] == 0) {
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 0;
			System.out.print(" ");
			player.y_coordinate --;
			Main.cn.getTextWindow().setCursorPosition(player.x_coordinate, player.y_coordinate);
			Main.GameArea[player.y_coordinate][player.x_coordinate] = 100;
			System.out.print("P");
			}
	}
	public int BackPackMatching(Stack backpack, int energy) {
        Stack tempbackpack = new Stack(backpack.size());
        while (!backpack.isEmpty()) {
            tempbackpack.push(backpack.peek());
            backpack.pop();
            if (tempbackpack.peek().equals("2") && backpack.peek().equals("2")) {
                tempbackpack.pop();
                backpack.pop();
                energy = energy + 30;
            }
            if (tempbackpack.peek().equals("2") && (backpack.peek().equals("3") || backpack.peek().equals("4") || backpack.peek().equals("5"))) {
                tempbackpack.pop();
                backpack.pop();
            }
            if (tempbackpack.peek().equals("3") && backpack.peek().equals("3")) {
                tempbackpack.pop();
                backpack.pop();
                backpack.push("=");
            }
            if (tempbackpack.peek().equals("3") && (backpack.peek().equals("2") || backpack.peek().equals("4") || backpack.peek().equals("5"))) {
                tempbackpack.pop();
                backpack.pop();
            }
            if (tempbackpack.peek().equals("4") && backpack.peek().equals("4")) {
                tempbackpack.pop();
                backpack.pop();
                energy = energy + 240;
            }
            if (tempbackpack.peek().equals("4") && (backpack.peek().equals("2") || backpack.peek().equals("3") || backpack.peek().equals("5"))) {
                tempbackpack.pop();
                backpack.pop();
            }
            if (tempbackpack.peek().equals("5") && backpack.peek().equals("5")) {
                tempbackpack.pop();
                backpack.pop();
                backpack.push("*");
            }

        }
        return energy;
    }
	
	
	
}
