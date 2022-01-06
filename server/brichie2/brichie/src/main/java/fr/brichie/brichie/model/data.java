package fr.brichie.brichie.model;
import org.apache.commons.lang3.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
public class data {

    @NotEmpty(message = "DB's name cannot be empty.")
    public String db;
    @NotEmpty(message = "serie's name cannot be empty.")
    public String serie;
    public String[] content={""};
    public ArrayList<String> keys = new ArrayList<String>();
    public ArrayList<String> values = new ArrayList<String>();
    public String getDb() {
        return db;
      }
    
      public void setDb(String db) {
        this.db = db;
      }
    
      public String getSerie() {
        return serie;
      }
    
      public void setSerie(String serie) {
        this.serie = serie;
      }
    public void refresh(){
        File[] existing_databases = new File("../../2021bddra-equipe2-storage/src/main/java/aionDB/databases/").listFiles(new FilenameFilter() 
		{
			@Override
			public boolean accept(File dir, String name) 
			{                         
				return name.endsWith(".txt");                     
			}                 
		});
		// Now we want to get their name :
		HashSet<String> results = new HashSet<String>();
		for (File file : existing_databases) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
        if(!results.contains(db+".txt"))
        {
            db = "Unknown DB, available are : " + results + " (remove the .txt)";
            return;
        }

        String[] allSerie = {""};
        String fileContent = "";
        try{
            Path pathToDB = Paths.get("../../2021bddra-equipe2-storage/src/main/java/aionDB/databases/"+db+".txt/");
            fileContent = new String(Files.readAllBytes(pathToDB), StandardCharsets.UTF_8);
            //System.out.println("CONTENU FICHIER  : " + fileContent);
            allSerie = StringUtils.substringsBetween(fileContent, "$", "$");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        int indexOfSerie = Arrays.asList(allSerie).indexOf(serie);
        if (indexOfSerie == -1)
        {
            serie = "unknown Serie, available are : "+Arrays.asList(allSerie);
            return;
        }

        String[] allSerieAndContent = fileContent.split("\\$");
        indexOfSerie = Arrays.asList(allSerieAndContent).indexOf(serie);
        if(indexOfSerie+1 == allSerieAndContent.length)
        {
            content[0] = "empty";
            return;
        }
        content = allSerieAndContent[indexOfSerie+1].split(",");
        for (String item: content)
        {
            keys.add(item.split(":")[0]);
            values.add(item.split(":")[1]);
        }
    }   
}

