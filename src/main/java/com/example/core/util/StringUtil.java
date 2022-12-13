package com.example.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.json.simple.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
    /**
     * Grid 의 ImageText 컴포넌트에서 TEXT 만 추출한다. .<br>
     * return Object(TEXT 를 Return)
     * @param map 의 Object Data(map.get("XXX"))
     * @return Object
     */
    public static Object getCellValueGridImageText(Object mapData) {
        return new JSONObject((Map) mapData).get("text");
    }

    /**
     * str에서 sub의 반복 횟수를 반환 합니다.<br>
     * return matching sub string count
     * @param str  확인할 문자열 null이 들어 올 수도 있습니다.
     * source string
     * @param sub  count 할 부분 문자열 null이 들어 올 수도 있습니다.
     * target String
     * @return int
     */
    public static int countMatches(String str, String sub) {
        return StringUtils.countMatches(str, sub);
    }

    /**
     * 문자열이 비었거나 null인지 확인 합니다.
     * NOTE: white space에 대한 검사는 하지 않습니다.<br>
     * return true when String is null or empty<br>
     * @param str  확인할 문자열 null일 수 있습니다.
     * @return 문자열이 null이거나 빈문자열 일 경우 {@code true}를 반환 합니다.
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 문자열이 비었거나 null인지 확인 합니다.<br>
     * return false when String is null or empty
     * @param str  확인할 문자열 null일 수 있습니다.
     * @return 문자열이 null 또는 공백이 아닐 경우 {@code true}를 반환 합니다.
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * String Array를 한 문자열로 합침니다.
     * 처음과 끝은 separator가 추가 되지 않습니다.
     * separator null 인 경우 빈문자열과 같이 취급 됩니다.<br>
     *
     * concat string array
     * Beginning and end of the String will not be added separator.
     *  separator null will be treated as empty string.
     * @param array  한 문자열로 합쳐질 배열
     * source
     * @param separator 구분자, null일 경우 빈문자열로 취급
     * seprator
     * @return String
     */
    public static String combinationArr(String[] array, String separator) {
        return StringUtils.join(array, separator);
    }

    /**
     * 왼쪽에 문자열 길이가 파라미터의 길이가 될때 까지 repChar를 채웁니다.<br>
     * add left with given character and length
     * @param str  패딩을 할 대상 문자열
     * source
     * @param length  문자열 길이
     * length
     * @param repChar  채울 물자열
     * fill character
     * @return String
     */
    public static String lpad(String str, int length, String repChar) {
        return StringUtils.leftPad(str, length, repChar);
    }

    /**
     * 오른쪽에 문자열 길이가 length가 될때 까지 repChar를 채웁니다.<br>
     * add right with given character and length
     * @param str  패딩을 할 대상 문자열
     * source
     * @param length  문자열 길이
     * length
     * @param repChar  채울 물자열
     * fill character
     * @return String
     */
    public static String rpad(String str, int length, String repChar) {
        return StringUtils.rightPad(str, length, repChar);
    }

    /**
     * str을 separatorChars 문자열로 잘라 문자열 배열을 만듭니다.<br>
     * str to string array with separator
     * @param str  자를 문자열
     * @param separatorChars  구분자
     * @return String[]
     */
    public static String[] strToArray(String str, String separatorChars) {
        return StringUtils.split(str, separatorChars);
    }

    /**
     * compare String
     * @param foo
     * @param bar
     * @return when same StringValue then reutrn true else false
     */
    public static boolean equals(String foo, String bar) {
        return StringUtils.equals(foo, bar);
    }

    /**
     * compare String
     * @param foo
     * @param bar
     * @return when not same StringValue then reutrn true else false
     */
    public static boolean notEquals(String foo, String bar) {
        return !StringUtils.equals(foo, bar);
    }

    /**
     * String foo의 값이 "true" 이면 fos "false" 이면 neg 둘다 아니면 foo를 반환<br>
     * return fos's value when foo's value is "true"
     * return neg's value when foo's value is "false"
     * else return foo's value
     * @param foo String
     * @param pos String
     * @param neg String
     * @return String
     * @throws Exception
     */
    public static String repToStr(String foo, String pos, String neg) {
        if (StringUtils.isEmpty(foo) || StringUtils.equals(foo, "null")) {
            return "";
        }
        if (StringUtils.equals(foo, "true")) {
            return pos;
        }
        if (StringUtils.equals(foo, "false")) {
            return neg;
        }
        return foo;
    }

    /**
     * 무작위 문자열을 randomStringLength 만큼 type으로 생성<br>
     * create random string
     * @param randomStringLength int 무작위 문자열을 만들 길이
     * string length
     * @param type String N: 숫자만, S: 문자열만, NS: 문자,숫자 혼합
     * N: only number, S:  only character, NS: number or character
     * @return String
     */
    public static String getRandomString(int randomStringLength, String type) {
        if ("N".equals(type)) {
            return RandomStringUtils.randomNumeric(randomStringLength);
        }
        if ("S".equals(type)) {
            return RandomStringUtils.randomAlphabetic(randomStringLength);
        }
        if ("NS".equals(type)) {
            return RandomStringUtils.randomAlphanumeric(randomStringLength);
        }
        return null;
    }

    /**
     * 문자열 중에 del문자열 제거<br>
     * remove del in str
     * @param str String
     * @param del String
     * @return String
     */
    public static String ignoreSeparator(String str, String del) {
        return StringUtils.replace(str, del, "");
    }

    /**
     * exception 정보를 String 형태로 return<br>
     * return exception infomation as String
     * @param throwable Throwable
     * @return String
     */
    public static String getStackTrace(Throwable throwable) {
        return ExceptionUtils.getStackTrace(throwable);
    }

    /**
     * 문자 코드
     * org.owasp.esapi.codecs.HTMLEntityCodec 에서 추출해서 사용
     * http://owasp-esapi-java.googlecode.com/svn/trunk/src/main/java/org/owasp/esapi/codecs/HTMLEntityCodec.java
     * @param str
     * @return
     */
    public static String replaceInjectionString(String str){
        Map<Character, String> map = new HashMap<Character,String>(252);
        map.put((char)34,       "\"");        /* quotation mark */
        map.put((char)39,       "\'");        /* single quotation mark */
//        map.put((char)38,       "＆");        /* & mark */
        map.put((char)60,       "&lt;");        /* less-than sign */
        map.put((char)62,       "&gt;");        /* greater-than sign */

        if(str == null) return null;
        map.keySet();
        for (Character ch : map.keySet()) {
            str = str.replaceAll(ch.toString(), map.get(ch));
        }

        /*
        if (str.toUpperCase().indexOf("--") != -1) {
            str = replace(str, "--", "－－");
        }

        if (stringValue.toUpperCase().indexOf(";") != -1) {
            stringValue = replace(stringValue, ";", "；");
        }

        if (stringValue.toUpperCase().indexOf("%") != -1) {
            stringValue = replace(stringValue, "%", "％");
        }
        */

        if (str.toUpperCase().indexOf("SYS.DATABASE_NAME") != -1) {
            int instr_location = str.toUpperCase().indexOf("SYS.DATABASE_NAME");
            String temp_str = str.substring(instr_location, instr_location + 17);
            str = StringUtil.replace(str, temp_str, "");
        }

        if (str.toUpperCase().indexOf("<XMP") != -1) {
            int instr_location = str.toUpperCase().indexOf("<XMP");
            String temp_str = str.substring(instr_location, instr_location + 4);
            str = StringUtil.replace(str, temp_str, "<ＸＭＰ");
        }

        if (str.toUpperCase().indexOf("<JAVASCRIPT") != -1) {
            int instr_location = str.toUpperCase().indexOf("<JAVASCRIPT");
            String temp_str = str.substring(instr_location, instr_location + 11);
            str = replace(str, temp_str, "<ＪＡＶＡＳＣＲＩＰＴ");
        }

        if (str.toUpperCase().indexOf("<IFRAME") != -1) {
            int instr_location = str.toUpperCase().indexOf("<IFRAME");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "<ＩＦＲＡＭＥ");
        }

        if (str.toUpperCase().indexOf("ALERT") != -1) {
            int instr_location = str.toUpperCase().indexOf("ALERT");
            String temp_str = str.substring(instr_location, instr_location + 5);
            str = replace(str, temp_str, "ＡＬＥＲＴ");
        }

        if (str.toUpperCase().indexOf("COOKIE") != -1) {
            int instr_location = str.toUpperCase().indexOf("COOKIE");
            String temp_str = str.substring(instr_location, instr_location + 6);
            str = StringUtil.replace(str, temp_str, "ＣＯＯＫＩＥ");
        }

        if (str.toUpperCase().indexOf("VBSCRIPT") != -1) {
            int instr_location = str.toUpperCase().indexOf("VBSCRIPT");
            String temp_str = str.substring(instr_location, instr_location + 8);
            str = StringUtil.replace(str, temp_str, "ＶＢＳＣＲＩＰＴ");
        }

        if (str.toUpperCase().indexOf("APPLET") != -1) {
            int instr_location = str.toUpperCase().indexOf("APPLET");
            String temp_str = str.substring(instr_location, instr_location + 6);
            str = StringUtil.replace(str, temp_str, "ＡＰＰＬＥＴ");
        }

        /*
        if (str.toUpperCase().indexOf("<EMBED") != -1) {
            int instr_location = str.toUpperCase().indexOf("<EMBED");
            String temp_str = str.substring(instr_location, instr_location + 6);
            str = StringUtil.replace(str, temp_str, "<ＥＭＢＥＤ");
        }
        */

        if (str.toUpperCase().indexOf("OBJECT") != -1) {
            int instr_location = str.toUpperCase().indexOf("OBJECT");
            String temp_str = str.substring(instr_location, instr_location + 6);
            str = StringUtil.replace(str, temp_str, "ＯＢＪＥＣＴ");
        }

        if (str.toUpperCase().indexOf("BGSOUND") != -1) {
            int instr_location = str.toUpperCase().indexOf("BGSOUND");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "ＢＧＳＯＵＮＤ");
        }

        if (str.toUpperCase().indexOf("ONBLUR") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONBLUR");
            String temp_str = str.substring(instr_location, instr_location + 6);
            str = StringUtil.replace(str, temp_str, "ＯＮＢＬＵＲ");
        }

        if (str.toUpperCase().indexOf("ONCHANGE") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONCHANGE");
            String temp_str = str.substring(instr_location, instr_location + 8);
            str = StringUtil.replace(str, temp_str, "ＯＮＣＨＡＮＧＥ");
        }

        if (str.toUpperCase().indexOf("ONCLICK") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONCLICK");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "ＯＮＣＬＩＣＫ");
        }

        if (str.toUpperCase().indexOf("ONDBLCLICK") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONDBLCLICK");
            String temp_str = str.substring(instr_location, instr_location + 10);
            str = StringUtil.replace(str, temp_str, "ＯＮＤＢＣＬＩＣＫ");
        }

        if (str.toUpperCase().indexOf("ONERROR") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONERROR");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "ＯＮＥＲＲＯＲ");
        }

        if (str.toUpperCase().indexOf("ONFOCUS") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONFOCUS");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "ＯＮＦＯＣＵＳ");
        }

        if (str.toUpperCase().indexOf("ONLOAD") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONLOAD");
            String temp_str = str.substring(instr_location, instr_location + 6);
            str = StringUtil.replace(str, temp_str, "ＯＮＬＯＡＤ");
        }

        if (str.toUpperCase().indexOf("ONMOUSE") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONMOUSE");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "ＯＮＭＯＵＳＥ");
        }

        if (str.toUpperCase().indexOf("ONSCROLL") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONSCROLL");
            String temp_str = str.substring(instr_location, instr_location + 8);
            str = StringUtil.replace(str, temp_str, "ＯＮＳＣＲＯＬＬ");
        }

        if (str.toUpperCase().indexOf("ONSUBMIT") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONSUBMIT");
            String temp_str = str.substring(instr_location, instr_location + 8);
            str = StringUtil.replace(str, temp_str, "ＯＮＳＵＢＭＩＴ");
        }

        if (str.toUpperCase().indexOf("ONUNLOAD") != -1) {
            int instr_location = str.toUpperCase().indexOf("ONUNLOAD");
            String temp_str = str.substring(instr_location, instr_location + 8);
            str = StringUtil.replace(str, temp_str, "ＯＮＵＮＬＯＡＤ");
        }

        if (str.toUpperCase().indexOf("<SCRIPT") != -1) {
            int instr_location = str.toUpperCase().indexOf("<SCRIPT");
            String temp_str = str.substring(instr_location, instr_location + 7);
            str = StringUtil.replace(str, temp_str, "<ＳＣＲＩＰＴ");
        }

        if (str.toUpperCase().indexOf("</SCRIPT") != -1) {
            int instr_location = str.toUpperCase().indexOf("</SCRIPT");
            String temp_str = str.substring(instr_location, instr_location + 8);
            str = StringUtil.replace(str, temp_str, "</ＳＣＲＩＰＴ");
        }

        /*
        if (str.toUpperCase().indexOf("<DIV") != -1) {
            int instr_location = str.toUpperCase().indexOf("<DIV");
            String temp_str = str.substring(instr_location, instr_location + 4);
            str = StringUtil.replace(str, temp_str, "<ＤＩＶ");
        }

        if (str.toUpperCase().indexOf("<IMG") != -1) {
            int instr_location = str.toUpperCase().indexOf("<IMG");
            String temp_str = str.substring(instr_location, instr_location + 4);
            str = StringUtil.replace(str, temp_str, "<ＩＭＧ");
        }

        if (str.toUpperCase().indexOf("&#") != -1) {
            int instr_location = str.toUpperCase().indexOf("&#");
            String temp_str = str.substring(instr_location, instr_location + 2);
            str = replace(str, temp_str, "＆＃");
        }
        */

        return str;
    }

    public static String replaceInjectionRichTextEdit(String str){
        Map<Character, String> map = new HashMap<Character,String>(252);
        map.put((char)39,       "＇");        /* single quotation mark */

        if(str == null) return null;
        map.keySet();
        for (Character ch : map.keySet()) {
            str = str.replaceAll(ch.toString(), map.get(ch));
        }

        String stringValue = str;

        if (stringValue.toUpperCase().indexOf("--") != -1) {
            stringValue = replace(stringValue, "--", "－－");
        }

        if (stringValue.toUpperCase().indexOf("%") != -1) {
            stringValue = replace(stringValue, "%", "％");
        }

        if (stringValue.toUpperCase().indexOf("JAVASCRIPT") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("JAVASCRIPT");
            String temp_str = stringValue.substring(instr_location, instr_location + 10);
            stringValue = replace(stringValue, temp_str, "ＪＡＶＡＳＣＲＩＰＴ");
        }
        if (stringValue.toUpperCase().indexOf("JSCRIPT") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("JSCRIPT");
            String temp_str = stringValue.substring(instr_location, instr_location + 7);
            stringValue = replace(stringValue, temp_str, "ＪＳＣＲＩＰＴ");
        }
        if (stringValue.toUpperCase().indexOf("VBSCRIPT") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("VBSCRIPT");
            String temp_str = stringValue.substring(instr_location, instr_location + 8);
            stringValue = replace(stringValue, temp_str, "ＶＢＳＣＲＩＰＴ");
        }
        if (stringValue.toUpperCase().indexOf("SCRIPT") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("SCRIPT");
            String temp_str = stringValue.substring(instr_location, instr_location + 6);
            stringValue = replace(stringValue, temp_str, "ＳＣＲＩＰＴ");
        }
        if (stringValue.toUpperCase().indexOf("IFRAME") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("IFRAME");
            String temp_str = stringValue.substring(instr_location, instr_location + 6);
            stringValue = replace(stringValue, temp_str, "ＩＦＲＡＭＥ");
        }
        if (stringValue.toUpperCase().indexOf("FRAME") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("FRAME");
            String temp_str = stringValue.substring(instr_location, instr_location + 5);
            stringValue = replace(stringValue, temp_str, "ＦＲＡＭＥ");
        }
        if (stringValue.toUpperCase().indexOf("EXPRESSION") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("EXPRESSION");
            String temp_str = stringValue.substring(instr_location, instr_location + 10);
            stringValue = replace(stringValue, temp_str, "ＥＸＰＲＥＳＳＩＯＮ");
        }
        if (stringValue.toUpperCase().indexOf("ALERT") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("ALERT");
            String temp_str = stringValue.substring(instr_location, instr_location + 5);
            stringValue = replace(stringValue, temp_str, "ＡＬＥＲＴ");
        }
        if (stringValue.toUpperCase().indexOf("OPEN") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("OPEN");
            String temp_str = stringValue.substring(instr_location, instr_location + 4);
            stringValue = replace(stringValue, temp_str, "ＯＰＥＮ");
        }
        if (stringValue.toUpperCase().indexOf("&#") != -1) {
            int instr_location = stringValue.toUpperCase().indexOf("&#");
            String temp_str = stringValue.substring(instr_location, instr_location + 2);
//			stringValue = replace(stringValue, temp_str, "＆＃");
        }

        return stringValue;
    }
