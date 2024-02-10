# DRM - Domande a Risposta Multipla

DRM stands for "Domande a Risposta Multipla" that means Multiple Choice Questions in Italian.
DRM is a simulator of a test with multiple choice questions.

## How to run DRM

### How to run with jre

1. Make sure you have jre-8 or latest version installed on your computer
2. Open the terminal in the DRM folder
3. Run the command `java -jar DRM.jar`

### How to generate the executable DRM.exe on Windows

You can generate the DRM.exe with [launch4j](https://launch4j.sourceforge.net/):
1. Run [launch4j](https://launch4j.sourceforge.net/)
2. In Basic "section" fill the following fields: "Output file" with the path and the name of .exe you want generate, "Jar" with the DRM.jar file and "Icon" with the Icon.ico file
3. If you want run DRM on a computer that doesn't have JRE installed, you can download it and then insert the JRE path in the JRE "section" at the "JRE paths" field
4. Click on the "Save configuration" and then on the "Build wrapper" buttons situated on the file menu
5. You have your executable file

### How to generate the executable DRM on Linux

1. Make sure you have jre-8 or latest version installed on your computer
2. Edit the DRM.desktop file: chenge "/absolute/path/DRM" with the absolute path of DRM folder. For example if you put the DRM folder in the Home folder, the absolute path will be "/home/USER_NAME/DRM"
3. move DRM.desktop in Home/.local/share/applications folder, you can use the following command from the terminal opened in the DRM folder `cp DRM.desktop ~/local/share/applications/`
4. The DRM icon should appear on your start menu

## How to generate a test with multiple choice questions

For generate edit the file **selected.json** in the folder **questions**.
You can add a new question adding the following block of code (that is a json object):

```json
{
		"author":"this is not mandatory, the program will ignore this field",
		"question":"<html>You can use this file as template for create your questions, you can use html for customize your answers and questions.<br><b>NOTE : </b> The index answer start from 0<br> For select the first one as correct, use the index 0 <br><br></html>",
		"answers":["<html>Answer 1</html>","<html>Answer 2</html>","<html>Answer 3</html>", "<html>Answer 4</html>"],
		"right_answers": ["0"]
}
```

You can manage your text using html tags as in the example.

Do not forgot to put a comma between two json object
