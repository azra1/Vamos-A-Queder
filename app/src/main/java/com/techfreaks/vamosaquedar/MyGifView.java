package com.techfreaks.vamosaquedar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Azra on 2/10/2018.
 */

public class MyGifView extends View {

        Movie movie,movie1;
        InputStream is=null,is1=null;
        long moviestart;
        @SuppressLint("ResourceType")
        public MyGifView(Context context) {
            super(context);

            is=context.getResources().openRawResource(R.drawable.posteventimage);
            movie=Movie.decodeStream(is);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);
            long now=android.os.SystemClock.uptimeMillis();
            System.out.println("now="+now);
            if (moviestart == 0) { // first time
                moviestart = now;

            }
            System.out.println("\tmoviestart="+moviestart);
            int relTime = (int)((now - moviestart) % movie.duration()) ;
            System.out.println("time="+relTime+"\treltime="+movie.duration());
            movie.setTime(relTime);
            float scalefactorx = (float) this.getWidth() / (float) movie.width();
            float scalefactory = (float) this.getHeight() / (float) movie.height();
            canvas.scale(scalefactorx,1);
            movie.draw(canvas, scalefactorx, scalefactory);
            this.invalidate();
        }
    }
