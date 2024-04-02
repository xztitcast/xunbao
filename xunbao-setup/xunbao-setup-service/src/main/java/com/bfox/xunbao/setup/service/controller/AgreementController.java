package com.bfox.xunbao.setup.service.controller;

import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.setup.entity.Agreement;
import com.bfox.xunbao.setup.i.service.AgreementService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端后台交互 -> 协议控制器
 * @author eden
 * @date 2023年2月15日 下午5:09:11
 */
@RestController
@RequestMapping("/setup/agreement")
public class AgreementController {
	
	@DubboReference
	private AgreementService agreementService;

	@GetMapping("/info")
	public R info(@RequestHeader Long tisid) {
		Agreement entity = agreementService.getAgreementInfo(tisid);
		return R.ok(entity);
	}
}
