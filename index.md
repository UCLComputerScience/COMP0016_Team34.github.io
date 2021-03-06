# COMP0016_Team34.github.io

## PROJECT BRIEF
>*"A full webservice that enables any GP practice or NHS clinic with a laptop and a webcam to generate a virtualised call queue with real receptionists engaging from their practice or at home, like waiting to be seen in an actual practice. The service will be activated by a twillio SMS message while you are in a waiting call to activate a link. It will present a visualisation of the patient queue and an estimate as to how long the patient will wait to be seen, as well as the ability for patients to provide a quick summary of their query for receptionists to answer them faster. It may also provide possible FHIR interaction for simulating patient records access for even greater efficiency and patient attention."*



### 22-10

Our first aim was to identify our users. The system has two main users; patients and receptionists. As well as this, both users had very different needs, which would require a different user interface to be made to accommodate for them.



### 26-10

We had our first meeting with two of the clients - John McNamara (IBM Master Inventor, Honorary Professor and IBM UK University Programs Lead) and Liz Kyrmalowski (Innovation Manager Policy Research and Campaigns at Royal College of General Practitioners) - with the intention of getting some clarification of the concept of the brief and the project requirements:

- There are two main elements to the system - The POV of the caller and the POV of the receptionists
  - Callers should be able to visualise their position in the queue with some kind of graphic
  - Callers should be able to give a short description of why they are calling
  
  - Receptionists should be able to see the entire queue
  - Receptionists should be able to see a short description of why each caller is calling


*From UCL, we were also tasked with ensuring that the system we made was an abstracted engine that could be used for any call-based service e.g. Police, Ambulance etc. It is important that the system is made as an API for developers to build upon.*



### 26-10

We created two surveys, one from each POV (caller/receptionist) to help us find the best way to implement the clients' requested features.
The system we were creating was to be used specifically for the NHS, however, we tried to make the questions in the survey more generic to allow the system to be abstractable for many uses.

Callers POV:
  - What would be your past experience of calling companies?
  - Are there things that you find annoying when calling?
  - What sort of experience would you like to have when you call?
  - How willing are you to call a company opposed to any other form of contact eg going in person or online?  Why?
  - What sort of things would you call the GP about?
  - Would you prefer to talk to a receptionist with video, two way or one way, or just audio?
  - How important is person-to-person contact when calling instead of speed of being seen?

Receptionists POV:
  - How would you prioritise answering people in a queue if you could do so? Eg time, frustration, significance of issue?
  - How useful would it be to know how many people are waiting?
  - Would you prefer to talk to the caller with video, two way or one way, or just audio?
  - Are there things that you find annoying when receiving a call?
  - Do you think there is a loss of personal contact for callers? If so is it worth it to improve the speed of them being seen? 



### 29-10

We received the results from the survey:

- Callers:
  - Callers value short waiting times and friendly staff
  - Callers do not like being left on hold
  - Callers mentioned that they would appreciate knowing the length of the queue and their position - i.e. approximate wait time
  - Callers preferred audio calling a lot more to video calls
  - Callers said that person-person interaction is valued but speed of being seen is their priority
  
- Receptionists:
  - Receptionists would, in general, prioritise by wait time, other than for very severe issues
  - Receptionists would find it very helpful to be able to visualise the entire queue
  - Receptionists also preferred audio to video but understood that it may be more relatable for patients if they could see the receptionist
  - Receptionists also agreed that person-person interaction was important but speed was most appreciated by patients
  
  
  
### 02-11

We made some initial sketches of the user interface and how system features could be implemented. We created some ideas of a graphical queue.

[Sketch_image_1](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/IMG_0035%20(1).png)
[Sketch_image_2](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/IMG_20201102_121423%20(1).jpg)
[Sketch_image_3.1](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/Hci%20draft-1.png)
[Sketch_image_3.2](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/Hci%20draft-2.png)
[Sketch_image_3.3](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/Hci%20draft-3.png)
[Sketch_image_3.4](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/Hci%20draft-4.png)

Using the sketches, we began working on some initial prototypes:

