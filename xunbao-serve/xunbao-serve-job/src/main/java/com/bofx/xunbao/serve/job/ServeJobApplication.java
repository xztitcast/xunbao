package com.bofx.xunbao.serve.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务JOB启动类
 * 需要配合xxl-job服务,xxl-job服务需要单独部署一份然后配置注册
 * @author Eden
 * @date 2023年9月16日 下午7:36:32
 */
@SpringBootApplication
public class ServeJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeJobApplication.class, args);
	}

}
