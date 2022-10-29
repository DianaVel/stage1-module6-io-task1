package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder input = new StringBuilder();
        try (java.io.FileReader reader = new java.io.FileReader(file)){
            int ch ;
            while ((ch = reader.read())!=-1){
               input.append((char)ch);
            }
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage());
        }
        return parseInfo(input.toString());
    }

    private Profile parseInfo (String info){
        List <String> lines =info.lines().collect(Collectors.toList());
        Map <String, String> pairs = new HashMap<>();
        String key;
        String val;
        for(int i=0; i<lines.size(); i++){
            String temp = lines.get(i);
            key = temp.substring(0,temp.indexOf(":"));
            val = temp.substring(temp.indexOf(" ")+1);
            pairs.put(key,val);
        }
        return new Profile(pairs.get("Name"), Integer.parseInt(pairs.get("Age")),pairs.get("Email"),Long.parseLong(pairs.get("Phone")));
    }
}
