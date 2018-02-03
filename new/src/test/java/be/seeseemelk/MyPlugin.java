

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.seeseemelk.mockbukkit.MockBukkit;

public class MyPluginPluginTest
{
	private ServerMock server;
	private MyPluginPlugin plugin;

	@Before
	public void setUp() throws Exception
	{
		server = MockBukkit.mock();
		plugin = MockBukkit.load(MyPluginPlugin.class);
	}
	
	@After
	public void tearDown()
	{
		MockBukkit.unload();
	}

	@Test
	public void test()
	{
		fail("Not implemented");
	}

}
