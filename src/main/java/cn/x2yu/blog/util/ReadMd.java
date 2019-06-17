package cn.x2yu.blog.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class ReadMd {

    public String readMdFile(String filename)throws Exception{
        Resource resource = new ClassPathResource("markdown/"+filename);
        File file = resource.getFile();
       // System.out.println(file.getName());

        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        String content = "";
        while ((str = in.readLine()) != null) {
            // System.out.println(str);
            content = content.concat(str+"\n");
        }
     //   System.out.println(content);
        return content;
    }
}
