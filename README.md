# Single Page Step-Down Tutorial

[![](https://jitpack.io/v/fahim44/Single-Page-Step-Down-Tutorial.svg)](https://jitpack.io/#fahim44/Single-Page-Step-Down-Tutorial)

It's a helping library to show text based tutorial in a single page in an android app.


[![Screenshot-2020-06-11-23-23-59-517-com-lamonjush-singlepagestep-downtutorial.jpg](https://i.postimg.cc/W4Lbcvsp/Screenshot-2020-06-11-23-23-59-517-com-lamonjush-singlepagestep-downtutorial.jpg)](https://postimg.cc/cvhNfPKP)

## Set-up

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency in your app module's build.gradle file
```
dependencies {
    implementation 'com.github.fahim44:Single-Page-Step-Down-Tutorial:VERSION'
}
```

## HowTo

1. Add the view in the layout file:
```
<com.lamonjush.singlepagestepdowntutorial.SinglePageStepDownTutorial
        android:id="@+id/tutorialView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:buttonBackgroundColor="@android:color/holo_red_dark"
        app:buttonTextColor="@android:color/white"
        app:titleTextColor="@android:color/black"
        app:blockedTitleTextColor="@color/colorBlocked"/>
```

2. Add the tutorial items, listener and the init the view from your Activity/Fragment:
```
val tutorialView : SinglePageStepDownTutorialListener = findViewById(R.id.tutorialView)
tutorialView.addItem("Title 1","Description 1","Continue")
tutorialView.addItem("Title 2","Description 2","Continue")
tutorialView.addItem("Title 3","Description 3","Finish")
tutorialView.setUpListener(object : SinglePageStepDownTutorialListener {
    override fun onCompleteStep(stepNumber: Int) {
        Log.d("MainActivity", "done with item : $stepNumber")
        // if ready to show the next step, call the following line
        tutorialView.moveToNext()
    }
})
tutorialView.show()
```
