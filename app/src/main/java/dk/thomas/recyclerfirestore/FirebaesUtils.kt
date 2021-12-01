package dk.thomas.recyclerfirestore

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FirebaesUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()


}

fun uploadData(collection: String, user: User){
    FirebaesUtils().fireStoreDatabase.collection(collection)
        .add(user)
        .addOnSuccessListener {
            Log.d(ContentValues.TAG, "Der blev uploaded")
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG,"Upload fejlede")
        }
}

fun readData(fireStoreCallback: FireStoreCallback){
    FirebaesUtils().fireStoreDatabase.collection("Test")
        .get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list: ArrayList<String> = ArrayList()
                for (word in task.result!!) {
                    val wordHej: String = word.data["hej"].toString()
                    list.add(wordHej)
                }
                fireStoreCallback.onCallBack(list)
            } else {
                Log.d(TAG,"Error getting data")
            }
        }

}

interface FireStoreCallback{
    fun onCallBack(value: List<String>)
}