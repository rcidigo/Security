# Security
Project from my Secure Communications course dealing with Authentication

							S/Key System Program Authentication
created by Richard Idigo
Course: Secure Communications 
DATE: December 11, 2015

								   READ ME 

----------------------------------------------------------------INSTRUCTIONS-------------------------------------------------------------
1) User will be asked if they would like to register or login
	--- to register enter r
	--- to login enter l
 
*NOTE*: if user enters something other than l or r the program informs them the input was invalid and
they are instructed to enter again. 

1.1) User chooses to login
	-- The program will then ask for you to enter your userID 
        -- After entering userID, will ask to enter your password
	--If these are both correct, then will be redirected to a sucessful login page

*NOTE*: If user is not in the system, meaning have not previously registered, then program will alert that 
	the login information is incorrect. They will have the following options:
	a) Try logging in again by entering 'L'
	b) Register an account by entering  'R'
	c) Exit session by entering 'exit'
	(this will happen anytime a user enters information that doesnt no match the system's current state)

1.2) User chooses to register
	The program will be directed to the USER REGISTRATION page, where they will follow the corresponding instructions\
	-- Enter a name
	-- Enter a userid
	-- Enter a password

	After user enters information, they will be asked the following;
	a) Login by entering 'L'
	b) Register an account by entering  'R'
	c) Exit session by entering 'exit'

Program can support multiple users at the same time. Everytime register is called assuming this is a uqiue user.



*NOTE*: During registration, if the userid entered by the user is already in the system, then they will be alerted as such as and
	registration will restart asking for a name.

*NOTE About Exit*: For this program, whenever the user chooses to exit, System.exit(0) is executed, thus ending the program. Once program is ended can be 
		   re-ran again using eclipse or any other IDE.


----------------------------------------------------------END OF INSTRUCTIONS-----------------------------------------------------------------

							    ABOUT PROGRAM


------------------CRYTOGRAPHIC FUNCTIONS USED: MD5---------------------
WHERE
I implemented the MD5 encryption algorithm as a method in my source code called ENCSTRING. The method
takes a String value as a parameter, hashes it, and returns it. When a user registers for the first time, the password that they
enter is first sent to the ENCSTRING method where its encrypted and then the resulting hash is set as the password. When the user logs in,
they enter their password and the program then encrypts the attemp and checks to see if it equals the stored hash value. If so, login is sucessful.
Also to maintain the structure of the s/Key system, the old password is hashed using the encryption method mentioned and is set as the new password.
Since we are assuming the user is aware of the chain, user has each password and can login sucessfully.


WHY
For this program I decided to use the encryption algorithm MD5.
The main reason being the simple implmentation. I had no trouble creating my encryption method ENCSTRING after
reading resources online. The other reason was the easy conversion of the hash value back to a string. Lastly its 
decently fast to hash.



----------------------HOW INFORMATION IS BEING RECORDED-------------------
Each user is characterized by a User object I created to represent every valid user in the system.
The User class holds three fields; name, userid, and password. 

To represent a system of all the records, I used the arraylist data structure and declares its type user.
Now each entry of the list is a potential user in the system. When a user registers, their information is added to the arraylist
as a User object. When a user is loggin in, the program checks each entry for userid, if its found they are in the system.

There is no textfile that is storing information of any kind, all the information is solely on the system. 




----------------------SECURITY ANALYSIS------------------------------------
USE OF MD5
In terms of Security I know one aspect I can discuss is my choice in encryption algorithms. I used MD5 because of its simplicity and speed, but the 
truth is I could have chose more secure encryptions algorithms inlcuding SHA-1. In terms of attacks that can be used an attacker can utilize collision attacks
and find hashed messages that equal eachother( i.e hash(m1)=hash(m2)). They could use something like the birthday attack to try and crack the password.

STRUCTURE OF PROGRAM
In terms of the actual program structure, I believe I have a decently secure implmentation. Though MD5 maybe insecure, I believe my system compensates.
For instance, the passwords are not stored in some textfile some where else. If an attacker were to get a hold of a textfile will all the hashed passwords
then there is no limit on what they can do. Also with that, the old passwords are hashed as soon as the user logs in and are set as the new password. 
So everytime the user logs in, the old password destroyed and instead replaces with its hash.
  If sucessful login true ---> new password= hash(oldpassword)

Along with that, if the attacker breaks into the system all they will see is the hashed value of the password. If the attacker gets a hold of a userid
and doesnt have the password, there is no "forgot password" functionality. So the attacker cannot pose as the user and ask for a password to be sent. They have 
reduce to trying to break in the systen or brute force to get the password which is infeasible.
