package com.colobu.fastjson;

import com.colobu.test.Teacher;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.util.Date;

import static org.junit.Assert.*;

/*
Only handles specific classes by pass class type array to FastJsonProvider's constructor.
 */
public class ClassDefinitionFastJsonProviderTest extends JerseyTest {

	@Path("teacher")
	public static class TeacherResource {
		@GET
		public Teacher getTeacher() {
			Teacher teacher = new Teacher();
			teacher.setId(12345L);
			teacher.setName("smallnest");
			teacher.setCreatedOn(new Date());
			return teacher;
		}
	}

	@Path("user2")
	public static class UserResource {
		@GET
		public User getUser() {
			User user = new User();
			user.setId(12345L);
			user.setName("smallnest");
			user.setCreatedOn(new Date());
			return user;
		}
	}
	
	@Override
	protected void configureClient(ClientConfig config) {		
		Class<?>[] jsonTypes = {User.class};
		config.register(new FastJsonFeature()).register(new FastJsonProvider(jsonTypes));
	}

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig();
		Class<?>[] jsonTypes = {User.class};
		config.register(new FastJsonFeature()).register(new FastJsonProvider(jsonTypes));
		config.packages("com.colobu.fastjson", "com.colobu.test");
		return config;
	}

	@Test
	public void testWriteTo() {
		final String user = target("user2").request().accept("application/json").get(String.class);
		// {"createdOn":1412036891919,"id":12345,"name":"smallnest"}]
		assertTrue(user.indexOf("createdOn") > 0);
		assertTrue(user.indexOf("\"id\":12345") > 0);
		assertTrue(user.indexOf("\"name\":\"smallnest\"") > 0);
	}
	
	@Test
	public void testReadFrom() {
		final User user = target("user2").request().accept("application/json").get(User.class);
		assertNotNull(user);
		assertNotNull(user.getCreatedOn());
		assertEquals(user.getId().longValue(), 12345L);
		assertEquals(user.getName(), "smallnest");
	}
	
	@Test(expected=InternalServerErrorException.class)
	public void testWriteToForTeacher() {		
		final String teacher = target("teacher").request().accept("application/json").get(String.class);
		assertTrue(teacher.indexOf("createdOn") > 0);
		assertTrue(teacher.indexOf("\"id\":12345") > 0);
		assertTrue(teacher.indexOf("\"name\":\"smallnest\"") > 0);
	}

	@Test(expected=InternalServerErrorException.class)
	public void testReadFromForTeacher() {
		final Teacher teacher = target("teacher").request().accept("application/json").get(Teacher.class);
		assertNotNull(teacher);
		assertNotNull(teacher.getCreatedOn());
		assertEquals(teacher.getId().longValue(), 12345L);
		assertEquals(teacher.getName(), "smallnest");
	}

}
