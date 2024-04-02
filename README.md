[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# FaceValidation Android App

## Overview

The FaceValidation app is designed for emotional validation studies. It presents participants with a series of images and allows them to select the emotion they perceive. The app captures and logs their responses, including the reaction time for each image. It's built with Android Studio and is compatible with Android SDK version 26 and above.

## Features

- **Emotion Selection:** Participants can select the emotion they perceive in each image.
- **Reaction Time Logging:** The app captures and logs the reaction time for each response.
- **Data Export:** Participant responses are saved in a CSV file on the device for further analysis.

## Setup

1. **Clone the repository** to your local machine.
2. **Open Android Studio** and import the project.
3. **Build the project** to resolve all dependencies.
4. **Run the app** on a physical device or emulator.

## How It Works

- The app contains 110 template images divided into two sets. Set one contains images named `photo_1` to `photo_60`, and set two contains images named `photo_61` to `photo_110`.
- Example: In a validation study our team conducted, Set 1 corresponded to country-specific photos, wherein 5 male and 5 female images each expressed six emotions (sad, neutral, happy, fearful, surprised, angry). Set 2 consisted of similar images from the [Developmental Emotional Faces Stimulus Set](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC5173446/), excluding the surprise emotion. This allowed us to compare the performance of our country-specific photos with that of a validated library.
- When a participant starts a task, images from one of the sets are randomly selected and presented. The order of images is also randomized to ensure variability in each session.
- Participants indicate the emotion they perceive in each image by selecting one of the provided options. The app logs their response along with the reaction time, which is the interval between the image presentation and the response.

## Customization

### Changing Images

- Images are stored in the `res/drawable` directory.
- To change images, replace the existing files with your new images, ensuring they are named consistently (e.g., `photo_1.png`, `photo_2.png`, ... `photo_110.png`).
- The app automatically detects images based on their naming convention and includes them in the tasks.

### Modifying Emotions

- The emotions are listed as buttons in the `activity_task.xml` layout file.
- To modify the emotions, change the text of the buttons and ensure the `onClick` handlers in `TaskActivity` are updated to reflect any new emotions.

## Advancds Customization

### Extending the Image Sets

To include more sets of photos beyond the initial two, you will need to modify the `TaskActivity` class. Specifically, you'll adjust the `loadImages` method to accommodate additional sets. Here's a step-by-step guide:

1. **Add More Images:** Place your new images in the `res/drawable` directory, following the existing naming convention (e.g., `photo_111.png`, `photo_112.png`, ...).

2. **Update the `loadImages` Method:** In `TaskActivity`, the `loadImages` method currently initializes two ArrayLists for two sets of images. To add another set, you would:

   - Create a new ArrayList for your additional set(s) of images.
   - Populate this ArrayList with the resource IDs of your new images, similar to how the first two sets are populated.
   - Optionally, shuffle the list if you want the images to appear in random order.
   - Finally, add this list to the `imageResIds` ArrayList following the same logic used for the first two sets. You might decide the order in which the sets appear or shuffle all sets together before adding them to `imageResIds`.

Example modification for an additional set:

```java
ArrayList<Integer> set3 = new ArrayList<>();
for (int i = 111; i <= 160; i++) {
    int resId = getResources().getIdentifier("photo_" + i, "drawable", getPackageName());
    set3.add(resId);
}
Collections.shuffle(set3);
// Decide on the order or shuffle before adding
imageResIds.addAll(set3);
```

### Customizing the App Layout

If you wish to change the design of the app, you may want to modify the XML layout files located in the `res/layout` directory. Here's what you can do:

- **Activity Layouts:** Each activity's layout can be customized by editing the corresponding XML file. For example, to change the button styles in `activity_task.xml`, you could update the attributes of the `Button` elements.
  
- **Adding New UI Elements:** To add new UI elements, such as additional buttons or text views, you can simply include them in the XML files. Remember to assign IDs and set up event handlers as necessary in the corresponding activity classes.

- **Theme and Styles:** Global styles and themes can be modified in `res/values/styles.xml` and `res/values/themes.xml`. Here, you can change the color scheme, default font, and other UI properties that affect the app's overall look and feel.

Remember, any changes to the UI should be reflected in the activity classes. For instance, if you add a new button in the XML layout, you'll need to add corresponding functionality in the activity class, such as setting up an `OnClickListener`.

### Note on Scalability

When adding more content or modifying the layout, consider the app's performance and usability. More images mean increased memory usage, so optimize your resources accordingly. Also, ensure that the UI remains user-friendly and accessible across different devices and screen sizes.

## Permissions

The app requests permissions to manage external storage for saving the CSV files. Ensure the app is granted the necessary permissions on first launch.

## Acknowledgements

This work is an output of the ALIVE project (Improving Adolescent mentaL health by reducing the Impact of PoVErty). This work was supported by the Wellcome Trust [221940/Z/20/Z].

## Questions

For any questions regarding the app, please contact:

<aminsinichi@gmail.com>
<m.sinichi@vu.nl>
