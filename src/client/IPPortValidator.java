package client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPPortValidator {
	private static final Exception NoMatchFoundException = null;
	private String ipRegex;
	
	public IPPortValidator() {
		ipRegex = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
	}
	
	public boolean isIPValid(String ipToTest) {
		Pattern ipPattern = Pattern.compile(ipRegex);
		Matcher ipMatcher = ipPattern.matcher(ipToTest);
		if(!ipMatcher.matches()) {
			return false;
		}
		for(int i = 0; i < 4; i++) {
			if(Short.parseShort(ipMatcher.group(i + 1)) > 255 ||
					Short.parseShort(ipMatcher.group(i + 1)) < 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isPortValid(String portToTest) {
		short port;
		try {
			port = Short.parseShort(portToTest);
		} catch (NumberFormatException e) {
			return false;
		}
		if(port > 65535 || port < 0) {
			return false;
		}
		return true;
	}

	public byte[] getIPBytes(String ipToGetBytes) throws Exception {
		Pattern ipPattern = Pattern.compile(ipRegex);
		Matcher ipMatcher = ipPattern.matcher(ipToGetBytes);
		byte[] ipAsByteArray = new byte[4];

		if(!ipMatcher.matches()) {
			throw NoMatchFoundException;
		}
		for(int i = 0; i < 4; i++) {
			ipAsByteArray[i] = new Integer(ipMatcher.group(i + 1)).byteValue();
		}
		return ipAsByteArray;
	}
}
