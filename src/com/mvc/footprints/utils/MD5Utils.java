package com.mvc.footprints.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/** 
     * MD5Utils 鍔犲瘑 
     */  
    private static String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes());  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
        } 
  
        byte[] byteArray = messageDigest.digest();  

        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
//        int i;
//        for (int offset = 0; offset < byteArray.length; offset++) { 
//            i = byteArray[offset]; 
//            if (i < 0) 
//                i += 256; 
//            if (i < 16) 
//            	md5StrBuff.append("0"); 
//            md5StrBuff.append(Integer.toHexString(i)); 
//        } 
  
        return md5StrBuff.toString();  
    }  
    
    public static void main(String[] args) {
		try {
//			System.out.println(getMD5Str("9059230240"));
//			System.out.println(generatePassword("yinsi307PIKA"));
//			System.out.println(getMD5("yinsi307PIKA"));
			
			System.out.println(MD5Utils.generatePassword(MD5Utils.generatePassword(null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//49c76cce7da8b15cd45a502868f19b8b
	}
    
  //鍗佸叚杩涘埗涓嬫暟瀛楀埌瀛楃鐨勬槧灏勬暟缁� 
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",  
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};  
      
    /** * 鎶奿nputString鍔犲瘑     */  
    public static String generatePassword(String inputString){  
        return encodeByMD5(inputString);  
    }  
      
      /** 
       * 楠岃瘉杈撳叆鐨勫瘑鐮佹槸鍚︽纭�
     * @param password    鍔犲瘑鍚庣殑瀵嗙爜 
     * @param inputString    杈撳叆鐨勫瓧绗︿覆 
     * @return    楠岃瘉缁撴灉锛孴RUE:姝ｇ‘ FALSE:閿欒 
     */  
    public static boolean validatePassword(String password, String inputString){  
        if(password.equals(encodeByMD5(inputString))){  
            return true;  
        } else{  
            return false;  
        }  
    }  
    /**  瀵瑰瓧绗︿覆杩涜MD5鍔犲瘑     */  
    private static String encodeByMD5(String originString){  
        if (originString != null){  
            try{  
                //鍒涘缓鍏锋湁鎸囧畾绠楁硶鍚嶇О鐨勪俊鎭憳瑕� 
                MessageDigest md = MessageDigest.getInstance("MD5");  
                //浣跨敤鎸囧畾鐨勫瓧鑺傛暟缁勫鎽樿杩涜鏈�悗鏇存柊锛岀劧鍚庡畬鎴愭憳瑕佽绠� 
                byte[] results = md.digest(originString.getBytes());  
                //灏嗗緱鍒扮殑瀛楄妭鏁扮粍鍙樻垚瀛楃涓茶繑鍥� 
                String resultString = byteArrayToHexString(results);  
                return resultString;  
            } catch(Exception ex){  
                ex.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    /**  
     * 杞崲瀛楄妭鏁扮粍涓哄崄鍏繘鍒跺瓧绗︿覆 
     * @param     瀛楄妭鏁扮粍 
     * @return    鍗佸叚杩涘埗瀛楃涓�
     */  
    private static String byteArrayToHexString(byte[] b){  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++){  
            resultSb.append(byteToHexString(b[i]));  
        }  
        return resultSb.toString();  
    }  
      
    /** 灏嗕竴涓瓧鑺傝浆鍖栨垚鍗佸叚杩涘埗褰㈠紡鐨勫瓧绗︿覆     */  
    private static String byteToHexString(byte b){  
        int n = b;  
        if (n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    } 
    
    /**
     * 鑾峰彇MD5鍔犲瘑鍚庣殑瀛楃涓�
     * @param str 鏄庢枃
     * @return 鍔犲瘑鍚庣殑瀛楃涓�
     * @throws Exception 
     */
    public static String getMD5(String str) throws Exception {
        /** 鍒涘缓MD5鍔犲瘑瀵硅薄 */
        MessageDigest md5 = MessageDigest.getInstance("MD5"); 
        /** 杩涜鍔犲瘑 */
        md5.update(str.getBytes());
        /** 鑾峰彇鍔犲瘑鍚庣殑瀛楄妭鏁扮粍 */
        byte[] md5Bytes = md5.digest();
        String res = "";
        for (int i = 0; i < md5Bytes.length; i++){
            int temp = md5Bytes[i] & 0xFF;
            if (temp <= 0XF){ // 杞寲鎴愬崄鍏繘鍒朵笉澶熶袱浣嶏紝鍓嶉潰鍔犻浂
                res += "0";
            }
            res += Integer.toHexString(temp);
        }
        return res;
    }
}
