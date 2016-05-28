package com.boubei.tss;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.boubei.tss.framework.Global;
import com.boubei.tss.framework.sso.IdentityCard;
import com.boubei.tss.framework.sso.TokenUtil;
import com.boubei.tss.framework.sso.context.Context;
import com.boubei.tss.um.UMConstants;
import com.boubei.tss.um.helper.dto.OperatorDTO;
import com.boubei.tss.util.URLUtil;

/**
 * 初始化数据库。
 * 
 * 执行前先把test/resources/application.properties改名。
 * 
 * 需使用 src/main/resources目录下的配置文件，比如persistence.xml, application.properties等。
 * 另外，初始化时需要把applicationContext.xml的<property name="generateDdl" value="true" /> 设置为true
 */
@ContextConfiguration(
        locations={
        		"classpath:META-INF/remote/um-remote.xml",
        		"classpath:META-INF/remote/um-interceptor.xml",
        		"classpath:META-INF/spring-framework.xml",  
                "classpath:META-INF/spring.xml"
        } 
      )
@TransactionConfiguration(defaultRollback = false) // 不自动回滚，否则后续的test中没有初始化的数据
public class InitAppDatabase extends AbstractTransactionalJUnit4SpringContextTests { 
 
    static Logger log = Logger.getLogger(InitAppDatabase.class);    

    @Before
    public void setUp() throws Exception {
        Global.setContext(super.applicationContext);
    }
    
    @Test
    public void initDatabase() {
        log.info("init databse starting......");
 
        String sqlPath = URLUtil.getResourceFileUrl("sql/mysql").getPath();
        H2DBServer.excuteSQL(sqlPath);
 
        OperatorDTO loginUser = new OperatorDTO(UMConstants.ADMIN_USER_ID, UMConstants.ADMIN_USER_NAME);
    	String token = TokenUtil.createToken("1234567890", UMConstants.ADMIN_USER_ID); 
        IdentityCard card = new IdentityCard(token, loginUser);
        Context.initIdentityInfo(card);
        
        log.info("init databse over.");
    }
}
