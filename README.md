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
	        implementation 'com.github.manalkaff:JetStick:v1.2'
	}
  
  
## Example
To use it, just simply add the JoyStick composable which return the X and Y joystick Offsets when it move
  ```
  JoyStick(
	Modifier.padding(30.dp),
	size = 150.dp,
	dotSize = 30.dp
  ){ x: Float, y: Float ->
      Log.d("JoyStick", "$x, $y")
  }
  ```
  
  ![image](https://user-images.githubusercontent.com/29891473/177016060-ce4b7784-cff1-42c2-bac8-0df2229f385f.png)

```
JoyStick: 0.7898413, 0.61714286
JoyStick: 0.7847619, 0.61714286
JoyStick: 0.7847619, 0.62222224
JoyStick: 0.7847619, 0.62222224
JoyStick: 0.7847619, 0.62222224
JoyStick: 0.0025396824, -0.0025396824
````
