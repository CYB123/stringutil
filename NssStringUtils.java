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
* 	�����ŷ StringUtils
* </pre>
* @Author woori
* @Since 2020-05-27
* @Modification-information
* <pre>
* ������		������			��������
* ----------------------------------------------------------------
* 2020-05-27	woori		���� �ۼ�
* 2020-12-14	hjkim		trim �ߺ�ȣ�� ����
* </pre>
*/
public class NssStringUtils extends StringUtils {

	/**
	 * String "" �� ���޹��� �Ķ���Ͱ����� ����
	 * @param param String
	 * @return �Է°� "" �� ��� ���޹��� ������ return
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
	 * String trim �Ǵ� null�� ""�� ����
	 * @param param String
	 * @return �Է°� trim, null�� ��� ""
	 */
	public static String null2void(String param) {

		return NssStringUtils.null2default(param, "");
	}

	/**
	 * String trim �Ǵ� null�� " "�� ����
	 * @param param String
	 * @return �Է°� trim, null�� ��� ""
	 */
	public static String null2Space(String param) {

		return NssStringUtils.null2default(param, " ");
	}

	/**
	 * Object���� String�ΰ�� String���� cast �� trim<br>
	 * null�� ��� ""null�� ""�� �����Ͽ� ����
	 * @param param Object
	 * @return �Է°� trim, null�� ��� ""
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
	 * Object���� String�ΰ�� String���� cast �� trim<br>
	 * null�� ��� " "null�� " "�� �����Ͽ� ����
	 * @param param Object
	 * @return �Է°� trim, null�� ��� ""
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
	 * �Ķ���Ͱ� null�̳� "" �̸� "0" ����
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
	 * �Ķ���Ͱ� null�̳� "" �̸� "0" ����
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
	 * �Ķ���Ͱ� null�̳� "" �̸� "0.0" ����
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
	 * Object���� null�̳� "" �̸� "0" ����
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
	 * �Է��� String�� �Ǿտ��ִ� '0'�� ���ش�.
	 * e) "00001234"   ---> "1234"
	 * ����(2002-01-31)
	 * �Ҽ��� ó��
	 * e) "000.00"     ---> "0.00"
	 * �ۼ� ��¥: (2001-12-24 ���� 6:17:07)
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
	 * �Է��� String�� �Ǿտ��ִ� '0'�� ���ش�.
	 * e) "00001234"   ---> "1234"
	 * ����(2002-01-31)
	 * �Ҽ��� ó��
	 * e) "000.00"     ---> "0.00"
	 * �ۼ� ��¥: (2001-12-24 ���� 6:17:07)
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
	 * �Է��� String�� �Ǿ�,�ڿ��ִ� '0'�� ���ش�.
	 * e) "000012340000"   ---> "1234"
	 * �Ҽ��� ó��
	 * e) "0045600.4500"     ---> "45600.45"
	 * �ۼ� ��¥: (2004-07-20 add by jimi0203)
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
	 * �Է��� String�� �Ǿտ��ִ� '0'�� ���ְ� offset���� ��ŭ�� ���� '0'�� ���ش�.
	 * e) "000012340000" �� offset=2�� ���  ---> "123400"
	 * �ۼ� ��¥: (2004-08.05 add by jimi0203)
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
	 * �Ϲ� ���ڿ��� �޾Ƽ� �ݾ� �������� ��ȯ�Ͽ� ����
	 * @param str
	 * @return �ݾ� �������� ����� ���ڿ�
	 */
	public static String getMoneyForm(String str) {
		if (str == null) {
			return "";
		}
		int offset = str.indexOf(".");
		String work_str = "";
		String nonwork_str = "";
		if (offset > 0) {
			//�Ҽ����� �ִ�
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
	 * �Ϲ� ���ڿ��� �޾Ƽ� �ݾ� �������� ��ȯ�Ͽ� ����
	 * @param str
	 * @param dist (0 :�Ϲݱݾ�ǥ��#,###,###, 1: �ڿ� 3�ڸ��� �Ҽ����� �ٿ��� �ݾ�ǥ�� #,###.###, 2: �ڿ� 3�ڸ��� �����ϰ� �ݾ�ǥ��)
	 *                      (3:�ڿ� 2�ڸ��� �����ϰ� �ݾ�ǥ��) (4:�Ҽ����Ʒ��� �����ϰ� �ݾ�ǥ��) (5:�ڿ� 1�ڸ��� �����ϰ� �ݾ�ǥ��)
	 *        2017-10-16 ����ǳ : 0 ���� ���� ó���� ���� ���� �߰���.
	 * @return �ݾ� �������� ����� ���ڿ�
	 */
	public static String getMoneyForm(String str, String dist) {
		if (dist.equals("1")) {
			str = NssStringUtils.makerLoanRate(str, 0, 3);
		} else if (dist.equals("2")) {
			if (str.length() > 3) {
				str = str.substring(0, str.length() - 3);
				int offset = str.indexOf(".");
				if (offset == (str.length() - 1)) {//�ǵڿ��Ҽ����ִ� ����
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
				//�Ҽ����� �ִ�
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
	 * ������ ������ ��ȯ..
	 * ����� �Ҽ��� ���� ����
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
	 * ������ ������ ��ȯ..
	 * ����� �Ҽ��� ���� ����
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
	 * ������ ������ ��ȯ..
	 * ����� �Ҽ��� ���� ����
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
	 * �Ҽ��������ڸ��� ä���ֱ�
	 * �Ҽ������� cnt��ŭ 0�� ä���
	 * �ۼ� ��¥: (2002-01-09 ���� 5:19:16)
	 * ���������� : 2002-02-18
	 * ������������ : String ---> StringBuffer��
	 * @return java.lang.String
	 */
	public static String getRateForm(String str, int cnt) {
		if ((str == null) || str.trim().equals("")) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(str);

		if (str.indexOf(".") < 0) {
			//�Ҽ����� ������
			sb.append(".");
			while (cnt > 0) {
				sb.append("0");
				cnt--;
			}
		} else {
			//�Ҽ����� ������
			while (cnt >= (str.length() - str.indexOf("."))) {
				sb.append("0");
				cnt--;
			}

		}
		return sb.toString();
	}

	/** ���¹�ȣ�� ü�迡 ���� Ư����ġ�� '-'�� ÷���Ѵ�. */
	public static String accountNo(String str) {
		str = NssStringUtils.null2void(str); //null ó�� �� trim

		String temp = null;
		int len = str.length();

		// 15�ڸ� ������ ��� ���ݰ� ������ ������ �ʿ� �ֽ�
		// ������ ����μ� ������ ����� ����.
		switch (len) {
		// ���� 3-2-6-4 (��-����-����ȣ-�Ϸù�ȣ)
		// ��) ���, ������ ��ġ��
		// ���� 3-6-3-3 (��-����ȣ-����-�Ϸù�ȣ)
		// ��) �츮���� ����

		/* 2004-8-3 Edit by Hjun */
		/* ���� ������ ��� 15�ڸ��� ������ ��Ʊ� ������ ����(-) ���� �� */
		/*
		 * case 15 : temp = str.substring(0, 3) + "-" + str.substring(3, 9) + "-" + str.substring(9, 12) + "-" + str.substring(12, 15); //temp = str.substring(0,3) + "-" // + str.substring(3,5) + "-" // + str.substring(5,11) + "-" // + str.substring(11,15); break; // 3-6-2-3 (��-����ȣ-����-�Ϸù�ȣ) // ��) ����, ��)�츮���� ����
		 */

		case 14:
			temp = str.substring(0, 3) + "-" + str.substring(3, 9) + "-" + str.substring(9, 11) + "-" + str.substring(11, 14);
			break;
		// 3-2-4-3 (��-����-�Ϸù�ȣ1-�Ϸù�ȣ2)
		// ��) ��ȭ
		case 13:
			temp = str.substring(0, 4) + "-" + str.substring(4, 7) + "-" + str.substring(7, 13);
			break;
		// 4-3-6
		// NBS �Ű��¹�ȣ 2004-07-30 �������߰�
		case 12:
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 9) + "-" + str.substring(9, 12);
			break;
		// 3-2-6 (��-����-���¹�ȣ)
		// ��) ���, �����
		case 11:
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 11);
			break;
		// ��Ÿ ���� �ڸ����� �츮���� ���˿� ���� �ʴ� ���
		default:
			temp = str;
			break;
		}
		return temp;
	}

	/**
	 * �츮���� ���¹�ȣ�� ����ϴ�.<br>
	 * 00000000000000  ---> 000-000000-00-000<br>
	 *
	 * �ۼ� ��¥: (2001-11-09 ���� 1:02:12)
	 * @return java.lang.String
	 */
	public static String getAcctForm(String acct) {

		return NssStringUtils.accountNo(acct);
	}

	/**
	 * ������¹�ȣ ǥ�ù������<br>
	 * 00000000000000  ---> 000-000000-00-000<br>
	 *
	 * �ۼ� ��¥: (2010-10-21)
	 * @return java.lang.String
	 */
	public static String getAcctForm(String bkcd, String acct) {

		bkcd = NssStringUtils.null2void(bkcd); //null ó�� �� trim

		if (!(bkcd.equals("020") || bkcd.equals("�츮����"))) {
			return acct;
		} else {
			return NssStringUtils.accountNo(acct);
		}
	}

	/**
	 * ī���ȣ�� ����ϴ�.<br>
	 * 000000000000000  ---> 0000-0000-0000-0000<br>
	 *
	 * �ۼ� ��¥: (2001-11-09 ���� 1:02:12)
	 * @return java.lang.String
	 */
	public static String getCardForm(String card) {
		return NssStringUtils.seccardNo(card);
	}

	/**
	 * String str�� int offset�� ��ġ�� "-"�� �����Ѵ�.<br>
	 * ��) <br>
	 * putDash("1234567", 3) �䷸�� �ϸ�<br>
	 * 123-4567 �䷸�� ����<br>
	 * �ۼ� ��¥: (2001-11-09 ���� 11:54:07)
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
	 * str���ٰ� offset1��°�ڸ��� '-'�� �����ϰ�<br>
	 * ���� cnt2��° �ڸ��� '-'����..<br>
	 *
	 * ��)
	 * putDash("1234567890", 4,  3) �̷��ϸ�
	 * "1234-567-89" �� ���� ����..
	 * �ۼ� ��¥: (2001-11-09 ���� 11:54:07)
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
	 * '-' 3���� ������ �ڸ��� ���� <br>
	 * �ۼ� ��¥: (2001-11-09 ���� 11:54:07)
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
	 * ���ڿ��� ���� ����
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
	 * ���ڿ��� ���� ���� ����
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
	 * ���ڿ��� ������ ���� ����
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

	/** ��ī���ȣ�� '-'�� ÷���Ѵ�.(�Ϻμ��� '*'�� ����ϰ� ����) */
	public static String seccardNo(String str) {

		String temp = null;
		int len = str.length();

		if (len != 16) {
			return str;
		}

		temp = str.substring(0, 4) + "-" + "****" + "-" + "****" + "-" + str.substring(12, 16);

		return temp;
	}

	/** �ֹι�ȣ �����  */
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
			// �Ҽ����� �ִ�
			preRate = str.substring(0, offset);
			backRate = str.substring(offset, offset + 3);
			loanRate = preRate + backRate;
		} else {
			// �������� �ڸ����� �ٸ��Ƿ� ȭ�鿡�� ó���ϰų� makerLoanRate�� ������� ����Ͽ��� �Ѵ�.
			loanRate = str;
		}
		return loanRate;
	}

	/**
	 * 90146903 �ݾ� ��ȭǥ�� �Ҽ�������
	 * @param str
	 * @return
	 */
	public static String getKRWMoney(String str) {

		if ((str == null) || "".equals(str)) {
			return str;
		}

		int offset = str.indexOf(".");
		if (offset > 0) {
			//�Ҽ����� ������
			str = str.substring(0, offset);
		}

		return str;
	}

	/** ����� ���̿� '.'�� ÷���Ѵ�. */
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

	/** ����� ���̿� '-'�� ÷���Ѵ�. */
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

	/** ����� ���̿� '.'�� ÷���Ѵ�. */
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

	/** ��� �ѱ۷� ǥ���Ѵ� */
	public static String dateHanYM(String str) {

		String temp = null;
		int len = str.length();

		if (len != 6) {
			return str;
		}
		if ((str.equals("000000")) || (str.equals("     0"))) {
			return "";
		}
		temp = str.substring(0, 4) + "�� " + Integer.parseInt(str.substring(4, 6)) + "��";

		return temp;
	}

	/** ����Ͻú� ���̿� '-', ':'�� ÷���Ѵ�.
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
	 * �����ʿ��� �ش� ���̸�ŭ �ڸ���..`
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
	 * ���ʿ��� �ش� ���̸�ŭ �ڸ���..
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

	/** �������� String�� ������ length�� String���� ��ȯ�Ѵ�. */
	/** �Էµ� String�� ������ ���̺��� ���� ��� space�� �߰��Ѵ�. */
	/** ���� '0':left align '1':right align */
	/**  ���� 2002.04.18  **/
	public static String fixlength(int kind, int out_len, String str, char fillCh) {
		byte[] temp = new byte[out_len];
		int i;
		for (i = 0; i < out_len; i++) {
			//temp[i] = Byte.parseByte(fillCh)   ;
			temp[i] = (byte) fillCh;
		} //out_len ��ŭ temp�� space�� ä���.

		if (!"".equals(NssStringUtils.null2void(str))) {
			byte[] input = str.getBytes();
			int j;
			int in_len = input.length;

			// �Էµ� ���̺��� �ش� String�� �� ���
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

	/** �������� String�� ������ length�� String���� ��ȯ�Ѵ�. */
	/** �Էµ� String�� ������ ���̺��� ���� ��� space�� �߰��Ѵ�. */
	/** ���� '0':left align '1':right align */
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

			// �Էµ� ���̺��� �ش� String�� �� ���
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

	/** �ϳ��� �� String�� �־��� integer array�� ������� tokenize */
	public static String[] parsing(int[] delim, String str) {

		int i, offset = 0;

		// �ѱ۹����� ���Ͽ� byte�� ��ȯ�� �� ó��
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

	/** �ϳ��� String(str)�� �־��� �и�����(tok) �������� tokenize   ***/
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

	/** �ϳ��� String(str)�� �־��� �и�����(tok) �������� tokenize   ***/
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

	/** �ϳ��� String(str)�� �־��� �и�����(tok) �������� tokenize   ***/
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

	/** number formating, ���ڿ� ','�� ÷���Ѵ�. */
	// ���� double �� ���ڿ��� �ٲ۴�..
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

	/** number formating, ���ڿ� ','�� ÷���Ѵ�. */
	// ���� double �� ���ڿ��� �ٲ۴�..
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

	/** number formating, ���ڿ� ','�� ÷���Ѵ�. */
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

	/** number formating, �Ҽ������� 2�ڸ� */
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

	/** number formating, �Ҽ������� 4�ڸ� */
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

	/** number formating, �Ҽ������� 2�ڸ� */
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

	/** number formating, �Ҽ������� 2�ڸ� */
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

	/** number formating, �Ҽ������� 3�ڸ� */
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

	/** number formating, �Ҽ������� 2�ڸ� */
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

	/** number formating, �Ҽ������� 3�ڸ� */
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

	/** number formating, �Ҽ������� 5 �ڸ� */
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
	 * ���ϴ� ������ alphaNumeric String�� ����� �޼ҵ�<br>
	 * getANstring("asdf",6) --> asdf  �� ��ȯ(asdf�ڿ� �����ĭ �߰� ^^;;)<br>
	 * �ۼ� ��¥: (2001-11-16 ���� 8:31:40)
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
	 * ���ϴ� ������ Numeric String�� ����� �޼ҵ� tag ��<br>
	 * getNstring(12345, 7) ---> "0012345" �� ��ȯ��.
	 * �ۼ� ��¥: (2001-11-16 ���� 8:31:40)
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
	 * ���ϴ� ������ Numeric String�� ����� �޼ҵ�<br>
	 * getNstring(12345, 7) ---> "0012345" �� ��ȯ��.
	 * �ۼ� ��¥: (2001-11-16 ���� 8:31:40)
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
	 * ���ϴ� ������ Numeric String�� ����� �޼ҵ�<br/>
	 * 3��° �Ķ���ʹ� ä������ �е������� ���� (1 ����, -1 ������)
	 * getNstring(12345, 7, 1) -> "0012345" <br/>
	 * getNstring(12345, 7, -1) -> "1234500" <br/>
	 * �ۼ� ��¥: (2001-11-16 ���� 8:31:40)
	 * @return ���������� ���ڿ�
	 */
	public static String getNstring(int intstr, int length, int direction) {
		String str = Integer.toString(intstr);
		for (int i = length - str.length(); i > 0; --i) {
			str = (direction > 0) ? "0" + str : str + "0";
		}
		return str;
	}

	/**
	 * ���¹�ȣ�� ü�迡 ����  '-'�� �����Ѵ�
	 *
	 * @param   str   ǥ���ϰ��� �ϴ� ���¹�ȣ
	 * @return   ���¹�ȣ
	 */
	public static String delDashAccNo(String str) {

		String temp = null;
		str = str.trim();
		int len = str.length();

		// ���� ������� �������� 15�ڸ� ���¹�ȣ ���й���� ����
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

	/** number formating, ���ڿ� ','�� ÷���Ѵ�. */
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

	/** ȯ�� number formating, ���ڿ� ','�� ÷���Ѵ�. */
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

	/**  ','�� �����Ѵ�. */
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

	/**  '-'�� �����Ѵ�. */
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

	/**  ':'�� �����Ѵ�. */
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

	/**  ���ڿ����� '.'�� �����Ѵ�. (�� : yyyy.mm.dd ���� ) */
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

	/**  ���ڿ����� ' '(space)�� �����Ѵ�.  (�߰� 2002.06.03)*/
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

	/**  ���ڿ����� '-','',':'�� �����Ѵ�. (�� : yyyy-mm-dd xx:xx:xx ���� ) */
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
	 *  String �߿� '\n'���� '&ltbr&gt'�� �����Ѵ�.
	 * @param String str
	 * @return '\n'���� '&ltbr&gt'�� ����� ���ڿ�
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
	 * �����̳� "-" ���ֱ�
	 * @param String param
	 * @return �����̳� "-"�� ���ŵ� ���ڿ�
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
	 * @param ccy : ��ȭ�ڵ� ��) USD, KRW...
	 * @param amount : �ݾ�  ��) 000001000 = > 1000.000
	 * @author �躹�� �븮, 2004-09-08
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
	 * @param ccy : ��ȭ�ڵ� ��) USD, KRW...
	 * @param amount : �ݾ�  ��) 000001000
	 * @author ������
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
	 * ��ȭ��ȣ �и�
	 * @param String v_telno
	 * @param String v_flag
	 * @return flag���� �и��� ��ȭ��ȣ ���ڿ�
	 *
	 * flag�� 1 �̸� ������ȣ�� �����ϰ�,
	 * flag�� 2 �̸� ������ �����ϰ�,
	 * flag�� 3 �̸� ��ȭ��ȣ�� �����ϰ�,
	 * flag�� 4 �̸� ��ü��ȣ�� �����ϰ�,
	 * flag�� 5 �̸� ������ȣ�� �����Ѵ�.
	 *
	 * ������ȣ�� ���
	 * �� DB������ ������ȣ�� ������ ����� �����Ƿ�,
	 * 2�ڸ� Ȥ�� 4���� ��츸 ������ ���ϵǰ�, 3�ڸ��� ���
	 * ������ ��ȭ��ȣ, ������ȣ�� ��� ������ ���ϵ� �� �ִ�.
	 */
	public static String getTelSep(String v_telno, int v_flag) {

		String telno = ""; // ���ڷ� ���� ��ü ��ȭ��ȣ
		String d_telno = ""; // ������ȣ
		String t_telno = ""; // ������ȣ�� ������ ��ȣ
		String r_telno = ""; // ������ ��ȣ
		String telno1 = ""; // ������ȣ
		String telno2 = ""; // ����ȣ
		String telno3 = ""; // ��ȭ��ȣ(4�ڸ�)
		String telno4 = ""; // ������ȣ

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

			// ������ȣ�� ���� ��
			if ((t_telno.length() == 7) || (t_telno.length() == 8)) {
				telno2 = t_telno.substring(0, t_telno.length() - 4);
				telno3 = t_telno.substring(t_telno.length() - 4);
				// ������ȣ�� 2�ڸ��� ��
			} else if ((t_telno.length() == 9) || (t_telno.length() == 10)) {
				telno2 = t_telno.substring(0, t_telno.length() - 6);
				telno3 = t_telno.substring(t_telno.length() - 6, t_telno.length() - 2);
				telno4 = t_telno.substring(t_telno.length() - 2);
				// ������ȣ�� 4�ڸ��� ��
			} else if ((t_telno.length() == 11) || (t_telno.length() == 12)) {
				telno2 = t_telno.substring(0, t_telno.length() - 8);
				telno3 = t_telno.substring(t_telno.length() - 8, t_telno.length() - 4);
				telno4 = t_telno.substring(t_telno.length() - 4);
			} else {
				r_telno = telno;
			}

			if (v_flag == 1) {
				r_telno = telno1; //���ڸ�

			} else if (v_flag == 2) {
				r_telno = telno2; //�߰��ڸ�

			} else if (v_flag == 3) {
				r_telno = telno3; //�������ڸ�

			} else if (v_flag == 4) {
				r_telno = telno1 + "-" + telno2 + "-" + telno3 + telno4; //��ü

			} else if (v_flag == 5) {
				r_telno = telno4;

			}
			/*
			 * 2005-12-13 add by Daemot ���������� ���û��� ���� - ��ȭ��ȣ ���ڸ� masking
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

		String telno = ""; // ���ڷ� ���� ��ü ��ȭ��ȣ
		String d_telno = ""; // ������ȣ
		String t_telno = ""; // ������ȣ�� ������ ��ȣ
		String r_telno = ""; // ������ ��ȣ
		String telno1 = ""; // ������ȣ
		String telno2 = ""; // ����ȣ
		String telno3 = ""; // ��ȭ��ȣ(4�ڸ�)
		String telno4 = ""; // ������ȣ

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

			// ������ȣ�� ���� ��
			if ((t_telno.length() == 7) || (t_telno.length() == 8)) {
				telno2 = t_telno.substring(0, t_telno.length() - 4);
				telno3 = t_telno.substring(t_telno.length() - 4);
				// ������ȣ�� 2�ڸ��� ��
			} else if ((t_telno.length() == 9) || (t_telno.length() == 10)) {
				telno2 = t_telno.substring(0, t_telno.length() - 6);
				telno3 = t_telno.substring(t_telno.length() - 6, t_telno.length() - 2);
				telno4 = t_telno.substring(t_telno.length() - 2);
				// ������ȣ�� 4�ڸ��� ��
			} else if ((t_telno.length() == 11) || (t_telno.length() == 12)) {
				telno2 = t_telno.substring(0, t_telno.length() - 8);
				telno3 = t_telno.substring(t_telno.length() - 8, t_telno.length() - 4);
				telno4 = t_telno.substring(t_telno.length() - 4);
			} else {
				r_telno = telno;
			}

			if (v_flag == 1) {
				r_telno = telno1; //���ڸ�

			} else if (v_flag == 2) {
				r_telno = telno2; //�߰��ڸ�

			} else if (v_flag == 3) {
				r_telno = telno3; //�������ڸ�

			} else if (v_flag == 4) {
				r_telno = telno1 + "-" + telno2 + "-" + telno3 + telno4; //��ü

			} else if (v_flag == 5) {
				r_telno = telno4;

			}
			/*
			 * 2005-12-13 add by Daemot ���������� ���û��� ���� - ��ȭ��ȣ ���ڸ� masking
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
	 * ���ڿ� �պκ��� '0'���� ä���� �ִ� ���<br>
	 * ���ڿ��� ���̸� ������ ä '0'�� ' '���� ��ȯ�Ͽ� �����Ѵ�.
	 *
	 * @param str '0'���� ä���� ���ڿ�
	 * @return '0'�� ' '���� �ٲ㼭 ���̸� ���� ���ڿ�
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
	 * ���� ��ȯ
	 * Returns a String with all occurrences of the String from
	 * replaced by the String to.
	 *
	 * @param in �۾��� ���ڸ� �����ϴ� ���ڿ�
	 * @param from ã�� ���ڿ�
	 * @param to ��ȯ�� ���ڿ�
	 * @return The new String
	 */
	public static String replace(String in, String from, String to) {

		/* from vs to ġȯ�� ���� ���� ������� ���� �߻� �Ͽ� ����ó�� */
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
	 * �Խ��ǿ� ���� ��ȯ
	 * Returns a String with all occurrences of the String sTagString
	 * replaced by the String for HTML.
	 *
	 * @param sTagString �۾��� ���ڸ� �����ϴ� ���ڿ�
	 * @return The new String HTML���� ������ ���ڿ�
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
	 * ���ڿ� �߰��� ������ ������ĭ���� ����
	 * @param txt
	 * @return ������ ��ĭ���� ������ ���ڿ�
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
	 * ���ڿ� �߰��� ������ ������ĭ���� ����
	 * @param txt
	 * @return ������ ��ĭ���� ������ ���ڿ�
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
	 * ����� �ѱ۷� ǥ��
	 * @param str �����(8�ڸ�) Ȥ�� ����Ͻú���(14�ڸ�)
	 * @return �ѱ� �������� ǥ���� �����(�ú���)
	 */
	public static String dateHan(String str) {

		int len = str.length();

		StringBuffer sbTemp = new StringBuffer();

		if ((len == 8) || (len == 14)) {
			if ((str.equals("00000000")) || (str.equals("       0"))) {
				return "";
			}

			sbTemp.append(str.substring(0, 4)).append("�� ").append(Integer.parseInt(str.substring(4, 6))).append("�� ").append(Integer.parseInt(str.substring(6, 8))).append("�� ");

			if (len == 14) {
				sbTemp.append(str.substring(8, 10)).append("�� ").append(Integer.parseInt(str.substring(10, 12))).append("�� ").append(Integer.parseInt(str.substring(12, 14)))
						.append("�� ");
			}

			return sbTemp.toString();
		} else {
			return str;
		}
	}

	/**
	 * 2004.8.4 add by Hjun
	 * 18 �ڸ� �ڸ����� szCut �ڸ��� �ٲ۴�.
	 * #NUM(13) ���� �ҽ� ������ (str)�� 13�� ����� (���ʺ��� �����ؼ� ������ ���� �ڸ���)
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
	 * 18 �ڸ� �ڸ����� 15�ڸ��� �ٲ۴�.
	 * �Ҽ��� 3�ڸ��� �ڸ���.
	 */
	public static String number15(String str) {

		//format�� �ȵǾ� �ִ� ��� - plain72
		String temp = NssStringUtils.null2ZeroStr(str);

		if (temp.length() > 3) { // null2ZeroStr�� ��ģ��� �Ҽ����� ���� 0�� ����� ����

			if (temp.indexOf(".") > -1) {
				//�Ҽ����� �ݾ��� �Բ� ���� ��� ex : 123456789.000 > 123456789 �� ��ȯ
				temp = temp.substring(0, temp.indexOf("."));
			} else {
				temp = temp.substring(0, (temp.length() - 3));
			}

		} else if (temp.length() == 3) { //�Ҽ��� �����ڸ��� �����ϴ� string �� ���
			temp = "0";
		}

		return temp;
	}

	/**
	 * 2017-09-05
	 * �Ҽ��� ���ϴ� �����Ѵ�.
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
	 * ��Ŵ��� Function���� ȣ��� (SiteFormatFucntion)
	 * ���ڿ� �Ҽ����� �߰��ϴ� �Լ�
	 * ��ü ���� ���ڿ� (szNumber)�� ���ڸ�(szDotLen) ���� ��ġ�� �Ҽ�÷�� �߰���
	 *
	 * @param szDotLen �Ҽ�÷ �߰��� ��ġ (�ڿ��� ���� �ڸ�)
	 * @param szNumber ��ü ���� ���ڿ�
	 * @return �Ҽ��� �߰��� ���� ���ڿ�
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
	 * ��Ŵ��� Function���� ȣ��� (SiteFormatFucntion)
	 * ���ڿ� �Ҽ����� �߰��ϴ� �Լ�
	 * ��ü ���� ���ڿ� (szNumber)�� ���ڸ�(szDotLen) ���� ��ġ�� �Ҽ�÷�� �߰���
	 *
	 * @param szDotLen �Ҽ�÷ �߰��� ��ġ (�ڿ��� ���� �ڸ�)
	 * @param szNumber ��ü ���� ���ڿ�
	 * @return �Ҽ����� õ���� �޸� �߰��� ���� ���ڿ�
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
	 * �Ҽ��� �ڸ��� ó���ϴ� �Լ�
	 * ��ü ���� ���ڿ� (szNumber)�� �Ҽ����� ������ �Ҽ��� ������ ���� �ڿ� ����(szDotLen)��ŭ 0�� �߰��ϰ�
	 * ��ü ���� ���ڿ� (szNumber)�� �Ҽ����� ������ �Ҽ����� �߰��ϰ� ���ڸ� ����(szDotLen)��ŭ 0�� �߰���
	 *
	 * @param szDotLen �Ҽ�÷ �ڸ���
	 * @param szNumber ��ü ���� ���ڿ�
	 * @return �Ҽ����� õ���� �޸� �߰��� ���� ���ڿ�
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
	 * �Ҽ����� ���� ���ڿ� ������ szNumber: ���� ���ڿ� szDotLen: �Ҽ��� ����
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
	/* ��ȭ ȯ��� FX10 ���� ���δ� */
	/** number formating, ���ڿ� ','�� ÷���Ѵ�. */
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
	/* ��ȭ ȯ��� FX10 ���� ���δ� */
	/** number formating, ���ڿ� ','�� ÷���Ѵ�. */
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

	/** �ֹε�Ϲ�ȣ �Ǵ� ����ڹ�ȣ�� '-'�� ÷���Ѵ�. */
	public static String regnNo(String str) {

		String temp = null;
		int len = str.length();

		if ((len != 13) && (len != 10)) {
			return str;
		}

		// ����ڹ�ȣ
		if (len == 10) {
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 10);
		}

		// �ֹε�Ϲ�ȣ
		if (len == 13) {
			temp = str.substring(0, 6) + "-" + str.substring(6, 13);
		}

		return temp;
	}

	/**
	 * �ֹε�� �� �ڸ��� Asterisk(*) ó�� �� '-'�� ÷���Ѵ�.
	 *
	 * @param str (-) �Է��� ���� �ֹ�/����� ��Ϲ�ȣ
	 * @return ����(Asterisk)�� �ֹε�Ϲ�ȣ
	 */
	public static String regnNoAst(String str) {
		return NssStringUtils.regnNoAst(str, 4);
	}

	/**
	 * �ֹε�� �� �ڸ��� Asterisk(*) ó�� �� '-'�� ÷���Ѵ�.
	 *
	 * @param str (-) �Է��� ���� �ֹ�/����� ��Ϲ�ȣ
	 * @return ����(Asterisk)�� �ֹε�Ϲ�ȣ
	 */
	public static String regnNoAst2(String str) {
		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 7); //�ֹι�ȣ 123456-*******
		} else {
			return NssStringUtils.regnNo(str); //����ڹ�ȣ 123-45-67890
		}
	}

	/**
	 * �ֹε�� �� �ڸ��� Asterisk(*) ó�� �� '-'�� ÷���Ѵ�.
	 *
	 * @param str (-) �Է��� ���� �ֹ�/����� ��Ϲ�ȣ
	 * @return ����(Asterisk)�� �ֹε�Ϲ�ȣ
	 */
	public static String regnNoAst3(String str) {
		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 7); //�ֹι�ȣ 123456-*******
		} else if (str.length() == 10) {
			return NssStringUtils.regnNoAst(str, 5); //����ڹ�ȣ 123-45-*****
		} else if (str.length() == 9) {
			return NssStringUtils.regnNoAst(str, 4); // ���ǹ�ȣ YS123****
		} else {
			return NssStringUtils.regnNo(str);
		}
	}

