package com.geekbrains.myfirsttests

import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        EmailValidator.isValidEmail("name@email.com")?.let { assertTrue(it) }
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        EmailValidator.isValidEmail("name@email.co.uk")?.let { assertTrue(it) }
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        EmailValidator.isValidEmail("name@email")?.let { assertFalse(it) }
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        EmailValidator.isValidEmail("name@email..com")?.let { assertFalse(it) }
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        EmailValidator.isValidEmail("@email.com")?.let { assertFalse(it) }
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        EmailValidator.isValidEmail("")?.let { assertFalse(it) }
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        EmailValidator.isValidEmail(null)?.let { assertFalse(it) }
    }

    // отсутствие домена
    @Test
    fun emailValidator_InvalidEmailNoDomain_ReturnsFalse() {
        EmailValidator.isValidEmail("name@email.")?.let { assertFalse(it) }
    }

    //длинный домен
    @Test
    fun emailValidator_TooLongDomain_ReturnsFalse() {
        EmailValidator.isValidEmail("name@email.ThisIsAnExampleOfVeryVeryLongDomain")?.let {
            assertFalse(
                it
            )
        }
    }

    //русские буквы
    @Test
    fun emailValidator_RussianLetters_ReturnsFalse() {
        EmailValidator.isValidEmail("имя@email.ru")?.let { assertFalse(it) }
    }

    //отсутствие собаки
    @Test
    fun emailValidator_NoDogEarEmail_ReturnsFalse() {
        EmailValidator.isValidEmail("nameemail.ru")?.let { assertFalse(it) }
    }

    @Test
    fun emailValidator_Email_NotNull(){
        assertNotNull(EmailValidator.isValidEmail("123"))
    }

    @Test
    fun emailValidator_Email_Null(){
        assertNull(EmailValidator.isValidEmail("1233333333333"))
    }
}
