
public abstract class Booking {
private String name;
private String moviename;
private String code;
private String location;
private String id;
public String getLocation() {
	return location;
}

public void setLocation(String alocation) {
	location =alocation;
}

public String getName() {
	return name;
}

public String getMovie() {
	return moviename;
}

public String getCode() {
	return code;
}

public String getID() {
	return id;
}


public void setCode(String acode) {
		code = acode;
}

public void setName(String aname) {
	name =aname;
}

public void setMovie(String amovie) {
	moviename = amovie;
}

public Booking(String name, String moviename,String code,String location,String id) {
	this.name =name;
	this.moviename= moviename;
	this.code =code;
	this.location=location;
	this.id =id;
	
}
public abstract void showinfo();
public abstract String totostring();
public abstract int getTime();
public abstract  boolean getStatus();
public abstract boolean status(boolean status);
public abstract void setFName(String fname);
public abstract void setLName(String lname);
public abstract void setPhno(int phno);
public  abstract void setTime(int starttime, int endtime);
}


