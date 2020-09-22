package za.co.discovery.assignment;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItsApplicationTest {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void main() {
		ItsApplication.main(new String[] {});
	}
}
