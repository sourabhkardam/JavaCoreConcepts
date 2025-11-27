package com.corejava.concepts.dates;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JavaDates {

	public static void main(String[] args) throws ParseException {
		/* return time with UTC timezone (Z at the end means UTC) */
//		String instant = Instant.now().toString();
//		System.out.println(instant);

		/* return time with current/server timezone */
//		String zonedTime = ZonedDateTime.now().toString();
//		System.out.println(zonedTime);
//
//		DateFormat toFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//		String formattedDate1 = toFormat1.format(new Date());
//		System.out.println(formattedDate1);

		/**
		 * SimpleDateFormat is used to format current date into the required format. As,
		 * here we are converting date to ISO 8601 format i.e.
		 * "yyyy-MM-dd'T'HH:mm:ssXXX" where yyyy-MM-dd represents date part, 'T' is used
		 * to separate date and time part, HH:mm:ss represents time part and XXX is used
		 * to represent the timezone of current VM w.r.t UTC. For e.g. for IST timezone,
		 * w.r.t UTC it is +05:30 and for UTC timezone it is Z.
		 */
		/**
		 * So, if your VM's current timezone is IST (Indian Standard Time) then it will
		 * return current IST date with +05:30 at the end representing IST timezone and
		 * if your VM's current timezone is UTC then it will return current UTC date
		 * with Z at the end representing UTC timezone.
		 */
		/**
		 * For e.g. i) 2024-01-15T14:11:00+05:30, means this time is in IST timezone and
		 * +5:30 represents IST timezone.
		 */
		/**
		 * ii) 2024-01-15T08:41:00Z, means this time is in UTC timezone and Z represents
		 * UTC timezone.
		 */

		DateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

		/**
		 * Set VM timezone to Asia/Kolkata (IST). So, it will return current date in IST
		 * format and +05:30 at the end representing IST timezone
		 */
//		toFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		/**
		 * Set VM timezone to UTC. So, it will return current date in UTC format and Z
		 * at the end representing UTC timezone
		 */
//		toFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		String formattedDate = toFormat.format(new Date());
		System.out.println(formattedDate);

		SimpleDateFormat fromFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = fromFormatter.parse(new Timestamp(System.currentTimeMillis()).toString());
		String tx_date = toFormat.format(date);
		System.out.println(tx_date);

		/**
		 * Date '2024-01-15T14:11:00+05:30' means this date is in IST timezone and +5:30
		 * represents IST timezone. So, while parsing this date, it will convert this
		 * IST date to current/server timezone date. If current/server timezone is IST,
		 * then no change in date/time and if current/server timezone is UTC, then it
		 * will convert this IST date to UTC date i.e. '2024-01-15T08:41:00Z'
		 */
//		System.out.println(toFormat.parse("2024-01-15T14:11:00+05:30"));

		/**
		 * Similarly, Date '2024-01-15T14:11:00+00:00' means this date is in UTC
		 * timezone and +00:00 represents UTC timezone. So, while parsing this date, it
		 * will convert this UTC date to current/server timezone date. If current/server
		 * timezone is UTC, then no change in date/time and if current/server timezone
		 * is IST, then it will convert this UTC date to IST date i.e.
		 * '2024-01-15T19:41:00+05:30'
		 */
//		System.out.println(toFormat.parse("2024-01-15T14:11:00+00:00"));

		/**
		 * Similarly, Date '2024-01-15T14:11:00+08:00' means this date is in AWST
		 * timezone and +08:00 represents AWST timezone. So, while parsing this date, it
		 * will convert this AWST date to current/server timezone date. If
		 * current/server timezone is AWST, then no change in date/time and if
		 * current/server timezone is UTC, then it will convert this AWST date to UTC
		 * date i.e. '2024-01-15T06:11:00Z' and if current/server timezone is IST, then
		 * it will convert this AWST date to IST date i.e. '2024-01-15T11:41:00+05:30'
		 */
//		System.out.println(toFormat.parse("2024-01-15T14:11:00+08:00"));

		/**
		 * Similarly, Date '2024-01-15T08:41:00Z' means this date is in UTC timezone and
		 * Z represents UTC timezone. So, while parsing this date, it will convert this
		 * UTC date to current/server timezone date. If current/server timezone is UTC,
		 * then no change in date/time and if current/server timezone is IST, then it
		 * will convert this UTC date to IST date i.e. '2024-01-15T14:11:00+05:30'
		 */
//		System.out.println(toFormat.parse("2024-01-15T08:41:00Z"));

		// Print current timestamp
//		System.out.println(new Timestamp(new Date().getTime()));
	}
}
