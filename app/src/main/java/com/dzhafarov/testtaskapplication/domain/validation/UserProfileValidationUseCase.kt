package com.dzhafarov.testtaskapplication.domain.validation

import javax.inject.Inject

class UserProfileValidationUseCase @Inject constructor(
    private val userNameValidator: UserNameValidator,
    private val emailValidator: EmailValidator
) {

    fun validate(userName: String?, email: String?): Boolean {
        return userNameValidator.validate(userName) && emailValidator.validate(email)
    }
}