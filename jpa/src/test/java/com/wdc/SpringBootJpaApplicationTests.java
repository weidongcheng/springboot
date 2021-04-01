package com.wdc;

import com.wdc.model.UserDetail;
import com.wdc.param.UserDetailParam;
import com.wdc.service.UserDetailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootJpaApplicationTests {

	@Resource
	private UserDetailService userDetailService;

	@Test
	public void testFindByCondition() {
		int page=0,size=10;
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page,size,sort);
		UserDetailParam param=new UserDetailParam();
		param.setIntroduction("程序员");
		param.setMinAge(10);
		param.setMaxAge(30);
		Page<UserDetail> page1=userDetailService.findByCondition(param,pageable);
		for (UserDetail userDetail:page1){
			System.out.println("userDetail: "+userDetail.toString());
		}
	}

}
