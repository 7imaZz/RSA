
package shorbgy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
	int p,q,n,z,d=0,e;	
        double c;
        BigInteger msgback;
        
        String encryptedMsg = "", decryptedMsg = ""; 
        
        
	System.out.println("Enter the text to be encrypted and decrypted");
        String msg=s.nextLine();
        
        p = generatePrimeNumber(); 
	while(true){
            q = generatePrimeNumber();
            if(p!=q){
                break; 
            }      
        }	
        
        n=p*q;
	z=(p-1)*(q-1);        
	for(e=2;e<z;e++)
        {
            if(gcd(e,z)==1) // e is for public key exponent
            {				
                break;
            }
        }
        
        for(int i=0;i<=9;i++)
	{
            int x=1+(i*z);
            if(x%e==0) //d is for private key exponent
            {
                d=x/e;
                break;
            }
        }
        
        for(int i=0; i<msg.length(); i++){
            c=(Math.pow((int)msg.charAt(i),e))%n;
            encryptedMsg += c+"-"; 
            //converting int value of n to BigInteger
            BigInteger N = BigInteger.valueOf(n);
            //converting float value of c to BigInteger
            BigInteger C = BigDecimal.valueOf(c).toBigInteger();
            msgback = (C.pow(d)).mod(N);
            decryptedMsg += (char)msgback.intValue();
        }
	
        System.out.println("Encrypted Msg: ");
        System.out.println(encryptedMsg);
        System.out.println("Decrypted Msg: ");
        System.out.println(decryptedMsg);
        
    }
    
    static int gcd(int e, int z){
        if(e==0)
            return z;	
        else
            return gcd(z%e,e);
    }
    
    static int generatePrimeNumber(){
        int primes[] = {157, 263, 373, 593, 607};
        return  primes[(int) (Math.random()*(primes.length-1))];
    }
}
