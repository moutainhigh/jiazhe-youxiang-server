package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.base.util.SerializableUtils;
import com.jiazhe.youxiang.server.dao.mapper.SimpleSessionPOMapper;
import com.jiazhe.youxiang.server.domain.po.SimpleSessionPO;
import com.jiazhe.youxiang.server.domain.po.SimpleSessionPOExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomSessionDAO extends AbstractSessionDAO {

    @Autowired
    private SimpleSessionPOMapper simpleSessionPOMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSessionDAO.class);
    private ConcurrentMap<Serializable, Session> sessions = new ConcurrentHashMap();

    public CustomSessionDAO() {
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        LOGGER.info("doCreate sessionId:{}", sessionId);
        SimpleSessionPO simpleSessionPO = new SimpleSessionPO();
        simpleSessionPO.setCookie(sessionId.toString());
        simpleSessionPO.setSession(SerializableUtils.serialize(session));
        simpleSessionPOMapper.insertSelective(simpleSessionPO);
        this.assignSessionId(session, sessionId);
        this.storeSession(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        LOGGER.info("doReadSession sessionId:{}", sessionId);
        Session session = this.sessions.get(sessionId);
        //是否有active的session
        if (null == session) {
            SimpleSessionPO simpleSessionPO = getSimpleSessionPOBySession(sessionId);
            if (null != simpleSessionPO) {
                session = SerializableUtils.deserialize(simpleSessionPO.getSession());
                this.assignSessionId(session, sessionId);
                this.storeSession(sessionId, session);
                ((SimpleSession) session).setLastAccessTime(new Date());
                LOGGER.info("无密码登录 sessionId:{}", sessionId);
            }
        }
        return session;
    }

    private SimpleSessionPO getSimpleSessionPOBySession(Serializable sessionId) {
        SimpleSessionPOExample example = new SimpleSessionPOExample();
        SimpleSessionPOExample.Criteria criteria = example.createCriteria();
        criteria.andCookieEqualTo(sessionId.toString());
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<SimpleSessionPO> simpleSessionPOList = simpleSessionPOMapper.selectByExample(example);
        return simpleSessionPOList.isEmpty() ? null : simpleSessionPOList.get(0);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        LOGGER.info("update sessionId:{}", session.getId());
        this.storeSession(session.getId(), session);
        if (session instanceof ValidatingSession
                && !((ValidatingSession) session).isValid()) {
            return;
        }
        SimpleSessionPO simpleSessionPO = getSimpleSessionPOBySession(session.getId());
        if (simpleSessionPO != null && !simpleSessionPO.getSession().equals(SerializableUtils.serialize(session))) {
            simpleSessionPO.setSession(SerializableUtils.serialize(session));
            simpleSessionPO.setModTime(new Date());
            simpleSessionPOMapper.updateByPrimaryKey(simpleSessionPO);
        }
    }

    @Override
    public void delete(Session session) {
        LOGGER.info("delete sessionId:{}", session.getId());
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        } else {
            Serializable id = session.getId();
            if (id != null) {
                SimpleSessionPOExample example = new SimpleSessionPOExample();
                SimpleSessionPOExample.Criteria criteria = example.createCriteria();
                criteria.andCookieEqualTo(session.getId().toString());
                simpleSessionPOMapper.deleteByExample(example);
                this.sessions.remove(id);
            }

        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<Session> values = this.sessions.values();
        return (Collection) (CollectionUtils.isEmpty(values) ? Collections.emptySet() : Collections.unmodifiableCollection(values));
    }

    protected Session storeSession(Serializable id, Session session) {
        LOGGER.info("storeSession sessionId:{}", id);
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        } else {
            return this.sessions.putIfAbsent(id, session);
        }
    }
}
