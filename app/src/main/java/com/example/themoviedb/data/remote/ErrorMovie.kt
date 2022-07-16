package com.example.themoviedb.data.remote

class ErrorMovie(message: String, cause: Throwable?) : Throwable(message, cause)