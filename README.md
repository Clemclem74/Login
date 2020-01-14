# OOSE-Project

This project was made with JAVA.

## How to launch the project

1 - Download the git repository  
2 - Exctract the files  
3 - Open OOSE-PROJECT on eclipse  
4 - right click on src [OOSE-Project master], select Build Path Configure Build Path   
5 - click on Add External JARs... then add all the javafx.something.jar and the mysql disponible in lib : see picture 1  

  ![alt text](https://image.noelshack.com/fichiers/2020/03/2/1578995965-captproj1.png)  

6 - Click on run, Run Configurations  
7 - in the windows that opened go to (x)= Arguments and past in VM arguments the following lines : see picture 2  
  make sure you modify the path to in module-path 
  --module-path "path\to\this\file\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib"  
  --add-modules=javafx.controls,javafx.fxml  
  
  ![alt text](https://image.noelshack.com/fichiers/2020/03/2/1578995965-captproj2.png)
  
8 - you can run the project !  

  - The page should look like this : 
  
  ![alt text](https://image.noelshack.com/fichiers/2020/03/2/1578996159-captproj3.png)
