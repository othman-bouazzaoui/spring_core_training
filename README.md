"# Spring Training XML Annotation and Crud Project MVC and RestApi " 


pom.xml : basic dependencies
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.oth</groupId>
    <artifactId>spring-core-xml</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!-- copy from here -->
    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring.version>6.1.3</spring.version>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
    <!-- copy to here -->
</project>
```

- two layers (DAO and BUSINESS)
  
```java
public interface IDao {
	void getData();
}
```

```java
public class DaoImpl implements IDao {
	@Override
	public void getData() {
		System.out.println("Fetch Data from DB .....");
	}
}
```

```java
public interface IBusiness {
	void fetchData();
}
```

```java
public class BusinessImpl implements IBusiness {

	private IDao dao;

	@Override
	public void fetchData() {
		dao.getData();
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}
}
```

- spring-config.xml file content :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- https://docs.spring.io/spring-framework/reference/core/beans/definition.html -->

    <!-- bean definitions here -->
    <bean id="dao"  class="com.oth.dao.DaoImpl"/>
    <bean id="business" class="com.oth.business.BusinessImpl">
         <property name="dao" ref="dao"/>
        <!-- constructor-arg name="dao" ref="dao"/-->
    </bean>

    <alias name="business" alias="IBusiness"/>

</beans>
```


Main class content for xml config XmlMainConfiguration :
```java
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		context.getBean(IBusiness.class).fetchData();
```

Main class content for annotation config AnnotationsMainConfiguration :
```java
		ApplicationContext context = new AnnotationConfigApplicationContext("com.oth");
		context.getBean(IBusiness.class).fetchData();
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.oth</groupId>
	<artifactId>spring_core_crud_project</artifactId>
	<version>1.0-SNAPSHOT</version>
	<name>spring_core_crud_project</name>
	<!-- copy from here -->
	<packaging>war</packaging>

	<properties>
		<maven.compiler.source>21</maven.compiler.source>
		<maven.compiler.target>21</maven.compiler.target>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<spring.version>6.1.0</spring.version>
		<spring-data-jpa.version>3.5.12</spring-data-jpa.version>
		<hibernate.version>6.4.0.Final</hibernate.version>
		<jakarta-persistence.version>3.1.0</jakarta-persistence.version>
		<hikaricp.version>6.3.2</hikaricp.version>
		<cargo-maven3-plugin.version>1.10.12</cargo-maven3-plugin.version>
		<cargo.servlet.port>9191</cargo.servlet.port>
	</properties>
	
	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<!-- Spring 6+ requires Jakarta EE instead of javax -->
		<dependency>
			<groupId>jakarta.servlet</groupId>
			<artifactId>jakarta.servlet-api</artifactId>
			<version>6.0.0</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
			<version>2.15.2</version>
		</dependency>

		<!-- JSTL with Jakarta EE support -->
		<dependency>
			<groupId>jakarta.servlet.jsp.jstl</groupId>
			<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
			<version>3.0.0</version>
		</dependency>

		<dependency>
			<groupId>org.glassfish.web</groupId>
			<artifactId>jakarta.servlet.jsp.jstl</artifactId>
			<version>3.0.1</version>
		</dependency>

		<!-- Spring Form Tags Support for Jakarta -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>6.1.0</version>
		</dependency>

		<!-- Spring ORM for JPA support -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<!-- Source:
		https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa -->
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-jpa</artifactId>
			<version>${spring-data-jpa.version}</version>
			<scope>compile</scope>
		</dependency>

		<!-- Jakarta Persistence API (Spring 6 requires jakarta instead of
		javax) -->
		<dependency>
			<groupId>jakarta.persistence</groupId>
			<artifactId>jakarta.persistence-api</artifactId>
			<version>${jakarta-persistence.version}</version>
		</dependency>

		<!-- Hibernate ORM for JPA implementation -->
		<dependency>
			<groupId>org.hibernate.orm</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>${hibernate.version}</version>
		</dependency>

		<!-- Connection pool -->
		<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
			<version>${hikaricp.version}</version>
			<exclusions>
				<exclusion>
					<artifactId>tools</artifactId>
					<groupId>com.sun</groupId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<version>9.7.0</version>
			<scope>compile</scope>
		</dependency>

	</dependencies>

	<build>
		<finalName>${project.artifactId}</finalName>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<configuration>
					<warName>${project.artifactId}</warName>
				</configuration>
				<version>3.3.2</version>
			</plugin>
			<!-- https://codehaus-cargo.github.io/cargo/Maven+3+Plugin+Getting+Started.html -->
			<plugin>
				<groupId>org.codehaus.cargo</groupId>
				<artifactId>cargo-maven3-plugin</artifactId>
				<version>${cargo-maven3-plugin.version}</version>
				<configuration>
					<container>
						<containerId>tomcat10x</containerId>
						<type>embedded</type>
					</container>
					<configuration>
						<type>standalone</type>
						<home>${project.build.directory}/tomcat</home>
						<properties>
							<cargo.servlet.port>${cargo.servlet.port}</cargo.servlet.port>
						</properties>
					</configuration>
					<deployables>
						<deployable>
							<groupId>com.oth</groupId>
							<artifactId>spring_core_crud_project</artifactId>

							<type>war</type>
							<properties>
								<!-- The context path for the deployed application -->,
								<context>spring_core_crud_project</context>
							</properties>
						</deployable>
					</deployables>
				</configuration>
			</plugin>
		</plugins>
	</build>
	<!-- copy to here -->
</project>
```

web.xml file content should be place in `src/main/webapp/WEB-INF` directory.

```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                             https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
    <display-name>My Spring WebApp</display-name>

    <!-- Spring Context Loader Listener -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <context-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
    </context-param>

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>com.oth.config.WebConfig</param-value>
    </context-param>

    <!-- Spring DispatcherServlet -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextClass</param-name>
            <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>com.oth.config.WebConfig</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>

```
