package com.palm.lingcai.repository;

import com.palm.lingcai.entity.Bank;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bankDao")
public interface BankDao {

    Bank get(Long id);

    List<Bank> getByUser(Long userid);

    List<Bank> search(Map<String, Object> parameters);

    Page<Bank> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

    void insert(Bank bank);

    void delete(@Param("userid") Long userid, @Param("id") Long id);

    void update(Bank bank);

	Bank findMinShengBank(@Param("userid") Long userid,  @Param("cardno")String lCBEAcNo);

	Bank findMinShengBankByCardNoAndRealName(@Param("cardno") String cardno, @Param("accountname") String accountname);

}
