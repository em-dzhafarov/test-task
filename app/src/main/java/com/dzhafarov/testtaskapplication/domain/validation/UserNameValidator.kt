package com.dzhafarov.testtaskapplication.domain.validation

import javax.inject.Inject

class UserNameValidator @Inject constructor(): Validator<String?> {

    override fun validate(input: String?): Boolean {
        return !input.isNullOrBlank()
    }
}