# JetStick

provides a very simple and ready-to-use Jetpack Compose virtual joystick for Android


## Add To Project
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.manalkaff:JetStick:Tag'
	}
  
  
## Example
To use it, just simply add the JoyStick composable which return the X and Y joystick Offsets when it move
  ```
  JoyStick(size = 100){ x: Float, y: Float ->
      Log.d("JoyStick", "$x, $y")
  }
  ```
  
  ![image](https://user-images.githubusercontent.com/29891473/177016060-ce4b7784-cff1-42c2-bac8-0df2229f385f.png)

```
JoyStick: -36.02227, 1.0222702
JoyStick: -36.02227, 2.0222702
JoyStick: -37.02227, 5.02227
JoyStick: -37.02227, 9.02227
JoyStick: -37.02227, 14.02227
JoyStick: -37.02227, 18.02227
JoyStick: -37.02227, 19.02227
JoyStick: -37.02227, 20.02227
JoyStick: -0.022270203, 0.022270203
````