Prototype 1:
>  User is sent an SMS message which leads them to a welcome sreen where they input identification details and some information about the severity of their issue. The caller is then placed in a queue and can add some information about their problem, while they wait, using their microphone. The patient can then be selected to be called by the receptionist and, after the call, may provide feedback on the call quality.
  The receptionist can see all callers along with a colour coded view of the callers' problem descriptions. They have the option to call each patient with a video call. During the call they can view the patient's problem description and have the option to pass on the patient to a physician i.e. dermatologist, pharmacist, nurse, GP etc.

[Initial_prototype](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/Initial%20Prototype%20(1).png)

Iteration 1:
>  Removed video options, now just audio calls. The welcome screen was also removed since it slows down the process too much - patients are calling for urgent requests and increasing the work to do so can be annoying. Also removed the option to state how 'severe' the patients' problems are. Patients are calling because they feel their problem is severe so including this feature may be, ultimately, useless as there is no sense of relativity amongst patients' problems.
  Removed colour coding of descriptions for receptionists and instead chose to highlight key words to ease receptionists job of scanning through patients' problems.

  

Iteration 2:
>  Users can now choose from a selection of flags to change their language options. The microphone option during the wait in queue was removed and changed to a textbox option which allows a more accurate a language-free description - people are more articualte over text and transcription accuracy is improved drastically. However, to ensure the patient does not add too much information for the receptionist to get through quickly, we added a word limit to the textbox. The feeback page at at the end of the call was also removed to improve simplicity and useability for the user.
  The receptionist view was also simplified to match changes.

[Prototype_2](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/PrototypeV2%20(1).png)

Iteration 3:
>  We decided to add back the microphone for patients to give their problem description and we would use speech-text to convey this to the receptionist. This is because it is important to detect crying/shouting over audio; this would be done using IBM's tone analyser. The callers now also have the option to turn on video during their call so that receptionists do not feel 'exposed' or 'unequal',as well as a mute option for the audio. Patients can no longer see their queue position; the system will now mimic a waiting room where the patient can see other patients in the waiting room but cannot know their exact position in the queue. This is to allow receptionists to choose the order they select patients to be called without upsetting the patients that were overtaken if the receptionist chooses a patient that came after them.
  Receptionists were also given the option to send patients to a physician immediately, without having to call the patient first.

[Prototype_3](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/PrototypeV3.png)



### 06-11

We had a meeting with the client to clarify how the system was to be used:
- Core hours are 8:00am - 6:30pm (outside of these hours are referred to as 'out of hours' times) - patients are therefore used to calling within these times - system needs to be up during these hours
- Patients should be given a disclaimer if wait times are longer than expected
- Maybe keep allowing patients to write-in problems and get back to them in the morning?

- Patient prioritisation system from queue is up to the practice (i.e. prioritising by wait time vs. severity of issue) - in general receptionists would see patients in order unless there is a severe issue with another patient



### 23-11

We chose to use Zoom for our video call functionality. Zoom rooms are to be used for video calls, with each receptionist having their own room. This allows managers to monitor receptionist work.
The client also requested some kind of data recording and analysis e.g. graphs and data about wait time/call times/staff seen most etc. - This information should only be available to authorised staff



### 27-11

Added optional DOB entering to system which can be used for authenticating patients (using link to NHS FHIR system - flag at-risk patients)

