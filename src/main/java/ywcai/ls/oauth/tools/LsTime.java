package ywcai.ls.oauth.tools;

import java.util.Calendar;

public class LsTime {
	public static String getNowDate() {
		String nowDate = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		return nowDate;
	}

	public static String getNowTime() {
		String nowTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND);
		return nowTime;
	}

	public static String getDetailTime() {
		return getNowDate() + " " + getNowTime();
	}
}
