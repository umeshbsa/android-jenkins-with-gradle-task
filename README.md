## android-jenkins-with-gradle-task

**Step 1: Setup simple Jenkins script**
* If you have any doubt to setuo simple jenkins please follow this URL<br/>
  https://github.com/umeshbsa/android-setup-jenkins-automation
 
 **Step 2: Write task in app.gradle file**
   * Please follow app.gradle code.
 ```java
    // Gradle task for app version code
    def appVersionCode = 1
    task(versionCodeTask) {
        if (project.hasProperty("versioncode")) {
            appVersionCode = Integer.parseInt(versioncode); ;
        }
    }

    // Gradle task for promary color
    def primaryColor = '"#FF00FF"'
    task(primaryColorTask) {
        if (project.hasProperty("primarycolor")) {
            primaryColor = primarycolor;
        }
    }
 
 ```
  * You have to set this task variable in product flavor in app.gradle file
  ```java
     productFlavors {
        prod {
            applicationId appApplicationId
            signingConfig signingConfigs.releaseConfig
            buildConfigField "String", "BASE_URL", baseUrl
            buildConfigField "String", "PRIMARY_COLOR", primaryColor
            manifestPlaceholders = [
                    appName: appName,
                    appIcon: appIcon
            ]
            resValue "drawable", "splash_logo", splashLogo
            versionCode appVersionCode
            versionName appVersionName
        }

        qa {
            applicationId appApplicationId
            signingConfig signingConfigs.debugConfig
            buildConfigField "String", "BASE_URL", baseUrl
            buildConfigField "String", "PRIMARY_COLOR", primaryColor
            manifestPlaceholders = [
                    appName: appName,
                    appIcon: appIcon
            ]         
            resValue "drawable", "splash_logo", splashLogo
            versionCode appVersionCode
            versionName appVersionName
        }
    }
  
  ```
  
  **Step 3: Write Jenkins script with all given task from app.gradle file**
  * Go to Jenkins console and Open Build section where we have write all script code in Command edit box.
   <img src="https://github.com/umeshbsa/android-jenkins-with-gradle-task/blob/master/jenkins0.png"/><br/>
  * You have to select one type of apk(Select release or debug apk)
  * Write this jenkins script into Command edit box
```
#!/bin/bash

APP_NAME="Jenkins_Gradle_Task" // As your wish
APPLICATION_ID="com.app.gradle" // As your wish
VERSION_NAME="1.0.0" // As your wish
VERSION_CODE=1 // As your wish
BASE_URL='"https://com.app/"' // As your wish
PRIMARY_COLOR='"#ff00ff"' // As your wish
APP_ICON="/home/umesh/Desktop/icon.png" // As your wish
SPLASH_LOGO="/home/umesh/Desktop/splash_logo.png" // As your wish. Image name must be splash_logo

SRC="/var/lib/jenkins/workspace/icon.png"
DEST1="/var/lib/jenkins/workspace/android-jenkins-with-gradle-task/app/src/main/res/mipmap-xhdpi"
DEST2="/var/lib/jenkins/workspace/android-jenkins-with-gradle-task/app/src/main/res/mipmap-xxhdpi"
DEST3="/var/lib/jenkins/workspace/android-jenkins-with-gradle-task/app/src/main/res/mipmap-xxxhdpi"
DEST4="/var/lib/jenkins/workspace/android-jenkins-with-gradle-task/app/src/main/res/drawable-xxxhdpi"

FILE_NAME_EXT_ICON=$(basename "$APP_ICON")
FILE_NAME_ICON="${FILE_NAME_EXT_ICON%.*}"

FILE_NAME_EXT_SPLASH=$(basename "$SPLASH_LOGO")
FILE_NAME_SPLASH="${FILE_NAME_EXT_SPLASH%.*}"

convert $APP_ICON -resize "96x96" $SRC
cp  $SRC $DEST1

convert $APP_ICON -resize "144x144" $SRC
cp  $SRC $DEST2

convert $APP_ICON -resize "192x192" $SRC
cp  $SRC $DEST3

cp  $SPLASH_LOGO $DEST4

#Start for prod release apk

KEY_STORE_FILE="/home/kiwitech/Documents/key1.jks" // As your wish in jks file setup
KEY_STORE_PASSWORD="654321" // As your wish in jks file setup
KEY_STORE_ALIAS="cbs1" // As your wish in jks file setup
KEY_PASSWORD="123456" // As your wish in jks file setup

./gradlew clean assembleProdRelease task keyStoreFileTask '-Pkeystorefile='$KEY_STORE_FILE task keyStorePasswordTask '-Pkeystorepassword='$KEY_STORE_PASSWORD task keyStoreAliasTask '-Pkeystorealias='$KEY_STORE_ALIAS task keyPasswordTask '-Pkeypassword='$KEY_PASSWORD task versionNameTask '-Pversionname='$VERSION_NAME task appNameTask '-Pappname='$APP_NAME task applicationIdTask '-Papplicationid='$APPLICATION_ID task versionCodeTask '-Pversioncode='$VERSION_CODE task baseUrlTask '-Pbaseurl='$BASE_URL task primaryColorTask '-Pprimarycolor='$PRIMARY_COLOR task appIconTask '-Pappicon=@mipmap/'$FILE_NAME_ICON  task splashLogoTask '-PsplashLogo=@drawable/'$FILE_NAME_SPLASH

#End for prod release apk

#Start for qa debug apk 

./gradlew clean assembleQaDebug task versionNameTask '-Pversionname='$VERSION_NAME task appNameTask '-Pappname='$APP_NAME task applicationIdTask '-Papplicationid='$APPLICATION_ID task versionCodeTask '-Pversioncode='$VERSION_CODE task baseUrlTask '-Pbaseurl='$BASE_URL task primaryColorTask '-Pprimarycolor='$PRIMARY_COLOR task appIconTask '-Pappicon=@mipmap/'$FILE_NAME_ICON  task splashLogoTask '-PsplashLogo=@drawable/'$FILE_NAME_SPLASH

#End for qa debug apk 
```
**Step 4: Setup hockey app or download apk from jenkins console**
* If you have any doubt to setup hockey app please follow this URL<br/>
  https://github.com/umeshbsa/android-setup-jenkins-automation
  
  
  
#### Licence

    Copyright 2018 Umesh Kumar

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

