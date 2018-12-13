# android-jenkins-with-gradle-task

```
#!/bin/bash

////////////// All Input

APP_NAME="CBS_Test_2"
APPLICATION_ID="com.urbanft.cbs.deerwoodbank"
VERSION_NAME="1.1.2"
VERSION_CODE=2
BASE_URL='"https://deerwoodbank-merchant.rdcselect.com/"'
LOCALYTICS_KEY=""
PRIMARY_COLOR='"#ff00ff"'
APP_ICON="/home/kiwitech/Desktop/icon.png"
SPLASH_BANNER="/home/kiwitech/Desktop/icon_splash.png"
LOGIN_BANNER="/home/kiwitech/Desktop/icon_banner.png"
 
if [ -z "$LOCALYTICS_KEY" ]
then     
      LOACLYTICS_ENABLE='false'
else     
      LOACLYTICS_ENABLE='true'
fi

echo "App name: "$APP_NAME
echo "Application id: "$APPLICATION_ID
echo "Version name: "$VERSION_NAME
echo "Version code: "$VERSION_CODE
echo "Base url: "+$BASE_URL
echo "Localytics key: "$LOCALYTICS_KEY
echo "Primary color: "$PRIMARY_COLOR
echo "Localytic enable : "$LOACLYTICS_ENABLE


SRC="/var/lib/jenkins/workspace/icon.png"
DEST1="/var/lib/jenkins/workspace/CBS-Android/app/src/main/res/mipmap-xhdpi"
DEST2="/var/lib/jenkins/workspace/CBS-Android/app/src/main/res/mipmap-xxhdpi"
DEST3="/var/lib/jenkins/workspace/CBS-Android/app/src/main/res/mipmap-xxxhdpi"

DEST4="/var/lib/jenkins/workspace/CBS-Android/app/src/main/res/drawable-xxxhdpi"

 

FILE_NAME_EXT_ICON=$(basename "$APP_ICON")
FILE_NAME_ICON="${FILE_NAME_EXT_ICON%.*}"

echo "App name: "$FILE_NAME_ICON

FILE_NAME_EXT_SPLASH=$(basename "$SPLASH_BANNER")
FILE_NAME_SPLASH="${FILE_NAME_EXT_SPLASH%.*}"

echo "Splash name: "$FILE_NAME_SPLASH

FILE_NAME_EXT_LOGIN=$(basename "$LOGIN_BANNER")
FILE_NAME_LOGIN="${FILE_NAME_EXT_LOGIN%.*}"

echo "Login name: "$FILE_NAME_LOGIN

convert $APP_ICON -resize "96x96" $SRC
cp  $SRC $DEST1
 
convert $APP_ICON -resize "144x144" $SRC
cp  $SRC $DEST2

convert $APP_ICON -resize "192x192" $SRC
cp  $SRC $DEST3


cp  $SPLASH_BANNER $DEST4
cp  $LOGIN_BANNER $DEST4



KEY_STORE_FILE="/home/kiwitech/Documents/key1.jks"
KEY_STORE_PASSWORD="654321"
KEY_STORE_ALIAS="cbs1"
KEY_PASSWORD="123456"

./gradlew clean assembleProductionRelease task keyStoreFileTask '-Pkeystorefile='$KEY_STORE_FILE task keyStorePasswordTask '-Pkeystorepassword='$KEY_STORE_PASSWORD task keyStoreAliasTask '-Pkeystorealias='$KEY_STORE_ALIAS task keyPasswordTask '-Pkeypassword='$KEY_PASSWORD task versionNameTask '-Pversionname='$VERSION_NAME task appNameTask '-Pappname='$APP_NAME task applicationIdTask '-Papplicationid='$APPLICATION_ID task versionCodeTask '-Pversioncode='$VERSION_CODE task baseUrlTask '-Pbaseurl='$BASE_URL task localyticsKeyTask '-Plocalyticskey='$LOCALYTICS_KEY task primaryColorTask '-Pprimarycolor='$PRIMARY_COLOR task appIconTask '-Pappicon=@mipmap/'$FILE_NAME_ICON task localyticsEnableTask '-Plocalyticsenable='$LOACLYTICS_ENABLE task bannerSplashTask '-Pbannersplash=@drawable/'$FILE_NAME_SPLASH task bannerHeaderTask '-Pbannerheader=@drawable/'$FILE_NAME_HEADER
```
