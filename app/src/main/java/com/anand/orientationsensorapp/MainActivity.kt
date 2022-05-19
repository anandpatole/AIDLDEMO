package com.anand.orientationsensorapp


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.anand.mylibrary.IRemoteService
import com.anand.mylibrary.MyService


class MainActivity : AppCompatActivity() {

    lateinit var  serviceStatus:TextView
    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            val service: IRemoteService = IRemoteService.Stub.asInterface(iBinder)
            serviceStatus.text="Service Connected"
//            Toast.makeText(
//                this@MainActivity,
//                service.data,
//                LENGTH_SHORT
//            ).show()

        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            serviceStatus.text="Service Disconnected"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceStatus=findViewById(R.id.service_status)


    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)

    }
}