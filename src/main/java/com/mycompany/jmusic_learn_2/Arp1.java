
package com.mycompany.jmusic_learn_2;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.music.tools.Mod;


// based on a sample code from Page 102 in Making Music with Java by Andrew R. Brown
// I added a chord progression from C to F to G 

/**
 *
 * @author tanwang
 */
public class Arp1 implements JMC {
    int rootNode=C2;
    int[] pattern={0,4,7,4};
    Phrase phr;
    Phrase phr2;
    Phrase phr3;
    Phrase phr4;
    Part p;
    
    public static void main(String[] args){
        Arp1 myArp=new Arp1();
        myArp.arpeggiator();
        myArp.saveAs();
    }
    
    public void arpeggiator(){
        phr=new Phrase(0.0);
        p=new Part(GUITAR);
        for(int i=0; i<pattern.length;i++){
            phr.addNote(new Note(rootNode+pattern[i],SN));
        }
        phr2=phr.copy();
        phr3=phr.copy();
        phr4=phr.copy();
        Mod.repeat(phr,8);
        phr2.setStartTime(8.0);
        Mod.repeat(phr2,4);
        Mod.transpose(phr2, 5);
        phr3.setStartTime(12.0);
        Mod.repeat(phr3,4);
        Mod.transpose(phr3, 7);
        phr4.setStartTime(16.0);
        Mod.repeat(phr4,8);
        
        p.addPhrase(phr);
        p.addPhrase(phr2);
        p.addPhrase(phr3);
        p.addPhrase(phr4);
        
        
        
    }
    
    public void saveAs(){
        Write.midi(p,"Arp1.mid");
        Play.midi(p);
    }
    
}
