/**************************************************************************
 * $Date: 2018-06-20$
 * $Author: Khin Yadanar Thein $
 * $Rev:  $
 * 2018 AEON Microfinance (Myanmar) Company Limited. All Rights Reserved.
 *************************************************************************/

package mm.aeon.com.ats.front.common.util;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.model.SelectItem;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.formula.functions.Irr;
import org.jboss.seam.contexts.Contexts;

import mm.aeon.com.ats.front.common.constants.VCSCommon;
import mm.aeon.com.ats.front.sessions.LoginUserInfo;
import mm.com.dat.presto.main.core.dao.controller.DaoServiceInvoker;
import mm.com.dat.presto.main.exception.BaseException;
import mm.com.dat.presto.main.front.common.ApplicationContextProvider;
import mm.com.dat.presto.main.utils.exception.PrestoRuntimeException;
import mm.com.dat.presto.utils.common.InputChecker;
import mm.com.dat.presto.utils.common.PropertyUtil;

/**
 * 
 * Utility class for Fast project.
 * <p>
 * 
 * <pre>
 * In this class, it includes the common methods for whole Fast project.
 * </pre>
 * 
 * </p>
 * 
 */
public final class CommonUtil {

    /**
     * 
     * Default Constructor.
     * <p>
     * 
     * <pre>
     * </pre>
     * 
     * </p>
     * 
     * @return CommonUtil
     */
    private CommonUtil() {

    }

    /**
     * 
     * Compare two BigDecimal values.
     * <p>
     * 
     * <pre>
     * </pre>
     * 
     * </p>
     * 
     * @param amount
     *            the value to compare
     * @param compareAmount
     *            the value to compare
     * @return the status of greater than or not
     */
    public static boolean isGreaterThan(BigDecimal amount, BigDecimal compareAmount) {

        if (amount.compareTo(compareAmount) < 1) {
            return false;
        }
        return true;
    }

