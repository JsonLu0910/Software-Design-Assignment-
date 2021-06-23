
public class Ticket extends Booking{

	
	public Ticket(String name, String moviename, String code,String seat) {
		super(name,moviename,code,seat, seat);
		
	}

	
	@Override
	public void showinfo() {
		System.out.println("--Booked by :"+getName() +"--Movie Name :"+getMovie()+"--Ticket Code :"+getCode()+"--Seat :"+getLocation());
		
	}

	@Override
	public String totostring() {
		// TODO Auto-generated method stub
		return getName()+","+getMovie()+","+getCode()+","+getLocation();

	}

	
	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean status(boolean status) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setPhno(int phno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setTime(int starttime, int endtime) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setFName(String fname) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setLName(String lname) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
}
