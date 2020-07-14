package com.secretdiary.studio.config.network

class ApiException : Exception {

    var code: Int
    override var message: String? = null

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.code = code
        this.message = msg
    }
}
