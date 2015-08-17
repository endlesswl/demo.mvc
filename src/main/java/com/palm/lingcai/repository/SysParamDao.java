package com.palm.lingcai.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.palm.lingcai.entity.SysParam;

@Repository("sysParamDao")
public interface SysParamDao {

	SysParam get(String id);

	List<SysParam> getByCode1(String code1);

	List<SysParam> searchAll();

	List<SysParam> search(Map<String, Object> parameters);

	SysParam getByCodes(@Param("code1") String code1,@Param("code2") String code2);
	
	void updateCode3(SysParam sysParam);

}
