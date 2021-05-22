package com.dzhafarov.testtaskapplication.domain.validation

interface Validator<T> {
    fun validate(input: T): Boolean
}
