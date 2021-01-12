# COMP0016_Team34.github.io

## PROJECT BRIEF
>*"A full webservice that enables any GP practice or NHS clinic with a laptop and a webcam to generate a virtualised call queue with real receptionists engaging from home, like waiting to be seen in an actual practice. The service will be activated by a twillio SMS message while you are in a waiting call to activate a link. It will present a virtual view in Unity of a call queue and present real receptionists in the AR environment with chromakeyed or subtracted background projection using streamlabs/OBS technologies and a webcam. It will also export the audio stream questions for team 35 and provide possible FHIR interation for simulating patient records access."*



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
  
  
