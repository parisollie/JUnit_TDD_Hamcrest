package com.cursosandroidant.auth

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


//V-34, paso 1.0, TEST PARA CORREO
class AuthTest {
    @Test
    fun login_complete_returnsTrue(){
        //Ponemos el usuario y contraseña que queremos probar
        val isAtuhenticated = userAuthentication("ant@gmail.com", "1234")
        assertTrue(isAtuhenticated)
    }
    @Test
    fun login_complete_returnsFalse(){
        val isAtuhenticated = userAuthentication("nt@gmail.com", "1234")
        assertFalse(isAtuhenticated)
    }
    @Test
    fun login_emptyEmail_returnsFalse(){
        val isAtuhenticated = userAuthentication("", "1234")
        assertFalse(isAtuhenticated)
    }
    /*  - Paso 1.2,TDD -
    @Test
    fun login_nullEmail_returnsFalse(){
        val isAtuhenticated = userAuthenticationTDD(null, "1234")
        assertFalse(isAtuhenticated)
    }
    @Test
    fun login_nullPassword_returnsFalse(){
        val isAtuhenticated = userAuthenticationTDD("ant@gmail.com", null)
        assertFalse(isAtuhenticated)
    }*/
}