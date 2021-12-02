package dk.thomas.recyclerfirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList:ArrayList<User>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var onItemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user: User = userList[position]
        holder.firstName.text = user.firstName
        holder.lasNme.text = user.lastName
        holder.age.text = user.age.toString()
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val firstName: TextView = itemView.findViewById(R.id.tvFirstNameValue)
        val lasNme: TextView = itemView.findViewById(R.id.tvLastNameValue)
        val age: TextView = itemView.findViewById(R.id.ageVal)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(userList[adapterPosition])
            }
        }

    }
}