package com.myproject.onideyak.onideyakapi;

import com.myproject.onideyak.onideyakapi.util.Generator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnideyakApiApplicationTests {

	@Test
	void contextLoads() {
		load();
	}

	public void load(){
		Generator generator= new Generator();
		String prefix = generator.generatePrefix(100);
		String u = generator.generatedPrimaryKey(prefix, "U", 500);
		System.out.println(prefix);
		System.out.println(u);
	}

}
