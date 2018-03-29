package com.media.ops.backend.controller.request;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加管理员，传入的requestBean
 *
 */
public class UserAddRequestBean {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户", required = true)
    private String account;

    /**
     * 密码
     * @return
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 3, max = 16, message = "请输入3-16位密码")
    @ApiModelProperty(value = "密码，必填", required = true)
    private String password;
    
    @ApiModelProperty(value = "姓名，选填")
    private String trueName;
    
    @Email(message="邮箱格式不正确")
    @ApiModelProperty(value = "邮箱，必填", required = true)
    private String email;
    
    @ApiModelProperty(value = "手机号码，必填", required = true)
    private String phone;
    @ApiModelProperty(value = "用户类型，0-为管理员，1-为直播员，必填", required = true)
    private int type;
    @ApiModelProperty(value = "找回密码问题，选填")
    private String question;
    @ApiModelProperty(value = "找回密码答案，选填")
    private String answer;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
    
}
