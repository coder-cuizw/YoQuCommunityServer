package net.gupt.community.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <h3>gupt-community</h3>
 * <p>Aes加解密工具类</p>
 *
 * @author : Cui
 * @date : 2019-07-31 08:59
 **/
public class AesUtil {

    private static final byte[] KEY = "1234567890ABCDEF1234567890ABCDEf".getBytes(StandardCharsets.UTF_8);
    /**
     * 偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
     */
    private static final String IV = "RvGhgdP41jz-QOCg";
    private static final String ALGORITHM = "AES";
    /**
     * 算法/模式/补码方式
     */
    private static final String ALGORITHM_PROVIDER = "AES/CBC/PKCS5Padding";

    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        //默认128，获得无政策权限后可为192或256
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    private static IvParameterSpec getIv() {
        return new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] encrypt(String src) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(ALGORITHM_PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(src.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] decrypt(String src) throws Exception {
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);

        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(ALGORITHM_PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] hexBytes = hexStringToBytes(src);
        return cipher.doFinal(hexBytes);
    }

    /**
     * 将byte转换为16进制字符串
     *
     * @param src byte数组
     * @return 16进制字符串
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (byte b : src) {
            int v = b & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     *
     * @param hexString 16进制字符串
     * @return byte数组
     */
    private static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
