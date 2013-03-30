package test;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImgCup {
	public static void main(String[] args) throws IOException{
		Thumbnails.of("C:/Users/test/Desktop/shuaige.jpg")
		.size(120, 120)  
		.keepAspectRatio(false) 
		.outputFormat("jpg")  
		.toFile("C:/Users/test/Desktop/shuaige_120x120");
	//中图
	Thumbnails.of("C:/Users/test/Desktop/shuaige.jpg")
			.size(36, 36)
			.keepAspectRatio(false)
			.outputFormat("jpg")
			.toFile("C:/Users/test/Desktop/shuaige_36x36");
	//小图
	Thumbnails.of("C:/Users/test/Desktop/shuaige.jpg")
			.size(28, 28)
			.keepAspectRatio(false)
			.outputFormat("jpg")
			.toFile("C:/Users/test/Desktop/shuaige_28x28");
	System.out.println("success");
	}
}
