
public class Movie extends Booking{

private int starttime;
private int endtime;
private boolean bookStatus = false;
private String owner= "Unbook";
private double price;
public Movie(String moviename,double price,int starttime,int endtime,String code,String seat,boolean stats,String name,String ID) {
	
	super(name,moviename,code,seat,ID);
	this.starttime =starttime;
	this.endtime =endtime;

	bookStatus = stats;
	owner= name;
	this.price =price;
}

public boolean isBookStatus() {
	return bookStatus;
}

public void setBookStatus(boolean bookStatus) {
	this.bookStatus=bookStatus;
}


public void setOwner(String name) {
	String x;
	x=getName();
	name =x;
}
public int getEndtime() {
	return endtime;
}


public int getStarttime() {
	return starttime;
}

public void setPhno(int aph) {
	if(aph <1000000 || aph>999999999) {
		throw new NegativeNumberException("Phone Number is invalid");
	}
	else {
		starttime = aph;
	}
}

public double getPrice() {
	return price;
}

public void showinfo() {
	
	System.out.println("Booked by :"+getName()+"|| Start Time :"+getStarttime()+"|| End Time :"+getEndtime()+"|| Seat :"+ getLocation()+"|| Moviename :"+ getMovie()+"|| Movie ID :"+ getID()+"|| Price :"+getPrice());
}


@Override
public String totostring() {
	// TODO Auto-generated method stub
	return getMovie()+","+getPrice()+","+getStarttime()+","+getEndtime()+","+getCode()+","+getLocation()+","+isBookStatus()+","+getName()+","+getID();
}

 public boolean status(boolean status) {
	 this.bookStatus = status;
	 return bookStatus;
 }

@Override
public boolean getStatus() {
	
	return bookStatus;
}

@Override
public int getTime() {
	// TODO Auto-generated method stub
	 return getStarttime() ;
}

public   void setTime(int start, int end) {
	if(end - start>245 || end -start<145) {
		throw new NegativeNumberException("Time enter is invalid");

	}
	
	else if(start ==2300) {
		endtime =145;
		starttime =start;
	}
	//Assume last call is 23 :40 PM
	else if(start == 2340) {
		endtime =205;
		starttime =start;
	}
	else starttime = start; endtime =end;
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
