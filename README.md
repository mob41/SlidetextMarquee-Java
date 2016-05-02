# SlidetextMarquee-Java
Slide text in Java! (known as "Marquee" in HTML)
## Example
![Video](http://mob41.github.io/images/slideTextExample_video.gif)

Check out the code [here](https://github.com/mob41/SlidetextMarquee-Java/tree/master/src/main/java/com/mob41/slidetext/example/SlidetextInSwing.java).

## Tutorial
1. Import the class

	```java
	import com.mob41.slidetext.Slidetext;
	```

2. Creates a new instance

	```java
	Slidetext slidetext = new Slidetext("I am a slide text!", 50); //"50" is the maximum sliding text slots
	```
	
3. Get the frames

	```java
	String[] frames = slidetext.getFrames(); //With blanks on the beginning and the end. (Smooth)
	
	// Alternative
	
	//slidetext.getFrames(false); //No blanks
	//slidetext.getFrames(true, false); //Blanks on the beginning only
	//slidetext.getFrames(false, true); //Blanks on the end only
	```
	
4. Simply loop over the frames! (In Swing GUI)

	```java
	for (int i = 0; i < frames.length; i++){
		lblText.setText(frames[i]);
		try {
			this.sleep(500);
		} catch (InterruptedException ignore){}
	}
	```

