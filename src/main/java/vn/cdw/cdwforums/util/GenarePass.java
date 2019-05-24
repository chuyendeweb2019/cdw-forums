package vn.cdw.cdwforums.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenarePass {

	public static void main(String[] args) {
		String mk = "quocdao108";
		BCryptPasswordEncoder bp = new BCryptPasswordEncoder();
		System.out.println(bp.encode(mk));
	}
}
