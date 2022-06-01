package com.poly.utilities;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptUtils {
	public String encrypt(String origin) {
		return BCrypt.hashpw(origin, BCrypt.gensalt());
	}

	public boolean check(String origin, String encrypted) {
		return BCrypt.checkpw(origin, encrypted);
	}
}
