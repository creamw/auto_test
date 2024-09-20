package tool;

import android.util.Base64;

import androidx.test.internal.util.LogUtil;

import java.security.MessageDigest;
import java.util.Arrays;


/**
 * Created by zhl on 2018/4/12.
 */
public class DigestUtil {

    private static final String TAG = DigestUtil.class.getSimpleName();

    /**
     * 生成方法请求签名
     *
     * @param timestamp
     * @param nonce
     * @param method
     * @param access_token
     * @return
     */
    public static String getSinature(String timestamp, String nonce, String method, String access_token) {
        // 1.将token、timestamp、nonce、access_token个参数进行字典序排序
        String[] arr = new String[]{timestamp, nonce, method, access_token};
        Arrays.sort(arr);

        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //LogUtil.d(TAG, "content: " + content);
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
            tmpStr = byteToStr(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmpStr;
    }

    /**
     * MD5摘要
     * 注：不可对原始敏感数据做数据摘要处理
     *
     * @param input
     * @return
     */
    public static String md5(String input) {

        String output = "";

        try {
            MessageDigest inst = MessageDigest.getInstance("MD5");
            byte[] digest = inst.digest(input.getBytes("UTF-8"));
            output = byteToStr(digest);
        } catch (Exception e) {
            //LogUtil.e(TAG, "md5 fail.", e);

        }

        return output;
    }


    /**
     * SHA1摘要
     *
     * @param input
     * @return
     */
    public static String sha1(String input) {

        String output = "";

        try {
            MessageDigest inst = MessageDigest.getInstance("SHA1");
            byte[] digest = inst.digest(input.getBytes("UTF-8"));
            output = byteToStr(digest);
        } catch (Exception e) {
            //LogUtil.e(TAG, "SHA1 fail.", e);
        }

        return output;
    }

    /**
     * SHA256摘要
     *
     * @param input
     * @return
     */
    public static String sha256(String input) {

        String output = "";

        try {
            MessageDigest inst = MessageDigest.getInstance("SHA-256");
            byte[] digest = inst.digest(input.getBytes("UTF-8"));
            output = byteToStr(digest);
        } catch (Exception e) {
            //LogUtil.e(TAG, "SHA-256 fail.", e);
        }

        return output;
    }

    /**
     * 字节转字十六进制符串
     *
     * @param byteArray
     * @return
     */
    public static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            strDigest.append(byteToHexStr(byteArray[i]));
        }
        return strDigest.toString();
    }

    private static final char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static char[] byteToHexStr(byte mByte) {
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return tempArr;
    }


    /**
     * Base64编码
     *
     * @param input 输入字符串
     * @return
     */
    public static String base64encode4UrlSafe(byte[] input) {

        try {
            return Base64.encodeToString(input, Base64.URL_SAFE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Base64编码
     *
     * @param input 输入字符串
     * @return
     */
    public static String base64encode(byte[] input) {

        try {
            return Base64.encodeToString(input, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Base64编码
     *
     * @param input       输入字符串
     * @param base64Flags 编码模式
     *                    {@link Base64#DEFAULT}
     *                    {@link Base64#URL_SAFE}
     *                    {@link Base64#NO_WRAP}
     * @return
     */
    public static String base64encode(byte[] input, int base64Flags) {

        try {
            return Base64.encodeToString(input, base64Flags);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * Base64解码
     *
     * @param input       输入字符
     * @param deCodeFlags 解码标识
     *                    {@link Base64#DEFAULT}
     *                    {@link Base64#URL_SAFE}
     *                    {@link Base64#NO_WRAP}
     * @return
     */
    public static byte[] base64decode(String input, int deCodeFlags) {
        try {
            return Base64.decode(input, deCodeFlags);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base64解码
     *
     * @param input
     * @return
     */
    public static byte[] base64decode(String input) {
        return base64decode(input, Base64.DEFAULT);
    }

}

//    String content = "这是一个Aes加密测试 abc 123 ...end";
//    //        String content = "Aes test abc 123 ...end";
//    String password = SessionData.getInstance().getUserInfo().getLoginUser().getSecret_key();
//
//    String enc = DigestUtil.aesEncrypt(content, password);
//    String dec = DigestUtil.aesDecrypt(enc, password);
//
//        LogUtil.d(TAG, "Aes \nsrc: " + content + ",\nenc: " + enc + ", \ndec: " + dec);
