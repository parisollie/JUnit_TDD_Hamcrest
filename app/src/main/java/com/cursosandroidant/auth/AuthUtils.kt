package com.cursosandroidant.auth


//Datos para ingresar a la app
fun userAuthentication(email: String, password: String): Boolean {
    if (email == "ant@gmail.com" && password == "1234"){
        return true
    }
    return false
}
/*
  V-35,Paso 1.1
  TDD
  Test driven development
 */
fun userAuthenticationTDD(email: String?, password: String?): AuthEvent {

    //Paso 2.8
    if (email == null && password == null) throw AuthException(AuthEvent.NULL_FORM)
    //Paso 2.5
    if (email == null) throw AuthException(AuthEvent.NULL_EMAIL)
    //Paso 2.7
    if (password == null) throw AuthException(AuthEvent.NULL_PASSWORD)


    if (email.isEmpty() && password.isEmpty()) return AuthEvent.EMPTY_FORM
    //Paso 1.9
    if (email.isEmpty()) return AuthEvent.EMPTY_EMAIL
    //Paso 1.12
    if (password.isEmpty()) return AuthEvent.EMPTY_PASSWORD

    //Paso 2.10
    if (password.length != 4) return AuthEvent.LENGTH_PASSWORD
    else {
        //Paso 1.15
        val passwordNumeric = password.toIntOrNull()

        //Paso 2.1,refactorizamos para no hacer redundancias
        return if (!isEmailValid(email) && passwordNumeric == null) AuthEvent.INVALID_USER
        else if (!isEmailValid(email)) AuthEvent.INVALID_EMAIL
        //Paso 1.16
        else if (passwordNumeric == null) AuthEvent.INVALID_PASSWORD
        else {
            //Paso 1.7
            return if (email == "ant@gmail.com" && password == "1234") AuthEvent.USER_EXIST
            else AuthEvent.NOT_USER_EXIST
        }
    }
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email);
}