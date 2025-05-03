package com.cursosandroidant.auth

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test
import java.lang.NullPointerException

//V-36 , paso 1.3
class AuthTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "1234")
        assertEquals(AuthEvent.USER_EXIST, result)
    }

    //V-37,paso 1.6
    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val result = userAuthenticationTDD("nt@gmail.com", "1234")
        assertEquals(AuthEvent.NOT_USER_EXIST, result)
    }

    //Paso 1.8
    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("", "1234")
        assertEquals(AuthEvent.EMPTY_EMAIL, result)
    }

    //Paso 1.11
    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "")
        assertEquals(AuthEvent.EMPTY_PASSWORD, result)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val result = userAuthenticationTDD("", "")
        assertEquals(AuthEvent.EMPTY_FORM, result)
    }

    //V-38,Paso 1.13
    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmailcom", "1234")
        assertEquals(AuthEvent.INVALID_EMAIL, result)
    }

    //Paso 1.14
    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "123e")
        assertEquals(AuthEvent.INVALID_PASSWORD, result)
    }

    //Paso 2.0,usuario invalido
    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmailcom", "123e")
        assertEquals(AuthEvent.INVALID_USER, result)
    }

    //Paso 2.2, retornamos una excepcion, el correo es null y le
    //decimos que esperamos una excepcion
    @Test(expected = AuthException::class)
    //Paso 2.4 retotornamos la clase personaliazada
    fun login_nullEmail_returnsException(){
        val result = userAuthenticationTDD(null, "123e")
        assertEquals(AuthEvent.NULL_EMAIL, result)
    }


    //V-42,Paso 2.6
    @Test
    fun login_nullPassword_returnsException(){
        assertThrows(AuthException::class.java){
            print(userAuthenticationTDD("ant@gmail.com", null))
        }
    }

    //V-43, paso 2.7
    @Test
    fun login_nullForm_returnsException(){
        try {
            val result = userAuthenticationTDD(null, null)
            assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception) {
            //Casteamos la excepcion
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    //Tarea, paso 2.9
    //@Ignore(falta definir algo del cliente), con esto ignora la prueba
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "123")
        assertEquals(AuthEvent.LENGTH_PASSWORD, isAuthenticated)
    }

}