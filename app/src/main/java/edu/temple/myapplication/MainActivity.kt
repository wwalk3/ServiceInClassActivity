package edu.temple.myapplication

import android.content.ComponentName
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var timerBinder : TimerService.TimerBinder

    var isConnected = false

//    val timerHandler = Handler(Looper.getMainLooper()){
//        true
//    }

    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            timerBinder = service as TimerService.TimerBinder
            isConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isConnected = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<Button>(R.id.startButton).setOnClickListener {
//            timerBinder.start(100)
//        }
//
//        findViewById<Button>(R.id.pauseButton).setOnClickListener {
//            timerBinder.pause()
//        }
//
//        findViewById<Button>(R.id.stopButton).setOnClickListener {
//            timerBinder.stop()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_start_player -> {
               if (isConnected) timerBinder.start(100)
            }

            R.id.action_pause_player -> {
               if (isConnected) timerBinder.pause()
            }

            R.id.action_stop_player -> {
                if (isConnected) timerBinder.stop()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}