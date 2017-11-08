Hotmob
======

Mobile Advertising with Hotmob, the first and largest mobile ad network in Hong Kong, where monetizes the mobile taffic of the top ranked publishers into revenue and meanwhile connects advertisers to target audience effectively.

Visit http://www.hot-mob.com/ for more details.

How To Get Started
------------------

* [Download Hotmob Android SDK](https://github.com/hotmobmobile/hotmob-android-sdk/archive/master.zip) and try out the included sample project
* Read the ["Getting Started" guide](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Getting-Started), ["Overview"](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Overview) , or [other articles on the Wiki](https://github.com/hotmobmobile/hotmob-android-sdk/wiki)
* Check out the [documentation](https://github.com/hotmobmobile/hotmob-android-sdk/wiki) for a comprehensive look at all of the APIs available in Hotmob SDK
* Read the [Hotmob SDK 4.0 Migration Guide](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/HotmobSDK-4.0-Migration-Guide) for an overview of the architectural changes from 3.0 or below

Integration 
---------------

### Android Studio

1.) Add Hotmob repositories in your project gradle.
```groovy
repositories {
    maven {
        credentials {
            username <Hotmob_provided_username>
            password <Hotmob_provided_password>
        }
        url "http://sdk.hot-mob.com/artifactory/libs-release-local"
    }
}
```
You need to request for unique credentials for Hotmob repositories per application. It is not recommended to use Hotmob testing account in our example provided.

2.) Import HotmobSDK dependency.
```groovy
dependencies {
    compile 'com.hotmob.sdk:hotmob_android_sdk:4.3.0'
}
```

#### Migrating to HotmobSDK 4.3

If you are already using previous version of HotmobSDK, you need to do the following steps in order to upgrade your SDK to the latest 4.3.

1.) Remove HotmobSDK module and add HotmobSDK dependency in Gradle.

2.) Remove all HotmobSDK related activities in your Android Manifest.

### Eclipse

Eclipse implementation of Hotmob SDK is no longer supported. HotmobSDK.jar will be provided per request for Eclipse project integration.

Requirements
------------
| HotmobSDK Version     | Minimum Android Target         | Notes |
| --------              |---------                       |-------|
| 4.3.x                 | Android level 15               |   Remove Eclipse support<br />New ad exchange support   |
| 4.x                   | Android level 15               |   New advertisement format   |
| 3.x                   | Android level 9                |   Architecture optimization    |

## Implement

1.) Following the HotmobAndroidSDKExampleBaseActivity.java in HotmobSDK example project to modify your base activity class.

2.) Start the HotmobSDK in first activity in your project.

``` java
HotmobManager.start(this);
HotmobManager.setDebug(true);
```

3.) Apply Activity Handling and Fragment Handling (if the app is a fragment-based application) into all Activities and Fragments. Please refer to [Getting Started](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Getting-Started#integrate-hotmob-android-sdk-in-activity-based-application) for details.

> You can try to implement following code to confirm `HotmobSDK`  is functional in your project.
``` java
// Add to `onCreate` at Launcher activity in your project
HotmobManager.getPopup(this, listener, "launch_popup", "hotmob_uat_android_image_inapp_popup", true, false);
```

Basic Usage
-----------

### Popup
To create the Hotmob Popup can refercence following step.

1.) Start the HotmobManager Service
```java

//Start HotmobSDK service
HotmobManager.start(this);

//To enable debug mode on HotmobSDK
HotmobManager.setDebug(true);
```

2.) Create the HotmobManagerListener for the callback method.
```java
HotmobManagerListener listener = new HotmobManagerListener() {
     @Override
     public void didLoadBanner(View bannerView) {
     }
};
```

3.) Request the HotmobPopup 
```java
HotmobManager.getPopup(getActivity(), listener, identifier, adCode, true);
```
You can set any String value into identifier.
For adCode, please contact Hotmob to obtain suitable ad code.

### Bottom Banner
To create the Hotmob Banner can refercence following step.

1.) Make sure HotmobManager service is started.
```java

//Start HotmobSDK service
HotmobManager.start(this);

//To enable debug mode on HotmobSDK
HotmobManager.setDebug(true);
```

2.) Create the HotmobManagerListener for the callback method.
```java
HotmobManagerListener listener = new HotmobManagerListener() {
     @Override
     public void didLoadBanner(View bannerView) {
         mBannerLayout.addView(bannerView);
     }
};
```

3.) Request the HotmobBanner 

```java
View bottomBannerView = HotmobManager.getBanner(object, listener, HotmobManager.getScreenWidth(getActivity()), identifier, adCode);
```

**Note:**  
i. If you placed the banner into an Activity, please set the Activity object into ```object```.  
&nbsp;&nbsp;&nbsp;&nbsp;If you placed the banner into a Fragment, please set the Fragment object into ```object```.  
ii. You can set any String value into ```identifier```.  
&nbsp;&nbsp;&nbsp;&nbsp;The value of ```identifier``` should be different for each banner.  
iii. For ```adCode```, please contact Hotmob to obtain suitable ad code.  
iv. For ```width```, it is recommended to use ```HotmobManager.getScreenWidth(getActivity())``` or ```HotmobManager.getScreenWidth(fragment.getActivity())``` if the banner width is equal to screen width. Otherwise, you should input a value in pixel.

4.) Integrate callback method `didLoadBanner()`: This method will be called when the HotmobBanner ready. Add the banner into the current view as a subview.
```java
@Override
public void didLoadBanner(View bannerView) {
     mBannerLayout.addView(bannerView);
}
```

5.) Integrate callback method `didHideBanner()`: This method will be called when the HotmobBanner was closed. You should modify your app layout to no HotmobBaner situation.
```java
@Override
public void didHideBanner(View bannerView) {
    // Add your implementation here.
}
```

6.) Integrate callback method `onResizeBanner()`: This method will be called when the size of HotmobBanner was changed. You should modify your app layout to fulfil the new banner size.
```java
@Override
public void onResizeBanner(View bannerView) {
    // Add your implementation here.
}
```

### Banner in ListView
To create the Hotmob Banner in ListView, please refer to the steps in "[Bottom Banner](#bottom-banner)", and add the following additional steps:

1.) Set OnScrollListener for the ListView, override `onScroll()` method.
```java
listView.setOnScrollListener(new AbsListView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        updateBannerPosition();
    }
});
```

2.) Also create a new method to notify HotmobManager for updating banner position:
```java
private void updateBannerPosition(){
    HotmobManager.updateBannerPosition(this.getActivity());
}
```

---
Other usage can refercence to wiki [Integrating Banner](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Integrating-Banner), [Integrating Popup](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Integrating-Popup), [Integrating Native Video Ads](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Integrating-Native-Video-Ads), [Integrating Third Party Ad Network](https://github.com/hotmobmobile/hotmob-android-sdk/wiki/Integrating-Third-Party-Ad-Network-for-Android).

Contact
-------
Website: [http://www.hot-mob.com](http://www.hot-mob.com/)