    /**
     * 
     * Get current Timestamp.
     * 
     * @return current Timestamp
     */
    public static Timestamp getCurrentTimeStamp() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return timestamp;
    }

    /**
     * 
     * Get current Timestamp.
     * 
     * @return current Timestamp
     */
    public static String getPlainCurrentTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd hhmmss");
        String timestamp = dateFormat.format(new Timestamp(new Date().getTime()));
        String[] time = timestamp.split(VCSCommon.SPACE);
        return time[1];
    }

    /**
     * 
     * Get Timestamp From String.
     * 
     * @return Timestamp
     */
    public static Timestamp getChangeStringToTimeStamp(String date) {

        Timestamp timestamp;
        try {
            // Long time = System.currentTimeMillis();
            date = date.replace("/", "-");
            date = date.concat(" 00:00:00");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            timestamp = new Timestamp(parsedDate.getTime());

        } catch (ParseException e) {
            throw new BaseException();
        }

        return timestamp;
    }

    /**
     * 
     * Get Timestamp From String.
     * 
     * @return Timestamp
     */
    public static Timestamp getChangeDMYStringToTimeStamp(String date) {

        Timestamp timestamp;
        try {
            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat output = new SimpleDateFormat("yyyy/MM/dd");
            String dateString = output.format(input.parse(date));
            dateString = dateString.replace("/", "-");
            dateString = dateString.concat(" 00:00:00");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(dateString);
            timestamp = new Timestamp(parsedDate.getTime());

        } catch (ParseException e) {
            throw new BaseException();
        }

        return timestamp;
    }

    public static String getChangeTimestampToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String string = dateFormat.format(timestamp);
        return string;
    }

    /**
     * 
     * Get current Year
     * 
     * @return year
     */
    public static String getCurrentYear() {
        Calendar now = Calendar.getInstance();
        int year;

        // if month = 2 => March
        if (now.get(Calendar.MONTH) < 3) {
            year = now.get(Calendar.YEAR) - 1;
        } else {
            year = now.get(Calendar.YEAR);
        }
        String currentYear = String.valueOf(year);

        return currentYear;
    }

    /**
     * 
     * Convert String to Double
     * 
     * @return double (after convertion)
     * 
     * @return null (error occur)
     */
    public static double convertDouble(String strValue) {

        if (!InputChecker.isBlankOrNull(strValue)) {
            Double doubleValue = Double.parseDouble(strValue);
            if (doubleValue > -1.00) {
                return doubleValue;
            }
        }
        return 0.0;
    }

    /**
     * 
     * convert String to int
     * 
     * @return int (after convertion)
     * 
     * @return null (error occur)
     */
    public static int convertInteger(String strValue) {
        int intValue = Integer.parseInt(strValue);
        if (intValue < 0) {
            return -1;
        }
        return intValue;
    }

    /**
     * 
     * check startPeriod is less than endPeriod
     * 
     * @return true (if startPeriod is less than endPeriod)
     */
    public static boolean isBeforeEndPeriod(Date startPeriod, Date endPeriod) {
        boolean isBefore = true;
        if (startPeriod.compareTo(endPeriod) <= 0) {
            isBefore = false;
        }
        return isBefore;
    }

    /**
     * 
     * get month 0 for January- 11 for December
     * 
     * @return int (index of month)
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month;
    }

    /**
     * 
     * get year depand on inputting date
     * 
     * @return int (index of year)
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static Date changeStringToDate(String dateStr) {
        Date date = null;
        try {
            if (dateStr != null) {
                if (dateStr.length() == 6) {
                    DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMM);
                    date = format.parse(dateStr);

                } else {
                    DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMMDD);
                    date = format.parse(dateStr);
                }
            }

        } catch (ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }
        return date;

    }

    public static Date changeYMDSlashStringToDate(String dateStr) {

        try {
            DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMMDD_SLASH);
            return format.parse(dateStr);
        } catch (

        ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }

    }

    public static Date changeYMSlashStringToDate(String dateStr) {

        try {
            DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMM_SLASH);
            return format.parse(dateStr);
        } catch (

        ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }

    }

    public static String changeDateToString(Date date) {

        if (date == null) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMMDD);
            return format.format(date);
        }
    }

    public static String changeDateToYMDSlashString(Date date) {

        if (date == null) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMMDD_SLASH);
            return format.format(date);
        }
    }

    public static String changeDateToYMSlashString(Date date) {

        if (date == null) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMM_SLASH);
            return format.format(date);
        }
    }

    public static Date changeDMYSlashStringToDate(String dateStr) {

        try {
            DateFormat format = new SimpleDateFormat(VCSCommon.DDMMYYYY_SLASH);
            return format.parse(dateStr);

        } catch (ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }

    }

    public static boolean isUnitPrice(String unitPrice) {
        Pattern p = Pattern.compile("^[0-9]{1,4}(\\.[0-9]*)?$");
        Matcher m = p.matcher(unitPrice);

        return m.matches();
    }

    public static boolean isNumeric(String inputStr) {
        Pattern p = Pattern.compile("^[0-9]{1,4}(\\.[0-9]*)?$");
        Matcher m = p.matcher(inputStr);

        return m.matches();
    }

    public static boolean isValidAmount(String inputStr) {
        Pattern p = Pattern.compile("^[0-9]{1,10}(\\.[0-9]*)?$");
        Matcher m = p.matcher(inputStr);

        return m.matches();
    }

    public static boolean isValidManMonth(String inputStr) {
        Pattern p = Pattern.compile("^[0-9]{1,6}(\\.[0-9]*)?$");
        Matcher m = p.matcher(inputStr);

        return m.matches();
    }

    public static boolean isValidBudgetTotal(String inputStr) {
        Pattern p = Pattern.compile("^[0-9]{1,16}(\\.[0-9]*)?$");
        Matcher m = p.matcher(inputStr);

        return m.matches();
    }

    public static boolean isValidInteger(String inputStr) {
        Pattern p = Pattern.compile("^[0-9]{1,2}$");
        Matcher m = p.matcher(inputStr);

        return m.matches();
    }

    public static boolean isValidDirId(String dirId) {

        Pattern p = Pattern.compile("[a-zA-Z0-9]{7}");
        Matcher m = p.matcher(dirId);

        return m.matches();
    }

    public static boolean isValidTeamName(String dirId) {
        Pattern p = Pattern.compile("^[a-zA-Z]+\\d*[a-zA-Z]*");
        Matcher m = p.matcher(dirId);

        return m.matches();
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat(VCSCommon.YYYYMMDD_SLASH);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getPlainCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat(VCSCommon.YYYYMMDD);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getFormattedNumberfor2Digit(String number) {
        return String.format("%.2f", Double.parseDouble(number));
    }

    public static String getFormattedNumberFor4Digit(String number) {
        return String.format("%.4f", Double.parseDouble(number));
    }

    public static String getFormattedNumber(String number, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        String numberAsString = decimalFormat.format(Double.parseDouble(number));

        return numberAsString;
    }

    /**
     * 
     * Get current Month
     * 
     * @return month(int)
     */
    public static int getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        int currentMonth = now.get(Calendar.MONTH) + 1;

        return currentMonth;
    }

    public static DaoServiceInvoker getDaoServiceInvoker() {
        DaoServiceInvoker daoServiceInvoker =
                (DaoServiceInvoker) ApplicationContextProvider.getBean("daoServiceInvoker");
        return daoServiceInvoker;
    }

    public static String getLoginUserName() {
        LoginUserInfo pmwUserInfo = (LoginUserInfo) Contexts.getSessionContext().get("userInfo");
        return pmwUserInfo.getUserId().trim();
    }

    public static int getLoginUserId() {
        LoginUserInfo pmwUserInfo = (LoginUserInfo) Contexts.getSessionContext().get("userInfo");
        return pmwUserInfo.getId();
    }

    public static String getLoginUserTypeId() {
        LoginUserInfo pmwUserInfo = (LoginUserInfo) Contexts.getSessionContext().get("userInfo");
        return pmwUserInfo.getUserTypeId();
    }

    public static LoginUserInfo getLoginUserInfo() {
        LoginUserInfo pmwUserInfo = (LoginUserInfo) Contexts.getSessionContext().get("userInfo");
        return pmwUserInfo;
    }

    public static String[] separateString(String param) {
        String[] output = param.split(VCSCommon.COMMA);
        return output;
    }

    /**
     * 
     * check firstDate is less than secondDate
     * 
     * @return firstDate (if firstDate is less than secondDate else secondDate)
     */
    public static String getStartDate(String firstDate, String secondDate) {
        String startDate = null;
        if (firstDate == null) {
            startDate = secondDate;
        } else {
            if (changeStringToDate(firstDate).compareTo(changeStringToDate(secondDate)) < 0) {
                startDate = firstDate;
            } else {
                startDate = secondDate;
            }
        }
        return startDate;
    }

    public static String increaseYear(String year) {
        int currentYear = Integer.parseInt(year) + 1;
        return Integer.toString(currentYear);
    }

    public static Date getStartDate(String dateStr) {
        Date date = null;
        try {
            if (dateStr != null) {
                dateStr = dateStr + "/01";
                DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMMDD_SLASH);
                date = format.parse(dateStr);

            }
        } catch (ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }
        return date;
    }

    public static Date getEndDate(String dateStr) {
        Date date = null;
        try {
            if (dateStr != null) {
                Calendar calendar = Calendar.getInstance();
                // passing month-1 because 0-->jan, 1-->feb... 11-->dec
                calendar.set(Integer.parseInt(dateStr.substring(0, 4)), Integer.parseInt(dateStr.substring(5, 7)) - 1,
                        1);
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

                DateFormat format = new SimpleDateFormat(VCSCommon.YYYYMMDD_SLASH);
                date = format.parse(format.format(calendar.getTime()));

            }
        } catch (ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }
        return date;
    }

    public static int getFileUploadSize() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String FILE_UPLOAD_SIZE = "file-upload-size";

        int fileUploadSize = Integer.parseInt(PropertyUtil.getProperty(PROJECT_CONFIG, FILE_UPLOAD_SIZE));
        return fileUploadSize;
    }

    public static String getUploadImageBaseFilePath() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String IMAGE_BASE_FILE_PATH = "imageBaseFilePath";

        String imageBaseFilePath = PropertyUtil.getProperty(PROJECT_CONFIG, IMAGE_BASE_FILE_PATH);
        return imageBaseFilePath;
    }

    public static String getPLMessagingImageBaseFilePath() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String IMAGE_BASE_FILE_PATH = "plMessagingImageFolder";

        String plMessagingImageBaseFilePath = PropertyUtil.getProperty(PROJECT_CONFIG, IMAGE_BASE_FILE_PATH);
        return plMessagingImageBaseFilePath;
    }

    public static String getProfileUploadImageFolder() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String PROFILE_IMAGE_FOLDER = "profileImageFolder";

        String profileImageFolder = PropertyUtil.getProperty(PROJECT_CONFIG, PROFILE_IMAGE_FOLDER);
        return profileImageFolder;
    }

    public static String getCouponUploadImageFolder() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String COUPON_IMAGE_FOLDER = "couponImageFolder";

        String couponImageFolder = PropertyUtil.getProperty(PROJECT_CONFIG, COUPON_IMAGE_FOLDER);
        return couponImageFolder;
    }

    public static String getAttachmentInfoUploadImageFolder() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String ATTACHMENT_IMAGE_FOLDER = "attachmentFileFolder";

        String attachmentImageFolder = PropertyUtil.getProperty(PROJECT_CONFIG, ATTACHMENT_IMAGE_FOLDER);
        return attachmentImageFolder;
    }

    public static int getCSVFileUploadSize() {
        final String PROJECT_CONFIG = "configure/project-config";
        final String FILE_UPLOAD_SIZE = "csv-file-upload-size";

        int fileUploadSize = Integer.parseInt(PropertyUtil.getProperty(PROJECT_CONFIG, FILE_UPLOAD_SIZE));
        return fileUploadSize;
    }

    public static String getFilePath() {
        final String projectConfig = "configure/project-config";
        final String filePathAttr = "server-file-path";

        String filePath = PropertyUtil.getProperty(projectConfig, filePathAttr);
        return filePath;
    }

    public static boolean hasDuplicateYear(String year, ArrayList<String> yearList) {
        int count = 0;
        if (yearList != null && yearList.size() > 1) {
            for (String tempYear : yearList) {
                if (tempYear == year) {
                    count++;
                }
            }
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    public static String getTotalManMonth(ArrayList<Double> totalPlanList) {
        double totalManMonth = 0.00;
        for (int i = 0; i < totalPlanList.size(); i++) {
            totalManMonth += totalPlanList.get(i);
        }
        return Double.toString(totalManMonth);

    }

    public static String removeBackSlash(String date) {
        if (date.matches("\\d{4}/\\d{2}/\\d{2}")) {
            date = date.replace(VCSCommon.BACK_SLASH, VCSCommon.BLANK);
        }
        return date;
    }

    /**
     * 
     * check two dates difference
     * 
     * @return days (difference days)
     */
    public static long getDifferenceDays(String sendDate, String currentDate) {
        long diffDays = 0;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date send = format.parse(sendDate);
            Date current = format.parse(currentDate);

            long diffTime = current.getTime() - send.getTime();
            diffDays = diffTime / (1000 * 60 * 60 * 24);

        } catch (ParseException e) {
            throw new PrestoRuntimeException(e.getMessage());
        }
        return diffDays;
    }

    public static boolean isValidPhoneNo(String inputStr) {
        Pattern p = Pattern.compile("[09][0-9]{7,10}");
        Matcher m = p.matcher(inputStr);

        return m.matches();
    }

    public static boolean isValidDMYDate(String dateString) {
        Pattern p = Pattern.compile("[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}");
        Matcher m = p.matcher(dateString);

        return m.matches();
    }

    public static boolean isValidAgreementNo(String agreementNo) {
        Pattern p = Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{10}-[0-9]{1}");
        Matcher m = p.matcher(agreementNo);

        return m.matches();
    }

    public static Map<Integer, String> getLocationMap() {
        Map<Integer, String> locationMap = new HashMap<>();
        locationMap.put(0, VCSCommon.YANGON);
        locationMap.put(1, VCSCommon.MANDALAY);

        return locationMap;
    }

    public static String changeLocation(int location) {
        String result = null;
        switch (location) {
            case VCSCommon.NOT_VALID:
                result = VCSCommon.YGN_LOCATION;
                break;
            case VCSCommon.IS_VALID:
                result = VCSCommon.MDY_LOCATION;
                break;
            default:
                break;
        }
        return result;
    }

    public static String changeLocationValue(String location) {
        String result = null;
        switch (location) {
            case VCSCommon.YGN_LOCATION:
                result = VCSCommon.ZERO;
                break;
            case VCSCommon.MDY_LOCATION:
                result = VCSCommon.ONE;
                break;
            default:
                break;
        }
        return result;
    }

    public static String showRecord(int sumCount) {
        String recordCount = null;

        if (sumCount == VCSCommon.ONE_INTEGER) {
            recordCount =
                    VCSCommon.OPEN_BRACKET + String.valueOf(sumCount) + VCSCommon.CLOSE_BRACKET + VCSCommon.RECORD;
        } else if (sumCount == VCSCommon.ZERO_INTEGER) {
            recordCount = null;
        } else {
            recordCount =
                    VCSCommon.OPEN_BRACKET + String.valueOf(sumCount) + VCSCommon.CLOSE_BRACKET + VCSCommon.RECORDS;
        }
        return recordCount;
    }

    public static Date getFirstDayOfMonth() {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.DAY_OF_MONTH, 1);
        return cl.getTime();
    }

    public static ArrayList<SelectItem> getJudgementStatusList(boolean isSearch) {

        ArrayList<SelectItem> judgementStatusList = new ArrayList<SelectItem>();

        SelectItem item = new SelectItem();
        if (isSearch) {
            item = new SelectItem();
            item.setLabel(VCSCommon.BLANK);
            item.setValue(0);
            judgementStatusList.add(item);
        }

        item = new SelectItem();
        item.setLabel(VCSCommon.JD_STATUS_APPROVE);
        item.setValue(2);
        judgementStatusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.JD_STATUS_REJECT);
        item.setValue(3);
        judgementStatusList.add(item);

        item = new SelectItem();
        item.setLabel(VCSCommon.JD_STATUS_CANCEL);
        item.setValue(4);
        judgementStatusList.add(item);

        return judgementStatusList;
    }

    public static List<String> getApprovedHeaderList() {
        List<String> headerList = new ArrayList<String>();
        headerList.add(VCSCommon.HEADER_NO);
        headerList.add(VCSCommon.HEADER_APPROVED_DATE);
        headerList.add(VCSCommon.HEADER_BRANCH_NAME);
        headerList.add(VCSCommon.HEADER_CUSTOMER_NAME);
        headerList.add(VCSCommon.HEADER_CUSTOMER_CODE);
        headerList.add(VCSCommon.HEADER_APPLICATION_FORM_NO);
        headerList.add(VCSCommon.HEADER_AGREEMENT_NO);
        headerList.add(VCSCommon.HEADER_TERMS);
        headerList.add(VCSCommon.HEADER_FINANCE_AMOUNT);
        headerList.add(VCSCommon.HEADER_PRODUCT);

        return headerList;
    }

    public static List<String> getRejectedHeaderList() {
        List<String> headerList = new ArrayList<String>();
        headerList.add(VCSCommon.HEADER_REJECT_NO);
        headerList.add(VCSCommon.HEADER_APPLICATION_DATE);
        headerList.add(VCSCommon.HEADER_BRANCH_NAME);
        headerList.add(VCSCommon.HEADER_CUSTOMER_NAME);
        headerList.add(VCSCommon.HEADER_CUSTOMER_CODE);
        headerList.add(VCSCommon.HEADER_TOWNSHIP);
        headerList.add(VCSCommon.HEADER_APPLICATION_NO);
        headerList.add(VCSCommon.HEADER_INTEREST_RATE);
        headerList.add(VCSCommon.HEADER_TERMS_OF_FINANCE);
        headerList.add(VCSCommon.HEADER_FINANCE_AMOUNT_MMK);
        headerList.add(VCSCommon.HEADER_INTEREST_TOTAL_MMK);
        headerList.add(VCSCommon.HEADER_REPAYMENT_TOTAL_MMK);
        headerList.add(VCSCommon.HEADER_REJECT_REASON);
        headerList.add(VCSCommon.HEADER_COMMENT);

        return headerList;
    }

    public static List<String> getCancelledHeaderList() {
        List<String> headerList = new ArrayList<String>();
        headerList.add(VCSCommon.HEADER_NO);
        headerList.add(VCSCommon.HEADER_APPLICATION_DATE);
        headerList.add(VCSCommon.HEADER_BRANCH_NAME);
        headerList.add(VCSCommon.HEADER_CUSTOMER_NAME);
        headerList.add(VCSCommon.HEADER_CUSTOMER_CODE);
        headerList.add(VCSCommon.HEADER_TOWNSHIP);
        headerList.add(VCSCommon.HEADER_APPLICATION_NO);
        headerList.add(VCSCommon.HEADER_INTEREST_RATE);
        headerList.add(VCSCommon.HEADER_TERMS_OF_FINANCE);
        headerList.add(VCSCommon.HEADER_FINANCE_AMOUNT_MMK);
        headerList.add(VCSCommon.HEADER_CANCEL_INTEREST_TOTAL_MMK);
        headerList.add(VCSCommon.HEADER_CANCEL_REPAYMENT_TOTAL_MMK);
        headerList.add(VCSCommon.HEADER_DECLINE_REASON);
        headerList.add(VCSCommon.HEADER_COMMENT);

        return headerList;
    }

    public static List<String> getCustomerHeaderList() {
        List<String> headerList = new ArrayList<String>();
        headerList.add(VCSCommon.HEADER_CUSTOMER_ID);
        headerList.add(VCSCommon.HEADER_CUSTOMER_NAME);

        return headerList;
    }

    public static String generateDigit() {
        int randomPIN = (int) (Math.random() * 90000) + 10000;
        return String.valueOf(randomPIN);
    }

    public static String decodePassword(String encodedPassword, boolean isDecode) {

        Base64 codec = new Base64();
        byte[] temp = null;
        String plainPassword = null;

        if (isDecode) {
            if (encodedPassword != null) {
                temp = codec.decode(encodedPassword.getBytes());
            }
        } else {
            temp = codec.encode(encodedPassword.getBytes());
        }
        plainPassword = new String(temp);

        return plainPassword;
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static char[] getOTP() {

        String numbers = "0123456789";
        Random rndm_method = new Random();
        int len = VCSCommon.OTP_LENGTH;

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static Date minusYearsFromToday(int years) {
        return minusYearFromDate(getCurrentTimeStamp(), years);
    }

    public static Date minusYearFromDate(Date date, int years) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, (years * -1));

        return cal.getTime();
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }

    /**
     * Get Current Time.
     */

    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Convert date into custom pattern String.
     */
    public static String formatByPattern(Date input, String pattern) {

        // Empty value check.
        if (input == null) {
            return null;
        }

        if (pattern == null || "".equals(pattern)) {
            return null;
        }

        // Convert process.
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(input);
    }

    /**
     * Get the current year, month and date in string form.
     */
    public static String getCurrentDateInString() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currentDate = df.format(Calendar.getInstance().getTime());
        return currentDate;
    }

    /**
     * Get DateTime as String.
     */
    public static String getDateTimeString(Date date) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        String currentDate = df.format(date);
        return currentDate;
    }

    /**
     * Get the last date of last month.
     */
    public static Date getLastMonthEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getFirstDayOfMonth(Date criDate) {
        Date firstDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(criDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        firstDate = cal.getTime();

        return firstDate;
    }

    public static Date getCurrentMonthLastDate(Date updateDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(updateDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getNextMonthFirstDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getPreviousMonthLastDayOfInputDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static boolean isPureAscii(String v) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(v);
        // or "ISO-8859-1" for ISO Latin 1
        // or StandardCharsets.US_ASCII with JDK1.7+
    }

    // Aeon Loan Calculation
    // ----------------------------------------------------------------------------------------------------

    public static double calculateLastPayment(double totalProductPrice, double firstPayment, double monthlyPayment,
            double totalInterest, int loanTerm, double monthlyInstallment) {
        double lastPayment = 0;
        Calendar cal = Calendar.getInstance();

        Date today = cal.getTime();
        cal.add(Calendar.MONTH, loanTerm + 1); // to get previous year add -1
        cal.set(5, 2);
        Date lastDate = cal.getTime();

        long diff = lastDate.getTime() - today.getTime();
        long totalDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        List<Double> cashFlows = new ArrayList<Double>();
        cashFlows.add(totalProductPrice * -1);
        cashFlows.add(firstPayment);
        for (int i = 0; i < loanTerm - 1; i++) {
            cashFlows.add(monthlyPayment);
        }

        double[] target = new double[cashFlows.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = cashFlows.get(i);
        }

        double amountDue = Irr.irr(target) * 100;

        double eirDays = 0;
        if (totalDays <= 361 && totalDays >= 357) {
            eirDays = amountDue * 365 / totalDays;
        } else {
            eirDays = amountDue;
        }

        double baseEIR = amountDue;

        double apr = 0;
        if (loanTerm > 12) {
            apr = eirDays * 12;
        } else {
            apr = eirDays * loanTerm;
        }

        double apr28 = 0;
        if (apr > 28) {
            apr28 = (totalInterest * 28 / 100) / apr * 100;
        } else {
            apr28 = 0;
        }
        DecimalFormat ttt = new DecimalFormat("######");

        double readjustLastPayment = 0;
        if (apr > 28) {
            readjustLastPayment = Math.round((totalInterest - Double.parseDouble(ttt.format(apr28))) / 100) * 100;
        } else {
            readjustLastPayment = 0;
        }

        lastPayment = monthlyInstallment - readjustLastPayment;
        return lastPayment;
    }

    public static double calculateFirstPayment(double initialPaymentForPSG, double totalRepayment,
            double monthlyInstallment, int loanTerm, int firstInstallmentRound, double firstPaymentForPSG) {
        double firstPayment = 0;

        if (initialPaymentForPSG == 0) {
            double value1 = monthlyInstallment * (loanTerm - 1);
            double value2 = totalRepayment - value1;
            Integer value2AsInt = (int) value2;
            String value2AsString = value2AsInt.toString();
            String lastTwoDigitsStringForValue2;
            int lastTwoDigitsForValue2;

            if (value2AsString.length() > 2) {
                lastTwoDigitsStringForValue2 = value2AsString.substring(value2AsString.length() - 2);
            } else {
                lastTwoDigitsStringForValue2 = value2AsString;
            }
            lastTwoDigitsForValue2 = Integer.parseInt(lastTwoDigitsStringForValue2);

            if (lastTwoDigitsForValue2 < 50) {
                double value3 = monthlyInstallment * (loanTerm - 1);
                double value4 = totalRepayment - value3;
                firstPayment = excelRoundDown(value4, firstInstallmentRound);
            } else {
                double value5 = monthlyInstallment * (loanTerm - 1);
                double value6 = totalRepayment - value5;
                firstPayment = excelRoundUp(value6, firstInstallmentRound);
            }

        } else {
            firstPayment = firstPaymentForPSG;
        }

        return firstPayment;
    }

    public static double calculateFirstPaymentForPSG(double initialPaymentForPSG, boolean paymentOnSaleDate) {
        double firstPaymentForPSG = 0;
        if (paymentOnSaleDate) {
            firstPaymentForPSG = initialPaymentForPSG;
        } else {
            firstPaymentForPSG = initialPaymentForPSG;
        }

        return firstPaymentForPSG;
    }

    public static double modifyCalculateTotalRepayment(double monthlyPayment, int loanTerm, double firstPayment,
            double lastPayment) {
        double modifyTotalRepayment = 0;
        modifyTotalRepayment = (monthlyPayment * (loanTerm - 2)) + firstPayment + lastPayment;
        return modifyTotalRepayment;
    }

    public static double calculateMonthlyInstallment(double totalProductPrice, double monthlyInstallmentForPSG,
            int loanTerm, int monthlyInstallmentRound) {
        double monthlyInstallment = 0;
        if (monthlyInstallmentForPSG == 0) {
            monthlyInstallment = excelRoundDown(totalProductPrice / loanTerm, monthlyInstallmentRound);
        } else {
            monthlyInstallment = monthlyInstallmentForPSG;
        }
        return monthlyInstallment;
    }

    public static double calculateTotalRepayment(double financeAmount, double totalInterest) {
        double totalRepayment = 0;
        totalRepayment = financeAmount + totalInterest;
        return totalRepayment;
    }

    public static double calculateTotalInterest(double financeAmount, int loanTerm, double interestRateMonthly,
            double totalInterestForPSG) {
        double totalInterest = 0;

        if (totalInterestForPSG == 0) {
            if (financeAmount * interestRateMonthly * loanTerm > 49) {
                totalInterest = Math.ceil((financeAmount / 100) * interestRateMonthly * loanTerm / 100) * 100;
            } else {
                totalInterest = Math.floor((financeAmount / 100) * interestRateMonthly * loanTerm / 100) * 100;
            }
        } else {
            totalInterest = totalInterestForPSG;
        }

        return totalInterest;
    }

    public static double calculateFinanceAmountForPSG(double totalProductPrice, double downPayment,
            double promoDiscount, double insPremium, double vatAmount, double financeAmountForPSG,
            boolean paymentOnSaleDate) {
        double financeAmount = 0;
        if (financeAmountForPSG == 0) {
            if (paymentOnSaleDate) {
                financeAmount = totalProductPrice - downPayment - promoDiscount + insPremium + vatAmount;
            } else {
                financeAmount = totalProductPrice - downPayment - promoDiscount + insPremium + vatAmount;
            }
        } else {
            financeAmount = financeAmountForPSG;
        }

        return financeAmount;
    }

    public static double calculateProcessingFees(double totalProductPrice) {
        double processingFees = 0;
        if (totalProductPrice >= 100000 && totalProductPrice <= 200000) {
            processingFees = (double) 1000;
        } else if (totalProductPrice > 200000 && totalProductPrice <= 300000) {
            processingFees = (double) 2000;
        } else if (totalProductPrice > 300000 && totalProductPrice <= 400000) {
            processingFees = (double) 3000;
        } else if (totalProductPrice > 400000 && totalProductPrice <= 500000) {
            processingFees = (double) 4000;
        } else if (totalProductPrice > 500000 && totalProductPrice <= 600000) {
            processingFees = (double) 5000;
        } else if (totalProductPrice > 600000 && totalProductPrice <= 700000) {
            processingFees = (double) 6000;
        } else if (totalProductPrice > 700000) {
            processingFees = (double) 10000;
        }
        return processingFees;
    }

    public static Double excelRoundUp(double amount, int signature) {
        double roundDownValue = 0;
        if (signature > 0) {
            BigDecimal bd = new BigDecimal(amount);
            bd = bd.setScale(signature, BigDecimal.ROUND_UP);
            roundDownValue = bd.doubleValue();
        }
        if (signature < 0) {
            roundDownValue = roundItTheHardWayForRoundUp(amount, signature);
        }
        return roundDownValue;

    }

    public static Double excelRoundDown(double amount, int signature) {
        double roundDownValue = 0;
        if (signature > 0) {
            BigDecimal bd = new BigDecimal(amount);
            bd = bd.setScale(signature, BigDecimal.ROUND_DOWN);
            roundDownValue = bd.doubleValue();
        }
        if (signature < 0) {
            roundDownValue = roundItTheHardWayForRoundDown(amount, signature);
        }
        return roundDownValue;

    }

    public static Double roundItTheHardWayForRoundUp(double x, int p) {
        return ((double) Math.ceil(x * Math.pow(10, p))) / Math.pow(10, p);
    }

    public static Double roundItTheHardWayForRoundDown(double x, int p) {
        return ((double) Math.floor(x * Math.pow(10, p))) / Math.pow(10, p);
    }
}
