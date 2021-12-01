package dk.thomas.recyclerfirestore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class SecondActivity : AppCompatActivity(){
    lateinit var recycler:RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var list: ArrayList<User>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        recycler = findViewById<RecyclerView>(R.id.recycler)

        list = arrayListOf()

        myAdapter = MyAdapter(list)

        recycler.adapter = myAdapter

        EventChangeListener()


        /* FirebaseFirestore.getInstance().collection("Test").addSnapshotListener { value, e ->
            if (e != null){
                Log.w(ContentValues.TAG, "failed", e)
                return@addSnapshotListener
            }
            for (i in value!!){
                createUserElement(i)

            }


            val collection = FirebaseFirestore.getInstance().collection("Test").get()
            Log.d("firebase get", "firebase get collection test"+collection.toString())

        }

         */

        recycler.setOnClickListener { startActivity(Intent(this,FirstActivity::class.java)) }

        // create:

        /*FirebaseFirestore.getInstance().collection("Test").addSnapshotListener{ value, e ->
            if (e != null){
                Log.w(ContentValues.TAG, "failed", e)
                return@addSnapshotListener
            }
            for (word in value!!){
                createWordElement(word)
            }
        }*/


    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Test").orderBy("age", Query.Direction.ASCENDING).
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?,
                                 error: FirebaseFirestoreException?
            ) {
                if(error != null){
                    Log.e("Firestore error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if( dc.type == DocumentChange.Type.ADDED){
1                        list.add(dc.document.toObject(User::class.java))
                    }
                }
                myAdapter.notifyDataSetChanged()
            }
        })
    }

    fun createUserElement(user: QueryDocumentSnapshot){
        var element: View = layoutInflater.inflate(R.layout.list_item,null,false)
        var first = element.findViewById<TextView>(R.id.tvFirstNameValue)
        var last = element.findViewById<TextView>(R.id.tvLastNameValue)
        var age = element.findViewById<TextView>(R.id.ageVal)

        first.text = user["firstName"].toString()
        last.text = user["lastName"].toString()
        age.text = user["age"].toString()

        recycler.addView(element)

    }
}