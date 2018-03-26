package com.training.service;

import com.training.data.UserData;
import com.training.form.UserForm;

public interface UserService {

	UserData findUser(UserForm userForm);

}
