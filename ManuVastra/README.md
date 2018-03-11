#EasyShopping App
  
EasyShopping is a sample e-commerce app that uses the OMCe CXA Analytics SDK to send analytics data to Oracle Mobile Cloud, Enterprise (OMCe).
  
##Motivation
  
This mobile app demonstrates key features of the OMCe CXA Analytics SDK.
  
##Requirements
  
* Android Studio, or the standalone Android SDK Tools from Google
* Java Development Kit (JDK) 1.7.0_67 or compatible
* OMCe 17.3.5 or later
* OMCe samples package for Android
* OMCe SDK (included in samples package)
  
##Installation
 
1. Create the project. From Android Studio, open EasyShopping.
 
2. In OMCe, register your analytics app. Create an app profile and a notifications profile for the EasyShopping app and associate them. Make a note of the notifications profile name. For detailed instructions, see [Analytics Apps] (http://www.oracle.com/pls/topic/lookup?ctx=en/cloud/paas/mobile-suite&id=MSUDG-GUID-9A0BA69A-080F-4006-AD90-971C31518BCA) in Developing Applications with Oracle Mobile Cloud, Enterprise.
 
3. Go to the Settings > Application page for the analytics app and make a note of the following values:
        * Application Key
        * OAuth Token Endpoint
        * Client ID
        * Client Secret
        * Collector URL
   The following value can be found on the Settings > Notifications page in CxA:
        * Profile Name
 
4. In the app, open `EasyShopping/app/src/main/assets/oracle_mobile_cloud_config.xml` and copy the above values in for their corresponding elements.
   * The `oAuthTokenEndPoint` appears near the top of the file.
   * The other keys are nested under `analyticsApp` (including `baseUrl`, which is where you fill in the collector URL).
  
5. Set up engagement (Optional)
  a. Replace the whole `google-services.json file` with your own.
  b. Create your own class to extend the CxA class and override the `onMessage` method as following:
    `public class MyFirebaseMessagingService extends CXFirebaseMessagingService  {
         /**
         * Create and show a simple notification containing the received FCM message.
         * TODO: Developer needs to override this method to customize notification behavior
         */
         @Override
         protected void onMessage(String title, String message) {//...}
     }`
  c. Update your class name in `Manifest.xml` as following:
    `<!-- [START custom_firebase_service] -->
     <service android:name=".MyFirebaseMessagingService">
         <intent-filter>
             <action android:name="com.google.firebase.MESSAGING_EVENT"/>
         </intent-filter>
     </service>`
  
6. Build and deploy the app to an emulator or device. In Android studio, click the Run button to run the project and choose emulator or device to run on.
  
##API References
  
[OMCe Android SDK Reference](http://www.oracle.com/pls/topic/lookup?ctx=en/cloud/paas/mobile-suite&id=mssda-index)