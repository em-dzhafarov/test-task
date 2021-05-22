package com.dzhafarov.testtaskapplication.domain.validation

import android.util.Patterns
import javax.inject.Inject

class EmailValidator @Inject constructor() : Validator<String?> {

    override fun validate(input: String?): Boolean {
        return !input.isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }
}