[Final_Prototype](https://github.com/UCLComputerScience/COMP0016_Team34.github.io/blob/main/Final_Prototype.PNG)



### 03-12

We received the Microsoft Azure license to host our server and API licenses for our program. We also sought to finalise the features of the system.
These features included:
- Language Compatibility
- Sending an SMS to the patient
- Tone analysis of the patient's call and problem description
- Keyword highlighting of the patient's problem description
- Calling the patient on some kind of video calling system
- Actions performed on the patient, such as sending to a physician - and then a check for the physicians to receive the intended patient
- Collecting user data from callers and receptionists to review by bosses to improve efficiency

We also came up with a plan for the program to begin our first implementation and began building a client-side webpage for the callers.



### 09-12
We had a call with the clients to review and made some changes to the system:
- No 3D queue - no longer needed
- System uses DOB and Name to look up patient in the FHIR database and flag vulnerable patients
- Use 'Zoom' room for patient calls
- Rather than sending patients to the same, single waiting room, patients are given a timeslot for when they would be seen - patients are given the ability to specify available/unavailable times
- All patient interaction is to be logged for confidentiality purposes - Call -> Receptionist -> GP    is one single interaction



### 12-01
- Finished server-side work using Django and Azure
- Built HTML,CSS webpage for callers and updated aesthetics
- Python-based client for receptionists completed
- Updated NLP markdown



### 03-02
We had another meeting with the clients, joined by Dean Mohamedally and Atia Rafiq. After a review of the system's progress, some suggestions were made about the system features:
- The interface should be tailored for mobile use as it will be the primary mode of accessing the system
- The app should be able to distinguish which surgery the user has called and should work independently for each practise - it is the practice's responsibility to host their own service
- Speech recognition features could be considered for users to speak in their own language
- There should be a distinction if the patient needs to just see a nurse/if there is an emergency etc.
- The description should also include the patients' concern and what they aim to achieve
- Receptionists should have the ability to send alert statuses to patients that have been waiting too long. However, receptionists should still maintain a policy to only bump people's queue positions if they have an emergency - focus on first come, first serve
- Let patients know what an 'emergency' is - let the patient know to call 999 if they have any symptoms from a provided list
  - Signs of a heart attack - pain like a very tight band, heavy weight or squeezing in the centre of your chest or any pain that moves into your jaw or neck
  - Signs of a stroke - face drooping on one side, can't hold both arms up, difficulty speaking, or weakness or numbness on one side of your body
  - Severe difficulty breathing - gasping, not being able to get words out, choking or lips turning blue
  - Heavy bleeding that won't stop - uncontrollable bleeding from any part of your body
  - Severe injuries - including deep cuts after a serious accident
  - etc.
- The system could show some additional NHS related or practice related information for the patient to look at while they are in the queue (e.g. if the patient has mentioned 'cough'/'sore throat' show some health advice regarding these symptoms)
  - This area could also be used as a news area - make money by hosting advertisements or links to pdf files to download brochures, patient participation groups, community activities, practice website etc.
- System should have NHS/RCGP/UCL/IBM logos but should specify 'proof of concept' or similar until finally approved



### 13-02
- Finished all the functions on the receptionist Client
- The current Client is only runnable with JVM, so after improvements, it should be built into an exe file
- Requires suggestions from clients

### 1-03
- Set up postgres database on azure
- Set up login functionality using djangos built in systems
- Added warning page for serious injuries

### 10-03
- Packed the client into an executable file
- Disabled the google nlp feature on client in preparation for deployment
- Updated development blog on java client


### Code

#### Server 

The Callers data is stored and requests are handled by a python Django server. This was chosen for its simplicity to use on a Microsoft Azure server and also its ease to set up. The callers data is sent to the server using HTML forms which can be created using django built in form classes. These are send using https' POST method to help give better security for the data. When the caller is sent to the queue they are given a box to send a description to the server and this is added on to the class holding that caller. The callers are identified by giving them a unique id generated using pythons ```uuid``` module. This is stored in a cookie on the callers browser and send with all requests inorder to detect which caller is which. 

Similarly there needs to be a method to detect when a caller leaves the queue, due to the statelessness of the HTTP(S)<sup>1</sup> it is not simple to have a method that will detect whenever someone leaves a site. eg the Javascript onunload event has times that it would not fire eg when the callers internet connection fails. The intitial solution used was to have a javascript function run every 5 seconds which send a form containing the callers id to the server. When the callers information was requested from the server by the receptionist if this message hadnt been recorded in the last 10 minutes then the server will assume this caller has left the queue. However this solution reloaded the page each time the form was sent and therefore any text in the input box for the callers description waas removed. A better solution was found which involved using a ajax and JQuery function which ran asynchronously and sent a post request with the callers id, this is then used in the same way as before . This does not reload the page and therefore is less intrusive than the first method.

When a receptionist's client requests the data for the patients only the changes (as a JSON file as specified in 'JSON spec.md') since the last time the data was sent is sent. When a caller first connects all their information - name, date of birth etc is sent along with the random id. However the next time the data is requested if nothing has changed then an empty JSON list is sent. If a caller adds a description or updates their information the changed data is added to the JSON to be sent as well as the id of the given user. When a caller is deemed to have left the call then a message containing the id is sent as well as a message saying that this user has left so the receptionist can be updated accordingly. 

Logging in a receptionist is handled by the receptionist client and djangos built in login system. We did consider using Microsoft Live however due to needing to have three parties involved (Microsoft, the receptionist and the server) it did not seem feasible to easily implement this. Therefore we used the django login system as it already exists and is built in to what we are doing already. An admin superuser can create receptionist accounts on the django admin page and then when a receptionist goes to log in their details are sent to the server which can then verify them using built in methods. This means we don't have to write our own, potentially insecure, methods but can use existing ones. Afterwards the server can use the build in session system in django to tell if a user is logged in and only allow access to sensitive data if the user has logged in. Otherwise a 403 forbidden reply is sent. The API for the login is specified in login.md and explains how the system should be used. 


#### Client

<b>General introduction</b>

The client for the receptionists is coded using java. The project is managed and built using maven. The libraries in use is java standard libraries, google nlp libraries.  The UI design uses java swing library.

<b>Source code introduction</b>

There are three packages and one main class "App" responsible for the execution process.
Inside Pages package, there are three classes, each responsible for the login page, link configuration page, main page.
Inside Dialogues package, there are dialogues that will be shown to user when certain behaviours occur.
Inside Widgets package, there are classes designed to implement the client.
All the classes are sufficiently commented in javadoc formats for each method/class. 
Please view source code for more information.

<b>Client work flow</b>

After starting the program, the receptionist sees a login page, on which he is able to enter his username, password and the address of the server in use. There is also a needhelp button, he gets all the necessary information from it when he needs technical support. 

After logging in, he sees a configuration page, on which he can add up to 10 links and store them. He can also remove any of them using remove. But he has to configure at least 1 link to send to the callers.

 After he clicked on the save and close button, the internal timer of the program starts, and the program gets the changes of the callers queue from the server every 10 seconds. The name, date of birth of the callers are presented to the receptionist. The description received is sent to a google natural language processing server, and that server returns a list of keywords. Then the client sets all the keywords in the description in bold and display it to the receptionist. 

The receptionist can then send any link he configured to the caller using the drop-down box. After he clicks on send, the client sends the server relevant information, and stores this caller in a txt file. The receptionist can also remove any prank callers using the cross button on the top right corner, and the client send a warning to the caller.

The receptionist can click on the settings button and configure the links at any time, he can click on the logout button to log out of the account. The client then informs the server that this receptionist has logged out.

<b>Plugins and dependencies</b>

This project is managed and built using maven. It relies on following plugins/dependencies:
Maven assembly complier plugin: This plugin helps compiling all the libraries into a single jar.
Google nlp dependency: This dependency is responsible for highlighting keywords in user's descriptions. After the client gets the user's description from the server, the client sends it to the google nlp server and the google nlp server sends back a list of keywords of that description. Then the client displays the description on the screen with the keywords in bold.

<b>Authentication and interaction with the server</b>

The interaction with the server is achieved using http get and post methods. 
The cookies sent from the server are managed by a single cookie manager. 
The client gets the username and password and send them to the server using post method, then get the confirmation from the server using get method. 
The client keeps receiving changes of the queue sent from the server and updates it on the screen. 
Whenever a decision is made by the receptionist, the client sends it to the server using post method.
When the receptionist has logged out, the client sends this info to the server.

<b>Deployment</b>

There are jar and executable files for this project, with more details specified in the Receptionist/ReceptionistClient/readme.txt in the github repository.

<b>Data recording</b>

The callers records are stored in a txt file. Ideally, all the caller data should be stored in a online database, since we are not given any available NHS database, we can only store the data locally.




#### Ethical Consideration

This system is not permitted to automatically triage or have non-clinical staff triage patients. While it does not make any automatic decisions it allows receptionists to select which callers to interact with this still represents a certain amount of clinical decisions by non-clinical staff. This is an inherent part of the product and could not be avoided within the design without reducing the system to nothing. While talking to the clients it was decided that this was an acceptable level from reception staff as they will make similar choices in an in persons setting. However Dr Raja Mukherjee (Consultant Psychiatrist and medical lead for adult neurodevelomental disorders in Surrey) has raised concerns suggesting it would be inappropriate for use due to non-clinical staff making clinical decisions. This potentially would not be cause to not use this system however procedures would be needed to ensure the system does not lead to clinical decisions being made without clinical staff. 



#### References

1. Developer.mozilla.org. 2021. *An overview of HTTP - HTTP | MDN*. [online] Available at: <https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview#http_is_stateless_but_not_sessionless> [Accessed 12 February 2021].

