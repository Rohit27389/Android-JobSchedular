package com.rohit.jobshedulara;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private final String TAG = "JobServiceEx";
    int JOB_ID = 101;
    JobScheduler scheduler;
    JobInfo info;
    public Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.jobIn);
        ComponentName componentName = new ComponentName(this, JobServiceEx.class);
        info = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(5000)
                .build();

        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job Schedule");
        } else {
            Log.d(TAG, "Job Schedule failed: ");
        }
    }
    public void stopJobService(View view) {
        scheduler.cancel(JOB_ID);
        Log.d(TAG, "stopJobService");
    }
}