/*
    public static Map<String, Object> forTransactionQuery(Map<String, Object> param) { //throws UserInfoNotFoundException {

        Map<String, Object> resultParam = new HashMap<String, Object>();

        Iterator<Entry<String, Object>> it = param.entrySet().iterator();

        String fieldID = "";
        Object fieldValue = "";
        String stringValue = "";

        while (it.hasNext()) {

            Entry<String, Object> entry = it.next();

            fieldID = toEmpty(entry.getKey());
            fieldValue = param.get(entry.getKey());

            stringValue = "";
            if (fieldValue instanceof String) {
                //stringValue = encoder.encodeForSQL(oracleCodec, toEmpty((String)fieldValue));
                stringValue = toEmpty((String)fieldValue);
            }

            if (!isEmpty(stringValue)) {
                //ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ
                if (stringValue.toUpperCase().indexOf("--") != -1) {
                    stringValue = replace(stringValue, "--", "－－");
                }

                if (stringValue.toUpperCase().indexOf(";") != -1) {
                    stringValue = replace(stringValue, ";", "；");
                }

                if (stringValue.toUpperCase().indexOf("%") != -1) {
                    stringValue = replace(stringValue, "%", "％");
                }

                if (stringValue.toUpperCase().indexOf("JAVASCRIPT") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("JAVASCRIPT");
                    String temp_str = stringValue.substring(instr_location, instr_location + 10);
                    stringValue = replace(stringValue, temp_str, "ＪＡＶＡＳＣＲＩＰＴ");
                }
                if (stringValue.toUpperCase().indexOf("JSCRIPT") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("JSCRIPT");
                    String temp_str = stringValue.substring(instr_location, instr_location + 7);
                    stringValue = replace(stringValue, temp_str, "ＪＳＣＲＩＰＴ");
                }
                if (stringValue.toUpperCase().indexOf("VBSCRIPT") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("VBSCRIPT");
                    String temp_str = stringValue.substring(instr_location, instr_location + 8);
                    stringValue = replace(stringValue, temp_str, "ＶＢＳＣＲＩＰＴ");
                }
                if (stringValue.toUpperCase().indexOf("SCRIPT") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("SCRIPT");
                    String temp_str = stringValue.substring(instr_location, instr_location + 6);
                    stringValue = replace(stringValue, temp_str, "ＳＣＲＩＰＴ");
                }
                if (stringValue.toUpperCase().indexOf("IFRAME") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("IFRAME");
                    String temp_str = stringValue.substring(instr_location, instr_location + 6);
                    stringValue = replace(stringValue, temp_str, "ＩＦＲＡＭＥ");
                }
                if (stringValue.toUpperCase().indexOf("FRAME") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("FRAME");
                    String temp_str = stringValue.substring(instr_location, instr_location + 5);
                    stringValue = replace(stringValue, temp_str, "ＦＲＡＭＥ");
                }
                if (stringValue.toUpperCase().indexOf("EXPRESSION") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("EXPRESSION");
                    String temp_str = stringValue.substring(instr_location, instr_location + 10);
                    stringValue = replace(stringValue, temp_str, "ＥＸＰＲＥＳＳＩＯＮ");
                }
                if (stringValue.toUpperCase().indexOf("ALERT") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("ALERT");
                    String temp_str = stringValue.substring(instr_location, instr_location + 5);
                    stringValue = replace(stringValue, temp_str, "ＡＬＥＲＴ");
                }
                if (stringValue.toUpperCase().indexOf("OPEN") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("OPEN");
                    String temp_str = stringValue.substring(instr_location, instr_location + 4);
                    stringValue = replace(stringValue, temp_str, "ＯＰＥＮ");
                }
                if (stringValue.toUpperCase().indexOf("&#") != -1) {
                    int instr_location = stringValue.toUpperCase().indexOf("&#");
                    String temp_str = stringValue.substring(instr_location, instr_location + 2);
                    stringValue = replace(stringValue, temp_str, "＆＃");
                }

                resultParam.put(fieldID, stringValue);

            } else {

                resultParam.put(fieldID, fieldValue);

                if (UserInfoManager.getUserInfoImpl() != null) {
                    (resultParam).put("ses", UserInfoManager.getUserInfoImpl());
                }
            }
        }
        return resultParam;
    }
*/
    public static String toEmpty(String str) {

        String rtnVal = "";
        byte[] byteRes = null;

        if (str == null) {
            rtnVal = "";
        } else {
            try {
                //rtnVal = URLDecoder.decode(str, "UTF-8");
                byteRes = str.getBytes("UTF-8");
                rtnVal = new String(byteRes, "UTF-8");
            } catch (UnsupportedEncodingException unsupportedencodingexception) {
                unsupportedencodingexception.printStackTrace();
            }
        }
        return rtnVal;
    }

    /**
     * 널 문자열이 들어오면 빈 문자열을 return
     * 아닐 경우는 입력 값을 return<br>
     * if input is  null then return empty String
     * else return input
     * @param str
     * @return String
     */
    public static String nullToEmptyString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str;
    }


    public static String CheckInjection(String str) {
        //return  replaceJavascriptInjectionString(encoder.encodeForSQL(oracleCodec, str));
        return  replaceInjectionString(str);
    }

    public static String CheckInjectionRichTextEdit(String str) {
        //return  replaceJavascriptInjectionString(encoder.encodeForSQL(oracleCodec, str));
        return  replaceInjectionRichTextEdit(str);
    }


    public static String nullToEmptyString(Object obj) {
        if (isEmpty((String)obj)) {
            return "";
        }
        return (String)obj;
    }

    /**
     * 널 또는 빈 문자열의 경우 디폴트 스트링을 반환
     * @param target
     * @param defaultString
     * @return
     */
    public static String defaultIfEmpty(String target, String defaultString) {
        return StringUtils.defaultIfEmpty(target, defaultString);
    }

    public static String replace(String text, String searchString, String replacement) {
        return StringUtils.replace(text, searchString, replacement);
    }

    public static String replaceAll(String text, String searchString, String replacement) {
        if (text == null) return "";
        String returnText = "";
        returnText = text;
        for (int i = 0; i < returnText.length(); i++) {
            if (String.valueOf(returnText.charAt(i)).equals(searchString)) {
                returnText = returnText.replace(searchString, replacement);
            }
        }
        return returnText;
    }

    /**
     * 2차원 문자열 배열에서 해당 문자열의 인덱스를 반환<br>
     * return searchString index in Dimension String array
     * difference: 첫번째 인덱스를 넘기고 두번째 인덱스만 리턴 받음
     * @param strComplexArray
     * @param searchString
     * @return String
     */
    public static int[] getIndexFromDimensionStringArray(String[][] strComplexArray, String searchString) {
        for (int i = 0; i < strComplexArray.length; i++) {
            for (int j = 0; j < strComplexArray.length; j++) {
                if (strComplexArray[i][j].equals(searchString)) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * convert \n to &lt;br/&gt;
     * @param str
     * @return String
     */
    public static String nToBr(String str) {
        return StringUtils.replace(str, "\n", "<br/>");
    }

    /**
     * convert &lt;br/&gt; to \n
     * @param str
     * @return String
     */
    public static String brToN(String str) {
        return StringUtils.replace(str, "<br/>", "\n");
    }

    /**
     * 문자열 내에 delimeter 로 구분된 항목 들을 sql in 절에 맞는 형태로 변형(for in query)<br>
     * convert string with delimeter as Sql IN Statement
     * @param str
     * @param delimeter
     * @return String
     */
    public static String forInQuery(String str, String delimeter) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }
        String[] split = StringUtils.split(str, delimeter);
        String join = StringUtils.join(split, "', '");
        return String.format("('%s')", join);
    }

    /**
     * Url encoding
     * @param str
     * @return String
     */
    public static String encodeUrl(String str) {
		/* @formatter:off */
        String[][] replaceMapArray = { {"%", "%25"}, {"#", "%23"}, {"&", "%26"}, {"'", "%27"}, {"+", "%2B"}, {":", "%3A"}, {";", "%3B"}, {"=", "%3D"}, {"\"", "%22"}, {" ", "+"}};
		/* @formatter:on */

        StrBuilder strBuilder = new StrBuilder(str);
        for (String[] replaceMap : replaceMapArray) {
            strBuilder.replaceAll(replaceMap[0], replaceMap[1]);
        }
        return strBuilder.toString();
    }

    /**
     * 문자열 덧셈<br>
     * Add number String
     * @param value1
     * @param value2
     * @return String
     */
    public static String add(String value1, String value2) {
        return new BigDecimal(value1).add(new BigDecimal(value2)).toString();
    }

    /**
     * 문자열 뺄셈<br>
     * subtract number String
     * @param value1
     * @param value2
     * @return String
     */
    public static String subtract(String value1, String value2) {
        return new BigDecimal(value1).subtract(new BigDecimal(value2)).toString();
    }

    /**
     * 문자열 곱셈<br>
     * multiply number String
     * @param value1
     * @param value2
     * @return String
     */
    public static String multiply(String value1, String value2) {
        return new BigDecimal(value1).multiply(new BigDecimal(value2)).toString();
    }

    /**
     * 문자열 나눗셈 rounding 모드와 자릿수 지정<br>
     * divide number String with rounding mode and digits
     * @param value1
     * @param value2
     * @param scale
     * @param roundMode
     * @return String
     */
    public static String divide(String value1, String value2, int scale, int roundMode) {
        return new BigDecimal(value1).divide(new BigDecimal(value2), scale, roundMode).toString();
    }

    /**
     * 문자열 나눗셈 반올림 할 자리수 지정<br>
     *  divide number String with rounding digits
     * @param value1
     * @param value2
     * @param scale
     * @return String
     */
    public static String divide(String value1, String value2, int scale) {
        return divide(value1, value2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 문자열 나눗셈 소숫점 둘째 자리 까지<br>
     * divide number Up to two decimal places
     * @param value1
     * @param value2
     * @return String
     */
    public static String divide(String value1, String value2) {
        return divide(value1, value2, 2);
    }

    /**
     * 문자열 나머지(mod, %)<br>
     * mod number String
     * @param value1
     * @param value2
     * @return String
     */
    public static String remainder(String value1, String value2) {
        return new BigDecimal(value1).remainder(new BigDecimal(value2)).toString();
    }

    /**
     * 문자열을 srcEncodingType에서 targetEncodingType으로 변환<br>
     * convert String Encoding
     * @param str
     * @param srcEncodingType
     * @param targetEncodingType
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String convertEncoding(String str, String srcEncodingType, String targetEncodingType) throws UnsupportedEncodingException {
//        return StringUtils.toEncodedString(str.getBytes(srcEncodingType), Charset.forName(targetEncodingType));
    	return new String(str.getBytes(srcEncodingType), targetEncodingType);
    }

    /**
     * 8859_1문자열을 KSC5601로 변환
     * 변환에 실패하면 null을 반환<br>
     * convert String 8859_1 when fail convert to KSC5601
     * @param str
     * @return String
     */
    public static String e2k(String str) {
        try {
            return convertEncoding(str, "8859_1", "KSC5601");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * KSC5601문자열을 8859_1로 변환<br>
     * convert KSC5601 String to 8859_1
     * 변환에 실패하면 null을 반환
     * @param str
     * @return String
     */
    public static String k2e(String str) {
        try {
            return convertEncoding(str, "KSC5601", "8859_1");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static ArrayList<String> chopSplitString(String data, int length) {

        ArrayList<String> list = new ArrayList<String>();

        String ui = data;
        int dataLength = getLengthb(ui);

        while (dataLength > 0) {
            list.add(getSubString(ui, 0, length));
            ui = getSubString(ui, length, dataLength);
            dataLength = getLengthb(ui);
        }

        return list;
    }

    public static String getSubString(String str, int start, int end) {
        if (str == null) return "";
        int rSize = 0;
        int len = 0;

        StringBuffer sb = new StringBuffer();

        for (; rSize < str.length(); rSize++) {
            if (str.charAt(rSize) > 0x007F) {
                len += 2;
            } else {
                len++;
            }

            if ((len > start) && (len <= end)) {
                sb.append(str.charAt(rSize));
            }
        }

        return sb.toString();
    }

    public static int getLengthb(String str) {
        int rSize = 0;
        int len = 0;

        for (; rSize < str.length(); rSize++) {
            if (str.charAt(rSize) > 0x007F) {
                len += 2;
            } else {
                len++;
            }
        }

        return len;
    }
/*
    public static String getDBLinkName(String tableName, String dbLinkName) {
        String databaseId = SpringContextUtil.getUtilService().getDatabaseCode();
        if ("OR".equals(databaseId)) {
            return tableName + dbLinkName;
        } else if ("MS".equals(databaseId)) {
            return dbLinkName + tableName;
        } else {
            return tableName;
        }
    }
*/
    
    /**
     * @Method Name : preventSqlInjection
     * @Author daguri
     * @Date 2014. 10. 31.
     * @Version 1.0
     * @Param :
     * @Return : String
     * @Description
     * @param value
     * @return
     */
    public static String preventSqlInjection(String value) {
        if(value == null) {
            return null;
        }

        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("\"", "&quot;");   // " : &#34
        value = value.replaceAll("#", "&#35;");    // # : &#35
        value = value.replaceAll("&", "&#38;");    // & : &amp;
        value = value.replaceAll("'", "&#x27;");   // ' : &#39
        //value = value.replaceAll("(", "&#40;");   // ( : &#40
        //value = value.replaceAll(")", "&#41;");   // ) : &#41
        value = value.replaceAll("/", "&#x2F;");   // / : &#47

        /*
        Map<Character, String> guideMap = new HashMap<Character, String>();
        guideMap.put((char)34, "&quot;");   // " : &#34
        guideMap.put((char)35, "&#35;");    // # : &#35
        guideMap.put((char)38, "&#38;");    // & : &amp;
        guideMap.put((char)39, "&#x27;");   // ' : &#39
        //guideMap.put((char)40, "&#40;");   // ( : &#40
        //guideMap.put((char)41, "&#41;");   // ) : &#41
        guideMap.put((char)47, "&#x2F;");   // / : &#47
        guideMap.put((char)60, "&lt;");     // < : &#60
        guideMap.put((char)62, "&gt;");     // > : &#62

        for(Character ch : guideMap.keySet()) {
            value = value.replaceAll(ch.toString(), guideMap.get(ch));
        }
        */

        return value;
    }

    /**
     * @Method Name : rePreventSqlInjection
     * @Author daguri
     * @Date 2014. 10. 31.
     * @Version 1.0
     * @Param :
     * @Return : String
     * @Description
     * @param value
     * @return
     */
    public static String rePreventSqlInjection(String value) {
        if(value == null) {
            return null;
        }

        Map<String, String> guideMap = new HashMap<String, String>();
        guideMap.put("&lt;", "<");
        guideMap.put("&gt;", ">");
        guideMap.put("&#39;", "'");

        for(String ch : guideMap.keySet()) {
            value = value.replaceAll(ch, guideMap.get(ch));
        }

        return value;
    }

    /**
     * @return 임시비밀번호 8 자리
     */
    public static String getRandomPassword() {

        char pwCollection[] = new char[] {
                '1','2','3','4','5','6','7','8','9','0',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                '!','@','#','$','%','^','*','(',')','+'
        };//배열에 선언

        char pwCollectionD[] = new char[] {
                '1','2','3','4','5','6','7','8','9','0'
        };

        char pwCollectionS[] = new char[] {
                '!','@','#','$','%','^','*','(',')','+'
        };

        char pwCollectionU[] = new char[] {
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        };//배열에 선언

        char pwCollectionL[] = new char[] {
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
        };//배열에 선언

        String ranPw = "";

        /* 아무거나 5자리 */
        for (int i = 0; i < 5; i++) {
            int selectRandomPw = (int)(new SecureRandom().nextDouble()*(pwCollection.length));
            ranPw += pwCollection[selectRandomPw];
        }

        /* 숫자 1자리 */
        for (int i = 0; i < 1; i++) {
            int selectRandomPw = (int)(new SecureRandom().nextDouble()*(pwCollectionD.length));
            ranPw += pwCollectionD[selectRandomPw];
        }

        /* 특수문자 1자리 */
        for (int i = 0; i < 1; i++) {
            int selectRandomPw = (int)(new SecureRandom().nextDouble()*(pwCollectionS.length));
            ranPw += pwCollectionS[selectRandomPw];
        }

        /* 아무거나 2자리 */
        for (int i = 0; i < 2; i++) {
            int selectRandomPw = (int)(new SecureRandom().nextDouble()*(pwCollection.length));
            ranPw += pwCollection[selectRandomPw];
        }

        // System.err.println("new ㅇㅇㅇㅇㅇㅇ ppwd --> " + ranPw);
        return ranPw;
    }

    /**
     * @param str
     * @return
     */
	public static String replaceToXmlString(String str) {
		String xmlStr = str;

		xmlStr = replace(xmlStr, "&", "&amp;");
		xmlStr = replace(xmlStr, "<", "&lt;");
		xmlStr = replace(xmlStr, ">", "&gt;");
		xmlStr = replace(xmlStr, "'", "&apos;");
		xmlStr = replace(xmlStr, "\"", "&quot;");

		return xmlStr;
	}

	public static String replaceStringFromXml(String xmlStr) {
		String str = xmlStr;

		str = replace(str, "&amp;", "&");
		str = replace(str, "&lt;", "<");
		str = replace(str, "&gt;", ">");
		str = replace(str, "&apos;", "'");
		str = replace(str, "&quot;", "\"");

		return str;
	}

    /**
     * 보안정책에 의한 마스킹 처리
     * 섬영 : 홍*동 (성 뒤 이름의 첫 자리 마스킹)
     * 전화번호 또는 휴대폰 전화번호 : 010-****-1234 (국번 마스킹)
     * 주소 : 서울 영등포구 여의도동 **-** (읍.면.동 또는 이후 주소 마스킹)
     * 인터넷 주소 : 123.123.***.123 (버전 4의 경우 17~24비트영역, 버전 6의 경우 113~128비트영역)
     * 패스워드 : ****** (전체자리 마스킹)
     * @param str
     * @param type("A":전체(비밀번호),"N":성명,"T":전화번호,"M":이메일,"I":IP Address
     * @return
     * @throws Exception
     */
    public static String setMaskString(String str, String type) throws Exception {

        if(StringUtil.isEmpty(str) || "null".equals(str)) {
            return "";
        }

	    if(type != null) {
            if (type.equals("A")) { /** All(전체 : 비밀번호) */
            	str = StringUtils.replacePattern(str, ".", "*");
            } else if (type.equals("N")) { /** 홍*동 (성 뒤 이름의 첫 자리 마스킹) */
            	try {
                    str = maskName(str);
                } catch(Exception ex) {
                    return ex.toString();
                }
            } else if (type.equals("T")) { /** 010-****-1234 (국번 마스킹) */
            	try {
                    str = maskPhoneNum(str);
                } catch(Exception ex) {
                    return ex.toString();
                }
            } else if (type.equals("M")) { /** ho*****@naver.com (3자리부터 @ 앞까지 마스킹) */
            	try {
                    str = maskEmail(str);
                } catch(Exception ex) {
                    return ex.toString();
                }
            } else if (type.equals("I")) { /** 123.123.***.123 (버전 4의 경우 17~24비트영역, 버전 6의 경우 113~128비트영역) */
            	try {
                    str = maskIp(str);
                } catch(Exception ex) {
                    return ex.toString();
                }
            } else if (type.equals("USER_ID")) { /* KHA**** 사용자 ID 일부만 표시한다. */
                try {
                    str = maskUserId(str);
                } catch(Exception ex) {
                    return ex.toString();
                }
            }
        } else {
	        throw new Exception("No type specified!");
        }

        return str;
    }

    /**
     * 사용자ID 마스킹 처리(aaaa******)
     * @param str
     * @return
     */
    private static String maskUserId(String str) {
        String LASTNAME_PATTERN = "(?<=.{0}).";

        if(str.length() < 4) {
            return str;
        } else {
            return str.substring(0, 4) + str.substring(4).replaceAll(LASTNAME_PATTERN, "*");
        }
    }

    /**
     * 이름 마스킹 처리(홍*동)
     * @param str
     * @return
     */
    public static String maskName(String str) {
		String replaceString = str;
		
		String pattern = "";
		if(str.length() == 2) {
			pattern = "^(.)(.+)$";
		} else {
			pattern = "^(.)(.+)(.)$";
		}
		
		Matcher matcher = Pattern.compile(pattern).matcher(str);
		if(matcher.matches()) {
			replaceString = "";
			for(int i=1;i<=matcher.groupCount();i++) {
				String replaceTarget = matcher.group(i);
				if(i == 2) {
					char[] c = new char[replaceTarget.length()];
					Arrays.fill(c, '*');
					
					replaceString = replaceString + String.valueOf(c);
				} else {
					replaceString = replaceString + replaceTarget;
				}
			}
		}
		return replaceString;
	}
    
    /**
     * 전화번호 마스킹 처리
     * @param str
     * @return
     */
    public static String maskPhoneNum(String str) {
        String replaceString = str.trim();
        Matcher matcher = Pattern.compile("^(\\d{3})-?(\\d{3,4})-?(\\d{4})$").matcher(str); //휴대전화(010-1111-2222)
        if(matcher.matches()) {
            replaceString = makeMaskPhone(str, matcher);
        } else {
            matcher = Pattern.compile("^(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$").matcher(str); //일반전화(02-3333-4444)
            if(matcher.matches()) {
                replaceString = makeMaskPhone(str, matcher);
            }
        }

        return replaceString;
    }

    /**
     *
     * @param str
     * @param matcher
     * @return
     */
    public static String makeMaskPhone(String str, Matcher matcher) {
        String replaceString = "";
        boolean isHyphen = false;
        if(str.indexOf("-") > -1) {
            isHyphen = true;
        }
        for(int i=1;i<=matcher.groupCount();i++) {
            String replaceTarget = matcher.group(i);
            if(i == 2) {
                char[] c = new char[replaceTarget.length()];
                Arrays.fill(c, '*');
                replaceString = replaceString + String.valueOf(c);
            } else {
                replaceString = replaceString + replaceTarget;
            }
            if(isHyphen && i < matcher.groupCount()) {
                replaceString = replaceString + "-";
            }
        }

        return replaceString;
    }
    
    /**
     * 이메일 마스킹 처리
     * @param str
     * @return
     */
    public static String maskEmail(String str) {
		String replaceString = str;
		
		Matcher matcher = Pattern.compile("^(..)(.*)([@]{1})(.*)$").matcher(str);
		if(matcher.matches()) {
			replaceString = "";
			for(int i = 1; i <= matcher.groupCount(); i++) {
				String replaceTarget = matcher.group(i);
				if(i == 2) {
					char[] c = new char[replaceTarget.length()];
					Arrays.fill(c, '*');
					replaceString = replaceString + String.valueOf(c);
				} else {
					replaceString = replaceString + replaceTarget;
				}
			}
		}
		return replaceString;
	}
    
    /**
     * IP 마스킹 처리
     * @param str
     * @return
     */
    public static String maskIp(String str) {
    	String replaceString = "";
		String[] strArr = str.split("[.]", 0);
		for( int i = 0; i < strArr.length; i++ ) {
			if( i == 2 ){
				strArr[i] = "***";
			}
			replaceString += (i > 0 ? "." : "") + strArr[i];
		}
		return replaceString;
	}

    public static Map<String, String> xmlParser(String xml, Map<String, String> nodePath) throws Exception {
        Map<String,String> xmlData = new HashMap<String, String>();

        try {
            xml = xml.replaceAll("<!\\[CDATA\\[", "").replaceAll("]]>", "");
            for (String key : nodePath.keySet()) {
                String lstr = "<" + key + ">";
                String rstr = "</" + key + ">";
                String result = xml.substring(xml.indexOf(lstr) + lstr.length(), xml.indexOf(rstr));
                xmlData.put(key, result);
            }

            /*InputSource is = new InputSource(new StringReader(xml));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

            // xpath 생성
            XPath xpath = XPathFactory.newInstance().newXPath();

            xmlData = new HashMap<String, String>();
            for (String key : nodePath.keySet()) {
                Node node = (Node) xpath.evaluate(nodePath.get(key), document, XPathConstants.NODE);

                xmlData.put(key, node.getTextContent());
            }*/

        } catch (Exception e) {
            throw e;
//            e.getMessage();
        }

        return xmlData;
    }

    public static Map<String, String> replaceInjection(Map<String, String> param) throws Exception {

        Map<String, String> paramInfo = new HashMap<>();
        Iterator<String> keys = param.keySet().iterator();
        while ( keys.hasNext() ) {
            String key   = keys.next();
            String value = param.get(key);
            //if(key.toUpperCase().indexOf("CALLBACK") > -1) {
            value = StringUtil.preventSqlInjection(value);
            //}
            paramInfo.put(key, value);
        }

        return paramInfo;
    }

	public static Map<String, Object> forTransactionQuery(Map<String, Object> param) {
		Map<String, Object> resultParam = new HashMap();
		Iterator<Entry<String, Object>> it = param.entrySet().iterator();
		String fieldID = "";
		Object fieldValue = "";
		String stringValue = "";

		while (it.hasNext()) {
			Entry<String, Object> entry = (Entry) it.next();
			fieldID = toEmpty((String) entry.getKey());
			Object in_fieldValue = param.get(entry.getKey());
			stringValue = "";
			if (in_fieldValue instanceof String) {
				stringValue = toEmpty((String) in_fieldValue);
			}

			if (!isEmpty(stringValue)) {
				if (stringValue.toUpperCase().indexOf("--") != -1) {
					stringValue = replace(stringValue, "--", "－－");
				}

				if (stringValue.toUpperCase().indexOf(59) != -1) {
					stringValue = replace(stringValue, ";", "；");
				}

				if (stringValue.toUpperCase().indexOf(37) != -1) {
					stringValue = replace(stringValue, "%", "％");
				}

				int instrLocation;
				String tempStr;
				if (stringValue.toUpperCase().indexOf("JAVASCRIPT") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("JAVASCRIPT");
					tempStr = stringValue.substring(instrLocation, instrLocation + 10);
					stringValue = replace(stringValue, tempStr, "ＪＡＶＡＳＣＲＩＰＴ");
				}

				if (stringValue.toUpperCase().indexOf("JSCRIPT") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("JSCRIPT");
					tempStr = stringValue.substring(instrLocation, instrLocation + 7);
					stringValue = replace(stringValue, tempStr, "ＪＳＣＲＩＰＴ");
				}

				if (stringValue.toUpperCase().indexOf("VBSCRIPT") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("VBSCRIPT");
					tempStr = stringValue.substring(instrLocation, instrLocation + 8);
					stringValue = replace(stringValue, tempStr, "ＶＢＳＣＲＩＰＴ");
				}

				if (stringValue.toUpperCase().indexOf("SCRIPT") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("SCRIPT");
					tempStr = stringValue.substring(instrLocation, instrLocation + 6);
					stringValue = replace(stringValue, tempStr, "ＳＣＲＩＰＴ");
				}

				if (stringValue.toUpperCase().indexOf("IFRAME") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("IFRAME");
					tempStr = stringValue.substring(instrLocation, instrLocation + 6);
					stringValue = replace(stringValue, tempStr, "ＩＦＲＡＭＥ");
				}

				if (stringValue.toUpperCase().indexOf("FRAME") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("FRAME");
					tempStr = stringValue.substring(instrLocation, instrLocation + 5);
					stringValue = replace(stringValue, tempStr, "ＦＲＡＭＥ");
				}

				if (stringValue.toUpperCase().indexOf("EXPRESSION") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("EXPRESSION");
					tempStr = stringValue.substring(instrLocation, instrLocation + 10);
					stringValue = replace(stringValue, tempStr, "ＥＸＰＲＥＳＳＩＯＮ");
				}

				if (stringValue.toUpperCase().indexOf("ALERT") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("ALERT");
					tempStr = stringValue.substring(instrLocation, instrLocation + 5);
					stringValue = replace(stringValue, tempStr, "ＡＬＥＲＴ");
				}

				if (stringValue.toUpperCase().indexOf("OPEN") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("OPEN");
					tempStr = stringValue.substring(instrLocation, instrLocation + 4);
					stringValue = replace(stringValue, tempStr, "ＯＰＥＮ");
				}

				if (stringValue.toUpperCase().indexOf("&#") != -1) {
					instrLocation = stringValue.toUpperCase().indexOf("&#");
					tempStr = stringValue.substring(instrLocation, instrLocation + 2);
					stringValue = replace(stringValue, tempStr, "＆＃");
				}

				resultParam.put(fieldID, stringValue);
			} else {
				resultParam.put(fieldID, fieldValue);
//				if (ClientHolder.getLoginInfo() != null) {
//					resultParam.put("ses", ClientHolder.getLoginInfo());
//				}
			}
		}

		return resultParam;
	}

	public static String getJsonString(Object jsonBean) throws IOException {
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = objMapper.writeValueAsString(jsonBean);
		return jsonString;
	}

	public static String arrayToJSON(String[] sResult) {
		return "{\"CODE\":\"" + sResult[0] + "\", \"MESSAGE\":\"" + sResult[1] + "\"}";
	}

	public static String setEncryptedString(String paramString, String type) {
		String resultStr = "";
		if (paramString == null) {
			return resultStr;
		} else {
			if (type != null) {
				StringBuilder s;
				if (type.equals("N")) {
					s = new StringBuilder(paramString);

					try {
						s.setCharAt(1, '*');
					} catch (StringIndexOutOfBoundsException var5) {
						return s.toString();
					}

					return s.toString();
				}

				if (type.equals("A")) {
					resultStr = StringUtils.replacePattern(paramString, ".", "*");
				} else if (type.equals("E")) {
					s = new StringBuilder(paramString);

					try {
						s.setCharAt(3, '*');
					} catch (StringIndexOutOfBoundsException var7) {
						return s.toString();
					}

					try {
						s.setCharAt(4, '*');
					} catch (StringIndexOutOfBoundsException var6) {
						return s.toString();
					}

					return s.toString();
				}
			}

			return resultStr;
		}
	}
	
    public static String changeCharacterSetDb2AppString(String sourceStr) {
//    	if(!Config.getBoolean("bzd.system.database.encoding.useFlag", false)) {
//    		return sourceStr;
//    	}
//
//    	String dbValue = Config.getString("bdz.system.database.encoding.db.value");
//    	String appValue = Config.getString("bdz.system.database.encoding.app.value");
//
//        try {
//            sourceStr = new String(nullToEmptyString(sourceStr).getBytes(dbValue), appValue);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return sourceStr;
    }
    
	/**
	 * 2008.10.22 이대규 한글 처리 관련 추가
	 * korean handle process
	 * @param filename
	 * @param encoding
	 * @return String
	 * @throws IOException
	 */
	public static String fileReadByOffsetByEncoding(String filename, String encoding) throws IOException {
		File file = null;
		BufferedReader in = null;
		StringWriter out = null;
		String contents = "";
		char[] buf = new char[1024];
		int len = 0;

		file = new File(filename);
		FileInputStream fileinputstream = new FileInputStream(file);

		if (file.exists()) {
			in = new BufferedReader(new InputStreamReader(fileinputstream, encoding));
			out = new StringWriter();

			while ((len = in.read(buf, 0, buf.length)) != -1) {
				out.write(buf, 0, len);
			}

			contents = out.toString();
		}

		return contents;
	}
	
}