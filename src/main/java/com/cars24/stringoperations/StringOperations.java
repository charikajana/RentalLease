package com.cars24.stringoperations;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/*import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;*/
import org.apache.commons.codec.binary.Base64;


public class StringOperations {
	
	private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
    private static final byte[] SALT = {
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };
	 final static int RANDOM_STRING_LENGTH = 10;
	 final static String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	 
	 
	public static String getTextBetweenTwoStrings(String givenString, String starttext, String endtext) {
		if (endtext.equals("")) {
			int beginIndex = givenString.indexOf(starttext);
			beginIndex += starttext.length();
			return givenString.substring(beginIndex);	
		}
		int beginIndex = givenString.indexOf(starttext);
		beginIndex += starttext.length();
		int endIndex =  givenString.indexOf(endtext, beginIndex);
		return givenString.substring(beginIndex,endIndex);
	}
	
	public static String getUniqueValue() throws UnknownHostException{
		return java.net.InetAddress.getLocalHost().toString().split("/")[0] + "_" + System.currentTimeMillis();		
	}
	
	 public static String generateRandomStringValue(){
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	
	    private static int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	    
	public static String getLastnCharacters(String inputString,int subStringLength){
		int length = inputString.length();
		if(length <= subStringLength){
			return inputString;
		}
		int startIndex = length-subStringLength;
		return inputString.substring(startIndex);
		}
	public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }

    private static String base64Encode(byte[] bytes) {
        // NB: This class is internal, and you probably should use another impl
        //return new BASE64Encoder().encode(bytes);
        return Base64.encodeBase64String(bytes);
    }

    public static String decrypt(String property) throws GeneralSecurityException, IOException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) throws IOException {
        // NB: This class is internal, and you probably should use another impl
        //return new BASE64Decoder().decodeBuffer(property);
        return Base64.decodeBase64(property);
    }
    
    public static String collectionConatinsString(Collection<String> collection, String containsValue){
    	String retValue = "";
    	for(String item:collection){
    		if(item.contains(containsValue)){
    			retValue = item;
    			break;
    		}			
    	}
    	return retValue;	
    }
    
    
	/*
	 * public static void printHashMapContents(HashMap<String,String> hashMap,
	 * Scenario scenario){ for(String key:hashMap.keySet()){ scenario.write(key +
	 * " : " + hashMap.get(key)); } }
	 */
       
    public static boolean compareHashMapContents(HashMap<String,String> hashMap1, HashMap<String,String> hashMap2){
      	 if(hashMap1.size() != hashMap2.size()){
      		 return false;
      	 }
      	 for(String key:hashMap1.keySet()){
      		 if(!(hashMap2.containsKey(key) && (hashMap2.get(key).equals(hashMap1.get(key))))){
      			 return false;
      		 }
      	 }
      	 return true;
      	 
       }
    
    public static HashMap<String,String> getHashMapFromArrayList(List<String> nameArrayList, List<String> valueArrayList){
      	 HashMap<String,String> returnValue = new HashMap<String,String>();
      	 for(int i=0;i<nameArrayList.size();i++){
      		 returnValue.put(nameArrayList.get(i), valueArrayList.get(i));
      	 }
      	 return returnValue;
   	}
	
}


