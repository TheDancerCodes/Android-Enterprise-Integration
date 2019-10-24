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

## Policies and commands
There are two methods that a mobile device management system will use to interact with a registered Android device through the installed device policy controller. 
These methods are **policies** and **commands**.

### Policies
A **policy** is a collection of settings defining the state in which a managed device should operate. 
> An important thing to remember is that a managed device can only be assigned a single policy at a time. 

#### Policy Functions
These policies can be designed to:
- Disable certain hardware components
- Restrict a user's access to the system
- Define the applications that should be installed
- Configure network settings.

### Commands
Commands are actions issued to a device by a Device Management System and carried out by the device policy controller.

#### Supported Commands:
When utilizing the Android management API, the only supported commands are:
- Locking the device
- Rebooting the device
- Resetting the password.

NOTE: You can check out the reference guide for that API [here.]([https://developers.google.com/android/management/reference/rest/v1/enterprises.devices/issueCommand#Command]

## The Device Administrator

|  Employee Owned | Enterprise Owned   |   
|---|---|
| DPC runs on as profile owner   |  DPC runs as device owner    
|  Work data is in separate profile |   Work data is not automatically segregated
|Device admin is work profile   | Device admin for whole system | 

> NOTE:  Device Owner Mode can only be enabled during the initial set up of the device.

### Reasons for Device Owner Restriction:
- Prevent malware from obtaining device admin permissions.
- Mitigate privacy concerns if device was previously owned.
- Restrict device to one Mobile Device Management System at a time.

> NOTE: In both of these cases, the device policy controller operates as a device admin. 

|  Profile Owner | Device Owner   |   
|---|---|
| DPC can remotely monitor the device status (CPU usage & system temperatures)   |  Profile owner capabilities (can do everything a profile owner can do)   
|  Control Hardware (Disable the camera or fingerprint reader) |   Restrict user access to settings
| Set password policies on the device   | Enabling or disabling radios (WiFi, Bluetooth, and data) | 
| Wipe profile data   | Initiating factory reset of the device & many more.. | 
