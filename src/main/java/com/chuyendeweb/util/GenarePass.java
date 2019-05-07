package com.chuyendeweb.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenarePass {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder p = new BCryptPasswordEncoder();
		System.out.println(p.encode("123"));
	}
}
