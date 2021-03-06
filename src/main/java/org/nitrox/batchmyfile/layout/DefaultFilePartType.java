package org.nitrox.batchmyfile.layout;

import org.nitrox.batchmyfile.file.FilePartType;

public enum DefaultFilePartType implements FilePartType {
    HEADER("0") ,
    DETAIL("1"),
    TRAILLER("2"),
    PART_DESCRIPTOR("-1");

    private String value;

    private DefaultFilePartType(String value) {

        this.value = value;
    }

    @Override
    public String getValue() {

        return value;
    }

}
