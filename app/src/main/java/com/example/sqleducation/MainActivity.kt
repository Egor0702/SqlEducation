package com.example.sqleducation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editName : EditText
    lateinit var  editEmail : EditText
    lateinit var buttonAdd : Button
    lateinit var buttonRead : Button
    lateinit var buttonClean : Button
    lateinit var textEnd : TextView
    lateinit var sqlClass: SqlClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.edit_text_name)
        editEmail = findViewById(R.id.edit_text_email)
        buttonAdd = findViewById(R.id.button_add)
        buttonRead = findViewById(R.id.button_read)
        buttonClean = findViewById(R.id.button_clean)
        textEnd = findViewById(R.id.text_end)
        sqlClass = SqlClass(this)
        val db = sqlClass.writableDatabase

        buttonAdd.setOnClickListener{
            var name = editName.text.toString()
            var email = editEmail.text.toString()
            if(!(name.equals("") && email.equals(""))){
            sqlClass.addData(db,name,email)
                editName.setText(R.string.Null)
                editEmail.setText(R.string.Null)
            }else
                Toast.makeText(this, R.string.Attention, Toast.LENGTH_SHORT).show()
    }
        buttonRead.setOnClickListener{
        val curs = db.query("NIKS",null, null, null,null, null, null)
            if (curs.moveToFirst()){
                var indexId = curs.getColumnIndex("ID")
                var indexName = curs.getColumnIndex("NAME")
                var indexEmail = curs.getColumnIndex("EMAIL")

                do{
                    textEnd.setText("${curs.getInt(indexId)} " +
                            "имя: ${curs.getString(indexName)} " +
                            "email: ${curs.getString(indexEmail)}")
                }while (curs.moveToNext())
            }else{
                Toast.makeText(this, R.string.Error, Toast.LENGTH_SHORT).show()
            }
        }
        buttonClean.setOnClickListener{
            db.delete("NIKS", null, null)
        }
    }
}