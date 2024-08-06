package com.ats.qzj.atschapter2.atsService;

import com.ats.qzj.atschapter2.mapper.DatabaseUtils;
import com.ats.qzj.atschapter2.model.SettleEntity;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataUtilsServ {
    @Autowired
     DatabaseUtils databaseUtils;

    public List<SettleEntity> findCardDetailsById(Long id) {
        List<SettleEntity> cardDetailsById = databaseUtils.findCardDetailsByIds(id);
        SettleEntity settleEntity = cardDetailsById.get(0);
        System.out.println(settleEntity.toString());
        return cardDetailsById;
    }
}
