package nss.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wbf.web.BizCustomer;

/**
* NssStringUtils
* @Description
* <pre>
* 	기업뱅킹 StringUtils
* </pre>
* @Author woori
* @Since 2020-05-27
* @Modification-information
* <pre>
* 수정일		수정자			수정내용
* ----------------------------------------------------------------
* 2020-05-27	woori		최초 작성
* 2020-12-14	hjkim		trim 중복호출 제거
* </pre>
*/
public class NssStringUtils extends StringUtils {

	/**
	 * String "" 을 전달받은 파라미터값으로 변경
	 * @param param String
	 * @return 입력값 "" 인 경우 전달받은 값으로 return
	 */
	public static String null2default(String param, String defaultValue) {
		if (param == null) {
			return defaultValue;
		}
		if (param.trim().equals("")) {
			return defaultValue;
		}

		return NssStringUtils.trim(param);
	}

	public static String null2default(Object param, String defaultValue) {
		String str = "";
		if (null == param) {
			return defaultValue;
		}
		str = param.toString();
		if (str.trim().equals("")) {
			return defaultValue;
		}

		return NssStringUtils.trim(str);
	}

	/**
	 * String trim 또는 null을 ""로 변경
	 * @param param String
	 * @return 입력값 trim, null인 경우 ""
	 */
	public static String null2void(String param) {

		return NssStringUtils.null2default(param, "");
	}

	/**
	 * String trim 또는 null을 " "로 변경
	 * @param param String
	 * @return 입력값 trim, null인 경우 ""
	 */
	public static String null2Space(String param) {

		return NssStringUtils.null2default(param, " ");
	}

	/**
	 * Object값이 String인경우 String으로 cast 후 trim<br>
	 * null인 경우 ""null을 ""로 변경하여 리턴
	 * @param param Object
	 * @return 입력값 trim, null인 경우 ""
	 */
	public static String null2void(Object param) {
		try {
			String str = "";
			if (null == param) {
				return "";
			} else if (param instanceof String) {
				str = param.toString();
				if ("".equalsIgnoreCase(str.trim())) {
					return "";
				}
			} else {
				str = String.valueOf(param);
				if ("".equalsIgnoreCase(str.trim())) {
					return "";
				}
			}
			return NssStringUtils.trim(str);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Object값이 String인경우 String으로 cast 후 trim<br>
	 * null인 경우 " "null을 " "로 변경하여 리턴
	 * @param param Object
	 * @return 입력값 trim, null인 경우 ""
	 */
	public static String null2Space(Object param) {
		try {
			if (param == null) {
				return " ";
			}
			if (((String) param).trim().equals("")) {
				return " ";
			}

			return NssStringUtils.trim(((String) param));
		} catch (Exception e) {
			return " ";
		}
	}

	/**
	 * 파라미터가 null이나 "" 이면 "0" 리턴
	 * @param param
	 * @return 0
	 */
	public static String null2ZeroStr(String param) {
		if (param == null) {
			return "0";
		}
		if (param.trim().equals("")) {
			return "0";
		}

		return param;
	}

	/**
	 * 파라미터가 null이나 "" 이면 "0" 리턴
	 * @param param
	 * @return 0
	 */
	public static int null2zero(String param) {
		if (param == null) {
			return 0;
		}
		if (param.trim().equals("")) {
			return 0;
		}

		return Integer.parseInt(param);
	}

	/**
	 * 파라미터가 null이나 "" 이면 "0.0" 리턴
	 * @param param
	 * @return 0
	 */
	public static Double null2zero2(String param) {
		if (param == null) {
			return 0.0;
		}
		if (param.trim().equals("")) {
			return 0.0;
		}

		return Double.parseDouble(param);
	}

	/**
	 * Object값이 null이나 "" 이면 "0" 리턴
	 * >> warning if param value is int it doesn't work
	 * @param param Object
	 * @return 0
	 */
	public static int null2zero(Object param) {
		try {
			if (param == null) {
				return 0;
			}
			if (((String) param).trim().equals("")) {
				return 0;
			}

			return Integer.parseInt((String) param);

		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 입력한 String의 맨앞에있는 '0'을 없앤다.
	 * e) "00001234"   ---> "1234"
	 * 수정(2002-01-31)
	 * 소수점 처리
	 * e) "000.00"     ---> "0.00"
	 * 작성 날짜: (2001-12-24 오후 6:17:07)
	 * @return java.lang.String
	 */
	public static String rmZero(String str) {
		if (str == null) {
			return "";
		}
		char indexChr = ' ';
		int index = 0;
		while (index < str.length()) {
			if (str.charAt(index) == '0') {
				index++;
			} else {
				indexChr = str.charAt(index);
				break;
			}
		}
		if (index < str.length()) {
			return str.substring(indexChr == '.' ? index - 1 : index);
		} else {
			return "0";
		}
	}

	/**
	 * 입력한 String의 맨앞에있는 '0'을 없앤다.
	 * e) "00001234"   ---> "1234"
	 * 수정(2002-01-31)
	 * 소수점 처리
	 * e) "000.00"     ---> "0.00"
	 * 작성 날짜: (2001-12-24 오후 6:17:07)
	 * @return java.lang.String
	 */
	public static String rmZero2(String str) {
		if (str == null) {
			return "0";
		}
		byte[] bStr = str.getBytes();

		int iIndex = 0;
		int iCount = bStr.length;
		boolean bMinus = false;

		if (bStr[0] == (byte) '-') {
			bMinus = true;
			bStr[0] = (byte) ' ';
			iIndex++;
		}

		for (; iIndex < iCount; iIndex++) {
			if ((bStr[iIndex] == (byte) '0') || (bStr[iIndex] == (byte) ' ')) {
				bStr[iIndex] = (byte) ' ';
			} else {
				break;
			}
		}

		if (iIndex < iCount) {
			if ((bStr[iIndex] == (byte) '.') && (iIndex > 1)) {
				bStr[--iIndex] = (byte) '0';
			}

			if (bMinus && (iIndex > 1)) {
				bStr[--iIndex] = (byte) '-';
			}

		} else {
			bStr[iCount - 1] = (byte) '0';
		}

		return new String(bStr, 0, iCount);
	}

	/**
	 * 입력한 String의 맨앞,뒤에있는 '0'을 없앤다.
	 * e) "000012340000"   ---> "1234"
	 * 소수점 처리
	 * e) "0045600.4500"     ---> "45600.45"
	 * 작성 날짜: (2004-07-20 add by jimi0203)
	 * @return java.lang.String
	 */
	public static String removeZero(String str) {
		if (str == null) {
			return "";
		}
		String tmp = NssStringUtils.rmZero(str);
		int index = tmp.length() - 1;
		while (0 < index) {
			if (tmp.charAt(index) == '0') {
				index--;
			} else {
				break;
			}
		}

		return tmp.substring(0, index + 1);
	}

	/**
	 * 입력한 String의 맨앞에있는 '0'을 없애고 offset길이 만큼의 뒤의 '0'을 없앤다.
	 * e) "000012340000" 의 offset=2일 경우  ---> "123400"
	 * 작성 날짜: (2004-08.05 add by jimi0203)
	 * @return java.lang.String
	 */
	public static String removeZero(String str, int offset) {
		if (str == null) {
			return "";
		}
		String tmp = NssStringUtils.rmZero(str);
		int index = tmp.length() - 1;
		int st = tmp.length() - offset;
		while (st <= index) {
			if (tmp.charAt(index) == '0') {
				index--;
			} else {
				break;
			}
		}
		return tmp.substring(0, index + 1);
	}

	/**
	 * 일반 문자열을 받아서 금액 형식으로 변환하여 리턴
	 * @param str
	 * @return 금액 형식으로 변경된 문자열
	 */
	public static String getMoneyForm(String str) {
		if (str == null) {
			return "";
		}
		int offset = str.indexOf(".");
		String work_str = "";
		String nonwork_str = "";
		if (offset > 0) {
			//소수점이 있다
			work_str = str.substring(0, offset);
			nonwork_str = str.substring(offset, str.length());
		} else {
			work_str = str;
		}
		DecimalFormat df = new DecimalFormat("###,##0");
		double d = 0;
		try {
			d = Double.valueOf(work_str).doubleValue();
		} catch (Exception e) {
			d = 0;
		}
		return df.format(d) + nonwork_str;
	}

	/**
	 * 일반 문자열을 받아서 금액 형식으로 변환하여 리턴
	 * @param str
	 * @param dist (0 :일반금액표시#,###,###, 1: 뒤에 3자리에 소수점을 붙여서 금액표시 #,###.###, 2: 뒤에 3자리를 제거하고 금액표시)
	 *                      (3:뒤에 2자리를 제거하고 금액표시) (4:소숫점아래를 제거하고 금액표시) (5:뒤에 1자리를 제거하고 금액표시)
	 *        2017-10-16 박재풍 : 0 번에 대한 처리가 없어 로직 추가함.
	 * @return 금액 형식으로 변경된 문자열
	 */
	public static String getMoneyForm(String str, String dist) {
		if (dist.equals("1")) {
			str = NssStringUtils.makerLoanRate(str, 0, 3);
		} else if (dist.equals("2")) {
			if (str.length() > 3) {
				str = str.substring(0, str.length() - 3);
				int offset = str.indexOf(".");
				if (offset == (str.length() - 1)) {//맨뒤에소수점있다 제거
					str = str.substring(0, str.length() - 1);
				}
			}
		} else if (dist.equals("3")) {
			if (str.length() > 2) {
				str = str.substring(0, str.length() - 2);
			}
		} else if (dist.equals("4")) {
			str = NssStringUtils.toDecimal(str);
		} else if (dist.equals("5")) {
			if (str.length() > 1) {
				str = str.substring(0, str.length() - 1);
			}
		} else if (dist.equals("0")) {
			if (str == null) {
				return "";
			}
			int offset = str.indexOf(".");
			if (offset > 0) {
				//소수점이 있다
				str = str.substring(0, offset);
			} else {
//				str = str;
			}
		}
		return NssStringUtils.getMoneyForm(str);
	}

	public static String getMoneyForm(String str, Integer decimalPlaces) {
		if (str == null) {
			return "";
		}
		int offset = str.indexOf(".");
		if (offset > 0) {
			String tempStr = str.substring(str.indexOf(".")+1, str.length());
			if (tempStr.length() > decimalPlaces) {
				str = str.substring(0, offset);
				tempStr = tempStr.substring(0, decimalPlaces);
				if(decimalPlaces!=0){
					str = str+"."+tempStr;
				}
			}
		}
		return NssStringUtils.getMoneyForm(str);
	}

	/**
	 * 지수를 정수로 변환..
	 * 계산결과 소수점 이하 버림
	 * @return java.lang.String
	 */
	public static String toDecimal(double dbValue) {
		try {
			java.text.DecimalFormat dF = new java.text.DecimalFormat("0");
			return dF.format(dbValue);
		} catch (Exception e) {
			return String.valueOf(dbValue);
		}
	}

	/**
	 * 지수를 정수로 변환..
	 * 계산결과 소수점 이하 버림
	 * @return java.lang.String
	 */
	public static String toDecimal(double dbValue, int nCount) {
		try {
			StringBuffer sbTemp = new StringBuffer();
			if (nCount > 0) {
				sbTemp.append(".");
				for (int i = 0; i < nCount; i++) {
					sbTemp.append("#");
				}
			} else {
				sbTemp.append("0");
			}
			java.text.DecimalFormat dF = new java.text.DecimalFormat(sbTemp.toString());
			return dF.format(dbValue);
		} catch (Exception e) {
			return String.valueOf(dbValue);
		}
	}

	/**
	 * 지수를 정수로 변환..
	 * 계산결과 소수점 이하 버림
	 * @return java.lang.String
	 */
	public static String toDecimal(String strValue) {
		try {
			return NssStringUtils.toDecimal(Double.valueOf(strValue).doubleValue());
		} catch (Exception e) {
			return strValue;
		}
	}

	/**
	 * 소수점이하자리수 채워주기
	 * 소수점이하 cnt만큼 0을 채운다
	 * 작성 날짜: (2002-01-09 오후 5:19:16)
	 * 최종수정일 : 2002-02-18
	 * 취종수정내용 : String ---> StringBuffer로
	 * @return java.lang.String
	 */
	public static String getRateForm(String str, int cnt) {
		if ((str == null) || str.trim().equals("")) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(str);

		if (str.indexOf(".") < 0) {
			//소수점이 없으면
			sb.append(".");
			while (cnt > 0) {
				sb.append("0");
				cnt--;
			}
		} else {
			//소수점이 있으면
			while (cnt >= (str.length() - str.indexOf("."))) {
				sb.append("0");
				cnt--;
			}

		}
		return sb.toString();
	}

	/** 계좌번호의 체계에 따라 특정위치에 '-'를 첨가한다. */
	public static String accountNo(String str) {
		str = NssStringUtils.null2void(str); //null 처리 및 trim

		String temp = null;
		int len = str.length();

		// 15자리 계좌의 경우 예금과 대출을 구분할 필요 있슴
		// 하지만 현재로선 구분할 방법이 없다.
		switch (len) {
		// 예금 3-2-6-4 (점-과목-고객번호-일련번호)
		// 구) 상업, 적립식 거치식
		// 대출 3-6-3-3 (점-고객번호-과목-일련번호)
		// 현) 우리은행 여신

		/* 2004-8-3 Edit by Hjun */
		/* 대출 계좌의 경우 15자리의 구분이 어렵기 때문에 구분(-) 삭제 함 */
		/*
		 * case 15 : temp = str.substring(0, 3) + "-" + str.substring(3, 9) + "-" + str.substring(9, 12) + "-" + str.substring(12, 15); //temp = str.substring(0,3) + "-" // + str.substring(3,5) + "-" // + str.substring(5,11) + "-" // + str.substring(11,15); break; // 3-6-2-3 (점-고객번호-과목-일련번호) // 구) 한일, 현)우리은행 수신
		 */

		case 14:
			temp = str.substring(0, 3) + "-" + str.substring(3, 9) + "-" + str.substring(9, 11) + "-" + str.substring(11, 14);
			break;
		// 3-2-4-3 (점-과목-일련번호1-일련번호2)
		// 구) 평화
		case 13:
			temp = str.substring(0, 4) + "-" + str.substring(4, 7) + "-" + str.substring(7, 13);
			break;
		// 4-3-6
		// NBS 신계좌번호 2004-07-30 김진아추가
		case 12:
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 9) + "-" + str.substring(9, 12);
			break;
		// 3-2-6 (점-과목-계좌번호)
		// 구) 상업, 입출식
		case 11:
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 11);
			break;
		// 기타 계좌 자리수가 우리은행 포맷에 맞지 않는 경우
		default:
			temp = str;
			break;
		}
		return temp;
	}

