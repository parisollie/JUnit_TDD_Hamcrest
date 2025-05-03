package com.cursosandroidant.auth

//V-41,Paso 2.3
class AuthException(val authEvent: AuthEvent, msg: String? = null) : Exception(msg)