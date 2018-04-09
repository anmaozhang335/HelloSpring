package com.zam.o2o.service;

import java.io.IOException;
import java.util.List;

import com.zam.o2o.entity.HeadLine;

public interface HeadLineService {
   public static final String HLLISTKEY = "headlinelist";
   
    /**
     * 
     * @param headLine
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
