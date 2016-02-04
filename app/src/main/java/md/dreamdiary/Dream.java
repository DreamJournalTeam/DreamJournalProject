package md.dreamdiary;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.Serializable;
import android.graphics.Bitmap;

/**
 * Created by michaeldiamond on 9/14/15.
 */
public class Dream implements Serializable {

    String text;
    String title;
    String date;

    String newEnding;

    //Image drawing

    DreamType type;

    int timesRepeated;

    Boolean nightmare;
    Boolean drawing;
    Boolean isRepeat;
    Boolean endingChanged;

    ArrayList<String> tags;
    ArrayList<String> properNouns;

    String q1Answer, q2Answer, q3Answer;


    // Do something with dream type


    public Dream() {

        text = "";
        title = "";
        date = "";

        timesRepeated = 0;

        newEnding = "";

        type = DreamType.NONE;
        nightmare = false;
        drawing = false;
        isRepeat = false;
        endingChanged = false;

        tags = new ArrayList<>();
        properNouns = new ArrayList<>();

        q1Answer = "Did not answer";
        q2Answer = "Did not answer";
        q3Answer = "Did not answer";

    }

    // get and set methods

    // text
    public void setText(String input) {
        text = input;
    }

    public String getText() {
        return text;
    }

    // title
    public void setTitle(String input) {
        title = input;
    }

    public String getTitle() {
        return title;
    }

    // date
    public void setDate(String input) {
        date = input;
    }

    public String getDate() {
        return date;
    }

    // numRepeats
    public void increaseRepeats() {
        timesRepeated++;
    }

    public void decreaseRepeats() {
        timesRepeated--;
    }

    public int getTimesRepeated() {
        return timesRepeated;
    }

    // isRepeat
    public void setIfRepeat(Boolean input) {
        isRepeat = input;
    }

    public Boolean getIfRepeat() {
        return isRepeat;
    }

    // nightmare
    public void setNightmare(Boolean input) {
        nightmare = input;
    }

    public Boolean getIfNightmare() {
        return nightmare;
    }

    // endingChanged
    public void setEndingChanged() {
        endingChanged = true;
    }

    public void setEndingNotChanged() {
        endingChanged = false;
    }

    public Boolean hasEndingChanged() {
        return endingChanged;
    }

    // tags
    public void addTag(String input) {
        tags.add(input);
    }

    public void removeTag(String input) {
        tags.remove(input);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    // properNouns
    public void addNoun(String input) {
        properNouns.add(input);
    }

    public void removeNoun(String input) {
        properNouns.remove(input);
    }

    public ArrayList<String> getNouns() {
        return properNouns;
    }

    // question answers
    public void setQ1Answer(String input) {
        q1Answer = input;
    }

    public void setQ2Answer(String input) {
        q2Answer = input;
    }

    public void setQ3Answer(String input) {
        q3Answer = input;
    }

    public String getQ1Answer() {
        return q1Answer;
    }

    public String getQ2Answer() {
        return q2Answer;
    }

    public String getQ3Answer() {
        return q3Answer;
    }

    // DreamType getter/setter
    public DreamType getType() {return type;}

    public void setType(DreamType t) {type = t;}

    public String getNewEnding() {return newEnding;}

    public void setNewEnding(String newNew) {newEnding = newNew;}

    public void clearTags() {
        tags.clear();
    }

    public void clearNouns() {
        properNouns.clear();
    }


}
