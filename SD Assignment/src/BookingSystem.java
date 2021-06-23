import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class BookingSystem {
	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(System.in);
		ArrayList<Booking> book= new ArrayList<Booking>();
		ArrayList<User>user= new ArrayList<User>();
		ArrayList<Movie>movielist = new ArrayList<Movie>();
		ArrayList<Admin>Admin = new ArrayList<Admin>();
		BufferedReader breader = null;
		try {
			breader = new BufferedReader (new FileReader("Data.txt"));
			String line =null;
			while((line= breader.readLine()) !=null) {
				String [] userinfo = line.split(",");
				if(userinfo.length ==9) {
					String movie = userinfo[0];
					double price = Double.parseDouble(userinfo[1]);
					int start=Integer.parseInt(userinfo[2]);
					int end=Integer.parseInt(userinfo[3]);
					String code =userinfo[4];
					String seat = userinfo[5];
					boolean stats = Boolean.parseBoolean(userinfo[6]);
					String owner = userinfo[7];
					String id = userinfo[8];
					book.add(new Movie(movie,price,start,end,code,seat,stats,owner,id));
					movielist.add(new Movie(movie,price,start,end,code,seat,stats,owner,id));
				}
				else if(userinfo.length ==7) {
						String name = userinfo[0];
						int ph=Integer.parseInt(userinfo[1]);
						String email = userinfo[2];
						String pw = userinfo[3];
						String address = userinfo[4];
						String fname = userinfo[5];
						String lname = userinfo[6];
						user.add(new User(name,ph,email,pw,address,fname,lname));
						book.add(new User(name,ph,email,pw,address,fname,lname));
					}
				else 
					if(userinfo.length == 8) {
						String adminname = userinfo[0];
						String adminid = userinfo[2];
						String admicode = userinfo[3];
						String admiemail = userinfo[4];
						String adminaddress = userinfo[5];
						int phno = Integer.parseInt(userinfo[1]);
						String Fname = userinfo[6];
						String Lname = userinfo[7];
						book.add(new Admin(adminname,phno,adminid,admicode,admiemail,adminaddress,Fname,Lname));
						Admin.add(new Admin(adminname,phno,adminid,admicode,admiemail,adminaddress,Fname,Lname));
						
					}
					else 
						if(userinfo.length ==4) {
							String ownername = userinfo[0];
							String moviebook = userinfo[1];
							String ticketcode = userinfo[2];
							String seat = userinfo[3];
							book.add(new Ticket(ownername,moviebook,ticketcode,seat));
							
							
						}

			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		breader.close();
		// Counter is for do while loop condition, While count and counter is for checking the Username and Pw KEy in is valid or not
		int Counter=0 , count =0, counter=-1, login =0;
		int dowhilecounterloop=0, loginpage=0;
		do {
			try {
		System.out.println("Welcome to TGV cinema"+"\n--------------------------------------------------------"
				+"\nMain Page"+"\nOption"+"\n1.) Log in to account"+"\n2.) Sign up an account"+"\n3.) Exit"+"\nPlease enter ur choice (1-3):");
		int choice =Integer.parseInt(in.nextLine());

		if(choice ==1) {
					System.out.println("1.) Log in as Client"+"\n2.) Log in as Admin"+"\n3.) Back To Main Page"+"\nPlease enter your choice (1-3)");
						 login= Integer.parseInt(in.nextLine());
							if(login ==1) {
								System.out.println("-------User Account-------\n"+"Please enter username :");
									String name = in.nextLine();
									System.out.println("Please enter the password :");
									String pw = in.nextLine();
		
	for(Booking check : book) {
		
		if(name.contentEquals(check.getName()) && pw.contentEquals(check.getCode()) && check instanceof User) {
			counter =count;
			System.out.println("Log in Successfully");
			int s =0;
			do {
				System.out.println("------USER Main Page------"+"\n-------Option--------"+"\n--------------------------------------------");
				System.out.println("1.) Book Ticket"+"\n2.) Search Ticket"+"\n3.) Delete Booking"+"\n4.) View Booking History"+ "\n5.) Update Information"+"\n6.) Logout");
			
			
				int opt;
				
				System.out.println("Please enter your option (1-6)");
				opt = Integer.parseInt(in.nextLine());
				switch(opt) {
				
				case 1: BookTicket(book,check,in, movielist);	
					break;
				case 2: SearchTicket(book,movielist,in);
					break;
				case 3:	DeleteBooking(book,check,in, movielist);
					break;
				case 4:	CheckBookingHistory(book,check,in);
					break;
				case 5: UserUpdateInformation(book,check,in,user);
					break;
				case 6:s=6;
				
					break;
					default :System.out.println("Error input, pls retry");
				
				
				}
			}while(s !=6);
			
		}
	}
	if(counter ==-1) {
		System.out.println("Username or Password enter is invalid, pls retry");
	}
							}
	else if( login ==2) {
		System.out.println("--------Admin Account-------\n"+"Please enter Admin ID :");
		String adminID = in.nextLine();
		System.out.println("Please enter the password :");
		String password = in.nextLine();
		
			for(Booking admin :book) {
				if(adminID.contentEquals(admin.getMovie()) && password.contentEquals(admin.getCode()) && admin instanceof Admin) {
					counter =count;
					System.out.println("Log in Successfully");
					int stop =0;
					do {
						System.out.println("-----ADMIN Main Page-----"+"\n-------Option--------"+"\n--------------------------------------------");
						System.out.println("1.) Create Ticket"+"\n2.) Display Ticket"+"\n3.) Search User"+"\n4.) Search Movie"+ "\n5.) ADMIN-Book Ticket"+"\n6.) Cancel Booking"+"\n7.) Logout");
						
						System.out.println("Please enter your option (1-7)");
						int opt = Integer.parseInt(in.nextLine());
						switch(opt) {
						
						case 1: CreateTicket(book,admin,in,movielist);
							break;
						case 2: DisplayAllticket(book,admin,in);
							break;
						case 3:	SearchUser(book,admin,in);
							break;
						case 4:	AdminSearchTicket(book,movielist,in);
							break;
						case 5: AdminBookTicket(book,admin,in,movielist);
						break;
						case 6: AdminDeleteBooking(book,admin,in);
						break;
						case 7:stop=7;
						
							break;
							default :System.out.println("Error input, pls retry");
							
						}
						
					}while(stop!=7);
					
				}
				
			}
			if(counter ==-1) {
				System.out.println("Username or Password enter is invalid, pls retry");
			}
		
		
				}
	else if(login ==3) {
		
		System.out.println("Back To Main Page");
		loginpage=3;
	}
	}
	
		
		
		 if(choice ==2) {
			CreateAccount(book,in,Admin,user);
		}
		else if(choice ==3) {
			exit();
		}
			}catch (RuntimeException ex) {
				System.out.println("Input insert is invalid, pls retry");
			}
			
	}while(dowhilecounterloop!=1);
		
	}
	

	
	//Allow the ADMIN to book ticket
	public static void AdminBookTicket(ArrayList<Booking>book, Booking admin,Scanner in,ArrayList<Movie>MOVIE) throws FileNotFoundException {
		int option =0; int time =0;
		int count =0, counter =-1 , moviecount =0, moviecounter=-1, ticketcounter =-1;
		String name =null; String code =null; String location =null;String movieid = null, moviename=null;
	do {
			System.out.println("Please enter the username which require to book :");
			String username=in.nextLine();
		
		for(Booking user :book) {
			if(username.contentEquals(user.getName()) && user instanceof User) {
				System.out.println("User Found");
				user.showinfo();
				counter =count;
				name = user.getName();
		}count++;
		}
		if(counter ==-1) {
			System.out.println("No User Found");
			
		}
		if(counter !=-1) {
			for(Booking b :book) {
				if(b instanceof Movie && !b.getStatus()) {
					
					b.showinfo();
				}
			}
			System.out.println("Current account :" +name);
			System.out.println("Please enter the Movie ID u want to book :");
			movieid= in.nextLine();
			for(Booking movie :book) {
				if(movieid.contentEquals(movie.getID()) && !movie.getStatus()) {
					movie.showinfo();
					moviename= movie.getMovie();
					System.out.println(moviename);
					moviecounter=1;
				}
				
			}
			if(moviecounter ==-1) {
				System.out.println("No Movie Found");
			}
			if(moviecounter !=-1) {
				System.out.println("Please enter the time you wish for :");
			    time = Integer.parseInt(in.nextLine());
				System.out.println("Please enter the seat you wish for :");
				location =in.nextLine();
				
			}
			for( Booking ticket :book) {
				if(time == ticket.getTime() && location.contentEquals(ticket.getLocation()) && moviename.contentEquals(ticket.getMovie()) && !ticket.getStatus() ) {
					ticket.showinfo();
					System.out.println("Is this the movie you want to book? (press 1 to continue) ");
					int confirm = Integer.parseInt(in.nextLine());
					if(confirm ==1) {
						ticketcounter = count;
						code = ticket.getCode();
						location = ticket.getLocation();
						
						ticket.setName(name);
						ticket.status(true);
						for(Movie movie :MOVIE) {
							movie.setBookStatus(true);
							movie.setOwner(name);
						}
					}
				}
			}
			
			if(ticketcounter !=-1) {
				CreateRecipt(book,name,moviename,code,location);
			}
		}
		System.out.println("Is there any more movie you want to book? (press 1 to continue, 2 to back to Main Page) ");
		option =Integer.parseInt(in.nextLine());
	}while(option !=2);
	
		System.out.println("Back to ADMIN Main Page");
		
		
		
	}
	
	
	
	// Allow the user to book the ticket 
	public static void BookTicket(ArrayList<Booking>book, Booking user,Scanner in, ArrayList<Movie>MOVIE) throws FileNotFoundException {
	//DATA POOL
	int counter=-1, found=-1;
	String name =user.getName();
	String movieid=null; 
	String moviename=null;
	String code =null;
	String location =null;
	
	for(Booking b :book) {
		if(b instanceof Movie && !b.getStatus()) {
			b.showinfo();
		}
	}
	
	//Require user to enter movie ID
	while(counter==-1) {
		
		System.out.println("Please enter the movie ID you want (exp:AXX) :");
		movieid = in.nextLine();
		
		for(Booking ticket :book) {
			if(movieid.contentEquals(ticket.getID())  && ticket instanceof Movie && !ticket.getStatus()) {
				ticket.showinfo();
				found=1;
				System.out.println("Is this the move you want to book? (If yes press 1 else press 2) :");
				int confirmation = Integer.parseInt(in.nextLine());
				if(confirmation==1) {
					counter=1;
					moviename = ticket.getMovie();
					code =ticket.getCode();
					location= ticket.getLocation();
					ticket.setName(name);
					ticket.status(true);
					
				}
				else {
					System.out.println("Press 1 to book again, 2 to exit");
					int choice = Integer.parseInt(in.nextLine());
					if(choice ==1) {
						counter=-1;
					}
					else {
						counter=1;
					}
				}
			}
			
			
		}
		if(found==-1) {
			System.out.println("Press 1 to book again, else press 2 to exit :");
			int choice = Integer.parseInt(in.nextLine());
			if(choice ==1) {
				counter=-1;
			}
			else {
				counter=1;
			}
		}
		
	}
		
	if(counter==1) {
		 CreateRecipt(book, name,  moviename,  code,  location);
	}
	
	
	}
	
	// Allow USER or ADMIN to search for the ticket
	public static void SearchTicket(ArrayList<Booking>book,ArrayList<Movie>movie, Scanner in) {
		int count =0, counter =-1, opt=0; // conditions 
		do {
		System.out.println("Search By\n---------------------\n"+"1.) Movie Name" +"\n2.) Time" +"\n3.) All Currently Available"+"\n4.) Exit");
		int choice = Integer.parseInt(in.nextLine());
		
		switch(choice) 
		{
		case 1:
			System.out.println("Search by Movie Name\n-------\n"+"Please enter the name of the movie :");
			String moviename = in.nextLine();
		for(Booking ticket :book) {
			
			if(moviename.contentEquals(ticket.getMovie()) && !ticket.getStatus() && ticket instanceof Movie) {
				ticket.showinfo();
				counter =count;
			}
			count ++;
		}if(counter ==-1) {
			System.out.println("Movie searched is not found");
		}
		
			break;
		case 2:
			System.out.println("Search by Time\n----------\n"+"Please enter the time you wish for :");
			int time= Integer.parseInt(in.nextLine());
			System.out.println("Currently Available");
				for(Movie m :movie) {
					if(time == m.getStarttime() && !m.getStatus()) {
						m.showinfo();
						counter =count;
					}count ++;

				}if(counter ==-1) {
					System.out.println("No Movie showing for the time you wish for ");
				}
				
			
			break;
		case 3:
			System.out.println("Search by Currently Available\n---------\n"+"Currently Available");
			
			for(Booking b : book ) {
				if(!b.getStatus()&& b instanceof Movie) {
					b.showinfo();
					counter =count;
				}
			}
			
			break;
		case 4:
			opt =4;
			System.out.println("Back to USER Main Page");
			break;
				default :System.out.println("error input, pls retry");
			
			
		}
				
	}while(opt!=4);
	}
	
	//Allow the ADMIN to delete the booking ticket, Assume that this FUNCTION is process with USER permission ALREADY//
	//Furthermore ASSSUME ADMIN already ask the ticket code from the user, which means USER already give permission to ADMIN to delete their BOOKING TICKET//
	public static void AdminDeleteBooking(ArrayList<Booking>book,Booking admin,Scanner in) throws FileNotFoundException {
		int option =0; int confirmation=0; int found =-1;
		int count =0, counter =-1;
		String name =null; 
		do {
		System.out.println("Current ADMIN Account :"+admin.getName()+"\nPlease enter the Username to track the booking history :");
		name = in.nextLine();
		for(Booking user :book) {
			if(name.contentEquals(user.getName()) && user instanceof User) {
				System.out.println("Ticket Found");
				user.showinfo();
				counter =count;
				name = user.getName();
		}count++;
		}
		if(counter ==-1) {
			System.out.println("No User Found");
			
		}
		if(counter !=-1) {
			System.out.println("Current ADMIN Account :"+admin.getName()+"Current User Account :"+ name);
			System.out.println("Please enter ur ticket code to delete the booking :");
			String code = in.nextLine();
		for( int i=0; i<book.size();i++) {
			Booking b = book.get(i);
			if(b.getName().contentEquals(name) && code.contentEquals(b.getCode())&& b instanceof Ticket ) {
				System.out.println("Is this the booking you want to delete ? (press 1 to confirm)");
				b.showinfo();
				confirmation = Integer.parseInt(in.nextLine());
				if(confirmation ==1) {
					found++;
				}
				if(found!=-1) {
					book.remove(b);
					for(Booking movie :book) {
						if(confirmation ==1) {
							if(movie.getName().contentEquals(name) && code.contentEquals(movie.getCode())&& movie instanceof Movie) {
								movie.setName("Unbook");
								movie.status(false);
							}
						}
					}
					PrintWriter update = new PrintWriter("Data.txt");
					for(Booking booked :book)
						update.println(booked.totostring());
						update.close();
				}
			}
		}
		
	}
		System.out.println("Any more ticket to delete? (press 1 to continue, 2 to back to Main Page");
		option = Integer.parseInt(in.nextLine());
		
		}while(option !=2);
		System.out.println("Back To ADMIN Main Page");
	}
	
	
	// allow the user to delete their booking ticket
	public static void DeleteBooking(ArrayList<Booking>book, Booking user, Scanner in, ArrayList<Movie>MOVIE) throws FileNotFoundException {
	
		int found =-1, confirmation=0, option=0;
		do {
		System.out.println("Current account :"+ user.getName());
		System.out.println("Please enter ur ticket code to delete the booking :");
		String code = in.nextLine();
		for(int i=0; i<book.size(); i++) {
			Booking b = book.get(i);
			
			if(b.getName().contentEquals(user.getName()) && code.contentEquals(b.getCode())&& b instanceof Ticket ) {
				System.out.println("Is this the booking you want to delete ? (press 1 to confirm)");
				b.showinfo();
				confirmation =Integer.parseInt(in.nextLine());
				if(confirmation ==1) {
					found++;
				}
				
				if(found !=-1) {
					book.remove(b);
					for(Booking movie :book) {
						if(confirmation ==1) {
							if(movie.getName().contentEquals(user.getName()) && code.contentEquals(movie.getCode())&& movie instanceof Movie) {
								movie.setName("Unbook");
								movie.status(false);
								for(Movie M : MOVIE) {
									M.setBookStatus(false);
									M.setOwner("Unbook");
								}
							}
						}
					}
					PrintWriter update = new PrintWriter("Data.txt");
					for(Booking booked :book)
						update.println(booked.totostring());
						update.close();
			}
		}
	
		}
			if(found==-1) {
					System.out.println("No booking found..");
				}
			
			
			System.out.println("Is there any movie ticket u want to delete? (Press 1 to continue, 2 to exit)");
			int confirm = Integer.parseInt(in.nextLine());
			if(confirm ==1) {
				option=1;
			}
			else {
				option=2;
			}
			
	}while(option!=2);
		System.out.println("Back to ADMIN Main Page");
	}
	
	
	// Allow ADMIN to search for the user
	public static void SearchUser(ArrayList<Booking>book,Booking admin,Scanner in) {
		System.out.println("Admin :" +admin.getName());
		int opt =0, count =0, counter=-1, acount=0, acounter=-1;
		do{
			System.out.println("Please enter the name of user :");
			String username = in.nextLine();
			for(Booking b :book) {
				if(username.contentEquals(b.getName()) && b instanceof User) {
					b.showinfo();
					counter =count;
				}
			}
			for(Booking a :book) {
				if(username.contentEquals(a.getName()) && a instanceof Ticket) {
					a.showinfo();
					acounter=acount;
				}
			}
			
			if(counter==-1) {
				System.out.println("No Record Found");
			}
			if(acounter==-1) {
				System.out.println("No Booking Found");
			}
			System.out.println("Anymore user to search ?(press 1 to continue 2 to Back to ADMIN Main Page) :");
			opt = Integer.parseInt(in.nextLine());
			
		}while(opt!=2);
		System.out.println("Back To ADMIN Main Page");

	}
		
	
	// ADMIN function check all the ticket no matter available or not
	public static void DisplayAllticket(ArrayList<Booking>book, Booking admin,Scanner in) {
		System.out.println("Admin :" +admin.getName());
		for(Booking b :book) {
			if(b instanceof Movie)
			b.showinfo();
		}
	}
	
	// This function is to create a receipt when the booking is done by the user, and it also can prove that the user own the ticket by showing User Name
	public static void CreateRecipt(ArrayList<Booking>book,String name, String moviename, String code, String location) throws FileNotFoundException {
		
		book.add(new Ticket(name,moviename,code,location));
		PrintWriter update = new PrintWriter("Data.txt");
		for(Booking b :book)
			update.println(b.totostring());
			update.close();
			
			
			
			
	}
	
	public static void AdminSearchTicket(ArrayList<Booking>book,ArrayList<Movie>movie, Scanner in) {
		int count =0, counter =-1, opt=0; // conditions 
		do {
			try {
			System.out.println("Search By\n---------------------\n"+"1.) Movie Name" +"\n2.) Time" +"\n3.) Currently Available"+"\n4.) Exit");
		int choice = Integer.parseInt(in.nextLine());
		
		switch(choice) 
		{
		case 1:
			System.out.println("Search by Movie Name\n-------\n"+"Please enter the name of the movie :");
			String moviename = in.nextLine();
		for(Booking ticket :book) {
			
			if(moviename.contentEquals(ticket.getMovie()) && !ticket.getStatus() && ticket instanceof Movie) {
				System.out.println("Available");
				ticket.showinfo();
				counter =count;
			}
			else if(moviename.contentEquals(ticket.getMovie()) && ticket.getStatus() && ticket instanceof Movie) {
				
					System.out.println("Booked");
					ticket.showinfo();
					counter =count;
				
			}
			count ++;
		}if(counter ==-1) {
			System.out.println("Movie searched is not found");
		}
		
			break;
		case 2:
			System.out.println("Search by Time\n----------\n"+"Please enter the time you wish for :");
			int time= Integer.parseInt(in.nextLine());
			
				for(Movie m :movie) {
					if(time == m.getStarttime() && !m.getStatus()) {
						System.out.println("Available");
						m.showinfo();
						counter =count;
					}
					
					else if(time == m.getStarttime() && m.getStatus()) {
						System.out.println("Booked");
						m.showinfo();
						counter =count;
					}count ++;

				}if(counter ==-1) {
					System.out.println("No Movie showing for the time you wish for ");
				}
				
			
			break;
		case 3:
			System.out.println("Search by Currently Available\n---------\n");
			for(Booking b : book ) {
				if(!b.getStatus()&& b instanceof Movie) {
					b.showinfo();
					counter =count;
				}
			}
			
			break;
		case 4:
			opt =4;
			System.out.println("Back to ADMIN Main Page");
			break;
				default :System.out.println("error input, pls retry");
	}
			}catch(Exception ex) {
				System.out.println("error input, pls retry");
			}
		}while(opt !=4);
		System.out.println("Back to ADMIN Main Page");
	}
	
	// Allow the user to check their booking history
		public static void CheckBookingHistory(ArrayList<Booking>book, Booking user, Scanner in) {
			int count =0, counter =-1;
			System.out.println("Current account :"+ user.getName());
			for(Booking b :book) {
				if(b.getName().contentEquals(user.getName()) && b instanceof Ticket) {
					b.showinfo();
					counter =count;
				}
				count++;
				
				
			}
			if(counter ==-1) {
					System.out.println("No Booking Found");
				}
			
		}
	
		
	// Function for User to update their personal information
		public static void UserUpdateInformation(ArrayList<Booking>book,Booking user,Scanner in, ArrayList<User>u) throws FileNotFoundException {
		int option =0;
			do {
				try {
			int count =0, phno =0, counter=-1;
			int i=0;
		
			String addr=null;
			
			System.out.println("Current account :"+ user.getName());
			for(Booking a :book) {
				
				if(a.getName().contentEquals(user.getName()) && a instanceof User) {
					
					count++;
					System.out.println("Welcome " +a.getName());
					int confirm;
					int choice;
				
						if(a instanceof User) {
							a.showinfo();
						}
						
						System.out.println("-----Selection----"+"\n1.) Username"+"\n2.) Email"+"\n3.) Password"+"\n4.) Address"+"\n5.) Phnone No."+"\n6.) Name"+"\n7.) Exit"+"\nPlease enter your option (1-7):");
						choice =Integer.parseInt(in.nextLine());
						
						
						switch(choice)
						{
						case 1:
							String [] name ;
							name = new String [book.size()];
								for( i=0; i<book.size();i++) {
									Booking check = book.get(i);
									name[i] = check.getName();
									
								}
							while(counter==-1){
							System.out.println("Please enter the new username :");
							
							String username = in.nextLine();;
							boolean c1 = validation(username,2);
							i=0;
							boolean checkname = false;
							while(counter ==-1) {
							if(i <name.length) {
							if(username.contentEquals(name[i])) {
									System.out.println("This Username has already been taken, pls retry");
									counter=1;
									checkname = true;
								
							}
							else {
								counter =-1;
							}
							}
							else if(!c1) {
								counter=1;
								checkname = true;
							}
							else if(username.length()<8) {
								System.out.println("Username must have atleast 8 characters");
							}
							else {
								counter =1;
								checkname =false;
							}
							i++;
						}
							if(checkname == false) {
								counter=1;
								System.out.println("Is this the Username u want to change for ?(press 1 to continue, 2 to cancel) :" +username);
								 confirm = Integer.parseInt(in.nextLine());
								if(confirm ==1) {
									
									for(Booking b:book) {
										if(b.getName().contentEquals(user.getName()) &&  b instanceof Movie) {
											b.setName(username);
										}
									}
									for(Booking c :book) {
										if(c.getName().contentEquals(user.getName()) && c instanceof Ticket) {
											c.setName(username);
										}
									}
									for(User USER:u) {
										if(USER.getName().contentEquals(user.getName())) {
											USER.setName(username);
										}
									}
									a.setName(username);
										counter = count;
								}
							}
							else {
								if(checkname==true) {
								counter=-1;	
								}
								}
							}
							break;
							
						case 2 :
							String [] checkemail ;
							checkemail = new String [book.size()];
								for( i=0; i<book.size();i++) {
									Booking check = book.get(i);
									checkemail[i] = check.getMovie();
									
								}
							while(counter==-1) {
							System.out.println("Please enter the new Email :");
							String email = in.nextLine();
							 i=0;
							 int I= email.indexOf('@');
							int j= email.lastIndexOf('@');
							boolean flag = false;
							while(counter ==-1) {
							if(i <checkemail.length) {
							if(email.contentEquals(checkemail[i]) ) {
									System.out.println("This email has already been taken, pls retry");
									counter=1;
									flag = true;
								
							}
							else {
								counter =-1;
							}
							}
							else if(email.indexOf('@')>=0 && email.indexOf(".com")>=0) {
								counter=1;
								flag=false;;
							}
							else if(j!=1) {
								counter=1;
								flag=true;
								System.out.println("This email is invalid, pls retry");
							}
							else if(email.indexOf('@')<0 && email.indexOf(".com")<0) { 
								counter=1;
								flag =true;
								System.out.println("This email is invalid, pls retry");
							}
							else {
								counter =1;
								flag=false;
							}
							i++;
						}
							if(flag == false) {
								counter=1;
								System.out.println("Is this the Email u want to change for ?(press 1 to continue, 2 to cancel) :" +email);
								 confirm = Integer.parseInt(in.nextLine());
								if(confirm ==1) {
									a.setMovie(email);;
									for(User USER:u) {
										if(USER.getName().contentEquals(user.getName())) {
											USER.setMovie(email);
										}
									}
								}
							}
							else {
								if(flag==true) {
									counter=-1;
								}
							}
						}
							break;
						case 3:
							String [] password ;
							password = new String [book.size()];
								for(int j=0; j<book.size();j++) {
									Booking check = book.get(j);
									password[j] = check.getCode();
									
								}
							while(counter==-1) {
							System.out.println("Please enter the new Password :");
							String pw = in.nextLine();
							int j=0;
							boolean c2= validation(pw,3);
							boolean checkpw = false;
							while(counter ==-1) {
							if(j <password.length) {
							if(pw.contentEquals(password[j])) {
									System.out.println("This Password has already been taken, pls retry");
									counter=1;
									checkpw = true;
								
							}
							else {
								counter =-1;
							}
							}
							else if(pw.length()<8) {
								System.out.println("Password must have atleast 8 characters");
								counter=1;
								checkpw = true;
							}
							else if(!c2) {
								counter=1;
								checkpw = true;
							}
							else {
								counter =1;
								checkpw=false;
							}
							j++;
						}
							if(checkpw == false) {
								counter=1;
								System.out.println("Is this the Password u want to change for ?(press 1 to continue, 2 to cancel) :" +pw);
								 confirm = Integer.parseInt(in.nextLine());
								if(confirm ==1) {
									a.setCode(pw);
									for(User USER:u) {
										if(USER.getName().contentEquals(user.getName())) {
											USER.setCode(pw);
										}
									}
								}
							}
							else {
								if(checkpw==true) {
									counter=-1;
								}
							}
							}
							break;
						case 4:
							//Assume some of the customer live together
							System.out.println("Please enter new Address :");
							 addr = in.nextLine();
							
							System.out.println("Is this the Address u want to change for ?(press 1 to continue) :" +addr);
							 confirm = Integer.parseInt(in.nextLine());
							if(confirm ==1) {
								a.setLocation(addr);
								for(User USER:u) {
									if(USER.getName().contentEquals(user.getName())) {
										USER.setLocation(addr);
									}
								}
							}
							break;
						case 5:
							int [] checkphnoeNo ;
							checkphnoeNo = new int [book.size()];
								for( i=0; i<book.size();i++) {
									Booking check = book.get(i);
									checkphnoeNo[i] = check.getTime();
									
								}	
								try {
							boolean checkPhoneNo = false;
							while(counter==-1) {
							System.out.println("Please enter the new Phone No :");
							int phoneNo = Integer.parseInt(in.nextLine());
							 i=0;
						
							while(counter ==-1) {
							if(i <checkphnoeNo.length) {
							if(phoneNo==checkphnoeNo[i] || phoneNo <1000000 || phoneNo>999999999) {
									System.out.println("This Phone No. is invalid, pls retry");
									counter=1;
									checkPhoneNo = true;
								
							}
							else {
								counter =-1;
							}
							}
							else {
								counter =1;
								checkPhoneNo =false;
							}
							i++;
						}
							if(checkPhoneNo == false) {
								counter=1;
								System.out.println("Is this the Phone No. u want to change for ?(press 1 to continue, 2 to cancel) :" +phoneNo);
								 confirm = Integer.parseInt(in.nextLine());
								if(confirm ==1) {
									a.setPhno(phoneNo);
									for(User USER:u) {
										if(USER.getName().contentEquals(user.getName())) {
											USER.setPhno(phoneNo);
										}
									}
								}
							}
							else {
								if(checkPhoneNo==true) {
									counter=-1;
								}
							}
							}
								}catch(NegativeNumberException ex) {
									System.out.println("Phone Number inser is invalid, pls retry");
								}
							break;
							
						case 6:
							//DATA Pool//
							String [] PASSWORD ;
							PASSWORD = new String [book.size()]; 
						    String [] EMAIL ;
						    EMAIL = new String [book.size()]; 
							String [] NAME ;
							NAME = new String [book.size()];
							int [] PHONE ;
							PHONE = new int [book.size()];
							String [] ucheckemail ;
							ucheckemail = new String [book.size()];
							
							//GET DATA//
								for( i=0; i<book.size();i++) {
									Booking check = book.get(i);
									NAME[i] = check.getName();
									EMAIL[i] = check.getMovie();
									PASSWORD[i] = check.getCode();
									PHONE[i] = check.getTime();
									ucheckemail[i] = check.getLocation();
								}
							boolean checkRname=false;
							while(counter ==-1) {
								
								
								//Assume all the name enter by the user, every alphabet become upper case//
								//Sometimes people may have same First name and Last name at the same time, so it is acceptable to have duplicate Real name//
								System.out.println("Reminder every alphabet enter will become upper case");
								System.out.println("Please enter the First Name :");
								String s1 = in.nextLine(); 
								String fname = s1.toUpperCase();
								System.out.println("Please enter the Last Name :");
								String s2 = in.nextLine();
								String lname = s2.toUpperCase();
							String rname =fname+" "+lname;
							boolean c = validation(fname,1);
							boolean d = validation(lname,1);
							i=0;
							while(counter ==-1) {
							if(i <NAME.length) {
							if(rname.contentEquals(NAME[i]) || rname.contentEquals(EMAIL[i]) || rname.contentEquals(PASSWORD[i])) {
									System.out.println("This Name is invalid, pls retry");
									counter=1;
									checkRname = true;
								
							}
							else {
								counter =-1;
							}
							}
							else if(!c || !d) {
								
								counter=1;
								checkRname=true;
							}
							else {
								checkRname= false;
								counter =1;
								
							}
							i++;
						}
							if(checkRname == true) {
								counter=-1;
							}
							else {
								count=1;
								if(checkRname == false) {
								
								System.out.println("Name available : " + rname);
								System.out.println("Is this the name you want to change for? (If yes press 1, else press 2) :");
								String FullName = fname+","+lname;
								 confirm = Integer.parseInt(in.nextLine());
								if(confirm ==1) {
									a.setFName(fname);
									a.setLName(lname);
									for(User USER:u) {
										if(USER.getName().contentEquals(user.getName())) {
											USER.setFName(fname);
											USER.setLName(lname);
										}
									}
								} 
								
							}
								
							}
						}
							
							
							break;
						case 7:
							System.out.println("Back to USER Main Page");
							option=7;
							break;
							default :System.out.println("error input, pls retry");
							
						
						}
								
					
				
				}
				
			}
			
				PrintWriter update = new PrintWriter("Data.txt");
						for(Booking b :book)
							update.println(b.totostring());
							update.close();
			
			
			
			
		
		
		}catch(NegativeNumberException ex) {
			System.out.println("Phone No insert is invalid, pls retry");
		}
			catch(RuntimeException ex) {
				System.out.println("Input insert is invalid, pls retry");

			}}while(option !=7);
		}
		
		// Allow ADMIN to create the ticket 
	   public static void CreateTicket(ArrayList<Booking>book, Booking admin, Scanner in, ArrayList<Movie>MOVIE) throws FileNotFoundException {
					int stop =0, counter=-1, i=0, control=-1, Control=-1,stime =0,endtime=0, counte=-1, CONTROL=-1;
					 double price =0;
					String n =null, code=null, seat=null;
					String MOVIEID =null;
					try {
						do {
					System.out.println("Admin :"+admin.getName());
					
					String [] movie;
					movie = new String [book.size()];
					for( i=0; i<book.size();i++) {
						Booking check =book.get(i);
						
						movie[i]= check.getName();
						
					}
					
					boolean checkmovie =false;
					while(counter==-1) {
						
					System.out.println("Enter the name of the movie :");	
					String s5 = in.nextLine();
					
					String moviename =s5.toUpperCase();	
					i=0;
					while(counter==-1) {
						
						if(i<movie.length) {
						if(moviename.contentEquals(movie[i])) {
							System.out.println("Moviename is invalid, pls retry");
							checkmovie =true;
							counter=1;
						}
						else {
							counter=-1;
						}
					}
						else {
							counter=1;
							checkmovie =false;
						}
						
						
						i++;	
						}
						
					
					if(checkmovie == true) {
							counter=-1;
						}
						else {
							counter=1;
							if(checkmovie == false) {
							
							System.out.println("Movie name available : " + moviename);
							 	 n = moviename;
							
						}
					
					}
					}
					
					boolean checktime =false;
					try {
					while(control==-1) {
						System.out.println("Enter the starting time of the movie :");
						stime =Integer.parseInt(in.nextLine());
						
						System.out.println("Enter the ending time of the movie :");
						endtime =Integer.parseInt(in.nextLine());
						
						while(control==-1) {
							if(stime ==2300) {
								endtime =130;
								control=1;
								checktime =false;
								price =15.5;
							}
							else if(stime ==2320) {
								endtime =150;
								control=1;
								checktime=false;
								price =15.5;
							}
							
							else if(endtime -stime >230 || endtime-stime <145 ) {
								System.out.println("The time entered is invalid, pls retry");
								checktime =true;
								control=1;
							}
							else {
								price =10.5;
								control=1;
								checktime =false;
							}	
						}
						if(checktime == true) {
							control=-1;
						}
						else if(checktime ==false) 
							 {
								control=1;
								System.out.println("Time available\n"+"Starttime : "+stime +" Endtime : "+endtime+"Price :"+price);
							}
						}
						
					
					}catch(NegativeNumberException ex) {
						ex.getMessage();
					}
					
					String [] Seat;
					String[] Moviename;
					int [] time;
					boolean checkseat =false;
					Seat = new String [book.size()];
					Moviename =new String[book.size()];
					time = new int [book.size()];
					for( i=0; i<book.size();i++) {
						Booking check = book.get(i);
						Seat[i] = check.getLocation();
						Moviename[i] =check.getMovie();
						time[i] =check.getTime();
					}
					
					while(counte==-1) {
						System.out.println("Please enter the seat of the movie :");
						String s1 = in.nextLine();
						seat = s1.toUpperCase();
						i=0;
						while(counte==-1) {
							if(i<Seat.length) {
								if(seat.contentEquals(Seat[i]) && n.contentEquals(Moviename[i]) && stime ==time[i] ) {
									System.out.println("This ticket is already exist, pls retry");
									counte=1;
									checkseat=true;
								}
								else {
									counte=-1;
								}
							}
							else {
								counte=1;
								checkseat=false;
							}
							i++;
						}
						
						if(checkseat==true) {
							counte=-1;
						}
						else  {
							if(checkseat==false)
							counte=1;
							System.out.println("This ticket is available\n"+"Movie :"+n +"|| Time :"+stime +"|| Seat :"+seat);
							
						}
					}
					String []acode;
				
					acode = new String [book.size()];
					for( i=0; i<book.size();i++) {
						Booking check = book.get(i);
						acode[i]= check.getCode();
					}
					
					String ticketcode =null;
					boolean checkcode=false;
					while(Control==-1) {
						System.out.println("Enter the code of the movie :");
						ticketcode = in.nextLine();
						i=0;
						while(Control==-1) {
							if(i<acode.length) {
								if(ticketcode.contentEquals(acode[i])) {
								System.out.println("This code is invalid, pls retry");
								Control=1;
								checkcode =true;
							}
								else {
									Control=-1;
									
								}
								
							}
							else {
								checkcode =false;
								Control=1;
							}
							i++;
						}
						
						if(checkcode == true) {
							Control=-1;
						}
						else if(checkcode==false) {
							Control=1;
							System.out.println("Code Available :" + ticketcode);
							code =ticketcode;
						}	
					}
					
					boolean status=admin.status(false);
					String owner = "Unbook";
					String []aid;
					
					aid = new String [MOVIE.size()];
					for( i=0; i<MOVIE.size();i++) {
						Movie check = MOVIE.get(i);
						aid[i]= check.getID();
					}
					
					
					String ticketid =null;
					boolean checkmovieid=false;
					for(i=0; i<aid.length;i++) {
						System.out.println("---Existed Movie ID---\n"+aid[i]);
					}
					while(CONTROL==-1) {
						System.out.println("Enter the ID of the movie :");
						MOVIEID= in.nextLine();
						i=0;
						while(CONTROL==-1) {
							if(i<aid.length) {
								if(MOVIEID.contentEquals(aid[i])) {
								System.out.println("This ID is existed, pls retry");
								CONTROL=1;
								checkmovieid =true;
							}
								else {
									CONTROL=-1;
									
								}
								
							}
							else {
								checkmovieid =false;
								CONTROL=1;
							}
							i++;
						}
						
						if(checkmovieid == true) {
							CONTROL=-1;
						}
						else if(checkmovieid==false) {
							CONTROL=1;
							System.out.println("ID Available :" + MOVIEID);
							ticketid=MOVIEID; 
						}	
					}
					
					
					
					System.out.println("Is this the movie ticket you want to create?\n"+"Movie ID :"+ticketid+"|| Movie :"+n+"|| Start time :"+stime+"|| End time :"+endtime+"|| Code :"+ code+"|| Seat :"+ seat+"|| Owner :"+ owner);
					book.add(new Movie(n,price,stime,endtime,code,seat,status,owner,ticketid));
					MOVIE.add(new Movie(n,price,stime,endtime,code,seat,status,owner,ticketid));
					PrintWriter update = new PrintWriter("Data.txt");
					for(Booking booked :book)
						update.println(booked.totostring());
						update.close();
						System.out.println("Is there any more ticket to creat ? (press 1 to continue, 2 to Back to ADMIN Main Page) ");
						stop = Integer.parseInt(in.nextLine());
						}
							while(stop !=2);
					}
					catch(Exception ex) {
							
							ex.getMessage();
						}
			
					System.out.println("Back to ADMIN Main Page");
				}
		
		
		
		
		//Function to Create Account for USER and ADMIN 
		public static void CreateAccount(ArrayList<Booking>book,Scanner in, ArrayList<Admin> admin, ArrayList<User>user) throws FileNotFoundException {
			int counter =-1, Counter =-1, i=0, confirm=0, phone=0, count=-1, Counte =-1, Control=-1, Acounter=-1, CCounte=-1, control=-1, counte=-1, ccounter=-1, controller=-1;
			String n =null, e=null, PassWord=null, ADMINID=null, fname =null, lname=null;
			do {
			try{
				System.out.println("Which Account to create (1-2) :"+"\n1.) Client"+"\n2.) Admin"+"\n3.) Exit");
			
			int opt =Integer.parseInt(in.nextLine());
			if(opt ==1) {
				System.out.println("-------User Account--------\n");
				//DATA Pool//
				String [] password ;
				password = new String [book.size()]; 
			    String [] checkemail ;
				checkemail = new String [book.size()]; 
				String [] name ;
				name = new String [book.size()];
				int [] checkphnoeNo ;
				checkphnoeNo = new int [book.size()];
				String [] ucheckemail ;
				ucheckemail = new String [book.size()];
				
				//GET DATA//
					for( i=0; i<book.size();i++) {
						Booking check = book.get(i);
						name[i] = check.getName();
						checkemail[i] = check.getMovie();
						password[i] = check.getCode();
						checkphnoeNo[i] = check.getTime();
						ucheckemail[i] = check.getLocation();
					}
				
				//NAME section//
				boolean checkRname = false;
				
				while(count ==-1) {
					
				
					//Assume all the name enter by the user, every alphabet become upper case//
					//Sometimes people may have same First name and Last name at the same time, so it is acceptable to have duplicate Real name//
					System.out.println("Reminder every alphabet enter will become upper case");
					System.out.println("Please enter the First Name :");
					String s1 = in.nextLine(); 
					fname = s1.toUpperCase();
					System.out.println("Please enter the Last Name :");
					String s2 = in.nextLine();
					lname = s2.toUpperCase();
				String rname =fname+" "+lname;
				boolean c = validation(fname,1);
				boolean d = validation(lname,1);
				i=0;
				while(count ==-1) {
				if(i <name.length) {
				if(rname.contentEquals(name[i]) || rname.contentEquals(checkemail[i]) || rname.contentEquals(password[i])) {
						System.out.println("This Name is invalid, pls retry");
						count=1;
						checkRname = true;
					
				}
				else {
					count =-1;
				}
				}
				else if(!c || !d) {
					
					count=1;
					checkRname=true;
				}
				else {
					checkRname= false;
					count =1;
					
				}
				i++;
			}
				if(checkRname == true) {
					count=-1;
				}
				else {
					count=1;
					if(checkRname == false) {
					
					System.out.println("Name available : " + rname);
					 	 
					
				}
					
				}
			}
			
				//USERNAME section//
				boolean checkname = false;
				String username =null;
				while(counter ==-1) {
					
				
				//Assume that the USERNAME is the primary key, so duplicate USERNAME is not allow//
				System.out.println("Please enter the username :");
				
				username = in.nextLine();
				boolean c1= validation(username,2);
				i=0;
				while(counter ==-1) {
				if(i <name.length) {
				if(username.contentEquals(name[i])|| username.contentEquals(checkemail[i]) || username.contentEquals(password[i])) {
						System.out.println("This username has already been taken, pls retry");
						counter=1;
						checkname = true;
					
				}
				else {
					counter =-1;
				}
				}
				else if(!c1) {
					checkname =true;
					counter=1;
					
				}
				else if(username.length()<8) {
					System.out.println("Username must atleast have 8 character!!!");
					checkname =true;
					counter=1;
				}
				else {
					checkname= false;
					counter =1;
					
				}
				i++;
			}
				if(checkname == true) {
					counter=-1;
				}
				else {
					counter=1;
					if(checkname == false) {
					
					System.out.println("Username available : " + username);
					 	 n = username;
					
				}
					
				}
			}
				
				
				//PHONE NO. section//	
				int phoneNo =0;
				boolean checkPhoneNo = false;	
			try {	
				while(Counter==-1) {
				System.out.println("Please enter the  Phone No :");
				 phoneNo = Integer.parseInt(in.nextLine());
				 i=0;
				while(Counter ==-1) {
				if(i <checkphnoeNo.length) {
				if(phoneNo ==checkphnoeNo[i] || phoneNo <1000000 || phoneNo>999999999 ) {
						System.out.println("This Phone No. insert is invalid");
						Counter=1;
						checkPhoneNo = true;
				}
				else {
					Counter =-1;
				}
				}
				else {
					checkPhoneNo = false;
					Counter =1;
				}
				i++;
			}
				if(checkPhoneNo == true) {
					Counter=-1;
				}
				
				else	if(checkPhoneNo == false)  
				{
						Counter=1;
					System.out.println("Phone No. available :" +phoneNo);
						phone= phoneNo;
						
				}
				}
			}catch (NegativeNumberException ex) {
				System.out.println("Phone Number inser is invalid, pls retry");
			}
				
				
					
				//EMAIL section//
				String email=null;
				boolean flag = false;
				while(Counte==-1) {	
				System.out.println("Please enter the Email :");
				email = in.nextLine();	
				i=0;
				while(Counte ==-1) {
			
					int I= email.indexOf('@');
					int j= email.lastIndexOf('@');
				if(i <checkemail.length) {
				if(email.contentEquals(checkemail[i]) || email.contentEquals(ucheckemail[i]) ||  email.contentEquals(checkemail[i]) || email.contentEquals(password[i]) ||email.contentEquals(name[i])) {
						System.out.println("This email has already been taken, pls retry");
						Counte=1;
						flag = true;
					
				}
				else {
					Counte=-1;
				}
				
				}
				else if(email.indexOf('@')>=0 && email.indexOf(".com")>=0) {
					Counte=1;
					flag=false;;
				}
				else if(j!=1) {
					Counte=1;
					flag=true;
					System.out.println("This email is invalid, pls retry");
				}
				else if(email.indexOf('@')<0 && email.indexOf(".com")<0) { 
					Counte=1;
					flag =true;
					System.out.println("This email is invalid, pls retry");
				}
				else {
					Counte =1;
					flag=false;
				}
				i++;
			}
				if(flag == true) {
					Counte=-1;
				}
				else {
					
					if(flag == false) {
						Counte=1;
						System.out.println("Email Available :" +email);
								e=email;
						}
					}
				}
				
				
				// Assume some of the user live together
				System.out.println("Please enter the address :");
				String s3 =in.nextLine();
				String address = s3.toUpperCase();
				
				//PASSWORD section//
				String pw =null;
				boolean checkpw = false;	
				while(Control==-1) {
				System.out.println("Please enter the  Password :");
				pw = in.nextLine();
				 i=0;
				
				while(Control ==-1) {
				if(i <password.length) {
				if(pw.contentEquals(password[i])||  pw.contentEquals(checkemail[i])  ||pw.contentEquals(name[i]) ||pw.contentEquals(fname + lname)) {
						System.out.println("This Password has already been taken, pls retry");
						Control=1;
						checkpw = true;
					
				}
				else {
					Control =-1;
				}
				}
				else if(password.length<8){
					System.out.println("Password must at least 8 character");
				}
				else {
					Control =1;
					checkpw = false;
				}
				i++;
			}
				if(checkpw ==true) {
					Control=-1;
			}
				else {
					Control=1;
					if(checkpw == false) {
					
					System.out.println("Password available :" +pw);
					PassWord =pw;
					
				}
				}
				}
				
				//Require confirmation from the user//
				System.out.println("Is this the accountyou want to create for? \n"+"Name : "+ n + "||Phone No. : "+ phone + " ||Email :" + e +" ||Password : "+ PassWord + " ||Address :"+address+"(press 1 to continue, 2 to cancel)" );
				confirm = Integer.parseInt(in.nextLine());
				if(confirm ==1) {
					System.out.println("Account succesfully created");
				book.add(new User(n,phone,e,PassWord,address,fname,lname));
				PrintWriter newacc = new PrintWriter("Data.txt");
				
				for(Booking b : book)
				newacc.println(b.totostring());
				newacc.close();
			
			}
				else {
					System.out.println("Account cancel");
				}
			}
				else
				if(opt ==2) {
					//DATA POOL//
					String [] checkID ;
					checkID = new String [book.size()];
					String [] adminname ;
					adminname = new String [book.size()];
					String [] password ;
					password = new String [book.size()];
					String [] checkemail ;
					String [] ucheckemail;
					ucheckemail = new String [book.size()];
					checkemail = new String [book.size()];
				
					
					//GET DATA//
						for( i=0; i<book.size();i++) {
							Booking check = book.get(i);
							adminname[i] = check.getName();
							checkID[i] = check.getMovie();
							password[i] = check.getCode();
							checkemail[i] = check.getLocation();
							ucheckemail[i] = check.getMovie();
						}
						
						
					System.out.println("--------Admin Account--------\n");
					//NAME SECTION//
					//Assume all the name enter by the ADMIN, every alphabet become upper case//
					//Assume sometimes ADMIN real name can same as the user or other ADMINS as well//
					//Sometimes people may have same First name and Last name at the same time, so it is acceptable to have duplicate Real name//
					boolean checkAname = false;
					
					while(controller ==-1) {
						
					
						//Assume all the name enter by the ADMIN, every alphabet become upper case//
						
						//Sometimes people may have same First name and Last name at the same time, so it is acceptable to have duplicate Real name//
						System.out.println("Reminder every alphabet enter will become upper case");
						System.out.println("Please enter the First Name :");
						String s1 = in.nextLine(); 
						fname = s1.toUpperCase();
						System.out.println("Please enter the Last Name :");
						String s2 = in.nextLine();
						lname = s2.toUpperCase();
					String rname =fname+" "+lname;
					
					boolean c = validation(fname,1);
					boolean d = validation(lname,1);
					i=0;
					int j=0;
					int k=0;
					while(controller ==-1) {
					if(i <adminname.length ) {
					if(rname.contentEquals(adminname[i]) || rname.contentEquals(checkemail[i]) || rname.contentEquals(password[i]) || rname.contentEquals(checkID[i])) {
							System.out.println("This Name is invalid, pls retry");
							controller=1;
							checkAname = true;
						
					}
					
					else {
						controller =-1;
					}
					}
					else if(!c || !d) {
						
						controller=1;
						checkAname=true;
					}
					else if(fname.length()<2 || lname.length()<2) {
						controller=1;
						checkAname=true;
						System.out.println("The minimum of the First Name and Last Name is 2 character, pls retry");
					}
					else {
						checkAname= false;
						controller =1;
						
					}
					i++;
					
				}
					if(checkAname == true) {
						controller=-1;
					}
					else {
						controller=1;
						if(checkAname == false) {
						
						System.out.println("Name available : " + rname);
						 	 
						
					}
						
					}
				}
					 
					
					//ADMIN username section//
					boolean checkname = false;
					String username =null;
					while(Acounter ==-1) {
					
					
					System.out.println("Please enter the ADMIN  username :");
					
					username = in.nextLine();
					boolean c= validation(username,2);
					i=0;
					while(Acounter ==-1) {
					if(i <adminname.length) {
					if(username.contentEquals(adminname[i]) || username.contentEquals(checkID[i]) || username.contentEquals(password[i]) || username.contentEquals(checkemail[i])) {
							System.out.println("This name has already been taken, pls retry");
							Acounter=1;
							checkname = true;
						
					}
					else {
						Acounter =-1;
					}
					}
					else if(!c) {
						Acounter=1;
						checkname =true;
					}
					else if(username.length()<8) {
						System.out.println("Username must have atleast 8 character");
						Acounter=1;
						checkname=true;
					}
					else {
						checkname= false;
						Acounter =1;
						
					}
					i++;
				}
					if(checkname == true) {
						Acounter=-1;
					}
					else {
						Acounter=1;
						if(checkname == false) {
						
						System.out.println("name available : " + username);
						 	 n = username;
						
					}
						
					}
				}
					
					//ADMIN ID SECTION//
					String adminid=null;
					boolean flag = false;
					while(CCounte==-1) {	
						System.out.println("Please enter Admin ID :");
						adminid =in.nextLine();
						adminid.toUpperCase();
						boolean c2 = validation(adminid,3);
					i=0;
					while(CCounte ==-1) {
				
					
					if(i <checkID.length) {
					if(adminid.contentEquals(checkID[i]) || adminid.contains(checkemail[i]) ||adminid.contentEquals(password[i]) ||adminid.contentEquals(adminname[i])) {
							System.out.println("This ID is invalid, pls retry");
							CCounte=1;
							flag = true;
						
					}
					else {
						CCounte =-1;
					}
					}
					else if(!c2) {
						CCounte=1;
						flag =true;
					}
					else if(adminid.length()<8) {
						System.out.println("ADMIN ID must have atleast 8 character");
						CCounte=1;
						flag =true;
					}
					else {
						CCounte =1;
						flag=false;
					}
					i++;
				}
					if(flag == true) {
						CCounte=-1;
					}
					else {
						if(flag == false) {
							CCounte=1;
							System.out.println("ID Available :" +adminid);
									ADMINID=adminid;
							}
						}
					}
				
					//PASSWORD SECTION//
					String code =null;
					boolean checkpw = false;	
					while(control==-1) {
						System.out.println("Please enter Admin Code :");
						 code =in.nextLine();
						 code.toUpperCase();
					 i=0;
					boolean c3 = validation(code,2);
					while(control ==-1) {
					if(i <password.length) {
					if(code.contentEquals(password[i]) || code.contentEquals(checkID[i]) || code.contains(checkemail[i]) ||code.contentEquals(adminname[i])) {
							System.out.println("This Code is ivalid, pls retry");
							control=1;
							checkpw = true;
						
					}
					else {
						control =-1;
					}
					}
					else if(!c3) {
						control=1;
						checkpw = true;
					}
					else if(code.length()<8) {
						System.out.println("Code enter must have atleast 8 characters");
						control=1;
						checkpw = true;
					}
					else {
						control =1;
						checkpw = false;
					}
					i++;
				}
					if(checkpw ==true) {
						control=-1;
				}
					else {
						control=1;
						if(checkpw == false) {
						
						System.out.println("Code available :" +code);
						PassWord =code;
						
					}
					}
					}
					System.out.println("Please enter address :");
					String addr =in.nextLine();
					
					
					
					//EMAIL SECTION//
					String email=null;
					boolean adminemail = false;
					
					while(counte==-1) {	
					System.out.println("Please enter the Email :");
					email = in.nextLine();	
					i=0;
					int I= email.indexOf('@');
					int j= email.lastIndexOf('@');
					while(counte ==-1) {
				
					
					if(i <checkemail.length) {
					if(email.contentEquals(checkemail[i]) || email.contentEquals(ucheckemail[i])) {
							System.out.println("This email has already been taken, pls retry");
							counte=1;
							adminemail = true;
						
					}
					else {
						counte =-1;
					}
					}
					else if(email.indexOf('@')>=0 && email.indexOf(".com")>=0) {
						counte=1;
						adminemail=false;;
					}
					else if(j!=1) {
						counte=1;
						adminemail=true;
						System.out.println("This email is invalid, pls retry");
					}
					else if(email.indexOf('@')<0 && email.indexOf(".com")<0) { 
						counte=1;
						adminemail =true;
						System.out.println("This email is invalid, pls retry");
					}
					else {
						counte =1;
						adminemail=false;
					}
					i++;
				}
					if(adminemail == true) {
						counte=-1;
					}
					else {
						if(adminemail == false) {
							counte=1;
							System.out.println("Email Available :" +email);
									e=email;
							}
						}
					}
					
					int PHoneNo=0;
					int [] checkphnoeNo ;
					checkphnoeNo = new int [book.size()];
						for( i=0; i<book.size();i++) {
							Booking check = book.get(i);
							checkphnoeNo[i] = check.getTime();
							
						}
					
					//ADMIN PHONE NO. section//
					int ph =0;
					boolean checkPhoneNo = false;	
				try {	
					while(ccounter==-1) {
					System.out.println("Please enter the  Phone No :");
					ph = Integer.parseInt(in.nextLine());
					 i=0;
					while(ccounter ==-1) {
					if(i <checkphnoeNo.length) {
					if(ph ==checkphnoeNo[i] || ph <1000000 || ph>999999999 ) {
							System.out.println("This Phone No. insert is invalid");
							ccounter=1;
							checkPhoneNo = true;
						
					}
					else {
						ccounter =-1;
					}
					}
					else {
						checkPhoneNo = false;
						ccounter =1;
					}
					i++;
				}
					if(checkPhoneNo == true) {
						ccounter=-1;
					}
					
					else	if(checkPhoneNo == false)  
					{
						ccounter=1;
						System.out.println("Phone No. available :" +ph);
							phone= ph;
							
							
						
					}
					
					
					}
				}catch (NegativeNumberException ex) {
					System.out.println("Phone Number inser is invalid, pls retry");
				}
					
					//Require confirmation from the ADMIN//
					System.out.println("Is this the accountyou want to create for? \n"+"Name : "+ n + "|| ID : "+ADMINID +" ||Phone No. : "+ phone + " ||Email :" + e +" ||Password : "+ PassWord + " ||Address :"+addr+" ||Name:"+fname+" "+lname+"(press 1 to continue, 2 to cancel)" );
					confirm = Integer.parseInt(in.nextLine());
					if(confirm ==1) {
						System.out.println("Account succesfully created");
					book.add(new Admin(n,phone,ADMINID,PassWord,addr,e,fname,lname));
					
					
					PrintWriter newacc = new PrintWriter("Data.txt");
					for(Booking b : book)
						newacc.println(b.totostring());
						newacc.close();
				
				}
					//Selection for the create another account or exit//
					System.out.println("Any more account to create? (press 1 to continue, 2 to Back to Main Page");
					counter = Integer.parseInt(in.nextLine());
				}
				else if(opt ==3) {
					counter=3;
					System.out.println("Back to Main Page");
				}
			}
				
			
			
			catch(RuntimeException ex) {
				System.out.println("Input insert is invalid, pls retry");
			}
			
			
			
			}while(counter !=3);
			
		}
		
	
	
	//Exit function
	public static void exit() {
		System.out.println("Thanks for using this application, have a nice day, enjoy ur movie, goodbye :)");
		System.exit(0);
	}

	//To check the input enter by user//
	public static boolean validation(String data,int section) {
	if(section==1) {
		data= data.toUpperCase();
		char[] charArray = data.toCharArray();
	      for (int i = 0; i < charArray.length; i++) {
	         char ch = charArray[i];
	         if  (!(ch>='A' && ch<='Z') && data.charAt(i) !=' '  || data.charAt(i) ==' ') {
	            System.out.println("Name enter can not have space or other numeric letter!!");
	        	 return false;
	         }
	      }
	      return true;
	}
	else if(section==2){
		int len = data.length();
		char[] charArray = data.toCharArray();
	      for (int i = 0; i < len; i++) {
	         char ch = charArray[i];
	         if ((Character.isLetterOrDigit(data.charAt(i)) == false) && data.charAt(i) ==' ') {
	        	 System.out.println("Input enter can not have space!!");
	             return false;
	          }
	      }
	     
	}
	else if(section ==3) {
		int len = data.length();
		char[] charArray = data.toCharArray();
	      for (int i = 0; i < len; i++) {
	         char ch = charArray[i];
	         if ((Character.isLetterOrDigit(data.charAt(i)) == false) && data.charAt(i) !=' ' || data.charAt(i) ==' ' ) {
	        	 System.out.println("Input enter can not have space or unicode presence!!");
	             return false;
	          }
	      }
	}
		
		
		return true;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
