package com.anand.mylibrary

import android.app.Service
import android.content.Intent
import android.os.IBinder


class MyService : Service() {


   val binder: IRemoteService.Stub= object :IRemoteService.Stub()
   {
       override fun getData(): String {
          return "test"
       }

   }
    override fun onBind(intent: Intent): IBinder {
      return binder
    }
}