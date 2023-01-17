package sdf.ibf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    String dirPath = "c:/Users/aqifs/ibf/fortune_cookie/cookie-folder";
    String fileName = "cookie_file.txt";
    List<String> cookie = null;

    public void readCookieFile() throws IOException {
        File newDirectory = new File(dirPath);
        cookie = new ArrayList<>();
        File file = new File(dirPath + File.separator + fileName);
        if (!newDirectory.exists()) {
            newDirectory.mkdir();
            FileWriter writer = new FileWriter((dirPath + File.separator + fileName));
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String readString;

        while ((readString = br.readLine()) != null) {
            cookie.add(readString);
        }
        br.close();
    }

    public String returnCookie() {
        Random random = new Random();

        if (cookie != null) {
            return cookie.get(random.nextInt(cookie.size()));
        } else {
            return "There are no cookies found.";
        }
    }

    public void showCookies() {
        if (cookie != null) {
            cookie.forEach(c -> System.out.println(c));
        }
    }
}
