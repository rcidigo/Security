

//create class
//create fields]
//create constructors
//create methods(setters and getters)


public class User
{
 //these are the fields that the user will have

 private String name;
 private String userid;
 private String password;

 // constructors

 public User (String name, String userid, String password)
 {
     this.userid=userid;
     this.name=name;
     this.password=password;

 }

 public String getUserID()
 {
     return userid;
 }

 public String getName()
 {
     return name;
 }

 public String getpassword()
 {
     return password;

 }

 public void setPassword(String password){
 // use encryption/hash here
   this.password=password;

 }
 public  String toString () 
 {
	 String x= "UserId: "+userid+" password: "+password; 
	 return x;
 }
}
