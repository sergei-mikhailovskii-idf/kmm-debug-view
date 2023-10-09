package com.idfinance.debugview.data.model

import com.idfinance.debugview.domain.LogType
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

internal class Log() : RealmObject {
    @PrimaryKey
    var _id = ObjectId()
    var tag: String = ""
    var message: String = ""
    var isError: Boolean = false

    constructor(tag: String = "", message: String = "", type: LogType = LogType.DEFAULT) : this() {
        this.tag = tag
        this.message = message
        this.isError = type == LogType.ERROR
    }
}