package com.jetpack.reader.app.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.jetpack.reader.app.model.MUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel() {
//val loadingState=MutableStateFlow(LoadingState.IDLE)
    private val auth:FirebaseAuth=Firebase.auth
    private val _loading=MutableLiveData(false)
    val loading:LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email:String,password:String,home:()->Unit)= viewModelScope.launch{
try {
    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
        task->if(task.isSuccessful){
        home()
    }else{
        print(task.result.toString())
    }
    }
}catch (ex:Exception){
    print(ex)
}
    }
    fun createUserWithEmailAndPassword(email: String,password: String,home: () -> Unit)=
        viewModelScope.launch{
if (_loading.value==false){
    _loading.value=true
    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
        task->if(task.isSuccessful){
            val displayName= task.result.user?.email?.split("@")?.get(0)
        createUser(displayName)
            home()
    }else{
        print(task.result.toString())

    }
        _loading.value=false
    }
}
    }

    private fun createUser(displayName: String?) {
val userId= auth.currentUser?.uid
//        val user= mutableMapOf<String,Any>()
        val user =MUser(userId = userId.toString(), displayName = displayName.toString(), avatarUrl = "", qoute = "life is great",
        profession = "Android Dev", id = null).toMap()
//        user["user_id"]=userId.toString()
//        user["display_name"]=displayName.toString()

        FirebaseFirestore.getInstance().collection("users").add(user)

    }


}