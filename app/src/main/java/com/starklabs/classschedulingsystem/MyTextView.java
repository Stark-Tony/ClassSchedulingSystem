package com.starklabs.classschedulingsystem;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MyTextView extends AppCompatTextView {

    int status;
    String profid;
    String slotid;
    String subjectid;

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyTextView,0,0);
        try {
            status = typedArray.getInteger(R.styleable.MyTextView_status,-1);
            profid = typedArray.getString(R.styleable.MyTextView_profid);
            slotid = typedArray.getString(R.styleable.MyTextView_slotid);
            subjectid = typedArray.getString(R.styleable.MyTextView_subjectid);
        }finally {
            typedArray.recycle();
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProfid() {
        return profid;
    }

    public void setProfid(String profid) {
        this.profid = profid;
    }

    public String getSlotid() {
        return slotid;
    }

    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }
}
