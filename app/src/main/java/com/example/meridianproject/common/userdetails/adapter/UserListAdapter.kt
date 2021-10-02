package com.example.meridianproject.common.userdetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meridianproject.R
import com.example.meridianproject.common.userdetails.model.UserDetailslist
import com.example.meridianproject.utils.PicassoCircleTransformation
import com.squareup.picasso.Picasso


class UserListAdapter  (val posts:ArrayList<UserDetailslist>): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UserListAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_user_list, parent, false)
        return UserListAdapter.ViewHolder(view,this)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.tv_firtName.text = posts[position].first_name
        holder.tv_SecondName.text = posts[position].last_name
        holder.tv_Useremail.text = posts[position].email

                Picasso.get()
                    .load(posts[position].avatar)//data!!.data!!.image
                    .transform(PicassoCircleTransformation())
                    .into(holder.iv_Img)


    }

    class ViewHolder(itemView: View, adapter:UserListAdapter) : RecyclerView.ViewHolder(itemView) {

        val tv_firtName: TextView = itemView.findViewById(R.id.tv_firstName)
        val tv_SecondName: TextView = itemView.findViewById(R.id.tv_SecondName)
        val tv_Useremail: TextView = itemView.findViewById(R.id.tv_UserEmail)
        val iv_Img :ImageView = itemView.findViewById(R.id.iv_Image)
    }
}