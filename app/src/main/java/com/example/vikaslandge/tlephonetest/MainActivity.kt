package com.example.vikaslandge.tlephonetest

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
 import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)
        val sms = ContextCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)
        val rps = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE)
        val internet =ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)
        val write =ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (call == PackageManager.PERMISSION_GRANTED && sms == PackageManager.PERMISSION_GRANTED && rps == PackageManager.PERMISSION_GRANTED
                    &&internet == PackageManager.PERMISSION_GRANTED && write == PackageManager.PERMISSION_GRANTED){

            run()

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(
                    android.Manifest.permission.CALL_PHONE,
                    android.Manifest.permission.SEND_SMS,android.Manifest.permission.READ_PHONE_STATE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET
            ),111)
        }



     }//onCreate

    fun run(){

        b1.setOnClickListener(){
            if (!et1.text.toString().isEmpty()){
                if(!et2.text.toString().isEmpty()) {
                    var sManager = SmsManager.getDefault()
                    sManager.sendTextMessage(et1.text.toString(), null, et2.text.toString(), null, null)
                    Toast.makeText(this, "Sending.....", Toast.LENGTH_LONG).show()
                 }else{
                    Toast.makeText(this,"type msg",Toast.LENGTH_LONG).show()
                }
            } else {

                Toast.makeText(this, "Number is mandatory", Toast.LENGTH_LONG).show()
            }
        }
        btncall.setOnClickListener() {
            if (!et1.text.toString().isEmpty()) {
                var i = Intent()
                i.action = Intent.ACTION_CALL
                i.data = Uri.parse("tel:${et1.text.toString()}")
                startActivity(i)

            } else {

                Toast.makeText(this, "Number is mandatory", Toast.LENGTH_LONG).show()
            }
        }
        attach.setOnClickListener(){
            var adiaolg  = AlertDialog.Builder(this)
            adiaolg.setTitle("Attachment")
            adiaolg.setMessage("attch the document to mail")
            adiaolg.setIcon(R.drawable.ic_attach_file_black_24dp)
            adiaolg.setPositiveButton("Camera", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                     var i = Intent("android.media.action.IMAGE_CAPTURE")
                    startActivityForResult(i,123)
                }
            })
            adiaolg.show()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED&& grantResults[1]==PackageManager.PERMISSION_GRANTED
                && grantResults[2]== PackageManager.PERMISSION_GRANTED && grantResults[3]== PackageManager.PERMISSION_GRANTED
                && grantResults[4]==PackageManager.PERMISSION_GRANTED){

            run()

        }
        else{
            Toast.makeText(this,"u cant continue with app without permissions",Toast.LENGTH_LONG).show()
        }
    }
}
