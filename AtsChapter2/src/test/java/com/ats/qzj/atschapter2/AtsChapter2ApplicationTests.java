package com.ats.qzj.atschapter2;

import com.ats.qzj.atschapter2.atsService.DataUtilsServ;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AtsChapter2ApplicationTests {
	@Autowired
	private DataUtilsServ dataUtilsServ;

	@Test
	void contextLoads() {
		System.out.println("");
	}


}
