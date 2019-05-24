package vn.cdw.cdwforums.controller.form;


import javax.validation.constraints.Size;

import vn.cdw.cdwforums.util.ForumConstants;

public class ChangePasswordForm {

    @Size(min = ForumConstants.PASSWORD_LENGTH_MIN, max = ForumConstants.PASSWORD_LENGTH_MAX)
    private String newPassword;

    @Size(min = ForumConstants.PASSWORD_LENGTH_MIN, max = ForumConstants.PASSWORD_LENGTH_MAX)
    private String newConfirmPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}

	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
	}
    
    
}
