package myPackage;
import java.security.MessageDigest;

public class StringUtil {
	
	public static String applySha256(String input)  {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hashString = new StringBuffer();
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hashString.append("0");
				hashString.append(hex);
			}
			return hashString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
		
	}

}
