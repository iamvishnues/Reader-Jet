package com.jetpack.reader.app.model

data class MUser(val id:String?,val userId:String,
                 val displayName:String,
                 val avatarUrl:String,
                 val qoute :String,
                 val profession: String){
    fun toMap():MutableMap<String,Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "qoute" to this.qoute,
            "profession" to this.profession,
            "avatar_url" to this.avatarUrl
        )
    }
}
