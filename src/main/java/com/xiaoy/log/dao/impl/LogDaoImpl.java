package com.xiaoy.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Log;
import com.xiaoy.log.dao.LogDao;

@Repository
public class LogDaoImpl extends CommonDaoImpl<Log> implements LogDao
{

}
