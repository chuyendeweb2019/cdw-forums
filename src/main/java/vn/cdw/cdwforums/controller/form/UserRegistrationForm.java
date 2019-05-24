package vn.cdw.cdwforums.controller.form;


import javax.validation.constraints.Size;

import vn.cdw.cdwforums.util.ForumConstants;

public class UserRegistrationForm {

    @Size(min = ForumConstants.USERNAME_LENGTH_MIN, max = ForumConstants.USERNAME_LENGTH_MAX)
    private String username;


    private String email;

    @Size(min = ForumConstants.PASSWORD_LENGTH_MIN, max = ForumConstants.PASSWORD_LENGTH_MAX)
    private String password;

    @Size(min = ForumConstants.PASSWORD_LENGTH_MIN, max = ForumConstants.PASSWORD_LENGTH_MAX)
    private String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


    
}