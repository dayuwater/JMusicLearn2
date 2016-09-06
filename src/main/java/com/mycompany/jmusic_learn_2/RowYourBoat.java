package com.mycompany.jmusic_learn_2;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.music.tools.*;

// based on a sample code from http://explodingart.com/mmwj/ch5/RowYourBoat.java
// I added a 4th part as the solution of one problem in that book

/**
 * Plays a melody as a round in three parts
 *
 * @author Andrew Sorensen and Andrew Brown
 */
public class RowYourBoat implements JMC {

    // declare instance variables
    Part flute, trumpet, clarinet, violin;
    Phrase phrase1, phrase2, phrase3, phrase4;
    Score score;
    int[] pitchArray;
    double[] rhythmArray;

    public static void main(String[] args) {
        RowYourBoat row = new RowYourBoat();
        row.makeMusicData();
        row.structureData();
        row.playAndSave();
    }

    // Constructor
    // Called when 'new RowYourBoat' is executed
    public RowYourBoat() {
        flute = new Part(FLUTE, 0);
        trumpet = new Part(TRUMPET, 1);
        clarinet = new Part(CLARINET, 2);
        violin=new Part(VIOLIN,3);
        // create empty phrases
        phrase1 = new Phrase(0.0);
        phrase2 = new Phrase(0.0);
        phrase3 = new Phrase(0.0);
        phrase4 = new Phrase(0.0);
        //Create the data objects we want to use
        score = new Score("Row Your Boat", 120);
        //Lets write the music in a convenient way.

        int pitchArray1[] = {C4, C4, C4, D4, E4, E4, D4, E4, F4, G4, C5, C5, C5,
            G4, G4, G4, E4, E4, E4, C4, C4, C4, G4, F4, E4, D4, C4};
        double rhythmArray1[] = {QN, QN, QNT, ENT, QN, QNT, ENT, QNT, QT, HN,
            ENT, ENT, ENT, ENT, ENT, ENT, ENT, ENT, ENT, ENT, ENT, ENT, QNT,
            ENT, QNT, ENT, HN};
        pitchArray = pitchArray1;
        rhythmArray = rhythmArray1;
    }

    public void structureData() {
        //add phrases to the parts
        flute.addPhrase(phrase1);
        trumpet.addPhrase(phrase2);
        clarinet.addPhrase(phrase3);
        violin.addPhrase(phrase4);

        //add parts to the score
        score.addPart(flute);
        score.addPart(trumpet);
        score.addPart(clarinet);
        score.addPart(violin);
    }

    public void makeMusicData() {
        //add the notes to a phrase
        phrase1.addNoteList(pitchArray, rhythmArray);

        //Make two new phrases and change start times to make a round
        phrase2 = phrase1.copy();
        phrase2.setStartTime(4.0);
        phrase3 = phrase1.copy();
        phrase3.setStartTime(8.0);
        phrase4=phrase1.copy();
        phrase4.setStartTime(12.0);

        
        Mod.transpose(phrase1, 12);
        Mod.transpose(phrase3, -12);
        Mod.transpose(phrase4, 7);
    }

    private void playAndSave() {
        //Now we do a SMF write 
        Write.midi(score, "Rowboat.mid");
        //Now play it back
        Play.midi(score);
    }
}
