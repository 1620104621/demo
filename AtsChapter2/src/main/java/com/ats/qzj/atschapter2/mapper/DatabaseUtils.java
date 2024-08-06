package com.ats.qzj.atschapter2.mapper;


import com.ats.qzj.atschapter2.model.SettleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
//@Repository
public interface DatabaseUtils {
    List<SettleEntity> findCardDetailsByIds(@Param("id") Long id);
}
