package com.dzp.springframework.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzp.springframework.log.model.OperLogPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OperLogDao Mapper 接口.
 *
 * @classname: com.sunshine.buddhistlife.component.log.OperLogDao.java
 * @copyright: Powered By Sunshine
 * @author: Somnus
 * @date: 2022-06-29 17:08:51
 * @version: V1.0
*/
@Mapper
public interface OperLogDao extends BaseMapper<OperLogPO> {

}
