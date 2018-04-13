package com.example.gdinh.colorblender

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var colorOne: Int = 0
    private var colorTwo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        blending_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                blendColors()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }
    // Function that allows user to pick the first color to be blended
    fun pickColor (view : View){
        // create the color picker intent
        val intent = Intent(this, ColorChooser::class.java)
        startActivityForResult(intent, 1)
    }

    // Function that allows the user to pick the second color to be blended
    fun pickSecondColor(view : View){
        val secondColorIntent = Intent(this, ColorChooser::class.java)
        startActivityForResult(secondColorIntent,2)
    }

    // Blend the two colors together using the seek bar
    private fun blendColors(){
        //Blends the two colors together
        blended_Colors.setBackgroundColor(
                Color.rgb((Color.red(colorOne)*(blending_bar.max-blending_bar.progress) + Color.red(colorTwo)*blending_bar.progress)/blending_bar.max,
                        (Color.green(colorOne)*(blending_bar.max-blending_bar.progress) + Color.green(colorTwo)*blending_bar.progress)/blending_bar.max,
                        (Color.blue(colorOne)*(blending_bar.max-blending_bar.progress) + Color.blue(colorTwo)*blending_bar.progress)/blending_bar.max))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Sets background color of first color chosen
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            colorOne = data!!.getIntExtra("Color", 0)
            colorOne_View.setBackgroundColor(colorOne)
        }
        // Sets background color of second color chosen
        if (requestCode == 2 && resultCode == Activity.RESULT_OK){
            colorTwo = data!!.getIntExtra("Color", 0)
            colorTwo_View.setBackgroundColor(colorTwo)
        }
        blendColors()
    }
}
