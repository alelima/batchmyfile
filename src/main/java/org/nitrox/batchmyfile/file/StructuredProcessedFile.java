package org.nitrox.batchmyfile.file;

import java.util.List;
import java.util.Map;

public class StructuredProcessedFile {

    Map<FilePartType, List<Map<String, Object>>> structuredFileMap;

    public void put(FilePartType part, List<Map<String, Object>> lines) {
        structuredFileMap.put(part, lines);
    }

    public List<Map<String, Object>> get(FilePartType part) {
        return structuredFileMap.get(part);
    }
}
