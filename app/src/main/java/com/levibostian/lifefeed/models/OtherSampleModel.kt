package com.levibostian.lifefeed.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class OtherSampleModel : RealmObject() {

    @PrimaryKey var id: Long = 0
    @Required var first_name: String = ""
    @Required var last_name: String = ""
    @Required var username: String = ""

    fun firstLastName(): String = first_name + " " + last_name

}