package info.toshim.finanzieren.repo;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import info.toshim.finanzieren.domain.Kind;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:test-context.xml", "classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class KindDaoImplTest
{
	@Autowired
	private KindDao kindDao;

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void findByIdで支出を取得する()
	{
		Kind actual = kindDao.findById(1);
		assertThat(actual.getId(), is(1));
		assertThat(actual.getKind(), is("TEST"));
	}
}
