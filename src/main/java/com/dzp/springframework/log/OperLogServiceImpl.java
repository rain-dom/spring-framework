package com.dzp.springframework.log;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzp.springframework.log.model.OperLogPO;
import org.springframework.stereotype.Service;

/**
 * OperLogServiceImpl 服务实现类.
 *
 * @classname: com.sunshine.buddhistlife.component.log.OperLogServiceImpl.java
 * @copyright: Powered By Sunshine
 * @author: Somnus
 * @date: 2022-06-29 17:08:51
 * @version: V1.0
*/
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogDao, OperLogPO> implements OperLogService {

}