	/**
	 * �ֹε�� �� �ڸ��� Asterisk(*) ó�� �� '-'�� ÷���Ѵ�.
	 *
	 * @param str (-) �Է��� ���� �ֹ�/����� ��Ϲ�ȣ
	 * @param rLen Asterisk ó���� ���� - �ֹε�Ϲ�ȣ �ִ� (7), ����ڹ�ȣ �ִ� (5)
	 * @return ����(Asterisk)�� �ֹε�Ϲ�ȣ
	 */
	public static String regnNoAst(String str, int rLen) {
		String temp = null;
		int len = str.length();

		int mLen = 5;
		String szAst = "*******";

		if ((len != 13) && (len != 10)) {
			return str;
		}

		// ����ڹ�ȣ
		if (len == 10) {
			if (rLen > 5) {
				throw new IllegalArgumentException();
			}

			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, mLen + (mLen - rLen)) + szAst.substring(0, rLen);
		}

		// �ֹε�Ϲ�ȣ
		if (len == 13) {
			if (rLen > 7) {
				throw new IllegalArgumentException();
			}

			mLen = 6;
			temp = str.substring(0, 6) + "-" + str.substring(6, mLen + (mLen - rLen) + 1) + szAst.substring(0, rLen);
		}
		// ���ǹ�ȣ
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
	 * �ֹε�� �� �ڸ��� Asterisk(*) ó�� �� '-'�� ÷���Ѵ�.
	 *
	 * @param str (-) �Է��� ���� �ֹ�/����� ��Ϲ�ȣ
	 * @param rLen Asterisk ó���� ���� - �ֹε�Ϲ�ȣ 123456-7******, 123-**-****1
	 * @return ����(Asterisk)�� �ֹε�Ϲ�ȣ
	 */
	public static String regnNoAstStd(String str) {
		String temp = str;
		String delDashStr = NssStringUtils.delDash(str);
		int len = delDashStr.length();

		// ����ڹ�ȣ
		if (len == 10) {
			temp = delDashStr.substring(0, 3) + "-**-****" + delDashStr.substring(9, 10);
		}
		// �ֹε�Ϲ�ȣ
		else if (len == 13) {
			temp = delDashStr.substring(0, 6) + "-" + delDashStr.substring(6, 7) + "******";
		}
		// ���ǹ�ȣ
		else if (len == 9) {
			temp = delDashStr.substring(0, 5) + "****";
		}

		return temp;
	}

