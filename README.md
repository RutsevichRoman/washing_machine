# About project
This project has implemented to control an appliance such as wash machine.
 
# REST API
Prefix for all REST requests is "/api/v1".
## GET requests:
- "/modes" - modes of washing mashing;
- "/power/state" - current state power (on/off);
- "/door/state" - current door state (opened/closed);
- "/mode/current" - current washing machine mode;
- "/mode/state" - current mode state.

## POST requests:
- "/power" - set power (on/off);
- "/door" - set door (open/close);
- "/mode" - set washing machine mode;
- "/run" - running washing machine mode.

# How to use
Project .jar file can be build with pre-installed 
Apache Maven of version 3.6.0 by executing goal "mvn clean package".

Related project tests can be executed by goal "mvn clean verify".

# How it works
Washing machine has 4 parameters:
- Mode (by default - INITIAL);
- ModeState (by default - READY);
- doorOpen (by default - false);
- powerOn (by default - false).

Advanced REST Client or Postman client can be used to execute REST API.
To test REST API was chosen Advanced REST Client.

Parameters states can be checked by executing corresponded GET requests.

Parameters states can be changed by executing corresponded POST requests.
##Settings for POST request:
- body content type - application/json;
- editor view - Raw input.

###Example of using requests for power:
- GET request "/power/state" the message "Power is off" will be shown;
- POST request "/power" (Body content type - application/json; Editor view - Raw input) with body "true" (must be set 
without quotes in REST client) the message "Power is on" will be shown with OK response;
- after the same attempt to send POST request "/power" with body "true" the message "Power can not be switched on" 
due to it's already set to "true" and vise-versa,
if current state of power is "false" and send POST request "/power" with body "false" (must be set without quotes in 
REST client) the message "Power can not be switched off" with BAD response due to it's already switched off.

###Example of using requests for door:
- GET request "/door/state" the message "Washing machine door is closed" will be shown;
- POST request "/door" with with body "true" the message "Washing machine door is open" will be shown with OK response 
due to door was closed;
- after the same attempt to send POST request with body "true" the message "Can not open the door" will be shown with 
BAD response due to door was opened by previous POST request;
- vise-versa by sending in arrow two POST request "/door" with body "false" the message "Can not close the door" will 
be shown due to there is second attempt to close the door.

###Example of using requests for washing machine mode:
- GET request "/mode/current". The message INITIAL will be shown due to it's default mode with OK response;
- POST "/mode" with body INITIAL. The message "Mode INITIAL can not be set" with BAD response due to it was previous 
mode of washing machine;
- POST "/mode" with body COTTON_30. The message "Mode COTTON_30 is set" with OK response. After same attempt POST 
"/mode" with body COTTON_30 the message "Mode COTTON_30 can not be set" with BAD request due to this mode was set 
in a previous step.

###Every mode has state READY by default:
- GET request "/mode/state". The message READY will be shown with OK response.

###Running washing machine:
- POST request "/run". The message "Mode COTTON_30 is run" will be shown in case of mode COTTON_30 with OK response.
Logging mode states executes in IDE console: READY, LOAD_WASHING_CAPSULE, LOAD_WATER, WASHING, 
UNLOAD_WATER, PRESSING, FINISHED. When mode executed it has state READY to repeat the washing mode;
- if washing machine has INITIAL mode it can not be run the message "Mode INITIAL can not be run" will be shown with 
BAD response, other mode must be set by POST request "/mode".