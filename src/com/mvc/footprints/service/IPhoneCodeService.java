package com.mvc.footprints.service;

import com.mvc.footprints.entity.TPhoneCode;


public interface IPhoneCodeService extends BaseService {
	
	/**
	 * 
	 * @param mobilePhone
	 * @param phcode
	 * @return
	 */
	public TPhoneCode verifyCode(String mobilePhone, String phcode);
}