	/**
	 * 
	 * �ֹ�/����� ��ȣ * ����ŷó��
	 * <pre> 
	 * �ֹι�ȣ�� �� 6�ڸ� ����ŷ ó�� �� ������ �����Ͽ� ���� ex) 123456-1****** <br/>
	 * ����ڹ�ȣ�� ����ŷ ���� ������ �����Ͽ� ���� ex) 123-45-67890
	 * </pre>
	 * @param str - �ֹ�/����� ��Ϲ�ȣ
	 * @return String - �����ڰ� ���Ե� �ֹ�/����ڵ�Ϲ�ȣ (�ֹι�ȣ�� �� 6�ڸ� ����ŷ)
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
	 *   �Է� string�� offset���� ���� n byte ����.
	 *   String method�� substring�� �� ��� �ѱ��� �� character�� �ν��ϹǷ�
	 *   byte�� ��ȯ�� ���� ó���Ѵ�
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

	/** �ú��� ���̿� ':'�� ÷���Ѵ�. */
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

	/** �ú� ���̿� ':'�� ÷���Ѵ�. */
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

	/** �ú� �ѱ۷� ǥ���Ѵ� */
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

		temp = Integer.parseInt(str.substring(0, 2)) + "�� " + Integer.parseInt(str.substring(2, 4)) + "��";

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

