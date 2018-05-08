package com.lswq.model;

import com.lswq.enums.DataStateEnum;
import com.lswq.enums.PersonStatusEnum;

public class User {
	private Integer userId;  
    private String userName;  
    private String userPassword;  
    private PersonStatusEnum status;
    private DataStateEnum state;

    public Integer getUserId() {  
        return userId;  
    }  
  
    public void setUserId(Integer userId) {  
        this.userId = userId;  
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    public String getUserPassword() {  
        return userPassword;  
    }  
  
    public void setUserPassword(String userPassword) {  
        this.userPassword = userPassword;  
    }

    public PersonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PersonStatusEnum status) {
        this.status = status;
    }

    public DataStateEnum getState() {
        return state;
    }

    public void setState(DataStateEnum state) {
        this.state = state;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userPassword='").append(userPassword).append('\'');
        sb.append(", status=").append(status);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
