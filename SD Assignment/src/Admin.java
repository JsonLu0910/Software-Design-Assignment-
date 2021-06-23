
public class Admin extends Booking{
private int phno;

private String fname;
private String lname;
public int getPhno() {
	return phno;
}

public Admin(String username,int phno,String ID, String pw, String email,String address,String fname,String lname) {
	super(username, ID, pw,email,address);
	
	this.phno = phno;
	this.fname=fname;
	this.lname=lname;
	
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
public void showinfo() {
	// TODO Auto-generated method stub
	System.out.println("Admin username :" +getName() + "|| Admin ID :" + getMovie()+ "|| Password : ****" + "|| Email :"+getLocation()+"|| Address :"+getID()
	+"|| Phnoe No. :"+ getPhno()+"|| NAME :"+getRname());
	
}


@Override
public String totostring() {
	// TODO Auto-generated method stub
	return getName()+","+getPhno()+","+getMovie()+","+getCode()+","+getLocation()+","+getID()+","+getRname();
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
	return getPhno();
}
	
public void setPhno(int aph) {
	if(aph <1000000 || aph>999999999) {
		throw new NegativeNumberException("Phone Number is invalid");
	}
	else {
		phno = aph;
	}
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
