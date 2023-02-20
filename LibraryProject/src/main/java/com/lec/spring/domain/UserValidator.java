package com.lec.spring.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");
        // ↓ 검증할 객체의 클래스 타입인지 확인 : WriteDTO = clazz; 가능여부
        boolean result = User.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        String username = user.getUsername();
        if(username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "사용자 아이디는 필수 입니다");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "사용자 이름은 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "사용자 비밀번호는 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "사용자 전화번호는 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "사용자 이메일는 필수입니다");

        // 입력 password, re_password 가 동일한지 비교
        if(!user.getPassword().equals(user.getRe_password())){
            errors.rejectValue("re_password", "비밀번호가 일치하지 않습니다");
        }

    }
}
