package com.mycompany.jmusic_learn_2;
import jm.util.*;

// based on a sample code from http://explodingart.com/mmwj/ch3/Interpolation.java
// I changed the linear interpolation into catmul rom spline interpolation

public final class Interpolation {	
    public static void main(String[] args){	
        float[] data = Read.audio("/Users/tanwang/Desktop/jm/RowBoat.mid");	
        double originalPitch = 440.0;	
        double newPitch = 220.0;	
        double resamplingRatio = newPitch/originalPitch;	
        float[] newData = new float[	
            (int)(data.length / resamplingRatio) + 1];	
        int counter = 0;	
        for(double i=0.0; i<data.length - 3;	
            i += resamplingRatio) {	
            float sample1 = data[(int)i];	
            float sample2 = data[(int)i + 1];	
            float sample3 = data[(int)i + 2];
            float sample4 = data[(int)i + 3];
            
            
            float t=(float)(i%1.0);
            // catmul rom spline interpolation
            float newSampleValue = (float)(0.5*((-sample1+3*sample2-3*sample3+sample4)*t*t*t+
                    (2*sample1-5*sample2+4*sample3-sample4)*t*t+
                    (-sample1+sample3)*t+2*sample2));
            newData[counter++] = newSampleValue;	
        }	
        Write.audio(newData, "/Users/tanwang/Desktop/jm/LinearInterpolated.aif");	
    }	
}