package com.idfinance.debugview.data.model

import com.idfinance.debugview.domain.LogType
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

internal class Log() : RealmObject {
    @PrimaryKey
    var _id = ObjectId()
    var message: String = ""
    var isError: Boolean = false

    constructor(message: String = "", type: LogType = LogType.DEFAULT) : this() {
        this.message = message
        this.isError = type == LogType.ERROR
    }
}