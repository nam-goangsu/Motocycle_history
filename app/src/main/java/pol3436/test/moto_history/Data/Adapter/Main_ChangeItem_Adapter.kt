/*
package pol3436.test.moto_history.Data.Adapter

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pol3436.test.moto_history.R


class Main_ChangeItem_Adapter :
    RecyclerView.Adapter<Main_ChangeItem_Adapter.MyViewHolder>() {

    companion object {
        public var checkboxList = SparseBooleanArray()
        public var userList = arrayListOf<User>()
    }
     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         return MyViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
         )
     }

     override fun getItemCount(): Int {
         return if(userList.isNullOrEmpty()){0}else{userList.size}
     }

     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val currentItem = userList[position]

         holder.itemView.id_txt.text = (position+1).toString()
         holder.itemView.id_txt.isChecked = checkboxList[position]
         Log.d(TAG, "CLicked : "+ checkboxList[position] )

         holder.itemView.firstName_txt.text = currentItem.firstName
         holder.itemView.lastName_txt.text = currentItem.lastName
         holder.itemView.age_txt.text = currentItem.age.toString()

         holder.itemView.id_txt.setOnClickListener {
             Log.d(TAG, "CLick" )
             if(!holder.itemView.id_txt.isChecked){
                 Log.d(TAG, "CLick list " + checkboxList.toString()+ " / " + userList.toString())
                 checkboxList.delete(position)
                 //checkboxList.put(position,false)
             }
             else{

                 checkboxList.put(position,true)

                 Log.d(TAG, "CLick true "+position  )
             }
             notifyItemChanged(position)
         }
         holder.itemView.rowLayout.setOnClickListener {
             val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
             holder.itemView.findNavController().navigate(action)
         }
     }
    fun setData(user: List<User>) {
        //유저리스트가 변경 되었을때, 업데이트해줍니다.
        userList = user as ArrayList<User>
        notifyDataSetChanged()
    }

 }*/
