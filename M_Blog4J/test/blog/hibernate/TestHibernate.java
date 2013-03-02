package blog.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
public class TestHibernate {
	
	private static SessionFactory sf = null;
	@BeforeClass
	public static void beforeClass(){
		sf = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	@Test
	public void test() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
}
