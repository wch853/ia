package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.InformRet;

public interface InformService {

    /**
     * 处理上行通知报文
     *
     * @param informRet
     */
    void consumeInfromRet(InformRet informRet);
}
