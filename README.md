# Custom Discord Rich Presence
This project was made for educational purposes only, but feel free to make improvements and forks.
A Discord Rich Presence customizable through a JSON.

**IMPORTANT: THE JAR ONLY WORKS IF YOU RUN IT THROUGH A TERMINAL!**

# How to build the JAR
There wont be instructions how to build the JAR for Eclipse IDE because i never used it for Maven before, apologies.

On IntelliJ IDEA, go to File > Project Structure > Artifacts and create a new Artifact JAR with Dependencies.
![image](https://user-images.githubusercontent.com/88702612/182337157-2f47b04e-553a-41ca-be98-aaf9cf27b85c.png)
The JAR will be created in out/artifacts.

I am also extremely stupid when it comes to the MANIFEST, for some reason it wont set the Main Class.
To fix it, open the JAR with an application like 7Zip or Ark, go to META-INF and open MANIFEST.MF, then add "Main-Class: Main".
Once you saved the file, you can run it!
