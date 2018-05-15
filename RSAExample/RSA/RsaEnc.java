
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
public class RsaEnc {

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, NoSuchPaddingException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
     	 Scanner kb=new Scanner(System.in);
 
		
		//******** KEY GENERATION ************//
        
        System.out.println("Enter key length(512,1024, or 2048 bits)");
        int kSize=kb.nextInt();
        kb.nextLine(); // consumes line
        
        float keystart= System.nanoTime();
        
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = new SecureRandom();
        
      
        pairgen.initialize(kSize, random);
        KeyPair keyPair = pairgen.generateKeyPair();
        
        // Saving the Public key in a file
        ObjectOutputStream publicKeyOS = new ObjectOutputStream(
            new FileOutputStream("pu.txt"));
        publicKeyOS.writeObject(keyPair.getPublic());
        publicKeyOS.close();
        
        // Saving the Private key in a file
        ObjectOutputStream privateKeyOS = new ObjectOutputStream(
            new FileOutputStream("pr.txt"));
        privateKeyOS.writeObject(keyPair.getPrivate());
        privateKeyOS.close();
        
        System.out.println();
        System.out.println("Private key and public key have been generated " + ((System.nanoTime()-keystart)/1000000)+ " ms");
        
        //******* ENCRYPTION**********///
        
        float enctime=System.nanoTime();
        System.out.println("\nNow entering Encryption");
        ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream("pu.txt"));
        Key publicKey = (Key) keyIn.readObject();
        keyIn.close();
        
        System.out.println("Enter plaintext file");
        String g= kb.nextLine();
        
        File file =new File(g);
        Scanner filereader=new Scanner(file);
        String pl=null;
        
        while(filereader.hasNext()){
        	pl=pl+ filereader.nextLine();
        }
        
        FileInputStream fileInput=null;
        byte[] bFile =pl.getBytes();
        byte [] cipherText=null;
        
	    
	    System.out.println("Encrypting...");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipherText=cipher.doFinal(bFile);
        
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ciphertext.txt"));    
        
        // writing to file for ciphertext
	       	out.writeObject((cipherText));
            
        out.close();
        
        System.out.println("Encryption Complete: "+((System.nanoTime()-enctime)/1000000) +" ms");
        
        
        
        //******* DECRYPTION**********///
 
        
        System.out.println("\nBeginning Decryption ");

        float dectime=System.nanoTime();
        // Grab private key from text
         keyIn = new ObjectInputStream(new FileInputStream("pr.txt"));
        Key privateKey = (Key) keyIn.readObject();
        keyIn.close();
        

        System.out.println("Decrypting...");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
       byte[] dplaintext=cipher.doFinal(cipherText);
        out = new ObjectOutputStream(new FileOutputStream("plaintext2.txt"));  
        
     // writing to file for ciphertext
	       	out.writeObject((dplaintext));
            
        out.close();
        
        float end= System.nanoTime();
        System.out.println("Decryption Complete" + ((System.nanoTime()-dectime)/1000000));
        
	}

}