		// �Էµ� ���̺��� �ش� String�� �� ���
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
	 * �Էµ� ���ڿ�(<code>originalString</code>)��
	 * �־��� ����(<code>resultLength<code>) �̻��� ���ڿ��� �����.
	 *
	 * @param	originalString ���� ���ڿ�. <code>null</code>�� ����.
	 * @param	resultLength ��ǥ ����
	 * @return	�־��� ���̸�ŭ ������ �߰��� ���ڿ�. �ش� ���ڿ��� ��ǥ ���̸�
	 *        	�ʰ��� ��쿡�� �ش� ���ڿ��� �״�� ����.
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
	//  ��ȭ ���� ���� ��ƿ.
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
	 *  �ݰ����ڸ� ���� ���ڷ� ��ȯ�Ѵ�.
	 */
	public static String toFullChar(String src) {
		// �Էµ� ��Ʈ���� null �̸� null �� ����
		if (src == null) {
			return null;
		}
		// ��ȯ�� ���ڵ��� �׾Ƴ��� StringBuffer �� �����Ѵ�
		StringBuffer strBuf = new StringBuffer();
		char c = 0;

		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);

			//�����̰ų� Ư�� ���� �ϰ��.
			if ((c >= 0x21) && (c <= 0x7e)) {
				c += 0xfee0;
			} else if (c == 0x20) {
				c = 0x3000;
			}