	/**
	 * 우리은행 계좌번호를 만듭니다.<br>
	 * 00000000000000  ---> 000-000000-00-000<br>
	 *
	 * 작성 날짜: (2001-11-09 오후 1:02:12)
	 * @return java.lang.String
	 */
	public static String getAcctForm(String acct) {

		return NssStringUtils.accountNo(acct);
	}

	/**
	 * 당행계좌번호 표시방법변경<br>
	 * 00000000000000  ---> 000-000000-00-000<br>
	 *
	 * 작성 날짜: (2010-10-21)
	 * @return java.lang.String
	 */
	public static String getAcctForm(String bkcd, String acct) {

		bkcd = NssStringUtils.null2void(bkcd); //null 처리 및 trim

		if (!(bkcd.equals("020") || bkcd.equals("우리은행"))) {
			return acct;
		} else {
			return NssStringUtils.accountNo(acct);
		}
	}

	/**
	 * 카드번호를 만듭니다.<br>
	 * 000000000000000  ---> 0000-0000-0000-0000<br>
	 *
	 * 작성 날짜: (2001-11-09 오후 1:02:12)
	 * @return java.lang.String
	 */
	public static String getCardForm(String card) {
		return NssStringUtils.seccardNo(card);
	}

	/**
	 * String str에 int offset의 위치에 "-"를 삽입한다.<br>
	 * 예) <br>
	 * putDash("1234567", 3) 요렇게 하면<br>
	 * 123-4567 요렇게 리턴<br>
	 * 작성 날짜: (2001-11-09 오전 11:54:07)
	 * @return java.lang.String
	 */
	public static String putDash(String str, int offset) {
		if (str == null) {
			return "";
		}
		if ((str.length() < offset) || (offset <= 0)) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (i == offset) {
				sb.append("-");
			}
			if (c != ' ') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * str에다가 offset1번째자리에 '-'를 삽입하고<br>
	 * 다음 cnt2번째 자리에 '-'삽입..<br>
	 *
	 * 예)
	 * putDash("1234567890", 4,  3) 이래하면
	 * "1234-567-89" 와 같이 리턴..
	 * 작성 날짜: (2001-11-09 오전 11:54:07)
	 * @return java.lang.String
	 */
	public static String putDash(String str, int offset1, int cnt2) {
		if (str == null) {
			return "";
		}
		int offset2 = offset1 + cnt2;
		if ((str.length() < offset2) || (offset2 <= 0)) {
			return str;
		}

		StringBuffer sb = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if ((i == offset1) || (i == offset2)) {
				sb.append("-");
			}
			if (c != ' ') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * '-' 3개를 지정한 자리에 삽입 <br>
	 * 작성 날짜: (2001-11-09 오전 11:54:07)
	 * @return java.lang.String
	 */
	public static String putDash(String str, int offset1, int cnt2, int cnt3) {
		if (str == null) {
			return "";
		}
		int offset2 = offset1 + cnt2;
		int offset3 = offset2 + cnt3;

		if ((str.length() < offset3) || (offset3 <= 0)) {
			return str;
		}

		StringBuffer sb = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if ((i == offset1) || (i == offset2) || (i == offset3)) {
				sb.append("-");
			}
			if (c != ' ') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 문자열의 공백 제거
	 * @param  java.lang.String
	 * @return trimed string with white space removed
	 *         from the front and end.
	 */
	public static String trim(String s) {

		if (s == null) {
			return "";
		}
		int st = 0;
		char[] val = s.toCharArray();
		int count = val.length;
		int len = count;

		while ((st < len) && ((val[st] <= ' ') || (val[st] == '\u3000'))) {
			st++;
		}
		while ((st < len) && ((val[len - 1] <= ' ') || (val[len - 1] == '\u3000'))) {
			len--;
		}

		return ((st > 0) || (len < count)) ? s.substring(st, len) : s;
	}

	/**
	 * 문자열의 왼쪽 공백 제거
	 * @param  java.lang.String
	 * @return trimed string with white space removed
	 *         from the front and end.
	 */
	public static String lTrim(String s) {
		if (s == null) {
			return "";
		}
		int st = 0;
		char[] val = s.toCharArray();
		int count = val.length;
		int len = count;

		while ((st < len) && ((val[st] <= ' ') || (val[st] == '\u3000'))) {
			st++;
		}

		return ((st > 0) || (len < count)) ? s.substring(st, len) : s;
	}

	/**
	 * 문자열의 오른쪽 공백 제거
	 * @param  java.lang.String
	 * @return trimed string with white space removed
	 *         from the front and end.
	 */
	public static String rTrim(String s) {
		if (s == null) {
			return "";
		}
		int st = 0;
		char[] val = s.toCharArray();
		int count = val.length;
		int len = count;

		while ((st < len) && ((val[len - 1] <= ' ') || (val[len - 1] == '\u3000'))) {
			len--;
		}

		return ((st > 0) || (len < count)) ? s.substring(st, len) : s;
	}

	/** 비씨카드번호에 '-'를 첨가한다.(일부숫자 '*'로 출력하게 변경) */
	public static String seccardNo(String str) {

		String temp = null;
		int len = str.length();

		if (len != 16) {
			return str;
		}

		temp = str.substring(0, 4) + "-" + "****" + "-" + "****" + "-" + str.substring(12, 16);

		return temp;
	}

	/** 주민번호 만들기  */
	public static String makeJuminNO(String str) {
		String temp = null;
		int len = (str.trim()).length();

		if (len != 13) {
			return str;
		}
		temp = str.substring(0, 6) + "-" + str.substring(6, 13);

		return temp;
	}

	public static String makerLoanRate(String str, int fLen, int tLen) {

		String preRate = "";
		String backRate = "";
		String loanRate = "";

		preRate = str.substring(0, str.length() - tLen);
		backRate = str.substring(str.length() - tLen);

		loanRate = preRate + "." + backRate;

		return NssStringUtils.rmZero(loanRate);
	}

	public static String makerLoanRate1(String str) {

		String preRate = "";
		String backRate = "";
		String loanRate = "";

		int offset = str.indexOf(".");
		if (offset > 0) {
			// 소수점이 있다
			preRate = str.substring(0, offset);
			backRate = str.substring(offset, offset + 3);
			loanRate = preRate + backRate;
		} else {
			// 전문마다 자리수가 다르므로 화면에서 처리하거나 makerLoanRate의 방식으로 사용하여야 한다.
			loanRate = str;
		}
		return loanRate;
	}

	/**
	 * 90146903 금액 원화표기 소수점없이
	 * @param str
	 * @return
	 */
	public static String getKRWMoney(String str) {

		if ((str == null) || "".equals(str)) {
			return str;
		}

		int offset = str.indexOf(".");
		if (offset > 0) {
			//소수점이 있으면
			str = str.substring(0, offset);
		}

		return str;
	}

	/** 년월일 사이에 '.'를 첨가한다. */
	public static String date(String str) {

		String temp = null;
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		str = str.trim();
		int len = str.length();

		if (len != 8) {
			return str;
		}
		if ((str.equals("00000000")) || (str.equals("00010101")) || (str.equals("       0"))) {
			return "";
		}
		temp = str.substring(0, 4) + "." + str.substring(4, 6) + "." + str.substring(6, 8);

		return temp;
	}

	/** 년월일 사이에 '-'를 첨가한다. */
	public static String dateDash(String str) {
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		String temp = null;
		int len = str.length();

		if (len != 8) {
			return str;
		}
		if ((str.equals("00000000")) || (str.equals("       0")) || (str.equals("00010101"))) {
			return "";
		}
		temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);

		return temp;
	}

	/** 년월일 사이에 '.'를 첨가한다. */
	public static String dateCom(String str) {
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		String temp = null;
		int len = str.length();

		if (len != 8) {
			return str;
		}
		if ((str.equals("00000000")) || (str.equals("       0")) || (str.equals("00010101"))) {
			return "";
		}
		temp = str.substring(0, 4) + "." + str.substring(4, 6) + "." + str.substring(6, 8);

		return temp;
	}

	/** 년월 한글로 표시한다 */
	public static String dateHanYM(String str) {

		String temp = null;
		int len = str.length();

		if (len != 6) {
			return str;
		}
		if ((str.equals("000000")) || (str.equals("     0"))) {
			return "";
		}
		temp = str.substring(0, 4) + "년 " + Integer.parseInt(str.substring(4, 6)) + "월";

		return temp;
	}

	/** 년월일시분 사이에 '-', ':'를 첨가한다.
	 * 	ex) "20171010 2222" -> "2017-10-10 22:22"
	 * "20171010141850" -> "2017-10-10 14:18"
	 *  */
	public static String dateTimeForm(String str) {
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		String temp = null;
		int len = str.length();

		if (len == 13) {
			temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(9, 11) + ":" + str.substring(11, 13);
		} else if(len > 13){
			temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12);
		} else {
			temp = str;
		}

		return temp;
	}

	/**
	 * 오른쪽에서 해당 길이만큼 자른다..`
	 * Add by Hjun 2004-09-06
	 */
	public static String Right(String str, int len) {

		if ((str == null) || "".equals(str)) {
			return "";
		}
		return StringUtils.right(str, len);
//		byte[] bStr = str.getBytes();
//		int iCount = bStr.length;
//		if (len >= iCount) {
//			return str;
//		}
//		return new String(bStr, iCount - len, len);
	}

	/**
	 * 왼쪽에서 해당 길이만큼 자른다..
	 * Add by Hjun 2004-09-06
	 */
	public static String Left(String str, int len) {
		if ((str == null) || "".equals(str)) {
			return "";
		}
		return StringUtils.left(str, len);
//		byte[] bStr = str.getBytes();
//		int iCount = bStr.length;
//		if (len >= iCount) {
//			return str;
//		}
//		return new String(bStr, 0, len);
	}

	/** 가변적인 String을 고정된 length의 String으로 변환한다. */
	/** 입력된 String이 고정된 길이보다 작을 경우 space를 추가한다. */
	/** 구분 '0':left align '1':right align */
	/**  수정 2002.04.18  **/
	public static String fixlength(int kind, int out_len, String str, char fillCh) {
		byte[] temp = new byte[out_len];
		int i;
		for (i = 0; i < out_len; i++) {
			//temp[i] = Byte.parseByte(fillCh)   ;
			temp[i] = (byte) fillCh;
		} //out_len 만큼 temp에 space를 채운다.

		if (!"".equals(NssStringUtils.null2void(str))) {
			byte[] input = str.getBytes();
			int j;
			int in_len = input.length;

			// 입력된 길이보다 해당 String이 긴 경우
			if (in_len > out_len) {
				in_len = out_len;
			}

			if (kind == 1) {
				for (i = (out_len - in_len), j = 0; i < out_len; i++, j++) {
					temp[i] = input[j];
				}
			} else {
				for (i = 0; i < in_len; i++) {
					temp[i] = input[i];
				}
			}
		}

		String output = new String(temp, 0, out_len);

		return output;
	}

	/** 가변적인 String을 고정된 length의 String으로 변환한다. */
	/** 입력된 String이 고정된 길이보다 작을 경우 space를 추가한다. */
	/** 구분 '0':left align '1':right align */
	public static String fixlength(int kind, int out_len, String str) {
		String temp_str = new String("                                                                          ");

		if ("".equals(NssStringUtils.null2void(str))) {
			String temp_str1 = temp_str.substring(0, out_len);
			return temp_str1;
		} else {
			byte[] input = str.getBytes();
			byte[] temp = new byte[out_len];

			int i, j;
			int in_len = input.length;

			for (i = 0; i < out_len; i++) {
				temp[i] = (byte) ' ';
			}

			// 입력된 길이보다 해당 String이 긴 경우
			if (in_len > out_len) {
				in_len = out_len;
			}

			if (kind == 1) {
				for (i = (out_len - in_len), j = 0; i < out_len; i++, j++) {
					temp[i] = input[j];
				}
			} else {
				for (i = 0; i < in_len; i++) {
					temp[i] = input[i];
				}
			}

			String output = new String(temp, 0, out_len);

			return output;
		}
	}

	public static String makeNumber(String str, int len) {

		str = str.trim();
		int numLen = str.length();
		String strTmp = "";
		for (int i = numLen; i < len; i++) {
			strTmp += "0";
		}
		return strTmp + str;
	}

	/** 하나의 긴 String을 주어진 integer array의 순서대로 tokenize */
	public static String[] parsing(int[] delim, String str) {

		int i, offset = 0;

		// 한글문제로 인하여 byte로 변환한 후 처리
		byte[] input = str.getBytes();
		String[] output = new String[delim.length];
		String temp;

		for (i = 0; i < delim.length; i++) {
			temp = new String(input, offset, delim[i]);
			output[i] = temp;
			offset += delim[i];
		}
		return output;
	}

	/** 하나의 String(str)을 주어진 분리문자(tok) 기준으로 tokenize   ***/
	public static String[] parsing2(String str, int len, String tok) {

		if (str == null) {
			return new String[0];
		}

		String[] output = new String[len];
		StringTokenizer st = new StringTokenizer(str, tok);
		int i = 0;

		while (st.hasMoreTokens()) {
			output[i++] = st.nextToken();
		}
		return output;
	}

	/** 하나의 String(str)을 주어진 분리문자(tok) 기준으로 tokenize   ***/
	public static String[] parsing3(String str, String tok) {

		if (str == null) {
			return new String[0];
		}

		String[] output = null;
		Vector vResult = new Vector();
		StringTokenizer st = new StringTokenizer(str, tok);

		while (st.hasMoreTokens()) {
			vResult.add(st.nextToken());
		}

		output = new String[vResult.size()];
		for (int i = 0; i < vResult.size(); i++) {
			output[i] = vResult.elementAt(i).toString();
		}

		return output;
	}

