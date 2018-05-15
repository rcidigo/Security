


/*Notes: Eveything seems to be good , run some extensive tests 
 * for each situation, try and break
 * write read me, 
 * try and encrypt userid, if not, whatever 
 */

import java.util.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 

public class SKeyAuthA
{
	public static void main(String[]args) throws NoSuchAlgorithmException
	{
		// variables we will need//
		Scanner input= new Scanner(System.in); // handles on input
		ArrayList <User> users= new ArrayList<>(); // will hold all valid users objects
		String choice;
		
		//opening message
		System.out.println("This is an authentication system using S/key implemtentation");

		do
		{
			System.out.println("Returning Users may login by entering \'L\' \n new Users must Register an account by entering \'R\'");
			choice= input.nextLine();

			// CHOICE- will repeat if invalid input is placed
			if (choice.equalsIgnoreCase("L")) {
				UserLogIn(input,users); // method for logging in
			}
			else if (choice.equalsIgnoreCase("R")) {
				UserRegister(input,users);
			}
			else {
				System.out.println("Invalid input!!!");

			}

		} while (!choice.equalsIgnoreCase("L") && !choice.equalsIgnoreCase("R"));

	}

	/* USERLOGIN
	 * This segment of the program work under the conditions
	 * that the user is already in the system. A sucessful login
	 * means that the user was found in the system, unsucessful can meean
	 * two things, userid isnt in system, userid was entered incorrectly, or 
	 * password was incorrect
	 */
	
	public static void UserLogIn(Scanner in, ArrayList <User>a) throws NoSuchAlgorithmException
	{
		//variables we need
		String Userid; // userid entered by person logging in
		String pwd;   // password entered by person logging in
	
		System.out.println("************USER LOG IN*************");

		System.out.println("Enter Userid");
		Userid=in.nextLine();

		System.out.println("Enter Password");
		pwd=in.nextLine();

		// check if Userid exists
		if(UserExists(Userid,a)==true)
		{
			// if pwd is part of the chain
			for(int i=0;i< a.size();i++)
			{
				if(a.get(i).getUserID().equalsIgnoreCase(Userid))
				{
					String pwdCheck=a.get(i).getpassword();

					if(pwdCheck.equals(EncString(pwd)))
					{
						//succesful login
						System.out.println("******Sucessfull Login******");
						
					// now that password has been used, we want to change it by hashing it	
						String newPass=EncString(pwdCheck); // 1st hash previous password
						a.get(i).setPassword(newPass); 	  //2nd set password to new password
						
						
						System.out.println("You can now logout and login with another account by entering \'L\' \n"
								+ "or \'R\' to register. You can also exit the system by entering \'exit\'");
						String afterLogin = in.nextLine();
						
						if( afterLogin.equalsIgnoreCase("l")) UserLogIn(in, a);
						else if( afterLogin.equalsIgnoreCase("r"))UserRegister(in, a);
						else if ( afterLogin.equalsIgnoreCase("exit")){System.out.println("Exited Session"); System.exit(0);}
						else
						{
							System.out.println("Invalid Input, you have been exited from the session"); 
							System.exit(0);
						}     
							

						
					}
					else{
						
						System.out.println("******UNSucessfull Login******");
					    System.out.println("Incorrect Login Information, please try again logging in again by entering \'L\' \n"+
						"or \'R\' to register the an account, or \'exit\' to leave the session");
						String uLogin=in.nextLine();
						
						if( uLogin.equalsIgnoreCase("l")) UserLogIn(in, a);
						else if( uLogin.equalsIgnoreCase("r"))UserRegister(in, a);
						else if ( uLogin.equalsIgnoreCase("exit")){System.out.println("Exited Session"); System.exit(0);}
						else
						{
							System.out.println("Invalid Input, you have been exited from the session"); 
							System.exit(0);
						}     
					
					
						}

				}
			}




		}

		else{
			System.out.println("******UnSucessfull Login******");
			System.out.println("Incorrect Login Information, please try again logging in again by entering \'L\' \n"+
					"or \'R\' to register the an account, or \'exit\' to leave the session");
					String uLogin=in.nextLine();
					
					if( uLogin.equalsIgnoreCase("l")) UserLogIn(in, a);
					else if( uLogin.equalsIgnoreCase("r"))UserRegister(in, a);
					else if ( uLogin.equalsIgnoreCase("exit")){System.out.println("Exited Session"); System.exit(0);}
					else
					{
						System.out.println("Invalid Input, you have been exited from the session"); 
						System.exit(0);
					}     
				
				

		}

	}
	
	/*USERREGISTER
	 * Allows User to register to be apart of the system. 
	 * They enter a name, userid, and also a key (password).
	 * The password passes as an argument to the EncString method which will encrypt the key
	 * After user registers they have the option of logging in or registering a new account
	 */
	public static void UserRegister(Scanner in,ArrayList <User>a) throws NoSuchAlgorithmException
	{
		String name;
		String Userid;
		String key;

		System.out.println("****************USER REGISTRATION**************");

		System.out.println("Enter a name");
		name=in.nextLine();


		System.out.println("Create a Userid");
		Userid=in.nextLine();

		System.out.println("Create a secure key");
		key=in.nextLine();



		//make sure Userid is not in structure already
		if((UserExists(Userid,a))==true)
		{
			System.out.println("USER ALREADY EXISTS, choose another User id");
			UserRegister(in,a);
		}

		else
		{
			// add the name, userid, and the md5(key) as password
			a.add(new User(name,Userid, EncString(key)));

			System.out.println("Congratulations "+name+", you have been added to the system.");
			listUsers(a);
		}
		// user should login or allow another register

		String choice2;
		do
		{
			System.out.println("You can now now login with \'L\'  or register with \'R\' another user ");
			System.out.println("You can also exit the system by entering \'exit\'");
			choice2=in.nextLine();


			if (choice2.equalsIgnoreCase("L")) {
				UserLogIn(in,a); // method for logging in
			}
			else if (choice2.equalsIgnoreCase("R")) {
				UserRegister(in,a);
			}
			else if (choice2.equalsIgnoreCase("exit")) {
				System.out.println("Exiting System. Goodbye");
				System.exit(0);

			}
			else{
				System.out.println("Invalid Input");
			}
		}while(!choice2.equalsIgnoreCase("L") && !choice2.equalsIgnoreCase("R")&& !choice2.equalsIgnoreCase("exit"));

	}
/*  LISTUSERS
 *  will print out every object in arraylist,
 *  used solely for debugging purposes
 * 
 */
	public static void listUsers( ArrayList <User>a)
	{
		System.out.println("List of USERS:");
		for(int i=0; i< a.size();i++)
		{
			System.out.print(a.get(i).toString()+"\n");
		}

	}
	
	/*USEREXISTS
	 *  This method checks the arraylist for the userid
	 * if userid is in the list, then the passed userid is a valid user
	 * returns true, else will return false.
	 */
	public static boolean UserExists(String id, ArrayList <User>a)
	{  
		if( a.size()==0) return false; //if there is nothing in first element, no users, therefore false

		for (int i = 0; i < a.size(); i++) 
		{
			if(a.get(i).getUserID().equalsIgnoreCase(id))
			{
				return true;	
			}

		}

		return false;
	}
	/* ENCSTRING
	 *  this method encrypts and string passed through it by using
	 * MD5 encrytption. The result is then returned to be stored in a 
	 * a string. When user registers, inital password is stored as hash
	 */
	public static String EncString(String str) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		
		return sb.toString();
	}



}

