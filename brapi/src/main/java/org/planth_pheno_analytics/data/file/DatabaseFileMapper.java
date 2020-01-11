package org.planth_pheno_analytics.data.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.planth_pheno_analytics.brapi.handler.ReadingFileException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DatabaseFileMapper {
    public DatabaseFile mapToDbFile(String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), DatabaseFile.class);
        } catch (IOException e) {
            throw new ReadingFileException("Cannot read json file", e);
        }
    }
}
