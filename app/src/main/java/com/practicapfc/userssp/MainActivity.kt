package com.practicapfc.userssp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.practicapfc.userssp.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() , OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private  lateinit var  linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter= UserAdapter(getUsers(),this)
        linearLayoutManager=LinearLayoutManager(this)

        val preferences=getPreferences(Context.MODE_PRIVATE)

        val isfirtsTime = preferences.getBoolean(getString(R.string.sp_firtsTime),true)
        Log.i("SP","${getString(R.string.sp_firtsTime)} = $isfirtsTime")

        if(isfirtsTime){
            val dialogView = layoutInflater.inflate(R.layout.dialog_register,null)
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm,{ dialogInterface, i ->
                    val username = dialogView.findViewById<TextInputEditText>(R.id.etUsername)
                        .text.toString()
                    with(preferences.edit()){
                        preferences.edit()
                            .putBoolean(getString(R.string.sp_firtsTime),false).commit()
                            putString(getString(R.string.sp_username), username)
                                .apply()
                    }
                    Toast.makeText(this,R.string.register_success,Toast.LENGTH_SHORT)
                })
                .setNeutralButton("Invitado", null)
                .show()
        } else {
            val username = preferences.getString(getString(R.string.sp_username),getString(R.string.hint_username))
            Toast.makeText(this,"Bienvenido $username", Toast.LENGTH_SHORT).show()
        }


        binding.reyclerView.apply {
            setHasFixedSize(true)
            layoutManager=linearLayoutManager
            adapter=userAdapter
        }

    }



    private fun getUsers(): MutableList<User>{
        val users= mutableListOf<User>()
        val Daniel=User(1,"Daniel","Obrero","https://media-exp1.licdn.com/dms/image/C5603AQEPem-Sysz7Cg/profile-displayphoto-shrink_200_200/0/1538980879362?e=1653523200&v=beta&t=cj1G0FWyAL-fRDVrA8_MyQxBKOaqa23bO-y4E190ULk")
        val Samanta=User(2,"Samanta","Mesa","https://citas.in/media/authors/none_Q7U1j6X.jpeg")
        val Javier=User(3,"Javier","Gomez","https://static2.abc.es/media/summum/2021/07/30/javierrey1-kZ5F--620x349@abc.jpeg")
        val emma=User(4,"Emma","Cruz","https://citas.in/media/authors/emma-roberts_nzwyAZ9.jpeg")

        users.add(Daniel)
        users.add(Samanta)
        users.add(Javier)
        users.add(emma)
        users.add(Daniel)
        users.add(Samanta)
        users.add(Javier)
        users.add(emma)
        users.add(Daniel)
        users.add(Samanta)
        users.add(Javier)
        users.add(emma)
        users.add(Daniel)
        users.add(Samanta)
        users.add(Javier)
        users.add(emma)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this,"$position : ${user.getFullName()}",Toast.LENGTH_SHORT).show()
    }
}