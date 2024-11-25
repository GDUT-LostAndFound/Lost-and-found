package com.example.lostandfound.util;

public class PhonePrivacyUtil {

    // 隐藏手机号中间的部分，保留前后4位
    public static String hidePhone(String phone) {
        if (phone == null || phone.length() < 7) {
            return phone;  // 如果手机号无效，返回原始手机号
        }

        // 保留前3位和后4位，其他部分替换为 '*'
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    // 如果需要其他隐私处理方法，比如加密，可以在此扩展
}
