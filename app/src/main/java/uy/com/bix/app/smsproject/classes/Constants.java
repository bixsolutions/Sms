package uy.com.bix.app.smsproject.classes;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Constants {

	private Constants() {
	}

	public static final String MSG_SENT = "SMS_SENT";
	public static final String KEY_MINUTE = "Minute";
	public static final String KEY_HOUR = "Hour";
	public static final String KEY_DAY = "Day";
	public static final String KEY_MONTH = "Month";
	public static final String KEY_YEAR = "Year";
	public static final String KEY_ACTIVE = "Active";
	public static final String KEY_SENT_SMS = "sentMessages";
	public static final String KEY_LAST_DAY = "LastDay";
	public static final String KEY_NOTIFY = "Notify";
	public static final String KEY_PHONE = "Phone";
	public static final String KEY_MESSAGE = "Message";
	public static final String KEY_MAX = "Max";
	public static final String KEY_ERROR = "Error";
	public static final String KEY_CONFIGURED = "Configured";
	public static final int DEFAULT_HOUR = 23;
	public static final int DEFAULT_MINUTES = 30;
	public static final int DEFAULT_MAX = 1;
	public static final int DEFAULT_SENT_SMS = 0;
	public static final boolean DEFAULT_ACTIVE = false;
	public static final boolean DEFAULT_NOTIFY = false;
	public static final boolean DEFAULT_LAST_DAY = false;
	public static final boolean DEFAULT_ERROR = false;
	public static final boolean DEFAULT_CONFIGURED = false;
	public static final String DEFAULT_MESSAGE = "Hello";
	public static final String DEFAULT_PHONE = "1";
	public static final Map<String, String[]> ORGANIZATION_INFO;
	static {
		Map<String, String[]> aMap = new HashMap<>();
		aMap.put("ASH", new String[]{"Amigos", "24200"});
		aMap.put("FACB", new String[]{"Mamas", "62627"});
		aMap.put("UNCF", new String[]{"Unicef", "3662"});
		aMap.put("OJ", new String[]{"Niño", "20202"});
		aMap.put("PG", new String[]{"Amigo", "10100"});
		aMap.put("AI", new String[]{"Aldeas", "10101"});
		ORGANIZATION_INFO = Collections.unmodifiableMap(aMap);
	}
}
