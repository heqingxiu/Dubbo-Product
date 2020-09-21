package com.qx.api.registry.api;

import com.qingxiu.utills.webresult.Result;

/**
 * Author: QX_He
 * DATA: 2020/8/10-16:27
 * Description:
 **/
public interface RegistryByEmailService {
    /**
     * Create a verification code by phone number
     *
     * @param phoneNumber
     * @return
     */
    String createVerificationCode(String phoneNumber);

    /**
     * Check verifications code
     *
     * @param phoneNumber
     * @param code
     * @return
     */
    Boolean checkUserMessagesByCode(String phoneNumber, String code);
}
