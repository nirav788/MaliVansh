package com.example.developer.myapplication.Beans;

/**
 * Created by Developer on 02-09-2016.
 */
public class Bean_Flatu {


    String TEACHER_ID, NARRATION, CLASS_NAME, TITLE, FIRST_NAME, MIDDLE_NAME,
            LAST_NAME;

    public Bean_Flatu() {
        super();
        TEACHER_ID = null;
        NARRATION = null;
        CLASS_NAME = null;
        TITLE = null;
        FIRST_NAME = null;
        MIDDLE_NAME = null;
        LAST_NAME = null;
    }

    public Bean_Flatu(String tEACHER_ID, String nARRATION,
                          String cLASS_NAME, String tITLE, String fIRST_NAME,
                          String mIDDLE_NAME, String lAST_NAME) {
        super();
        TEACHER_ID = tEACHER_ID;
        NARRATION = nARRATION;
        CLASS_NAME = cLASS_NAME;
        TITLE = tITLE;
        FIRST_NAME = fIRST_NAME;
        MIDDLE_NAME = mIDDLE_NAME;
        LAST_NAME = lAST_NAME;
    }

    public String getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(String tEACHER_ID) {
        TEACHER_ID = tEACHER_ID;
    }

    public String getNARRATION() {
        return NARRATION;
    }

    public void setNARRATION(String nARRATION) {
        NARRATION = nARRATION;
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String cLASS_NAME) {
        CLASS_NAME = cLASS_NAME;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String tITLE) {
        TITLE = tITLE;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String fIRST_NAME) {
        FIRST_NAME = fIRST_NAME;
    }

    public String getMIDDLE_NAME() {
        return MIDDLE_NAME;
    }

    public void setMIDDLE_NAME(String mIDDLE_NAME) {
        MIDDLE_NAME = mIDDLE_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String lAST_NAME) {
        LAST_NAME = lAST_NAME;
    }

}
