

package main;

import java.io.IOException;

//import java.Instrumentation;

class Bubble_with_instrumentation extends SortAlgorithm
{
    Instrumentation ins = Instrumentation.getInstance();


    int[] sort(int a[]) throws IOException
    {
        ins.startTiming("Bubble sort in class");

        int[] t = new int[1];
        t[0] = 0;

        ins.activate(true);
        for (int i = a.length; --i >= 0;)
        {
            boolean flipped = false;


            for (int j = 0; j < i; j++)
            {
                if (stopRequested)
                {
                    return t;
                }
                super.compares++;
                super.activeMarker = j;
                if (a[j] > a[j + 1])
                {
                    int T = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = T;
                    super.moves++;
                    flipped = true;
                }
                super.updateAllViews();
            }


            if (!flipped)
            {
                ins.stopTiming("Bubble sort in class");
                return a;
            }
            super.hiMarker = i;
            super.updateAllViews();
        }


        super.updateAllViews(-1, -1);

        return a;
    }
}
