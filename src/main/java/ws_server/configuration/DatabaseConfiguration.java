package ws_server.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")	//application.properties를 사용할 수 있도록 설정 파일의 위치를 지정
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	//application.properties에 설정했던 디비 관련 정보를 사용하도록 지정
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")	// prefix로 시작하는 설정을 이용해서 히카리CP의 설정 파일을 만듭니다.
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	//앞서 만든 히카리CP의 설정 파일을 이용해서 데이터베이스와 연결하는 데이터 소스를 생성
	@Bean
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println("-----------dataSource : " + dataSource.toString() + "-------------");
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		//스프링-마이바티스에서는 SqlSessionFactory를 생성하기 위해서 SqlSessionFactoryBean을 사용합니다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		//앞서 만든 데이터 소스를 설정합니다.
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		//마이바티스 매퍼 파일의 위치를 설정. 매퍼는 애플리케이션에서 사용할 SQL을 담고 있는 SQL을 담고 있는 XML파일을 의미. 패턴을 기반으로 한번에 등록하는 것이 좋다.
		// /mapper/**/ - mapper 폴더 밑의 모든 폴더를 의미합니다.
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")	//application.properties의 설정 중 마이바티스에 관련된 설정을 가져옵니다.
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();	//가져온 마이바티스 설정을 자바 클래스로 만들어서 반환합니다.
	}
	
}
