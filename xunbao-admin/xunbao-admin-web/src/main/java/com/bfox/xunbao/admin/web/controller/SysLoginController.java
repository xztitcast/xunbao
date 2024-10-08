package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.event.SysLoginEvent;
import com.bfox.xunbao.admin.web.modelAndView.model.SmsModel;
import com.bfox.xunbao.common.core.Constant;
import com.bfox.xunbao.common.core.Constant.RedisKey;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.S;
import com.bfox.xunbao.common.core.utils.IPUtil;
import com.google.code.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 管理系统登陆验证码控制器
 * @author eden
 * @time 2022年7月22日 上午11:36:39
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends BaseController implements ApplicationEventPublisherAware {

	@Autowired
	private Producer producer;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * 获取图片码
	 * @param response
	 * @param uuid
	 * @throws Exception
	 */
	@GetMapping("/captcha.jpg")
	public void captcha(HttpServletResponse response, @Validated @NotBlank(message = "uuid不能为空!") @RequestParam("uuid") String uuid) throws Exception {
		String text = this.producer.createText();
		String cap = text.substring(0, text.lastIndexOf("@"));
		String code = text.substring(text.lastIndexOf("@") + 1);
		BufferedImage image = this.producer.createImage(cap);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", bos);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/gif");
		ServletOutputStream sos = response.getOutputStream();
		sos.write(bos.toByteArray());
		sos.flush();
		sos.close();
		redisTemplate.opsForValue().set(uuid, code, 5, TimeUnit.MINUTES);
	}

	/**
	 * 双因子短信验证
	 * 为了防刷短信，必须是用户密码登录成功后再验证短信
	 * 短信验证成功后台TOKEN将在该方法生成
	 * @param sm
	 * @return
	 */
	@PostMapping ("/smsVerify")
	public R smsVerify(@Validated @RequestBody SmsModel sm) {
		String redisKey = RedisKey.SYS_SMS_MOBILE_KEY.concat(sm.getMobile());
		String value = this.redisTemplate.opsForValue().get(redisKey);
		if(StringUtils.isBlank(value)) {
			return R.error(S.CODE_EXPIRE);
		}
		this.redisTemplate.delete(redisKey);
		if(!value.equals(sm.getParam())) {
			return R.error(S.CODE_ERROR);
		}
		String token = UUID.randomUUID().toString().replace("-", "");
		return R.ok(token);
	}

	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	public R logout(HttpServletRequest request) {
		this.applicationEventPublisher.publishEvent(new SysLoginEvent(getUser().getUsername(), IPUtil.getIpAddr(request)));
		redisTemplate.delete(RedisKey.SYS_SESSION_ID_KEY.concat(request.getHeader(Constant.Sys.TOKEN)));
		SecurityContextHolder.clearContext();
		return R.ok();
	}

	/**
	 * 发送短信验证吗
	 * 后台发送短信验证码必须要有账号密码登录成功的凭证才能发送防刷短信接口
	 * @param sm 凭证
	 * @return
	 */
	@PostMapping("/send")
	public R send(@Validated @RequestBody SmsModel sm) {
		if(!this.redisTemplate.hasKey(RedisKey.SYS_SMS_ID_KEY.concat(sm.getParam()))) {
			return R.error(S.TOKEN_EXPIRE);
		}
		//存在发送短信并删除掉该token
		return R.ok();
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
