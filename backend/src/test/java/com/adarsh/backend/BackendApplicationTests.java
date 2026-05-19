package com.adarsh.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties={
	"spring.cloud.vault.enabled=false"
})
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
