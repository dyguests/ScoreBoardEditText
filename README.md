# ScoreBoardEditText

[![](https://jitpack.io/v/dyguests/ScoreBoardEditText.svg)](https://jitpack.io/#dyguests/ScoreBoardEditText)

A EditText with score board ui style.

![](./graphices/cap1.gif)

## Import

```gradle
	// Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	// Add the dependency
	dependencies {
	        implementation 'com.github.dyguests:ScoreBoardEditText:x.y.z'
	}
```

## Usage

    // also support EditText styleable.

    <declare-styleable name="ScoreBoardEditText">
        <attr name="charBackground" format="reference"/>
        <attr name="charPadding" format="dimension"/>
    </declare-styleable>

## License

Copyright 2018 fanhl

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
