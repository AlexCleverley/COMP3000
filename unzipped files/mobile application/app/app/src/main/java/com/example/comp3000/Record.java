package com.example.comp3000;

public class Record {
    private int recordID;
    private byte[] recordImage;
    private String recordAttributes;

    public Record(int recordID, byte[] recordImage, String recordAttributes) {
        this.recordID = recordID;
        this.recordImage = recordImage;
        this.recordAttributes = recordAttributes;
    }

    public int getRecordID() {
        return  recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public byte[] getRecordImage() {
        return recordImage;
    }

    public void setRecordImage(byte[] recordImage) {
        this.recordImage = recordImage;
    }

    public String getRecordAttributes() {
        return recordAttributes;
    }

    public void setRecordAttributes(String recordAttributes) {
        this.recordAttributes = recordAttributes;
    }
}
