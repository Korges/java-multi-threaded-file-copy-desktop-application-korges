# Multi Threaded File Copy Desktop Application

Java application that can copy multiple files simultaneously from one location to another.

Main application window :

![Alt text](image-a.PNG?raw=true "Main Window")

Copy file dialog

![Alt text](image-b.PNG?raw=true "Main Window")
"From" and "To" are absolute file paths.

**bold How Does It Work You Ask?**
* Users can start a "copy operation" by clicking on a "Copy" button.
* After that a "copy dialog" is shown to the user where users can specify what to copy, where to copy, should the destination file be overridden?
* Currently running copy operations are shown in the "main window".
* Each copy operation is displayed as follows.
* Title: contains what is copied where.
* Progress: displays how many bytes of the source file is copied to the destination path in percentage.
_E.g. if the source is file's size is 100MBs and the progress bar is at 33% then 33MBs are copied to the destination file.
Stop button: by clicking on it the copy operation is cancelled._
