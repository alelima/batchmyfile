package org.nitrox.batchmyfile.entity;

import org.nitrox.batchmyfile.file.FilePartType;

public enum MyFilePartDescriptor implements FilePartType {
    HEADER("A"),
    DETAIL("B"),
    TRAILLER("C"),
    PART_DESCRIPTOR("-1");

    private String value;

    MyFilePartDescriptor(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
