package test;

import java.io.IOException;
import java.util.regex.Pattern;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Text;
import com.service.TextService;
import com.action.*;

public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String contents = "shdjshdjs\r\nsdsd\r\n\r\naaaaaa" ;
//		String[] strs = contents.split("(\r\n){2,}");
//		for(int i =0;i<strs.length;i++){
//			System.out.println("~~"+strs[i]);
//		}
		//ApplicationContext act = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//BeanFactory factory = (BeanFactory)act;
		
		//EditParaAction t = (EditParaAction)factory.getBean("editpara");
		//System.out.println(t);
		
//		System.out.println(t);
//		System.out.println(t.viewAllText());
//		for(Text u:t.viewAllText()){
//			System.out.println(u.getTitle());
//			
//		}
		
		try {
			Thumbnails.of("D:/work/workplace/MyeclipseArea/gzszlwiki/WebRoot/image/user/DefaultImg.jpg")
			.size(36, 36)
			.keepAspectRatio(false)
			.outputFormat("jpg")
			.toFile("D:/work/workplace/MyeclipseArea/gzszlwiki/WebRoot/image/user/defaultAvatar_mid");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
