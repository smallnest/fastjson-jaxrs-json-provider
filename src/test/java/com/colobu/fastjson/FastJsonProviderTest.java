package com.colobu.fastjson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class FastJsonProviderTest extends JerseyTest {

	@Path("user")
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
		config.register(new FastJsonFeature()).register(FastJsonProvider.class);
	}

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig config = new ResourceConfig();
		config.register(new FastJsonFeature()).register(FastJsonProvider.class);
		config.packages("com.colobu.fastjson");
		return config;
	}

	@Test
	public void testWriteTo() {
		final String user = target("user").request().accept("application/json").get(String.class);
		// {"createdOn":1412036891919,"id":12345,"name":"smallnest"}]
		assertTrue(user.indexOf("createdOn") > 0);
		assertTrue(user.indexOf("\"id\":12345") > 0);
		assertTrue(user.indexOf("\"name\":\"smallnest\"") > 0);
	}

	@Test
	public void testReadFrom() {
		final User user = target("user").request().accept("application/json").get(User.class);
		assertNotNull(user);
		assertNotNull(user.getCreatedOn());
		assertEquals(user.getId().longValue(), 12345L);
		assertEquals(user.getName(), "smallnest");
	}

}
