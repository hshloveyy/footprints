package com.mvc.footprints.dao;

import com.mvc.footprints.entity.PreUcenterMembers;

public interface IPreUcenterMemberDao extends BaseDao{

	PreUcenterMembers findByUsername(String username);

	PreUcenterMembers findUserByParam(PreUcenterMembers members);

	PreUcenterMembers findByUserId(Integer userId);

	PreUcenterMembers findUserByMobilePhone(String phoneNumber);

}
