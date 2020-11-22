package app.awish.client.syncModule;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;



import app.awish.client.app.AwishApplication;
import app.awish.client.utilities.Logger;
import app.awish.client.utilities.SessionManager;


public class VisitSummaryWork extends Worker {

    private SessionManager sessionManager = null;
    private String TAG = VisitSummaryWork.class.getSimpleName();

    public VisitSummaryWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        sessionManager = new SessionManager(context);
    }


    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            Logger.logE(TAG, "Exception in doWork method", e);
        }
        Logger.logD(TAG, "doWork");

        Intent in = new Intent();
        in.setAction("downloadprescription");
        AwishApplication.getAppContext().sendBroadcast(in);

        return Result.success();
    }
}
