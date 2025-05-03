package com.cursosandroidant.auth

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Test

//Paso 3.1
class AuthHamcrestTest {
    //Paso 3.2 given-when-then, deben de tener esta estructura
    /**
     * given -> dado que
     * when -> cuando ,aciones o estados que se van a probar
     * then-> entonces ,resultado esperado
     */
    @Test
    fun loginUser_correctData_returnsSuccessEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "1234")
        //Paso 3.4, le ponemos asserThat y `is`
        //un valor esperado es igual al valor actual de un proceso anterior
        assertThat(AuthEvent.USER_EXIST, `is`(result))
    }

    //V-48,paso 3.5 migramos todo a Hamcrest
    @Test
    fun loginUser_wrongData_returnsFailEvent(){
        val result = userAuthenticationTDD("nt@gmail.com", "1234")
        assertThat(AuthEvent.NOT_USER_EXIST, `is`(result))
    }

    @Test
    fun loginUser_emptyEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("", "1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_emptyPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "")
        assertThat(AuthEvent.EMPTY_PASSWORD, `is`(result))
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent(){
        val result = userAuthenticationTDD("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is`(result))
    }

    @Test
    fun loginUser_invalidEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmailcom", "1234")
        assertThat(AuthEvent.INVALID_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "123e")
        assertThat(AuthEvent.INVALID_PASSWORD, `is`(result))
    }

    @Test
    fun loginUser_invalidUser_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmailcom", "123e")
        assertThat(AuthEvent.INVALID_USER, `is`(result))
    }

    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsException(){
        val result = userAuthenticationTDD(null, "123e")
        assertThat(AuthEvent.NULL_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_nullPassword_returnsException(){
        Assert.assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("ant@gmail.com", null))
        }
    }

    @Test
    fun loginUser_nullForm_returnsException(){
        try {
            val result = userAuthenticationTDD(null, null)
            assertThat(AuthEvent.NULL_FORM, `is`(result))
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertThat(AuthEvent.NULL_FORM, `is`(it.authEvent))
            }
        }
    }

    @Test
    fun loginUser_errorLengthPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmailcom", "123")
        assertThat(AuthEvent.LENGTH_PASSWORD, `is`(result))
    }

    //V-49,paso 3.7, otras validaciones con Hamcrest
    @Test
    //La letra i esta contenido en la palabra Maria
    fun checkNames_differentUsers_match(){
        assertThat("Maria", both(containsString("a")).and(containsString("i")))
    }

    //Paso 3.8
    @Test
    fun checkData_emailPassword_noMatch(){
        val email = "ant@gmail.com"
        val password = "1234"
        //Afirmamos que el correo no es igual al password
        assertThat(email, not(`is`(password)))
    }

    //V-49,Paso 3.9, checando con arreglos
    @Test
    fun checkExist_newEmail_returnsString(){
        val oldEmail = "ant@gmail.com"
        val newEmail = "ant@cursosandroid.com"
        val emails = arrayOf(oldEmail, newEmail)
        assertThat(emails, hasItemInArray(newEmail))
    }

    @Test
    fun checkDomain_arrayEmails_returnsString(){
        val nextEmail = "alain@cursosandroid.com"
        val oldEmail = "ant@gmail.com"
        val newEmail = "ant@cursosandroid.com"
        val emails = arrayListOf(oldEmail, newEmail, nextEmail)
        val newEmails = arrayListOf(newEmail, nextEmail)
        //Verificar que tengan el mismo dpminio
        assertThat(newEmails, everyItem(endsWith("cursosandroid.com")))
    }
}