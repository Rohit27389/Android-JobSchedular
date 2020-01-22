package com.rohit.jobshedulara;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by Rohit Kumar on 21-01-2020.
 */

public class JobServiceEx extends JobService {
    public static final String TAG="JobService";
    private boolean isJobCancelled=false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");
        doInBackground(params);
        return true;
    }

    private void doInBackground(final JobParameters jobParameters){

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<=10;i++){
                    Log.d(TAG, "run: " + i);
                    if(isJobCancelled){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: " + "Job FInished");
                    jobFinished(jobParameters,false);
                }
            }
        }).start();
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: " +"Job Finished");
        isJobCancelled=true;
        return false;
    }
}
