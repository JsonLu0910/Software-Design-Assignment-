
public class User extends Booking{

private int phno;
private String fname;
private String lname;

public User(String name, int phno,String movie,String code,String address,String fname, String lname) {
	super(name,movie,code,address,fname);
	this.phno =phno;
	this.fname= fname;
	this.lname=lname;
}

public int getPhoneno() {
	return phno;
}



public void setPhno(int aph) {
	if(aph <1000000 || aph>999999999) {
		throw new NegativeNumberException("Phone Number is invalid");
	}
	else {
		phno = aph;
	}
}

public void showinfo() {
	System.out.println("User Data");
	System.out.println("Username :"+ getName()+"|| Email :"+getMovie()+"|| Phnoe No. :"+getPhoneno()+"|| Address :"+getLocation()+"|| Name :" +getRname());
}

public String getRname() {
	return fname+","+lname;
}

public String getFname() {
	return fname;
}

public String getLname() {
	return lname;
}

@Override
public String totostring() {
	// TODO Auto-generated method stub
	return getName()+","+getPhoneno()+","+getMovie()+","+ getCode()+","+getLocation()+","+getFname()+","+getLname();
}




@Override
public boolean status(boolean status) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean getStatus() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public int getTime() {
	// TODO Auto-generated method stub
	return getPhoneno();
}

@Override
public void setTime(int starttime, int endtime) {
	// TODO Auto-generated method stub
	
}

@Override
public void setFName(String Fistname) {
	// TODO Auto-generated method stub
	fname =Fistname;
}

@Override
public void setLName(String Lastname) {
	// TODO Auto-generated method stub
	lname =Lastname;
}





}