	/** 하나의 String(str)을 주어진 분리문자(tok) 기준으로 tokenize   ***/
	public static String[] parsing4(String str, String tok) {

		if (str == null) {
			return new String[0];
		}

		String[] output = null;
		Vector vResult = new Vector();
		Vector vTemp = new Vector();
		StringTokenizer stTemp = new StringTokenizer(str, tok, true);
		StringTokenizer st = new StringTokenizer(str, tok, true);

		while (stTemp.hasMoreTokens()) {
			String s = stTemp.nextToken();
			vTemp.add(s);
		}

		int iCnt = 0;

		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			iCnt++;

			if (s.equals(tok)) {
				if ((iCnt > 1) && vTemp.elementAt(iCnt - 2).toString().equals(tok)) {
					vResult.add("");
				} else if (iCnt == 1) {
					vResult.add("");
				}
				continue;
			}
			vResult.add(s);
		}

		output = new String[vResult.size()];
		for (int i = 0; i < vResult.size(); i++) {
			output[i] = vResult.elementAt(i).toString();
		}

		return output;
	}

	/** number formating, 숫자에 ','를 첨가한다. */
	// 실제 double 을 문자열로 바꾼다..
	// by Hjun  2000.10.18
	public static String numberDouble(double d) {

		String temp = null;

		if (d == 0) {
			temp = "0";
		} else {
			DecimalFormat decimal = new DecimalFormat("###,###,###.###");
			temp = decimal.format(d);
		}

		return temp;
	}

	/** number formating, 숫자에 ','를 첨가한다. */
	// 실제 double 을 문자열로 바꾼다..
	// by Hjun  2000.10.18
	public static String numberDouble2(double d, String s) {

		String temp = null;

		if (d == 0) {
			temp = "0.000";
		} else {
			if ("1".equals(s)) {
				DecimalFormat decimal = new DecimalFormat("###,###,##0.000");
				temp = decimal.format(d);
			} else {
				DecimalFormat decimal = new DecimalFormat("###,###,###.###");
				temp = decimal.format(d);
			}

		}

		return temp;
	}

	/** number formating, 숫자에 ','를 첨가한다. */
	public static String number(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###.###");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 2자리 */
	public static String number1(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,##0.00");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 4자리 */
	public static String number2(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,##0.0000");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 2자리 */
	public static String number5_2(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("##,##0.00");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 2자리 */
	public static String number2_2(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("#0.00");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 3자리 */
	public static String number12_3(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###,##0.000");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 2자리 */
	public static String number12_2(String str) {
		String temp = null;
		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###,##0.00");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 3자리 */
	public static String number3(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,##0.000");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** number formating, 소숫점이하 5 자리 */
	public static String number5(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,##0.00000");
			temp = decimal.format(change);
		}
		return temp;
	}

	/**
	 * 원하는 길이의 alphaNumeric String을 만드는 메소드<br>
	 * getANstring("asdf",6) --> asdf  을 반환(asdf뒤에 공백두칸 추가 ^^;;)<br>
	 * 작성 날짜: (2001-11-16 오후 8:31:40)
	 * @return java.lang.String
	 */
	public static String getANstring(String str, int length) {
		if (str == null) {
			return "";
		}
		for (int i = length - str.getBytes().length; i > 0; --i) {
			str += " ";
		}
		return str;
	}

	/**
	 * 원하는 길이의 Numeric String을 만드는 메소드 tag 용<br>
	 * getNstring(12345, 7) ---> "0012345" 를 반환다.
	 * 작성 날짜: (2001-11-16 오후 8:31:40)
	 * @return java.lang.String
	 */
	public static String getNstringToTag(Integer intstr, Integer length) {
		String str = Integer.toString(intstr);
		for (int i = length - str.length(); i > 0; --i) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 원하는 길이의 Numeric String을 만드는 메소드<br>
	 * getNstring(12345, 7) ---> "0012345" 를 반환다.
	 * 작성 날짜: (2001-11-16 오후 8:31:40)
	 * @return java.lang.String
	 */
	public static String getNstring(int intstr, int length) {
		String str = Integer.toString(intstr);
		for (int i = length - str.length(); i > 0; --i) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 원하는 길이의 Numeric String을 만드는 메소드<br/>
	 * 3번째 파라미터는 채워넣을 패딩문자의 방향 (1 왼쪽, -1 오른쪽)
	 * getNstring(12345, 7, 1) -> "0012345" <br/>
	 * getNstring(12345, 7, -1) -> "1234500" <br/>
	 * 작성 날짜: (2001-11-16 오후 8:31:40)
	 * @return 숫자형태의 문자열
	 */
	public static String getNstring(int intstr, int length, int direction) {
		String str = Integer.toString(intstr);
		for (int i = length - str.length(); i > 0; --i) {
			str = (direction > 0) ? "0" + str : str + "0";
		}
		return str;
	}

	/**
	 * 계좌번호의 체계에 따라  '-'를 삭제한다
	 *
	 * @param   str   표현하고자 하는 계좌번호
	 * @return   계좌번호
	 */
	public static String delDashAccNo(String str) {

		String temp = null;
		str = str.trim();
		int len = str.length();

		// 현재 구상업과 구한일의 15자리 계좌번호 구분방법이 없음
		switch (len) {
		case 17:
			temp = str.substring(0, 3) + str.substring(4, 10) + str.substring(11, 13) + str.substring(14, 17);
			break;
		case 13:
			temp = str.substring(0, 3) + str.substring(4, 6) + str.substring(7, 13);
			break;

		default:
			temp = str;
			break;
		}
		return temp;
	}

	/** number formating, 숫자에 ','를 첨가한다. */
	public static String addComma(String str) {

		String temp = null;

		if (str == null) {
			temp = "0";
		} else {
			double change = Double.valueOf(str.trim()).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###,###");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** 환율 number formating, 숫자에 ','를 첨가한다. */
	public static String eRateFormat(String str) {

		String temp = null;

		if (str == null) {
			temp = "0";
		} else {
			double change = Double.valueOf(str.trim()).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###.##");
			temp = decimal.format(change);
		}

		return temp;
	}

	/**  ','를 삭제한다. */
	public static String delComma(String str) {

		if (str == null) {
			return "";
		}

		StringBuffer dest = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			switch (c) {
			case ',':
				break;
			default:
				dest.append((char) c);
				break;
			}
		}
		return dest.toString();
	}

	/**  '-'를 삭제한다. */
	public static String delDash(String s) {
		if (s == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '-') {
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**  ':'를 삭제한다. */
	public static String delColon(String s) {
		if (s == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ':') {
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**  문자열에서 '.'를 삭제한다. (예 : yyyy.mm.dd 에서 ) */
	public static String delDot(String str) {

		if (str == null) {
			return "";
		}

		StringBuffer dest = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			switch (c) {
			case '.':
				break;
			default:
				dest.append((char) c);
				break;
			}
		}
		return dest.toString();
	}

	/**  문자열에서 ' '(space)를 삭제한다.  (추가 2002.06.03)*/
	public static String delSpace(String s) {
		if (s == null) {
			return "";
		}
		return StringUtils.deleteWhitespace(s);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < s.length(); i++) {
//			char c = s.charAt(i);
//			if (c == ' ') {
//				continue;
//			}
//			sb.append(c);
//		}
//		return sb.toString();
	}

	/**  문자열에서 '-','',':'를 삭제한다. (예 : yyyy-mm-dd xx:xx:xx 에서 ) */
	public static String delChar(String str) {

		if (str == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '-') {
				continue;
			}
			if (c == ' ') {
				continue;
			}
			if (c == ':') {
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 *  String 중에 '\n'값을 '&ltbr&gt'로 변경한다.
	 * @param String str
	 * @return '\n'값이 '&ltbr&gt'로 변경된 문자열
	 */
	public static String enterToBr(String str) {
		String ret = "";

		try {
			StringTokenizer st = new StringTokenizer(str, "\n");
			while (st.hasMoreTokens()) {
				ret += st.nextToken() + "<br>";
			}
			return ret;
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 공백이나 "-" 없애기
	 * @param String param
	 * @return 공백이나 "-"가 제거된 문자열
	 */
	public static String mTrim(String param) {

		String tParam = NssStringUtils.null2void(param);

		for (int i = 0; i < tParam.length();) {
			if (tParam.substring(i, i + 1).equals(" ") || tParam.substring(i, i + 1).equals("-")) {
				tParam = tParam.substring(0, i) + tParam.substring(i + 1, tParam.length());
			} else {
				i++;
			}
		}

		return tParam;
	}

	/**
	 * @param ccy : 통화코드 예) USD, KRW...
	 * @param amount : 금액  예) 000001000 = > 1000.000
	 * @author 김복주 대리, 2004-09-08
	 */
	public static String getMoneyFormByCCY(String ccy, String amount) {
		String money = "";

		if ((ccy == null) || "".equals(ccy)) {
			money = NssStringUtils.getMoneyForm(amount, "2");

		} else if ("KRW".equals(ccy) || "JPY".equals(ccy)) {
			money = NssStringUtils.getMoneyForm(amount, "2");

		} else {
			money = NssStringUtils.numberDotFormat("2", NssStringUtils.removeZero(amount, 1));
		}

		return money;
	}

	/**
	 * @param ccy : 통화코드 예) USD, KRW...
	 * @param amount : 금액  예) 000001000
	 * @author 전수민
	 */
	public static String getMoneyFormByCCY2(String ccy, String amount) {
		String money = "";

		if ((ccy == null) || "".equals(ccy)) {
			money = NssStringUtils.getMoneyForm(amount, "4");

		} else if ("KRW".equals(ccy) || "JPY".equals(ccy)) {
			money = NssStringUtils.getMoneyForm(amount, "4");

		} else {
			money = NssStringUtils.getMoneyForm(NssStringUtils.removeZero(amount, 1));
		}

		return money;
	}

	/**
	 * 전화번호 분리
	 * @param String v_telno
	 * @param String v_flag
	 * @return flag별로 분리된 전화번호 문자열
	 *
	 * flag가 1 이면 지역번호를 리턴하고,
	 * flag가 2 이면 국번을 리턴하고,
	 * flag가 3 이면 전화번호를 리턴하고,
	 * flag가 4 이면 전체번호를 리턴하고,
	 * flag가 5 이면 내선번호를 리턴한다.
	 *
	 * 내선번호의 경우
	 * 현 DB구조상 내선번호를 구별할 방법이 없으므로,
	 * 2자리 혹은 4자일 경우만 정상값이 리턴되고, 3자리일 경우
	 * 국번과 전화번호, 내선번호가 모두 비정상 리턴될 수 있다.
	 */
	public static String getTelSep(String v_telno, int v_flag) {

		String telno = ""; // 인자로 받은 전체 전화번호
		String d_telno = ""; // 지역번호
		String t_telno = ""; // 지역번호를 제외한 번호
		String r_telno = ""; // 리턴할 번호
		String telno1 = ""; // 지역번호
		String telno2 = ""; // 국번호
		String telno3 = ""; // 전화번호(4자리)
		String telno4 = ""; // 내선번호

		if (v_telno != null) {
			if (v_telno.length() < 9) {
				return v_telno;
			}

			telno = NssStringUtils.mTrim(v_telno);

			d_telno = telno.substring(0, 2);

			if (d_telno.equals("02")) {
				telno1 = telno.substring(0, 2);
				t_telno = telno.substring(2);
			} else {
				telno1 = telno.substring(0, 3);
				t_telno = telno.substring(3);
			}

			// 내선번호가 없을 때
			if ((t_telno.length() == 7) || (t_telno.length() == 8)) {
				telno2 = t_telno.substring(0, t_telno.length() - 4);
				telno3 = t_telno.substring(t_telno.length() - 4);
				// 내선번호가 2자리일 때
			} else if ((t_telno.length() == 9) || (t_telno.length() == 10)) {
				telno2 = t_telno.substring(0, t_telno.length() - 6);
				telno3 = t_telno.substring(t_telno.length() - 6, t_telno.length() - 2);
				telno4 = t_telno.substring(t_telno.length() - 2);
				// 내선번호가 4자리일 때
			} else if ((t_telno.length() == 11) || (t_telno.length() == 12)) {
				telno2 = t_telno.substring(0, t_telno.length() - 8);
				telno3 = t_telno.substring(t_telno.length() - 8, t_telno.length() - 4);
				telno4 = t_telno.substring(t_telno.length() - 4);
			} else {
				r_telno = telno;
			}

			if (v_flag == 1) {
				r_telno = telno1; //앞자리

			} else if (v_flag == 2) {
				r_telno = telno2; //중간자리

			} else if (v_flag == 3) {
				r_telno = telno3; //마지막자리

			} else if (v_flag == 4) {
				r_telno = telno1 + "-" + telno2 + "-" + telno3 + telno4; //전체

			} else if (v_flag == 5) {
				r_telno = telno4;

			}
			/*
			 * 2005-12-13 add by Daemot 금융감독원 지시사항 관련 - 전화번호 뒷자리 masking
			 */
			else if (v_flag == 6) {
				r_telno = telno1 + "-" + telno2 + "-";
				for (int i = 0; i < (telno3 + telno4).length(); i++) {
					r_telno = r_telno + "*";
				}
			}
		}

		return r_telno;
	}

	public static String getTelSepToTag(String v_telno, Integer v_flag) {

		String telno = ""; // 인자로 받은 전체 전화번호
		String d_telno = ""; // 지역번호
		String t_telno = ""; // 지역번호를 제외한 번호
		String r_telno = ""; // 리턴할 번호
		String telno1 = ""; // 지역번호
		String telno2 = ""; // 국번호
		String telno3 = ""; // 전화번호(4자리)
		String telno4 = ""; // 내선번호

		if (v_telno != null) {
			if (v_telno.length() < 9) {
				return v_telno;
			}

			telno = NssStringUtils.mTrim(v_telno);

			d_telno = telno.substring(0, 2);

			if (d_telno.equals("02")) {
				telno1 = telno.substring(0, 2);
				t_telno = telno.substring(2);
			} else {
				telno1 = telno.substring(0, 3);
				t_telno = telno.substring(3);
			}

			// 내선번호가 없을 때
			if ((t_telno.length() == 7) || (t_telno.length() == 8)) {
				telno2 = t_telno.substring(0, t_telno.length() - 4);
				telno3 = t_telno.substring(t_telno.length() - 4);
				// 내선번호가 2자리일 때
			} else if ((t_telno.length() == 9) || (t_telno.length() == 10)) {
				telno2 = t_telno.substring(0, t_telno.length() - 6);
				telno3 = t_telno.substring(t_telno.length() - 6, t_telno.length() - 2);
				telno4 = t_telno.substring(t_telno.length() - 2);
				// 내선번호가 4자리일 때
			} else if ((t_telno.length() == 11) || (t_telno.length() == 12)) {
				telno2 = t_telno.substring(0, t_telno.length() - 8);
				telno3 = t_telno.substring(t_telno.length() - 8, t_telno.length() - 4);
				telno4 = t_telno.substring(t_telno.length() - 4);
			} else {
				r_telno = telno;
			}

			if (v_flag == 1) {
				r_telno = telno1; //앞자리

			} else if (v_flag == 2) {
				r_telno = telno2; //중간자리

			} else if (v_flag == 3) {
				r_telno = telno3; //마지막자리

			} else if (v_flag == 4) {
				r_telno = telno1 + "-" + telno2 + "-" + telno3 + telno4; //전체

			} else if (v_flag == 5) {
				r_telno = telno4;

			}
			/*
			 * 2005-12-13 add by Daemot 금융감독원 지시사항 관련 - 전화번호 뒷자리 masking
			 */
			else if (v_flag == 6) {
				r_telno = telno1 + "-" + telno2 + "-";
				for (int i = 0; i < (telno3 + telno4).length(); i++) {
					r_telno = r_telno + "*";
				}
			}
		}

		return r_telno;
	}


	/**
	 * 문자열 앞부분이 '0'으로 채워져 있는 경우<br>
	 * 문자열의 길이를 유지한 채 '0'을 ' '으로 취환하여 리턴한다.
	 *
	 * @param str '0'으로 채워진 문자열
	 * @return '0'을 ' '으로 바꿔서 길이를 맞춘 문자열
	 */
	public static String getDigitFixLen(String str) {
		int iLength = str.length();
		String sResult = NssStringUtils.rmZero(str);

		return NssStringUtils.fixlength(1, iLength, sResult);
	}

	public static boolean isDigit(String input) {
		if ((input == null) || input.equals("")) {
			return false;
		}
		input.getBytes();
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (!Character.isDigit(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 문자 취환
	 * Returns a String with all occurrences of the String from
	 * replaced by the String to.
	 *
	 * @param in 작업할 문자를 포함하는 문자열
	 * @param from 찾는 문자열
	 * @param to 취환할 문자열
	 * @return The new String
	 */
	public static String replace(String in, String from, String to) {

		/* from vs to 치환시 양쪽 값이 같은경우 문제 발생 하여 예외처리 */
		if (from.equals(to)) {
			return in;
		}

		StringBuffer sb = new StringBuffer(in.length() * 2);
		String posString = in.toLowerCase();
		String cmpString = from.toLowerCase();
		int i = 0;
		boolean done = false;
		while ((i < in.length()) && !done) {
			int start = posString.indexOf(cmpString, i);
			if (start == -1) {
				done = true;
			} else {
				sb.append(in.substring(i, start) + to);
				i = start + from.length();
			}
		}
		if (i < in.length()) {
			sb.append(in.substring(i));
		}
		return sb.toString();
	}

	/**
	 * 게시판용 문자 취환
	 * Returns a String with all occurrences of the String sTagString
	 * replaced by the String for HTML.
	 *
	 * @param sTagString 작업할 문자를 포함하는 문자열
	 * @return The new String HTML에서 보여질 문자열
	 */
	public static String replaceStr(String sTagString) {

		String sTextString = null;

		sTextString = NssStringUtils.replace(sTagString, "\"", "&quot;");
		sTextString = NssStringUtils.replace(sTextString, "& ", "&amp; ");
		sTextString = NssStringUtils.replace(sTextString, "<", "&lt;");
		sTextString = NssStringUtils.replace(sTextString, ">", "&gt;");
		sTextString = NssStringUtils.replace(sTextString, "\n", "<br>\n");
		//sTextString = replace(sTextString, "'","&#x27");
		return sTextString;
	}

	/**
	 * 문자열 중간의 간격을 공백한칸으로 유지
	 * @param txt
	 * @return 공백이 한칸으로 유지된 문자열
	 */
	public static String trimMid(String txt) {
		int p = 0;
		int txtLen = txt.length();
		for (int i=0; i<txtLen; i++) {
			p = txt.indexOf(" ");
			if (p < 1) {
				return txt.trim();
			}
			txt = new StringBuffer().append(txt.substring(0, p)).append(txt.substring(p + 1)).toString();
		}
		return txt.trim();
	}

	/**
	 * 문자열 중간의 간격을 공백한칸으로 유지
	 * @param txt
	 * @return 공백이 한칸으로 유지된 문자열
	 */
	public static String trimMidAndToHalfChar(String txt) {
		txt = NssStringUtils.null2void(txt);

		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(txt);

		while (st.hasMoreTokens()) {
			sb.append(st.nextToken()).append(" ");
		}

		return NssStringUtils.toHalfChar(sb.toString()).trim();
	}

	/**
	 * 년월일 한글로 표시
	 * @param str 년월일(8자리) 혹은 년월일시분초(14자리)
	 * @return 한글 형식으로 표현된 년월일(시분초)
	 */
	public static String dateHan(String str) {

		int len = str.length();

		StringBuffer sbTemp = new StringBuffer();

		if ((len == 8) || (len == 14)) {
			if ((str.equals("00000000")) || (str.equals("       0"))) {
				return "";
			}

			sbTemp.append(str.substring(0, 4)).append("년 ").append(Integer.parseInt(str.substring(4, 6))).append("월 ").append(Integer.parseInt(str.substring(6, 8))).append("일 ");

			if (len == 14) {
				sbTemp.append(str.substring(8, 10)).append("시 ").append(Integer.parseInt(str.substring(10, 12))).append("분 ").append(Integer.parseInt(str.substring(12, 14)))
						.append("초 ");
			}

			return sbTemp.toString();
		} else {
			return str;
		}
	}

	/**
	 * 2004.8.4 add by Hjun
	 * 18 자리 자리수를 szCut 자리로 바꾼다.
	 * #NUM(13) 원래 소스 데이터 (str)을 13로 만든다 (왼쪽부터 시작해서 오른쪽 까지 자른다)
	 */
	public static String numberCut(String szCut, String str) {
		String szResult = NssStringUtils.null2ZeroStr(str);
		int iLen = 0;

		try {
			iLen = NssStringUtils.null2zero(szCut);
		} catch (NumberFormatException ne) {
			return szResult;
		}

		if ((iLen > 0) && (iLen < szResult.length())) {
			szResult = szResult.substring(0, iLen);
		}

		return szResult;
	}

	/**
	 * 2004.7.13 add by Hjun
	 * 18 자리 자리수를 15자리로 바꾼다.
	 * 소수점 3자리를 자른다.
	 */
	public static String number15(String str) {

		//format이 안되어 있는 경우 - plain72
		String temp = NssStringUtils.null2ZeroStr(str);

		if (temp.length() > 3) { // null2ZeroStr를 거친경우 소숫점이 없는 0이 생기기 때문

			if (temp.indexOf(".") > -1) {
				//소수점과 금액이 함께 오는 경우 ex : 123456789.000 > 123456789 로 변환
				temp = temp.substring(0, temp.indexOf("."));
			} else {
				temp = temp.substring(0, (temp.length() - 3));
			}

		} else if (temp.length() == 3) { //소숫점 이하자리만 존재하는 string 의 경우
			temp = "0";
		}

		return temp;
	}

	/**
	 * 2017-09-05
	 * 소수점 이하는 삭제한다.
	 */
	public static String delDecimalPoint(String str) {
		String temp = NssStringUtils.null2ZeroStr(str);
		BigInteger returnData = null;
		try {
			BigDecimal decimal = new BigDecimal(temp);
			returnData = decimal.toBigInteger();
		} catch (Exception e) {
			return str;
		}
		return returnData.toString();
	}

	/**
	 * 2004.7.13 add by Hjun
	 * 룰매니져 Function에서 호출됨 (SiteFormatFucntion)
	 * 숫자에 소수점을 추가하는 함수
	 * 전체 숫자 문자열 (szNumber)에 뒤자리(szDotLen) 길이 위치에 소수첨을 추가함
	 *
	 * @param szDotLen 소수첨 추가할 위치 (뒤에서 부터 자름)
	 * @param szNumber 전체 숫자 문자열
	 * @return 소수점 추가된 숫자 문자열
	 */
	public static String numberDot(String szDotLen, String szNumber) {
		String temp = NssStringUtils.null2ZeroStr(szNumber);
		int iDot = 0;

		try {
			iDot = Integer.parseInt(szDotLen);
		} catch (Exception e) {
			return szNumber;
		}

		if (temp.length() > iDot) {
			temp = temp.substring(0, temp.length() - iDot) + "." + temp.substring(temp.length() - iDot);
		}

		return temp;
	}

	/**
	 * 2004.7.16 add by jimi0203
	 * 2004.7.13 add by Hjun
	 * 룰매니져 Function에서 호출됨 (SiteFormatFucntion)
	 * 숫자에 소수점을 추가하는 함수
	 * 전체 숫자 문자열 (szNumber)에 뒤자리(szDotLen) 길이 위치에 소수첨을 추가함
	 *
	 * @param szDotLen 소수첨 추가할 위치 (뒤에서 부터 자름)
	 * @param szNumber 전체 숫자 문자열
	 * @return 소수점과 천단위 콤마 추가된 숫자 문자열
	 */
	public static String numberDotFormat(String szDotLen, String szNumber) {

		if ((szDotLen == null) || szDotLen.trim().equals("")) {
			return "";
		}

		String temp = NssStringUtils.null2ZeroStr(szNumber);
		int iDot = 0;

		try {
			iDot = Integer.parseInt(szDotLen);
		} catch (Exception e) {
			return szNumber;
		}

		if (temp.length() > iDot) {
			temp = temp.substring(0, temp.length() - iDot) + "." + temp.substring(temp.length() - iDot);
		} else if (temp.length() == iDot) {
			temp = "0." + temp;
		} else if (temp.length() == (iDot - 1)) {
			temp = "0.0" + temp;
		} else if (temp.length() == (iDot - 2)) {
			temp = "0.00" + temp;
		}

		return NssStringUtils.getMoneyForm(temp);
	}

	/**
	 * 2017.9.18 add by 90145230
	 * 소수점 자리수 처리하는 함수
	 * 전체 숫자 문자열 (szNumber)에 소수점이 있으면 소수점 이후의 숫자 뒤에 길이(szDotLen)만큼 0을 추가하고
	 * 전체 숫자 문자열 (szNumber)에 소수점이 없으면 소수점을 추가하고 뒤자리 길이(szDotLen)만큼 0을 추가함
	 *
	 * @param szDotLen 소수첨 자리수
	 * @param szNumber 전체 숫자 문자열
	 * @return 소수점과 천단위 콤마 추가된 숫자 문자열
	 */
	public static String numberDotFormat2(String szDotLen, String szNumber) {

		if ((szDotLen == null) || szDotLen.trim().equals("")) {
			return "";
		}

		String temp = NssStringUtils.null2ZeroStr(szNumber);
		int iDot = 0;

		try {
			iDot = Integer.parseInt(szDotLen);
		} catch (Exception e) {
			return szNumber;
		}

		String num1 = "";
		String num2 = "";

		if (szNumber.indexOf('.') > -1) {
			num1 = szNumber.split("[.]")[0];
			num2 = szNumber.split("[.]")[1];
		} else {
			num1 = szNumber;
		}

		for (int i = num2.length(); i < iDot; i++) {
			num2 += "0";
		}

		temp = num1 + "." + num2;

		return NssStringUtils.getMoneyForm(temp);
	}

	/*
	 * 소수점을 가진 문자열 포맷팅 szNumber: 숫자 문자열 szDotLen: 소수점 길이
	 */
	public static String numberFxFormat(String szNumber, String szDotLen) {
		String temp = NssStringUtils.null2ZeroStr(szNumber);
		int iDotLen = 0;
		try {
			iDotLen = Integer.parseInt(szDotLen);
		} catch (Exception e) {
			iDotLen = 0;
		}

		int offset = temp.indexOf(".");
		String work_str = "";
		String nonwork_str = "";
		if (offset > 0) {
			work_str = temp.substring(0, offset);
			nonwork_str = temp.substring(offset + 1, temp.length());
		} else {
			work_str = temp;
		}

		DecimalFormat df = new DecimalFormat("###,##0");
		double d = Double.valueOf(work_str).doubleValue();
		work_str = df.format(d);

		if (iDotLen > 0) {
			if (nonwork_str.length() > iDotLen) {
				nonwork_str = nonwork_str.substring(0, iDotLen);
			} else {
				for (int i = 0, len = iDotLen - nonwork_str.length(); i < len; i++) {
					nonwork_str += "0";
				}
			}
			return work_str + "." + nonwork_str;

		} else {
			return work_str;
		}
	}

	/* 2000. 9. 29 add by Hjun */
	/* 원화 환산액 FX10 에서 쓰인다 */
	/** number formating, 숫자에 ','를 첨가한다. */
	public static String numberKWR(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###,###");
			temp = decimal.format(change);
		}

		return temp;
	}

	/* 2000. 9. 29 add by Hjun */
	/* 원화 환산액 FX10 에서 쓰인다 */
	/** number formating, 숫자에 ','를 첨가한다. */
	public static String numberKRW(String str) {

		String temp = null;

		if ((str == null) || ((str.trim()).length() == 0)) {
			temp = "0";
		} else {
			double change = Double.valueOf(NssStringUtils.delComma(str.trim())).doubleValue();
			DecimalFormat decimal = new DecimalFormat("###,###,###,###");
			temp = decimal.format(change);
		}

		return temp;
	}

	/** 주민등록번호 또는 사업자번호에 '-'를 첨가한다. */
	public static String regnNo(String str) {

		String temp = null;
		int len = str.length();

		if ((len != 13) && (len != 10)) {
			return str;
		}

		// 사업자번호
		if (len == 10) {
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 10);
		}

		// 주민등록번호
		if (len == 13) {
			temp = str.substring(0, 6) + "-" + str.substring(6, 13);
		}

		return temp;
	}

	/**
	 * 주민등록 뒤 자리를 Asterisk(*) 처리 후 '-'를 첨가한다.
	 *
	 * @param str (-) 입력이 없는 주민/사업자 등록번호
	 * @return 변형(Asterisk)된 주민등록번호
	 */
	public static String regnNoAst(String str) {
		return NssStringUtils.regnNoAst(str, 4);
	}

	/**
	 * 주민등록 뒤 자리를 Asterisk(*) 처리 후 '-'를 첨가한다.
	 *
	 * @param str (-) 입력이 없는 주민/사업자 등록번호
	 * @return 변형(Asterisk)된 주민등록번호
	 */
	public static String regnNoAst2(String str) {
		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 7); //주민번호 123456-*******
		} else {
			return NssStringUtils.regnNo(str); //사업자번호 123-45-67890
		}
	}

	/**
	 * 주민등록 뒤 자리를 Asterisk(*) 처리 후 '-'를 첨가한다.
	 *
	 * @param str (-) 입력이 없는 주민/사업자 등록번호
	 * @return 변형(Asterisk)된 주민등록번호
	 */
	public static String regnNoAst3(String str) {
		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 7); //주민번호 123456-*******
		} else if (str.length() == 10) {
			return NssStringUtils.regnNoAst(str, 5); //사업자번호 123-45-*****
		} else if (str.length() == 9) {
			return NssStringUtils.regnNoAst(str, 4); // 여권번호 YS123****
		} else {
			return NssStringUtils.regnNo(str);
		}
	}

	/**
	 * 주민등록 뒤 자리를 Asterisk(*) 처리 후 '-'를 첨가한다.
	 *
	 * @param str (-) 입력이 없는 주민/사업자 등록번호
	 * @param rLen Asterisk 처리할 길이 - 주민등록번호 최대 (7), 사업자번호 최대 (5)
	 * @return 변형(Asterisk)된 주민등록번호
	 */
	public static String regnNoAst(String str, int rLen) {
		String temp = null;
		int len = str.length();

		int mLen = 5;
		String szAst = "*******";

		if ((len != 13) && (len != 10)) {
			return str;
		}

		// 사업자번호
		if (len == 10) {
			if (rLen > 5) {
				throw new IllegalArgumentException();
			}

			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, mLen + (mLen - rLen)) + szAst.substring(0, rLen);
		}

		// 주민등록번호
		if (len == 13) {
			if (rLen > 7) {
				throw new IllegalArgumentException();
			}

			mLen = 6;
			temp = str.substring(0, 6) + "-" + str.substring(6, mLen + (mLen - rLen) + 1) + szAst.substring(0, rLen);
		}
		// 여권번호
		if (len == 9) {
			if (rLen > 4) {
				throw new IllegalArgumentException();
			}
			mLen = 5;
			temp = str.substring(0, mLen + (mLen - rLen)) + szAst.substring(0, rLen);
		}
		return temp;
	}

	/**
	 * 주민등록 뒤 자리를 Asterisk(*) 처리 후 '-'를 첨가한다.
	 *
	 * @param str (-) 입력이 없는 주민/사업자 등록번호
	 * @param rLen Asterisk 처리할 길이 - 주민등록번호 123456-7******, 123-**-****1
	 * @return 변형(Asterisk)된 주민등록번호
	 */
	public static String regnNoAstStd(String str) {
		String temp = str;
		String delDashStr = NssStringUtils.delDash(str);
		int len = delDashStr.length();

		// 사업자번호
		if (len == 10) {
			temp = delDashStr.substring(0, 3) + "-**-****" + delDashStr.substring(9, 10);
		}
		// 주민등록번호
		else if (len == 13) {
			temp = delDashStr.substring(0, 6) + "-" + delDashStr.substring(6, 7) + "******";
		}
		// 여권번호
		else if (len == 9) {
			temp = delDashStr.substring(0, 5) + "****";
		}

		return temp;
	}

	/**
	 * 
	 * 주민/사업자 번호 * 마스킹처리
	 * <pre> 
	 * 주민번호는 뒷 6자리 마스킹 처리 후 구분자 삽입하여 리턴 ex) 123456-1****** <br/>
	 * 사업자번호는 마스킹 없이 구분자 삽입하여 리턴 ex) 123-45-67890
	 * </pre>
	 * @param str - 주민/사업자 등록번호
	 * @return String - 구분자가 포함된 주민/사업자등록번호 (주민번호만 뒷 6자리 마스킹)
	 */
	public static String maskRegnNo(String str) {
		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 6); 
		} else {
			return NssStringUtils.regnNo(str);
		}
	}
	/**
	 *   입력 string을 offset에서 부터 n byte 추출.
	 *   String method인 substring을 쓸 경우 한글을 한 character로 인식하므로
	 *   byte로 변환한 다음 처리한다
	 */
	public static String substr(String str, int offset, int len) {
		String output = "";

		String tStr = NssStringUtils.null2void(str);
		int tOffset = NssStringUtils.null2zero(offset);
		int tLen = NssStringUtils.null2zero(len);

		byte[] input = tStr.getBytes();
		if (tOffset >= input.length) {
			return output;
		}
		if ((tOffset + tLen) > input.length) {
			tLen = input.length - tOffset;
		}
		output = new String(input, tOffset, tLen);

		return output;
	}

	/** 시분초 사이에 ':'를 첨가한다. */
	public static String time(String str) {

		String temp = null;
		// Hjun edit.. 2000.11.1
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		int len = str.length();

		if (len != 6) {
			return str;
		}

		temp = str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6);

		return temp;
	}

	/** 시분 사이에 ':'를 첨가한다. */
	public static String time2(String str) {

		String temp = null;
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		int len = str.length();
		if (len < 4) {
			return str;
		}

		temp = str.substring(0, 2) + ":" + str.substring(2, 4);

		return temp;
	}

	/** 시분 한글로 표시한다 */
	/* by hjun 2000.12.06 */
	public static String timeHanHM(String str) {
		if ((str == null) || ((str.trim()).length() == 0)) {
			return "";
		}
		String temp = null;
		int len = str.length();

		if (len > 6) {
			return str;
		}

		temp = Integer.parseInt(str.substring(0, 2)) + "시 " + Integer.parseInt(str.substring(2, 4)) + "분";

		return temp;
	}

	public static String fixlengthZ(int out_len, String str) {

		byte[] input = str.getBytes();
		byte[] temp = new byte[out_len];

		int i, j;
		int in_len = input.length;

		for (i = 0; i < out_len; i++) {
			temp[i] = (byte) ' ';
		}

		// 입력된 길이보다 해당 String이 긴 경우
		if (in_len > out_len) {
			in_len = out_len;
		}

		for (i = (out_len - in_len), j = 0; i < out_len; i++, j++) {
			temp[i] = input[j];
		}

		String output = new String(temp, 0, out_len);

		return output;
	}

	/**
	 * 입력된 문자열(<code>originalString</code>)을
	 * 주어진 길이(<code>resultLength<code>) 이상의 문자열로 만든다.
	 *
	 * @param	originalString 원본 문자열. <code>null</code>도 허용됨.
	 * @param	resultLength 목표 길이
	 * @return	주어진 길이만큼 공백이 추가된 문자열. 해당 문자열이 목표 길이를
	 *        	초과할 경우에는 해당 문자열을 그대로 리턴.
	 */
	public static String convertLengthTo(String originalString, int resultLength) {

		StringBuffer sb = new StringBuffer(resultLength);

		int originalLength = 0;
		int appendLength = resultLength;

		if (originalString != null) {
			originalLength = originalString.length();

			if (originalLength >= resultLength) {
				return originalString;
			}

			appendLength = resultLength - originalLength;
			sb.append(originalString);
		}

		for (int i = 0; i < appendLength; i++) {
			sb.append(" ");
		}

		return sb.toString();
	}

	/////////////////////////////////////////////////////////////////
	//  외화 관련 포맷 유틸.
	/////////////////////////////////////////////////////////////////
	public static String Round(double dbValue, int index) {
		try {
			BigDecimal rounder = new BigDecimal(dbValue).setScale(index, java.math.BigDecimal.ROUND_HALF_EVEN);
			return NssStringUtils.toDecimal(rounder.doubleValue(), index - 1);
		} catch (Exception e) {
			return String.valueOf(dbValue);
		}
	}

	public static String Round(String strValue, int index) {
		try {
			return NssStringUtils.Round(Double.valueOf(strValue.trim()).doubleValue(), index);
		} catch (Exception e) {
			return strValue;
		}
	}

	public static String Rounddn(double dbValue, int index) {
		try {
			BigDecimal rounder = new BigDecimal(dbValue).setScale(index, java.math.BigDecimal.ROUND_DOWN);
			return NssStringUtils.toDecimal(rounder.doubleValue(), index - 1);
		} catch (Exception e) {
			return String.valueOf(dbValue);
		}
	}

	public static String Rounddn(String strValue, int index) {
		try {
			return NssStringUtils.Rounddn(Double.valueOf(strValue.trim()).doubleValue(), index);
		} catch (Exception e) {
			return strValue;
		}
	}

	public static String Roundup(double dbValue, int index) {
		try {
			BigDecimal rounder = new BigDecimal(dbValue).setScale(index, java.math.BigDecimal.ROUND_UP);
			return NssStringUtils.toDecimal(rounder.doubleValue(), index - 1);
		} catch (Exception e) {
			return String.valueOf(dbValue);
		}
	}

	public static String Roundup(String strValue, int index) {
		try {
			return NssStringUtils.Roundup(Double.valueOf(strValue.trim()).doubleValue(), index);
		} catch (Exception e) {
			return strValue;
		}
	}

	/**
	 *  반각문자를 전각 문자로 변환한다.
	 */
	public static String toFullChar(String src) {
		// 입력된 스트링이 null 이면 null 을 리턴
		if (src == null) {
			return null;
		}
		// 변환된 문자들을 쌓아놓을 StringBuffer 를 마련한다
		StringBuffer strBuf = new StringBuffer();
		char c = 0;

		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);

			//영문이거나 특수 문자 일경우.
			if ((c >= 0x21) && (c <= 0x7e)) {
				c += 0xfee0;
			} else if (c == 0x20) {
				c = 0x3000;
			}

			// 문자열 버퍼에 변환된 문자를 쌓는다
			strBuf.append(c);
		}
		return strBuf.toString();

	}

	/**
	 *  전각문자를 반각 문자로 변경한다.
	 */
	public static String toHalfChar(String src) {
		StringBuffer strBuf = new StringBuffer();
		char c = 0;

		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);

			//영문이거나 특수 문자 일경우.
			if ((c >= 0xff01) && (c <= 0xff5e)) {
				c -= 0xfee0;
			} else if (c == 0x3000) {
				c = 0x20;
			}

			// 문자열 버퍼에 변환된 문자를 쌓는다
			strBuf.append(c);
		}

		return strBuf.toString();
	}

	/*
	 * 주어진 String중 뒷부분 n byte를 '*'로 변경한다.
	 */
	public static String maskTail(String s, int len) {
		if ((s == null) || (s.length() <= 0)) {
			return s;
		}
		char c[] = s.toCharArray();
		for (int i = c.length - 1; (i > 0) && (len > 0); i--) {
			if ((c[i] != ' ') && (c[i] != '\t')) {
				c[i] = '*';
				len--;
			}
		}
		return new String(c);
	}

	/**
	* maskString
	* @param s 마스킹할 대상 문자열
	* @param pos 마스킹 위치
	**/
	public static String maskString(String s, int pos) {

		if ((s == null) || (s.length() <= 0)) {
			return s;
		}

		char c[] = s.toCharArray();
		pos = pos - 1;

		if ((pos > c.length) || (pos < 0)) {
			return s;
		}

		for (int i = c.length - 1; i > 0; i--) {
			if ((c[i] != ' ') && (c[i] != '\t')) {
				if (i == pos) {
					c[i] = '*';
				}
			}
		}
		return new String(c);
	}

	/**
	 * 주민등록 뒤 자리 중, 첫번째 자리수를 제외한 6자리를 Asterisk(*) 처리 후 '-'를 첨가한다.
	 *
	 * @param str (-) 입력이 없는 주민/사업자 등록번호
	 * @return 변형(Asterisk)된 주민등록번호
	 * @author 채널서비스부 서성문
	 */
	public static String regnNoAst4(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 6); //주민번호 123456-1******
		} else if (str.length() == 10) {
			return NssStringUtils.regnNoAst(str, 5); //사업자번호 123-45-*****
		} else if (str.length() == 9) {
			return NssStringUtils.regnNoAst(str, 4); // 여권번호 YS123****
		} else {
			return NssStringUtils.regnNo(str);
		}
	}

	/**
	 * 고객명 2번째자리, 법인명 3번째 자리에 대해서 '*'처리한다.
	 *
	 * @param String 타입의 고객명, 법인명
	 * @return '*' 처리된 고객명, 법인명
	 * @author 채널서비스부 서성문
	 */
	public static String transAstName(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String temp = null;
		int len = str.length();
		if (str.length() <= 4) {
			temp = str.substring(0, 1) + "*" + str.substring(2, len);
		} else {
			temp = str.substring(0, 2) + "*" + str.substring(3, len);
		}
		return temp;
	}

	/**
	 * 문자열 특정인덱스 이후에 대해서 '*'처리한다.
	 *
	 * @param String타입 문자열
	 * @return 특정인덱스 이후, '*'처리된 String타입 문자열
	 * @author 채널서비스부 서성문
	 */
	public static String transAstAfterIdx(String str, int rLen) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String temp = null;
		String szAst = "********************";
		int len = str.length(); //전체 문자열길이
		temp = str.substring(0, rLen) + szAst.substring(0, len - rLen); //개발자가 지정한 길이만큼만 보여주고, 나머지 부분에 대해서는 '*'처리

		return temp;
	}

	/**
	 * 계좌번호 5번째자리부터 마스킹 '*' 처리 후, 나머지 계좌번호 정보를 보여준다.
	 *
	 * @param String 타입의 계좌번호
	 * @return '*' 처리된 계좌번호
	 * @author 전자금융부 박일
	 */
	public static String transAcctMask(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String temp = null;
		int len = str.length();
		if (str.length() >= 11) {
			temp = str.substring(0, 4) + "******" + str.substring(10, len);
		} else {
			temp = str.substring(0, 2) + "*" + str.substring(3, len);
		}

		return temp;
	}

	/**
	 * 코드값을 코드명으로 변환한다.
	 *
	 * 2017.04.26
	 * 차세대 대응개발
	 */
	public static String decode(String pStrCdVal, String pStrCdList) {
		String[] keys;
		String returnVal = pStrCdVal;
		String[] splits = pStrCdList.split("\\,");
		boolean CdFlag = false;

		for (String split : splits) {
			keys = split.split("=");

			if (keys[0].equals(pStrCdVal)) {
				if (keys.length > 1) {
					returnVal = keys[1];

					CdFlag = true;
				}
			}

			if (!CdFlag) {
				returnVal = "";
			}
		}

		return returnVal;
	}

	/**
	 * 주민번호 : 13자리 중 생년월일과 성별을 제외한 뒷 6자리 마스킹
	 * @param str
	 * @param hypen
	 * @return '*' 처리된 주민번호
	 * @author 90145784
	 */
	public static String mastRegnNo(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String tmp = str.substring(0, 6).concat("-").concat(str.substring(6, 7)).concat("******");

		return tmp;
	}
	/**
	 * 주민번호 : 13자리 중 생년월일과 성별을 제외한 뒷 6자리 마스킹
	 * @param str
	 * @param hypen
	 * @return '*' 처리된 주민번호
	 * @author 90145784
	 */
	public static String transSsnoMaskWithHypen(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String tmp = str.substring(0, 6).concat("-").concat(str.substring(6, 7)).concat("******");

		return tmp;
	}

	/**
	 * 계좌번호 5번째자리부터 마스킹 '*' 처리 후, 나머지 계좌번호 정보를 보여준다.
	 *
	 * (1) 10자리 계좌번호 : 뒷 6자리 마스킹 (14220-64601  14220-*****)
	 * (2) 10자리 초과 계좌번호 : 뒷 7자리 마스킹 (1422-064-60123456  142-20-646***-****)
	 *
	 * @param String 타입의 계좌번호
	 * @return '*' 처리된 계좌번호
	 * @author 90145784
	 */
	public static String transAcctMaskWithHypen(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String temp = null;
		int len = str.length();
		if (str.length() > 11) { // 12 자리 이상
			temp = str.substring(0, len - 7).concat("*******");
			temp = temp.substring(0, 4).concat("-").concat(temp.substring(4, 7)).concat("-").concat(temp.substring(7, len));
		} else if (str.length() == 11) { // 11 자리
			temp = str.substring(0, 4).concat("-").concat(str.substring(4, 7)).concat("-").concat("****");
		} else { // 10 자리
			temp = str.substring(0, 4).concat("*").concat("-").concat("*****");
		}
		return temp;
	}

	/**
	 * 한글 - 첫 문자 이후 {(전체이름길이-1)/2} 자리
	 * (홍길동  홍*동 / 김빛나리  김*나리 / 성명이 2자리인 경우 마스킹 미적용)
	 * @param value
	 * @return '*' 처리된 성명
	 * @author 90145585
	 */
	public static String transNameMask(String value) {
		StringBuffer val = new StringBuffer("");

		if ((value == null) || "".equals(value)) {
			return "";
		}

		else {
			/*
			 * 고객명 마스킹 첫번째와 마지막글자외에는 일부 마스킹 처리 마스킹출력 예 : 테스트 -> 테*트 / 신테스트 -> 신*스트
			 */
			int len = value.length();
			int lencnt = 1;

			val.append(value.substring(0, 1));

			// 개인정보 마스킹 대상 및 처리기준[2018.03.15 기준]
			for (lencnt = 0; lencnt < ((len - 1) / 2); lencnt++) {
				val.append("*");
			}

			val.append(value.substring(++lencnt, len));

		}
		return val.toString();
	}

	/**
	 * 소수점이하 숫자 가져오기
	 * (ex: 12345.67 => 67)     value:12345.67
	 * (ex: 12345.67 => 6700)   value:12345.67
	 * @param value
	 * @return '*' 처리된 성명
	 * @author 90145336
	 */
	public static String dpNumber(String value) {
		if ((value == null) || value.trim().equals("")) {
			return "";
		}

		String num = "";

		if (value.indexOf('.') > -1) {
			num = value.split("[.]")[1];
		}

		return num;
	}

	/** 휴대폰번호의 체계에 따라 특정위치에 '-'를 첨가한다. */
	public static String hpNo(String str, String hcd) {
		str = NssStringUtils.null2void(str).trim(); //null 처리 및 trim

		String temp = null;
		int len = str.length();

		if ("0".equals(hcd)) {
			switch (len) {
			case 10:
				temp = str.substring(0, 3) + "-" + str.substring(3, 6) + "-" + str.substring(6, 10);
				break;
			case 11:
				temp = str.substring(0, 3) + "-" + str.substring(3, 7) + "-" + str.substring(7, 11);
				break;
			default:
				temp = str;
				break;
			}
		} else {
			switch (len) {
			case 10:
				temp = str.substring(0, 3) + "-" + str.substring(3, 6) + "-****";
				break;
			case 11:
				temp = str.substring(0, 3) + "-" + str.substring(3, 7) + "-****";
				break;
			default:
				temp = str;
				break;
			}
		}
		return temp;
	}

	/**
	 * StringUtils.leftPad wrapping add By leo
	 * @param str
	 * @param len
	 * @param addStr
	 * @return
	 */
	public static String lPad(String str, int len, String addStr) {
		return StringUtils.leftPad(str, len, addStr);
	}

	/** 2017.11.27 addleo
	 * leftPad  with space
	 * @param str
	 * @param len
	 * @return
	 */
	public static String lPad(String str, int len) {
		return StringUtils.leftPad(str, len);
	}

	/**
	 * StringUtils.rightPad wrapping add By leo
	 * rightPad with space
	 * @param str
	 * @param len
	 * @param addStr
	 * @return
	 */
	public static String rPad(String str, int len) {
		return StringUtils.rightPad(str, len);
	}

	/**
	 * StringUtils.rightPad wrapping add By leo
	 * @param str
	 * @param len
	 * @param addStr
	 * @return
	 */
	public static String rPad(String str, int len, String addStr) {
		return StringUtils.rightPad(str, len, addStr);
	}

	/**
	 * 난수생성.
	 * <pre>
	 * 	요청범위내의 난수를 생성.
	 * </pre>
	 * @param bound 난수 생성 범위. ex) 100 : 0~99
	 * @return
	 */
	public static String randomNumber(int bound) {
		SecureRandom random = new SecureRandom();
		int rndmNum = random.nextInt(bound);
		int len = String.valueOf(bound).length() - 1;
		while (rndmNum == 0) {
			rndmNum = random.nextInt(bound);
		}
		return NssStringUtils.lPad(String.valueOf(rndmNum), len, "0");
	}

	/**
	 *
	 * script tag 제거
	 * <pre>
	 * 	script tag 제거
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String deleteTag(String str) {
		str = NssStringUtils.null2void(str);
		return str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

	/**
	 * URL Encode
	 * @param enc 인코딩할 URL
	 * @return 인코딩된 URL
	 */
	public static String URLEncode(String enc) {
		if (enc == null) {
			return "";
		}
		byte[] encbyte = enc.getBytes();

		StringBuffer sb = new StringBuffer();
		for (byte element : encbyte) {
			if (((element >= 48) && (element <= 57)) || ((element >= 65) && (element <= 90)) || ((element >= 97) && (element <= 122))) {
				sb = sb.append((char) element);
			} else if (element == 32) {
				sb = sb.append('+');
			} else if ((element >= 0) && (element < 10)) {
				sb = sb.append("%0").append(Integer.toHexString((element & 0x000000ff)));
			} else {
				sb = sb.append("%").append(Integer.toHexString((element & 0x000000ff)));
			}
		}
		return sb.toString();
	}

	/**
	 * isEmpty.
	 * <pre>
	 * 	null, 공백 체크
	 * </pre>
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * 카멜표기법으로 변수 변환
	 * <pre>
	 * 	ex) ETC_AMT -> etcAmt
	 * </pre>
	 * @param string
	 * @return 카멜표기법 변수명
	 */
	public static String convert2CamelCase(String str) {
		if ((str.indexOf("_") < 0) && Character.isLowerCase((str.charAt(0)))) {
			return str;
		}

		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		int len = str.length();

		for (int i = 0; i < len; i++) {
			char currentChar = str.charAt(i);
			if (currentChar == '_') {
				nextUpper = true;
			} else {
				if (nextUpper) {
					result.append(Character.toUpperCase(currentChar));
					nextUpper = false;
				} else {
					result.append(Character.toLowerCase(currentChar));
				}
			}
		}

		return result.toString();
	}

	/**
	 * 리스트 정렬
	 * <pre>
	 * 	리스트 정렬
	 * </pre>
	 * @param list
	 * @return
	 */
	public static String[][] getSortList(String list[][]) {
		String[][] sortList = null;

		if ((list != null) && (list.length > 0)) {
			int addIndex = 0;
			String curCode = null;
			String curName = null;
			String[] tmpArray = null;
			String[] addArray = null;
			List tmpList = new ArrayList();
			for (String[] element : list) {
				curCode = NssStringUtils.null2void(element[0]); // 코드
				curName = NssStringUtils.null2void(element[1]); // 이름

				tmpArray = new String[2];
				addIndex = 0;
				for (int j = (tmpList.size() - 1); j >= 0; j--) {
					tmpArray = (String[]) tmpList.get(j);

					if (Integer.parseInt(tmpArray[0]) < Integer.parseInt(curCode)) {
						addIndex = j + 1;
						break;
					}
				}

				addArray = new String[2];
				addArray[0] = curCode;
				addArray[1] = curName;
				tmpList.add(addIndex, addArray);
			}

			sortList = new String[tmpList.size()][2];
			for (int i = 0; i < tmpList.size(); i++) {
				sortList[i][0] = ((String[]) tmpList.get(i))[0]; // 코드
				sortList[i][1] = ((String[]) tmpList.get(i))[1]; // 이름
			}
		}

		return sortList;
	}

	/**
	 * 리스트 정렬 후, JsonArray로 리턴
	 * <pre>
	 * 	리스트 정렬 후, JsonArray로 리턴
	 * </pre>
	 * @param list
	 * @return
	 */
	public static JSONArray getSortListToJsonArray(String list[][]) {
		String[][] sortList = null;

		JSONArray jsonSet = new JSONArray();
		JSONObject rst;
		if ((list != null) && (list.length > 0)) {
			int addIndex = 0;
			String curCode = null;
			String curName = null;
			String[] tmpArray = null;
			String[] addArray = null;
			List tmpList = new ArrayList();
			for (String[] element : list) {
				curCode = NssStringUtils.null2void(element[0]); // 코드
				curName = NssStringUtils.null2void(element[1]); // 이름

				tmpArray = new String[2];
				addIndex = 0;
				for (int j = (tmpList.size() - 1); j >= 0; j--) {
					tmpArray = (String[]) tmpList.get(j);

					if (Integer.parseInt(tmpArray[0]) < Integer.parseInt(curCode)) {
						addIndex = j + 1;
						break;
					}
				}

				addArray = new String[2];
				addArray[0] = curCode;
				addArray[1] = curName;
				tmpList.add((addIndex), addArray);
			}

			sortList = new String[tmpList.size()][2];
			for (int i = 0; i < tmpList.size(); i++) {
				sortList[i][0] = ((String[]) tmpList.get(i))[0]; // 코드
				sortList[i][1] = ((String[]) tmpList.get(i))[1]; // 이름
			}
		}
		if (sortList != null) {
			for (String[] element : sortList) {
				rst = new JSONObject();
				if (!"".equals(NssStringUtils.null2void(element[0]))) {
					rst.put("code", element[0]);
					rst.put("value", element[1]);
					jsonSet.add(rst);
				}
			}
		}
		return jsonSet;
	}

	/**
	 * 해시 코드 변환
	 * <pre>
	 * 	해시 코드 변환
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String getHash(String str) {
		str = null2void(str);
		int hashCode = str.hashCode();
		return String.valueOf(Math.abs(hashCode));
	}

	/**
	 * 금액패딩처리
	 * <pre>
	 * 	금액값을 받아서 left padding 0 처리
	 * 	ex) 1, 18 	: 000000000000000001
	 * 	ex) -1, 18 : -00000000000000001
	 * </pre>
	 * @param value
	 * @param len
	 * @return
	 */
	public static String amountPadding(String value, int len) {
		if (value == null) {
			return value;
		} else {
			int tmpSize = len;
			String returnVal = value;

			if (value.indexOf("-") > -1) {
				returnVal = returnVal.replaceAll("-", "");
				tmpSize = tmpSize - 1;
			}

			for (int i = returnVal.length(); i < tmpSize; i++) {
				returnVal = "0" + returnVal;
			}

			if (len != tmpSize) {
				returnVal = "-" + returnVal;
			}

			return returnVal;
		}
	}

	/**
	 * 전자어음 결제원에 맞도록 셋팅
	 * 정각처리,스페이스 2개이상 삭제,특수문자(물음표)삭제
	 * @param han 한글 string
	 * @return 결제원 한글 형식 변경된 String
	 */
	public static String convHan(String han) {
		String cHan = "";
		cHan = toFullChar(han);
		cHan = cHan.replaceAll("\\uA3BF", "");
		cHan = cHan.replaceAll("\\u3000{2,}", "\\\u3000");
		return trim(cHan);
	}

	/**
	 * Object값이 BigDecimal인 경우, Integer로 변환 후 리턴
	 * null인 경우, 0 리턴
	 * @param param
	 * @return 입력값(Integer), null인경우 0
	 */
	public static int null2Decimal(Object param) {
		try {
			if (param == null) {
				return 0;
			}

			return ((BigDecimal) param).intValue();

		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * String 값을 특정길이만큼 substring
	 * @param str
	 * @param length
	 * @return outputStr
	 */
	public static String subStrLen(String str, int length) {
		if (str == null || "".equals(str)) {
			return "";

		} else {
			String outputStr = "";

			if (str.length() > length) {
				outputStr = toFullChar(str).substring(0, length);
			} else {
				outputStr = toFullChar(str);
			}

			return outputStr;
		}
	}

	/**
	 * containsIgnoreCase
	 * @param str
	 * @param searchStr
	 * @return boolean
	 */
	public static boolean containsIgnoreCase(String str, String searchStr) {
		return StringUtils.containsIgnoreCase(str, searchStr);
	}

	/**
	 *
	 *  crlfToBr
	 * <pre>
	 * 	개행문자(\r\n)를 <BR> 문자로 변환
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String crlfToBr(String str) {
		StringBuffer rtnStr = new StringBuffer("");
		String linSep2 = "\r\n";
		int sepLen = linSep2.length();
		if (str.indexOf(linSep2) > -1) {
			while ((str.indexOf(linSep2)) > -1) {
				rtnStr.append(str.substring(0, str.indexOf(linSep2))).append("<BR>");
				str = str.substring(str.indexOf(linSep2) + sepLen);
			}
			rtnStr.append(str);
			return rtnStr.toString();
		} else {
			return str;
		}
	}

	private static String[] chengeHex = new String[]{"A1AD"};
	private static String[] removeHex = new String[]{"A3BF"};

    /**
     * 헥사값 문자열 변환
     * <pre>
     * 	헥사값 문자열 변환
     * </pre>
     * @param hex
     * @return
     */
    private static String hexToString(String hex) {
    	byte[] bt = null;
    	try{
			bt = new byte[hex.length() /2];
			for(int i = 0 ; i < hex.length() ; i+=2){
				String toParse = hex.substring(i,i+2);
				bt[i/2] = (byte)Integer.parseInt(toParse,16);
			}
    	}catch(Exception e){
			return hex;
		}
		return new String(bt);
    }

	/**
	 * 전자서명값 내 변환 안되는 문자열 변환(물결 -> 대쉬)
	 * <pre>
	 * 	전자서명값 내 변환 안되는 문자열 변환(물결 -> 대쉬)
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String chNeedlessStr(String str) {
    	String returnStr = "";
    	if(str == null){
    		return str;
    	}
    	try{
	    	returnStr = str;
    		for(int i = 0; i < chengeHex.length; i++){
	    		returnStr = NssStringUtils.replace(returnStr, NssStringUtils.hexToString(chengeHex[i]), NssStringUtils.toFullChar("-"));
	    	}

	    	for(int i = 0; i < removeHex.length; i++){
	    		returnStr = NssStringUtils.replace(returnStr, NssStringUtils.hexToString(removeHex[i]), "");
	    	}
    	}catch(Exception e){
    		return str;
    	}
    	return NssStringUtils.trim(returnStr);
    }

	/**
	 * index == len 인 경우 처리가 안 되는 문제가 있음
	 * @param str
	 * @param limit
	 * @return
	 */
	@Deprecated
	public static String shortCutString(String str, int limit) {
  		if (str == null || limit < 4) {
  			return str;
  		}
  		int len = str.length();
  		int cnt = 0;
  		int index = 0;

  		while (index < len && cnt < limit) {
     		if (str.charAt(index++) < 256) { // 1바이트 문자라면...
        			cnt++; // 길이 1 증가
     		} else {
           			cnt += 2; // 길이 2 증가
     		}
  		}

  		if (index < len && limit >= cnt ) {
     		str = str.substring(0, index);
  		} else if(index < len && limit < cnt ) {
     		str = str.substring(0, index-1);
  		}

   		return str;
	}

	public static String substringSafe(String s, int len) {
		byte[] b = s.getBytes();
		if (b.length <= len)
			return s;
		int m =  (b.length < len? b.length : len) - 1;
		boolean han_safe = true;
		for (int i = m; 0 <= i && (b[i] & 0x80) != 0; i--)
			han_safe = (han_safe == false);
		if (!han_safe)
			m--;
		
		return new String(b, 0,  m+1);
	}
	
	public static String del2ByteSpace(String str) {
		if (str == null) {
			return str;
		}
		return str.replaceAll("　", " ");
	}

	public static String getFundSTSStr(String header, String sts) {

		String tmpStr = "";

		if("10".equals(sts)) {
			tmpStr = header + " 접수";
		} else if("20".equals(sts)) {
			tmpStr = header + " 대기중";
		} else if("30".equals(sts)) {
			tmpStr = header + " 완료";
		} else if("90".equals(sts)) {
			tmpStr = header + " 취소";
		} else if("70".equals(sts)) {
			tmpStr = "처리 오류";
		}
		return tmpStr;
	}

	public static String getFundChgMoneyFormat(String amt, String cucd) {
		String strRst = NssStringUtils.getMoneyForm(amt,"2");

		if (!"KRW".equalsIgnoreCase(cucd)) {
			strRst = NssStringUtils.numberFxFormat(amt,"3");
		}

		return strRst;
	}

	public static long getFundAmtPasreLong(String amt, String cucd) {
		long lRst = 0L;

		if ("KRW".equalsIgnoreCase(cucd)) {

			lRst = Long.parseLong(NssStringUtils.number15( amt ));

		}else{
			lRst = Long.parseLong(NssStringUtils.null2void( amt.replace(".","") ));
		}

		return lRst;
	}

	public static String getFundChgSumAmt(long amt, String cucd) {
		String strRst = "";

		if ("KRW".equalsIgnoreCase(cucd)) {
			strRst = NssStringUtils.getMoneyForm(NssStringUtils.rmZero(String.valueOf(amt))) ;
		} else {
			strRst = NssStringUtils.numberDotFormat("3", NssStringUtils.null2void(String.valueOf(amt)));
		}

		return strRst;
	}

	public static String getShortYearDate(String strDt) {

		if (strDt.length() > 8) {
			strDt = strDt.substring(2);
		}

		return strDt;
	}

	public static double getFundRate(double total, double prn) {
		double dRst = 0.0000000;

		if(prn > 0){
			dRst = ((total - prn) * 100d) / prn; // 수익률 = (( 평가금액 - 투자원금 ) * 100) / 투자원금
			BigDecimal bNum = new BigDecimal(dRst);
			String sTemp = String.valueOf ( dRst );

			if (sTemp.indexOf("E") > 0) {
				String sNum = bNum.toString();
				String sA[]  = NssStringUtils.split(sNum, ".");
				dRst = Double.parseDouble(sA[0] + sA[1].substring(0, 3) );
			}
		}

		return dRst;
	}

	public static String getFundRateBubo(double rate) {
		String strRst = "0";
		String strTemp = String.valueOf(rate);

		if (rate == 0) {
			strRst = "0";
		} else if (strTemp.startsWith("-")) {
			strRst = "-";
		} else {
			strRst = "+";
		}

		return strRst;
	}

	public static String getFundFormatRate(String rate, int iLen) {
		String strRst = rate;
		int offset = strRst.indexOf(".");

		if (offset > 0){

			String rateA[] = NssStringUtils.split(strRst, ".");
			String strB = rateA[1];

			if (strB.length() > iLen) {
				strB = strB.substring(0, iLen);
			} else {
				strB = NssStringUtils.rPad(strB, iLen, "0");
			}

			if (iLen > 2) {
				double dNum = Double.parseDouble(  "0." + strB );
				String sNum = String.format("%.2f", dNum);

				String sA[] = NssStringUtils.split(sNum, ".");
				strRst = NssStringUtils.addComma( rateA[0] ) + "." + sA[1] + "0";
			} else {
				strRst = NssStringUtils.addComma( rateA[0] ) + "." + strB;
			}
		}

		return strRst;
	}

	public static String getAddMonth(String data1, String format, int monthCnt) {
	    if(data1 == null || data1.length() < 8) {
	        return "0";
	    }

	    try {
	        int year   = Integer.parseInt(data1.substring(0, 4));
	        int month  = Integer.parseInt(data1.substring(4, 6)) - 1;
	        int day    = Integer.parseInt(data1.substring(6, 8));
	        int time   = 0;
	        int minute = 0;
	        int second = 0;
	        Calendar cdate = new GregorianCalendar(year, month, day, time, minute, second);
	        cdate.add(Calendar.MONTH, monthCnt);
	        Date date = cdate.getTime();
	        SimpleDateFormat sdf  = new SimpleDateFormat(format);

	        return sdf.format(date);
	    } catch(NumberFormatException nfe) {
	        return "0";
	    }
	}

	public static Float getFloat(String inValue) {
        float outValue = 0;
        outValue = (inValue == null || "".equals(inValue)) ? 0 : Float.parseFloat(inValue);
        return outValue;
    }

	public static String[] splitFundFilePathNm(String fullNm) {
        String[] rtnStr = new String[2];

        if(!"".equals(NssStringUtils.null2void(fullNm)) && fullNm.indexOf("/") != -1) {
            String[] tempAry = NssStringUtils.parsing3(fullNm, "/");
            rtnStr[0] = tempAry[tempAry.length-1];
            rtnStr[1] = fullNm.replace(rtnStr[0], "").replace("/TERMS", "");
        } else {
            rtnStr[0] = fullNm;
            rtnStr[1] = "";
        }

        return rtnStr;
    }

	public static String fundFormatPftrt(String pftrt) {
        if("".equals(NssStringUtils.null2void( pftrt ))) {
            return "-";
        }
        return NssStringUtils.number12_2(NssStringUtils.zeroAdd( pftrt ));
    }

	public static String zeroAdd(String str) {
        if(str.startsWith(".")) {
            str = "0" + str;
        }

        return str;
    }

	public static String getFundMoneyForm(String money, String cucd) {

		String strRst = "";
		String ivstPrnP = "";
		String ivstPrnB = "";

		if ("KRW".equals(cucd)) {
			strRst = NssStringUtils.addComma(NssStringUtils.delDecimalPoint(money));
		} else {
			ivstPrnP = NssStringUtils.addComma(NssStringUtils.delDecimalPoint(money));
			ivstPrnB = NssStringUtils.dpNumber(money);

			if (ivstPrnB.length() < 3) {
				ivstPrnB = NssStringUtils.rPad(ivstPrnB, 3, "0");
			} else {
				ivstPrnB = ivstPrnB.substring(0, 3);
			}

			strRst = ivstPrnP + "." + ivstPrnB;
		}

		return strRst;
	}

	public static String getFundMoneyFormDot(String money, String cucd) {

		String strRst = "";
		String ivstPrnP = "";
		String ivstPrnB = "";

		if ("KRW".equals(cucd)) {
			ivstPrnP = NssStringUtils.addComma(NssStringUtils.delDecimalPoint(money));
			ivstPrnB = NssStringUtils.dpNumber(money);

			if (ivstPrnB.length() < 2) {
				ivstPrnB = NssStringUtils.rPad(ivstPrnB, 2, "0");
			} else {
				ivstPrnB = ivstPrnB.substring(0, 2);
			}

			strRst = ivstPrnP + "." + ivstPrnB;
		} else {
			ivstPrnP = NssStringUtils.addComma(NssStringUtils.delDecimalPoint(money));
			ivstPrnB = NssStringUtils.dpNumber(money);

			if (ivstPrnB.length() < 3) {
				ivstPrnB = NssStringUtils.rPad(ivstPrnB, 3, "0");
			} else {
				ivstPrnB = ivstPrnB.substring(0, 3);
			}

			strRst = ivstPrnP + "." + ivstPrnB;
		}

		return strRst;
	}

	/**
	 * XSS sciprtexe 항목 제거
	 */
	public static  String removeScriptTxt(String str) {
		//String xssPattern = "<script;<iframe;javascript:;</script;location.href;window\\[;=alert;string.fromcharcode;=eval;alert`;prompt`";
		String xssPattern = "<;>;\\(;\\);#;&;]";
		String[] xssPatArr = xssPattern.split(";");

		for (int i = 0; i < xssPatArr.length; i++) {
			String rmStr = xssPatArr[i];
			str = str.replaceAll(rmStr, "");
		}

		return str;
	}
	
	/**
	 * 문자열 바이트 길이 취득
	 * @param str 문자열
	 * @param charset 인코딩 문자셋
	 * @return
	 */
	public static int getByteLength(String str, String charset) {
	    try {
	        return str.getBytes(charset).length;
	    } catch (Exception e) {
	        return 0;
	    } 
	}

	/**
	 * 문자열 바이트 단위 자르기
	 * 
	 * UTF-8 기준
	 * 한글 3Byte | 알파벳, 대소문자, 숫자, 띄어쓰기 1Byte로 계산됨
	 * 
	 * @param str 문자열
	 * @param startBytes 시작 바이트
	 * @param endBytes 끝 바이트
	 * @return
	 */
	public static String substringByBytes(String str, int startBytes, int endBytes) {
		if ( "".equals(NssStringUtils.null2void(str)) || str.length() == 0 ) {
			return "";
		}
		
		if ( startBytes < 0 ) {
			startBytes = 0;
		}
		
		if ( endBytes < 1 ) {
			return "";
		}
		
		int len = str.length();
		
		int startIndex = -1;
		int endIndex = 0;
		
		int curBytes = 0;
		String ch = null;
		
		for ( int i = 0; i < len; i++ ) {
			ch = str.substring(i, i+1);
			curBytes += ch.getBytes().length;
			
			if ( startIndex == -1 && curBytes >= startBytes ) {
				startIndex = i;
			}
			
			if ( curBytes > endBytes ) {
				break;
			} else {
				endIndex = i+1;
			}
		}
		
		return str.substring(startIndex, endIndex);
	}

	/**
	 * addZero
	 * <pre>
	 * 	str문자에서 0을 size만큼 앞에 넣어줌
	 * </pre>
	 * @param size
	 * @param str
	 * @return
	 */
	public static String addZero(int size, String str) {
		int makeSpaceNum = size - str.length();
        for (int i = 0; i < makeSpaceNum; i++) {
        	str = "0" + str;
        }
        return str;
	}	
	
	/**
	 * 개발편의를 위해서 개발에만 존재
	 * @param map
	 * @return
	 */
	public static String hashMaptoString(Map<?, ?> map) {
		String _result = "";
		if(map == null)
			return "";
		Set<?> entries = map.entrySet();
		for(Object entry:entries) {
			_result += "\n ______ KEY : "+((Map.Entry<Object,Object>)entry).getKey()+" VALUE : "+((Map.Entry<Object,Object>)entry).getValue();
		}
		return _result;
	}

	/**
	 * 개발편의를 위해서 개발에만 존재
	 * @param map
	 * @return
	 */
	public static String customerToString(BizCustomer customer) {
		String _result = "";
		String[] customerGetMethodNm = {
				// 공통 - 신규추가 시작
				"getItCsNo", "getBizPeItCsNo", "getPsBzNo", "getBizPeRgsNo", "getCUS_DSCD", "getBRDT", "getJNPE_AGE",
				"getMNW_DSCD", "getNTVF_DSCD", "getNRSR_YN", "getELT_LIE_PRVN_XT_YN", "getBR_CHID_SECRD_HLDG_YN",
				"getOBST_YN"
				// 추가 20171231
				, "getMdAgrYn", "getEbnkFstUsgDt"

				// 공통 - ASIS 동일
				, "getUserID", "getUserNm", "useCoBnkg", "useEclips", "useBestLn", "usePsnBnkg", "useEsrw", "usePotl",
				"useInqSvc", "useQckBnkg", "useMoblBnkg", "useNoble", "useVIP", "useWrCsh", "useHidnAct", "getScrtMns",
				"getLstTsDD", "getWCoaExprDD", "useEmlRv", "getSerialNo", "getNobleUse", "getLstUseDT", "getLstUseTM",
				"getOtpKind", "getInputActUse", "isUserPcRgsYN", "isUserNPCOptYN", "isOTPGrac", "getOTPGracRgsDT",
				"getOTPGracExprDT", "getCardScrtMns", "getPsnCbrUseYN", "getCoCbrUseYN", "getIbcomScrtDis",
				"getCertSerialNo", "getCertLocation", "getHSMRegYN", "gePSNBZ_RGS_YN", "getRETIR_EMP_YN",
				"getTWO_CHANNEL", "getFEE_EVNT_TGT_YN", "getFRG_IP_BLOC_YN", "getTWOCH_VALID_DATE", "getHP_CRTSVC",
				"getSCSMS_USEYN", "getUSER_PWNO_CHG_DT", "getIcheSvc", "getFndtsTrlmtYN", "getOTP_BAND_CD",
				"getOTP_SERIAL_NO", "getBIZ_AllAct_USE", "getGraphicAuthUse", "getTwoAuthMeans", "getSmtPhoneUse",
				"getTsCrtCode", "getCustInfoInvalid", "getPSN_IMG_ID", "getSFCNF_CHR_INP", "getRemoteAddr",
				"isTsLmtReduct", "getSmtPhoneUseDate", "hasDDAcct", "hasSNAcct", "isDelayTs", "getDelayTsAmt",
				"getSimpleBnkgUse", "getSimpleTrsUse", "getWdrActNo", "getUserDis", "useIcheService", "useEFLn",
				"useFileSend", "isTsPwnoUse", "isBizAllActUse", "isBnkgLoginUser"

				// Object return
				, "getCustomerSecurity", "getConsultant", "getValidUser"

				// 개인 및 기업뱅킹사용가능 개인
				, "getEml", "getTelNO", "getHpNo"

		};
		String[] customerSetMethodNm = {
				// 공통 - 신규추가 시작
				"setItCsNo", "setBizPeItCsNo", "setPsBzNo", "setBizPeRgsNo", "setCUS_DSCD", "setBRDT", "setJNPE_AGE",
				"setMNW_DSCD", "setNTVF_DSCD", "setNRSR_YN", "setELT_LIE_PRVN_XT_YN", "setBR_CHID_SECRD_HLDG_YN",
				"setOBST_YN"
				// 추가 20171231
				, "setMdAgrYn", "setEbnkFstUsgDt"

				// 공통 - ASIS 동일
				, "setUserID", "setUserNm", "setCoBnkgUseYN", "setEclipsUseYN", "setBestLnUseYN", "setPsnBnkgUseYN",
				"setEsrwUseYN", "setPotlUseYN", "setInqSvcUseYN", "setQckBnkgUseYN", "setMoblBnkgUseYN",
				"setNobleUseYN", "setVipUseYN", "setWrCshUseYN", "setHidnActUseYN", "setScrtMns", "setLstTsDD",
				"setWCoaExprDD", "setEmlRvYN", "setSerialNo", "setNobleUse", "setLstUseDT", "setLstUseTM", "setOtpKind",
				"setInputActUse", "setUserPcRgsYN", "setUserNPCOptYN", "setOTPGracYN", "setOTPGracRgsDT",
				"setOTPGracExprDT", "setCardScrtMns", "setPsnCbrUseYN", "setCoCbrUseYN", "setIbcomScrtDis",
				"setCertSerialNo", "setCertLocation", "setHSMRegYN", "setPSNBZ_RGS_YN", "setRETIR_EMP_YN",
				"setTWO_CHANNEL", "setFEE_EVNT_TGT_YN", "setFRG_IP_BLOC_YN", "setTWOCH_VALID_DATE", "setHP_CRTSVC",
				"setSCSMS_USEYN", "setUSER_PWNO_CHG_DT", "setIcheSvc", "setFndtsTrlmtYN", "setOTP_BAND_CD",
				"setOTP_SERIAL_NO", "setBIZ_AllAct_USE", "setGraphicAuthUse", "setTwoAuthMeans", "setSmtPhoneUse",
				"setTsCrtCode", "setCustInfoInvalid", "setPSN_IMG_ID", "setSFCNF_CHR_INP", "setRemoteAddr",
				"setTsLmtReduct", "setSmtPhoneUseDate", "setDormantDepositHolding", "setSimpleNewHolding", "setDelayTs",
				"setDelayTsAmt", "setSimpleBnkgUse", "setSimpleTrsUse", "setWdrActNo", "setUserDis", "setIcheServiceYN",
				"setEFLnYN", "setFileSendYN", "setTsPwnoUse", "setBizAllActUse", "setBnkgLogin"

				// Object return
				, "setCustomerSecurity", "setConsultant", "setValidUser"

				// 개인 및 기업뱅킹사용가능 개인
				, "setEml", "setTelNO", "setHpNo"

		};
		String[] customerMethodTitle = {
				// 공통 - 신규추가 시작
				"통합고객번호", "사업자통합고객번호", "주민사업자등록번호", "사업자등록번호", "사용자구분<br/>1:개인,2:개인사업자,3:법인", "생년월일", "가입자연령", "남녀구분코드",
				"내외국인구분코드", "비거주자여부", "전자사기방지제외여부", "점자보안카드보유여부", "장애여부"
				// 추가 20171231
				, "매체동의여부", "전자뱅킹최초사용일자"

				// 공통 - ASIS 동일
				, "이용자ID", "이용자명", "기업뱅킹사용여부", "이클립스사용여부", "베스트론사용여부", "개인뱅킹사용여부", "에스크로사용여부", "포털사용여부", "조회서비스사용여부",
				"퀵뱅크사용여부", "모바일뱅킹사용여부", "노블닷컴사용여부", "VIP사용여부", "우리캐시사용여부", "숨김계좌사용여부", "보안수단번호", "최종이체일", "인증서만료일",
				"이메일수신여부", "인증서 일련번호", "노블닷컴사용여부", "최종사용일", "최종사용시각", "OTP 종류", "입금지정구분코드", "이용자PC등록여부", "이용자PC옵션여부",
				"상태번호", "OTP등록일", "종료일", "카드 사용자 인증방법", "개인사이버사용여부", "기업사이버사용여부", "보안수단", "인증서 serial", "인증서 저장매체",
				"특수인증서사용여부", "개인사업자등록여부", "퇴직직원여부", "2채널인증여부", "수수료이벤트대상여부", "해외IP차단유무여부", "기업2채널비상유효일", "핸드폰인증서비스신청여부",
				"보안SMS등록여부", "사용자비밀번호변경일", "이체서비스여부", "자금이체거래제한여부", "OTP밴드코드", "OTP일련번호", "법인전계좌조회사용여부", "그래픽인증사용여부",
				"2차인증 수단값(그래픽인증 포함)", "스마트폰사용여부", "이체SMS인증구분코드", "고객정보오류여부", "개인이미지식별자ID", "본인확인문자입력내용", "복호화된 IP",
				"이체한도축약대상여부", "스마트최종사용일", "휴면예금보유여부", "간편신규보유여부", "가입대상개인여부", "금액대단위지수", "간편뱅킹사용여부", "간편이체등록여부", "",
				"사용자구분", "", "", "", "이체비밀번호미등록여부", "법인전체보유계좌조회사용여부", "뱅킹 사용자 여부"

				// Object return
				, "temp 성 customer 정보", "상담원정보", "사용자 레벨 정보"

				// 개인 및 기업뱅킹사용가능 개인
				, "이메일주소", "전화번호", "핸드폰번호"

		};
//		String[] bizCustomerGetMethodNm = {
//				// 개인 및 기업뱅킹사용가능 개인
//				"hasBizCusInf"
//				// 법인사업자
//				, "getCoCusMstID", "getCoCusMstNm", "getCoCusNm", "getPsRgNo", "getUserSts", "getItCsNoByCust",
//				"getTsAcm", "getOneDayTsLmt", "getDeptNm", "getJbLvlNm", "getHP", "getMstHP", "getAplAutUserCnt",
//				"hasApvAut", "isPsnBiz", "isMaster", "isSnglUser", "getTelNO"/* ,"getUserAplAut" */ , "getCoCusNcm",
//				"isSuperMaster" };
//		String[] bizCustomerSetMethodNm = {
//				// 개인 및 기업뱅킹사용가능 개인
//				"setBizCusInfYN"
//				// 법인사업자
//				, "setCoCusMstID", "setCoCusMstNm", "setCoCusNm", "setPsRgNo", "setUserSts", "setItCsNoByCust",
//				"setTsAcm", "setOneDayTsLmt", "setDeptNm", "setJbLvlNm", "setHP", "setMstHP", "setAplAutUserCnt",
//				"setApvAutYN", "setPsnBizYN", "setMasterYN", "setSnglUserYN", "setTelNO"/* ,"setUserAplAut" */ ,
//				"setCoCusNcm", "setSuperMaster" };
//		String[] bizCustomerMethodTitle = {
//				// 개인 및 기업뱅킹사용가능 개인
//				"기업고객정보 적재 여부"
//				// 법인사업자
//				, "기업고객대표ID", "이용자명(기업마스터 이름)", "기업고객명", "실명번호(개인/개인사업자)<br/>사업자등록번호(법인)", "사업자상태번호", "고객기준 통합고객번호",
//				"이체누적액", "1일이체한도금액", "부서명", "직급명", "핸드폰번호", "휴대폰번호(기업마스터 이름)", "결제권한이 있는 사용자수", "결재권한여부", "개인사업자여부",
//				"마스터 여부", "단독사용자 여부", "전화번호"/* ,"업무번호" */ , "고객별칭명", "슈퍼마스터여부" };
		for(int i=0; i<customerGetMethodNm.length; i++){
			try {
				Method method = wbf.web.Customer.class.getMethod(customerGetMethodNm[i]);
				if("getItCsNo".equals(customerGetMethodNm[i])){
					_result += "\n ROKYUNG================================공통 - 신규추가====================================";
				} else if("getUserID".equals(customerGetMethodNm[i])){
					_result += "\n ROKYUNG================================공통 - ASIS 동일====================================";
				} else if("getEml".equals(customerGetMethodNm[i])){
					_result += "\n ROKYUNG================================개인 및 기업뱅킹사용가능 개인====================================";
				}
				_result += "\n ROKYUNG || "+customerMethodTitle[i]+"("+customerGetMethodNm[i]+","+customerSetMethodNm[i]+") || ";

				if("setCustomerSecurity".equals(customerSetMethodNm[i])
						||"setConsultant".equals(customerSetMethodNm[i])
						||"setValidUser".equals(customerSetMethodNm[i])
				){
					try {
						method.invoke(customer);
					}catch(Exception ignore) {}
				} else if("boolean".equals(method.getReturnType().getName())
						||"setTsLmtReduct".equals(customerSetMethodNm[i])			//Y로 Setting시 'true'를 Return(setTsLmtReduct(String) -> boolean isTsLmtReduct())
						||"setDormantDepositHolding".equals(customerSetMethodNm[i])	//Y로 Setting시 'true'를 Return(setDormantDepositHolding(String) -> boolean hasDDAcct())
						||"setSimpleNewHolding".equals(customerSetMethodNm[i]) 		//Y로 Setting시 'true'를 Return(setSimpleNewHolding(String) -> boolean hasSNAcct())
						||"setDelayTs".equals(customerSetMethodNm[i]) 				//Y로 Setting시 'true'를 Return(setDelayTs(String) -> boolean isDelayTs())
				){
					try {
						_result += "BOOLEAN VALUE : "+Boolean.toString((Boolean) method.invoke(customer));
					}catch(Exception ignore) {}
				} else if("java.lang.String".equals(method.getReturnType().getName())){
					try {
						_result += " STRING VALUE : "+(String)method.invoke(customer);
					}catch(Exception ignore) {}
				} else{
					try {
						_result += " VALUE : "+method.getReturnType().getName();
					}catch(Exception ignore) {}
				}
			} catch (Exception ignore) {}
		}
		return _result;
	}
}