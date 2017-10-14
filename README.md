Summary
-----

QuickShot Camera is a camera that takes consecutive photos continuously while also telling the user when the photos being captured start to become blurry (i.e. the camera moved too fast or the object being captured is out of focus) so that they can adjust their speed or distance accordingly. This is all done in real-time.
This camera app is actually part of a bigger picture (no pun intended), where the user can further process the set of photos taken and reconstruct a 3D model using the aforementioned photos. 
The main reasoning behind the 3D model is so that one can get visual image data on landmarks. While someone could slice up a video to achieve the same effect, it would not include and EXIF data to indicate the locations of where the discrete images were taken.
To remedy this, a tool that could take burst images for a prolonged amount of time can provide image data and a constant stream of images for the user to use as modelling data afterwards. To ensure that the images used in the model are actually clear, the app has a visual indicator of blurriness to tell the user to slow down (or correct the distance) and give the camera a chance to focus on the subject. In order to check the blurriness, a Laplacian transform is applied on a grayscaled version of the image taken to detect the edges in the image.
The more edges it has, the less "blurry" the photo is. If the number of edges is lower than the user defined threshold, the blurriness indicator will change colors and indicate to the user that they are going too fast and to slow down their movements. 
Starter Code used: https://github.com/googlesamples/android-Camera2Basic

Key Features
-----
- A real-time blurriness indicator to tell the user when they are moving the camera too fast or if the image is otherwise out of focus 
- Ability to capture and save a set of continuous photos tagged with metadata
- Tolerance setting for user to determine how blurry is considered "blurry"
- A resolution setting for users to determine the resolution of their photos
- A frame rate selector for the user to set the frames-per-second frequency by which they capture the images
- EXIF location data to assist with associating each image with their geolocation

Future Steps
-----
- Add in blurriness index of photo taken in EXIF metadata for post-processing
- Cosmetic changes
- Create fallback mechanism to reduce framerate for underpowered hardware

Technical roadblocks
-----

**Solved**
- Performance issue on Nexus 5x
  - lag when capturing images consecutively on default resolution

- Contention for memory buffer holding image data between capturing, saving and checking for blurriness
  - separate threads were created for each of the above activities
  - create critical sections with mutex guarding access to image data buffer

- Unsure of how to check for blurriness in a photo
  - Assumed number of edges in image would be related related to blurriness
  - Laplacian transformation was employed to determine variance of edges in an efficient manner