			// ���ڿ� ���ۿ� ��ȯ�� ���ڸ� �״´�
			strBuf.append(c);
		}
		return strBuf.toString();

	}

	/**
	 *  �������ڸ� �ݰ� ���ڷ� �����Ѵ�.
	 */
	public static String toHalfChar(String src) {
		StringBuffer strBuf = new StringBuffer();
		char c = 0;

		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);

			//�����̰ų� Ư�� ���� �ϰ��.
			if ((c >= 0xff01) && (c <= 0xff5e)) {
				c -= 0xfee0;
			} else if (c == 0x3000) {
				c = 0x20;
			}

			// ���ڿ� ���ۿ� ��ȯ�� ���ڸ� �״´�
			strBuf.append(c);
		}

		return strBuf.toString();
	}

	/*
	 * �־��� String�� �޺κ� n byte�� '*'�� �����Ѵ�.
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
	* @param s ����ŷ�� ��� ���ڿ�
	* @param pos ����ŷ ��ġ
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
	 * �ֹε�� �� �ڸ� ��, ù��° �ڸ����� ������ 6�ڸ��� Asterisk(*) ó�� �� '-'�� ÷���Ѵ�.
	 *
	 * @param str (-) �Է��� ���� �ֹ�/����� ��Ϲ�ȣ
	 * @return ����(Asterisk)�� �ֹε�Ϲ�ȣ
	 * @author ä�μ��񽺺� ������
	 */
	public static String regnNoAst4(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.delDash(str);
		if (str.length() == 13) {
			return NssStringUtils.regnNoAst(str, 6); //�ֹι�ȣ 123456-1******
		} else if (str.length() == 10) {
			return NssStringUtils.regnNoAst(str, 5); //����ڹ�ȣ 123-45-*****
		} else if (str.length() == 9) {
			return NssStringUtils.regnNoAst(str, 4); // ���ǹ�ȣ YS123****
		} else {
			return NssStringUtils.regnNo(str);
		}
	}

	/**
	 * ���� 2��°�ڸ�, ���θ� 3��° �ڸ��� ���ؼ� '*'ó���Ѵ�.
	 *
	 * @param String Ÿ���� ����, ���θ�
	 * @return '*' ó���� ����, ���θ�
	 * @author ä�μ��񽺺� ������
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
	 * ���ڿ� Ư���ε��� ���Ŀ� ���ؼ� '*'ó���Ѵ�.
	 *
	 * @param StringŸ�� ���ڿ�
	 * @return Ư���ε��� ����, '*'ó���� StringŸ�� ���ڿ�
	 * @author ä�μ��񽺺� ������
	 */
	public static String transAstAfterIdx(String str, int rLen) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String temp = null;
		String szAst = "********************";
		int len = str.length(); //��ü ���ڿ�����
		temp = str.substring(0, rLen) + szAst.substring(0, len - rLen); //�����ڰ� ������ ���̸�ŭ�� �����ְ�, ������ �κп� ���ؼ��� '*'ó��

		return temp;
	}

	/**
	 * ���¹�ȣ 5��°�ڸ����� ����ŷ '*' ó�� ��, ������ ���¹�ȣ ������ �����ش�.
	 *
	 * @param String Ÿ���� ���¹�ȣ
	 * @return '*' ó���� ���¹�ȣ
	 * @author ���ڱ����� ����
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
	 * �ڵ尪�� �ڵ������ ��ȯ�Ѵ�.
	 *
	 * 2017.04.26
	 * ������ ��������
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
	 * �ֹι�ȣ : 13�ڸ� �� ������ϰ� ������ ������ �� 6�ڸ� ����ŷ
	 * @param str
	 * @param hypen
	 * @return '*' ó���� �ֹι�ȣ
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
	 * �ֹι�ȣ : 13�ڸ� �� ������ϰ� ������ ������ �� 6�ڸ� ����ŷ
	 * @param str
	 * @param hypen
	 * @return '*' ó���� �ֹι�ȣ
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
	 * ���¹�ȣ 5��°�ڸ����� ����ŷ '*' ó�� ��, ������ ���¹�ȣ ������ �����ش�.
	 *
	 * (1) 10�ڸ� ���¹�ȣ : �� 6�ڸ� ����ŷ (14220-64601  14220-*****)
	 * (2) 10�ڸ� �ʰ� ���¹�ȣ : �� 7�ڸ� ����ŷ (1422-064-60123456  142-20-646***-****)
	 *
	 * @param String Ÿ���� ���¹�ȣ
	 * @return '*' ó���� ���¹�ȣ
	 * @author 90145784
	 */
	public static String transAcctMaskWithHypen(String str) {

		if ((str == null) || (str.length() <= 0)) {
			return str;
		}

		str = NssStringUtils.trim(str);

		String temp = null;
		int len = str.length();
		if (str.length() > 11) { // 12 �ڸ� �̻�
			temp = str.substring(0, len - 7).concat("*******");
			temp = temp.substring(0, 4).concat("-").concat(temp.substring(4, 7)).concat("-").concat(temp.substring(7, len));
		} else if (str.length() == 11) { // 11 �ڸ�
			temp = str.substring(0, 4).concat("-").concat(str.substring(4, 7)).concat("-").concat("****");
		} else { // 10 �ڸ�
			temp = str.substring(0, 4).concat("*").concat("-").concat("*****");
		}
		return temp;
	}

	/**
	 * �ѱ� - ù ���� ���� {(��ü�̸�����-1)/2} �ڸ�
	 * (ȫ�浿  ȫ*�� / �������  ��*���� / ������ 2�ڸ��� ��� ����ŷ ������)
	 * @param value
	 * @return '*' ó���� ����
	 * @author 90145585
	 */
	public static String transNameMask(String value) {
		StringBuffer val = new StringBuffer("");

		if ((value == null) || "".equals(value)) {
			return "";
		}

		else {
			/*
			 * ���� ����ŷ ù��°�� ���������ڿܿ��� �Ϻ� ����ŷ ó�� ����ŷ��� �� : �׽�Ʈ -> ��*Ʈ / ���׽�Ʈ -> ��*��Ʈ
			 */
			int len = value.length();
			int lencnt = 1;

			val.append(value.substring(0, 1));

			// �������� ����ŷ ��� �� ó������[2018.03.15 ����]
			for (lencnt = 0; lencnt < ((len - 1) / 2); lencnt++) {
				val.append("*");
			}

			val.append(value.substring(++lencnt, len));

		}
		return val.toString();
	}

	/**
	 * �Ҽ������� ���� ��������
	 * (ex: 12345.67 => 67)     value:12345.67
	 * (ex: 12345.67 => 6700)   value:12345.67
	 * @param value
	 * @return '*' ó���� ����
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

	/** �޴�����ȣ�� ü�迡 ���� Ư����ġ�� '-'�� ÷���Ѵ�. */
	public static String hpNo(String str, String hcd) {
		str = NssStringUtils.null2void(str).trim(); //null ó�� �� trim

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
	 * ��������.
	 * <pre>
	 * 	��û�������� ������ ����.
	 * </pre>
	 * @param bound ���� ���� ����. ex) 100 : 0~99
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
	 * script tag ����
	 * <pre>
	 * 	script tag ����
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
	 * @param enc ���ڵ��� URL
	 * @return ���ڵ��� URL
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
	 * 	null, ���� üũ
	 * </pre>
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * ī��ǥ������� ���� ��ȯ
	 * <pre>
	 * 	ex) ETC_AMT -> etcAmt
	 * </pre>
	 * @param string
	 * @return ī��ǥ��� ������
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
	 * ����Ʈ ����
	 * <pre>
	 * 	����Ʈ ����
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
				curCode = NssStringUtils.null2void(element[0]); // �ڵ�
				curName = NssStringUtils.null2void(element[1]); // �̸�

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
				sortList[i][0] = ((String[]) tmpList.get(i))[0]; // �ڵ�
				sortList[i][1] = ((String[]) tmpList.get(i))[1]; // �̸�
			}
		}

		return sortList;
	}

	/**
	 * ����Ʈ ���� ��, JsonArray�� ����
	 * <pre>
	 * 	����Ʈ ���� ��, JsonArray�� ����
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
				curCode = NssStringUtils.null2void(element[0]); // �ڵ�
				curName = NssStringUtils.null2void(element[1]); // �̸�

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
				sortList[i][0] = ((String[]) tmpList.get(i))[0]; // �ڵ�
				sortList[i][1] = ((String[]) tmpList.get(i))[1]; // �̸�
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
	 * �ؽ� �ڵ� ��ȯ
	 * <pre>
	 * 	�ؽ� �ڵ� ��ȯ
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
	 * �ݾ��е�ó��
	 * <pre>
	 * 	�ݾװ��� �޾Ƽ� left padding 0 ó��
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
	 * ���ھ��� �������� �µ��� ����
	 * ����ó��,�����̽� 2���̻� ����,Ư������(����ǥ)����
	 * @param han �ѱ� string
	 * @return ������ �ѱ� ���� ����� String
	 */
	public static String convHan(String han) {
		String cHan = "";
		cHan = toFullChar(han);
		cHan = cHan.replaceAll("\\uA3BF", "");
		cHan = cHan.replaceAll("\\u3000{2,}", "\\\u3000");
		return trim(cHan);
	}

	/**
	 * Object���� BigDecimal�� ���, Integer�� ��ȯ �� ����
	 * null�� ���, 0 ����
	 * @param param
	 * @return �Է°�(Integer), null�ΰ�� 0
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
	 * String ���� Ư�����̸�ŭ substring
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
	 * 	���๮��(\r\n)�� <BR> ���ڷ� ��ȯ
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
     * ��簪 ���ڿ� ��ȯ
     * <pre>
     * 	��簪 ���ڿ� ��ȯ
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
	 * ���ڼ��� �� ��ȯ �ȵǴ� ���ڿ� ��ȯ(���� -> �뽬)
	 * <pre>
	 * 	���ڼ��� �� ��ȯ �ȵǴ� ���ڿ� ��ȯ(���� -> �뽬)
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
	 * index == len �� ��� ó���� �� �Ǵ� ������ ����
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
     		if (str.charAt(index++) < 256) { // 1����Ʈ ���ڶ��...
        			cnt++; // ���� 1 ����
     		} else {
           			cnt += 2; // ���� 2 ����
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
		return str.replaceAll("��", " ");
	}

	public static String getFundSTSStr(String header, String sts) {

		String tmpStr = "";

		if("10".equals(sts)) {
			tmpStr = header + " ����";
		} else if("20".equals(sts)) {
			tmpStr = header + " �����";
		} else if("30".equals(sts)) {
			tmpStr = header + " �Ϸ�";
		} else if("90".equals(sts)) {
			tmpStr = header + " ���";
		} else if("70".equals(sts)) {
			tmpStr = "ó�� ����";
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
			dRst = ((total - prn) * 100d) / prn; // ���ͷ� = (( �򰡱ݾ� - ���ڿ��� ) * 100) / ���ڿ���
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
	 * XSS sciprtexe �׸� ����
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
	 * ���ڿ� ����Ʈ ���� ���
	 * @param str ���ڿ�
	 * @param charset ���ڵ� ���ڼ�
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
	 * ���ڿ� ����Ʈ ���� �ڸ���
	 * 
	 * UTF-8 ����
	 * �ѱ� 3Byte | ���ĺ�, ��ҹ���, ����, ���� 1Byte�� ����
	 * 
	 * @param str ���ڿ�
	 * @param startBytes ���� ����Ʈ
	 * @param endBytes �� ����Ʈ
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
	 * 	str���ڿ��� 0�� size��ŭ �տ� �־���
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
	 * �������Ǹ� ���ؼ� ���߿��� ����
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
	 * �������Ǹ� ���ؼ� ���߿��� ����
	 * @param map
	 * @return
	 */
	public static String customerToString(BizCustomer customer) {
		String _result = "";
		String[] customerGetMethodNm = {
				// ���� - �ű��߰� ����
				"getItCsNo", "getBizPeItCsNo", "getPsBzNo", "getBizPeRgsNo", "getCUS_DSCD", "getBRDT", "getJNPE_AGE",
				"getMNW_DSCD", "getNTVF_DSCD", "getNRSR_YN", "getELT_LIE_PRVN_XT_YN", "getBR_CHID_SECRD_HLDG_YN",
				"getOBST_YN"
				// �߰� 20171231
				, "getMdAgrYn", "getEbnkFstUsgDt"

				// ���� - ASIS ����
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

				// ���� �� �����ŷ��밡�� ����
				, "getEml", "getTelNO", "getHpNo"

		};
		String[] customerSetMethodNm = {
				// ���� - �ű��߰� ����
				"setItCsNo", "setBizPeItCsNo", "setPsBzNo", "setBizPeRgsNo", "setCUS_DSCD", "setBRDT", "setJNPE_AGE",
				"setMNW_DSCD", "setNTVF_DSCD", "setNRSR_YN", "setELT_LIE_PRVN_XT_YN", "setBR_CHID_SECRD_HLDG_YN",
				"setOBST_YN"
				// �߰� 20171231
				, "setMdAgrYn", "setEbnkFstUsgDt"

				// ���� - ASIS ����
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

				// ���� �� �����ŷ��밡�� ����
				, "setEml", "setTelNO", "setHpNo"

		};
		String[] customerMethodTitle = {
				// ���� - �ű��߰� ����
				"���հ���ȣ", "��������հ���ȣ", "�ֹλ���ڵ�Ϲ�ȣ", "����ڵ�Ϲ�ȣ", "����ڱ���<br/>1:����,2:���λ����,3:����", "�������", "�����ڿ���", "���౸���ڵ�",
				"���ܱ��α����ڵ�", "������ڿ���", "���ڻ��������ܿ���", "���ں���ī�庸������", "��ֿ���"
				// �߰� 20171231
				, "��ü���ǿ���", "���ڹ�ŷ���ʻ������"

				// ���� - ASIS ����
				, "�̿���ID", "�̿��ڸ�", "�����ŷ��뿩��", "��Ŭ������뿩��", "����Ʈ�л�뿩��", "���ι�ŷ��뿩��", "����ũ�λ�뿩��", "���л�뿩��", "��ȸ���񽺻�뿩��",
				"����ũ��뿩��", "����Ϲ�ŷ��뿩��", "�����Ļ�뿩��", "VIP��뿩��", "�츮ĳ�û�뿩��", "������»�뿩��", "���ȼ��ܹ�ȣ", "������ü��", "������������",
				"�̸��ϼ��ſ���", "������ �Ϸù�ȣ", "�����Ļ�뿩��", "���������", "�������ð�", "OTP ����", "�Ա����������ڵ�", "�̿���PC��Ͽ���", "�̿���PC�ɼǿ���",
				"���¹�ȣ", "OTP�����", "������", "ī�� ����� �������", "���λ��̹���뿩��", "������̹���뿩��", "���ȼ���", "������ serial", "������ �����ü",
				"Ư����������뿩��", "���λ���ڵ�Ͽ���", "������������", "2ä����������", "�������̺�Ʈ��󿩺�", "�ؿ�IP������������", "���2ä�κ����ȿ��", "�ڵ����������񽺽�û����",
				"����SMS��Ͽ���", "����ں�й�ȣ������", "��ü���񽺿���", "�ڱ���ü�ŷ����ѿ���", "OTP����ڵ�", "OTP�Ϸù�ȣ", "������������ȸ��뿩��", "�׷���������뿩��",
				"2������ ���ܰ�(�׷������� ����)", "����Ʈ����뿩��", "��üSMS���������ڵ�", "��������������", "�����̹����ĺ���ID", "����Ȯ�ι����Է³���", "��ȣȭ�� IP",
				"��ü�ѵ�����󿩺�", "����Ʈ���������", "�޸鿹�ݺ�������", "����űԺ�������", "���Դ���ο���", "�ݾ״��������", "�����ŷ��뿩��", "������ü��Ͽ���", "",
				"����ڱ���", "", "", "", "��ü��й�ȣ�̵�Ͽ���", "������ü����������ȸ��뿩��", "��ŷ ����� ����"

				// Object return
				, "temp �� customer ����", "��������", "����� ���� ����"

				// ���� �� �����ŷ��밡�� ����
				, "�̸����ּ�", "��ȭ��ȣ", "�ڵ�����ȣ"

		};
//		String[] bizCustomerGetMethodNm = {
//				// ���� �� �����ŷ��밡�� ����
//				"hasBizCusInf"
//				// ���λ����
//				, "getCoCusMstID", "getCoCusMstNm", "getCoCusNm", "getPsRgNo", "getUserSts", "getItCsNoByCust",
//				"getTsAcm", "getOneDayTsLmt", "getDeptNm", "getJbLvlNm", "getHP", "getMstHP", "getAplAutUserCnt",
//				"hasApvAut", "isPsnBiz", "isMaster", "isSnglUser", "getTelNO"/* ,"getUserAplAut" */ , "getCoCusNcm",
//				"isSuperMaster" };
//		String[] bizCustomerSetMethodNm = {
//				// ���� �� �����ŷ��밡�� ����
//				"setBizCusInfYN"
//				// ���λ����
//				, "setCoCusMstID", "setCoCusMstNm", "setCoCusNm", "setPsRgNo", "setUserSts", "setItCsNoByCust",
//				"setTsAcm", "setOneDayTsLmt", "setDeptNm", "setJbLvlNm", "setHP", "setMstHP", "setAplAutUserCnt",
//				"setApvAutYN", "setPsnBizYN", "setMasterYN", "setSnglUserYN", "setTelNO"/* ,"setUserAplAut" */ ,
//				"setCoCusNcm", "setSuperMaster" };
//		String[] bizCustomerMethodTitle = {
//				// ���� �� �����ŷ��밡�� ����
//				"��������� ���� ����"
//				// ���λ����
//				, "�������ǥID", "�̿��ڸ�(��������� �̸�)", "�������", "�Ǹ��ȣ(����/���λ����)<br/>����ڵ�Ϲ�ȣ(����)", "����ڻ��¹�ȣ", "������ ���հ���ȣ",
//				"��ü������", "1����ü�ѵ��ݾ�", "�μ���", "���޸�", "�ڵ�����ȣ", "�޴�����ȣ(��������� �̸�)", "���������� �ִ� ����ڼ�", "������ѿ���", "���λ���ڿ���",
//				"������ ����", "�ܵ������ ����", "��ȭ��ȣ"/* ,"������ȣ" */ , "����Ī��", "���۸����Ϳ���" };
		for(int i=0; i<customerGetMethodNm.length; i++){
			try {
				Method method = wbf.web.Customer.class.getMethod(customerGetMethodNm[i]);
				if("getItCsNo".equals(customerGetMethodNm[i])){
					_result += "\n ROKYUNG================================���� - �ű��߰�====================================";
				} else if("getUserID".equals(customerGetMethodNm[i])){
					_result += "\n ROKYUNG================================���� - ASIS ����====================================";
				} else if("getEml".equals(customerGetMethodNm[i])){
					_result += "\n ROKYUNG================================���� �� �����ŷ��밡�� ����====================================";
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
						||"setTsLmtReduct".equals(customerSetMethodNm[i])			//Y�� Setting�� 'true'�� Return(setTsLmtReduct(String) -> boolean isTsLmtReduct())
						||"setDormantDepositHolding".equals(customerSetMethodNm[i])	//Y�� Setting�� 'true'�� Return(setDormantDepositHolding(String) -> boolean hasDDAcct())
						||"setSimpleNewHolding".equals(customerSetMethodNm[i]) 		//Y�� Setting�� 'true'�� Return(setSimpleNewHolding(String) -> boolean hasSNAcct())
						||"setDelayTs".equals(customerSetMethodNm[i]) 				//Y�� Setting�� 'true'�� Return(setDelayTs(String) -> boolean isDelayTs())
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