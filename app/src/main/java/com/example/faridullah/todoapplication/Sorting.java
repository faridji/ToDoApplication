package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Haierrr on 5/16/2016.
 */
public class Sorting{

    public void sort_A_Z(final ArrayList list){

      Collections.sort(list, new Comparator() {
          @Override
          public int compare(Object lhs, Object rhs) {
              String s1= lhs.toString();
              String s2= rhs.toString();
              return s1.compareTo(s2);
          }
      });
    }

    public void sort_Z_A(final ArrayList list){
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String s1 = rhs.toString();
                String s2 = lhs.toString();
                return s1.compareTo(s2);
            }
        });
    }



}
