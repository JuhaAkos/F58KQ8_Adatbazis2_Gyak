package pack;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sqlSessionFactory;
	private static final String CONFIG_LOCATION="mybatis-config.xml";
	
	public MyBatisUtil() {
		InputStream inputStream = 
				Resources.getResourceAsStream(CONFIG_LOCATION);
				MyBatisUtil.sqlSessionFactory = new SqlSessionFactory.build(inputStream);
	}
	
	public static SqlSessionFactory getFactory() {
		return MyBatisUtil.sqlSessionFactory;
	}
}
