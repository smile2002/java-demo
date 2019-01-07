package cn.smile;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
public class ShirowebdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShirowebdemoApplication.class, args);
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest req) {

		Subject subject = SecurityUtils.getSubject();
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);

		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			return "Login failed!";
		}
		String sId = subject.getSession(false).getId().toString();
		return "Login Successfully! sId = " + sId;
	}

	@RequestMapping("/app1")
	@ResponseBody
	public String app1(HttpServletRequest req) {
		Subject subject = SecurityUtils.getSubject();
		String sId = subject.getSession(false).getId().toString();
		String name = subject.getPrincipal().toString();
		Session session = subject.getSession();
		System.out.println("SESSION CONTENT: ");
		for (Object key : session.getAttributeKeys()) {
			System.out.println("[" + key.toString() + "] = " + session.getAttribute(key));
		}
		StringBuilder builder = new StringBuilder();
		builder.append("this is app1. ");
		builder.append("sId = " + sId + ". ");
		builder.append("name = " + name + ". ");
		builder.append("tid = " + Thread.currentThread().getId());
		return builder.toString();
	}

	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest req) {
		Subject subject = SecurityUtils.getSubject();
		String name = null;
		Object principal = null;
		if (subject != null) { principal = subject.getPrincipal(); }
		if (principal != null) { name = principal.toString(); }
		boolean succ = false;
		if (name != null) {
			try {
				subject.logout();
				succ = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "User = " + name + (succ?" Logout OK.":" Logout Err");
		}
		return "您未登陆!";

	}
}
