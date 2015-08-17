package com.palm.lingcai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palm.lingcai.entity.SysParam;
import com.palm.lingcai.repository.SysParamDao;

@Service
@Transactional
public class SysParamService {
	@Autowired
	private SysParamDao sysParamDao;

	public SysParam getByCode1(String code1) {
		List<SysParam> list = sysParamDao.getByCode1(code1);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}

	public SysParam getByCodes(String code1, String code2) {
		return sysParamDao.getByCodes(code1, code2);
	}

	public void updateCode3(SysParam sysParam) {
		sysParamDao.updateCode3(sysParam);
	}
}
