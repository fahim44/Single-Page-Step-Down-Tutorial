package com.lamonjush.singlepagestep_downtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lamonjush.singlepagestepdowntutorial.SinglePageStepDownTutorialListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tutorialView.addItem("Title 1","Description 1","Continue")
        tutorialView.addItem("Title 2","Description 2","Continue")
        tutorialView.addItem("Title 3","Description 3","Finish")
        tutorialView.setUpListener(object : SinglePageStepDownTutorialListener {
            override fun onCompleteStep(stepNumber: Int) {
                Log.d("MainActivity", "done with item : $stepNumber")
            }
        })
        tutorialView.show()
    }
}