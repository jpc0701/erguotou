package service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * SqlSession的单例模式
 * @author 人生自古谁无死
 *
 */
public class SqlSessionSingleton {
//	private static Logger logger = LogManager.getLogger(SqlSessionSingleton.class);
    private SqlSessionSingleton() {}
    public static SqlSession getSqlSession(){
        try {
            // 指定配置文件
            String resource = "mybatis-config.xml";
            // 读取配置文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 构建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 获取sqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
//                        SqlSessionSingleton.logger.debug("数据库初始化成功！");
//			System.out.println("数据库初始化成功！");
			return sqlSession;
		} catch (IOException e) {
//						SqlSessionSingleton.logger.error("数据库初始化失败！");
			e.printStackTrace();
		}
        return null;
    }
}
