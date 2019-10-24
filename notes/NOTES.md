# Android App Development: Enterprise Integration Notes

In this enterprise world, there are three APIs that are used in the administration of   
Android devices by mobile device management systems.   
  
These are the:  
  
1) [Android Management API](https://developers.google.com/android/management/reference/rest/)  
2) Device Administration API  
3) Google Play EMM API.  
  
  
*[a]* The **Android Management API** is an interface created to help developers and enterprises integrate   
device and application management features into their solutions.  
  
- Here is a link to the [Android Management API Explorer](https://developers.google.com/android/management/reference/rest/v1/enterprises/create)  
  
*[b]* The **Device Administration API** is an interface used in the creation of applications that enforce policies on a managed device.  
  
> **NOTE:** A device policy controller uses the **DevicePolicyManager** and the **DeviceAdminReceiver** classes  from this API to manage a device.  
  
*[c]* The **Google Play EMM API** is used to include access to the Google Play store from within a mobile device management console.   
  
> Through this API, IT administrators can get information about applications and their available   
manage configurations to help them configure their device policies.
