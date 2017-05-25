package net.onyxmueller.android.fruity.reminders;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import net.onyxmueller.android.fruity.QuizActivity;
import net.onyxmueller.android.fruity.R;
import net.onyxmueller.android.fruity.data.DatabaseManager;
import net.onyxmueller.android.fruity.data.SortType;

public class ReminderService extends IntentService {

    private static final String TAG = ReminderService.class.getSimpleName();

    private static final int NOTIFICATION_ID = 42;

    public ReminderService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Quiz reminder event triggered");

        //Present a notification to the user
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Create action intent
        Cursor cursor = DatabaseManager.getInstance(this).queryAllFruits(SortType.NONE);
        Intent action = QuizActivity.newIntent(this, cursor);
        cursor.close();
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(QuizActivity.class);
        stackBuilder.addNextIntent(action);

        PendingIntent operation = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification note = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_fruity_notification_icon)
                .setContentIntent(operation)
                .setAutoCancel(true)
                .build();

        manager.notify(NOTIFICATION_ID, note);
        AlarmReceiver.scheduleAlarm(this);
    }
}
