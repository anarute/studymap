/*
 *
 *
 */
package br.com.jcomputacao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Murilo
 */
public class TimeUtil {
    public static final double SECOND = 1.0 / 1000;
    public static final double MINUTE = 1.0 / 60;
    public static final double HOUR = 1.0 / 60;
    private static final double DAY = 1.0 / 24;
    public static final long MILLIS_IN_A_MINUTE = 60 * 1000;
    public static final long MILLIS_IN_A_HOUR = 60 * MILLIS_IN_A_MINUTE;
    public static final long MILLIS_IN_A_DAY = 24 * MILLIS_IN_A_HOUR;
    static private long fakeTimeMillis = -1;
//    private static DateFormat formatDate = null;
    private static DateFormat shortTimeFormat = null;
    private static DateFormat shortDateFormat = null;
    private static DateFormat shortDateBdFormat = null;
    private static DateFormat shortDateTimeFormat = null;
    private static DateFormat shortDateTimeDbFormat = null;
    private static Date serverTime;
    private static Date localTime;

    static public java.util.Date createDate() {
        java.util.Date date = new java.util.Date();
        if (fakeTimeMillis != -1) {
            date.setTime(fakeTimeMillis);
        }
        return date;
    }

    static public java.sql.Date createDateSql() {
        java.util.Date dateUtil = createDate();
        return new java.sql.Date(dateUtil.getTime());
    }

    static public java.util.Calendar createCalendar() {
        Calendar calendar = java.util.Calendar.getInstance();
        if (fakeTimeMillis != -1) {
            calendar.setTimeInMillis(fakeTimeMillis);
        }
        return calendar;
    }

    static public java.util.Calendar createCalendar(java.util.Date data) {
        Calendar calendar = createCalendar();
        calendar.setTime(data);
        return calendar;
    }

    static public java.util.Calendar createCalendar(java.util.Calendar cal) {
        return createCalendar(cal.getTime());
    }

    static public void setFakeCurrentTimeMillis(long time) {
        fakeTimeMillis = time;
    }

    static public void setFakeCurrentDate(java.util.Date fakeDate) {
        setFakeCurrentTimeMillis(fakeDate.getTime());
    }

