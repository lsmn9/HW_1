package com.geekbrains.myfirsttests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null))
    }

    // отсутствие домена
    @Test
    fun emailValidator_InvalidEmailNoDomain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email."))
    }

    //длинный домен
    @Test
    fun emailValidator_TooLongDomain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email.ThisIsAnExampleOfVeryVeryLongDomain"))
    }

    //русские буквы
    @Test
    fun emailValidator_RussianLetters_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("имя@email.ru"))
    }

    //отсутствие собаки
    @Test
    fun emailValidator_NoDogEarEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("nameemail.ru"))
    }

}
