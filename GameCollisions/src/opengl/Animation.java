package opengl;

import opengl.resource.ImageResource;

public class Animation {
	
	public ImageResource[] frames;
	
	public int fps = 1;
	private int currentFrame = 0;
	private long lastFrameTime = 0;
	
	public boolean loop = true;
	
	public void play() {
		long currentTime = System.nanoTime();
		
		if(currentTime > lastFrameTime + 1000000000 / fps) {
			currentFrame++;
			
			if(currentFrame >= frames.length) {
				if(loop)
					currentFrame = 0;
				else 
					currentFrame --;
			}
			lastFrameTime = currentTime;
				
		}
	}
	
	public ImageResource getImage() {
		return frames[currentFrame];
	}

}