    static public void setFakeCurrentDate(String fakeDate) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
        try {
            setFakeCurrentDate(dateFormat.parse(fakeDate));
        } catch (ParseException e) {
        } catch (NullPointerException npe) {
        }
    }

    static public void setRealCurrentTimeMillis() {
        fakeTimeMillis = -1;
    }

    static public long currentTimeMillis() {
        if (fakeTimeMillis != -1) {
            return fakeTimeMillis;
        } else {
            return System.currentTimeMillis();
        }
    }

    static public java.sql.Date createDateSql(Date data) {
        return (data != null ? new java.sql.Date(data.getTime()) : null);
    }

    static public java.sql.Date createDateSql(Calendar data) {
        return (data != null ? createDateSql(data.getTime()) : null);
    }

    public static Date getToday() {
        return createDate();
    }

    public static Date getNextWeekDay() {
        Date date = createDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_WEEK)>=Calendar.FRIDAY) {
            int week = calendar.get(Calendar.WEEK_OF_MONTH);
            week++;
            calendar.set(Calendar.WEEK_OF_MONTH, week);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else {
            calendar.setTimeInMillis(calendar.getTimeInMillis()+MILLIS_IN_A_DAY);
        }
        return calendar.getTime();
    }

    public static Date getYesterday() {
        return subtractDaysToDate(TimeUtil.getToday(), 1);
    }

    public static java.sql.Date getTodayAsSqlDate() {
        return createDateSql();
    }

    public static Date getFirstDayOfCurrentMonth() {
        return getFirstDayOfMonth(getToday());
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = TimeUtil.createCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getLastDayOfCurrentMonth() {
        Calendar c = TimeUtil.createCalendar();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();

    }

    public static java.sql.Date getFirstDayOfCurrentMonthAsSQLDate() {
        return TimeUtil.createDateSql(TimeUtil.getFirstDayOfCurrentMonth());

    }

    public static java.sql.Date getLastDayOfCurrentMonthAsSQLDate() {
        return TimeUtil.createDateSql(TimeUtil.getLastDayOfCurrentMonth());

    }

    public static Date addDaysToDate(Date oldDate, int days) {
        Calendar c = TimeUtil.createCalendar();
        c.setTime(oldDate);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    public static Date subtractDaysToDate(Date oldDate, int days) {
        Calendar c = TimeUtil.createCalendar();
        c.setTime(oldDate);
        c.add(Calendar.DAY_OF_MONTH, (days * -1));
        return c.getTime();
    }

    public static Date subtractMonthsToDate(Date oldDate, int months) {
        Calendar c = TimeUtil.createCalendar();
        c.setTime(oldDate);
        c.add(Calendar.MONTH, (months * -1));
        return c.getTime();
    }

    public static int getDaysBetween(Date startDate, Date endDate) {
        long millisecondsBetweenStartDateAndEndDate = endDate.getTime() - startDate.getTime();
        double timeInSeconds = millisecondsBetweenStartDateAndEndDate * SECOND;
        double timeInMinutes = timeInSeconds * MINUTE;
        double timeInHours = timeInMinutes * HOUR;
        double timeInDays = timeInHours * DAY;
        return (int) timeInDays;
    }

    public static Date addMonthsToDate(Date oldDate, long months) {
        Calendar c = TimeUtil.createCalendar();
        c.setTime(oldDate);
        c.add(Calendar.MONTH, (int) months);
        return c.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar c = TimeUtil.createCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static boolean isLastDayOfCurrentMonth(Date data) {
        Calendar dateInformed = TimeUtil.createCalendar(data);
        Calendar today = TimeUtil.createCalendar();

        if (!checkMesAno(today, dateInformed)) {
            return false;
        }

        int maxDayInformed = dateInformed.get(Calendar.DAY_OF_MONTH);
        int maxDay = today.getActualMaximum(Calendar.DAY_OF_MONTH);

        return maxDayInformed == maxDay;
    }

    public static boolean isFirstDayOfCurrentMonth(Date data) {
        Calendar dateInformed = TimeUtil.createCalendar(data);
        Calendar today = TimeUtil.createCalendar();

        if (!checkMesAno(today, dateInformed)) {
            return false;
        }

        int minDayInformed = dateInformed.get(Calendar.DAY_OF_MONTH);
        int minDay = today.getActualMinimum(Calendar.DAY_OF_MONTH);

        return minDayInformed == minDay;

    }

    private static boolean checkMesAno(Calendar today, Calendar dateInformed) {
        int mesCorrente = today.get(Calendar.MONTH);
        int mesInformado = dateInformed.get(Calendar.MONTH);
        int anoCorrente = today.get(Calendar.YEAR);
        int anoInformado = dateInformed.get(Calendar.YEAR);

        if (mesCorrente != mesInformado) {
            return false;
        }

        if (anoCorrente != anoInformado) {
            return false;
        }


        return true;
    }

    public static Date getLastDayOfLastMonth() {
        return getLastDayOfLastMonth(getToday());
    }

    public static Date getLastDayOfNextMonth() {
        return getLastDayOfNextMonthy(getToday());
    }

    private static Date getLastDayOfNextMonthy(Date today) {
        Calendar c = TimeUtil.createCalendar(today);
        c.add(Calendar.MONTH, +1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    public static Date getLastDayOfLastMonth(Date date) {
        Calendar c = TimeUtil.createCalendar(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    public static Date getFirstDayOfLastMonth() {
        return getFirstDayOfLastMonth(getToday());
    }

    public static Date getFirstDayOfNextMonth() {
        return getFirstDayOfNextMonth(getToday());
    }

    public static Date getFirstDayOfNextMonth(Date today) {
        Calendar c = TimeUtil.createCalendar(today);
        c.add(Calendar.MONTH, +1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar c = TimeUtil.createCalendar(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    public static java.sql.Date getLastDayOfLastMonthAsSqlDate(Date date) {
        return TimeUtil.createDateSql(getLastDayOfLastMonth(date));
    }

    public static String getShortDateTime(Date date) {
        if (date == null) {
            return "00:00:00 00/00/00";
        }
        if (shortDateTimeFormat == null) {
            shortDateTimeFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        }
        return shortDateTimeFormat.format(date);
    }

    public static String getShortDateTimeForDb(Date date) {
        if (date == null) {
            return "0000-00-00 00:00:00";
        }
        if (shortDateTimeDbFormat == null) {
            shortDateTimeDbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return shortDateTimeDbFormat.format(date);
    }

    public static String getShortDateTimeForUi(Date date) {
        if (date == null) {
            return "";
        } else {
            return getShortDateTime(date);
        }
    }

    public static String getShortDate(Date date) {
        if (date == null) {
            return "00/00/00";
        }
        if (shortDateFormat == null) {
            shortDateFormat = new SimpleDateFormat("dd/MM/yy");
        }
        return shortDateFormat.format(date);
    }

    public static String getShortDateForBd(Date date) {
        if (date == null) {
            return "0000-00-00";
        }
        if (shortDateBdFormat == null) {
            shortDateBdFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return shortDateBdFormat.format(date);
    }

    public static String getShortDateForUi(Date date) {
        if (date == null) {
            return "";
        } else {
            return getShortDate(date);
        }
    }

    public static String getShortTime(Date date) {
        if (date == null) {
            return "00:00:00";
        }
        if (shortTimeFormat == null) {
            shortTimeFormat = new SimpleDateFormat("HH:mm");
        }
        return shortTimeFormat.format(date);
    }

    public static String getShortTimeForUi(Date date) {
        if (date == null) {
            return "";
        } else {
            return getShortTime(date);
        }
    }

    public static Calendar getDateAsCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static long getDiferenceInMillisDouble(Date date1, Date date2){
        long diference = 0;
        diference = date2.getTime() - date1.getTime();
        return diference;
    }
    
    public static void setServerTime(Date start) {
        serverTime = start;
        localTime = new Date();
    }

    public static Date getNow() {
        if (serverTime == null) {
            return new Date();
        }
        Date now = new Date();
        long time = now.getTime();
        long diff = serverTime.getTime()-localTime.getTime();
        now.setTime(time+diff);
        return now;
    }

    public static Date getTodayZeroTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(getToday());
        System.out.println("Data/Hora antes : " + getShortDateTime(c.getTime()));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        System.out.println("Data/Hora depois : " + getShortDateTime(c.getTime()));
        return c.getTime();
    }

}
