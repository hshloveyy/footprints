package com.mvc.footprints.dao;

import com.mvc.footprints.entity.TPhoneCode;

public interface IPhoneCodeDao extends BaseDao{

	TPhoneCode verfiyCode(String mobilePhone, String phcode);

}
